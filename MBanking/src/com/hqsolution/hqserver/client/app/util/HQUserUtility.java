package com.hqsolution.hqserver.client.app.util;

import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.client.common.FinancialRequest;
import com.hqsolution.hqserver.client.data.helper.ApplicationDataHelper;

/**
 * 
 * @author QuanLe
 *
 */
public class HQUserUtility {
	private static HQUserUtility instance;
	
	public static synchronized HQUserUtility getInstance(){
		if(instance == null){
			instance = new HQUserUtility();
		}
		return instance;
	}
	
	
	public boolean login(ApplicationDataHelper dataHelper, String username, String password){
		/*FinancialRequest request = FinancialRequest.Factory.newInstance();
		HQAccount account = request.login(username, password);
		if(account == null){
			return false;
		}
		AppUtil.getSession().set("user", account);*/
		
		return true;
	}
}
