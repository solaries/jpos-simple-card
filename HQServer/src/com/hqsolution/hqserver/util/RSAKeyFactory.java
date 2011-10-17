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
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * Generate Public Key and Private Key to encrypte message
 * @author HUNGPT
 *
 */
public class RSAKeyFactory {

	private Key publicKey = null;
	private Key privateKey = null;
	private static final int KEY_SIZE = 1024; 
	
	private static RSAKeyFactory instance = RSAKeyFactory.getInstance();

	public RSAKeyFactory() {}
	
	/**
	 * Get RSAFactory instance. Create a new one if it does not exist.
	 * @return RSAKeyFactory instance
	 */
	public static RSAKeyFactory getInstance() {
		if(instance != null) {
			return instance;
		}
		return new RSAKeyFactory();
	}

	/**
	 * 
	 * @param keySize
	 */
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
	
	public void loadKey(String publicKeyPath, String privateKeyPath) {
		if(publicKey != null || privateKey != null) {
			return; // Key has already existed in memory
		}else {
			publicKey = loadKey(publicKeyPath);
			privateKey = loadKey(privateKeyPath);
			if(publicKey == null || privateKey ==null) {
				/*We can not find out any private key and public key
				 * We should creat a new one and save them to system file
				 */
				generateKeyPair(KEY_SIZE);
				saveKeyToFile(privateKey, privateKeyPath);
				saveKeyToFile(publicKey, publicKeyPath);
			}
		}
	}
	
	public PrivateKey getPrivateKey() {
		
		if(privateKey == null) 
			return null;
		
		KeyFactory fact;
		PrivateKey priKey = null;
		try {
			fact = KeyFactory.getInstance("RSA");
			RSAPrivateKeySpec keySpec = fact.getKeySpec(privateKey, RSAPrivateKeySpec.class);
			priKey = fact.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return priKey;
	}
	
	public PublicKey getPublicKey() {
		
		if(publicKey == null) 
			return null;
		
		KeyFactory fact;
		PublicKey pubKey = null;
		try {
			fact = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec keySpec = fact.getKeySpec(publicKey, RSAPublicKeySpec.class);
			pubKey = fact.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return pubKey;
	}
	
	public static void main(String[] arg) {
		String privateKeyPath = "E:\\Nam4\\LuanVan\\JProject\\HQServer\\keystore\\private.key";
		String publicKeyPath = "E:\\Nam4\\LuanVan\\JProject\\HQServer\\keystore\\public.key";
		RSAKeyFactory keyFactory = RSAKeyFactory.getInstance();
		keyFactory.loadKey(publicKeyPath, privateKeyPath);
		if(keyFactory.getPrivateKey() != null && keyFactory.getPublicKey() != null) {
			System.out.println("Private and Public key were loaded successfully");
			System.out.println(keyFactory.getPrivateKey().toString());
			System.out.println(keyFactory.getPublicKey().toString());
		}
	}

}
