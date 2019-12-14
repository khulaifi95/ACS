/** 
 *  An IslamicMortgageAccount is characterized that no interest is
 *  paid.  All the costs are included in the payout and the fees,
 *  which combined give the initial negative balance. Over a period of
 *  `months' many months the mortgage is paid back, in form of
 *  `months-1' many equal payments and the remainder in the first
 *  months. Remember that there is the general relationship n/m * m +
 *  n%m = n for number of type int.
 *
 *  @version 2016-11-11
 *  @author Manfred Kerber
 */
public class IslamicMortgageAccount extends BankAccount {

    /**
     *   The three additional field variables describe the running time
     *   the amount towards the house (payout) and the bank fees (cost).
     */
    private int months;
    private long payout;
    private long fee;
	
    /** 
     * This constructor creates an Islamic mortgage account from the
     * three parts, customer, accountNumber and password.
     * @param customer The customer of the BankAccount.
     * @param accountNumber The accountNumber of the BankAccount.
     * @param password The password to access the BankAccount.
     * @param months The number of months over which the mortgage has
     * to be paid back.
     * @param payout The sum that is paid towards the hose.
     * @param fee The bank fees.
     */
    public IslamicMortgageAccount(Customer customer, 
                                  String accountNumber, 
                                  String password, int months, long payout, long fee){
	
	super(customer, accountNumber, password);
	this.months = months;
	this.payout = payout;
	this.fee = fee;
	/* Initialize the balance to the negative of the payout
	 * and the fee combined.
	 */
	setBalance(- (payout + fee));	
    }
	
	
    /**
     *  Getter for the number of months
     *  @return The number of months over which the mortgage runs.
     */
    public int getMonths() {
        return months;
    }


    /**
     *  Getter for the payout.
     *  @return The sum that the mortgage pays towards the house.
     */
    public long getPayout() {
        return payout;
    }


    /**
     *  Getter for the fee.
     *  @return The fee for the bank fees that are incurred.
     */
    public long getFee() {
        return fee;
    }


    /**
     *  Setter for the number of months
     *  @param months The new number of months over which the mortgage runs.
     */
    public void setMonths(int months) {
        this.months = months;
    }

    /**
     *  Setter for the payout
     *  @param payout The new payout of the mortgage.
     */
    public void setPayout(long payout) {
        this.payout = payout;
    }

    /**
     *  Setter for the fee
     *  @param fee The new fees of the mortgage.
     */
    public void setFee(long fee) {
        this.fee = fee;
    }

    /**
     *  The mortgage is to be payed back equally over (months-1) many months.
     *  @return The monthly amount for paying back the mortgage for
     *  all months except the first.
     */
    public int followUpPayment() {
        return (int) (getPayout() + getFee()) / (getMonths() -1);
    }

    /**
     *  The mortgage is to be payed back equally over (months-1) many
     *  months. The remainder is to be paid back in the first month.
     *  @return The amount for paying back the mortgage in month one
     *  as the remainder compared to the some minus all other
     *  payments.
     */
    public int initialPayment() {
        return (int) (getPayout() + getFee()) % (getMonths() -1);
    }

    /** 
     *  This method checks whether there are sufficient funds
     *  available to withdraw an amount from the current balance.
     *  Since we want to disallow withdrawals, we always return false.
     *  We actually do not need this method, since for a mortgage account
     *  the balance will never be positive, hence we cannot withdraw (assumed
     *  the fundsAvailable method in the superclass is not changed).
     *  @param amount The amount to be withdrawn.
     *  @return always false.
     */
    @Override
    public boolean fundsAvailable(long amount) {
        return false;
    }

    /*
     *  main method for initial test.
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Customer carl = new Customer("Carl", "CS", "4141111");
        IslamicMortgageAccount carlsMortgage =
            new IslamicMortgageAccount(carl, "MA111",
                                       "sesame", 240, 90000, 10000);
        System.out.println(carlsMortgage);
        System.out.println(100000/240.0);
        System.out.println(100000/239.0);

        // Should give 98 = 100000 - (239*418)
        System.out.println(carlsMortgage.initialPayment());
        // Result 418 = 100000/239
        System.out.println(carlsMortgage.followUpPayment());
        // withdraw is rejected.
        carlsMortgage.withdraw(200, "sesame");
        // deposit
        carlsMortgage.deposit(98);
        carlsMortgage.deposit(418);
        carlsMortgage.deposit(418);
        carlsMortgage.deposit(418);
        carlsMortgage.deposit(418);
        carlsMortgage.deposit(418);
        carlsMortgage.deposit(418);
        System.out.println(carlsMortgage);
    }
}
