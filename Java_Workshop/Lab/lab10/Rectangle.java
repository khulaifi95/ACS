/**
 *  The Rectangle class implements the Area interface, that is, it
 *  provides an implementation for the area method. It contains
 *  another method, circumference as well as getters for the two
 *  fields length and width.
 * 
 *  @version 2017-11-16
 *  @author Manfred Kerber
 */
public class Rectangle implements Area {
    private double length;
    private double width;

    /**
     *  @param length The length of the Rectangle in cm.
     *  @param width The width of the Rectangle in cm.
     */
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    /**
     *  getter for the field variable length.
     *  @return The length of the Rectangle in cm.
     */
    public double getLength() {
        return length;
    }

    /**
     *  getter for the field variable width.
     *  @return The width of the Rectangle in cm.
     */
    public double getWidth() {
        return width;
    }

    /**
     *  Standard toString method.
     *  @return The object in a human readable form.
     */
    public String toString() {
        return "The object has an area of " + area() +
            " and a circumference of " + circumference() + ".";
    }

    /**
     *  A method to compute the area of a Rectangle.
     *  @return The area of the Rectangle in cm^2.
     */
    public double area() {
        return getLength() * getWidth();
    }
    
    /**
     *  A method to compute the circumference of a Rectangle.
     *  @return The circumference of the Rectangle in cm.
     */
    public double circumference() {
        return 2 * (getLength() + getWidth());
    }

    /*
     *  Main method for initial test.
     */
    public static void main(String[] args) {
        Rectangle a = new Rectangle(4.0, 3.0);
        Rectangle b = new Rectangle(2.0, 2.0);

        System.out.println(a);
        System.out.println(b);
    }
}
