/**
 *  @version 2016-10-31
 *  @author Manfred Kerber 
 *
 *  The Patient class implements the Measurable interface and as such
 *  it has to contain a method double get Measure(), which in this
 *  case returns the weight of a patient.
 */

public class Patient implements Measurable {

    /**
     *   The field variables represent the name, the age, and the
     *   weight of a patient.
     */
    private String name;
    private int age;
    private double weight;

    /**
     *  A constructor to create objects.
     *  @param name The name of the patient.
     *  @param age The age of the patient.
     *  @param weight The weight of the patient.
     */
    public Patient(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    /**
     *  Implementation of the getMeasure() method.
     *  @return The measure is the weight of the patient.
     */
    public double getMeasure(){
        return this.weight;
    }
}

