/** Use of variables in unit conversion
 *
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */

public class Var6 { 
    public static void main(String[] args) {

       /* Example: Conversion imperial to metric system 
        */

       double cmPerInch = 2.54;
       double inchPerFoot = 12;
       double cmPerFoot = cmPerInch * inchPerFoot;

       /*  Assume a person with a height of 5 foot 3 inches. 
        *  What is their height in cm? 
        */

       double x = 5; // foot
       double y = 7; // inches


       double z = cmPerFoot*x + cmPerInch*y;

       System.out.print("A person with a height of "); 
       System.out.print(x);
       System.out.print(" foot and ");
       System.out.print(y);
       System.out.print(" inches is ");
       System.out.print(z);
       System.out.println(" cm tall.");
   }
}
