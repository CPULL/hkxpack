package com.dexesttp.hkxunpack.resources;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
	private static String folder = "D:\\Documents\\SANDBOX\\FO4\\classDefs";
	public static Map<String, String> filenameConverter = new HashMap<String, String>();
	
	public static void setFolder(String newFolder) {
		folder = newFolder;
	}
	
	public static void initFolder() {
		File dirFile = new File(folder);
		for(final File fileEntry : dirFile.listFiles()) {
			String className = extractName(fileEntry.getName());
			filenameConverter.put(className, fileEntry.getAbsolutePath());
		}
	}
	
	public static String getFileName(String classname) {
		return filenameConverter.get(classname);
	}
	
	private static String extractName(String fullName) {
		return fullName.substring(0, fullName.indexOf("_"));
	}
}
