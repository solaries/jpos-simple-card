package home.edu.common;

import com.hqsolution.hqserver.app.dao.AccountLogin;
import com.hqsolution.hqserver.app.dao.BankAccount;

/**
 * 
 * Define methods that make requests to server
 * @author Quan
 *
 */
public interface FinancialRequest {
	boolean saveUserInfo(AccountLogin accountLogin);
}
