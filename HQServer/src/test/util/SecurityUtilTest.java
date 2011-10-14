package test.util;

import org.junit.Test;

import com.hqsolution.hqserver.util.SecurityUtil;

/**
 * 
 * @author HUNGPT
 *
 */
public class SecurityUtilTest {

	@Test
	public void testMD5() {
		String plainText = "HQServer";
		String cipherText = SecurityUtil.md5ToString(plainText);
		System.out.println(cipherText);

	}
}
