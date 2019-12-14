/**
 *  This class is used to compute the area of rectangles.
 *  @author Manfred Kerber
 *  @version 2019-10-03
 */
public class Rectangle {
  /**
   *  The method computes the area of a rectangle
   *  with given width and height. 
   *  For instance, the area of a rectangle with
   *  width 4 and height 5 is 20.
   *  @param width The width of the rectangle.
   *  @param height The height of the rectangle.
   *  @return The area of the rectangle.
   */
  public static double area(double width, double height) {
    return width * height;
  }

  public static void main(String[] args) {
     double width = 4;
     double height = 5;
     System.out.println("A rectangle with width " + width + 
                        " and height " + height + 
                        " has an area of "+ area(width,height) +".");
  }
}
