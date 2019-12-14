/** Amount is a class for dealing with amount of money given in the form 
 *  pounds 3.14 without making use of double
 *  @author   Manfred Kerber
 *  @version  18 October 2013
 */
public class Amount{
    private int     fullpence;

    /** Amount is a constructor for creating an Amount.
     *  @param  pounds is the part of the amount in full pounds
     *  @param  pence is the part of the amount in pence
     */
    public Amount(int pounds, int pence) {
    fullpence   = 100*pounds + pence;
    }

    /* Now we write methods to get the parts of an Amount,
     * so called accessor methods.
     */

    /** 
     *  @return the pounds of an Amount as int
     */
    public int getPounds(){
     return fullpence/100;
    }

    /** 
     *  @return the pence of an Amount as int
     */
    public int getPence(){
	return fullpence%100;
    }

    /** 
     *  @return the fullpence of an Amount as int
     */
    public int getFullpence(){
	return fullpence;
    }

    /** toString defines how to print a BankAccount
     *  
     *  @return  the print type of an account
     */
    public String toString(){
	return "\u00A3" + getPounds() + "." + getPence();   
    }

    /* Now we write methods to set the parts of a bank account,
     * so called setters.
     */

    /**
     *  sets the amount converted to pence
     *  @param  newFullpence for the changed fullpence value
     */
    public void setFullpence(int newFullpence){
     fullpence = newFullpence;
    }

    /** the amount will be added to the balance
     *  @param  amount to be added
     *  @return the sum
     */
    public Amount add(Amount amount){
	int localFullpence = this.getFullpence() + amount.getFullpence();
	return new Amount(localFullpence/100,localFullpence%100);
    }

    /** the amount will be taken from the balance
     *  @param  amount the amount to be withdrawn
     *  @return the difference
     */
    public Amount subtract(Amount amount) {
        int localFullpence = getFullpence() - amount.getFullpence();
	return new Amount(localFullpence/100,localFullpence%100);
    }

    /** the amount will be checked against the current amount
     *  @param amount the amount the object is compared to.
     *  @return true if the object is bigger than or equal to amount, 
     *  false else.
     */
    public boolean biggerOrGreater(Amount amount) {
	return this.getFullpence() >= amount.getFullpence();
    }

    /** checks whether the object is equal to a second amount
     *  @param  amount the amount to be compared with.
     *  @return true if the object is equal to the amount, false else.
     */
    public boolean equals(Amount amount){
	return (this.getFullpence() == amount.getFullpence());
    }
}
