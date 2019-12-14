/**
 *
 *  Assume two receiver units are 100 km apart, arranged in a plane at
 *  coordinates A=(0,0) and B=(0,b2)=(0,100). They locate the directions
 *  of a spy sender at angles alpha=32 degree and beta=145 degree with
 *  respect to the line AB. Write a program that computes the position
 *  C=(c1,c2) of the sender. Use variables b2, alpha, beta and compute c1
 *  and c2 from them. Do not forget to comment your program and to System.out.print
 *  out the result appropriately.
 *
 *  In figure <a href="./Var10.jpg">Var10.jpg</a>, we have that tan is
 *  defined so that tan(alpha)= h/{h_a}$ and $tan(beta)=h/{h_b}$.  Note
 *  that tan expects as input that the angle is given in radiant and not
 *  in degree. Find a method in the Math class from <a
 *  href="http://docs.oracle.com/javase/1.4.2/docs/api/">the Java api</a> which
 *  does the conversion.
 *  <pre>
 *  --------------------------------------------------------------------------------
 *  </pre>
 *
 *  Let l1 be the line through (0,0) and (c1,c2).
 *  It can be written as tan(alpha) * x.
 *
 *  Let l2 be the line through (0,b20 and (c1,c2).
 *  It can be written as  - tan(beta) * (x - b2).
 *
 *  Since the two lines intersect in (c1,c2) follows
 *
 *  tan(alpha) * c1 = - tan(beta) * (c1 -b2) or
 *
 *  c1 = b2/(1 + tan(alpha)/tan(beta)) and in consequence
 *  c2 = b2 * tan(alpha)/(1 + tan(alpha)/tan(beta))
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */

public class Var10 { 
    public static void main(String[] args) {

       double alpha = 32; //The angle alpha in degree.
       double beta  = 55; //The angle beta in degree.
       // double alpha = 60; //The angle alpha in degree. Test for 60 deg
       // double beta  = 60; //The angle beta in degree.  Test for 60 deg
       double b2 = 100;   //The coordinate b2 in metres.

       double alphar = Math.toRadians(alpha); //The angle alpha in radiant
       double betar =  Math.toRadians(beta);  //The angle beta  in radiant

       // The input to tan must be in radiant.
       double c1 = b2/(1 + Math.tan(alphar)/Math.tan(betar));
       double c2 = b2 * Math.tan(alphar)/(1 + Math.tan(alphar)/Math.tan(betar));

       // Print the result:
       System.out.println("The coordinates of the sender are (" + c1 + "," + c2 + ").");
   }
}
