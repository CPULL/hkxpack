package com.dexesttp.hkxunpack.parser;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.dexesttp.hkxunpack.object.HKXNode;
import com.dexesttp.hkxunpack.object.Section;
import com.dexesttp.hkxunpack.object.classobjet.ClassObject;
import com.dexesttp.hkxunpack.object.classobjet.Classes;
import com.dexesttp.hkxunpack.resources.ByteUtils;

public class ContentParser {

	private Map<Integer, ClassObject> classVTable = new HashMap<>();
	private ArrayList<ClassObject> todoList = new ArrayList<>();
	
	public HKXNode generateNodes(RandomAccessFile in, Section data, int classPos, Classes classData) throws IOException {
		generateClassesLinker(in, data, classPos, classData);
		generateVirtualOrder();
		generateData();
		return null;
	}

	private void generateClassesLinker(RandomAccessFile in, Section data, int classPos, Classes classData) throws IOException {
		final long limit = ByteUtils.getInt(data.offset) + ByteUtils.getInt(data.part4);
		byte[] classVCode = new byte[4];
		byte[] zeros = new byte[4];
		byte[] classAddress = new byte[4];
		in.seek(ByteUtils.getInt(data.offset) + ByteUtils.getInt(data.part3));
		long pos;
		while((pos = in.getFilePointer()) + 12 < limit) {
			in.read(classVCode);
			in.read(zeros);
			in.read(classAddress);
			if(ByteUtils.getInt(classVCode) != -1) {
				in.seek(classPos + ByteUtils.getInt(classAddress));
				String className = ByteUtils.readString(in);
				ClassObject classObj = classData.getClass(className);
				classVTable.put(ByteUtils.getInt(classVCode), classObj);
			}
			in.seek(pos + 12);
		}
	}

	private void generateVirtualOrder() {
		// TODO Auto-generated method stub
		
	}

	private void generateData() {
		// TODO Auto-generated method stub
		
	}

}
