package home.edu.pack;

import home.edu.entiy.EntityType;
import home.edu.util.AppUtil;
import home.edu.util.MessageUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class ObjectPackage extends CommonMessage{
	private Object obj;
	private byte[] type;

	public ObjectPackage() {
		
	}

	public ObjectPackage(Object obj, byte[] objectType) {
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
			byte[] d = AppUtil.longToByteArray(len);
			d = AppUtil.to2ByteArray(d);
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
