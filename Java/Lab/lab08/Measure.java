/**
 *  This class is to create objects which contain a description and a
 *  value which is always non-negative (e.g., this could be films with
 *  a rating, bank accounts with a non-negative balance, processes
 *  with times needed to complete them).

 *  @version 2015-11-20
 *  @author Manfred Kerber
 */

public class Measure {
    /**
     *  The field variables store the description of the object and
     *  its numerical value.
     */
    private String description;
    private double value;

    /**
     *  Constructor to create objects of type measure.
     *  @param description The verbal description of the object.
     *  @param value The numerical value.
     */
    public Measure(String description, double value) {
        this.description = description;
        this.value = value;
    }

    /**
     *  Getter for description
     *  @return The verbal description of the object.
     */
    public String getDescription() {
        return description;
    }

    /**
     *  Getter for value
     *  @return The numerical value of the object.
     */
    public double getValue() {
        return value;
    }
}
