package com.dexesttp.afff.parsing.parsers;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.afff.model.Struct;
import com.dexesttp.afff.model.structs.File_hk_2010_behavior_struct;
import com.dexesttp.afff.parsing.AbstractParser;
import com.dexesttp.afff.resources.Utils;

public class File_hk_2010_behavior_parser extends AbstractParser {


	@Override
	public Struct parse(RandomAccessFile in) throws IOException{
		File_hk_2010_behavior_struct struct = new File_hk_2010_behavior_struct();
		
		// Go to file beginning.
		in.seek(0);
		
		// Read each element as needed.
		in.read(struct.header);		// Header
		in.read(struct.classname);	// __classname__ data
		in.read(struct.types);		// __types__ data
		in.read(struct.data);		// __data__... data
		fillStructOffsets(struct);	// Find offsets in data.
		
		readClassNames(in, struct);	// All names of used class w/ their 4-byte code after it.
		readData(in, struct);
		fillData(in, struct, struct.data1);
		fillData(in, struct, struct.data2);
		fillData(in, struct, struct.data3);
		
		return struct;
	}

	private void fillStructOffsets(File_hk_2010_behavior_struct struct) {
		struct.classname_offset = Utils.makeLong(struct.classname, 20, 24, true);
		struct.classname_size = Utils.makeLong(struct.classname, 24, 28, true);
		struct.types_offset = Utils.makeLong(struct.types, 20, 24, true);
		struct.data_offset = Utils.makeLong(struct.data, 20, 24, true);
		struct.data1_offset = Utils.makeLong(struct.data, 24, 28, true);
		struct.data2_offset = Utils.makeLong(struct.data, 28, 32, true);
		struct.data3_offset = Utils.makeLong(struct.data, 32, 36, true);
		struct.eof_offset = Utils.makeLong(struct.data, 36, 40, true);
	}
}
