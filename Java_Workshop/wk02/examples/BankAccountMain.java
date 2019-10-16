/**  This class is to test the BankAccount class and its methods.
 *   We create some objects of class BankAccount and
 *   get some parts back.
 *   @version 2017-10-04
 *   @author Manfred Kerber
 */
public class BankAccountMain{
    
    public static void main(String[] args) {
	BankAccount manfredBankAccount =  new BankAccount(1, "Manfred");
	// This creates account number 1 with an
        // initial balance of pounds 0.

	System.out.println(manfredBankAccount);

        manfredBankAccount.withdraw(200);
	System.out.println(manfredBankAccount);

        manfredBankAccount.payIn(100);
	System.out.println(manfredBankAccount);
    }
}
