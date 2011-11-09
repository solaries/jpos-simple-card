package dao;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class DaoTestSuite {
	public static void main(String[] args) {
		System.out.println("------Run DAO test suite------");
		Result result = JUnitCore.runClasses(MySqlConnectionPoolTest.class, DataProviderTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("------End DAO test suite-------");
	}
}
