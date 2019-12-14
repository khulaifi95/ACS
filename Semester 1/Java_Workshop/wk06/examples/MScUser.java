import java.util.ArrayList;

/** Note: It is very important to have a method getLongLoan() here -
 *       although it is syntactically identical to the method
 *       super.getLongLoan(), i.e. the getLongLoan() method in Student.java. 
 *       This is so since the method in the current class
 *       - MScUser - will return the value 20, while the identical method
 *       super.getLongLoan() will return 10. I.e. if we hadn't the method,
 *       MScUsers would get the long loan books only for 10 and not for
 *       20 days. No use would be made of the field variable longLoan = 20.
 *       
 *       Likewise the setter in this class changes the value of the corresponding
 *       variable in this class, but the super.setLongLoan changes the value 
 *       in the superclass StudentUser. If there was not a method in this class 
 *       on calling the method setLongLoan automatically the method in the superclass
 *       would be taken.
 *  
 *  @version 2015-11-03
 *  @author Manfred Kerber
 */

public class MScUser extends StudentUser {

    private static int longLoanDefault = 20; // days

    private int longLoan;
	
    /**
     * 
     * @param firstName the first name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of the user
     * @param booksOnLoan the books which the user has on loan.
     */
    public MScUser(String firstName, String surname, String phoneNumber,
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
    public MScUser(String firstName, String surname, String phoneNumber,
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
