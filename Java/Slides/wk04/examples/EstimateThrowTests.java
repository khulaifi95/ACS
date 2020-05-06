import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
    import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *  This file contains 58 JUnit tests for testing the estimateInBounds
 *  static method as defined in the EstimateThrow.java class.  The tests
 *  are written before the EstimateThrow.java class is written, which
 *  initially is only a stub and always returns false, that is,
 *  initially all the tests which start with assertTrue will fail.
 *
 *  In addition to the 56 JUnit tests from the lab lecture, we have
 *  one two tests to confirm that an exception is thrown if the
 *  nominal argument is below 5 or above 10000. Some of the tests that
 *  returned tested for true before, have now to test whether an
 *  exception is thrown.
 *
 *  @author Manfred Kerber
 *  @version 2019-10-20
 */

//actual, nominal
public class EstimateThrowTests {

    /* We allow for a tiny computational deviation in the computation.
     * If we did not test estimateTest5_2 fails since it is exactly at
     * the border of an accepted value of 4.55 but the accepted value
     * is computed as a tiny value below this. The computational
     * tolerance is stored in a static final variable of type double
     * called TOLERANCE.
     */
    public static final double TOLERANCE = 0.000000001;

    @Test public void estimateTestLessThan5_1() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(0.0 + TOLERANCE, 0.0);}); //exception
    }
    @Test public void estimateTestLessThan5_2() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(0.0 + TOLERANCE, -10.0);});//exception
    }
    @Test public void estimateTestLessThan5_4() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(5.0 + TOLERANCE, -10.0);});//exception
    }
    @Test public void estimateTestLessThan5_5() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(3.0 + TOLERANCE, 4.0);});//exception
    }
    @Test public void estimateTest5_1() {
       	assertFalse(EstimateThrow.estimateInBounds(4.0 + TOLERANCE, 5.0));     // no
    }
    @Test public void estimateTest5_2() {
       	assertTrue(EstimateThrow.estimateInBounds(4.55 + TOLERANCE, 5.0));     // yes
    }
    @Test public void estimateTest5_3() {
       	assertTrue(EstimateThrow.estimateInBounds(5.0 + TOLERANCE, 5.0));     // yes
    }
    @Test public void estimateTest5_4() {
       	assertTrue(EstimateThrow.estimateInBounds(6.0 + TOLERANCE, 5.0));     // yes
    }
    @Test public void estimateTestBetween5And50_1() {
       	assertTrue(EstimateThrow.estimateInBounds(10.3 + TOLERANCE, 11.0));     // yes
    }
    @Test public void estimateTestBetween5And50_2() {
	assertFalse(EstimateThrow.estimateInBounds(10.0 + TOLERANCE,11.0));        // no
    }
    @Test public void estimateTest50_1() {
	assertFalse(EstimateThrow.estimateInBounds(45 + TOLERANCE,50.0));        // no
    }
    @Test public void estimateTest50_2() {
	assertTrue(EstimateThrow.estimateInBounds(45.5 + TOLERANCE,50.0));        // yes
    }
    @Test public void estimateTest50_3() {
	assertTrue(EstimateThrow.estimateInBounds(50.0 + TOLERANCE,50.0));        // yes
    }
    @Test public void estimateTest50_4() {
	assertTrue(EstimateThrow.estimateInBounds(55.0 + TOLERANCE,50.0));        // yes
    }
    @Test public void estimateTestBetween50And100_1() {
        assertTrue(EstimateThrow.estimateInBounds(55.5 + TOLERANCE, 60.0));     // yes
    }
    @Test public void estimateTestBetween50And100_2() {
        assertFalse(EstimateThrow.estimateInBounds(55.0 + TOLERANCE, 60.0));     // no
    }
    @Test public void estimateTest100_1() {
        assertFalse(EstimateThrow.estimateInBounds(95.0 + TOLERANCE, 100.0));     // no
    }
    @Test public void estimateTest100_2() {
        assertTrue(EstimateThrow.estimateInBounds(95.5 + TOLERANCE, 100.0));     // yes
    }
    @Test public void estimateTest100_3() {
        assertTrue(EstimateThrow.estimateInBounds(100.0 + TOLERANCE, 100.0));     // yes
    }
    @Test public void estimateTest100_4() {
        assertTrue(EstimateThrow.estimateInBounds(110.0 + TOLERANCE, 100.0));     // yes
    }
    @Test public void estimateTestBetween100And200_1() {
        assertTrue(EstimateThrow.estimateInBounds(109.0 + TOLERANCE, 110.0));     // yes
    }
    @Test public void estimateTestBetween100And200_2() {
        assertFalse(EstimateThrow.estimateInBounds(100.0 + TOLERANCE, 110.0));   // no
    }
    @Test public void estimateTest200_1() {
        assertFalse(EstimateThrow.estimateInBounds(100.0 + TOLERANCE, 200.0));   // no
    }
    @Test public void estimateTest200_2() {
        assertFalse(EstimateThrow.estimateInBounds(190.9 + TOLERANCE, 200.0));   // no
    }
    @Test public void estimateTest200_3() {
        assertTrue(EstimateThrow.estimateInBounds(191.0 + TOLERANCE, 200.0));   // yes
    }
    @Test public void estimateTest200_4() {
        assertTrue(EstimateThrow.estimateInBounds(200.0 + TOLERANCE, 200.0));   // yes
    }
    @Test public void estimateTest200_5() {
        assertTrue(EstimateThrow.estimateInBounds(210.0 + TOLERANCE, 200.0));   // yes
    }
    @Test public void estimateTestBetween200And300_1() {
        assertTrue(EstimateThrow.estimateInBounds(210.0 + TOLERANCE, 219.0));   // yes
    }
    @Test public void estimateTestBetween200And300_2() {
        assertFalse(EstimateThrow.estimateInBounds(210.0 + TOLERANCE, 220.0));   // no
    }
    @Test public void estimateTest300_1() {
        assertFalse(EstimateThrow.estimateInBounds(290.9 + TOLERANCE, 300.0));   // no
    }
    @Test public void estimateTest300_2() {
        assertTrue(EstimateThrow.estimateInBounds(291.0 + TOLERANCE, 300.0));   // yes
    }
    @Test public void estimateTest300_3() {
        assertTrue(EstimateThrow.estimateInBounds(300.0 + TOLERANCE, 300.0));   // yes
    }
    @Test public void estimateTest300_4() {
        assertTrue(EstimateThrow.estimateInBounds(310.0 + TOLERANCE, 300.0));   // yes
    }
    @Test public void estimateTestBetween300And500_1() {
        assertTrue(EstimateThrow.estimateInBounds(310.0 + TOLERANCE, 319.0));   // yes
    }
    @Test public void estimateTestBetween300And500_2(){
        assertFalse(EstimateThrow.estimateInBounds(310.0 + TOLERANCE, 320.0));   // no
    }
    @Test public void estimateTest500_1(){
        assertFalse(EstimateThrow.estimateInBounds(484.9 + TOLERANCE, 500.0));   // no
    }
    @Test public void estimateTest500_2(){
        assertTrue(EstimateThrow.estimateInBounds(485.0 + TOLERANCE, 500.0));   // yes
    }
    @Test public void estimateTest500_3(){
        assertTrue(EstimateThrow.estimateInBounds(500.0 + TOLERANCE, 500.0));   // yes
    }
    @Test public void estimateTest500_5(){
        assertTrue(EstimateThrow.estimateInBounds(510.0 + TOLERANCE, 500.0));   // yes
    }
    @Test public void estimateTestBetween500And1000_1(){
        assertTrue(EstimateThrow.estimateInBounds(510.0 + TOLERANCE, 525.0));   // yes
    }
    @Test public void estimateTestBetween500And1000_2(){
        assertFalse(EstimateThrow.estimateInBounds(510.0 + TOLERANCE, 530.0));   // no
    }
    @Test public void estimateTest1000_1(){
        assertFalse(EstimateThrow.estimateInBounds(984.9 + TOLERANCE, 1000.0));   // no
    }
    @Test public void estimateTest1000_2(){
        assertTrue(EstimateThrow.estimateInBounds(985.0 + TOLERANCE, 1000.0));   // yes
    }
    @Test public void estimateTest1000_3(){
        assertTrue(EstimateThrow.estimateInBounds(1000.0 + TOLERANCE, 1000.0));   // yes
    }
    @Test public void estimateTest1000_4(){
        assertTrue(EstimateThrow.estimateInBounds(1010.0 + TOLERANCE, 1000.0));   // yes
    }
    @Test public void estimateTestBetween1000And10000_1(){
        assertTrue(EstimateThrow.estimateInBounds(1000.0 + TOLERANCE, 1001.0)); // yes
    }
    @Test public void estimateTestBetween1000And10000_2(){
        assertFalse(EstimateThrow.estimateInBounds(1000.0 + TOLERANCE, 1016.0)); // no
    }
    @Test public void estimateTest10000_1() {
        assertTrue(EstimateThrow.estimateInBounds(9850.0 + TOLERANCE, 10000.0)); //  yes
    }
    @Test public void estimateTest10000_2() {
        assertFalse(EstimateThrow.estimateInBounds(9849.9 + TOLERANCE, 10000.0)); // no
    }
    @Test public void estimateTest10000_3() {
        assertTrue(EstimateThrow.estimateInBounds(9851 + TOLERANCE, 10000.0)); // yes
    }
    @Test public void estimateTestMoreThan10000_1() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(0.0 + TOLERANCE, 100000.0);}); // exception
    }
    @Test public void estimateTestMoreThan10000_2() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(9999.0 + TOLERANCE, 100000.0);}); // exception
    }
    @Test public void estimateTestMoreThan10000_3() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(100000.0 + TOLERANCE, 100000.0);}); // exception
    }
    @Test public void estimateTestMoreThan10000_4() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(100001.0 + TOLERANCE, 100000.0);}); // exception
    }
    @Test public void estimateTestMoreThan10000_5() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(0.0 + TOLERANCE, 200000.0);}); // exception
    }
    @Test public void estimateTestMoreThan10000_6() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(100001.0 + TOLERANCE, 200000.0);}); // exception
    }
    @Test public void estimateTestMoreThan10000_7() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(200000.0 + TOLERANCE, 200000.0);}); // exception
    }
    @Test public void estimateTestMoreThan10000_8() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(400000.0 + TOLERANCE, 200000.0);}); // exception
    }
    @Test public void estimateTestThrow1() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(4.0 + TOLERANCE, 4.0);});
    }
    @Test public void estimateTestThrow2() {
        assertThrows(IllegalArgumentException.class, () -> {
                EstimateThrow.estimateInBounds(400000.0 + TOLERANCE, 200000.0);});
    }

}
