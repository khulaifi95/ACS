import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 *  Realization of an EventHandler by a separate class.
 *
 *  This class contains a start method that puts a single Button in a
 *  Group, which forms a Scene that is associated with a Stage. The
 *  Button is linked to an EventHandler that is found in a separate
 *  class, which we call EndingListener.
 *
 *  The example is adapted from the 4th edition of "Absolute Java"
 *  by Walter Savitch, p.971ff
 *  
 *  @version 2018-11-29
 *  @author Manfred Kerber
 */

public class Main extends Application {
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
         * The EventHandler is defined in the EndingListener class.
         */
        EndingListener eventHandlerEnd = new EndingListener();
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
