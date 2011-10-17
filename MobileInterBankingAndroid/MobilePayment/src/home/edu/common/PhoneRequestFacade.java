package home.edu.common;

import home.edu.entiy.BankAccount;

/**
 * Implements the methods that make request to server
 * @author Quan
 *
 */
public class PhoneRequestFacade {
	private static PhoneRequest instance;
	
	public static PhoneRequest getInstance(){
		if(instance == null){
			return new PhoneRequestImpl();
		}
		return instance;
	}
	
	public static final class PhoneRequestImpl implements PhoneRequest{
		
		
		PhoneRequestImpl(){
			
		}
		
		public boolean storeCreditCard(BankAccount creditCard) {
			return false;
		}
	}
}
