package com.dexesttp.analysis.resources;

import java.util.HashMap;

public class OptionContainer {
	HashMap<String, String> list = new HashMap<String, String>();
	
	public void add(String key, String value) {
		list.put(key, value);
	}
	
	public String getValue(String key) {
		return list.get(key);
	}
}
