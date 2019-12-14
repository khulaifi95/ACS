/** We study here variables and assignments
 *
 *  If we want to understand what an assignment does we can imagine that
 *  we have variables represented by their names and their values in a
 *  table as follows:
 *  <pre>
 *	  -----++-----+-----+-----+-----+-----+
 *	  name ||  x  |  y  |  z  |  u  |  v  |
 *	  -----++-----+-----+-----+-----+-----+
 *	  val  ||  -  |  -  |  -  |  -  |  -  |
 *	  -----++-----+-----+-----+-----+-----+
 *  </pre>
 *
 *  After an assignment var = term; the term of the right hand side is
 *  evaluated with respect to the current values in the table. (If
 *  variables are needed for this evaluation which do not have a value yet
 *  - indicated by the hyphen in the table - then the assignment fails and
 *  an error occurs. E.g. x = x+1; would fail since x is not currently
 *  defined. However, x = 3; succeeds since the expression on the right
 *  hand side is defined.
 *
 *  Before we can use a variable we have to declare it with a type.
 *  In our example we declare the variables x,y,z,u,v to be of type  
 *  int by 
 *  <pre>
 *     int x,y,z,u,v;
 *  </pre>
 *
 *  The assignment x = 3; means that the table is updated, any
 *  old value of x (or no value in this case) is overwritten by
 *  the new value. Care has to be taken if the old value is
 *  still needed. It must be copied before the assignment,
 *  since after the assignment it is otherwise irrecoverably lost.
 *  <pre>
 *        -----++-----+-----+-----+-----+-----+
 *        name ||  x  |  y  |  z  |  u  |  v  |
 *        -----++-----+-----+-----+-----+-----+
 *        val  ||  3  |  -  |  -  |  -  |  -  |
 *        -----++-----+-----+-----+-----+-----+
 *  </pre>
 *
 * After the assignment  y = 2*(x+5); the table is
 * by evaluating the expression 2*(x+5) in the OLD TABLE
 * and then overwriting (or assigning for the first time) the
 * value of y by the result.
 * <pre>
 *	 -----++-----+-----+-----+-----+-----+
 *	 name ||  x  |  y  |  z  |  u  |  v  |
 *	 -----++-----+-----+-----+-----+-----+
 *	 val  ||  3  |  16 |  -  |  -  |  -  |
 *	 -----++-----+-----+-----+-----+-----+
 * </pre>
 *
 * After the assignment y = 2*(y+x*x);
 * <pre>
 *	 -----++-----+-----+-----+-----+-----+
 *	 name ||  x  |  y  |  z  |  u  |  v  |
 *	 -----++-----+-----+-----+-----+-----+
 *	 val  ||  3  |  50 |  -  |  -  |  -  |
 *	 -----++-----+-----+-----+-----+-----+
 * </pre>
 *
 * After the assignments z = x+y; u = 3*z; x = 0;
 * the table is:
 * <pre>
 *	 -----++-----+-----+-----+-----+-----+
 *	 name ||  x  |  y  |  z  |  u  |  v  |
 *	 -----++-----+-----+-----+-----+-----+
 *	 val  ||  0  |  50 |  53 | 159 |  -  |
 *	 -----++-----+-----+-----+-----+-----+
 * </pre>
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */

public class Var4 { 
    public static void main(String[] args) {
       int x,y,z,u,v;
       x = 3;
       System.out.print(" x: "); System.out.println(x);

       y = 2*(x+5);
       System.out.print(" x: "); System.out.print(x);System.out.print(" y: "); System.out.println(y);

       y = 2*(y+x*x);
       System.out.print(" x: "); System.out.print(x);System.out.print(" y: "); System.out.println(y);

       z = x+y; u = 3*z; x = 0;
       System.out.print(" x: "); System.out.print(x);
       System.out.print(" y: "); System.out.print(y);
       System.out.print(" z: "); System.out.print(z);
       System.out.print(" u: "); System.out.println(u);
       //System.out.print(" v: "); System.out.println(v);  // results in an error since v is not defined.
   }
}
