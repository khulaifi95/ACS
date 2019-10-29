import java.util.function.Function;

/**
 *  A simple class to demonstrate how to define and use functions
 *  (also called lambda expressions)
 *  @version 2017-10-24
 *  @author Manfred Kerber
 */
public class FunMain {

    /**
     *  @param f A function from int to int for which the values
     *  between 0 and n are to be computed and printed.
     *  @param n The maximal value to be printed.
     */
    public static void printN(Function<Integer,Integer> f, int n) {
        for (int i = 0; i <= n; i++){
            System.out.printf("%3d ", f.apply(i));
        }
        System.out.println();
    }

    /** 
     *  Functions can also be bound to variables such as in this example.
     */
    public static final Function<Integer,Integer> f0 =
        x -> {return x * x + x - 7;};
    public static void main(String[] args) {
        System.out.print("x -> {return x;}             ");
        printN(x -> {return x;}, 10);
        System.out.print("x -> {return x * x;}         ");
        printN(x -> {return x * x;}, 10);
        System.out.print("x -> {return x + 1;}         ");
        printN(x -> {return x + 1;}, 10);
        System.out.print("x -> {return x * x + x - 7;} ");
        printN(f0, 10);
    }
}
