package myTest;
/**
 * @author Manfred Kerber
 * @version 2015/11/09
 * Example from Horstmann: Java Concepts p.293
 * 
 * The example shows that introducing a new variable with the same
 * name in a constructor `shadows' the field variable and that
 * accordingly the corresponding field variable is not set.
 */

public class Coin {
    private int value;
    private String unit;

    /**
     *  @param val The value of the coin, e.g. 50 on a 50p coin.
     *  @param u The unit on the coin, e.g. "pence" for a 50p coin.
     *
     */
    public Coin(int val, String u) {
        value = val;
        /* The following line of code is wrong, since it
         * introduces a NEW variable with name unit which
         * shadows the field variable with name unit. Hence
         * the value of the NEW variable is set to u on
         * construction of a Coin object, but the field
         * variable unit remains uninitialized, hence will be
         * null. That is, this line should be deleted.
         * Lesson learned: superfluous variables are dangerous.
         */
        String unit = u;
        // The following line should replace the previous.
        //              unit = u;
    }

    /**
     *  @return A human readable String for a Coin object.
     */
    public String toString() {
        return "Value: " + value + " " + unit;
    }

    /*
     *  Main method to test the Coin class. We do not get the unit
     *  when printing, that is, not "2 pounds", but "2 null" in the
     *  example.
     */
    public static void main(String[] args) {
        Coin twoPounds = new Coin(2, "pounds");
        System.out.println(twoPounds);
    }
}
