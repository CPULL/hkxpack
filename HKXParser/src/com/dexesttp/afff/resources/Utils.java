package com.dexesttp.afff.resources;

public class Utils {

	public static String formatBinary(byte[] list) {
		String s = "";
		for(int i = 0; i < list.length; i++) {
			s += String.format("%02X", list[i]);
			if(i%16 == 15)
				s += "\n";
			else if(i%2 == 1)
				s += " ";
		}
		return s;
	}

}
