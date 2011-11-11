package home.edu.common;

import home.edu.factory.IsoMessageBuilder;
import home.edu.processor.AccountCreateProcessor;

import com.hqsolution.hqserver.app.dao.AccountLogin;
import com.hqsolution.hqserver.app.dao.BankAccount;

/**
 * Implements the methods that make request to server
 * @author Quan
 *
 */
public class FinancialRequestFacade {
	private static FinancialRequest instance;
	
	public static FinancialRequest getInstance(){
		if(instance == null){
			return new PhoneRequestImpl();
		}
		return instance;
	}
	
	public static final class PhoneRequestImpl implements FinancialRequest{
		
		
		PhoneRequestImpl(){
			
		}
		
		public boolean saveUserInfo(AccountLogin accountLogin) {
			AccountCreateProcessor processor = new AccountCreateProcessor(accountLogin);
			processor.process();
			return false;
		}
	}
}
