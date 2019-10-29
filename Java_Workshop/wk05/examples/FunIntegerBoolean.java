import java.util.function.Function;

/**
 *  Another class to demonstrate how to define and use functions
 *  (also called lambda expressions)
 *  @version 2019-10-28
 *  @author Manfred Kerber
 */
public class FunIntegerBoolean {

    /**
     *  @param f A function from Integer to Boolean for which all
     *  those are those smaller than or equal to n are printed for
     *  which the function is true.
     *  @param n The maximal value to be printed.
     */
    public static void printN(Function<Integer,Boolean> f, int n) {
        for (int i = 0; i <= n; i++){
            if (f.apply(i)) {
                System.out.printf("%3d ", i);
            }
        }
        System.out.println();
    }

    /** 
     *  Functions can also be bound to variables such as in this
     *  example. The function primeNumber returns true if and only if
     *  the number is divisble only by 1 and itself.
     */
    public static final Function<Integer,Boolean>
        primeNumber = x -> {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    };

    /**
     *  Standard main method.
     */
    public static void main(String[] args) {
        System.out.print("Even numbers:   ");
        printN(x -> {return x % 2 == 0;}, 20);
        
        System.out.print("Odd numbers:    ");
        printN(x -> {return x % 2 == 1;}, 20);

        System.out.print("Prime numbers:  ");
        printN(x -> {return primeNumber.apply(x);}, 100000);
    }
}
