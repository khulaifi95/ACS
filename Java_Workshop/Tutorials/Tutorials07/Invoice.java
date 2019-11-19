/**
 *  @version 2016-10-31
 *  @author Manfred Kerber 
 *
 *  The Invoice class implements the Measurable interface and as such
 *  it has to contain a method double get Measure(), which in this
 *  case returns the value of the amount field variable.
 */

public class Invoice implements Measurable {
    /**
     *   The field variables contain information where the money has
     *   to been sent to (accountNumber and sortCode) as well as the
     *   amount.
     */
    private String accountNumber;
    private String sortCode;
    private double amount;

    /**
     *  Constructor
     *  @param accountNumber The number of the account to clear the invoice.
     *  @param sortCode The sort code of the account to clear the invoice.
     *  @param amount The amount of money to be paid.
     */
    public Invoice(String accountNumber, String sortCode, double amount){
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.amount = amount;
    }

    /**
     *  Implementation of the getMeasure() method.
     *  @return The measure is the amount to be paid.
     */
    public double getMeasure(){
        return this.amount;
    }
}
