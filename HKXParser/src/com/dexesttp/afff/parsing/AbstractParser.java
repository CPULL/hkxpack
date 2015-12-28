package com.dexesttp.afff.parsing;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collection;

import com.dexesttp.afff.model.HkExternalObject;
import com.dexesttp.afff.model.HkInternalObject;
import com.dexesttp.afff.model.Struct;
import com.dexesttp.afff.resources.Utils;

public abstract class AbstractParser implements Parser {

	protected void readClassNames(RandomAccessFile in, Struct struct) throws IOException {
		byte[] value = new byte[4];
		byte[] valueTemp = new byte[3];
		// Put cursor to the offset for classes
		in.seek(struct.classname_offset);
		byte b = in.readByte();
		// Seek struct.classname_size bytes of value or the FF at the end.
		while(in.getFilePointer() < struct.classname_size + struct.classname_offset) {
			in.read(valueTemp);
			if(in.readByte() == -1)
				break;
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
	}
	
	protected void readExternalData(RandomAccessFile in, Struct struct) throws IOException {
		final long data1pos = struct.data1_offset + struct.data_offset;
		final long data2pos = struct.data2_offset + struct.data_offset;
		readExternalData(in, struct.data1, data1pos, data2pos);
	}

	private void readExternalData(RandomAccessFile in, Collection<HkExternalObject> dataList, long begin, long limit) throws IOException {
		final byte[] defaultValue = {-1, -1, -1, -1};
		byte[] value = new byte[4];
		in.seek(begin);
		while(in.getFilePointer() < limit) {
			in.read(value);
			if(! Arrays.equals(value, defaultValue))
				dataList.add(new HkExternalObject(Utils.makeLong(value, 0, 4, true)));
		}
	}
	
	protected void readInternalData(RandomAccessFile in, Struct struct) throws IOException {
		final long data2pos = struct.data2_offset + struct.data_offset;
		final long data3pos = struct.data3_offset + struct.data_offset;
		final long endpos = struct.eof_offset + struct.data_offset;
		readInternalData(in, struct.data2, data2pos, data3pos);
		readInternalData(in, struct.data3, data3pos, endpos);
	}

	private void readInternalData(RandomAccessFile in, Collection<HkInternalObject> dataList, long begin, long limit) throws IOException {
		final byte[] defaultValue = {-1, -1, -1, -1};
		byte[] value = new byte[4];
		in.seek(begin);
		while(in.getFilePointer() < limit) {
			in.read(value);
			if(! Arrays.equals(value, defaultValue)) {
				HkInternalObject created = new HkInternalObject(Utils.makeLong(value, 0, 4, true));
				created.content = value.clone();
				dataList.add(created);
			}
		}
	}

	protected void fillData(RandomAccessFile in, Struct struct, Collection<HkExternalObject> dataList) throws IOException {
		final long offset = struct.data_offset;
		for(HkExternalObject obj : dataList) {
			in.seek(obj.filePos + offset);
			in.read(obj.content);
		}
	}
}
