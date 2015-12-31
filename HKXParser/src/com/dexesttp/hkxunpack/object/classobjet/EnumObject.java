package com.dexesttp.hkxunpack.object.classobjet;

import java.util.HashMap;
import java.util.Map;

public class EnumObject {
	private String enumName;
	private String flags;
	
	public Map<String, Integer> entries = new HashMap<>();
	
	public EnumObject(String name, String flags) {
		this.enumName = name;
		this.flags = flags;
	}
	
	public void addEntry(String name, int value) {
		entries.put(name, value);
	}
}
