package main;


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
		System.out.println("Run HQ test suite");
		Result result = JUnitCore.runClasses(util.SecurityUtilTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("End HQ test suite");
	}
}
