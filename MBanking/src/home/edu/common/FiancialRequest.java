package home.edu.common;

import home.edu.entiy.AccountLogin;
import home.edu.entiy.BankAccount;

/**
 * 
 * Define methods that make requests to server
 * @author Quan
 *
 */
public interface FiancialRequest {
	boolean saveUserInfo(AccountLogin accountLogin);
}
