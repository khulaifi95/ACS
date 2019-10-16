/**
 *
 * In a while loop we distinguish the condition (included in round
 * brackets) and the body of the loop (included in curly brackets). If
 * the condition is true the body is executed repeatedly until the
 * condition is false after executing the body in full. 
 * 
 * Loops are difficult for several reasons. Firstly, it may be difficult
 * to understand what they do, secondly, loops are a source of potential
 * non-termination.
 * 
 * In order to understand non-trivial loops we need to give a so-called
 * invariant. An invariant is a boolean expression which is true before
 * the loop is entered and is true again after each execution of the body
 * in full. That is, it is also true if and when the loop terminates.
 * 
 * In order to guarantee the termination of a loop we typically consider
 * an integer expression which is firstly bounded (from below or above)
 * and secondly which is either decreased or increased (resp.) by at
 * least 1 by each execution of the loop.
 * 
 * Careful, better test for "less than" or "greater than" than for
 * "equal". Take also care that if you take a bound from below (above)
 * that the expression is actually decreasing (increasing, resp.).
 *
 * @version 2015-10-13
 * @author Manfred Kerber
 * 
 */
public class WhileMain{ 

    /* 
     * Example of a while loop which prints numbers from 1 to 7.
     * 
     * INVARIANT:   only trivial invariant: true 
     * TERMINATION: the variable i is initialized to 0 and increased in
     *  each loop by 1 and will eventually be bigger than the upper bound 7
     *
     */
    public static void main(String[] args){
	int i = 0 ;
	while (i <  7) {
            // i = i+1; 
            System.out.print(i + " ");
        }
	System.out.println();
    }
}
