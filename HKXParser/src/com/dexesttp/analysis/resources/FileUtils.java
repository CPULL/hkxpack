package com.dexesttp.analysis.resources;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FileUtils {

	public static String readString(RandomAccessFile in) throws IOException {
		String s = "";
		byte b = 0;
		while((b = in.readByte()) != 0)
			s += (char) b;
		System.out.println(s);
		return s;
	}

	public static byte[] readUntil(RandomAccessFile in, byte end) throws IOException {
		ArrayList<Byte> list = new ArrayList<>();
		byte b = 0;
		while((b = in.readByte()) != end)
			list.add(new Byte(b));
		list.add(new Byte(b));
		final int n = list.size();
	    byte ret[] = new byte[n];
	    for (int i = 0; i < n; i++) {
	        ret[i] = list.get(i);
	    }
	    return ret;
	}
}
