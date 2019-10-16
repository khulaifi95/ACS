/** INVARIANT: gcd(a,b) == gcd(x,y)
 *
 *  TERMINATION: either x or y is decreased in each run of the loop, x and
 *  y will always be positive (assumed we start with positive a and b).
 *
 *  RESULT: if x = y then gcd(a,b) == gcd(x+y/2,x+y/2) == x
 *
 * // Example adapted from a lecture by Dijkstra //
 * @author Manfred Kerber
 * @version 2013-10-22
 */
public class GcdLcmMain {
    //    /*
    public static void main(String[] args) {
	int a,b,x,y;
        // Integer.parseInt transforms an integer into a string,
        // e.g. "123" --> 123.
	a = Integer.parseInt(args[0]); 
	b = Integer.parseInt(args[1]);
	x = Math.abs(a); 
	y = Math.abs(b);
	while (x>y | y>x) {
	    // System.out.println("x: " + x + " y: " + y);
	    if (x>y){
		x = x - y;
	    }
	    else if (y>x) {
		y = y - x;
	    }
	}
	System.out.println("gcd("+a+","+b+")=="+(x+y)/2);
    }
}

//*/


/*

  INVARIANT: gcd(a,b) == gcd(x,y) && 2*a*b == x * v + y * u
  TERMINATION: either x or y is decreased in each run of the loop, x and
           y will always be positive (assumed we start with positive a and b).

  RESULT: if x = y then gcd(a,b) == (x+y)/2 == x == y.
          Hence (u+v)/2 == a*b/gcd(a,b), 
          hence (u+v)/2 == lcm(a,b).

	  // Example adapted from a lecture by Dijkstra //
*/

/*
    public static void main(String[] args) {
	int a,b,x,y,u,v;
        // Integer.parseInt transforms an integer into a string,
        // e.g. "123" --> 123.
	a = Integer.parseInt(args[0]); 
	b = Integer.parseInt(args[1]);
        // Math.abs is used in order to allow negative arguments as well.
	x = Math.abs(a); y = Math.abs(b);
	u = Math.abs(a); v = Math.abs(b);

	while (x>y | y>x) {
	    System.out.println("x: "+x+" y: "+y+" u: "+u+" v: "+v+
			       " x*v+y*u: "+(x*v+y*u));
		
		if (x>y) {
		    x = x - y; u = u + v;
		}
		else if (y>x) {
		    y = y - x; v = v + u; 
		}
	}
	System.out.println("x: "+x+" y: "+y+" u: "+u+" v: "+v+
                           " x*v+y*u: "+(x*v+y*u));
	System.out.println("gcd("+a+","+b+")=="+(x+y)/2);
	System.out.println("lcm("+a+","+b+")=="+(u+v)/2);
    }
}

*/
