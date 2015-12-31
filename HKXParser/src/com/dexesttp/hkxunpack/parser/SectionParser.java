package com.dexesttp.hkxunpack.parser;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.hkxunpack.object.Header;
import com.dexesttp.hkxunpack.object.Section;

public class SectionParser {
	private int offset = 0;
	private int skip = 0;
	public SectionParser(Header head) {
		if(head.version[0] >= 11) {
			offset = head.val2[0];
			skip  = 0x10;
		}
	}
	
	public Section parseSection(RandomAccessFile in, int sectionID) throws IOException {
		Section section = new Section();
		in.seek(0x40 + offset + (0x30 + skip) * sectionID);
		in.read(section.name);
		in.read(section.junk);
		in.read(section.offset);
		in.read(section.part1);
		in.read(section.part2);
		in.read(section.part3);
		in.read(section.part4);
		in.read(section.part5);
		in.read(section.part6);
		return section;
	}
}
