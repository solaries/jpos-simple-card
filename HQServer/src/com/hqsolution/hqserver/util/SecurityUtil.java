package com.hqsolution.hqserver.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * This class aim to encrypt and decrypt ISO8583 meassage.
 * @author HUNGPT
 * 
 */
public class SecurityUtil {

	public static String md5ToString(String plainText) {

		byte messageDigest[] = SecurityUtil.md5(plainText);
		if (messageDigest == null) {
			return "";
		}
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < messageDigest.length; i++) {
			hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
		}
		return hexString.toString();
	}

	public static byte[] md5(String plainText) {
		byte[] defaultBytes = plainText.getBytes();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte[] messageDigest = algorithm.digest();
			return messageDigest;
		} catch (NoSuchAlgorithmException nsae) {
			return null;
		}
	}

	public static byte[] rsaEncrypt(byte[] plainData) {
		PublicKey publicKey = RSAKeyFactory.getInstance().getPublicKey();
		Cipher cipher = null;
		byte[] cipherData = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			cipherData = cipher.doFinal(plainData);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return cipherData;
	}
	
	public static byte[] rsaDecrypt(byte[] cipherData) {
		Key privateKey = RSAKeyFactory.getInstance().getPrivateKey();
		Cipher cipher = null;
		byte[] plainData = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			plainData = cipher.doFinal(cipherData);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return plainData;
	}
	
	public static byte[] rsaEncryptedString(String plainText) {
		byte[] plainByte = plainText.getBytes();
		return SecurityUtil.rsaEncrypt(plainByte);
	}
	
	public static String rsaDecrypted(byte[] cipherData) {
		byte[] plainData = SecurityUtil.rsaDecrypt(cipherData);
		return new String(plainData);
	}

}
