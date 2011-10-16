package util;

import org.junit.Assert;
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
		Assert.assertEquals("8fb19fbfe9764733485e165d58c888dd", cipherText);

	}
}
