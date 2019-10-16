import java.util.ArrayList;

/** BankAccount is a class for a very simple bank account created
 *  from a bank account and the name of the account holder.
 *  @author   Manfred Kerber
 *  @version  13 October 2015
 */
public class BankAccount{
    private int     accountNumber;
    private String  accountName;
    private Amount   balance;
    // private ArrayList<Transaction> statement;

    /** BankAccount is a constructor  for a very simple bank account created
     *  @param  accountNumber is the account number as int
     *  @param  accountName the account name as String
     */
    public BankAccount(int accountNumber, String  accountName){
        this.accountNumber      = accountNumber;
        this.accountName        = accountName;
        this.balance            = new Amount(0,0);
        // this.statement = new ArrayList<Transaction>();
    }

    /* Now we write methods to get the parts of a BankAccount,
     * so called accessor methods.
     */

    /** 
     *  @return the account number of a BankAccount as int
     */
    public int getAccountNumber(){
     return accountNumber;
    }

    /** 
     *  @return the accountName as a String
     */
    public String getAccountName(){
	return accountName;
    }

    /** 
     *  @return the balance of a BankAccount 
     */
    public Amount getBalance(){
     return balance;
    }

    /** toString defines how to print a BankAccount
     *  
     *  @return  the print type of an account
     */
    public String toString(){
	return "Account number: " + accountNumber + 
  	       " Account name: "  + accountName   +
               " Balance: "       + balance;   
    }

    /* Now we write methods to set the parts of a bank account,
     * so called setters.
     */

    /** 
     *  sets the account number of a BankAccount 
     * @param newAccountNumber for the changed account number
     */
    public void setAccountNumber(int newAccountNumber) {
        accountNumber = newAccountNumber;
    }

    /**
     *  sets the balance of a BankAccount 
     *  @param  newBalance the new balance on the account
     */
    public void setBalance(Amount newBalance){
        balance = newBalance;
    }

    /** the amount will be added to the balance
     *  @param  amount the amount to be paid in
     */
    public void payin(Amount amount){
	setBalance(getBalance().add(amount));
    }

    /** the amount will be taken from the balance
     *  @param  amount the amount to be withdrawn
     */
    public void withdraw(Amount amount){
	if (this.getBalance().biggerOrGreater(amount)) {
	    setBalance(getBalance().subtract(amount));
	}
	else System.out.println("Insufficient funds");
    }
}
