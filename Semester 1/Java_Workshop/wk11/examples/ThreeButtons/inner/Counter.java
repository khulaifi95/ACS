/**
 *  The counter class defines a counter, which is respresentd as a
 *  field variable of the same name (of type int). It can be accessed
 *  by the usual getter and setter.
 *
 *  @version 2018-11-30
 *  @author Manfred Kerber
 */

public class Counter{
    /**
     *  The only field variable.
     */
    private int counter;
    /**
     *  The constructor initializes the counter to 0.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * getter for counter
     * @return the counter we change and display.
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * setter for counter
     * @param counter the counter we change and display is updated to
     * its new value
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
