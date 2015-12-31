package com.dexesttp.hkxunpack.object.classobjet;

import java.util.HashMap;
import java.util.Map;

public class Classes {
	private Map<String, ClassObject> classes = new HashMap<String, ClassObject>();
	private Map<String, EnumObject> enums = new HashMap<String, EnumObject>();
	
	public ClassObject getClass(String classname) {
		return classes.get(classname);
	}

	public void putClass(String classname, ClassObject classObj) {
		classes.put(classname, classObj);
	}
	public EnumObject getEnum(String enumname) {
		return enums.get(enumname);
	}
	
	public void putEnum(String enumname, EnumObject classObj) {
		enums.put(enumname, classObj);
	}
}

