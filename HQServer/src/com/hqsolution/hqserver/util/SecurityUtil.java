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

	/**
	 * Get hash string of a plain text 
	 * @param plainText
	 * @return Hase string
	 */
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

	/**
	 * Get hash byte of a plain text
	 * @param plainText Plain text need to be hash
	 * @return
	 */
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

	/**
	 * Get array of byte after doing asymmetric encrytion
	 * @param plainData Plain data need to be encryption
	 * @return array of byte after encrypting.
	 */
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
	
	/**
	 * Get array of byte after doing asymmetric dencrytion
	 * @param plainData Plain data (raw byte data) need to be dencryption
	 * @return array of byte after dencrypting.
	 */
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
	
	/**
	 * Get array of byte after doing asymmetric encrytion
	 * @param plainData Plain data (string data) need to be encryption
	 * @return array of byte after encrypting.
	 */
	public static byte[] rsaEncryptedString(String plainText) {
		byte[] plainByte = plainText.getBytes();
		return SecurityUtil.rsaEncrypt(plainByte);
	}
	
	/**
	 * Get string data after doing asymmetric dencrytion
	 * @param plainData Plain data (raw byte data) need to be dencryption
	 * @return array of byte after dencrypting.
	 */
	public static String rsaDecrypted(byte[] cipherData) {
		byte[] plainData = SecurityUtil.rsaDecrypt(cipherData);
		return new String(plainData);
	}

}
