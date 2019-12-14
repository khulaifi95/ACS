/** Use of variables in unit conversion
 *  Var6.java revisited: How not to do it.
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */

public class Var7 { 
    public static void main(String[] args) {

       /* The previous example revisited.
	*  What's the difference?
	*  Advantages/disadvantages
        */

       double cmPerInch = 2.54;
       double inchPerFoot = 12;
       double cmPerFoot = cmPerInch * inchPerFoot;

       /* Assume a person with a height of 5 foot 7 inches. 
        * What is their height in cm? 
        */

       System.out.print("A person with a height of "); 
       System.out.print(5);
       System.out.print(" foot and ");
       System.out.print(7);
       System.out.print(" inches is ");
       System.out.print(cmPerFoot*5 + cmPerInch*7);
       System.out.println(" cm tall.");

       /* What's the difference? See below */




























       /* In contrast to the previous program, which uses variables, this
        * program is not as easily adjusted. In the previous program, we can
	* easily change the height of a person in a SINGLE PLACE by changing
	* the values for x and y and get a correct answer. Here, however, we
	* have to change it at several places, and in doing so, we may easily
	* forget one place.
        *
	* We always want to write programs so that they can be EASILY CHANGED
	* later on. This is important in debugging (elimination of errors) or
	* extensions of programs.
        *
	* For this reason, the current program is BAD programming style and
	* should be avoided.
        */
   }
}
