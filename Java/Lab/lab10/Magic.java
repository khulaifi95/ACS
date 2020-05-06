import java.util.*;

/**
 *  This class deal with magic squares. A magic square of size n
 *  should contain all numbers between 1 and n*n exacly once so that
 *  each row, each column, and each diagonal add up to the same
 *  number. (see Horstmann, Big Java, 4th edition p. 299.).
 *  E.g.
 *  <pre>
 *  {{16,  3,  2, 13},
 *   { 5, 10, 11,  8},
 *   { 9,  6,  7, 12},
 *   { 4, 15, 14,  1}}
 *  </pre>
 *  is a magic square of size 4.
 *
 *  The class contains a constructor, a static method readMagic to
 *  read in a magic square - first reading the size, then the numbers
 *  one by one and a method check that checks whether the conditions
 *  are satisfied.
 *  
 *  @version 2018-11-20
 *  @author Manfred Kerber
 */
public class Magic {

    /**
     *  The size of the magic square.
     */
    private int size;
    /**
     *  The matrix representing the magic square
     */
    private int[][] square;

    /** 
     *  Standard constructor
     *  @param size The size of the magic square.
     *  @param square The matrix representing the magic square.
     */
    public Magic(int size, int[][] square) {
        this.size = size;
        this.square = square;
    }

    /**
     *  getter for the square
     *  @return The two-dimensional matrix representing the square. 
     */
    public int[][] getSquare() {
        return square;
    }
    
   /**
     *  A method to create a String to print a one-dimensional array of int[].
     *  @param a An arbitrary array of type int[].
     *  @return A String that can be printed to display the array.
     */
    public static String toString(int[] a) {
        String result = "";
        /* The elements up to index i have been added to the result. */
        for (int i = 0; i < a.length; i++) {
            result += (a[i] + "  ");
        }
        return result;
    }

    /**
     *  A method to create a String to print a two-dimensional array of int[][].
     *  @param a An arbitrary array of type int[][].
     *  @return A String that can be printed to display the array.
     */
    public static String toString(int[][] a) {
        String result = "";
        /* The rows up to index i have been added to the result. */
        for (int i = 0; i < a.length; i++) {
            result += toString(a[i]) + "\n";
        }
        return result;
    }

    /**
     *  Standard toString method for a magic square
     *  @return A string to display a magic square in a human readable
     *  form.
     */
    public String toString() {
        return toString(this.getSquare());
    }

