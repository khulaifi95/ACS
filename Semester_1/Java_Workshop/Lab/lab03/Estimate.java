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
 *  @version 2015-10-15
 */

public class Estimate {
    /**
     *  @param actual The actual amount of liquid in the container.
     *  @param nominal The amount of liquid that should be in the container.
     *  @return true if the the actual amount of liquid is within the
     *  definition of estimate at least approximately the nominal
     *  amount, false else.
     *  
     *  Note this method is a stub which always returns true. A proper
     *  implementation is still missing.
     */
    public static boolean estimateInBounds(double actual, double nominal) {
    	double absShortFall = nominal - actual;
    	double relShortFall = absShortFall / nominal;
        return true;
  }
}
