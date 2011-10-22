package home.edu.common;

import home.edu.entiy.BankAccount;

/**
 * 
 * Define methods that make requests to server
 * @author Quan
 *
 */
public interface FiancialRequest {
	boolean storeCreditCard(BankAccount creditCard);
}
