/**
 *   The class contains a method to reverse the content of an array of
 *   type int[]. No new array is created.
 * 
 *   @author Manfred Kerber
 *   @version 2017-11-16
 */

public class Reverse {

    /**
     *  The method reverses the content of an array of type int[].
     *  @param a An arbitrary array of type int[].
     *  @return The same array with elements reversed, that is, the
     *  last element is swaped with element zero, and so on.
     */
    public static int[] reverse(int[] a) {
        int n = a.length;
        int aux;
        for (int i = 0; i < n/2; i++) {
            aux = a[i];
            a[i] = a[n-i-1];
            a[n-i-1] = aux;
        }
        return a;
    }

    
    /**
     *  A method to print a one-dimensional array of int[].
     *  @param a An arbitrary array of type int[].
     */
    public static void print(int[] a) {
        /* The elements up to index i have been printed. */
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

    /*
     *  Main method for initial test.
     */
    public static void main(String[] args) {
        int[] a = {2, 4, 6, 1, 3, 9, 0, -4};
        int[] b = {4, 6, 1, 3, 9, 0, -4};
        int[] aReversed = reverse(a);
        int[] bReversed = reverse(b);
        print(a);
        print(aReversed);
        print(b);
        print(bReversed);
    }
}
