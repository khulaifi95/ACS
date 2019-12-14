import java.util.function.Function;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

/**
 *  Realization of an EventHandler by a Function.
 *  This class contains a start method that puts a single Button in a
 *  Group, which forms a Scene that is associated with a Stage. The
 *  Button is linked to an EventHandler that is defined as a Function.
 *  Essentially, when the button is clicked the program is terminated
 *  by the System.exit(1) command.
 * 
 *  @version 2018-11-29
 *  @author Manfred Kerber
 */
public class DisplayEndButton extends Application{
    /**
     *  The eventHandlerEnd constant is a function that maps an event
     *  to the two command to firstly print something and secondly to
     *  terminate the program.
     */
    private final EventHandler<MouseEvent> eventHandlerEnd = e -> {
        System.out.println("This is the end."); 
        System.exit(1);
    };

    @Override
    /**
     *  The start method.
     *  @param stage The stage on which the button is displayed.
     */
    public void start(Stage stage) throws Exception {
        // A new Button is created with the label "End"
        Button endButton = new Button("End");
        // The position of the Button is determined.
        endButton.setLayoutX(60);
        endButton.setLayoutY(60);

        /*
         * The event that triggers the EventHandler eventHandlerEnd is
         * that the endButton is clicked with the mouse.
         */
        endButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerEnd);

        //Creating a scene graph, consisting of the endButton. 
        Group root = new Group(endButton);
        //The scene containing one scene graph
        Scene scene = new Scene(root, 150, 150);
        stage.setTitle("End");
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
