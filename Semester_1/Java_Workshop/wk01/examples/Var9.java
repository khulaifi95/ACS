/** Use meaningful variable names,
 *  conversion imperial to metric lengths
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */

public class Var9 { 
    public static void main(String[] args) {

       double cmPerInch = 2.54;
       double inchPerFoot = 12;
       double cmPerFoot = cmPerInch * inchPerFoot;

       /* Assume a person with a height of 5 foot 3 inches. What is their
        * height in cm? 
        */

       double feet   = 5; // foot
       double inches = 7; // inches

       double cm = cmPerFoot*feet + cmPerInch*inches;

       System.out.println("A person with a height of " + feet + " foot and " + inches + " inches is " + cm + " cm tall.");
   }
}
