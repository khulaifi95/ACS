/**
 *  The class shows some examples how to catch different exceptions
 *  that can occur when the user is asked to enter to integers so that
 *  the second is not zero.
 *
 *  @version 2015-10-20
 *  @author Manfred Kerber
 */
public class Exceptions{

    public static void main(String[] args) {
        int a,b;
        try {
            a = Integer.parseInt(args[0]); 
            b = Integer.parseInt(args[1]);
                
            System.out.println(a/b);

        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops, two arguments required");
        }
        catch (ArithmeticException e) {
            System.out.println("oops, do not divide by 0");
        }
        catch(NumberFormatException e) {
            System.out.println("You need to provide two integers");
        }
        finally {
            System.out.println("Many thanks for using this service.");
        }
    }
}
