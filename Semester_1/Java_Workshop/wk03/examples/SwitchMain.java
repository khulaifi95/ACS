/**
 *  The class demonstrates the usage of the "switch" conditional.
 *  @author Manfred Kerber
 *  @version 2019-10-14
 */

public class SwitchMain { 
    public static void main(String[] args) {
	/* switch provides a convenient way to select between different 
         *   integer values and char
         * <pre>
	 * the syntax is 
	 * switch(var){
	 *  case value1: ... break;
	 *  case value2: ... break;
	 *  ...
	 * default: ... break;
	 * }
	 * </pre>
	 * If the default is missing and none of the cases occurs then
	 * the switch statement does nothing. 
	 */

	int input = Integer.parseInt(args[0]);
	String output = "";

	System.out.println("You have entered " + input + ".");

	switch (input){
	    case 1:
		System.out.println("Please enter your credit card details now.");
		output = "credit card";
		break;
	    case 2:
		System.out. println("Please enter your username and password. Then press enter.");
		output = "login";
		break;
	    case 3:
		System.out.println("You have forgotten your password. A new one will be sent to you by email. Please change it when you log in again.");
		output = "email";
		break;
	    case 4:
		System.out.println("You abort the transaction. Your basket will be emptied.");
		output = "abort";
		break;
	    case 5:
		System.out.println("Congratulations: you won a prize");
		break;
	    default:
		System.out.println("We will store your data until you call in again.");
		output = "no choice";
		break;
	    }

	System.out.println(output);
    }
}
