package company;

/** 
 *  @author Manfred Kerber
 *  @version 2016-11-08 
 *
 * The following example is adapted from [Deitel and Deitel, 2010,
 * Java - How to program, 8th edition, p. 427]. The interface provides
 * only skeleton of a class, that is, here, that there are two methods
 * which take no arguments and give back an int each, the amount to be
 * paid, and the day of the month when it has to be paid. Obviously a
 * calling methods needs to know no more. However, in order to run the
 * methods we need implementations.  [In a real system we would have
 * further methods such as String getBankAccount() and String
 * getSortCode();]
 */
 
public interface Payable {
    /**
     *  @return The amount of money that has to be paid. 
     */
    int paymentAmount();
    /**
     *  @return The day of the month when the payment is due.
     */
    int dueDate();  
}
