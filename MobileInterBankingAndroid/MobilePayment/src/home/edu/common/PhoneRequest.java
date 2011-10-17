package home.edu.common;

import home.edu.entiy.BankAccount;

/**
 * 
 * Define methods that make requests to server
 * @author Quan
 *
 */
public interface PhoneRequest {
	boolean storeCreditCard(BankAccount creditCard);
}
