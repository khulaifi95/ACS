/**
 *  This class demonstrates some example on Strings
 *  s.substring(n,m) with take the substring from the n-th
 *  character inclusively to the m-th character exclusively Note,
 *  we start counting from 0.
 *  @author Manfred Kerber
 *  @version 2019-10-14
 */

public class StringMain{   
    public static void main(String[] args){
 	String s;

	s = "Hello, Java\u2122";
	System.out.println("s.substring(0,4) --> " + s.substring(0,4));
        System.out.println("Text".equals("Te"+"xt"));
        System.out.println(s.substring(0,4) == "He"+"ll");
        System.out.println(s.substring(0,4).equals("He"+"ll"));
        
        
    }
}
