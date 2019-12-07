/**
 *   The class is used displays the lengths of Strings in
 *   form of dots (stars).
 * 
 *   @author Manfred Kerber
 *   @version 2017-11-16
 */


public class Dots {

    /**
     *  The method writes the lengths of the strings in an array of
     *  type String[] into an array of type int[].
     *  @param strings An arbitrary array of type String[].
     *  @return An array of the same length with the lengths of the
     *  Strings in the original array as values.
     */
    public static int[] lengths(String[] strings) {
        int[] result = new int[strings.length];
        /* We iterate through the input array and set all the values
         * in the result array 
         */
        for (int i = 0; i < strings.length; i++) {
            result[i] = strings[i].length();
        }
        return result;
    }

    /**
     *  The method computes the maximal element in an array of type
     *  int[].
     *  @param a An arbitrary array of type int[].
     *  @return The maximum of all the elements in the array if it
     *  exists (otherwise an IllegalArgumentException is thrown).
     */
    public static int max(int[] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException();
        } else {
            int l = a.length;
            int result = a[0];
            // result stores the smallest value seen so far.
            for (int i = 1; i < l; i++) {
                if (result < a[i]) {
                    result = a[i];
                }
            }
            return result;
        }
    }
    
    /**
     *  The method prints the lengths of strings in an array bottom-up.
     *  @param strings An arbitrary array of type String[].
     */
    public static void printLengths(String[] strings) {
        printLengths(lengths(strings));
    }

    /**
     *  The method prints as many stars bottom-up as the int[] specifies.
     *  @param ints An arbitrary array of type int[].
     */
    public static void printLengths(int[] ints) {
        /* 
         *  The outer loop determines line by line to print stars
         *  (top-down). That is after going one time through the loop
         *  all those stars are printed that correspond to the maximal
         *  value in ints. In the next round the second highest as
         *  well and so on until one star.
         */
        for (int i = max(ints); i > 0; i--) {
            /*
             *  In each row we print a star if in the corresponding
             *  column there should be one (i.e., if ints[j] is
             *  greater than or equal to i) and an empty space else.
             */
            for (int j = 0; j < ints.length; j++) {
                if (ints[j] >= i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            // When we have reached the end of a row, we start a new one.
            System.out.println();
        }
    }

    /*
     *  Main method for initial test.
     */
    public static void main(String[] args) {
        String[] s1 = {"The", "cat", "sat", "on", "the", "mat"};
        printLengths(s1);
        System.out.println("------------------------");

        String[] s2 = {"All", "human", "beings", "are", "born", "free",
                       "and", "equal", "in", "dignity", "and", "rights",
                       "They", "are", "endowed", "with", "reason", "and",
                       "conscience", "and", "should", "act", "towards",
                       "one", "another", "in", "a", "spirit", "of",
                       "brotherhood", "Everyone", "is", "entitled", "to",
                       "all", "the", "rights", "and", "freedoms", "set",
                       "forth", "in", "this", "Declaration", "without",
                       "distinction", "of", "any", "kind", "such", "as",
                       "race", "colour", "sex", "language", "religion",
                       "political", "or", "other", "opinion", "national",
                       "or", "social", "origin", "property", "birth",
                       "or", "other", "status"};

        printLengths(s2);
        System.out.println("------------------------");
        int[] i = {3, 4, 1, 6, 9, 2, 6, 5};
        printLengths(i);
    }
}
