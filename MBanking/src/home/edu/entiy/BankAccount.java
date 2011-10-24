package home.edu.entiy;

import home.edu.util.MessageUtil;

import java.io.IOException;
import java.io.Serializable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

public class BankAccount implements Serializable{
	
	private static final long serialVersionUID = 1L;
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
	
	public ISOMsg createSavingMsg(){
		try {
			byte[] objectByte = MessageUtil.convertObjectToByteArray(this);
			ISOMsg isoMsg = new ISOMsg();
			isoMsg.setMTI ("0200");
			isoMsg.set (3, "000004");
			isoMsg.set (41, "00000025");
			isoMsg.set (42, MessageUtil.getHexString(this.fullName.getBytes()));
			isoMsg.set (48, MessageUtil.getHexString(objectByte));
			//isoMsg.set (61, "QUAN MINH LE AAAAAAAAAAAAAAAAAA");
			return isoMsg;
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static BankAccount fromIsoMessage(ISOMsg isoMsg){
		try {
			String bits = isoMsg.getString(48);
			byte[] bytes = MessageUtil.bitsToBytes(bits);
			return (BankAccount)MessageUtil.convertByteArrayToObject(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
