/** BankAccount is a class for a very simple bank account created
 *  from a bank account and the name of the account holder.
 *  We distinguish three field variable:
 *  <pre>
 *   accountNumber, an int to uniquely find an account
 *   accountName, the name (or names) of the account holder(s), and
 *   balance, how much money is in the account.
 *  </pre>
 *  @author   Manfred Kerber
 *  @version  2017-10-04
 */
public class BankAccount{
    private int     accountNumber;
    private String  accountName;
    private int     balance;

    /** BankAccount is a constructor  for a very simple bank account created
     *  @param  accountNumber is the account number as int
     *  @param  accountName the account name as String
     */
    public BankAccount(int     accountNumber, 
		       String  accountName){
        this.accountNumber      = accountNumber;
        this.accountName        = accountName;
        this.balance            = 0;
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
    public int getBalance(){
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
     * @param accountNumber for the changed account number
     */
    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }

    /**
     *  sets the balance of a BankAccount 
     *  @param balance the new balance on the account
     */
    public void setBalance(int balance){
        this.balance = balance;
    }

    /** 
     *  this method checks whether the BankAccount is equal to a
     *  second BankAccount
     *  return true if the current BankAccount (*this*) is equal 
     *         to the BankAccount it is compared to, that is, 
     *         if it agrees with it in number, name, balance.
     *  @param a The second BankAccount.
     *  @return true if and only if the this bankaccount is equal to the
     *  bankaccount a wrt account number, account name, and balance.
     *  NOTE: equality is a tricky concept!
     */
    public boolean equals(BankAccount a){
        return
            (this.getAccountNumber() == a.getAccountNumber()) &&
            (this.getAccountName().equals(a.getAccountName())) &&
            (this.getBalance() == a.getBalance());
    }

    /** the amount will be taken from the balance
     *  @param  amount The amount to be withdrawn.
     */
    public void withdraw(int amount){
	setBalance(getBalance() - amount);
    }

    /** the amount will be added the balance
     *  @param amount The amount to be paid in.
     */
    public void payIn(int amount){
	setBalance(getBalance() + amount);
    }
}
