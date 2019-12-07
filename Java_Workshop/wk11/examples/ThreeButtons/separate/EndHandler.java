import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

/**
 *  Adapted from the 4th edition of "Absolute Java" by Walter Savitch,
 *  p.971 (where the corresponding Swing implementation is described).
 *  The class implements the EndHandler, that is, the handler for the
 *  event that the endButton is clicked.

 *  @version 2018-11-30
 *  @author Manfred Kerber
 *
 */
public class EndHandler implements EventHandler<MouseEvent> {
    /**
     *  There is one field variable for the counter.
     */
    private Counter counter;

    /**
     *  Standard constructor that initializes the field variable.
     *  @param counter The counter whose value is to be written out.
     */
    public EndHandler(Counter counter) {
        this.counter = counter;
    }

    /** 
     *  Standard getter for the counter.
     *  @return The counter is returned.
     */
    public Counter getCounter() {
        return counter;
    }
    
    /** 
     *  Standard setter for the counter.
     *  @param counter The new value of the counter.
     */
    public void setCounter(Counter counter) {
        this.counter = counter;
    }
    
    public void handle(MouseEvent e) { 
    /**
     *  The handle for the mouse event, consisting of two commands:
     *  first to print the final value of the counter, and second to
     *  exit the program.
     *  @param e A mouse event such as clicking the mouse.
     */
        System.out.println("Final Count: " + getCounter().getCounter());
        System.exit(1);
    } 
}
