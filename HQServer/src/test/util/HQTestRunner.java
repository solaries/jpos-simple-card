package test.util;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * 
 * @author HUNGPT
 *
 */
public class HQTestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(test.util.SecurityUtilTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
	}
}
