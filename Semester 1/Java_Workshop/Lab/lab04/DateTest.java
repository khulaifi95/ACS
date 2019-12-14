import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *  The tests are just a start and as discussed in the lab lecture
 *  many more tests would be necessary. Note that the last test,
 *  dateTest9, tests whether the Date constructor actually throws an
 *  exception (of type IllegalArgumentException in this case) and
 *  gives the correct error message ("Invalid date in class Date." in
 *  this case). The test passes if such an exception is thrown and the
 *  corresponding error message generated.
 *
 *  @version 2019-10-22
 *  @author Manfred Kerber, Alex Evangelidis
 */
public class DateTest {


    @Test public void dateTest1() {
       	assertTrue(Date.admissible(20, "October", 2019));  
    }

    @Test public void dateTest2() {
       	assertTrue(Date.admissible(31, "October", 2019));     
    }

    @Test public void dateTest3() {
       	assertFalse(Date.admissible(32, "October", 2019));    
    }

    @Test public void dateTest4() {
       	assertFalse(Date.admissible(21, "Monday", 2019));  
    }

    @Test public void dateTest5() {
       	assertFalse(Date.admissible(21, "October", -1));    
    }

    @Test public void dateTest6() {
       	assertFalse(Date.admissible(29, "February", 2019));  
    }

    @Test public void dateTest7() {
       	assertTrue(Date.admissible(29, "February", 2020));   
    }

    @Test public void dateTest8() {
       	assertFalse(Date.admissible(31, "April", 2019));   
    }

    @Test public void dateTest9() {
       	assertFalse(Date.admissible(2, "Monday", 2019));   
    }
    @Test public void dateTest10() {
       	assertFalse(Date.admissible(2, "", 2019));   
    }

    @Test public void dateTest11() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
                new Date(31, "April", 2019);});
        assertEquals("Invalid date in class Date.", e.getMessage());

    }
}
