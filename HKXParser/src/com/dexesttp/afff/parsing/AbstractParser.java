package com.dexesttp.afff.parsing;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.afff.model.HkObject;
import com.dexesttp.afff.model.Struct;
import com.dexesttp.afff.resources.Utils;

public abstract class AbstractParser implements Parser {
	
	protected void readData1(RandomAccessFile in, Struct struct) throws IOException {
		final long limit = struct.data2_offset + struct.data_offset;
		final byte[] defaultValue = {-128, -128, -128, -128};
		byte[] value = new byte[4];
		in.seek(struct.data1_offset + struct.data_offset);
		while(in.getFilePointer() < limit) {
			in.read(value);
			if(value != defaultValue)
				struct.data1.add(new HkObject(Utils.makeLong(value, 0, 4, true)));
		}
	}

	protected void fillData1(RandomAccessFile in, Struct struct) throws IOException{
		final long offset = struct.data_offset;
		for(HkObject obj : struct.data1)
			obj.fill(in, offset);
	}
}
