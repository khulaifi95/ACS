/**
 *  The class introduces complex numbers and the two basic operations
 *  of addition and multiplication. The two field variables are the
 *  realPart and the imaginaryPart of the complex number.
 *
 *  @version 2018-11-22
 *  @author Manfred Kerber
 */

public class Complex {

    /**
     *  field variable to represent the real part of the complex number.
     */
    private double realPart;
    /**
     *  field variable to represent the imaginary part of the complex number.
     */
    private double imaginaryPart;

    /**
     *  Standard constructor
     *  @param realPart The real part of the complex number.
     *  @param imaginaryPart The imaginary part of the complex number.
     */
    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    /**
     *  getter for the realPart
     *  @return The real part of the complex number 
     */
    public double getRealPart() {
        return realPart;
    }

    /**
     *  getter for the imaginaryPart
     *  @return The imaginary part of the complex number 
     */
    public double getImaginaryPart() {
        return imaginaryPart;
    }

    /**
     *  Method for adding a complex number to the complex number object.
     *  @param summand The complex number to be added the complex number object.
     *  @return The sum of the complex number object and the summand.
     */
    public Complex add(Complex summand) {
        return new Complex(this.getRealPart() + summand.getRealPart(),
                           this.getImaginaryPart() + summand.getImaginaryPart());
    }

    /**
     *  Method for multiplying a complex number with the complex number object.
     *  @param factor The complex number to be multiplied with the
     *  complex number object.
     *  @return The product of the complex number object and the
     *  factor.
     */
    public Complex multiply(Complex factor) {
        return new Complex(this.getRealPart() * factor.getRealPart() -
                           this.getImaginaryPart() * factor.getImaginaryPart(),
                           this.getRealPart() * factor.getImaginaryPart() +
                           this.getImaginaryPart() * factor.getRealPart());
    }

    /**
     *   Standard toString method.
     *   @return A human readable String to display the complex number.
     */
    public String toString() {
        return this.getRealPart() + " + i * " + this.getImaginaryPart();
    }

    /*
     *  main method for initial tests. 
     */
    public static void main(String[] args) {
        Complex c1_0 = new Complex(1,0);
        Complex c0_1 = new Complex(0,1);
        Complex c1_2 = new Complex(1,2);
        Complex c3_4 = new Complex(3,4);
        Complex c2_5 = new Complex(2,5);
        Complex c11_8 = new Complex(11,8);
        Complex c_7_7 = new Complex(-7,7);

        System.out.println(c11_8.multiply(c_7_7));
        System.out.println(c1_0.multiply(c1_2));
        System.out.println(c0_1.multiply(c1_2));
        System.out.println(c3_4.add(c2_5));
    }
}
