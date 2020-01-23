/**  This class is to test the BankAccount class and its methods.
 *   We create some objects of class BankAccount and
 *   get some parts back.
 */
public class BankAccountMain{ 
    public static void main(String[] args) {
	BankAccount manfredBankAccount =  new BankAccount(1, "Manfred");
	// This creates account number 1 and account name "Manfred"
        // with an initial balance of pounds 0.

	System.out.println(manfredBankAccount);
        System.out.println();

	Amount manfredsPayIn = new Amount(430,65);
	System.out.println("Pay in " + manfredsPayIn);
        manfredBankAccount.payin(manfredsPayIn);
	System.out.println(manfredBankAccount);
        System.out.println();

	Amount manfredsWithdrawal = new Amount(4,35);
	System.out.println("Withdraw " + manfredsWithdrawal);
        manfredBankAccount.withdraw(manfredsWithdrawal);
	System.out.println(manfredBankAccount);
        System.out.println();

	Amount manfredsWithdrawal2 = new Amount(444,35);
	System.out.println("Withdraw " + manfredsWithdrawal2);
        manfredBankAccount.withdraw(manfredsWithdrawal2);
	System.out.println(manfredBankAccount);
    }
}
