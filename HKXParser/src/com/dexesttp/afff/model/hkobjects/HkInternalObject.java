package com.dexesttp.afff.model.hkobjects;

public class HkInternalObject extends HkObject {
	public HkInternalObject(long uid, long pos) {
		this.uid = uid;
		this.filePos = pos;
		this.content = new byte[8];
	}
}
