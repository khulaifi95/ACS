/** 
 *  The class tests the value of 1+2*3. The result is 7 since * binds
 *  more strongly than +. If we want to compute the triple of the sum
 *  of 1 and 2, we need to use explicit brackets.
 *  @version 2015-09-30
 *  @author Manfred Kerber
 */

public class SumTimes {

    public static void main(String[] args) {
        System.out.println(1+2*3);
        System.out.println((1+2)*3);
    }
}
