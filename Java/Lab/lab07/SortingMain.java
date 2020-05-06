/**
 *  A main method to compare bubbleSort and quickSort.  Note the main
 *  method needs one argument, the size of the initial array, that is,
 *  the program needs to be called with something like
 *
 *  java SortingTest 1000
 *  
 *  @version 2017-10-25
 *  @author Manfred Kerber
 */

public class SortingMain {

    /**
     *  @param a A one-dimensional array to be printed.
     *  Using standard for loop.
     */
    public static void print(double[] a){
        System.out.println("[");
        for (int i=0; i < a.length; i++){ //i.e. in the example from 0..14
            System.out.print(a[i] + " ");
        }
        System.out.println("]");
    }

    /*
     *  A main method in which we first create a double array test of
     *  size args[0] and populate it with random number between 0 and
     *  multiplier (and copy it to an array test1).  We use then these
     *  arrays to test the selectionSort and the quickSort algorithms.
     *  In each case we check whether the supposedly sorted array is
     *  actually sorted and measure the time using System.nanoTime()
     *  to measure the time needed to sort the array.  
     *
     *  With inputs 1, 10, 100, 1000, 10000, 100000, 1000000 we
     *  observe the computational complexity of the two algorithms.
     *  
     *  Note that selectionSort will not answer in good time for the big values!
     *
     */
    public static void main(String[] args) {
        double[] a = {1.0, 2.0, 3.0};
        double[] b = {1.0, 3.0, 2.0};
        double[] c = {3.0, 2.0, 1.0};
        
        System.out.println("a "+ Sorting.isSorted(a)); // true
        System.out.println("b "+ Sorting.isSorted(b)); // false
        System.out.println("c "+ Sorting.isSorted(c)); // false
        
        long before, after;
        int size = Integer.parseInt(args[0]); 
        double multiplier = 100.0;

        double[] test = new double[size];
        for (int i = 0; i < size; i++) {
            test[i] = multiplier * Math.random();
        }

        double[] test1 = new double[size];
        for (int i = 0; i < size; i++) {
            test1[i] = test[i];
        }

        /* We save the system time before the execution and later
         * after the execution of the sorting algorithm in nano-
         * seconds (a nano-second is a billionth of a second). In
         * order to have better readability we divide the number by a
         * million and display the answer in milli-seconds (a milli-
         * second is a thousands of a second).
         *
         * We do this for selection sort and then for quickSort. Note
         * that test1 is a copy of test, that is, it is not sorted
         * after sorting test.
         */
        before = System.nanoTime();
        System.out.println(Sorting.isSorted(Sorting.bubbleSort(test)));
        after = System.nanoTime();
        System.out.println((after - before)/1000000 + "ms");
     
        before = System.nanoTime();
        System.out.println(Sorting.isSorted(Sorting.quickSort(test1)));
        after = System.nanoTime();
        System.out.println((after - before)/1000000 + "ms");
    }
}
