package com.hqsolution.hqserver.app.resource;

import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

import com.hqsolution.hqserver.app.common.DatabaseConnection;
import com.hqsolution.hqserver.app.dao.mysql.MySqlConnectionPool;
import com.hqsolution.hqserver.util.HQConfiguration;
import com.hqsolution.hqserver.util.SystemConstant;

public class Q2ContainerResourceManager implements ResourceManager {

	private static final String DB_TYPE = "dbtype";
	
	public Q2ContainerResourceManager(){ }
	
	/**{@inherit}*/
	@Override
	public DatabaseConnection getQ2ContainerConnection() {
		try {
			HQConfiguration cfg = (HQConfiguration)NameRegistrar.get(SystemConstant.SYSTEM_CONF);
			if(cfg.getConfiguration().get(DB_TYPE).equals("mysql")){
				return MySqlConnectionPool.getConnection();
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		//default MySQL DB connection
		return MySqlConnectionPool.getConnection();
	}

}
