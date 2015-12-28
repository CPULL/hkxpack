package com.dexesttp.afff.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Struct {
	// Header & head data
	public byte[] header = new byte[64];
	public byte[] classname = new byte[64];
	public byte[] types = new byte[64];
	public byte[] data = new byte[64];
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
	public void addClass(String s, byte[] value) {
		classes.put(s, value);
	}
	
	// Behaviour graph data (no idea what it is yet)
	public byte[] behgraph = new byte[528];
	
	// HKB file (I assume at least)
	public String fileName;
	public byte[] fileHeader = new byte[192];
	public ArrayList<HkObject> data1 = new ArrayList<>();
}
