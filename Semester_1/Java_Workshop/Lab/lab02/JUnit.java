/** 
 *  This class is used to demonstrate JUnit tests. Tests for the class
 *  have to be written in a file called JUnitTests.java.  In order to
 *  run JUnit tests (Version 5), a so-called jar file with name
 *  junit-platform-console-standalone-1.5.2.jar is needed.
 *
 *  Assume you have stored the file
 *  junit-platform-console-standalone-1.5.2.jar in a directory 
 *  /usr/local/java/junit-platform-console-standalone-1.5.2.jar
 *  then you first compile the two files  (from the command line) by
 *  javac -d bin JUnit.java
 *  javac -d bin -cp bin:/usr/local/java/junit-platform-console-standalone-1.5.2.jar
 *
 *  The -d option is used to determine the directory where the .class files
 *  are to be stored.
 *  
 *  The tests are then run by
 *  java -jar /usr/local/java/junit-platform-console-standalone-1.5.2.jar --class-path bin --scan-class-path
 *
 *  @version 2019-10-05
 *  @author Manfred Kerber
 */
public class JUnit {

    /**
     *  The method multiples to numbers.
     *  @param factor1 The first factor of the product.
     *  @param factor2 The second factor of the product.
     *  @return The product of the two factors.
     */
    public static double multiply(double factor1, double factor2) {
        return factor1 * factor2;
    }
}
