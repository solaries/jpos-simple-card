package dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hqsolution.hqserver.app.dao.mysql.DataProvider;

public class DataProviderTest {

	@BeforeClass
	public void runBeforeClass() {
		System.out.println("Run " + this.getClass().getName() + " .");
	}

	@AfterClass
	public void runAfterClass() {
		System.out.println("End " + this.getClass().getName() + " .");
	}

	@Test
	public void testConnection() {
		/** Declare the JDBC objects. **/
		Connection con = null;

		String userName = "hqgroup";
		String passWord = "matkhau";
		String host = "localhost";
		String port = "3306";
		String dbName = "hqdb";

		con = DataProvider.getConnection(userName, passWord, host, port, dbName);
		if (con == null) {
			fail();
		} else {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
