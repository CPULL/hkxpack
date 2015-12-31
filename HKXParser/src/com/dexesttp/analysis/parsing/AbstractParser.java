package com.dexesttp.analysis.parsing;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collection;

import com.dexesttp.analysis.model.hkobjects.HkClassObject;
import com.dexesttp.analysis.model.hkobjects.HkExternalObject;
import com.dexesttp.analysis.model.hkobjects.HkInternalObject;
import com.dexesttp.analysis.model.structs.Struct;
import com.dexesttp.analysis.resources.FileUtils;
import com.dexesttp.analysis.resources.Utils;

public abstract class AbstractParser implements Parser {

	protected void readClassNames(RandomAccessFile in, Struct struct) throws IOException {
		byte[] value = new byte[4];
		byte[] valueTemp = new byte[3];
		// Put cursor to the offset for classes
		in.seek(struct.classname_offset);
		byte b = in.readByte();
		// Seek struct.classname_size bytes of value or the FF at the end.
		while(in.getFilePointer() < struct.classname_size + struct.classname_offset) {
			in.read(valueTemp);
			if(in.readByte() == -1)
				break;
			value[0] = b;
			String s = FileUtils.readString(in);
			value[1] = valueTemp[0];
			value[2] = valueTemp[1];
			value[3] = valueTemp[2];
			struct.classes.put(s, value.clone());
			b = in.readByte();
		}
	}
	
	protected void readData(RandomAccessFile in, Struct struct) throws IOException {
		final long data1pos = struct.data1_offset + struct.data_offset;
		final long data2pos = struct.data2_offset + struct.data_offset;
		final long data3pos = struct.data3_offset + struct.data_offset;
		final long endpos = struct.eof_offset + struct.data_offset;
		readExternalData(in, struct.data1, data1pos, data2pos);
		readInternalData(in, struct.data2, data2pos, data3pos);
		readClassData(in, struct.data3, data3pos, endpos);
	}

	private void readExternalData(RandomAccessFile in, Collection<HkExternalObject> dataList, long begin, long limit) throws IOException {
		final byte[] defaultValue = {-1, -1, -1, -1};
		byte[] value = new byte[4];
		long pos;
		in.seek(begin);
		while((pos = in.getFilePointer()) < limit) {
			in.read(value);
			if(! Arrays.equals(value, defaultValue))
				dataList.add(new HkExternalObject((pos - begin) / 4, Utils.makeLong(value, 0, 4, true)));
		}
	}

	private void readClassData(RandomAccessFile in, Collection<HkClassObject> dataList, long begin, long limit) throws IOException {
		final byte[] defaultValue = {-1, -1, -1, -1};
		byte[] flagPos = new byte[4];
		byte[] classPos = new byte[4];
		long pos = 0;
		in.seek(begin);
		try {
		while((pos = in.getFilePointer()) < limit) {
			in.read(flagPos);
			in.skipBytes(4);
			in.read(classPos);
			if(! Arrays.equals(flagPos, defaultValue)) {
				HkClassObject created = new HkClassObject((pos - begin) / 4,
						Utils.makeLong(classPos, 0, 4, true),
						Utils.makeLong(flagPos, 0, 4, true));
				dataList.add(created);
			}
		}
		} catch(EOFException e) {
			
		}
	}

	private void readInternalData(RandomAccessFile in, Collection<HkInternalObject> dataList, long begin, long limit) throws IOException {
		final byte[] defaultValue = {-1, -1, -1, -1};
		byte[] value = new byte[4];
		long pos = 0;
		in.seek(begin);
		while((pos = in.getFilePointer()) < limit) {
			in.read(value);
			if(! Arrays.equals(value, defaultValue)) {
				HkInternalObject created = new HkInternalObject((pos - begin) / 4, Utils.makeLong(value, 0, 4, true));
				created.content = value.clone();
				dataList.add(created);
			}
		}
	}

	protected void fillExternalData(RandomAccessFile in, Struct struct, Collection<HkExternalObject> dataList) throws IOException {
		final long offset = struct.data_offset;
		for(HkExternalObject obj : dataList) {
			in.seek(obj.filePos + offset);
			in.read(obj.content);
		}
	}

	protected void fillClassData(RandomAccessFile in, Struct struct, Collection<HkClassObject> dataList) throws IOException {
		final long dOffset = struct.data_offset;
		final long cOffset = struct.classname_offset;
		for(HkClassObject obj : dataList) {
			// Get the class name
			in.seek(obj.namePos + cOffset);
			System.out.println("Pos : " + (obj.namePos + cOffset));
			obj.className = FileUtils.readString(in);
			// Get the class flags ?
			in.seek(obj.filePos + dOffset);
			obj.content = FileUtils.readUntil(in, (byte) -128);
		}
	}
}
