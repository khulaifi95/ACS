package myTest;

/** 
 *  The area of a rectangle is given by length * width. The area can
 *  be computed from the length and the width. Should we have three
 *  fields length, width, and area, when making use of the area often?
 *  That is very dangerous, since it introduces redundancy as shown in
 *  the example below. If not handled very carefully it can lead to
 *  incorrect result. Hence it should be avoided.
 *
 *  Compare Horstmann Java Concepts p.294
 *  
 *
 *  @version 2016-11-08
 *  @author  Manfred Kerber
 */
public class Rectangle {
    /** 
     * We have a redundant field variable area, which needs to be set
     * on construction
     */
    private double length;
    private double width;
    private double area;
    
    /**
     *  A rectangle is characterized by length, width, and area. Note
     *  that the last of the three is redundant.
     *  @param length The length of the rectangle.
     *  @param width The width of the rectangle.
     */
    public Rectangle(double length, double width){
        this.length = length;
        this.width = width;
        this.area = length * width;
    }

    /**
     *  Getter for length.
     *  @return The length of the rectangle. 
     */
    public double getLength() {
        return length;
    }

    /**
     *  Setter for length.
     *  @param length The new length of the rectangle. 
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     *  Getter for width.
     *  @return The width of the rectangle. 
     */
    public double getWidth() {
        return width;
    }

    /**
     *  Setter for width.
     *  @param width The new width of the rectangle. 
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     *  Getter for area.
     *  @return The area of the rectangle. 
     */
    public double getArea() {
        return area;
    }

    /**
     *  Setter for area.
     *  @param area The new area of the rectangle. 
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     *  @return A human readable String for a Coin object.
     */
    public String toString() {
        return "A rectangle with length " + getLength() + " and width " + getWidth() + " has an area of " + getArea();
    }
    
    /*
     *  Main method to show how easy it is to introduce an
     *  inconsistency when we have redundant field variables. We could
     *  have a rectangle with side lengths 10 and 20 and an area of 5.
     *  A much superior way would be to have only two field variable,
     *  length and width, and to always determine the area by a method
     *  area();
     *
     *  If you really need redundant information you should consider
     *  to impose a suitable class invariant which guarantees that the
     *  different values cannot result in illegal values.
     */
    public static void main(String[] args) {
        Rectangle a = new Rectangle(10, 20);
        System.out.println(a);
        a.setArea(5);
        System.out.println(a);
    }
}
