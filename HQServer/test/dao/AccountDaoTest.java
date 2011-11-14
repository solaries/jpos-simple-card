package dao;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import main.BaseTest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hqsolution.hqserver.app.common.DatabaseConnection;
import com.hqsolution.hqserver.app.dao.AccountDao;
import com.hqsolution.hqserver.app.dao.mysql.MySqlPoolableObjectFactory;
import com.hqsolution.hqserver.app.dto.Account;
import com.hqsolution.hqserver.util.IdGenerator;
import com.hqsolution.hqserver.util.SQLResult;
import com.mysql.jdbc.PreparedStatement;

public class AccountDaoTest extends BaseTest {

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Run " + AccountDaoTest.class.getName() + " .");
	}
	
	@AfterClass
	public static void runAfterClass() {
		System.out.println("End " + AccountDaoTest.class.getName() + " .");
	}
	@Test
	public void testCreatAccount(){
		/*Account acct = new Account("a@abc.com", "Hung Pham", "123");
		AccountDao accountDao = new AccountDao();
		DatabaseConnection conn = new MySqlPoolableObjectFactory.DatabasePoolConnection(getConnection());
		String result = accountDao.createAccount(conn, acct);
		Assert.assertEquals(result, SQLResult.SUCCESS);*/
		System.out.println("Insert new Account into DB");
		String INSERT_ACCOUNT = "insert into hqdb.account(accountId,email,password,fullName) values(?,?,?,?)";
		Account acct = new Account("a@abc.com", "Hung Pham", "123");
		String accountId = IdGenerator.getId();
		try {
			PreparedStatement insertAccount = (PreparedStatement) getConnection().prepareStatement(INSERT_ACCOUNT);
			insertAccount.setString(1, accountId);
			insertAccount.setString(2, acct.getEmail());
			insertAccount.setString(3, acct.getPassword());
			insertAccount.setString(4, acct.getFullName());
			int result = insertAccount.executeUpdate();
			if(result <= 0){
				fail();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
