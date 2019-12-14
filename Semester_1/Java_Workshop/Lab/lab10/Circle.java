/**
 *  The Circle class is a subclass of the Ellipse class. It does not
 *  contain any new field variables, but the values of the two field
 *  variables of the superclass are identical.
 * 
 *  @version 2017-11-16
 *  @author Manfred Kerber
 */

public class Circle extends Ellipse {
    /**
     *  A Circle is constructed by constucting an Ellipse with equal
     *  radii (i.e., majorRadius = minorRadius).
     *  @param radius The radius of the Circle.
     */
    public Circle(double radius) {
        super(radius,radius);
    }

    /**
     *  A method that looks like a getter for the radius, the radius
     *  being equal to field variable majorRadius (and minorRadius).
     *  @return The radius of the Circle in cm.
     */
    public double getRadius() {
        return getMajorRadius();
    }

    /**
     *  A method to compute the circumference of a Circle.
     *  @return The circumference of the Circle in cm.
     */
    public double circumference() {
        return 2 * Math.PI * getRadius();
    }

    /*
     *  Main method for initial test.
     */
    public static void main(String[] args) {
        Circle a = new Circle(4.0);
        Circle b = new Circle(5.1);

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.getRadius());
        System.out.println(b.getRadius());
        
    }
}
