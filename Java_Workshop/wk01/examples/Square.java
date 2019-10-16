/** The class computes the area of a square with length a (with an integer a). 
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */

public class Square {
   public static void main(String[] args) {
       int a;
       // Integer.parseInt transforms a string into an integer,
       // e.g. "123" --> 123.
       a = Integer.parseInt(args[0]); 
       System.out.println("The area of a square with side length " + a + " is " + a*a + ".");
   }
}
