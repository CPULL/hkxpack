package com.dexesttp.hkxunpack.parser;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.hkxunpack.object.Header;

public class HeaderParser {
	public Header parseHeader(RandomAccessFile in) throws IOException {
		Header header = new Header();
		in.read(header.fileID);
		in.read(header.version);
		in.read(header.junk);
		in.read(header.versionName);
		in.read(header.junk2);
		in.read(header.val1);
		in.read(header.val2);
		return header;
	}
}
