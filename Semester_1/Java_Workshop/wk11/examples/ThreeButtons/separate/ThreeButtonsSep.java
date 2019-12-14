import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight;


/**
 *  We add in this class three buttons to a Group which is displayed
 *  in the Stage. One button to count up, one to count down, and one
 *  to terminate the application.
 *
 *  The EventHandlers for the three different MouseEvents are defined
 *  by in different classes.
 * 
 *  @version 2018-11-30
 *  @author Manfred Kerber
 */
public class ThreeButtonsSep extends Application{
    
    @Override
    /**
     *  The start method.
     *  @param stage The stage on which the buttons are displayed.
     */
    public void start(Stage stage) throws Exception {
        // A new counter object is created.
        Counter counter = new Counter();
        /* A new text object at a particular position is created.  It
         * contains initially the initial value of the counter, i.e.,
         * 0.
         */
        Text text = new Text(220.0,150.0, "" + counter.getCounter());

        //Changing the font to "verdana" at a size of 70 pt
        text.setFont(Font.font("verdana", 70));

        // The upButton is created with text and position.
        Button upButton = new Button("Count up");
        upButton.setLayoutX(60);
        upButton.setLayoutY(20);

        // The downButton is created with text and position.
        Button downButton = new Button("Count down");
        downButton.setLayoutX(60);
        downButton.setLayoutY(120);

        // The endButton is created with text and position.
        Button endButton = new Button("End");
        endButton.setLayoutX(60);
        endButton.setLayoutY(220);

        /* EventHandlers for the three buttons are constructed using
         * the constructors in the three external classes UpHandler,
         * DownHandler, and EndHandler.
         */
        UpHandler eventHandlerUp = new UpHandler(counter, text);
        DownHandler eventHandlerDown = new DownHandler(counter, text);
        EndHandler eventHandlerEnd = new EndHandler(counter);
        
        /* Adding event filters and handlers to the buttons.
         * Concretly that mean that the mouse must be clicked (that
         * is, it is not good enough to just hover with the mouse over
         * the button, e.g.). Furthermore to the buttons the
         * corresponding handlers are associated, e.g., the upBotton
         * to the eventHandlerUp.
         */
        upButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerUp);
        downButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerDown);
        endButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerEnd);

        //Creating a scene graph, consisting of the circle and the ellipse. 
        Group root = new Group(upButton, downButton, endButton, text);
        //The scene containing one scene graph
        Scene scene = new Scene(root, 600, 300);
        stage.setTitle("Three Buttons");
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
