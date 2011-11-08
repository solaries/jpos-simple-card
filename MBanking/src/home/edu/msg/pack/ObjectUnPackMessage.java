package home.edu.msg.pack;

import home.edu.msg.util.MessageUtil;

import java.io.IOException;
import java.util.List;

/**
 * 
 * @author Anh Quan
 *
 */
public class ObjectUnPackMessage extends CommonUnPackMessage {

	private Object obj;

	public ObjectUnPackMessage(byte[] data) {
		super(data);
	}

	/**
	 * Unpack to raw Object
	 * 
	 * @return Object raw object
	 */
	public Object unpackObject() {
		if (obj != null) {
			return obj;
		}
		try {
			byte[] ba = unpack();
			List<byte[]> list = MessageUtil.cutByteArray(ba, 2);
			byte[] tmpObjAndLen = list.get(1);
			list = MessageUtil.cutByteArray(ba, 2);
			byte[] obj = list.get(1);
			return MessageUtil.convertByteArrayToObject(obj);
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {

		}
		return null;
	}
}