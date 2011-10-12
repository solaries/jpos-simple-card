package home.edu.common;

import home.edu.entiy.CreditCard;

public class PhoneRequestImpl implements PhoneRequest{
	private static PhoneRequest instance;
	
	private PhoneRequestImpl(){
		
	}
	
	public static PhoneRequest getInstance(){
		return new PhoneRequestImpl();
	}
	
	
	public boolean storeCreditCard(CreditCard creditCard) {
		return false;
	}
}
