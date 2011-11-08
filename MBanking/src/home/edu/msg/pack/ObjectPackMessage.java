package home.edu.msg.pack;


import home.edu.msg.util.MessageUtil;

import java.io.IOException;
import java.util.List;


/**
 * 
 * @author Anh Quan
 *
 */
public class ObjectPackMessage extends CommonPackMessage{
	
	private Object obj;
	private byte[] type;

	
	public ObjectPackMessage(Object obj, byte[] objectType) {
		super();
		setObj(obj, objectType);
	}
	
	

	public void setObj(Object obj, byte[] objectType) {
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
			long len = bytes.length;
			byte[] d = MessageUtil.longToByteArray(len);
			d = MessageUtil.to2ByteArray(d);
			this.data = MessageUtil.bindToHead(d, this.data);			
			//add object type
			
			this.data = MessageUtil.bindToHead(type, data);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.process();
	}
}
