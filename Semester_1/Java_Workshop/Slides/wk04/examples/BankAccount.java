import java.util.ArrayList;

/**
 *  For simplicity we assume here that the balance is represented
 *  in Pounds and that only full pounds are used.
 *  @author Manfred Kerber
 *  @version 2015-10-20
 */
public class BankAccount{
    private Customer customer;
    private String password;
    private long balance; 
    private ArrayList<Transaction> statement;

    public static final String stringCurrency = "\u00A3";

    /**
     * This constructor creates a bank account from the
     * three parts, customer, accountNumber and password.
     * @param customer The customer according to the Customer class.
     * @param password A string to represent the password. Obviously in a real system
     * we would never store a password in its plain form, but would use a safe way as
     * described on http://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html, e.g.
     */
    public BankAccount (Customer customer, String password){
    this.customer = customer;
    this.password = password;
    this.balance = 0;
    this.statement = new ArrayList<Transaction>();
    }

    /**
     * Getter method to return the balance.
     * @return The balance of the BankAccount.
     */
    public long getBalance(){
     return balance;
    }

    /**
     * Getter method to return the customer.
     * @return The customer of the BankAccount.
     */
    public Customer getCustomer(){
     return customer;
    }
    
    /**
     * Getter method to return the password. As a minimal protection mechanism we make the getter private.
     * @return The password of the BankAccount.
     */
    private String getPassword(){
     return password;
    }

    /**
     * Method to compare the password with a given password.
     * @param  password The password to which the password of the this object is compared.
     * @return true if the password of the BankAccount agrees with the argument, false else.
     */
    public boolean checkPassword(String password){
        return this.getPassword().equals(password);
    }


    /**
     * Getter method to return the statement. 
     * @return The current statement of the BankAccount.
     */
    public ArrayList<Transaction> getStatement() {
        return statement;
    }


    /** 
     * Adds the amount to the current balance
     * and adds the transaction to the statement
     * @param amount The amount being deposited.
     */
    public void deposit(long amount){
        setBalance(getBalance() + amount);
        this.getStatement().add(new Transaction(amount,(byte) 2, getBalance()));
    }

    /**
     *  Setter for the amount. Particularly well
     *  suited for fraud if made public rather than private.
     *  @param amount The new amount in the balance.
     */
    private void setBalance(long amount){
	balance = amount;
    }
 
    /**
     *  Setter for the password, declared private to prevent unauthorized access.
     *  @param password The new password for the account.
     */
    private void setPassword(String password){
	this.password = password;
    }
 
    /** withdraws amount from the current balance add transaction to
     *  the statement if there are sufficient funds and the password is
     *  correct.  Otherwise an appropriate error message is printed.
     *  @param amount The amount to be withdrawn.
     *  @param password The password to access the account.
     */
    public void withdraw(long amount, String password){
	if (this.getPassword().equals(password) && balance >= amount) {
	    setBalance(getBalance() - amount);
	    this.getStatement().add(new Transaction(amount,(byte) 3, getBalance()));
	    }
	else if (this.getPassword().equals(password)) {
	    printInsufficientFunds(amount);
        }
        else {
            printWrongPassword();
        }
    }

    /** 
     * Changes the password from old to new 
     * if the old password is correct,
     * else an error message is printed.
     * @param oldPassword The current password.
     * @param newPassword The future password.
     */
    public void changePassword(String oldPassword, String newPassword){
	if (oldPassword.equals(this.getPassword())){
	    setPassword(newPassword);
        } else {
            printWrongPassword();
        }
    }
  
    /** 
     * Transfers an amount of money from the this account to the
     * toAccount assumed the password is correct and the this account
     * has sufficient money, else an error message is printed.
     * @param toAccount The account to which the money is to be transferred.
     * @param amount The amount of money to be transferred.
     * @param password The password of the this account.
     */
    public void transferMoney(BankAccount toAccount,long amount, String password) {
	if (this.getPassword().equals(password) && (balance >= amount)) {
            setBalance(getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            this.getStatement().add(new Transaction(amount, (byte) 4, getBalance()));
            toAccount.getStatement().add(new Transaction(amount, (byte) 5, toAccount.getBalance()));
        } else if (this.getPassword().equals(password)) {   
            printInsufficientFunds(amount);
        } else {
            printWrongPassword();
        }
    }
    /**
     * @return The name of the customer
     */
    public String toString(){
        return this.getCustomer().toString();
    }
  
    /*****************************************************************
     * Print functions. By keeping these separate from the actual    *
     * program it is easy to adjust them, for instance, for a        *
     * different language.                                           *
     *****************************************************************/

    /** 
     *  Prints that a deposit was successful
     *  @param amount The amount that is deposited.
     */
    public void printDepositSuccessful(long amount){
	System.out.printf("The deposit of %s %d for %s was successful.\n", 
                          stringCurrency,amount,this.getCustomer().getName());
    }

    /** 
     *  Prints that a withdrawal was successful
     *  @param amount The amount that is withdrawn.
     */
    public void printWithdrawSuccessful(long amount){
	System.out.printf("The withdrawal of %s %d for %s was successful.\n", 
                          stringCurrency,amount,this.getCustomer().getName());
    }

    /** 
     *  Prints that a transfer was successful.
     *  @param amount The amount that is transferred.
     */
    public void printTransferSuccessful(long amount){
	System.out.printf("The transfer of %s %d for %s was successful.\n", 
                          stringCurrency,amount,this.getCustomer().getName());
    }

    /** 
     *  Prints that the funds are insufficient for a transaction.
     *  @param amount The amount that was to be transferred.
     */
    public void printInsufficientFunds(long amount){
	System.out.printf("The funds of %s %d for %s are insufficient "
                          + "for this transaction.\n",
                          stringCurrency,this.getBalance(),this.getCustomer().getName());
    }

    /** 
     *  Prints that a password change was successful.
     */
    public void printPasswordChangeSuccessful(){
	System.out.printf("Change of password for %s was successful.\n", 
			  this.getCustomer().getName());
    }

    /** 
     *  Prints that a password has not been recognized.
     */
    public void printWrongPassword(){
	System.out.println("The password you entered was not recognized.\n");
    }
    
    /** 
     *  A method to print the balance of a bank account.
     */
    public void printBalance(){
	System.out.printf("The balance of %s is %s %d.\n", 
			  this.getCustomer().getName(), stringCurrency, balance);
    }

    /** 
     *  A method to print a statement of a bank account.
     */
    public void printStatement() {
        System.out.println(this);
    	for (int i = 0; i < statement.size(); i++) {
            System.out.println(statement.get(i));
    	}
    }
    /*****************************************************************
     * END of print functions. Separate the output from the actual   *
     * program                                                       *
     *****************************************************************/
}
