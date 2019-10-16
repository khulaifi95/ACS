/** Problems with non-existing values
 *
 *
 *  When a value of a mathematical function does not exist you may get a NaN answer.
 *  Division by zero results in error, called an exception.
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */

public class Computation7{ 

    public static void main(String[] args)
   {

       /* Value that do not exist go to NaN */

       System.out.print("System.out.println(Math.sqrt(-1));          ==>       "); System.out.println(Math.sqrt(-1));
       System.out.print("System.out.println(Math.asin(2));           ==>       "); System.out.println(Math.asin(2));
       System.out.print("System.out.println(Math.acos(2));           ==>       "); System.out.println(Math.acos(2));


       /* Division by zero results in an error */

       System.out.print("System.out.println(1/0);                    ==>       "); System.out.println(1/0);

  }
}

