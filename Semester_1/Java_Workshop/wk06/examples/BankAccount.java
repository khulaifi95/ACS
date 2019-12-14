/**
 *
 * In the following we define again the BankAccount class
 * as we have seen it previously. A BankAccount consists of a
 * customer, a number (for the account number), a password, the
 * balance, and the currency.
 * 
 * @author Manfred Kerber
 * @version 2015-11-03
 */
public class BankAccount {

    private Customer customer;
    private String number;
    private String password;
    private long balance; 

    /**
     *  there is some unclarity here whether we actually 
     * represent pounds or pence (or another currency), 
     * a better solution would be to represent the balance
     * separately for full pounds and pence
     */
    private static String stringCurrency = "\u00A3";

    /** 
     * This constructor creates a bank account from the
     * three parts, customer, accountNumber and password.
     * @param customer The customer of the BankAccount.
     * @param accountNumber The accountNumber of the BankAccount.
     * @param password The password to access the BankAccount.
     */
    public BankAccount (Customer customer, 
			String accountNumber, 
			String password) {
        this.customer = customer;
        this.number = accountNumber;
        this.password = password;
        this.balance = 0;
    }

    /**
     *  Getter for the balance
     *  @return The balance of the account. 
     */
    public long getBalance(){
        return balance;
    }

    /**
     * Getter method to return the password. As a minimal protection
     * mechanism we make the getter private. It would be better to use
     * strong encryption to protect the password.
     * @return The password of the BankAccount.
     */
    private String getPassword(){
     return password;
    }

    /**
     * Method to compare the password with a given password.
     * @param password The password to which the password of the this
     * object is compared.
     * @return true if the password of the BankAccount agrees with the
     * argument, false else.
     */
    public boolean checkPassword(String password){
        return this.getPassword().equals(password);
    }

    /**
     *  Setter for the password, protected by making it private.
     *  @param password The new password for the account.
     */
    private void setPassword(String password){
	this.password = password;
    }

    /**
     *  Getter for the customer
     *  @return The customer of the account. 
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *  Getter for the account number
     *  @return The number of the account. 
     */
    public String getNumber() {
        return number;
    }

    /**
     *  Method to return the currency universal to the class.
     *  @return The String of the currency.
     */
    public static String getStringCurrency() {
        return stringCurrency;
    }

	
    /**
     *  Setter for the balance. It allows to reset the balance in an
     *  account. Particularly well suited for fraud if made public
     *  rather than private. We need it public here, since if it were
     *  private we could not use it in any sub-class.
     *  @param amount The new amount in the account
     */
    public void setBalance(long amount) {
        balance = amount;
    }
	
    /**
     *   Method that adds amount to the current balance.
     *   @param amount The amount to be added to the current balance.
     */
    public void deposit(long amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);
        }
    }
	

    /** 
     *  This method is to withdraw an amount from the current balance
     *  if the password is correct and the balance will not fall below zero.
     *  @param amount The amount to be withdrawn.
     *  @param password The password of the account
     */
    public void withdraw(long amount, String password) {
        if (checkPassword(password) && getBalance() >= amount){
            setBalance(getBalance() - amount);
        } else {
            System.out.println("Withdrawal rejected. " +
                               "Insufficient funds or wrong password");
        }
    }

    /**
     * Changes the password from old to new if the old password is
     * correct, an error message is printed else
     * @param oldPassword The current password
     * @param newPassword The updated password
     */
    public void changePassword(String oldPassword, String newPassword) {
        if (checkPassword(oldPassword)) {
            printPasswordChangeSuccessful();
            setPassword(newPassword);
        } else {
            printWrongPassword();
        }
    }

    /**
     *  returns the name of a BankAccount's holder 
     *  @return The customer name
     */
    public String holder() {
        return customer.getCustomerName();
    }

    /**
     *  transferMoney transfers money from the current account to the
     *  toAccount if the password is correct and the amount not
     *  greater than the current balance.
     *  @param toAccount The account to which which the money is transferred.
     *  @param amount The amount to be transferred.
     *  @param password The password of the account.
     */
    public void transferMoney(BankAccount toAccount,long amount,
                              String password){
        if (checkPassword(password) && (getBalance() >= amount)) {
            setBalance(getBalance() - amount);
            toAccount.deposit(amount);
        } else {
            System.out.println("Transfer rejected." +
                               "Insufficient funds or wrong password");
        }
    }
    
    /**
     * ***************************************************************
     * print functions. Separate the output from the actual program
     */

    /**
     *  prints the balance of a BankAccount
     */ 
    public void printBalance() {
        System.out.printf("The balance of %s is %s %d.\n", 
                          customer.getCustomerName(), stringCurrency, balance);
    }
	
    /**
     *  prints that a deposit was successful.
     *  @param amount The amount deposited.
     */ 
    public void printDepositSuccessful(long amount) {
        /* prints that a deposit was successful
         */
        System.out.printf("The deposit of %s %d for %s was successful.\n", 
                          stringCurrency,amount,customer.getCustomerName());
    }

    /**
     *  prints that a withdrawal was successful.
     *  @param amount The amount withdrawn.
     */ 
    public void printWithdrawSuccessful(long amount) {
        /* prints that a withdrawal was successful
         */
        System.out.printf("The withdrawal of %s %d for %s was successful.\n", 
                          stringCurrency,amount,customer.getCustomerName());
    }

    /**
     *  prints that a transfer was successful.
     *  @param amount The amount transferred.
     */ 
    public void printTransferSuccessful(long amount) {
        /* prints that a withdrawal was successful
         */
        System.out.printf("The transfer of %s %d for %s was successful.\n", 
                          stringCurrency,amount,customer.getCustomerName());
    }

    /**
     *  prints that a change of password was successful.
     */ 
    public void printPasswordChangeSuccessful() {
        /* prints that a password change was successful.
         */
        System.out.printf("Change of password for %s was successful.\n", 
                          customer.getCustomerName());
    }

    /**
     *  prints that a change of password was unsuccessful.
     */ 
    public void printWrongPassword() {
        System.out.printf("The password you entered for %s was incorrect.\n", 
                          customer.getCustomerName());
    }

    /**
     *  toString method for an account
     *  @return A String representation of the account
     */
    public String toString() {
        return "Account number: " + number + 
            " balance:" + balance;
    }

    /*****************************************************************
     * END of print functions. Separate the output from the actual program
     */
}
