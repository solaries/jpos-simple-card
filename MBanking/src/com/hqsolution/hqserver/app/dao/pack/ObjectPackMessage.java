package com.hqsolution.hqserver.app.dao.pack;

import java.io.IOException;
import java.util.List;

import com.hqsolution.hqserver.app.dao.util.MessageUtil;

/**
 * 
 * @author Anh Quan
 * 
 */
public class ObjectPackMessage extends CommonPackMessage {

	private Object obj;
	private short type;

	public ObjectPackMessage(Object obj, short objectType) {
		super();
		setObj(obj, objectType);
	}

	public void setObj(Object obj, short objectType) {
		this.obj = obj;
		this.type = objectType;
		process();
	}

	public byte[] pack() {
		return data;
	}

	protected void process() {
		try {
			byte[] bytes = MessageUtil.convertObjectToByteArray(obj);
			this.data = bytes;
			int len = bytes.length;
			byte[] d = MessageUtil.intToByteArray(len);
			// add object len
			this.data = MessageUtil.bindToHead(d, this.data);
			// add object type
			this.data = MessageUtil.bindToHead(
					MessageUtil.shortToByteArray(type), data);
			// add message len
			super.process();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
