package com.hqsolution.hqserver.app.dao.mysql;

import java.util.NoSuchElementException;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPoolFactory;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.jpos.q2.QBeanSupport;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

import com.hqsolution.hqserver.app.common.DatabaseConnection;

/**
 * MySqlConection Pool object contains a list of Database Connection.
 * @author HUNGPT
 *
 */
public class MySqlConnectionPool extends QBeanSupport {
	
	private ObjectPool pool;
	
	private MySqlConnectionPool() {
		super();
	}
	
	@Override
	protected void initService() throws Exception {
		super.initService();
		log.info("MySql Connection Pool is initializing.....");
		pool = initMySqlConnectionPool();
		NameRegistrar.register("connection.pool.MySqlConnectionPool", this);
		log.info("MySql Connection Pool initialed.");
	}

	@Override
	protected void startService() throws Exception {
		super.startService();
		if(pool != null)
			log.info("MySql Connection Pool is ready to serve. ");
	}

	@Override
	protected void stopService() throws Exception {
		super.stopService();
		log.info("MySql Connection Pool is closing....");
		if(pool != null) {
			pool.close();
		}
		log.info("MySql Connection Pool was closed successfully.");
	}

	
	
	/**
	 * Initialize for MySql Connection Pool.
	 * @return <code>ObjectPool</code> Connection after was initialized.
	 */
	public ObjectPool initMySqlConnectionPool() {
		
        String host = cfg.get("host");
        String port = cfg.get("port");
        String schema = cfg.get("dbname");
        String user = cfg.get("username");
        String password = cfg.get("password");

       return createConnectionPool(host, port, schema, user, password);
   }
	
	private ObjectPool createConnectionPool(String host, String port, String schema, String user, String password) {
		PoolableObjectFactory mySqlPoolableObjectFactory = new MySqlPoolableObjectFactory(host,
	             Integer.parseInt(port), schema, user, password);
	        Config config = new GenericObjectPool.Config();
	          config.maxActive = 10;
	          config.maxWait = 20000;
	          config.maxIdle = 5;
	          config.minIdle = 0;
	          config.testOnBorrow = false;
	          config.testWhileIdle = true;
	          config.timeBetweenEvictionRunsMillis = 10000;
	          config.minEvictableIdleTimeMillis = 60000;

	        GenericObjectPoolFactory genericObjectPoolFactory = new GenericObjectPoolFactory(mySqlPoolableObjectFactory, config);
	        ObjectPool pool = genericObjectPoolFactory.createPool();
	        return pool;
	}
	
	private DatabaseConnection getDBConnection() {
		log.info("Get database connection from pool.");
		try {
			return (DatabaseConnection)pool.borrowObject();
		} catch (NoSuchElementException e) {
			log.error("Can not find Connection", e);
		} catch (IllegalStateException e) {
			log.error("IllegalStateException", e);
		} catch (Exception e) {
			log.error("MySqlConnectionPool error : ", e);
		}
		return null;
	}
	
	public static DatabaseConnection getConnection() {
		MySqlConnectionPool mySqlPool = null;
		try {
			mySqlPool = (MySqlConnectionPool) NameRegistrar.get("connection.pool.MySqlConnectionPool");
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		if(mySqlPool == null)
			return null;
		return mySqlPool.getDBConnection();
	}
	
	/**
	   * @see Object#toString()
	   */
	  public String toString() {
	    return "DatabasePool[name=" + getName()
	      + ", numActive=" + pool.getNumActive()
	      + ", numIdle=" + pool.getNumIdle()
	      + "]"
	      ;
	  }
}
