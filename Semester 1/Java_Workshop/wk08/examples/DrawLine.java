import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 *  This is an example how to display a straigh line with the
 *  two end points (x1,y1) and (x2,y2) with the constructor
 *  Line(x1,y1, x2,y2).
 *
 *  The example is adapted from
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_application.htm">
 *  www.tutorialspoint.com</a>.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class DrawLine extends Application{

    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Creating a line object 
        Line line = new Line(100,150, 500,180); 
         
        /* Alternatively, create an empty line by
         *  Line line = new Line(); 
         * and then set the 4 values:
         *  line.setStartX(100.0); 
         *  line.setStartY(150.0); 
         *  line.setEndX(500.0); 
         *  line.setEndY(180.0); 
         */
         
        // Create a Group (scene graph) with the line as single element.
        Group root = new Group(line);
        // The scene consists of just one group.
        Scene scene = new Scene(root, 600, 300);

        // Give the stage (window) a title and add the scene.
        stage.setTitle("Line");
        stage.setScene(scene);
        stage.show();
    }

    /*
     *  main method to launch the application. If not present, start
     *  will be executed.
     */
    /*    public static void main(String[] args) { 
     *   launch(args);
     *}
     */
}
