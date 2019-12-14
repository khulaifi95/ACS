/** Some more examples of assignment.
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */


public class Var2 { 
    public static void main(String[] args) {
       int x,y;

       System.out.print("System.out.println(x = 3;);     ==> ");  x = 3; System.out.println();
       System.out.print("System.out.println(x);          ==> ");  System.out.println(x);
       System.out.println();

       System.out.print("System.out.println(y = 4;);     ==> ");  y = 4; System.out.println();
       System.out.print("System.out.println(x+y);        ==> ");  System.out.println(x+y);
       System.out.println();

       System.out.print("System.out.println(x = x+y;);   ==> ");  x = x+y; System.out.println();
       System.out.print("System.out.println(x);          ==> ");  System.out.println(x);
       System.out.println();
   }
}
