package com.dexesttp.afff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.afff.model.HkObject;
import com.dexesttp.afff.model.Struct;
import com.dexesttp.afff.parsing.File_hk_2014_behavior_parser;
import com.dexesttp.afff.parsing.Parser;
import com.dexesttp.afff.resources.OptionContainer;
import com.dexesttp.afff.resources.Utils;

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
			RandomAccessFile file = new RandomAccessFile(new File(fileName), "r");
			struct = p.parse(file);
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Offset : " + struct.data_offset);
		
		for(HkObject obj : struct.data1) {
			System.out.println("Object : " + obj.filePos);
			System.out.println("Content : " + Utils.formatBinary(obj.content));
		}
		return struct;
	}
}
