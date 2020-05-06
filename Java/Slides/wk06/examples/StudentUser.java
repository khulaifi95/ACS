import java.util.*;

/**
 * 
 * @author Manfred Kerber
 * @version 2015-11-03
 * 
 * The class StudentUser extends the class User. It inherits all the
 * methods from the superclass, except the ones dealing with longLoan,
 * since this is set to 10 days by default (standard).
 *
 */
public abstract class StudentUser extends User {
	
    private static int longLoanDefault = 10; // days
	
    private int longLoan;

    /**
     * Constructor for StudentUser with default long loan value
     * @param firstName the first name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of the user
     * @param booksOnLoan the books which the user has on loan.
     */
    public StudentUser(String firstName, String surname, String phoneNumber,
                       ArrayList<BookOnLoan> booksOnLoan) {
        super(firstName, surname, phoneNumber, booksOnLoan);
        this.longLoan = longLoanDefault;
    }
	
    /**
     * Constructor for StudentUser with explicit long loan value
     * @param firstName the first name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of the user
     * @param booksOnLoan the books which the user has on loan.
     * @param longLoan the number of days the user may take out a book if
     * if it is a longLoan book.
     */
    public StudentUser(String firstName, String surname, String phoneNumber,
                       ArrayList<BookOnLoan> booksOnLoan, int longLoan) {
        super(firstName, surname, phoneNumber, booksOnLoan);
        this.longLoan = longLoan;

    }

    /**
     *  Getter for longLoan.
     *  @return The value of longLoan. 
     */
    @Override
    public int getLongLoan() {
        return longLoan;
    }
	
    /**
     *  Setter for longLoan.
     *  @param longLoan The new value of longLoan. 
     */
    @Override
    public void setLongLoan(int longLoan) {
    }
}
