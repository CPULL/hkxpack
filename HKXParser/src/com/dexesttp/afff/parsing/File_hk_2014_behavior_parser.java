package com.dexesttp.afff.parsing;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.afff.model.Struct;
import com.dexesttp.afff.resources.Utils;

public class File_hk_2014_behavior_parser extends AbstractParser {


	@Override
	public Struct parse(RandomAccessFile in) throws IOException{
		Struct struct = new Struct();
		
		// Go to file beginning.
		in.seek(0);
		
		// Read each element as needed.
		in.read(struct.header);		// Header
		in.read(struct.classname);	// __classname__ data
		in.read(struct.types);		// __types__ data
		in.read(struct.data);		// __data__... data
		fillStructOffsets(struct);	// Find offsets in data.
		
		readClassNames(in, struct);	// All names of used class w/ their 4-byte code after it.
		readData1(in, struct);
		
		fillData1(in, struct);
		
		return struct;
	}

	private void fillStructOffsets(Struct struct) {
		struct.classname_offset = Utils.makeLong(struct.classname, 20, 24, true);
		struct.classname_size = Utils.makeLong(struct.classname, 24, 28, true);
		struct.types_offset = Utils.makeLong(struct.types, 20, 24, true);
		struct.data_offset = Utils.makeLong(struct.data, 20, 24, true);
		struct.data1_offset = Utils.makeLong(struct.data, 24, 28, true);
		struct.data2_offset = Utils.makeLong(struct.data, 28, 32, true);
		struct.data3_offset = Utils.makeLong(struct.data, 32, 36, true);
		struct.eof_offset = Utils.makeLong(struct.data, 36, 40, true);
	}

	// TODO : rewrite this using the offset/size and the readLine method
	private void readClassNames(RandomAccessFile in, Struct struct) throws IOException {
		byte[] value = new byte[4];
		byte[] valueTemp = new byte[3];
		// Put cursor to the offset for classes
		in.seek(struct.classname_offset);
		byte b = in.readByte();
		// Seek struct.classname_size bytes of value or the FF at the end.
		while(b != -1 && in.getFilePointer() < struct.classname_size + struct.classname_offset) {
			in.read(valueTemp);
			in.skipBytes(1);
			String s = "";
			value[0] = b;
			while((b = in.readByte()) != 0)
				s += (char) b;
			value[1] = valueTemp[0];
			value[2] = valueTemp[1];
			value[3] = valueTemp[2];
			struct.classes.put(s, value.clone());
			b = in.readByte();
		}
		// Go to the end of the FF part
		while(in.readByte() == -1);
		// Return to just after the end of the FF part
	}
}
