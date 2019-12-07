/**
 * This class is part of the tutorial handout of week 6.
 * 
 * @authors Alexandros Evangelidis and Manfred Kerber.
 * @version 02-11-2018
 */
public class BankAccount implements Measurable {

	private double balance;
	private int accountNumber;
	private String accountName;

	/**
	 * Constructor to be used in the Measurable class.
	 * 
	 * @param balance the balance of an account.
	 */
	public BankAccount(double balance) {
		this.balance = balance;
	}

	/**
	 * Constructor to be used in the Fun class.
	 * 
	 * @param accountNumber an account number.
	 * @param accountName   an account name.
	 * @param balance       an account balance.
	 */
	public BankAccount(int accountNumber, String accountName, double balance) {
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.balance = balance;
	}

	/**
	 * @return the account number.
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber a new account number to be set.
	 */
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the account name.
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName a new account name to be set.
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @param balance a new balance to be set.
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the balance of an account.
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * toString defines how to print a BankAccount.
	 * 
	 * @return the print type of an account,
	 */
	@Override
	public String toString() {
		return "BankAccount [balance=" + balance + ", accountNumber=" + accountNumber + ", accountName=" + accountName
				+ "]";
	}

	@Override
	public double getMeasure() {
		return balance;
	}

}
