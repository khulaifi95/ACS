/**
 * Assertions are used to establish that properties we are certain
 * that they hold at a particular point in the program actually do
 * hold. If the assertion does not hold then an exception will be
 * raised -- assumed the compiler is correspondingly configured (by
 * the -ea option in `Run Configurations' and `(x)= Arguments' under
 * `VM Arguments' in Eclipse). This way, programs can be debugged,
 * however, they will not raise an exception when the program has
 * actually to perform.
 * 
 * @version 2017-10-18
 * @author Manfred Kerber
 *
 */
public class AssertExample {
    
    public static void main(String[] args) {
        double x = -0.5;
        // Assumed we execute x = Math.abs(x) then the value of x must
        // be greater than or equal to 0.  If, however, we forget the
        // assert command will raise an exception.
        x = Math.abs(x);
        assert x >= 1;
        System.out.println(Math.sqrt(x));
    }
}
