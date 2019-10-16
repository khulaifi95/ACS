/** The class computes for rectangle the circumference. 
 * The sidelengths are represented by doubles.
 * @version 2016-09-30
 * @author Manfred Kerber
 */
public class Rectangle{ 
    /**
     *   @param a first side length of the rectangle
     *   @param b second side length of the rectangle
     *   @return The circumference of the rectangle
     */
    public static double circumference(double a, double b){
      return 2*(a + b);
   }

   /**
     *   @param a first side length of the rectangle
     *   @param b second side length of the rectangle
     *   Prints the circumference of the rectangle
     */

    public static void printCircumference(double a, double b) {
      System.out.println("The circumference of a " +                       "rectangle with side lengths "
+ a + " and " + b + " is " + circumference(a,b) + ".");
    }

    public static void main(String[] args){
       double result = circumference(1,2);
       System.out.println(result);
       result = circumference(1,circumference(2,3));
       System.out.println(result);
       printCircumference(4,5);
       printCircumference(4.1,5.7);
    }
}
