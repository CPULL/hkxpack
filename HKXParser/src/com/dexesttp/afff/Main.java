package com.dexesttp.afff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import com.dexesttp.afff.model.Struct;
import com.dexesttp.afff.parsing.File_hk_2014_behavior_parser;
import com.dexesttp.afff.parsing.Parser;
import com.dexesttp.afff.resources.OptionContainer;

public class Main {

	OptionContainer options;
	
	public Main(OptionContainer options) {
		this.options = options;
	}

	public Struct start() {
		String fileName = options.getValue("file");
		Parser p = new File_hk_2014_behavior_parser();
		Struct struct = null;
		try {
			struct = p.parse(new RandomAccessFile(new File(fileName), "r"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return struct;
	}
}
