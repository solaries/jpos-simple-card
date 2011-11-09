package dao;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPoolFactory;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hqsolution.hqserver.app.common.DatabaseConnection;
import com.hqsolution.hqserver.app.dao.mysql.MySqlPoolableObjectFactory;

public class MySqlConnectionPoolTest {
	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Run MySqlConnectionPoolTest.");
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("End MySqlConnectionPoolTest .");
	}

	@Test
	public void testConnection() {
		/** Declare the JDBC objects. **/
		DatabaseConnection con = null;

		String userName = "hqgroup";
		String passWord = "matkhau";
		String host = "localhost";
		String port = "3306";
		String dbName = "hqdb";

		PoolableObjectFactory mySqlPoolableObjectFactory = new MySqlPoolableObjectFactory(
				host, Integer.parseInt(port), dbName, userName, passWord);
		Config config = new GenericObjectPool.Config();
		config.maxActive = 10;
		config.maxWait = 20000;
		config.maxIdle = 5;
		config.minIdle = 0;
		config.testOnBorrow = false;
		config.testWhileIdle = true;
		config.timeBetweenEvictionRunsMillis = 10000;
		config.minEvictableIdleTimeMillis = 60000;

		GenericObjectPoolFactory genericObjectPoolFactory = new GenericObjectPoolFactory(
				mySqlPoolableObjectFactory, config);
		ObjectPool pool = genericObjectPoolFactory.createPool();

		try {
			con = (DatabaseConnection) pool.borrowObject();
		} catch (NoSuchElementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (con == null) {
			fail();
		} else {
			try {
				con.close();
				pool.close();
				System.out.println("Test Connection successfully");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
