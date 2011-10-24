package home.edu.common;

import home.edu.entiy.BankAccount;

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
		
		public boolean storeCreditCard(BankAccount creditCard) {
			return false;
		}
	}
}