    /**
     *  A method that reads in first the size n and then the n*n many
     *  elements of a magic square one by one.
     *  @return The magic square that was just read in.
     */
    public static Magic readMagic(){
        // Read elements via a scanner.
        Scanner s = new Scanner(System.in);
        // The elements read should be integers
        try {int size = Integer.parseInt(s.next());
            // create a matrix of corresponding size
            int[][] square = new int[size][size];
            // read elements one by one (one line after the other).
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    square[i][j] = Integer.parseInt(s.next());
                }
            }
            // return the corresponding magic square.
            return new Magic(size, square);
        }
        /*
         *  If no magic square is read, one with one element, 1, is returned.
         */
        catch (IllegalArgumentException e) {
            System.out.println("Oops");
            int[][] square = {{1}};
            return new Magic(1, square);
        }
    }
    
    /**
     *  Checks whether a magic square structure actually forms a magic
     *  square.  It would also be possible to not generate the object
     *  if the condition was not fulfilled.
     *
     *  @return true if the structure is a magic square, false else.
     */
    public boolean check() {
        int[][] square = getSquare();
        // emtpy squares satisfy the conditions.
        if (square.length == 0) {
            return true;
        } else {
            // The reference sum can be computed as the sum of the first row.
            /*int sum = 0;
            for (int i = 0; i < square.length; i++) {
                sum += square[0][i];
            }
            */
            // Alternatively, it can be computed as
            int sum = size * (size * size + 1) / 2;
            // Each row must sum up to sum
            return checkRows(square, sum) &&
                // Each column must sum up to sum
                checkColumns(square, sum) &&
                // Each diagonal must sum up to sum
                checkDiagonals(square, sum) &&
                // The elements must be unique and without gaps.
                checkUnique(square);
        }
    }

    /**
     *  Checks whether each row adds up to the given sum
     *
     *  @param square A matrix to be checked.
     *  @param sum Each row must add up to the given sum.
     *  @return true if each row adds up to sum, false else.
     */
    public static boolean checkRows(int[][] square, int sum) {
        int aux;
        for (int i = 0; i < square.length; i++) {
            aux = 0;
            for (int j = 0; j < square.length; j++) {
                aux += square[i][j];
            }
            /* if a sum (represented in aux) does not add up to sum
             * return false (after printing the row number).
             */
            if (aux != sum) {
                System.out.println("Wrong row " + (i+1));
                return false;
            }
        }
        // return true if there is no counterexample.
        return true;
    }

    /**
     *  Checks whether each column adds up to the given sum
     *
     *  @param square A matrix to be checked.
     *  @param sum Each column must add up to the given sum.
     *  @return true if each column adds up to sum, false else.
     */
    public static boolean checkColumns(int[][] square, int sum) {
        int aux;
        for (int j = 0; j < square.length; j++) {
            aux = 0;
            for (int i = 0; i < square.length; i++) {
                aux += square[i][j];
            }
            /* if a sum (represented in aux) does not add up to sum
             * return false (after printing the column number).
             */
            if (aux != sum) {
                System.out.println("Wrong column " + (j+1));
                return false;
            }
        }
        // return true if there is no counterexample.
        return true;
    }

    /**
     *  Checks whether each diagonal adds up to the given sum
     *
     *  @param square A matrix to be checked.
     *  @param sum Each diagonal must add up to the given sum.
     *  @return true if each column adds up to sum, false else.
     */
    public static boolean checkDiagonals(int[][] square, int sum) {
        // check primary diagonal
        int aux = 0;
        for (int i = 0; i < square.length; i++) {
            aux += square[i][i];
            }
        if (aux != sum) {
            System.out.println("Wrong primary diagonal.");
            return false;
        }
        // check secondary diagonal
        aux = 0;
        for (int i = 0; i < square.length; i++) {
            aux += square[i][square.length - 1 - i];
            }
        if (aux != sum) {
            System.out.println("Wrong secondary diagonal.");
            return false;
        }
        return true;
    }

    /**
     *  Checks whether the number 1, 2, 3, 4, ...., n*n-1, n*n all
     *  occur.  This is done by creating an array of type boolean
     *  called check which is initially false. Whenever a number is
     *  found it is set to true. If all its elements are then true the
     *  check passes, else a counterexample is printed and false is
     *  returned.
     *
     *  @param square A two-dimensional matrix to checked.
     *  @return true If all elements 1,2, 3, 4, ...., n*n-1, n*n
     *  occur. false else.
     */
    public static boolean checkUnique(int[][] square) {
        int length = square.length;
        boolean[] check = new boolean[length * length];
        /*
         *  Iterate over the whole array square and enter each number
         *  in the check array (mapped into the range 0,1,2,3, ...,
         *  n*n-2, n*n-1).
         *  If a number is read that is out of scope, false is returned.
         */
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (0 < square[i][j] && square[i][j] <= length * length) {
                    check[square[i][j]-1] = true;
                } else {
                    return false;
                }
                    
            }
        }
        /*
         *  If a single element in the range was not read, this is
         *  printed and false is returned.
         */
        for (int i = 0; i < length*length; i++) {
            if (!check[i]) {
                System.out.println("Element " + (i+1) + " missing");
                return false;
            }
        }
        // If no counterexample is found, return true.
        return true;
    }
            
    /*
     *  main method to call the readMagic method as well as the check
     *  method.
     */
    public static void main(String[] args) {
        Magic m = readMagic();
        System.out.println(m);
        System.out.println(m.check());
        // System.out.println(checkUnique(m.getSquare()));
    }
    
}
