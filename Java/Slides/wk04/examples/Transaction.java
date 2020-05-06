import java.util.*;

/**
 *  A Transaction is characterized by the fields amount, the transaction type
 *  <pre>
 * 2: deposit
 * 3: withdrawal
 * 4: transfer from
 * 5: transfer to
 * </pre>
 * the balance and the date of the transaction.
 *
 *  @version 2015-10-20
 *  @author Manfred Kerber
 */
public class Transaction {
   private long amount;
   private byte transactionType;
   private long balance;
   private String date;

    /** A constructor for a transaction. A time stamp is automatically added.
     *  @param amount The amount of the transaction.
     *  @param transactionType 2 for deposit, 3 for withdrawal, 4 for transfer from,  5 for transfer to.
     *  @param balance The balance after the transaction has been carried out.
     */
    public Transaction(long amount, byte transactionType, long balance) {
	this.amount = amount;
	this.transactionType = transactionType;
	this.balance = balance;
	this.date = new Date().toString();
	}

    /** Getter for amount.
     *  @return The amount of the transaction.
     */
    public long getAmount() {
	return amount;
    }

    /** Setter for amount.
     *  @param amount The new amount.
     */
    public void setAmount(long amount) {
	this.amount = amount;
    }

    /** Getter for the transactionType.
     *  @return The amount of the transaction.
     */
    public byte getTransactionType() {
	return transactionType;
    }

    /** Setter for amount.
     *  @param transactionType The new transaction type.
     */
    public void setTransactionType(byte transactionType) {
	this.transactionType = transactionType;
    }

    /** A toString method for printing a transaction.
     *  @return A readable form of the transaction.
     */
    public String toString() {
	String transactionTypePrint; 
        switch (this.getTransactionType()) {
        case 2:  transactionTypePrint = " has been payed in.";  	           break;
        case 3:  transactionTypePrint = " has been withdrawn";                     break;
        case 4:  transactionTypePrint = " has been transfered to another account.";break;
        case 5:  transactionTypePrint = " has been transfered into this account."; break;
        default: transactionTypePrint = " unknown transaction.";                   break;
        }
	return "On " + date + " the amount of " + amount + 
            transactionTypePrint + " Balance: " + balance;
    }
}
