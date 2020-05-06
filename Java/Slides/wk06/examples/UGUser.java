import java.util.ArrayList;

/** Note: It is very important to have a method getLongLoan() here -
 *       although it is syntactically identical to the method
 *       super.getLongLoan(), i.e. the getLongLoan() method in Student.java. 
 *       This is so since the method in the current class
 *       - UGUser - will return the value 20, while the identical method
 *       super.getLongLoan() will return 10. I.e. if we hadn't the method,
 *       ugUsers would get the long loan books only for 10 and not for
 *       14 days. No use would be made of the field variable longLoan = 14.
 */

public class UGUser extends StudentUser {
    private static int longLoanDefault = 10; // days

    private int longLoan;
	
    /**
     * Constructor for UGUser with default long loan value
     * @param firstName the first name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of the user
     * @param booksOnLoan the books which the user has on loan.
     */
    public UGUser(String firstName, String surname, String phoneNumber,
                  ArrayList<BookOnLoan> booksOnLoan) {
        super(firstName, surname, phoneNumber, booksOnLoan);
        this.longLoan = longLoanDefault;
    }
	
    /**
     * Constructor for UGUser with explicit long loan value
     * @param firstName the first name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of the user
     * @param booksOnLoan the books which the user has on loan.
     * @param longLoan the number of days the user may take out a book if
     * if it is a longLoan book.
     */
    public UGUser(String firstName, String surname, String phoneNumber,
                  ArrayList<BookOnLoan> booksOnLoan, int longLoan) {
        super(firstName, surname, phoneNumber, booksOnLoan);
        this.longLoan = longLoan;
    }
	

    /**
     *  Getter for longLoan.
     *  @return The value of longLoan. 
     */
    public int getLongLoan() {
        return longLoan;
    }

    /**
     *  Setter for longLoan.
     *  @param longLoan The new value of longLoan. 
     */
    public void setLongLoan(int longLoan) {
        this.longLoan = longLoan;
    }
}
