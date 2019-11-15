/**
 * 
 * @author Alex Evangelidis and Manfred Kerber
 * @version 2017-10-25 with changes 2019-11-12
 *
 * The class contains a stub for implementations of two sorting algorithms,
 * bubbleSort and quickSort. Furthermore we have a stub for a method to check
 * whether a given array of type double[] is sorted.
 *
 */
public class Sorting {
    /**
     *  @param a An array for which it is to be checked whether it is sorted.
     *  @return true if and only if the array sorted in increasing order.
     */
    public static boolean isSorted(double[] a) {
        return false; // to be written.
    }

    /**
     *  @param numbers The array takes an (unsorted) array of double.
     *  @return The same numbers, but in ascending order.
     */
    public static double[] bubbleSort(double[] numbers) {
        double tmp;
        int size = numbers.length;
        /* The loop invariant for the outer loop is that the first i
         * elements will be in the correct order. Initially that is
         * the element at position 0 will be in the correct order,
         * then the one in position 1 as well, then the element in
         * position 2 as well, and so on, that is, in each round one
         * more element will be in the right order from the left.
         * Furthermore, the array will always contain the same
         * elements.
         */
        //      for (int i=???; i<???; i++){
            /* The loop invariant for the inner loop is that the
             * smallest element between i and the end of the array can
             * be found among the elements between i and j.  If the
             * smallest element is toward the right of the array it
             * will be moved forward. As a consequence the smallest
             * element will be in position i after the loop
             * terminates.
             */
        //          for (int j = ???; ? >= ?; ???){
        //              if (???){
                    // swap
        //      }
        //  }
        //
        return numbers;
    }

    /**
     * Helper method to call quickSort.
     * @param a The array to be sorted
     * @return The sorted array.
     */
    public static double[] quickSort(double[] a) {
        if (a.length == 0) {
            return a;
        }
        return quickSort(a, 0, a.length - 1);
    }

    /**
     *   @param a The array to be sorted in a range.
     *   @param start The smallest index in the range of the array to be sorted.
     *   @param end The largest index in the range of the array to be sorted.
     *   @return The value of the pivot.
     */
    public static double determinePivot(double[] a, int start, int end) {
        // first get the pivot index (middle index of the array)
        int pivot_index = start + (end - start) / 2;

        // Use pivot to store the middle element of the array
        return a[pivot_index];
    }

    /**
     *   Performs in-place quicksort over the provided array,
     *   @param a The array to be sorted in a range.
     *   @param start The smallest index in the range of the array to be sorted.
     *   @param end The largest index in the range of the array to be sorted.
     *   @return The array in which the specified range is sorted.
     */
    public static double[] quickSort(double[] a, int start, int end) {
        double temp;
        int i = start;
        int j = end;

        double pivot = determinePivot(a, start, end);
        
        // As long as i and j do not cross
        while (i <= j) {

            // Scan the array from left to right as long as the
            // value is less than the pivot
            while (a[i] < pivot) {
                i++;
            }   

            // Scan the array from right to left as long as
            // the value is greater than the pivot
            while (a[j] > pivot) {
                j--;
            }

            // In the case where the ith element
            // is greater than the pivot
            // or in the case where the jth element
            // is less than the pivot
            // Swap i with j and increment and decrement
            // i and j, respectively.

            if (i <= j) {
                //    ....
            }
        }

        // At this point the partitioning phase
        // has been completed because i and j cross
        // Now call the same method recursively
        // for the two sub arrays("left" and "right")
        if (start < j) {
            // quickSort(....);
        }
        if (i < end) {
            // quickSort(....);
        }
        return a;
    }
}
