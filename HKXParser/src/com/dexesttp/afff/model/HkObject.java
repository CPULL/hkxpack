package com.dexesttp.afff.model;

public class HkObject {
	public long filePos;
	public HkObject(long pos) {
		this.filePos = pos;
	}
	public byte[] content = new byte[8];
}
