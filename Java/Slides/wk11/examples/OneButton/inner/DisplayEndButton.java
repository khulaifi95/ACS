import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

/**
 *  Realization of an EventHandler by an inner class.
 *  This class contains a start method that puts a single Button in a
 *  Group, which forms a Scene that is associated with a Stage. The
 *  Button is linked to an EventHandler that is defined as a Function.
 *  Essentially, when the button is clicked the program is terminated
 *  by the System.exit(1) command.
 *
 *  The example is adapted from the 4th edition of "Absolute Java" by
 *  Walter Savitch, p.971.
 * 
 *  @version 2018-11-29
 *  @author Manfred Kerber
 */
public class DisplayEndButton extends Application {
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
         * The EventHandler is defined in the constructor as an inner class.
         */
        EventHandler<MouseEvent> eventHandlerEnd =
            new EventHandler<MouseEvent>() { 
                @Override 
                /**
                 *  The eventHandlerEnd is defined by the method
                 *  handle which takes a MouseEvent as parameter. In
                 *  case of a suitable mouse event, the two commands
                 *  to firstly print something and secondly to
                 *  terminate the program are executed.
                 */
                public void handle(MouseEvent e) { 
                    System.out.println("This is the end."); 
                    System.exit(1);
                } 
            };

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
}
