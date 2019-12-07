import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.Font; 

/**
 *  The contoller says what the actions related to the three buttons
 *  should be. It extends VBox, a vertical box is put on the Scene.
 *  The exact shape is read in from the threeButtons.fxml file.
 *  
 *  Further information can be found in the oracle online tutorials, e.g.
 *  https://docs.oracle.com/javafx/2/fxml_get_started/custom_control.htm#BABDAAHE
 *  @version 2018-11-30
 *  @author Manfred Kerber
 */
public class Controller extends VBox {

    //A new counter is created.
    private Counter counter = new Counter();

    /* A new textField is created to display the current value of the
     * counter. With the @FXML tag it is attached to the
     * corresponding Label in the threeButtons.fxml file
    */  
    @FXML private Label textField;

    /**
     *  The constructor loads the definitions from the
     *  threeButtons.fxml file by creating an FXMLLoader.
     */
    public Controller() {
        FXMLLoader fxmlLoader =
            new FXMLLoader(getClass().getResource("threeButtons.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            // The fxmlLoader is loaded
            fxmlLoader.load();
            /* The textField is initially set to 0, it will change on
             * changes of the counter.
             */
            textField.setText(("" + 0));
        } catch (IOException exception) {
            /* If the loader cannot be loaded, a RuntimeException is
             * thrown.
             */
            throw new RuntimeException(exception);
        }
    }

    /* The tag @FXML links the method to the corresponding buttons in
     * the threeButtons.fxml file.
     */
    @FXML
    /** The event handler for the event of clicking the endButton is
     * defined by a method with the two command: first printing the
     * final value of the counter and second exiting the program.
     */
    public void endApplication() {
        System.out.println("Final Count: " + counter.getCounter());
        System.exit(1);
    }

    @FXML
    /** The event handler for the event of clicking the upButton is
     * defined by a method with the two commands: increasing the
     * counter by one and resetting the text to display the current
     * value of the counter.
     */
    public void upApplication() {
        counter.setCounter(counter.getCounter() + 1);
        textField.setText("" + counter.getCounter());
    }

    @FXML
    /** The event handler for the event of clicking the downButton is
     * defined by a method with the two commands: decreasing the
     * counter by one and resetting the text to display the current
     * value of the counter.
     */
    public void downApplication() {
        counter.setCounter(counter.getCounter() - 1);
        textField.setText("" + counter.getCounter());
    }
}
