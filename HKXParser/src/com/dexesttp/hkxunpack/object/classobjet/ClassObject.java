package com.dexesttp.hkxunpack.object.classobjet;

import java.util.ArrayList;

public class ClassObject {

	String classname;
	byte[] classcode;
	
	ArrayList<ClassMember> contents = new ArrayList<>();
	
	public ClassObject(String classname, byte[] classcode) {
		this.classname = classname;
		this.classcode = classcode;
	}
	
	public void addContent(ClassMember content) {
		contents.add(content);
	}

	public String getName() {
		return classname;
	}
}
