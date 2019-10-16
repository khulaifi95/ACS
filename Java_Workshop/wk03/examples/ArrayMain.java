/**
 * In this file introduce examples of 1-dimensional and 2-dimensional
 * arrays, how to initialize them and how to iterate over their
 * elements.
 * @author Manfred Kerber
 * @version 2015-10-13
 */

public class ArrayMain{
    /**
     *  @param a A one-dimensional array to be printed.
     *  Using standard for loop.
     */
    public static void print(int[] a){
	for (int i=0; i < a.length; i++){ //i.e. in the example from 0..14
	    System.out.print(a[i] + " ");
	}
        System.out.println();
    }

    /**
     *  @param a A one-dimensional array to be printed. 
     *  Using for loop over array.
     */
    public static void printAlternative(int[] a){
	for (int element : a) {
	    System.out.print(element + " ");
	}
	System.out.println();
    }


    /**
     *  @param a A two-dimensional array to be printed. 
     *  Using two standard for loops.
     */
    public static void print(int[][] a){
    	for (int i=0; i<a.length; i++) {
	    for (int j=0; j<a[0].length; j++){
		System.out.printf("%4d ",a[i][j]);
	    }
	    System.out.println();
	}
	System.out.println();
    }
    
    /**
     *  @param a A two-dimensional array to be printed. 
     *  Using two for loop over array.
     */
    public static void printAlternative(int[][] a){
	for (int[]  element : a){
	    for (int el : element){
		System.out.printf("%4d ",el);
	    }
	    System.out.println();
	}
	System.out.println("\n");
    }
    
    public static void main(String[] args) {

	int length = 15;
	int[] a = new int[length];
	System.out.println("Initial values:");
        print(a);

	System.out.println("Square number values, standard for-loop:");
	for (int i=0; i < length; i++){
	    a[i] = i*i;
	}
        print(a);
        printAlternative(a);

	System.out.println("Initialization for a one-dimensional array");
	int [] b = {2, 5, 24, 6};

	print(b);


////////////////////////////////////////////////////////////////////////////////

	System.out.println("Initialization for a two-dimensional array");
	int [][] d = {{2,   3,  4}, 
                      {5,   6,  7}, 
                      {24, 25, 26}, 
	              {6,   7,  8}};
	System.out.println("standard for-loops:");
        print(d);
	System.out.println("simplified for-loops:");
        printAlternative(d);


        length = 20;
        int width  = 20;
	System.out.println("Multiplication Table");
	int[][] multi = new int[length][width];
	for (int i=0; i < length; i++){
	    for (int j=0; j< width; j++){
		multi[i][j] = i*j;
	    }
	}
        print(multi);
    }
}
