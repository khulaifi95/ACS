import java.util.ArrayList;
/**
 *  @version 2016-10-31
 *  @author Manfred Kerber 
 *
 *  The class is used to compute the mean and the standard deviation
 *  of an ArrayList of type Measurable with two corresponding static
 *  methods. The class does not contain a constructor and the methods
 *  are all static.
 */
public class Compare {

    /**
     *  @param a An ArrayList of type Measurable.
     *  @return The mean value of the values (as given by
     *  getMeasure()) in the list.
     */
    public static double mean(ArrayList<Measurable> a) {
        double sum = 0;
        for (Measurable el : a) {
            sum += el.getMeasure();
        }
        return sum/a.size();
    }

    /**
     *  The following method has linear complexity, since the
     *  computation of the mean is linear and the loop requires to go
     *  exactly once through the ArrayList a and each single step has
     *  constant complexity.
     *  @param a An ArrayList of type Measurable.
     *  @return The standard deviation of the values (as given by
     *  getMeasure()) in the list.
     */
    public static double standardDeviation1(ArrayList<Measurable> a) {
        double mu = mean(a);
        double sum = 0;
        double aux;
        /* Loop invariant: sum contains the sum of all square
         * differences of the element values from the mean for all
         * those values visited.
         */
        for (Measurable el : a) {
            aux = (el.getMeasure() - mu);
            sum += aux * aux;
        }
        return Math.sqrt(sum/a.size());
    }

    /**
     *  The following method has quadratic complexity, since the
     *  computation of the mean is linear and the loop requires to go
     *  exactly once through the ArrayList a and each single step has
     *  linear complexity of recomputing the mean (two times).
     *  @param a An ArrayList of type Measurable.
     *  @return The standard deviation of the values (as given by
     *  getMeasure()) in the list.
     */
    public static double standardDeviation2(ArrayList<Measurable> a) {

        double sum = 0;
        /* Loop invariant: sum contains the sum of all square
         * differences of the element values from the mean for all
         * those values visited.
         */
        for (Measurable el : a) {
            sum += (el.getMeasure() - mean(a)) * (el.getMeasure() - mean(a));
        }
        return Math.sqrt(sum/a.size());
    }

    /*
     *  Main method to make experiments wrt the time the methods take.
     */
    public static void main(String[] args) {
        ArrayList<Measurable> p = new ArrayList<Measurable>();
        int n = 100000;
        int rand;
        // Generate an ArrayList of given size n with random values
        // between 50 and 150.
        for (int i = 0; i < n; i++) {
            rand = (int) (50 + 100 * Math.random());
            p.add(new Patient("John", 20, rand));
        }

        long before, after;

        before = System.nanoTime();
        System.out.println(standardDeviation1(p));
        after = System.nanoTime();
        System.out.println((after - before)/1000000 + "ms");

        before = System.nanoTime();
        System.out.println(standardDeviation2(p));
        after = System.nanoTime();
        System.out.println((after - before)/1000000 + "ms");

        //System.out.println("Mean weight: " + mean(p));
        //System.out.println("Standard deviation: " + standardDeviation(p));
    }
}
