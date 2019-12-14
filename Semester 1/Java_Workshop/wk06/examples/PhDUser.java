import java.util.ArrayList;
/** Note: It is very important to have a method getLongLoan() here -
 *       although it is syntactically identical to the method
 *       super.getLongLoan(), i.e. the getLongLoan() method in Student.java. 
 *       This is so since the method in the current class
 *       - PhDUser - will return the value 20, while the identical method
 *       super.getLongLoan() will return 10. I.e. if we hadn't the method,
 *       PhDUsers would get the long loan books only for 10 and not for
 *       60 days. No use would be made of the field variable longLoan = 60.
 */

public class PhDUser extends StudentUser {

    private static int longLoanDefault = 60; // days
    private int longLoan;

    /**
     * Constructor for PhDUser with default long loan value
     * @param firstName the first name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of the user
     * @param booksOnLoan the books which the user has on loan.
     */
    public PhDUser(String firstName, String surname, String phoneNumber,
                   ArrayList<BookOnLoan> booksOnLoan) {
        super(firstName, surname, phoneNumber, booksOnLoan);
        this.longLoan = longLoanDefault;
    }
	
    /**
     * @param firstName the first name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of the user
     * @param booksOnLoan the books which the user has on loan.
     * @param longLoan the number of days the user may take out a book if
     * if it is a longLoan book.
     */
    public PhDUser(String firstName, String surname, String phoneNumber,
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
