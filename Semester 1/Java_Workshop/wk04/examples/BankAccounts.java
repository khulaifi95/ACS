import java.util.*;
import java.util.regex.Pattern;

/** This is a preliminary version of the BankAccounts class, however,
 * with a user interface. It is in several ways deficient.
 *
 * One deficiency is that passwords are stored as plaintext. In any
 * system that is taking security seriously this would never
 * happen. Typically they are not stored at all, but only a hashvalue
 * of the password is stored. If the password is re-entered, the
 * hashvalue is computed and this value is compared to the stored
 * hashvalue.
 *
 * [In a true system one would have to able to save the results to a
 *  file. One would make use of a database and use a Graphical User
 *  Interface.]
 * @version 2015-10-20
 * @author Manfred Kerber
 */
public class BankAccounts {
    //Some patterns for input as static final variables.
    //Pattern for choice of input: either 1, 2, or 3
    public static final Pattern p123 = Pattern.compile("[123]"); 
    //Pattern for choice of input: either 1, 2, 3, 4, or 5
    public static final Pattern p123456 = Pattern.compile("[123456]"); 
    //Any sequence letter, digit, or _ (at least one sign)
    public static final Pattern pLetterDigit_ = Pattern.compile("[a-zA-Z0-9_]+"); 
    //Any sequence of digits (at least one sign)
    public static final Pattern pNumber = Pattern.compile("[0-9]+"); 

    private ArrayList<BankAccount> allBankAccounts;
    //An ArrayList in which all bankAccounts are kept.

    /**
     *  On creation of a new BankAccountUserInterface a new arrayList
     */
    public BankAccounts(){
         allBankAccounts = new ArrayList<BankAccount>();

         //System.in is like System.out, however, for input and not for output.
         Scanner s = new Scanner(System.in);
         //can take value 1 (access existing account) 2 (create new account), or 3 (quit)
         byte topChoice = 1; // not 3, else immediate termination.
	
         while (topChoice != 3) {
             System.out.println("Please enter:\n"+
                                "1 if you want to access an existing account\n"+
                                "2 if you want to create a new account\n"+
                                "3 if you want to quit");
             try {topChoice = (byte) Integer.parseInt(s.next(p123));
                 switch(topChoice) {
                 case 1:  bankAccountExistingAccount(s);         break;
                 case 2:  bankAccountNewAccount(s);              break;
                 case 3:  System.out.println("quit");            break;
                 default: System.out.println("no such choice");  break;
                 }
             }
             /* Any input different from 1, 2, or 3 will be rejected
              * by an InputMismatchException and caught here.  Since
              * the scanner s will be exited, a new one has to be
              * created, otherwise we run into an endless loop.
              */
             catch (InputMismatchException e2) {
                 System.out.println("Unexpected input.");
                 s = new Scanner(System.in);
             }
         }	
    }
    
    /**
     *  In this method an existing account is accessed, 5 different options are given:
     *  <pre>
     *  1 = check balance
     *  2 = make a deposit
     *  3 = make a withdrawal
     *  4 = make a transfer
     *  5 = quit
     *  </pre>
     *
     *  First the account number and the password are read in. It
     *  is important to check that the accountNumber is in the
     *  appropriate range. Likewise for a money transfer.
     *  @param s The scanner from which the input is read.
     *
     */
    public void bankAccountExistingAccount(Scanner s) {
	int accountNumber;
	System.out.println("Please enter the account number of " +
			   "the account you want to access");
	byte        exChoice;
	boolean     exChoiceBool;
	String      password;
	long        amount;
	int         toAccountNumber;
	BankAccount toAccount;
	accountNumber = Integer.parseInt(s.next());
	if (0<= accountNumber && accountNumber < allBankAccounts.size()) {
	    BankAccount currentAccount = allBankAccounts.get(accountNumber);
	    System.out.println("Please enter the password.");
	    password = s.next();
	    if (currentAccount.checkPassword(password)) {
		exChoiceBool = true;
		while (exChoiceBool) {
		    System.out.println("Please enter:\n"+
				       "1 if you want to check the balance\n"+
				       "2 if you want to make a deposit\n"+
				       "3 if you want to make a withdrawal\n"+
				       "4 if you want to transfer money\n"+
				       "5 if you want a statement\n"+
				       "6 if you want to quit");
                    // The input must be one of 1,2,3,4,5,6
                    try {exChoice = (byte) Integer.parseInt(s.next(p123456));
                        switch(exChoice) {
                        case 1: System.out.println("check");
                            System.out.println("The current balance is: "
                                               + currentAccount.getBalance());
                            break;
                        case 2: System.out.println("deposit");
                            System.out.println("How much is to be paid in? (Enter only if you have got the money already).");
                            amount = (long) Integer.parseInt(s.next());
                            currentAccount.deposit(amount);
                            break;
                        case 3: System.out.println("withdrawal");
                            System.out.println("How much is to be withdrawn?");
                            amount = (long) Integer.parseInt(s.next());
                            currentAccount.withdraw(amount,password);
                            break;
                        case 4: System.out.println("quit");
                            System.out.println("How much is to be transferred?");
                            amount = (long) Integer.parseInt(s.next());
                            System.out.println("To which bank account?");
                            toAccountNumber = Integer.parseInt(s.next());
                            if (0 <= toAccountNumber && toAccountNumber < allBankAccounts.size()) {
                                toAccount = allBankAccounts.get(toAccountNumber);
                                currentAccount.transferMoney(toAccount, amount, password);
                            } else {
                                System.out.println("Account not found.");
                            }
                            break;					
                        case 5: System.out.println("Statement:");
                            currentAccount.printStatement();
                            break;
                        case 6: System.out.println("quit");
                            exChoiceBool = false;
                            break;
                        default: System.out.println("error in switch BankAccountUserInterfaceExAccount");
                            break;
		    	}
                    }
		    catch (InputMismatchException e2) {
		    	System.out.println("oops wrong input for account");
                        s = new Scanner(System.in);
		    }
		}
	    } else {
		currentAccount.printWrongPassword();
	    }
	} else {
	    System.out.println("Account does not exist");
	}
    }


    /**
     *  In this method a new account is created. The information for
     *  this needs to be inputted in form of customerName, customerGender,
     *  customerAddress, customerPhone, and password. All these are of
     *  type String, however, the first two and the last must match
     *  [a-zA-Z0-9_]+, the customerPhone must match [0-9]+.
     *
     *  Any created account will go as next element into the
     *  allBankAccounts array.
     *  
     *  @param s The scanner from which the input is read.
     *
     */
    public void bankAccountNewAccount(Scanner s){
 	String customerName;
        String customerGender;
	String customerAddress;
	String customerPhone;
	String password;

	System.out.println("Please enter the name of the account holder");
	customerName = s.next(pLetterDigit_);

        System.out.println("Please enter the gender of the account holder: either \"m\", \"f\", or \"x\"");
	customerGender = s.next(pLetterDigit_);

        System.out.println("Please enter the address of the account holder");
	customerAddress = s.next(pLetterDigit_);

        System.out.println("Please enter the phone number of the account holder");
	customerPhone = s.next(pNumber);
	
	Customer currentCustomer = 
	    new  Customer(customerName, customerGender, customerAddress, customerPhone);

	System.out.println("Please enter the password for the new account");
	password = s.next(pLetterDigit_);

	System.out.println("The account number of the new account is " +
			   allBankAccounts.size());
	
	BankAccount currentBankAccount = 
	    new BankAccount(currentCustomer, password);
	allBankAccounts.add(currentBankAccount);
    }
    
    public static void main(String[] args){
        new BankAccounts();
    }
}
