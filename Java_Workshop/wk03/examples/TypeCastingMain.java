/**
 * In this class we demonstrate some type conversions.
 * Some examples are given in the following. 
 * Some types can be converted into each other.  
 * @author Manfred Kerber
 * @version 2019-10-14
 */
public class TypeCastingMain { 

    public static void main(String[] args){
	byte b;
	short s;
	int i;
	long l;

	float f;
	double d;

	char c;

 	boolean bool;

	b = 4;
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
	c = (char) i;
	System.out.println("i: " + i + ". c: " + c + ".");
	System.out.println("**************************************************");

	c = 'A';
	i = (int) c;
	System.out.println("i: " + i + ". c: " + c + ".");
	System.out.println("**************************************************");
    }
}
