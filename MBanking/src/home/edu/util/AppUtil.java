package home.edu.util;

import home.edu.R;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class AppUtil {
	public static ListAdapter createListAdapter(Activity activity,
			List<String> items) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
				R.layout.task_row, R.id.rowItem, items);
		return adapter;
	}

	public static List<Byte> toList(byte[] bs) {
		List<Byte> list = new ArrayList<Byte>();
		for (byte b : bs) {
			list.add(b);
		}

		return list;
	}

	public static byte[] toBytes(List<Byte> list) {
		byte[] bs = new byte[list.size()];
		for (int i = 0; i < list.size(); i++) {
			bs[i] = list.get(i);
		}
		return bs;
	}

	public static List<Byte> to2ByteList(List<Byte> list) {
		if (list.size() < 2) {
			List<Byte> bytes = new ArrayList();
			bytes.add(Byte.valueOf("0"));
			bytes.addAll(list);
			return bytes;
		} else if (list.size() > 2){
			if(list.get(list.size() - 3) != (byte)0){
				return null;
			}
			List<Byte> bytes = new ArrayList();	
			bytes.add(list.get(list.size() - 2));
			bytes.add(list.get(list.size() - 1));
			return bytes;
		}
		return list;
	}
	
	public static byte[] to2ByteArray(byte[] bs) {
		List<Byte> li = MessageUtil.toList(bs);
		li = to2ByteList(li);
		return toBytes(li);
	}
	
	public static byte[] longToByteArray(long number){
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeLong(number);
			dos.flush();
			//lenth have to be 2 byte
			byte[] d = bos.toByteArray();
			dos.close();
			
			return d;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
