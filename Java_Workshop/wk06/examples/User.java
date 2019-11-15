import java.util.*;

/**
 * 
 * @author Manfred Kerber
 * @version 2015-11-03
 * 
 * The abstract class User of a library consists of the fields
 * firstName, surname, phoneNumber, booksOnLoan (an ArrayList of books
 * that the user has on loan), as well as the number of days the user
 * can take out books on longLoan (which is typically 30, but may be
 * individually set differently)
 * 
 * The class is abstract in order to prevent direct creation of
 * objects. This can only happen in the subclasses.
 * 
 * The class consists of constructors, some getters and setters, a
 * toString method as well as a takeOutBook method.
 *
 */
public abstract class User {
    // a typical user can take books for 30 days on loan.
    private static int longLoanDefault = 30;
	
    private String firstName;
    private String surname;
    private String phoneNumber;
    private ArrayList<BookOnLoan> booksOnLoan;
    private int longLoan; 

    /**
     *  Constructor for User which takes the time they can take out
     *  books as the default value.
     *  @param firstName The first name of the user.
     *  @param surname The surname of the user.
     *  @param phoneNumber The phone number of the user.
     *  @param booksOnLoan The books the user has on loan.
     */
    public User(String firstName, String surname, String phoneNumber,
                ArrayList<BookOnLoan> booksOnLoan) {
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.booksOnLoan = booksOnLoan;
        this.longLoan = longLoanDefault;
    }

    /**
     *  Constructor for User
     *  @param firstName The first name of the user.
     *  @param surname The surname of the user.
     *  @param phoneNumber The phone number of the user.
     *  @param booksOnLoan The books the user has on loan.
     *  @param longLoan The number of days the user can take books on loan.
     */
    public User(String firstName, String surname, String phoneNumber,
                ArrayList<BookOnLoan> booksOnLoan, int longLoan) {
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.booksOnLoan = booksOnLoan;
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

    /**
     *  Getter for firstName.
     *  @return The value of first name. 
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *  Setter for firstName.
     *  @param firstName The new value of first name. 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *  Getter for surname.
     *  @return The value of surname. 
     */
    public String getSurname() {
        return surname;
    }

    /**
     *  Setter for surname.
     *  @param surname The new value of surname. 
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *  Getter for phone number.
     *  @return The value of the phone number. 
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *  Setter for phone number.
     *  @param phoneNumber The new value of the phone number. 
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *  Getter for the books on loan.
     *  @return The books on loan by the user.
     */
    public ArrayList<BookOnLoan> getBooksOnLoan() {
        return booksOnLoan;
    }

    /**
     * @param book the book taken on loan and added to the ArrayList booksOnLoan
     * 
     * Note that the book taken out is converted into a BookOnLoan
     * object with the three field, book, now, and returnTime. The
     * book is determined by the parameter, now by the time now using
     * the Calendar class and the return time but either adding 1 day
     * to now (for short loans) or getLongLoan() of the user for books
     * not on short loan.
     */
    public void takeOutBook(Book book) {
		
        Calendar now = Calendar.getInstance();
        Calendar returnTime = Calendar.getInstance();
        returnTime.add(returnTime.DAY_OF_MONTH, 
                       book.getLoanType() == 0 ? 1 : getLongLoan());
        // The following line would be incorrect since
        // variables are not overridden, that it, longLoan 
        // would always be the value from the User class, i.e.
        // 30 days.
        // book.getLoanType() == 0 ? 1 : longLoan); 
        this.booksOnLoan.add(new BookOnLoan(book, now, returnTime));
    }

    /**
     *  @return A String representation of the User.
     */
    public String toString() {
        return firstName + " " + surname + " "
            + "Tel:" + phoneNumber + ",\n booksOnLoan:\n" + booksOnLoan;
    }
}
