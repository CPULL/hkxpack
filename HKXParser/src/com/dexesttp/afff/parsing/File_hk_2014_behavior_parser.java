package com.dexesttp.afff.parsing;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.afff.model.Struct;

public class File_hk_2014_behavior_parser extends AbstractParser {


	@Override
	public Struct parse(RandomAccessFile in) {
		Struct struct = new Struct();
		try {
			// Read each element as needed.
			in.read(struct.header);		// Header
			in.read(struct.classname);	// __classname__ data
			in.read(struct.types);		// __types__ data
			in.read(struct.data);		// __data__... data
			readClassNames(in, struct);	// All names of used class w/ their 4-byte code after it.
			in.read(struct.behgraph);	// No idea yet what it is.
			readFileHeader(in, struct);
			in.read(struct.fileHeader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return struct;
	}

	private void readClassNames(RandomAccessFile in, Struct struct) throws IOException {
		byte[] value = new byte[4];
		byte[] valueTemp = new byte[3];
		byte b = in.readByte();
		// While there's no 'FF', seek values
		while(b != -1) {
			in.read(valueTemp);
			in.skipBytes(1);
			String s = "";
			value[0] = b;
			while((b = in.readByte()) != 0)
				s += (char) b;
			value[1] = valueTemp[0];
			value[2] = valueTemp[1];
			value[3] = valueTemp[2];
			struct.addClass(s, value.clone());
			b = in.readByte();
		}
		// Go to the end of the FF part
		while(in.readByte() == -1);
		// Return to just after the end of the FF part
		in.seek(in.getFilePointer() - 1);
	}

	private void readFileHeader(RandomAccessFile in, Struct struct) throws IOException {
		byte b;
		String s = "";
		while((b = in.readByte()) != 0)
			s += (char) b;
		struct.fileName = s;
		in.skipBytes((int) (16 - (in.getFilePointer() % 16)));
	}
	
}
