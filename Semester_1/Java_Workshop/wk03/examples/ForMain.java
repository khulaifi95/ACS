/**
 * A "for" loop is similar to a "while" loop, however, in the round brackets
 * we declare and initialize an iteration variable, separate by a semicolon the
 * termination condition and again by a semicolon the update expression for the
 * iteration variable.
 * 
 * Again in order to understand non-trivial for loops we need to give an
 * invariant, that is, a boolean expression which is true before the loop
 * is entered and is true again after each execution of the body in
 * full. That is, it is also true if and when the loop terminates.
 * 
 * In order to guarantee the termination of a for loop we consider the
 * iteration variable and make sure that it is firstly bounded (from
 * below or above) and secondly that it is either decreased or increased
 * (resp.) by at least 1 by each execution of the loop.
 * 
 * Careful, better test for less equal or greater equal than for == or
 * !=. Take also care that if you take a bound from below (above) that
 * the expression is actually decreasing (increasing, resp.).
 * 
 * @version 2015-10-13
 * @author Manfred Kerber
 */


/* 
 *
 * Example of a for loop which prints numbers from 0 to 7.
 *
 * INVARIANT:   only trivial invariant: true 
 * TERMINATION: the variable i is initialized to 0 and increased in
 *  each loop by 1 and will eventually be bigger than the upper bound 7
 *
 */

public class ForMain{ 
    public static void main(String[] args){

	int n = 7;
	
	System.out.println("for (int i = 0 ; i <=n ; i++)");
	for (int i = 1 ; i <=n ; i++){
		System.out.print(i + " ");
	    }
	System.out.println();
    }
}


