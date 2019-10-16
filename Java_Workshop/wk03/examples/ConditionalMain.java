/**
 *  Examples to demonstrate the if construct.
 *
 *  if has the structure if (<bool>) {<command>*}
 *   or in case of a single command if (<bool>)<command>
 *
 * The command(s) are/is only executed if the boolean expression bool
 * evaluates to true.  
 *
 * if else in an if (<cond>) {<commands>*} else {<commands>*} construct
 *   first the condition <cond> is evaluated. If it is true the commands
 *   following it will be executed, else the ones following the else.
 * 
 *  Note this program needs one argument to run, ie., call it eg. as
 *  java ConditionalTest evening
 *
 *  @version 2015-10-13
 *  @author Manfred Kerber
 */
public class ConditionalMain{ 

    public static void main(String[] args) {
 	int i = -5;
	if (i < 0){
            i = -i;
        }
	System.out.println(i);

	double x = -2.5;
	if (x >= 0){
            x = Math.sqrt(x);
        } else {
            x = Math.sqrt(-x);
        }
	System.out.println(x);

	if (x == 0) {  
            System.out.println(x);
        } else {
            System.out.println(1/x);
        }

	/* Test for equality on String type is .equals, not == */
	String str = args[0];
	if (str.equals("morning"))
	    System.out.println("Good morning, have a lovely day.");
	else if (str.equals("noon"))
	    System.out.println("Enjoy your lunch.");
	else if (str.equals("afternoon"))
	    System.out.println("Good afternoon, see you soon.");
	else if (str.equals("evening"))
	    System.out.println("See you tomorrow.");
	else 
	    System.out.println("Oops. I don't understand \"" + str + "\".");
    }
}
