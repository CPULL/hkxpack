package com.dexesttp.afff.model;

public class HkInternalObject extends HkObject {
	public HkInternalObject(long pos) {
		this.filePos = pos;
		this.content = new byte[8];
	}
}
