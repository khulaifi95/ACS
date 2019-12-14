/** 
 *  The Transaction class is used in order to represent single
 *  transactions which are needed in the SavingsAccount class to
 *  compute the interest. A transaction is defined as consisting of
 *  four parts (represented in the field variables):
 *  <pre>
 *  private int dayOfYear; 
 *    The day of the year when the transaction took place
 *    (numbered from 1 through 365) 
 *  private String typeOfTransaction; 
 *    (One of "initial balance", "deposit", or "withdrawal")
 *  private long amount; 
 *    (The amount of the transaction.)
 *  private long balance; 
 *    (The balance after the transaction.)
 *  </pre>
 *
 *  @version 2016-11-11
 *  @author Manfred Kerber
 */


public class Transaction {

    private int dayOfYear;  
    private String typeOfTransaction;
    private long amount;
    private long balance;

    /**
     *  Constructor which initializes the four field variables.
     *  @param dayOfYear The day of the year when the transaction took place
     *    (numbered from 1 through 365).
     *  @param typeOfTransaction One of "initial balance", "deposit",
     *    or "withdrawal"
     *  @param amount The amount of the transaction.
     *  @param balance The balance after the transaction.
     */
    public Transaction(int dayOfYear, String typeOfTransaction,
                       long amount, long balance) {
        this.dayOfYear = dayOfYear;
        this.typeOfTransaction = typeOfTransaction;
        this.amount = amount;
        this.balance = balance;
    }

    /**
     *  Getter for the day of year
     *  @return The day of the year (number between 1 and 365 inclusively).
     */
    public int getDayOfYear() {
        return dayOfYear;
    }

    /**
     *  Getter for the type of the transaction.
     *  @return The type of the transaction as one of "initial
     *    balance", "deposit", or "withdrawal"
     */
    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    /**
     *  Getter for the amount of the transaction.
     *  @return The amount of the transaction.
     */
    public long getAmount() {
        return amount;
    }

    /**
     *  Getter for the balance after the transaction.
     *  @return The balance after the transaction.
     */
    public long getBalance() {
        return balance;
    }
}
