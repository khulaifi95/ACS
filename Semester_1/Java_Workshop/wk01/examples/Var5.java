/** How to swap the values of two variables?
 *  Let two values be given e.g.,
 *  int a = 5; int b = 4;
 *
 *  We want that a contains the value of b and 
 *  b contains the value of a (whatever they are)
 *
 *  a = b; b = a;
 *  Does not work. Why not?
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */


public class Var5 { 
    public static void main(String[] args) {

       int a = 5;
       int b = 4;

       a = b; b = a;

       System.out.println(a);
       System.out.println(b);































       /* We need an auxiliary variable. Let's call it aux.

	  System.out.println();
	  int aux;
	  a = 5;
	  b = 4;

	  aux = a;
	  a = b;
	  b = aux;

	  System.out.println(a);
	  System.out.println(b);


       */

       /* Obviously the ORDER of assignments may matter. That is, the 6
	  following sequences may result in different behaviours.
	  Find out which. 
       int aux;

	  a = 5;b = 4;aux = a;a = b;b = aux;System.out.print("a: ");System.out.print(a);System.out.print(" b: ");System.out.println(b);
	  a = 5;b = 4;aux = a;b = aux;a = b;System.out.print("a: ");System.out.print(a);System.out.print(" b: ");System.out.println(b);
	  a = 5;b = 4;a = b;aux = a;b = aux;System.out.print("a: ");System.out.print(a);System.out.print(" b: ");System.out.println(b);
	  a = 5;b = 4;a = b;b = aux;aux = a;System.out.print("a: ");System.out.print(a);System.out.print(" b: ");System.out.println(b);
	  a = 5;b = 4;b = aux;a = b;aux = a;System.out.print("a: ");System.out.print(a);System.out.print(" b: ");System.out.println(b);
	  a = 5;b = 4;b = aux;aux = a;a = b;System.out.print("a: ");System.out.print(a);System.out.print(" b: ");System.out.println(b);
       */
   }
}
