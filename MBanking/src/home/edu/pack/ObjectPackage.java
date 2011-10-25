package home.edu.pack;

import home.edu.util.AppUtil;
import home.edu.util.MessageUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class ObjectPackage {
	private Object obj;
	private byte[] data;

	public ObjectPackage() {
		// TODO Auto-generated constructor stub
	}

	public ObjectPackage(Object obj) {
		super();
		this.obj = obj;
		process();
	}

	public void setObj(Object obj) {
		this.obj = obj;
		process();
	}

	public byte[] pack() {
		return data;
	}

	private void process() {
		try {
			byte[] bytes = MessageUtil.convertObjectToByteArray(obj);
			this.data = bytes;
			long len = bytes.length;
			// convert len to byte[]
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeLong(len);
			dos.flush();
			//lenth have to be 2 byte
			byte[] d = bos.toByteArray();
			dos.close();
			
			List<Byte> list = MessageUtil.toList(d);
			list = MessageUtil.to2ByteList(list);
			list.addAll(MessageUtil.toList(data));
			this.data =  MessageUtil.toBytes(list);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
