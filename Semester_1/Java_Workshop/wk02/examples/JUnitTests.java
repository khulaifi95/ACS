import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *  This file contains 7 JUnit tests for demonstrating assertEquals,
 *  assertFalse, and asserTrue.
 *
 *  Note that the class does not contain a main method.
 *  That is, it canNOT be run by java JUnitTests.
 *
 *  In order to run JUnit tests (Version 5), a so-called jar file with
 *  name junit-platform-console-standalone-1.5.2.jar is needed.
 *
 *  Assume you have stored the file
 *  junit-platform-console-standalone-1.5.2.jar in a directory 
 *  /usr/local/java/junit-platform-console-standalone-1.5.2.jar
 *  then you first compile the two files  (from the command line) by
 *  javac -d bin JUnit.java
 *  javac -d bin -cp bin:/usr/local/java/junit-platform-console-standalone-1.5.2.jar JUnitTests.java
 *
 *  The -d option is used to determine the directory where the .class files
 *  are to be stored.
 *  
 *  The tests are then run by
 *  java -jar /usr/local/java/junit-platform-console-standalone-1.5.2.jar --class-path bin --scan-class-path
 *
 *  It is possible to create abbreviations (in the .cshrc file) by
 *  alias junitc 'javac -d bin -cp bin:/usr/local/java/junit5-r5.5.2/junit-platform-console-standalone/build/libs/junit-platform-console-standalone-1.5.2.jar'
 *  alias junit 'java -jar /usr/local/java/junit5-r5.5.2/junit-platform-console-standalone/build/libs/junit-platform-console-standalone-1.5.2.jar --class-path bin --scan-class-path'
 *  With these the above can be written as
 *  javac -d bin JUnit.java
 *  junitc JunitTests.java
 *  junit
 *  
 *
 *  @author Manfred Kerber
 *  @version 2019-10-05
 */

public class JUnitTests {
    
    @Test
    void assertEqualsTestMultiply() {
        assertEquals(20, JUnit.multiply(4,5),
                     "Failure in multiply.");
    }

    @Test
    void assertEqualsTestString() {
        assertEquals("text", "te"+ "xt", 
                     "Failure in assertEqualsTest1: " +
                     " expected String not equal given String\n");
    }

    @Test
    void assertEqualsTestSqrt() {
        assertEquals(5, Math.sqrt(5) * Math.sqrt(5), 0.00001,
                     "failure in assertEqualsTestSqrt: " +
                     " expected sqrt(5) * sqrt(5) ~= 5");
    }

    @Test
    void assertEqualsTestInt() {
        assertEquals(4, 2 * 2,
                     "failure in assertEqualsTestInt: " +
                     " expected 4 == 2 * 2");
    }

    @Test
    void assertEqualsTest2() {
        assertEquals(2.0, //expected
                     2.1, //actual
                     0.11, // tolerance >= |expected - actual|
                     "failure in assertEqualsTest2: " +
                     "expected and actual values differ");
    }

    @Test
    void assertFalseTest() {
        assertFalse(3 == 4,
                    "failure in assertFalseTest: " +
                    " expected false but got true");
    }

    @Test
    void assertTrueTest() {
        assertTrue(2 < 5,
                    "failure in assertTrueTest: " +
                    "expected true but got false");
    }
}


