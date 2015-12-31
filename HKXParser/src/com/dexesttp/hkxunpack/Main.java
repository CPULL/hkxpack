package com.dexesttp.hkxunpack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.hkxunpack.parser.Parser;
import com.dexesttp.hkxunpack.resources.InvalidFormatException;

public class Main {
	public static void main(String[] args) {
		String fileName = "D:\\Documents\\SANDBOX\\FO4\\BloatflyRootBehavior.hkx";
		if(args.length > 0)
			fileName = args[0];
		Parser p = new Parser();
		try {
			p.parseFile(new RandomAccessFile(fileName, "r"));
		} catch (FileNotFoundException e) {
			System.err.println("Error : file not found (" + fileName + ")");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error while reading file content. Check your file format.");
			System.exit(1);
		} catch (InvalidFormatException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
