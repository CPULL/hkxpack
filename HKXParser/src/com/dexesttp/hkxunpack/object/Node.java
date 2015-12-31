package com.dexesttp.hkxunpack.object;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class Node {
	public abstract String xmlDump(int level);
	public abstract String xmlWrite(PrintWriter in, int level) throws IOException;
}
