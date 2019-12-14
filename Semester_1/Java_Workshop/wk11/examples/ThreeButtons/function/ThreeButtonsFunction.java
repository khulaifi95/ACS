import java.util.function.Function;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.text.Font; 

/**
 *  We add in this class three buttons to a Group which is displayed
 *  in the Stage. One button to count up, one to count down, and one
 *  to terminate the application.
 *
 *  The EventHandlers for the three different MouseEvents are defined
 *  by constant functions in this class.
 * 
 *  @version 2018-11-30
 *  @author Manfred Kerber
 */

public class ThreeButtonsFunction extends Application{
    // A new counter object is created.
    Counter counter = new Counter();
    // A text is used to display the value of the counter.
    Text text = new Text(220.0,150.0, "" + counter.getCounter());
    /* The event handler for the event of clicking the upButton is
     * defined by a constant function that maps an event to two
     * commands: increasing the counter by one and resetting the text
     * to display the current value of the counter.
     */
    private final EventHandler<MouseEvent> eventHandlerUp = e -> {
        counter.setCounter(counter.getCounter() + 1);
        text.setText("" + counter.getCounter());
    };
    /* The event handler for the event of clicking the downButton is
     * defined by a constant function that maps an event to two
     * commands: decreasing the counter by one and resetting the text
     * to display the current value of the counter.
     */
    private final EventHandler<MouseEvent> eventHandlerDown = e -> {
        counter.setCounter(counter.getCounter() - 1);
        text.setText("" + counter.getCounter());
    };
    /* The event handler for the event of clicking the endButton is
     * defined by a constant function that maps an event to two
     * commands: printing out the final value of the counter to the
     * command line and exiting the program.
     */
    private final EventHandler<MouseEvent> eventHandlerEnd = e -> {
        System.out.println("Final Count: " + counter.getCounter()); 
        System.exit(1);
    };

    @Override
    /**
     *  The start method.
     *  @param stage The stage on which the buttons are displayed.
     */
    public void start(Stage stage) throws Exception {
        //Changing the font to "verdana" at a size of 70 pt
        text.setFont(Font.font("verdana", 70));

        // Creates the upBotton with a label and positions it on the stage.
        Button upButton = new Button("Count up");
        upButton.setLayoutX(60);
        upButton.setLayoutY(20);

        // Creates the downBotton with a label and positions it on the stage.
        Button downButton = new Button("Count down");
        downButton.setLayoutX(60);
        downButton.setLayoutY(120);

        // Creates the endBotton with a label and positions it on the stage.
        Button endButton = new Button("End");
        endButton.setLayoutX(60);
        endButton.setLayoutY(220);

        /* Adding event Filter to the three buttons. This links the
         * buttons to the corresponding eventHanders.
         */
        upButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerUp);
        downButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerDown);
        endButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerEnd);

        //Creating a scene graph, consisting of the three buttons.
        Group root = new Group(upButton, downButton, endButton, text);
        //The scene containing one scene graph
        Scene scene = new Scene(root, 600, 300);
        stage.setTitle("Counter");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *  main method to call the start method.
     *  @param args Passed on to launch. Not used in the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
