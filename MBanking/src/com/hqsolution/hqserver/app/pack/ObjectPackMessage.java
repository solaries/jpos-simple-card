package com.hqsolution.hqserver.app.pack;

import java.io.IOException;
import java.util.List;

import com.hqsolution.hqserver.app.util.MessageUtil;

/**
 * This class is aim to pack an object
 * @author QuanLe
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

	/**
	 * {@inheritDoc}
	 */
	public byte[] pack() {
		return data;
	}

	/**
	 * Data = data_length + object type + Object length + Object
	 */
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
