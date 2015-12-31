package com.dexesttp.analysis.model.hkobjects;

public class HkClassObject extends HkObject {
	public String className;
	public long namePos;
	public HkClassObject(long uid, long namePos, long flagPos) {
		this.uid = uid;
		this.namePos = namePos;
		this.filePos = flagPos;
		this.content = new byte[40];
	}

}
