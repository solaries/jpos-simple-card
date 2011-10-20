package util;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class UtilTestSuite {
	public static void main(String[] args) {
		System.out.println("--------Run test.util test suite-------");
		Result result = JUnitCore.runClasses(util.SecurityUtilTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("--------End test.util test suite--------");
	}
}
