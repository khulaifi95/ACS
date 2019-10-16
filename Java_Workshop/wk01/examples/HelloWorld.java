/**
 *
 * Java is an object-oriented programming language. As such everything is put
 * in classes. The file name, in this case HelloWorld.java has to match the 
 * name of the class programmed in it.
 * The syntax is
 *                      public class HelloWorld 
 *
 * We do not worry this week about the meaning of all this and ignore
 * for now the meaning of "public class" and the whole line 
 * "public static void main(String[] args)".
 *
 * In order to run a Java program as in this file, HelloWorld.java
 * we have first to compile it by the command
 *                      javac HelloWorld.java
 * This generates a file
 *                      HelloWorld.class
 * which in turn
 * can be run by
 *                      java HelloWorld
 * @author  Manfred Kerber
 * @version 2014-09-27
 */

public class HelloWorld{ 
    public static void main(String[] args){
       System.out.println("Hello World!");
   }
}
