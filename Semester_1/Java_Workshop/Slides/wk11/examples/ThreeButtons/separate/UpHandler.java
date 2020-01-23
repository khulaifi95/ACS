import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

/**
 *  Adapted from the 4th edition of "Absolute Java" by Walter Savitch,
 *  p.971 (where the corresponding Swing implementation is described).
 *  The class implements the UpHandler, that is, the handler for the
 *  event that the upButton is clicked.

 *  @version 2018-11-30
 *  @author Manfred Kerber
 *
 */
public class UpHandler implements EventHandler<MouseEvent> {
    /**
     *  There are two field variables for the counter and the text to
     *  be displayed.
     */
    private Counter counter;
    private Text text;

    /**
     *  Standard constructor that initializes the field variables.
     *  @param counter The counter to be updated.
     *  @param text The text to be updated.
     */
    public UpHandler(Counter counter, Text text) {
        this.counter = counter;
        this.text = text;
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

    /** 
     *  Standard getter for the text.
     *  @return The text is returned.
     */
    public Text getText() {
        return text;
    }
    
    /** 
     *  Standard setter for the text.
     *  @param text The new value of the text.
     */
    public void setText(Text text) {
        this.text = text;
    }
    
    @Override 
    /**
     *  The handle for the mouse event, consisting of two commands:
     *  first to increase the counter, and second to update the text
     *  accordingly.
     *  @param e A mouse event such as clicking the mouse.
     */
    public void handle(MouseEvent e) { 
        counter.setCounter(counter.getCounter() + 1);
        text.setText("" + counter.getCounter());
    } 
}
