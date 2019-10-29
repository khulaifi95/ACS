import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;


/** JUnit tests for BankAccount.java 
 * Some tests about gender and password for the BankAccount example
 * @author  Manfred Kerber
 * @version 2016-10-25
 */

public class BankAccountTest {
    private Customer john, mary, sam, tony, jim, john2;
    private BankAccount bankAccountJohn, bankAccountMary, bankAccountSam;
    private Customer[] customers1, customers2, customers3; 

    @BeforeEach
    public void beforeEach() {
        john = new Customer ("John Smith", "M", "CS", "01214144789");
        mary = new Customer ("Mary Jones", "F", "CS", "01214144788");
        sam = new Customer ("Sam Smith", "X", "CS", "01214144787");
        bankAccountJohn = new BankAccount(john, "sesameJohn");
        bankAccountMary = new BankAccount(mary, "sesameMary");
        bankAccountSam = new BankAccount(sam, "sesameSam");
        jim = john;
        john2 = new Customer ("John Smith", "M", "CS", "01214144789");
        customers1 = new Customer[] {john, mary, sam, jim, john2};
        customers2 = new Customer[] {jim, mary, sam, john, john2};
        customers3 = new Customer[] {john2, mary, sam, jim, john};
        
    }
    
    @Test
    public void test1() {
        assertEquals("M", john.getGender(), "Error in getGender()");
        assertEquals("F", mary.getGender(), "Error in getGender()");
        assertEquals("X", sam.getGender(), "Error in getGender()");
        
        assertTrue(bankAccountJohn.checkPassword("sesameJohn"),
                   "Error in checkPassword()");
        assertFalse(bankAccountJohn.checkPassword("sesameMary"),
                    "Error in checkPassword()");
    }

    @Test public void test2() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
                new Customer ("ACME", "NN", "CS", "01214144789");});
        assertEquals("Gender must be \"M\", \"F\", or \"X\"", e.getMessage());
    }

    @Test public void test3() {
        assertNull(tony);
    }
    
    @Test public void test4() {
        assertNotNull(sam);
    }

    @Test public void test5() {
        assertSame(john, jim);
    }

    @Test public void test6() {
        assertNotSame(john, john2);
    }

    @Test public void test7() {
        assertArrayEquals(customers1, customers2);
    }

    @Test public void test8() {
        assertTrue("John".contains("Jo"));
  }

    @Disabled public void test10() {
        fail();
    }
}
