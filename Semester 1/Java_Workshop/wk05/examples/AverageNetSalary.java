import java.util.function.Function;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *  A class in which the gross salaries of a group of people are given
 *  in form of an ArrayList and we want to compute the average net
 *  salary after taxes have been applied.
 *  @version 2018-08-20
 *  @author Manfred Kerber
 */
public class AverageNetSalary {

    /**
     *  @param a An ArrayList of gross salaries.
     *  @param tax A function from Double to Double to compute the tax
     *  due for a particular salary.
     *  @return The corresponding average net salary.
     */
    public static Double netAverage(ArrayList<Double> a, Function<Double,Double> tax) {
        double average = 0;
        for (Double gross : a) {
            average += gross - tax.apply(gross);
        }
        return average/a.size();
    }

    /** 
     *  Functions can also be bound to variables such as the following three.
     *  The tax is constant at 20 percent.
     */
    public static Function<Double,Double> tax0 =
        x -> {return 0.2*x;};
    
    /** 
     *  The tax is a step function, up to an income of 30000 it is 20
     *  percent, for higher incomes it is 30 percent.
     */
    public static Function<Double,Double> tax1 =
        x -> {return (x <= 30000) ? 0.2*x : 0.3*x;};
    
    /**
     *  The tax is computed to the current income tax rules of the UK:
     *  up to a threshold of GBP 11850 the income is tax free;
     *  anything above up to 34500 will be taxed at 20%, income above
     *  34500 up to 150000 is taxed at 40%, and income above that at
     *  45%.
     */
    public static Function<Double,Double> tax2 =
        x -> {
        if (x <= 11850){
            return 0.0;
        }
        else if (x <= 34500){
            return 0.2 * (x - 11850.0);
        }
        else if (x <= 150000){
            return 0.4 * (x - 34500.0) + 0.2 * 34500.0;
        }
        else { // x > 150000
            return 0.45 * (x - 150000.0) + 0.4 * (150000.0 - 34500.0) + 0.2 * 34500.0;
        }
    };

            
    public static void main(String[] args) {
        ArrayList<Double> salaries0 =
            new ArrayList<Double>(Arrays.asList(10000.0, 20000.0, 15000.0));
        System.out.println(salaries0);
        System.out.println(netAverage(salaries0,tax0));
        System.out.println(netAverage(salaries0,tax1));
        System.out.println(netAverage(salaries0,tax2));
        ArrayList<Double> salaries1 =
            new ArrayList<Double>(Arrays.asList(10000.0, 30000.0, 40000.0, 2000000.0));
        System.out.println("\n"+salaries1);
        System.out.println(netAverage(salaries1,tax0));
        System.out.println(netAverage(salaries1,tax1));
        System.out.println(netAverage(salaries1,tax2));
        ArrayList<Double> salaries2 =
            new ArrayList<Double>(Arrays.asList(4000000.0, 2000000.0));
        System.out.println("\n"+salaries2);
        System.out.println(netAverage(salaries2,tax0));
        System.out.println(netAverage(salaries2,tax1));
        System.out.println(netAverage(salaries2,tax2));
    }
}
