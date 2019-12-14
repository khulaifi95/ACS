/**
 * 
 * @author Manfrred Kerber
 * @version 2017-11-07
 * 
 * We revisit here the multiplication table from week 3
 * since we want to demonstrate the debugger in Eclipse. 
 *
 */
public class Multiplication {
	/**
	 * 
	 * @param n The size of a quadratic multiplication table
	 * so that the numbers are evaluated up to (n-1)x(n-1).
	 * @return The multiplication table as a two-dimensional array
	 * of size nxn.
	 */
	public static int[][] multiplicationTable(int n) {
	int[][] multi = new int[n][n];
	for (int i=0; i < n; i++){
	    for (int j=0; j< n; j++){
	        multi[i][j] = i*j;
	    }
	}
	return multi;
	}
	
	/**
	 *   Prints out a two-dimensional array (with width up to 3
	 *   digits for a single number).
	 * 
	 * @param a The array to be printed.
	 */
	public static void printArray(int[][] a) {
		for (int[] element : a){
		    for (int el : element){
		        System.out.printf("%4d", el);
		    }
		    System.out.println();
		}
	}
	
	/* 
	 *  A main method to run the program up to 12x12.
	 */
	public static void main(String[] args) {
		printArray(multiplicationTable(13));
	}
	
}
