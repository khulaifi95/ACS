import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Polyline;

/**
 *  This is an example display how to display a polyline with the
 *  points (x0,y0), (x1,y1), ..., and (xn,yn) using constructors
 *  Polyline(x0,y0, x1,y1), Polyline(x0,y0, x1,y1, x2,y2), and so on.
 *
 *  The example is adapted from
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_2d_shapes.htm">
 *  www.tutorialspoint.com</a>.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class DrawPolyline extends Application{

    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Creating a line object 
        Polyline polyline = new Polyline(210,10, 10,210, 410,210); 
         
        /* Alternatively, create an empty polyline by
         *  Polyline polyline = new Polyline(); 
         * and then add the 3 points:
         *  polyline.getPoints().addAll(new Double[]{
         *  210.0,10.0,
         *  10.0,210.0,
         *  410.0,210.0);
         */
         
        // Create a Group (scene graph) with the line as single element.
        Group root = new Group(polyline);
        // The scene consists of just one group.
        Scene scene = new Scene(root, 600, 300);

        // Give the stage (window) a title and add the scene.
        stage.setTitle("Polyline");
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
