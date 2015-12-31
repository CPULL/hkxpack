package com.dexesttp.analysis.resources;

public class UnhandledFileTypeException extends Exception {
	private static final long serialVersionUID = -6382934038936417183L;
	
	public UnhandledFileTypeException(String format) {
		super("Unknown file type : " + format);
	}

}
