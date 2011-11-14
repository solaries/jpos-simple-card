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
	
	public BaseTest(){
		init();
	}
	
	public void init(){
		System.out.println("Create connection.");
		con =  DataProvider.getConnection(userName, passWord, host, port, dbName);
		if(con != null) {
			System.out.println("Create connection sucessfully");
		}else {
			System.out.println("fail to create connection.");
		}
	}
	
	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Run " + BaseTest.class.getName() + " .");
	}
	
	@AfterClass
	public static void runAfterClass() {
		System.out.println("End " + BaseTest.class.getName() + " .");
	}
	
	public Connection getConnection(){
		return con;
	}
	
	
}
