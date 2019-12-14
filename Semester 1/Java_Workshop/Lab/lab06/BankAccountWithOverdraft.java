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
     *  This method checks whether there are sufficient funds
     *  available to withdraw an amount from the current balance.
     *  @param amount The amount to be withdrawn.
     *  @return true if and only if sufficient funds are available.
     */

    @Override
    public boolean fundsAvailable(long amount) {
        return getOverDraftLimit() + getBalance() >= amount;
    }
    

    /**
     *  toString method for an account with overdraft
     *  @return A String representation of the account
     */
    public String toString() {
        return super.toString() +
            " overdraft limit: " + getOverDraftLimit();
    }

}
