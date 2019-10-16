/**
 *  Some types can be converted into each other. This is done if we
 *  need the variable with a different type. For instance, if we want
 *  to list characters in a certain range we can convert the first
 *  into an integer, add one and revert it back.
 *
 *  This conversion is called `casting' and done by writing the result type
 *  in brackets before the term, e.g., (byte) 4.
 *
 *  If we write an integer down and don't give its type it is assumed
 *  to be of type int. Likewise a floating point number is assumed to
 *  be of type double.
 *
 *  More examples are given in the following in this file.
 * 
 *  @author  Manfred Kerber
 *  @version 2014-09-29
 */

public class TypeCasting { 
    public static void main(String[] args) {
	byte b;
	short s;
	int i;
	long l;

	float f;
	double d;

	char c1, c2;
 	boolean bool;

	b = (byte) 4;
	s = b;
	i = s;
	l = i;

	System.out.println("b: " + b + ". s: " + s + ". i: " + i + ". l: " + l + ".");
	System.out.println("**************************************************");

	l = Long.MAX_VALUE / 48;
	i = (int) l;
	s = (short) i;
	b = (byte) s;

	System.out.println("b: " + b + ". s: " + s + ". i: " + i + ". l: " + l + ".");
	System.out.println("**************************************************");

	// The following does not work.
	//        l = 5;
	//        i = l;

	f = (float) i;
	System.out.println("f: " + f + ". i: " + i + ".");

	d = 123.856778;
	i = (int) d;
	System.out.println("d: " + d + ". i: " + i + ".");
	System.out.println("**************************************************");

	i = 123;
	c1 = (char) i;
	System.out.println("i: " + i + ". c1: " + c1 + ".");
	System.out.println("**************************************************");

	c1 = 'A';
	i = (int) c1;
        c2 = (char) (i+1);
	System.out.println("i: " + i + ". c1: " + c1 + ".");
	System.out.println("i: " + i + ". c2: " + c2 + ".");
	System.out.println("**************************************************");
    }
}
