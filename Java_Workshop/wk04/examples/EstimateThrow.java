/**
 *  The main idea is to compute the relative and absolute error, and then
 *  have a case analysis. The first case is that the nominal quantity is
 *  less than 5ml or more than 10000ml. In these cases the nominal
 *  quantity is not in the range of the definition of \u212E.  Else the
 *  program checks whether for a nominal amount in a particular case the
 *  corresponding maximal negative absolute or relative error is within
 *  the bounds. If it is a corresponding positive answer is given. If not,
 *  a corresponding negative answer is given.
 *
 *  Unlike in the previous version, we properly take care of the
 *  situation now when a nominal value is outside the domain of the
 *  definition, that is, when the nominal value is below 5ml and above
 *  10000ml an exception is thrown.
 *
 *  Note that the main method expects two arguments, the nominal and the actual.
 *
 *  @author Manfred Kerber
 *  @version 2019-10-20
 */
public class EstimateThrow {

    /**
     *  The method checks whether the actual value is within the error
     *  bounds given by the estimates definition. For nominal values
     *  below 5 and above 10000 an IllegalArgumentException is thrown.
     *
     *  @param actual The actual content of a container.
     *  @param nominal The nominal content of a container.
     *  @return true if the actual content is within the error bounds.
     *  @exception An IllegalArgumentException is thrown when the
     *  nominal amount is below 5 or above 10000.
     */
    public static boolean estimateInBounds(double actual, double nominal) {
        double absShortFall = nominal - actual;
        double relShortFall = absShortFall / nominal;
        if (nominal < 5 || nominal > 10000) {
            throw new IllegalArgumentException("\"nominal\" is outside range.");
        } else {
            return
                ((5 <= nominal    && nominal <= 50 && relShortFall <= 0.09)||
                (50 < nominal   && nominal <= 100 && absShortFall <= 4.5)||
                (100 < nominal  && nominal <= 200 && relShortFall <= 0.045)||
                (200 < nominal  && nominal <= 300 && absShortFall <= 9)||
                (300 < nominal  && nominal <= 500 && relShortFall <= 0.03)||
                (500 < nominal  && nominal <= 1000 && absShortFall <= 15)||
                (1000 < nominal && nominal <= 10000 && relShortFall <= 0.015));
        }
    }
    /*
     *  Two integer values are expected as input.
     */
    public static void main(String[] args) {
        double actual = Double.parseDouble(args[0]); 
        double nominal = Double.parseDouble(args[1]);
        System.out.println(estimateInBounds(actual,nominal));
    }
}
