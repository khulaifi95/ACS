import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *  This file contains 56 JUnit tests for testing the estimateInBounds
 *  static method as defined in the Estimate.java class.  The tests
 *  are written before the Estimate.java class is written, which
 *  initially is only a stub and always returns false, that is,
 *  initially all the tests which start with assertTrue will fail.
 *
 *  @author Manfred Kerber
 *  @version 2019-10-16
 */

//actual, nominal
public class EstimateTests {

    /* We allow for a tiny computational deviation in the computation.
     * If we did not test estimateTest5_2 fails since it is exactly at
     * the border of an accepted value of 4.55 but the accepted value
     * is computed as a tiny value below this. The computational
     * tolerance is stored in a static final variable of type double
     * called TOLERANCE.
     */
    public static final double TOLERANCE = 0.000000001;

    @Test public void estimateTestLessThan5_1() {
       	assertTrue(Estimate.estimateInBounds(0.0 + TOLERANCE, 0.0));     // yes
    }
    @Test public void estimateTestLessThan5_2() {
       	assertTrue(Estimate.estimateInBounds(0.0 + TOLERANCE, -10.0));     // yes
    }
    @Test public void estimateTestLessThan5_4() {
       	assertTrue(Estimate.estimateInBounds(5.0 + TOLERANCE, -10.0));     // yes
    }
    @Test public void estimateTestLessThan5_5() {
       	assertTrue(Estimate.estimateInBounds(3.0 + TOLERANCE, 4.0));     // yes
    }
    @Test public void estimateTest5_1() {
       	assertFalse(Estimate.estimateInBounds(4.0 + TOLERANCE, 5.0));     // no
    }
    @Test public void estimateTest5_2() {
       	assertTrue(Estimate.estimateInBounds(4.55 + TOLERANCE, 5.0));     // yes
    }
    @Test public void estimateTest5_3() {
       	assertTrue(Estimate.estimateInBounds(5.0 + TOLERANCE, 5.0));     // yes
    }
    @Test public void estimateTest5_4() {
       	assertTrue(Estimate.estimateInBounds(6.0 + TOLERANCE, 5.0));     // yes
    }
    @Test public void estimateTestBetween5And50_1() {
       	assertTrue(Estimate.estimateInBounds(10.3 + TOLERANCE, 11.0));     // yes
    }
    @Test public void estimateTestBetween5And50_2() {
	assertFalse(Estimate.estimateInBounds(10.0 + TOLERANCE,11.0));        // no
    }
    @Test public void estimateTest50_1() {
	assertFalse(Estimate.estimateInBounds(45 + TOLERANCE,50.0));        // no
    }
    @Test public void estimateTest50_2() {
	assertTrue(Estimate.estimateInBounds(45.5 + TOLERANCE,50.0));        // yes
    }
    @Test public void estimateTest50_3() {
	assertTrue(Estimate.estimateInBounds(50.0 + TOLERANCE,50.0));        // yes
    }
    @Test public void estimateTest50_4() {
	assertTrue(Estimate.estimateInBounds(55.0 + TOLERANCE,50.0));        // yes
    }
    @Test public void estimateTestBetween50And100_1() {
        assertTrue(Estimate.estimateInBounds(55.5 + TOLERANCE, 60.0));     // yes
    }
    @Test public void estimateTestBetween50And100_2() {
        assertFalse(Estimate.estimateInBounds(55.0 + TOLERANCE, 60.0));     // no
    }
    @Test public void estimateTest100_1() {
        assertFalse(Estimate.estimateInBounds(95.0 + TOLERANCE, 100.0));     // no
    }
    @Test public void estimateTest100_2() {
        assertTrue(Estimate.estimateInBounds(95.5 + TOLERANCE, 100.0));     // yes
    }
    @Test public void estimateTest100_3() {
        assertTrue(Estimate.estimateInBounds(100.0 + TOLERANCE, 100.0));     // yes
    }
    @Test public void estimateTest100_4() {
        assertTrue(Estimate.estimateInBounds(110.0 + TOLERANCE, 100.0));     // yes
    }
    @Test public void estimateTestBetween100And200_1() {
        assertTrue(Estimate.estimateInBounds(109.0 + TOLERANCE, 110.0));     // yes
    }
    @Test public void estimateTestBetween100And200_2() {
        assertFalse(Estimate.estimateInBounds(100.0 + TOLERANCE, 110.0));   // no
    }
    @Test public void estimateTest200_1() {
        assertFalse(Estimate.estimateInBounds(100.0 + TOLERANCE, 200.0));   // no
    }
    @Test public void estimateTest200_2() {
        assertFalse(Estimate.estimateInBounds(190.9 + TOLERANCE, 200.0));   // no
    }
    @Test public void estimateTest200_3() {
        assertTrue(Estimate.estimateInBounds(191.0 + TOLERANCE, 200.0));   // yes
    }
    @Test public void estimateTest200_4() {
        assertTrue(Estimate.estimateInBounds(200.0 + TOLERANCE, 200.0));   // yes
    }
    @Test public void estimateTest200_5() {
        assertTrue(Estimate.estimateInBounds(210.0 + TOLERANCE, 200.0));   // yes
    }
    @Test public void estimateTestBetween200And300_1() {
        assertTrue(Estimate.estimateInBounds(210.0 + TOLERANCE, 219.0));   // yes
    }
    @Test public void estimateTestBetween200And300_2() {
        assertFalse(Estimate.estimateInBounds(210.0 + TOLERANCE, 220.0));   // no
    }
    @Test public void estimateTest300_1() {
        assertFalse(Estimate.estimateInBounds(290.9 + TOLERANCE, 300.0));   // no
    }
    @Test public void estimateTest300_2() {
        assertTrue(Estimate.estimateInBounds(291.0 + TOLERANCE, 300.0));   // yes
    }
    @Test public void estimateTest300_3() {
        assertTrue(Estimate.estimateInBounds(300.0 + TOLERANCE, 300.0));   // yes
    }
    @Test public void estimateTest300_4() {
        assertTrue(Estimate.estimateInBounds(310.0 + TOLERANCE, 300.0));   // yes
    }
    @Test public void estimateTestBetween300And500_1() {
        assertTrue(Estimate.estimateInBounds(310.0 + TOLERANCE, 319.0));   // yes
    }
    @Test public void estimateTestBetween300And500_2(){
        assertFalse(Estimate.estimateInBounds(310.0 + TOLERANCE, 320.0));   // no
    }
    @Test public void estimateTest500_1(){
        assertFalse(Estimate.estimateInBounds(484.9 + TOLERANCE, 500.0));   // no
    }
    @Test public void estimateTest500_2(){
        assertTrue(Estimate.estimateInBounds(485.0 + TOLERANCE, 500.0));   // yes
    }
    @Test public void estimateTest500_3(){
        assertTrue(Estimate.estimateInBounds(500.0 + TOLERANCE, 500.0));   // yes
    }
    @Test public void estimateTest500_5(){
        assertTrue(Estimate.estimateInBounds(510.0 + TOLERANCE, 500.0));   // yes
    }
    @Test public void estimateTestBetween500And1000_1(){
        assertTrue(Estimate.estimateInBounds(510.0 + TOLERANCE, 525.0));   // yes
    }
    @Test public void estimateTestBetween500And1000_2(){
        assertFalse(Estimate.estimateInBounds(510.0 + TOLERANCE, 530.0));   // no
    }
    @Test public void estimateTest1000_1(){
        assertFalse(Estimate.estimateInBounds(984.9 + TOLERANCE, 1000.0));   // no
    }
    @Test public void estimateTest1000_2(){
        assertTrue(Estimate.estimateInBounds(985.0 + TOLERANCE, 1000.0));   // yes
    }
    @Test public void estimateTest1000_3(){
        assertTrue(Estimate.estimateInBounds(1000.0 + TOLERANCE, 1000.0));   // yes
    }
    @Test public void estimateTest1000_4(){
        assertTrue(Estimate.estimateInBounds(1010.0 + TOLERANCE, 1000.0));   // yes
    }
    @Test public void estimateTestBetween1000And10000_1(){
        assertTrue(Estimate.estimateInBounds(1000.0 + TOLERANCE, 1001.0)); // yes
    }
    @Test public void estimateTestBetween1000And10000_2(){
        assertFalse(Estimate.estimateInBounds(1000.0 + TOLERANCE, 1016.0)); // no
    }
    @Test public void estimateTest10000_1() {
        assertTrue(Estimate.estimateInBounds(9850.0 + TOLERANCE, 10000.0)); //  yes
    }
    @Test public void estimateTest10000_2() {
        assertFalse(Estimate.estimateInBounds(9849.9 + TOLERANCE, 10000.0)); // no
    }
    @Test public void estimateTest10000_3() {
        assertTrue(Estimate.estimateInBounds(9851 + TOLERANCE, 10000.0)); // yes
    }
    @Test public void estimateTestMoreThan10000_1() {
        assertTrue(Estimate.estimateInBounds(0.0 + TOLERANCE, 100000.0)); // yes, not in range
    }
    @Test public void estimateTestMoreThan10000_2() {
        assertTrue(Estimate.estimateInBounds(9999.0 + TOLERANCE, 100000.0)); // yes, not in range
    }
    @Test public void estimateTestMoreThan10000_3() {
        assertTrue(Estimate.estimateInBounds(100000.0 + TOLERANCE, 100000.0)); // yes, not in range
    }
    @Test public void estimateTestMoreThan10000_4() {
        assertTrue(Estimate.estimateInBounds(100001.0 + TOLERANCE, 100000.0)); // yes, not in range
    }
    @Test public void estimateTestMoreThan10000_5() {
        assertTrue(Estimate.estimateInBounds(0.0 + TOLERANCE, 200000.0)); // yes, not in range
    }
    @Test public void estimateTestMoreThan10000_6() {
        assertTrue(Estimate.estimateInBounds(100001.0 + TOLERANCE, 200000.0)); // yes, not in range
    }
    @Test public void estimateTestMoreThan10000_7() {
        assertTrue(Estimate.estimateInBounds(200000.0 + TOLERANCE, 200000.0)); // yes, not in range
    }
    @Test public void estimateTestMoreThan10000_8() {
        assertTrue(Estimate.estimateInBounds(400000.0 + TOLERANCE, 200000.0)); // yes, not in range
    }
}
