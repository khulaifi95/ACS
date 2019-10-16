/**
 * Write a loop that sums up the first n number of type int, that is,
 *   that computes 0+1+2+3+4+ ...+ (n-1)+n. Test this for n = 100.
 * @author Manfred Kerber
 * @version 2019-10-14
 */

public class ForWhileMain{ 
    public static void main(String[] args)
    {

	// A for loop
	int n = 100;
	int sum = 0;

	for (int i = 0 ; i <=n ; i++){
            sum = sum+i;
        }
	System.out.println("The sum 0+1+2+ ... +" + n + " is equal to " + sum + ".");
    

	// A while loop
        sum = 0;
        int i=0;
        while (i<=n){
	    sum = sum+i;
	    i= i+1;
	}
        System.out.println("The sum 0+1+2+ ... +" + n + " is equal to " + sum + ".");
    }
}


