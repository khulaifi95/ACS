/**
 *  An extension of the BankAccount with the additional field variable
 *  overDraftLimit so that customers can withdraw (or transfer) money
 *  to bring the balance in the negative up to the overDraftLimit.
 *
 *  @version 2015-11-04
 *  @author Manfred Kerber
 */

public class BankAccountWithOverdraft extends BankAccount{

    private long overDraftLimit;
	
    /** 
     * This constructor creates a bank account from the
     * three parts, customer, accountNumber and password.
     * @param customer The customer of the BankAccount.
     * @param accountNumber The accountNumber of the BankAccount.
     * @param password The password to access the BankAccount.
     * @param overDraftLimit The maximal amount to which the account
     * can go in debit.
     */
    public BankAccountWithOverdraft(Customer customer, 
                                    String accountNumber, 
                                    String password,
                                    long overDraftLimit) {
        // First, call the constructor of the superclass
        // by using the `super' construct in the correct order.
        super(customer, accountNumber, password);
        this.overDraftLimit = overDraftLimit;
    }

    /**
     *  Getter for the overDraftLimit
     *  @return The overDraftLimit of the account. 
     */
    public long getOverDraftLimit() {
        return this.overDraftLimit;
    }
    
    /**
     *  withdraw is changed to that an amount can be withdrawn as long
     *  as the overDraftLimit and the balance combined are greater
     *  than or equal to the amount to be withdrawn.
     *  @param amount The amount to be withdrawn.
     *  @param password The password of the account.
     */
    @Override
    public void withdraw(long amount, String password) {
        if (checkPassword(password) && 
            getOverDraftLimit() + getBalance() >= amount){
            setBalance(getBalance() - amount);
        } else {
            System.out.println("Withdrawal rejected. " +
                               "Insufficient funds or wrong password");
        }
    }
	
    /**
     *  transferMoney is changed to that an amount can be transferred
     *  as long as the overDraftLimit and the balance combined are
     *  greater than or equal to the amount to be transferrred.
     *  @param toAccount The account to which which the money is transferred.
     *  @param amount The amount to be transferred.
     *  @param password The password of the account.
     */
    @Override
    public void transferMoney(BankAccount toAccount,long amount, String password) {
        if (checkPassword(password) && 
            (getOverDraftLimit() + getBalance() >= amount)) {
            setBalance(getBalance() - amount);
            toAccount.deposit(amount);
        } else {
            System.out.println("Transfer rejected." +
                               "Insufficient funds or wrong password");
        }
    }

    /**
     *  toString method for an account with overdraft
     *  @return A String representation of the account
     */
    @Override
    public String toString() {
        return super.toString() +
            " overdraft limit: " + getOverDraftLimit();
    }

}
