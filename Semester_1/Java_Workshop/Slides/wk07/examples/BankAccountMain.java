/** 
 * Some tests for the BankAccount and BankAccountWithOverDraft
 * @version 2016-10-31
 * @author Manfred Kerber
 */

public class BankAccountMain {

	/*   This class is to test the BankAccountWithOverdraft
	 *   extension.  First we try the old test, then some new
	 *   ones. John has a standard account, Anne one with
	 *   Overdraft.
	 */
	public static void main(String[] args){
		Customer customerAnne = 
				new  Customer("Anne", "5 Bull Ring", "0121 555555");
		Customer customerJohn = 
				new  Customer("John", "5 Bull Ring", "0121 555555");
		BankAccount bAJohn = 
				new BankAccount(customerJohn, "123456788", 
						"sesame open");
                BankAccountWithOverdraft bAAnne = 
                    new BankAccountWithOverdraft(customerAnne, "123456789", 
                        "sesame open", 500);
                

		System.out.println("*** TESTS WITH JOHN'S BANKACCOUNT");

		System.out.println(bAJohn);

		bAJohn.deposit(100);
		bAJohn.withdraw(200,"sesame");
		bAJohn.withdraw(200,"sesame open");
		bAJohn.withdraw(50,"sesame open");
		System.out.println("John's balance: "); bAJohn.printBalance();
		bAJohn.withdraw(20,"sesame");
		bAJohn.changePassword("sesame","sesam");
		bAJohn.changePassword("sesame open","sesame closed");
		bAJohn.withdraw(10,"sesame open");
                bAJohn.withdraw(5,"sesame closed");

                bAJohn.printBalance();

                System.out.println("*** TESTS WITH ANNE'S BANKACCOUNT");

	        System.out.println(bAAnne);

		System.out.print("Anne's balance: "); bAAnne.printBalance();
                bAAnne.withdraw(100,"sesame open");
		System.out.print("Anne's balance: "); bAAnne.printBalance();
		System.out.print("Anne's overdraft limit is: "); System.out.println(bAAnne.getOverDraftLimit());
                
                System.out.println("Withdraw 1000 Anne "); bAAnne.withdraw(1000,"sesame open");
                System.out.println("Withdraw 50 Anne "); bAAnne.withdraw(50,"sesame");
                System.out.println("Deposit 150 Anne "); bAAnne.deposit(150);
                System.out.print("Anne's balance: "); bAAnne.printBalance();
                
               
	}
}

