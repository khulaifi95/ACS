import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color; 


/**
 *  This is an example display a rectangle with sides parallel 
 *  to the x- or y-axes.
 *
 *  The example is adapted from
 *  <a href="https://www.tutorialspoint.com/javafx/2dshapes_rectangle.htm">
 *  www.tutorialspoint.com</a>.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class DrawRectangle extends Application{

    /**
     * <pre>
     *  The method starts the graphics for a particular window.
     *  The constructor for a Rectangle takes 4 parameters: 
     *  - the x-coordinate of the left upper corner 
     *  - the y-coordinate of the left upper corner 
     *  - the width
     *  - the height
     *
     *   A rectangle is created.
     *   (20,30)            100
     *      +---------------------------------+
     *      |                                 |
     *      |                                 |
     *      |                                 |
     *  50  |                                 |
     *      |                                 |
     *      |                                 |
     *      |                                 |
     *      +---------------------------------+
     *  </pre>
     *
     *  The scene takes as arguments a scene graph, the width (in
     *  pixels), and the height (in pixels).
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Rectangle rectangle = new Rectangle(20, 30, 100, 50);
        rectangle.setFill(Color.BLUE);
        //Creating a scene graph, consisting of the rectangle only. 
        Group root = new Group(rectangle);
        //The scene containing one scene graph
        Scene scene = new Scene(root, 600, 300);
        stage.setTitle("Rectangle");
        stage.setScene(scene);
        stage.show();
    }

    /*
     *  main method to launch the application.
     */
    public static void main(String[] args) { 
        launch(args);
    }
}
