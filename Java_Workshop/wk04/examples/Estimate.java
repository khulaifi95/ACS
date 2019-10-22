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
 *  @author Manfred Kerber
 *  @version started 2014-10-06, last changed 2019-10-18 by Alexandros Evangelidis
 */

public class Estimate { 
    /**
     *  The estimateInBounds method determines whether the actual
     *  amount of liquid in a bottle is at least the nominal amount
     *  minus the bounds given by the Estimate definition.
     *  @param actual The actual amount of liquid in a bottle measured
     *  in millilitres (ml).
     *  @param nominal The nominal amount (that is, the amount as
     *  indicated on the label of the bottle) of liquid in a bottle
     *  measured in millilitres (ml).
     *  @return true if the the actual amount of liquid in the bottle
     *  is at least the nominal amount minus the bounds given by the
     *  Estimate definition.
     */
    public static boolean estimateInBounds(double actual, double nominal) {
    	double absShortFall = nominal - actual;
    	double relShortFall = absShortFall / nominal;
    	return   absShortFall <= 0 || nominal < 5 || nominal > 10000      ||
            (5 <= nominal    && nominal <= 50    && relShortFall <= 0.09) ||
            (50 < nominal   && nominal <= 100   && absShortFall <= 4.5)   ||
            (100 < nominal  && nominal <= 200   && relShortFall <= 0.045) ||
            (200 < nominal  && nominal <= 300   && absShortFall <= 9)     ||
            (300 < nominal  && nominal <= 500   && relShortFall <= 0.03)  ||
            (500 < nominal  && nominal <= 1000  && absShortFall <= 15)    ||
            (1000 < nominal && nominal <= 10000 && relShortFall <= 0.015);
    }
}
