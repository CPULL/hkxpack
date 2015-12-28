package com.dexesttp.afff;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.afff.model.structs.Struct;
import com.dexesttp.afff.parsing.Parser;
import com.dexesttp.afff.parsing.ParserFactory;
import com.dexesttp.afff.resources.OptionContainer;
import com.dexesttp.afff.resources.UnhandledFileTypeException;

public class Main {

	OptionContainer options;
	
	public Main(OptionContainer options) {
		this.options = options;
	}

	public Struct start() throws IOException, UnhandledFileTypeException {
		String fileName = options.getValue("file");
		Struct struct = null;
		
		RandomAccessFile file = new RandomAccessFile(new File(fileName), "r");
		Parser p = ParserFactory.getParser(file);
		struct = p.parse(file);
		file.close();
		
		System.out.println("Size 1 : " + struct.data1.size());
		System.out.println("Size 2 : " + struct.data2.size());
		System.out.println("Size 3 : " + struct.data3.size());
		
		return struct;
	}
}
