//import java.util.IllegalArgumentException;
/**
 *   The class contains a method compute the minimal element in a
 *   two-dimensional array of type double[][].
 * 
 *   @author Manfred Kerber
 *   @version 2017-11-16
 */

public class Min {

    /**
     *  The method to compute the minimal element in an array of type
     *  double[][].  The code has the problem that an array may
     *  contain elements, but none in the first row that then it will throw an 
     *  ArrayIndexOutOfBoundsException.
     *  @param a An arbitrary array of type double[][].
     *  @return The minimum of all the elements in the array.
     */
    public static double min(double[][] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException("No elements in array provided to method min.");
        } else {
            double min = a[0][0];
            // Iteration over all rows
            for (double[] row : a) {
                // Iteration over all elements in a row.
                for (double el : row) {
                    if (el < min) {
                        min = el;
                    }
                }
            }
        return min;
        }
    }

    /**
     *  The method to compute the minimal element in an array of type double[][]
     *  @param a An arbitrary array of type double[][].
     *  @return The minimum of all the elements in the array.
     */
    public static double minImproved(double[][] a) {
        double min = Double.MAX_VALUE;
        boolean elementFound = false;
        /* Iteration over all rows to check whether there is a row
         * with an element. If yes, the element is taken as the
         * provisional minimum and the variable elementFound is set to
         * true. The loop is left immediately when such an element is
         * found. 
         */
        for (double[] row : a) {
            if (row.length > 0) {
                min = row[0];
                elementFound = true;
                break;
            }
        }
        // If there is no element in the array an IllegalArgumentException is thrown.
        if (! elementFound) {
            throw new IllegalArgumentException("No elements in array provided to method min.");
        } else {
            // Iteration over all rows.
            for (double[] row : a) {
                /* Iteration over all elements in a row. min will be
                 * the minimal element seen so far (if seen any). 
                 */
                for (double el : row) {
                    if (el < min) {
                        min = el;
                    }
                }
            }
            return min;
        }
    }


    /*
     *  Main method for initial test.
     */
    public static void main(String[] args) {
        
        double[][] a = {{2.2, 4.4, 6.6, 11.1, 3.3, 9.8, 0, -4.2},
                        {-3.2},
                        {1, 2, 3, 4, 5, 6, 7}};
        double[][] b = {{-4, -6, -1, -3, -9, -1, -4}};
        double[][] c = {{0, 1, 2}, {}};
        double[][] d = {{}, {-1, 1, 2}, {}};
        try {
        System.out.println(minImproved(a));
        System.out.println(minImproved(b));
        System.out.println(minImproved(c));
        System.out.println(minImproved(d));
        System.out.println("************************************");
        System.out.println(min(a));
        System.out.println(min(b));
        System.out.println(min(c));
        System.out.println(min(d));
        }
        catch (IllegalArgumentException e) {
            System.out.println("Oops, no element found.");
        }
    }
}
