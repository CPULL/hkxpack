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

	public static long makeLong(byte[] list, int begin, int end, boolean reversed) {
		final int len = end - begin;
		int accu = 1;
		long res = 0;
		if(reversed) {
			for(int i = 0; i < len; i++) {
				res += ((int) (list[i + begin] & 0xFF)) * accu;
				accu *= 256;
			}
		}
		else {
			for(int i = 0; i < len; i++) {
				res += ((int) (list[end - i - 1] & 0xFF)) * accu;
				accu *= 256;
			}
		}
		return res;
	}

}
