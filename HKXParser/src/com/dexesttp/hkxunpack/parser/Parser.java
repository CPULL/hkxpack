package com.dexesttp.hkxunpack.parser;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.hkxunpack.object.Header;
import com.dexesttp.hkxunpack.object.HKXNode;
import com.dexesttp.hkxunpack.object.Section;
import com.dexesttp.hkxunpack.object.classobjet.ClassObject;
import com.dexesttp.hkxunpack.object.classobjet.Classes;
import com.dexesttp.hkxunpack.resources.ByteUtils;
import com.dexesttp.hkxunpack.resources.FileUtils;
import com.dexesttp.hkxunpack.resources.InvalidFormatException;

public class Parser {
	public void parseFile(RandomAccessFile in) throws IOException, InvalidFormatException {
		FileUtils.initFolder();
		HeaderParser headParser = new HeaderParser();
		Header header = headParser.parseHeader(in);
		
		SectionParser sectionParser = new SectionParser(header);
		Section classname = sectionParser.parseSection(in, 0);
		Section data = sectionParser.parseSection(in, 2);
		
		ClassParser classParser = new ClassParser();
		Classes classes = classParser.parseClasses(in, classname);
		
		ContentParser contentParser = new ContentParser();
		HKXNode dataNodes = contentParser.generateNodes(in, data, ByteUtils.getInt(classname.offset), classes);
		
	}
}
