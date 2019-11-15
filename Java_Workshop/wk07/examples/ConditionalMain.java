/** With this method we demonstrate the use of the conditional construct as in
 *  var = condition ? term1 : term2;
 *  @version 2018-08-21
 *  @author Manfred Kerber
 */
public class ConditionalMain {

    /**
     *  A method for printing out a one-dimensional array of int.
     *  @param a The array to be printed.
     */
    public static void print(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length -1; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println(a[a.length-1] + "]");
    }

    /* 
     *  A main method to test the conditional operator.
     */
    public static void main(String[] args) {
        int[] arr = {5, -3, 0, 7, -12, -11, -20, 4};
        int[] result = new int[arr.length];
        int[] result1 = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) {
                result[i] = arr[i] ;
            } else {
                result[i] = -arr[i];
            }
        }

        // A more concise way of doing the initialization using the conditional.
        for (int i = 0; i < arr.length; i++) {
            result1[i] = arr[i] >=0 ? arr[i] : -arr[i];
        }

        print(arr);
        print(result);
        print(result1);
    }
}
