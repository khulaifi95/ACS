/**
 *   pi, e, and trigonometric functions, exponentation as found on a pocket calculator 
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 *
 */

public class Computation4 { 

    public static void main(String[] args) {

       /* 
	*  Note that 2.6881171418161356E43 means 2.6881171418161356 * 10^43
	*  and       3.720075976020836E-44 means 3.720075976020836  * 10^-44, 
        *            which is 3.720075976020836/10^44.
        *
        */

       System.out.print("System.out.println(Math.pow(2,5));                            ==>       "); System.out.println(Math.pow(2,5));
       System.out.print("System.out.println(Math.pow(Math.E,0));                       ==>       "); System.out.println(Math.pow(Math.E,0));
       System.out.print("System.out.println(Math.pow(Math.E,1));                       ==>       "); System.out.println(Math.pow(Math.E,1));
       System.out.print("System.out.println(Math.pow(Math.E,-1));                      ==>       "); System.out.println(Math.pow(Math.E,-1));
       System.out.print("System.out.println(Math.pow(Math.E,3+5));                     ==>       "); System.out.println(Math.pow(Math.E,3+5));
       System.out.print("System.out.println(Math.pow(Math.E,3)*Math.pow(Math.E,5));    ==>       "); System.out.println(Math.pow(Math.E,3)*Math.pow(Math.E,5));
       System.out.print("System.out.println(Math.pow(Math.E,Math.PI));                 ==>       "); System.out.println(Math.pow(Math.E,Math.PI));
       System.out.print("System.out.println(Math.pow(Math.PI,Math.E));                 ==>       "); System.out.println(Math.pow(Math.PI,Math.E));
       System.out.print("System.out.println(Math.pow(Math.E,5));                       ==>       "); System.out.println(Math.pow(Math.E,5));
       System.out.print("System.out.println(Math.exp(5));                              ==>       "); System.out.println(Math.exp(5));
       System.out.print("System.out.println(Math.exp(100));                            ==>       "); System.out.println(Math.exp(100));
       System.out.print("System.out.println(Math.exp(-100));                           ==>       "); System.out.println(Math.exp(-100));
       System.out.print("System.out.println(Math.log(148.4131591025766));              ==>       "); System.out.println(Math.log(148.4131591025766));
       System.out.println();
       System.out.print("System.out.println(Math.log(128)/Math.log(2));                ==>       "); System.out.println(Math.log(128)/Math.log(2));
       System.out.print("System.out.println(Math.log(32768)/Math.log(2));              ==>       "); System.out.println(Math.log(32768)/Math.log(2));
       System.out.println();

       System.out.println("For more standard mathematical functions (\"methods\") see");
       System.out.println("http://docs.oracle.com/javase/7/docs/api/");
       System.out.println("Look for the \"Math\" class");
       System.out.println("http://docs.oracle.com/javase/1.4.2/docs/api/");
       System.out.println("is a good site to bookmark anyway.");
   }
}
