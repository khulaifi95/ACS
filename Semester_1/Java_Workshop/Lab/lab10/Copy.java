/**
 *  The class contains mainly two methods, one to copy one-dimensional
 *  arrays, one to copy two-dimensional arrays.
 *
 *  @version 2018-11-20
 *  @author Manfred Kerber
 */

public class Copy {
    /**
     *  A method to print a one-dimensional array of double[].
     *  @param a An arbitrary array of type double[].
     */
    public static void print(double[] a) {
        /* The elements up to index i have been printed. */
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

    /**
     *  A method to print a two-dimensional array of double[][].
     *  @param a An arbitrary array of type double[][].
     */
    public static void print(double[][] a) {
        /* The rows up to index i have been printed. */
        for (int i = 0; i < a.length; i++) {
            print(a[i]);
        }
        System.out.println();
    }

    /**
     *  A method to copy a one-dimensional array of double[].
     *  @param a An arbitrary array of type double[].
     *  @return An array with exactly the same elements.
     */
    public static double[] copy(double[] a) {
        double[] result = new double[a.length];
        /* The elements up to index i have been copied. */
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i];
        }
        return result;
    }

    /**
     *  A method to copy a two-dimensional array of double[][].
     *  @param a An arbitrary array of type double[][].
     *  @return An array with exactly the same elements.
     */
    public static double[][] copy(double[][] a) {
        double[][] result = new double[a.length][];
        /* The rows up to row i have been copied. */
        for (int i = 0; i < a.length; i++) {
            result[i] = copy(a[i]);
        }
        return result;
    }

    /*
     *  Main method for initial test.
     */
    public static void main(String[] args) {
        double[][] a = {{1.3, 2.5, 55},
                        {5.3, 1},
                        {2.0},
                        {1,2,3,4,5,6,7}};
        double [][] b = copy(a);
        double [][] c = a;
        a[1][1] = 2;
        print(a);
        System.out.println("--------------------------");
        print(b);
        System.out.println("--------------------------");
        print(c);
    }
    
}
