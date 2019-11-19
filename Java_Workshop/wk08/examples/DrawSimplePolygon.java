import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color; 

/**
 *  This is an example display how to display a polygon with the
 *  points (x0,y0), (x1,y1), ..., and (xn,yn) using constructors
 *  Polygon(x0,y0, x1,y1), Polygon(x0,y0, x1,y1, x2,y2), and so on.
 *  The main difference between a polygon and a polyline is that the
 *  polygon is closed, that is, there is a straight line from the last
 *  point (xn,yn) to the zeroth (x0,y0).
 *
 *  The example is adapted from
 *  <a href="https://www.tutorialspoint.com/javafx/2dshapes_polygon.htm">
 *  www.tutorialspoint.com</a>.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class DrawSimplePolygon extends Application{

    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Creating a line object 
        Polygon polygon = new Polygon(210,10, 10,210, 410,210); 
        /* Alternatively, create an empty polygon by
         *  Polygon polygon = new Polygon(); 
         * and then add the 3 points:
         *  polygon.getPoints().addAll(new Double[]{
         *  210.0,10.0,
         *  10.0,210.0,
         *  410.0,210.0});
         */

        // Without the following command the polygon would be fully black.
        polygon.setFill(null);
        /* After setting the fill colour to null the polygon would be
         * invisible unless the stroke colour is explicitly set.
         */
        polygon.setStroke(Color.BLACK);
         
        // Create a Group (scene graph) with the polygon as single element.
        Group root = new Group(polygon);
        // The scene consists of just one group.
        Scene scene = new Scene(root, 600, 300);

        // Give the stage (window) a title and add the scene.
        stage.setTitle("Polygon");
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
