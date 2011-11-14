package com.hqsolution.hqserver.app.dao.mysql;

import com.hqsolution.hqserver.app.dao.AccountDao;
import com.hqsolution.hqserver.app.dao.BaseDbDao;
import com.hqsolution.hqserver.app.dao.factory.DbDaoFactory;
import com.hqsolution.hqserver.app.dao.idao.IAccount;

/**
 * Concrete MySQL database object of DAO Factory.
 * @author HUNGPT
 * @see DbDaoFactory
 */
public class MySqlDao extends BaseDbDao {

	/**
	 * Read an account object with account ID. 
	 * 
	 */
	@Override
	public IAccount getAccount() {
		return new AccountDao();
	}
	
	

}
