package com.hqsolution.hqserver.app.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZipUtil {
	public static byte[] compress(String str) {
		try {
			if (str == null || str.length() == 0) {
				return null;
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes());
			gzip.close();
			return out.toByteArray();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String decompress(byte[] b) {
		ByteArrayInputStream bi = null;
		GZIPInputStream gzip = null;
		InputStreamReader inreader = null;
		BufferedReader reader = null;
		try {
			bi = new ByteArrayInputStream(b);
			gzip = new GZIPInputStream(bi);
			inreader = new InputStreamReader(gzip);
			reader = new BufferedReader(inreader);
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
}