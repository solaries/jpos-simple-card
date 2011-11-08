package log;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class LogTestSuite {
	public static void main(String[] args) {
		System.out.println("------Run Log test suite------");
		Result result = JUnitCore.runClasses(HQLoggerTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("------End Log test suite-------");
	}
}
