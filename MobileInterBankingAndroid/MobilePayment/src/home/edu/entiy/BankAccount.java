package home.edu.entiy;

public class BankAccount {
	private String bankName;
	private String routingNumber;
	private String accountNumber;
	private String fullName;
	
	public BankAccount() {
		// TODO Auto-generated constructor stub
	}

	public BankAccount(String bankName, String routingNumber,
			String accountNumber, String fullName) {
		super();
		this.bankName = bankName;
		this.routingNumber = routingNumber;
		this.accountNumber = accountNumber;
		this.fullName = fullName;
	}

	public String getBankName() {
		return bankName;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	public String getFullName() {
		return fullName;
	}

	
}
