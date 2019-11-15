import java.util.ArrayList;

/**
 * 
 * @author Manfred Kerber
 * @version 2015-11-03
 * 
 * Unlike objects of other classes, objects of the class CommunityUser
 * also understands the method getFee(), which gives the fee that the
 * user has to pay to take a book out.
 * 
 * The toString method is overridden in order to also display the fee
 * due for a book.
 *
 */
public class CommunityUser extends User {
    private int fee;
	
    /**
     * Constructor for CommunityUser
     * @param firstName the first name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of the user
     * @param booksOnLoan the books which the user has on loan.
     * @param fee The fee the community user has to pay for taking out a book.
     */
    public CommunityUser(String firstName, String surname, String phoneNumber,
                         ArrayList<BookOnLoan> booksOnLoan, int fee) {
        super(firstName, surname, phoneNumber, booksOnLoan);
        this.fee = fee;
    }
	
    /**
     *  Getter for fee.
     *  @return The value of the fee the community user has to pay per book. 
     */
    public int getFee() {
        return this.fee;
    }
	
    /**
     *  Setter for fee.
     *  @param fee The new value of the fee the community user has to pay per book. 
     */
    public void setFee(int fee) {
        this.fee = fee;
    }
	
    /**
     *  toString() method extended by displaying the fee.
     *  @return A representation of the community user as a String.
     */
    @Override
    public String toString() {
        return super.toString() + "\n Fee: \u00A3" + getFee();
    }
}
