package com.dexesttp.analysis.model.structs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.dexesttp.analysis.model.hkobjects.HkClassObject;
import com.dexesttp.analysis.model.hkobjects.HkExternalObject;
import com.dexesttp.analysis.model.hkobjects.HkInternalObject;

public abstract class Struct {
	// Header & head data
	public byte[] header = new byte[64];
	
	// Values and positions depends of file type
	public byte[] classname;
	public byte[] types;
	public byte[] data;
	
	// These are extracted from the above, varies by file type too.
	public long classname_offset;
	public long classname_size;
	public long types_offset;
	public long data_offset;
	public long data1_offset;
	public long data2_offset;
	public long data3_offset;
	public long eof_offset;
	
	// Classes & classes values
	public HashMap<String, byte[]> classes = new HashMap<String, byte[]>();
	
	// Data1 content
	public ArrayList<HkExternalObject> data1 = new ArrayList<>();
	// Data2 content
	public Collection<HkInternalObject> data2 = new ArrayList<>();
	// Data3 content
	public ArrayList<HkClassObject> data3 = new ArrayList<>();
}
