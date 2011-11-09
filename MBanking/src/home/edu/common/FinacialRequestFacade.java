package home.edu.common;

import com.hqsolution.hqserver.app.dao.AccountLogin;
import com.hqsolution.hqserver.app.dao.BankAccount;

/**
 * Implements the methods that make request to server
 * @author Quan
 *
 */
public class FinacialRequestFacade {
	private static FiancialRequest instance;
	
	public static FiancialRequest getInstance(){
		if(instance == null){
			return new PhoneRequestImpl();
		}
		return instance;
	}
	
	public static final class PhoneRequestImpl implements FiancialRequest{
		
		
		PhoneRequestImpl(){
			
		}
		
		public boolean saveUserInfo(AccountLogin accountLogin) {
			
			return false;
		}
	}
}
