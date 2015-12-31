package com.dexesttp.analysis.model.hkobjects;

public class HkExternalObject extends HkObject {
	public HkExternalObject(long uid, long pos) {
		this.uid = uid;
		this.filePos = pos;
		this.content = new byte[8];
	}
}
