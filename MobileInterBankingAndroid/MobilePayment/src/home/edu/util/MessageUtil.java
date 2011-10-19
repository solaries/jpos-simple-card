package home.edu.util;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.binary.Hex;

public class MessageUtil {
	
	
	
	public static byte[] stringToByteArray(String input) {
		return input.getBytes();
	}	

	
	public static List<byte[]> hexToListBytesArray(String s) throws DecoderException {
		List<String> listHex = new ArrayList<String>();
		if (s.length() == 2) {
			listHex.add(s);
		} else if (s.length() > 2 && ((s.length() % 2) == 0)) {
			for(int i = 0; i < s.length(); i++){
				String ss = s.substring(i, i+2);
				i = i+1;
				listHex.add(ss);
			}
		}else{
			return null;
		}
		Hex hex = new Hex();
		
		List<byte[]> listByteArray = new ArrayList<byte[]>();
		for(String sh : listHex){
			listByteArray.add(hex.decode(sh.getBytes()));
		}
		return listByteArray;
	}
	
	public static String byteToStringOfBit(byte[] bs) {
		BinaryCodec binaryCodec = new BinaryCodec();
		return new String(binaryCodec.encode(bs));
	}
}
