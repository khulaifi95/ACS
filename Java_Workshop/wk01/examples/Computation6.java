/** Rounding with Math.round, Math.ceil, Math.floor
 *
 *
 * Math.round returns the closest long if the argument is a double and
 * the closest int if the argument is a float. 
 * long, int, double, float are so-called types.
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */

public class Computation6 { 

    public static void main(String[] args) {

       System.out.print("System.out.println(Math.round(3.2));      ==>       "); System.out.println(Math.round(3.2));
       System.out.print("System.out.println(Math.round(3.5));      ==>       "); System.out.println(Math.round(3.5));
       System.out.print("System.out.println(Math.round(3.8));      ==>       "); System.out.println(Math.round(3.8));

       System.out.print("System.out.println(Math.round(4.2));      ==>       "); System.out.println(Math.round(4.2));
       System.out.print("System.out.println(Math.round(4.5));      ==>       "); System.out.println(Math.round(4.5));
       System.out.print("System.out.println(Math.round(4.8));      ==>       "); System.out.println(Math.round(4.8));

       System.out.print("System.out.println(Math.round(-3.2));     ==>       "); System.out.println(Math.round(-3.2));
       System.out.print("System.out.println(Math.round(-3.5));     ==>       "); System.out.println(Math.round(-3.5));
       System.out.print("System.out.println(Math.round(-3.8));     ==>       "); System.out.println(Math.round(-3.8));
       System.out.println();

/* Math.ceil returns the smallest double value that is greater than or
 *  equal to the argument and is equal to a mathematical integer 
 */

       System.out.print("System.out.println(Math.ceil(4.2));       ==>       "); System.out.println(Math.ceil(4.2));
       System.out.print("System.out.println(Math.ceil(4.5));       ==>       "); System.out.println(Math.ceil(4.5));
       System.out.print("System.out.println(Math.ceil(4.8));       ==>       "); System.out.println(Math.ceil(4.8));

       System.out.print("System.out.println(Math.ceil(-4.2));      ==>       "); System.out.println(Math.ceil(-4.2));
       System.out.print("System.out.println(Math.ceil(-4.5));      ==>       "); System.out.println(Math.ceil(-4.5));
       System.out.print("System.out.println(Math.ceil(-4.8));      ==>       "); System.out.println(Math.ceil(-4.8));
       System.out.println();


/* Math.floor returns the largest double value that is greater than or
 * equal to the argument and is equal to a mathematical integer 
 */

       System.out.print("System.out.println(Math.floor(4.2));      ==>       "); System.out.println(Math.floor(4.2));
       System.out.print("System.out.println(Math.floor(4.5));      ==>       "); System.out.println(Math.floor(4.5));
       System.out.print("System.out.println(Math.floor(4.8));      ==>       "); System.out.println(Math.floor(4.8));

       System.out.print("System.out.println(Math.floor(-4.2));     ==>       "); System.out.println(Math.floor(-4.2));
       System.out.print("System.out.println(Math.floor(-4.5));     ==>       "); System.out.println(Math.floor(-4.5));
       System.out.print("System.out.println(Math.floor(-4.8));     ==>       "); System.out.println(Math.floor(-4.8));
   }
}
