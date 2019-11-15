/**
 * 
 * @author Manfred Kerber
 * @version 2015-11-03
 * 
 * A book consists in this class of three parts, a title, an author,
 * and its loanType (0 for short loan, i.e., 1 day, and 1 for long
 * loan).
 *
 */

public class Book {
    private String title;
    private String author;
    private byte loanType; // 0 = short, 1 = long

    /**
     *  Contructor for Book
     *  @param title The title of the book.
     *  @param author The author(s) of the book
     *  @param loanType 0 = short, 1 = long loan
     */
    public Book(String title, String author, byte loanType) {
        this.title = title;
        this.author = author;
        this.loanType = loanType;
    }

    
    /**
     *  Getter for loanType.
     *  @return The value of the loan type. 
     */
    public byte getLoanType() {
        return loanType;
    }

    /**
     *  Setter for loanType.
     *  @param loanType The new value of the loan type. 
     */
    public void setLoanType(byte loanType) {
        this.loanType = loanType;
    }
	
    /**
     *  @return A String representation of the Book.
     */
    public String toString() {
        String loan;
        if (loanType == 0) 
            loan = " [short loan]";
        else loan = " [long loan]";

        return title + " by " + author + loan;
    }
}
