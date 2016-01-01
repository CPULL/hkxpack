package com.dexesttp.hkxunpack.parser;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dexesttp.hkxunpack.object.Section;
import com.dexesttp.hkxunpack.object.classobjet.ClassMember;
import com.dexesttp.hkxunpack.object.classobjet.ClassObject;
import com.dexesttp.hkxunpack.object.classobjet.Classes;
import com.dexesttp.hkxunpack.object.classobjet.EnumObject;
import com.dexesttp.hkxunpack.resources.ByteUtils;
import com.dexesttp.hkxunpack.resources.DOMUtils;
import com.dexesttp.hkxunpack.resources.FileUtils;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class ClassParser {

	public Classes parseClasses(RandomAccessFile in, Section classsection) throws IOException {
		final long endaddress = ByteUtils.getInt(classsection.offset) + ByteUtils.getInt(classsection.part1);
		byte[] classID = new byte[4];
		in.seek(ByteUtils.getInt(classsection.offset));
		Classes classes = new Classes();
		while(in.getFilePointer() + 6 < endaddress) {
			in.read(classID);
			in.skipBytes(1);
			String classname = ByteUtils.readString(in);
			DOMParser parser = new DOMParser();
			try {
				parser.parse(FileUtils.getFileName(classname));
			} catch (SAXException e) {
				e.printStackTrace();
				throw new IOException("Couldn't parse file for " + classname + " : invalid DOM.");
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException("Error reading file for " + classname + " : " + e.getMessage());
			}
			// Java DOM parsing is kind of ugly.
			Document document = parser.getDocument();
			NodeList enums = document.getElementsByTagName("enum");
			for(int i = 0; i < enums.getLength(); i++) {
				Node enumNode = enums.item(i);
				EnumObject enumObj = new EnumObject(
						DOMUtils.getNodeAttr("name", enumNode),
						DOMUtils.getNodeAttr("flags", enumNode));
				NodeList entries = enumNode.getChildNodes();
				for(int j = 0; j < entries.getLength(); j++) {
					Node entry = entries.item(j);
					if(entry.getNodeType() == Node.ELEMENT_NODE)
						enumObj.addEntry(
								DOMUtils.getNodeAttr("name", entry),
								Integer.parseInt(DOMUtils.getNodeAttr("value", entry)));
				}
			}
			ClassObject classObj = new ClassObject(classname, classID);
			NodeList members = document.getElementsByTagName("member");
			for(int i = 0; i < members.getLength(); i++) {
				Node memberNode = members.item(i);
				ClassMember memberObj = new ClassMember(
						DOMUtils.getNodeAttr("name", memberNode),
						DOMUtils.getNodeAttr("vtype", memberNode),
						DOMUtils.getNodeAttr("vsubtype", memberNode),
						DOMUtils.getNodeAttr("ctype", memberNode));
				classObj.addContent(memberObj);
			}
			
		}
		return classes;
	}
}
