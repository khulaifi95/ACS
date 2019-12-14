/** <pre>
 *  In this file we look at the eight different primitive data types in Java. 
 *  We distinguish for the representation of integers 1, 2, 4, and 8
 *  byte representations
 *
 *  byte  for integers in the range -128 to 127
 *
 *  short for integers in the range -32,768 to 32,767
 *
 *  int   for integers in the range -2,147,483,648 to  2,147,483,647
 *
 *  long  for integers in the range -9,223,372,036,854,775,808 to
 *                                   9,223,372,036,854,775,807
 *
 *  Types are a powerful tool for avoiding errors in programming. Java is
 *  a strictly typed language. That is, each variable must be typed by a
 *  type declaration. Any misspelling of variables, any use of undeclared
 *  variables will be detected by the compiler before the program is run.
 *
 *  Type declarations work in the way:
 *  |type| |variable|;
 *  |type| |variable| = |initial_value|;
 *
 *  or in general
 *  |type| |variable|{,|variable|}*;
 *  |type| |variable| = |initial_value|{,|variable| = |initial_value|}*;
 *
 *
 *  The first we see is 
 *  byte b; 
 *  That is, b is declared as a variable of type byte. However, it has not
 *  yet a value. We can also give a variable an initial value as in
 *
 *  byte b = 0;
 *
 *  or declare several variables at the same time
 *
 *  byte b1,b2,b3;
 *
 *  or declare and initialize several variables at the same time
 *  byte b1 = 0, b2 = 3, b3 = -7;
 *
 *  Likewise we declare variables of integer types short, int, and long.
 *
 *  Representation of float and double:
 *  float (floating point numbers, i.e.numbers with fractional part),
 *        are represented by 4 bytes, 6-7 significant decimal digits
 *        3.4028235E38
 *  double correspondingly by 8 bytes
 *
 *  The type boolean contains two elements true and false.
 *  boolean values can be connected by two ampersands, two |, and !.
 *
 *  The type char is used to represent characters such as 'a', 'b', 'c', and
 *  '\u00A3' (for the pound symbol). They are enclosed in single quotes.
 *  </pre>
 * 
 *  @author  Manfred Kerber
 *  @version 2014-09-29
 */

public class Types { 
    public static void main(String[] args) {

	byte b1,b2,b3;
	short s1,s2,s3;
	int i1,i2,i3;
	long l1,l2,l3;

	float f1,f2,f3,f4,f5;
	double d1,d2,d3,d4,d5;

	char c1,c2,c3;

 	boolean bool1,bool2,bool3;
	
	b1 = Byte.MAX_VALUE;
	System.out.println("Byte.MAX_VALUE: " + b1);
	b2 = Byte.MIN_VALUE;
	System.out.println("Byte.MIN_VALUE: " + b2);
	b3 = (byte) (b1 + 1);
	System.out.println("Byte.MAX_VALUE + 1: " + b3);
	System.out.println("**************************************************");

	s1 = Short.MAX_VALUE;
	System.out.println("Short.MAX_VALUE: " + s1);
	s2 = Short.MIN_VALUE;
	System.out.println("Short.MIN_VALUE: " + s2);
	s3 = (short) (s1 + 1);
	System.out.println("Short.MAX_VALUE + 1: " + s3);
	System.out.println("**************************************************");

	i1 = Integer.MAX_VALUE;
	System.out.println("Integer.MAX_VALUE: " + i1);
	i2 = Integer.MIN_VALUE;
	System.out.println("Integer.MIN_VALUE: " + i2);
	i3 = i1 + 1;
	//	i3 = (integer) (i1 + 1);
	System.out.println("Integer.MAX_VALUE + 1: " + i3);
	System.out.println("**************************************************");

	l1 = Long.MAX_VALUE;
	System.out.println("Long.MAX_VALUE: " + l1);
	l2 = Long.MIN_VALUE;
	System.out.println("Long.MIN_VALUE: " + l2);
	l3 = l1 + 1;
	System.out.println("Long.MAX_VALUE + 1: " + l3);
	System.out.println("**************************************************");


	f1 = Float.MAX_VALUE;
	f2 = Float.MIN_VALUE;
	f3 = Float.POSITIVE_INFINITY;
	f4 = Float.NEGATIVE_INFINITY;
	f5 = Float.NaN;

	System.out.println("Float.MAX_VALUE: " + f1);
	System.out.println("Float.MIN_VALUE: " + f2);
	System.out.println("Float.POSITIVE_INFINITY: " + f3);
	System.out.println("Float.NEGATIVE_INFINITY: " + f4);
	System.out.println("Float.NaN: " + f5);
	System.out.println("1.0/0.0: " + 1.0/0.0);
	System.out.println("-1.0/0.0: " + -1.0/0.0);
	System.out.println("0.0/0.0: "+ 0.0/0.0);
	System.out.println("**************************************************");

	d1 = Double.MAX_VALUE;
	d2 = Double.MIN_VALUE;
	d3 = Double.POSITIVE_INFINITY;
	d4 = Double.NEGATIVE_INFINITY;
	d5 = Double.NaN;

	System.out.println("Double.MAX_VALUE: " + d1);
	System.out.println("Double.MIN_VALUE: " + d2);
	System.out.println("Double.POSITIVE_INFINITY: " + d3);
	System.out.println("Double.NEGATIVE_INFINITY: " + d4);
	System.out.println("Double.NaN: " + d5);
	System.out.println("0.0/0.0 == Double.NaN: " + (0.0/0.0 == Double.NaN));
	System.out.println("Double.isNaN(0.0/0.0): " + Double.isNaN(0.0/0.0));
	System.out.println("**************************************************");


        /* type char stands for characters such as `a' */
        
        c1 = 'a';
        c2 = '3';
        c3 = '\u00A3';

	System.out.println("c1: " + c1);
	System.out.println("c2: " + c2);
	System.out.println("c3: " + c3);

	/* type boolean contains two elements true and false
         * boolean values can be connected by &&, ||, and !.
	 */

	
	bool1 = true;
	bool2 = false;

	System.out.println("bool1: " + bool1);
	System.out.println("bool2: " + bool2);
	System.out.println("bool1 && bool2: " + (bool1 && bool2));
	System.out.println("bool1 || bool2: " + (bool1 || bool2));
	System.out.println("(bool1 || bool2) && !(bool1 && bool2): " + //
                           ((bool1 || bool2) && !(bool1 && bool2)));



	}
}	
