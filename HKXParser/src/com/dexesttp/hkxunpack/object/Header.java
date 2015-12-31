package com.dexesttp.hkxunpack.object;

public class Header {
	public byte[] fileID = new byte[12];
	public byte[] version = new byte[4];
	public byte[] junk = new byte[24];
	public byte[] versionName = new byte[16];
	public byte[] junk2 = new byte[4];
	public byte[] val1 = new byte[2];
	public byte[] val2 = new byte[2];
}
