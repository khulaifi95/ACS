import java.util.ArrayList;

/** 
 *  For a SavingsAccount intrest is paid for the days that money is in
 *  the account. We assume that the year has always 365 days (no
 *  interest for a possible 366th day) and that the account is
 *  initially empty (balance 0). All transactions are stored in an
 *  ArrayList of all transactions.
 *
 *  @version 2016-12-01
 *  @author Manfred Kerber
 */
public class SavingsAccount extends BankAccount {

    /** 
     *  In addition to a standard BankAccount, we need the
     *  interestRate (as a double) and to store all transactions in
     *  order to compute the annual interest.
     */
    private double interestRate;
    private ArrayList<Transaction> transactions;

	
    /** 
     * This constructor creates a bank account from the
     * three parts, customer, accountNumber and password.
     * @param customer The customer of the BankAccount.
     * @param accountNumber The accountNumber of the BankAccount.
     * @param password The password to access the BankAccount.
     * @param interestRate The annual interest for the balance in the
     * BankAccount.
     */
    public SavingsAccount(Customer customer, String accountNumber, 
                          String password, double interestRate) {
        super(customer, accountNumber, password);
        this.interestRate = interestRate;
        this.transactions = new ArrayList<Transaction>();
        /* Add an initial transaction with initial deposit and
         * balance of 0 at day 1
         */
        transactions.add(new Transaction(1, "initial balance", 0, 0));
    }

    /**
     *   Method that adds amount to the current balance and adds to the
     *   transactions. Note that this method makes USE of the deposit
     *   method in the superclass, but DOES NOT OVERRIDE it, since it
     *   has a different argument list.
     *   @param amount The amount to be added to the current balance.
     *   @param day The day of year when the deposit took place.
     */
    public void deposit(long amount, int day) {
        deposit(amount);
        Transaction trans = new Transaction(day, "deposit", 
                                            amount, getBalance());
        transactions.add(trans);
    }
	
    /**
     *   Method that adds up the interests over the different periods
     *   with the different balance levels by computing the interest
     *   for a particular balance multiplied by the number of days for
     *   which this balance was valid divided by 365 and multiplied by
     *   the interest rate.
     *   @return The annual interest rounded to the next integer.
     */
    public int annualInterest() {
        double interest = 0;
        for (int i = 1; i < this.transactions.size(); i++) {
            interest += transactions.get(i-1).getBalance() *
                (transactions.get(i).getDayOfYear() -
                 transactions.get(i-1).getDayOfYear())
                /365.0
                * interestRate;
        }
        /* Add interest for the last period to day 365 */
        interest += transactions.get(this.transactions.size()-1).getBalance() *
            (366 - transactions.get(this.transactions.size()-1).getDayOfYear())/365.0
            * interestRate;
        return (int) Math.round(interest);
    }

    /*
     *  main method for initial test.
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Customer catheryn = new Customer("Catheryn", "CS", "012144444");
        SavingsAccount catherynsSavings = 
            new SavingsAccount(catheryn, "Savings111", 
                               "sesame", 0.01);
        catherynsSavings.deposit(1000,1);
        catherynsSavings.deposit(1000,201);
        System.out.println(catherynsSavings);
        System.out.println(catherynsSavings.annualInterest());

        Customer sam = new Customer("Sam", "CS", "012133333");
        SavingsAccount samsSavings =
            new SavingsAccount(sam, "Savings112",
                               "sesame",0.01);
        samsSavings.deposit(1000,1);
        System.out.println(samsSavings);
        System.out.println(samsSavings.annualInterest());

    }
}
