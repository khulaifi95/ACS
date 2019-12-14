/**
 *  The Square1 class is used to show a very simple example of a
 *  (static) method., name that of a square function that takes as
 *  argument the side length of square and return its area.
 *
 *  @version 2015-09-30
 *  @author Manfred Kerber
 *
 */

public class Square1 {

    /**
     * @param x  side length
     * @return area of the square.
     */
    public static double square(double x) {
        return x * x;
    }

    public static void main(String[] args) {

        System.out.println(square(5.1));
        System.out.println(square(5.2));
        System.out.println(square(-5.3));
    }
}
