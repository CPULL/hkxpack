package com.dexesttp.afff.model;

import java.io.IOException;
import java.io.RandomAccessFile;

public class HkObject {
	public long filePos;
	public HkObject(long pos) {
		this.filePos = pos;
	}
	public byte[] content = new byte[8];
	public void fill(RandomAccessFile in, long offset) throws IOException {
		in.seek(filePos + offset);
		in.read(content);
	}
}
