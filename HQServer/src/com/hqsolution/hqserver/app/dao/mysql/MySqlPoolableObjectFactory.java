package com.hqsolution.hqserver.app.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.ObjectPool;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

import com.hqsolution.hqserver.app.common.BaseConnectionWrapper;
import com.hqsolution.hqserver.app.common.DatabaseConnection;

/**
 * Create Connection object for MySql pool.
 * 
 * @author HUNGPT
 * 
 */
public class MySqlPoolableObjectFactory extends BasePoolableObjectFactory {
	private String host;
	private int port;
	private String schema;
	private String user;
	private String password;

	public MySqlPoolableObjectFactory(String host, int port, String schema,
			String user, String password) {
		this.host = host;
		this.port = port;
		this.schema = schema;
		this.user = user;
		this.password = password;
	}

	@Override
	public Object makeObject() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String url = "jdbc:mysql://" + host + ":" + port + "/" + schema
				+ "?autoReconnectForPools=true";
		Connection conn = DriverManager.getConnection(url, user, password);
		DatabaseConnection poolCon = new DatabasePoolConnection(conn);
		return poolCon;
	}

	  
	public static final class DatabasePoolConnection extends
			BaseConnectionWrapper {
		public DatabasePoolConnection(Connection delegate) {
			super(delegate);
		}
		
		private ObjectPool getPool() {
			ObjectPool pool = null;
			try {
				pool = (ObjectPool)NameRegistrar.get("connection.pool.MySqlConnectionPool");
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			return pool;
		}
		
		 public void safeClose() {
	          if (getDelegate() != null) {
	               try {
	            	   ObjectPool pool = getPool();
	            	   if(pool != null) {
	            		   pool.returnObject(getDelegate());
	                    }else {
	                    	close();
	                    }
	               }
	               catch (Exception e) {
	                    e.printStackTrace();
	               }
	          }
	     }
	}
}
