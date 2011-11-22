package com.hqsolution.hqserver.app.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.binary.Hex;

/**
 * Utility class for Message.
 * @author Quan Le
 *
 */
public class MessageUtil {

	static final byte[] HEX_CHAR_TABLE = { (byte) '0', (byte) '1', (byte) '2',
			(byte) '3', (byte) '4', (byte) '5', (byte) '6', (byte) '7',
			(byte) '8', (byte) '9', (byte) 'a', (byte) 'b', (byte) 'c',
			(byte) 'd', (byte) 'e', (byte) 'f' };

	/**
	 * convert byte array to Hex String
	 * 
	 * @param raw byte[]
	 * @return Hex String
	 * @throws UnsupportedEncodingException
	 */
	public static String getHexString(byte[] raw)
			throws UnsupportedEncodingException {
		byte[] hex = new byte[2 * raw.length];
		int index = 0;

		for (byte b : raw) {
			int v = b & 0xFF;
			hex[index++] = HEX_CHAR_TABLE[v >>> 4];
			hex[index++] = HEX_CHAR_TABLE[v & 0xF];
		}
		return new String(hex, "ASCII");
	}

	/**
	 * Convert Object to Byte Array
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] convertObjectToByteArray(Object obj)
			throws IOException {
		ObjectOutputStream os = null;
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
		os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
		os.flush();
		os.writeObject(obj);
		os.flush();
		byte[] sendBuf = byteStream.toByteArray();
		os.close();
		return sendBuf;

	}

	public static Object convertByteArrayToObject(byte[] bytes)
			throws IOException, ClassNotFoundException {
		ObjectInputStream is = null;
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				bytes);
		is = new ObjectInputStream(
				new BufferedInputStream(byteArrayInputStream));
		Object obj = is.readObject();
		is.close();
		return obj;

	}

	public static byte[] stringToByteArray(String input) {
		return input.getBytes();
	}

	public static List<byte[]> hexToListBytesArray(String s)
			throws DecoderException {
		List<String> listHex = new ArrayList<String>();
		if (s.length() == 2) {
			listHex.add(s);
		} else if (s.length() > 2 && ((s.length() % 2) == 0)) {
			for (int i = 0; i < s.length(); i++) {
				String ss = s.substring(i, i + 2);
				i = i + 1;
				listHex.add(ss);
			}
		} else {
			return null;
		}
		Hex hex = new Hex();

		List<byte[]> listByteArray = new ArrayList<byte[]>();
		for (String sh : listHex) {
			listByteArray.add(hex.decode(sh.getBytes()));
		}
		return listByteArray;
	}

	public static String byteToStringOfBit(byte[] bs) {
		BinaryCodec binaryCodec = new BinaryCodec();
		return new String(binaryCodec.encode(bs));
	}

	public static byte[] bitsToBytes(String bits) {
		BinaryCodec binaryCodec = new BinaryCodec();
		return binaryCodec.decode(bits.getBytes());
	}

	public static byte[] bindToHead(byte[] b1, byte[] b2) {
		List<Byte> b1List = MessageUtil.toList(b1);
		List<Byte> b2List = MessageUtil.toList(b2);
		b1List.addAll(b2List);
		return toBytes(b1List);
	}

	public static byte[] longToByteArray(long number) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeLong(number);
			dos.flush();
			// lenth have to be 2 byte
			byte[] d = bos.toByteArray();
			dos.close();

			return d;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static long byteArrayToLong(byte[] input) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(input);
			DataInputStream dis = new DataInputStream(bis);
			long numb = dis.readLong();
			dis.close();
			return numb;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] dateToSixBytes(Date date) {
		String strLong = String.valueOf(date.getTime());
		StringBuilder builder = new StringBuilder(strLong);
		String strInt = builder.substring(strLong.length() - 9);
		builder.delete(strLong.length() - 9, strLong.length());
		String strShort = builder.toString();

		int intNum = Integer.parseInt(strInt);
		short shortNum = Short.parseShort(strShort);

		byte[] intBytes = intToByteArray(intNum);
		byte[] shortBytes = shortToByteArray(shortNum);

		return bindToHead(shortBytes, intBytes);
	}

	public static Date sixBytesToDate(byte[] bs) {
		if (bs.length != 6)
			return null;
		List<byte[]> listByte = cutByteArray(bs, 2);

		String intNum = String.valueOf(byteArrayToInt(listByte.get(1)));
		while (intNum.length() < 9) {
			intNum = "0" + intNum;
		}
		String shortNum = String.valueOf(byteArrayToShort(listByte.get(0)));
		long l = Long.parseLong(shortNum+intNum);
		return new Date(l);
	}

	public static byte[] intToByteArray(int number) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeInt(number);
			dos.flush();
			// lenth have to be 2 byte
			byte[] d = bos.toByteArray();
			dos.close();

			return d;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static int byteArrayToInt(byte[] input) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(input);
			DataInputStream dis = new DataInputStream(bis);
			int numb = dis.readInt();
			dis.close();
			return numb;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] shortToByteArray(short number) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeShort(number);
			dos.flush();
			// lenth have to be 2 byte
			byte[] d = bos.toByteArray();
			dos.close();

			return d;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static short byteArrayToShort(byte[] input) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(input);
			DataInputStream dis = new DataInputStream(bis);
			short numb = dis.readShort();
			dis.close();
			return numb;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static ArrayList<byte[]> cutByteArray(byte[] bs, int bNo) {
		if (bs.length < 2) {
			throw new RuntimeException("");
		}
		List<Byte> list = toList(bs);
		List<Byte> listHead = new ArrayList<Byte>();
		for (int i = 0; i < bNo; i++) {
			listHead.add(list.get(0));
			list.remove(0);
		}
		ArrayList<byte[]> arrayList = new ArrayList<byte[]>();
		arrayList.add(toBytes(listHead));
		arrayList.add(toBytes(list));
		return arrayList;
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

}
