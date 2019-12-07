/**
 *  The Square class is a subclass of the Rectangle class. It does not
 *  contain any new field variables, but the values of the two field
 *  variables of the superclass are identical.
 * 
 *  @version 2017-11-16
 *  @author Manfred Kerber
 */

public class Square extends Rectangle {
    /**
     *  A Square is constructed by constucting a Rectangle with equal
     *  sides (i.e., height = width).
     *  @param a The side length in the Square.
     */
    public Square(double a) {
        super(a,a);
    }

    /*
     *  Main method for initial test.
     */
    public static void main(String[] args) {
        Square a = new Square(4.0);
        Square b = new Square(5.1);

        System.out.println(a);
        System.out.println(b);
    }
}
