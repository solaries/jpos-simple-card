package main;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.hqsolution.hqserver.app.dao.mysql.DataProvider;

public class BaseTest {

	Connection con = null;
	String userName = "hqgroup";
	String passWord = "matkhau";
	String host = "localhost";
	String port = "3306";
	String dbName = "hqdb";
	
	@BeforeClass
	public void runBeforeClass() {
		System.out.println("Run " + this.getClass().getName() + " .");
		System.out.println("Create connection.");
		con =  DataProvider.getConnection(userName, passWord, host, port, dbName);
		if(con != null) {
			System.out.println("Create connection sucessfully");
		}else {
			System.out.println("fail to create connection.");
		}
	}
	
	@AfterClass
	public void runAfterClass() {
		System.out.println("End " + this.getClass().getName() + " .");
	}
}
