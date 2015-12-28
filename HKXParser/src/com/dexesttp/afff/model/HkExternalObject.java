package com.dexesttp.afff.model;

public class HkExternalObject extends HkObject {
	public HkExternalObject(long pos) {
		this.filePos = pos;
		this.content = new byte[8];
	}
}
