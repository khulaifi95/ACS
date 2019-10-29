import java.util.function.Function;
import java.util.ArrayList;
import java.util.Arrays;

/** 
 *  In this class we look at the three related methods
 *  Math.round(double value), Math.floor(double value),
 *  Math.ceil(double value), which return a rounded long (that is, up
 *  to exclusively .5 the next smaller long is returned, from
 *  inclusively .5 the next larger long is returned. In case of
 *  Math.floor always the double is taken that corresponds to the next
 *  long number that is equal to or smaller than it. Likewise in case
 *  of Math.ceil the one that is bigger or equal to it.  We compute a
 *  number of examples making use of a function and an array in which
 *  we store the values we want to compute.
 *
 *  @version 2017-10-24
 *  @author  Manfred Kerber
 */
public class RoundMain{

    /** 
     *  The function prints a String that corresponds to the
     *  application of the function f from Double to Long to the
     *  value. printForm is a readable form of the function.
     *  @param f The function for which the value is computed.
     *  @param printForm A written form of the function to display it
     *  symbolically.
     *  @param value The value to which the function is applied.
     */
    public static void printSingle(Function<Double,Long> f,
                                   String printForm,
                                   double value) {
        System.out.printf("%s%s%2.14f%s   %d \n", printForm,
                          "(", value, "):", f.apply(value));
    }

    /**
     *  The values for which the function value is computed are stored
     *  in an array of type double.
     */
    public static final double[] VALUES =
           {0.9, 1.0, 1.499999999, 1.5, 1.6, 1.7, 2.0, 2.5, 3.5, 4.5, 5.5,
            6.5, -1.7, -1.5, -1.3};

    /**  
     *  Alternatively, the values for which the function values are
     *  computed are stored in an ArrayList of type
     *  Double. Alternatively, we can add values one at a time by
     *  something like VALUE_LIST.add{0.9};
     */
    public static ArrayList<Double> VALUE_LIST =
        new ArrayList<Double>(Arrays.asList(0.9, 1.0, 1.499999999, 1.5, 1.6,
                                            1.7, 2.0, 2.5, 3.5, 4.5, 5.5, 6.5,
                                            -1.7, -1.5, -1.3));

    /**
     *  The function prints a String that corresponds to the
     *  application of the function f from Double to Long to the
     *  value. printForm is a readable form of the function.
     *  @param f The function for which the values are computed.
     *  @param printForm A written form of the function to display it
     *  symbolically.
     *  @param values The values to which the function is applied.
     *  (If we replace the array by an ArrayList, we have to change
     *  the type from double[] to an ArrayList of type Double.
     */
    public static void printArray(Function<Double,Long> f,
                                  String printForm,
                                  ArrayList<Double> values) {
        for (Double value : values) {
            printSingle(f, printForm, value);
        }
    }

    /* main method to test printArray */
    public static void main(String[] args) {
        //printArray((Double x) -> Math.round(x), "Math.round", VALUE_LIST);
        printArray((Double x) -> (long) Math.ceil(x), "(int) Math.ceil", VALUE_LIST);
        //printArray((Double x) -> (long) Math.floor(x), "(int) Math.floor", VALUES);
        /* We can replace all this now by one line.
        System.out.println("Math.round(0.9): " + Math.round(0.9));
        System.out.println("Math.round(1.0): " + Math.round(1.0));
        System.out.println("Math.round(1.499999999): " + Math.round(1.499999999));
        System.out.println("Math.round(1.5): " + Math.round(1.5));
        System.out.println("Math.round(1.6): " + Math.round(1.6));
        System.out.println("Math.round(1.7): " + Math.round(1.7));
        System.out.println("Math.round(2.0): " + Math.round(2.0));
        System.out.println("Math.round(2.5): " + Math.round(2.5));
        System.out.println("Math.round(3.5): " + Math.round(3.5));
        System.out.println("Math.round(4.5): " + Math.round(4.5));
        System.out.println("Math.round(5.5): " + Math.round(5.5));
        System.out.println("Math.round(6.5): " + Math.round(6.5));
        System.out.println("Math.round(-1.7): " + Math.round(-1.7));
        System.out.println("Math.round(-1.5): " + Math.round(-1.5));
        System.out.println("Math.round(-1.3): " + Math.round(-1.3));
        */
    }
}
