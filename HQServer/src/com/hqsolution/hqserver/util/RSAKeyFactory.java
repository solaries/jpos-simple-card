package com.hqsolution.hqserver.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author HUNGPT
 *
 */
public class RSAKeyFactory {

	private Key publicKey = null;
	private Key privateKey = null;

	public void generateKeyPair(int keySize) {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(keySize);
			KeyPair kp = kpg.genKeyPair();
			publicKey = kp.getPublic();
			privateKey = kp.getPrivate();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	public void saveKeyToFile(Key key, String fileName) {
		ObjectOutputStream oout = null;
		try {
			oout = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(fileName)));
			oout.writeObject(key);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(oout !=null) {
				try {
					oout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Key loadKey(String fileName) {
		ObjectInputStream oin = null;
		Key result = null;
		try {
			oin = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(fileName)));
			result = (Key)oin.readObject();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(oin !=null) {
				try {
					oin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
