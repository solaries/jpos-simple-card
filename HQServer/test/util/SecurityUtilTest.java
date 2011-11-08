package util;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hqsolution.hqserver.util.RSAKeyFactory;
import com.hqsolution.hqserver.util.SecurityUtil;

/**
 * 
 * @author HUNGPT
 *
 */
public class SecurityUtilTest {

	
	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Run SecurityUtilTest .");
	}
	
	@AfterClass
	public static void runAfterClass() {
		System.out.println("End SecurityUtilTest .");
	}
	
	
	@Test
	public void testMD5() {
		String plainText = "HQServer";
		String cipherText = SecurityUtil.md5ToString(plainText);
		Assert.assertEquals("8fb19fbfe9764733485e165d58c888dd", cipherText);
	}
	
	@Test
	public void testRSA() {
		String privateKeyPath = "E:\\Nam4\\LuanVan\\JProject\\HQServer\\keystore\\private.key";
		String publicKeyPath = "E:\\Nam4\\LuanVan\\JProject\\HQServer\\keystore\\public.key";
		RSAKeyFactory keyFactory = RSAKeyFactory.getInstance();
		keyFactory.loadKey(publicKeyPath, privateKeyPath);
		if(keyFactory.getPrivateKey() != null && keyFactory.getPublicKey() != null) {
			System.out.println("Private and Public key were loaded successfully");
			System.out.println(keyFactory.getPrivateKey().toString());
			System.out.println(keyFactory.getPublicKey().toString());
		}
		String plainText = "HQServer";
		byte[] cipherByte = SecurityUtil.rsaEncryptedString(plainText);
		String result = SecurityUtil.rsaDecrypted(cipherByte);
		Assert.assertEquals("Expected text" + plainText + " Actuall text " + result , plainText, result);
		
	}
}
