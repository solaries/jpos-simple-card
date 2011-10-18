package com.hqsolution.hqserver.app.dao.factory;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

import com.hqsolution.hqserver.app.dao.idao.IAccount;
import com.hqsolution.hqserver.app.dao.mysql.MySqlDao;
import com.hqsolution.hqserver.util.HQConfiguration;
import com.hqsolution.hqserver.util.SystemConstant;

/**
 * 
 * @author HUNGPT
 * General class for all database.
 */
public abstract class DbDaoFactory {
	
	private static final String DB_TYPE = "dbtype";
	
	public abstract IAccount getAccount();
	
	
	/**
	 * Get instance of class indicate for specific database..
	 * @return instance of class indicate for specific database.
	 */
	public static DbDaoFactory getInstances() {
		try {
			HQConfiguration cfg = (HQConfiguration)NameRegistrar.get(SystemConstant.SYSTEM_CONF);
			if(cfg.getConfiguration().get(DB_TYPE).equals("mysql")){
				return new MySqlDao();
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		//default SQL server
		return new MySqlDao();
	}
}
