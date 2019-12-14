/**
 *  The Ellipse class implements the Area interface, that is, it
 *  provides an implementation for the area method.
 * 
 *  @version 2017-11-16
 *  @author Manfred Kerber
 */
public class Ellipse implements Area {
    private double majorRadius;
    private double minorRadius;
    
    /**
     *  @param majorRadius The bigger radius of the Ellipse in cm.
     *  @param minorRadius The smaller radius of the Ellipse in cm.
     */
    public Ellipse(double majorRadius, double minorRadius) {
        this.majorRadius = majorRadius;
        this.minorRadius = minorRadius;
    }

    /**
     *  getter for the field variable majorRadius.
     *  @return The bigger radius of the Ellipse in cm.
     */
    public double getMajorRadius() {
        return majorRadius;
    }

    /**
     *  getter for the field variable minorRadius.
     *  @return The smaller radius of the Ellipse in cm.
     */
    public double getMinorRadius() {
        return majorRadius;
    }

    /**
     *  Standard toString method.
     *  @return The object in a human readable form.
     */
    public String toString() {
        return "The object has an area of " + area() + ".";
    }

    /**
     *  A method to compute the area of an Ellipse.
     *  @return The area of the Ellipse in cm^2.
     */
    public double area() {
        return Math.PI * getMajorRadius() * getMinorRadius();
    }
}
