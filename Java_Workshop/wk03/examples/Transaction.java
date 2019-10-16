import java.util.Date;
import java.util.*;

/**
 * 
 * @author Manfred Kerber
 * @version 2015-10-15 
 *
 * We define a transaction in a bank account which consists of the
 * parts transactionType such as "pay in" or "withdrawal", the balance
 * before the transaction, the amount of the transaction, the amount
 * after the transaction and a timestamp that gives the date and time
 * of the transaction. For this we use the GregorianCalendar class.
 * 
 * For the transaction we have just a constructor and a toString method.
 */

public class Transaction {
    private String transactionType;
    private Amount oldBalance;
    private Amount change;
    private Amount newBalance;
    private String timeStamp;

    /**
     *  @param transactionType Currently either payin or withdraw.
     *  @param oldBalance The balance before the transaction.
     *  @param change The amount of the transaction.
     *  @param newBalance The balance after the transaction.
     */
    public Transaction(String transactionType, Amount oldBalance, Amount change,
				Amount newBalance) {
	GregorianCalendar date = new GregorianCalendar();
	int year, month, day, hour, minute, second;
	year = date.get(Calendar.YEAR);
	month = date.get(Calendar.MONTH);
	day = date.get(Calendar.DAY_OF_MONTH);	
	hour = date.get(Calendar.HOUR_OF_DAY);
	minute = date.get(Calendar.MINUTE);
	second = date.get(Calendar.SECOND);

	this.transactionType = transactionType;
	this.oldBalance = oldBalance;
	this.change = change;
	this.newBalance = newBalance;
	this.timeStamp = year + "-" + month + "-" +
	    day + " " + hour + ":" + minute + ":" + second;
    }

    /**
     *  @return A string representation of the transaction. 
     */
    public String toString() {
	return transactionType + ": oldBalance: " + oldBalance + " " + 
	     " with " + change + 
	    " result newBalance: " + newBalance + 
	    " executed @ " + this.timeStamp;
    }

    public static void main(String[] args) {
        Transaction t1 = new Transaction("payin", new Amount(200,0),
                                         new Amount(20,0), new Amount(220,0));
        Transaction t2 = new Transaction("withdrawal", new Amount(220,0),
                                         new Amount(50,0), new Amount(170,0));

        System.out.println(t1);
        System.out.println(t2);
    }
}
