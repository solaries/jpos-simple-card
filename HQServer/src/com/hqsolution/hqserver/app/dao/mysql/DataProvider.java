package com.hqsolution.hqserver.app.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

import com.hqsolution.hqserver.util.HQConfiguration;
import com.hqsolution.hqserver.util.SystemConstant;

/**
 * This class aim to get mysql connect.
 * @author HUNGPT
 * 
 */
public class DataProvider {
	
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String HOST = "host";
	private static final String PORT = "port";
	private static final String DATABASE_NAME = "dbname";
	
	/**
	 * Read system configuration parameters from configuration XML file through
	 * system configuration Object.
	 * After a while, we will create a new connection.
	 * @return Connection to specify database.
	 * @see com.hqsolution.hqserver.util.HQConfiguration
	 */
	public static Connection getConnection() {

		/** Declare the JDBC objects. **/
		Connection con = null;
			
			/**
			 * Read all configuration value from configuration file through
			 * configuration object which is registered in NameRegistrar.
			 */
			HQConfiguration cfg;
			try {
				cfg = (HQConfiguration) NameRegistrar.get(SystemConstant.SYSTEM_CONF);
			} catch (NotFoundException e) {
				e.printStackTrace();
				return null;
			}
			String userName = cfg.getConfiguration().get(USERNAME);
			String passWord = cfg.getConfiguration().get(PASSWORD);
			String host = cfg.getConfiguration().get(HOST);
			String port = cfg.getConfiguration().get(PORT);
			String dbName = cfg.getConfiguration().get(DATABASE_NAME);
			con = DataProvider.getConnection(userName, passWord, host, port, dbName);
			
			if(cfg != null)
				cfg = null; //make a change to GC deallocate configuration reference
			
		return con;	
	}
	
	public static Connection getConnection(String userName, String passWord, String host, String port, String dbName) {
		/** Declare the JDBC objects. **/
		Connection con = null;

		try {
			
			/** Create a variable for the connection URL string. **/
			String connectionUrl = "jdbc:mysql://" + host + ":" + port
					+ "/" + dbName;

			/** Register mySQL server driver and establish the connection. **/
			Class.forName("com.mysql.jdbc.Driver");
			
			/** Get connection **/
			con = DriverManager.getConnection(connectionUrl,userName,passWord);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
		return con;
	}
}
