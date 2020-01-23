import java.util.*;
/** 
 * 
 * @author Manfred Kerber
 * @version 2015-11-03
 * 
 * A book on loan consists of a Book as well the start and end date of
 * a loan.
 *
 */
public class BookOnLoan {
    private Book book;
    private Calendar loanDate;
    private Calendar returnDate;

    /**
     *  Constructor for BookOnLoan
     *  @param book The book that is taken on loan.
     *  @param loanDate The date when the book was taken out.
     *  @param returnDate The date by when the book must be returned.
     */
    public BookOnLoan(Book book, Calendar loanDate, Calendar returnDate) {
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    /**
     *  Getter for book.
     *  @return The book of type Book. 
     */
    public Book getBook() {
        return book;
    }

    /**
     *  Setter for book.
     *  @param book The new book of type Book. 
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     *  Getter for loanDate.
     *  @return The date when the book was taken on loan.
     */
    public Calendar getLoanDate() {
        return loanDate;
    }

    /**
     *  Setter for loanDate.
     *  @param loanDate The new date when the book was taken on loan.
     */
    public void setLoanDate(Calendar loanDate) {
        this.loanDate = loanDate;
    }

    /**
     *  Getter for returnDate.
     *  @return The date when the book must be returned.
     */
    public Calendar getReturnDate() {
        return returnDate;
    }

    /**
     *  Setter for returnDate.
     *  @param returnDate The new date when the book must be returned.
     */
    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }

    /**
     *  A toString method for the BookOnLoan
     *  @return a representation of the book plus of the dates when it
     *  was taken out and needs to be returned.
     */
    public String toString() {
        return book.toString() + ",\n taken out on: " + 
            loanDate.get(loanDate.YEAR) + "-" +
            (loanDate.get(loanDate.MONTH)+1) + "-" + 
            loanDate.get(loanDate.DAY_OF_MONTH) +
            "\n to be given back: " + 
            returnDate.get(returnDate.YEAR) + "-" +
            (returnDate.get(returnDate.MONTH)+1) + "-" + 
            returnDate.get(returnDate.DAY_OF_MONTH) + "\n\n";
    }
}
