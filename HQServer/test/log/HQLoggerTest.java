package log;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hqsolution.hqserver.app.log.HQLogger;
import com.hqsolution.hqserver.app.log.LogLevel;

public class HQLoggerTest {

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Run " + HQLoggerTest.class.getName() + ".");
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("End " + HQLoggerTest.class.getName() + ".");
	}

	@Test
	public void testGetLevel() {
		HQLogger logger = new HQLogger();
		LogLevel lv = logger.getLevel("trace");
		Assert.assertEquals("TRACE", lv.name());
		lv = logger.getLevel("debug");
		Assert.assertEquals("DEBUG", lv.name());
		lv = logger.getLevel("info");
		Assert.assertEquals("INFO", lv.name());
		lv = logger.getLevel("warn");
		Assert.assertEquals("WARN", lv.name());
		lv = logger.getLevel("error");
		Assert.assertEquals("ERROR", lv.name());
		lv = logger.getLevel("fatal");
		Assert.assertEquals("FATAL", lv.name());
	}
}
