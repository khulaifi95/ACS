/**
 *  In the class a credit card is represented by the three fields
 *  name, accountNumber, and amount.
 *  @author Alexandros Evangelidis and Manfred Kerber
 *  @version 2019-11-05
 */

public class CreditCard {

    private String name;
    private String accountNumber;
    private int  amount;

    /**
     * Standard constructor
     * @param name the name of the credit card
     * @param accountNumber the account number of the credit card
     * @param amount the amount of money available
     */
    public CreditCard(String name, String accountNumber, int amount) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    /** 
     * @return the amount
     */
    public int getAmount() {
	return amount;
    }

    /**
     * @param amount the new amount to be set
     */   
    public void setAmount(int amount) {
	this.amount = amount;
    }
}
