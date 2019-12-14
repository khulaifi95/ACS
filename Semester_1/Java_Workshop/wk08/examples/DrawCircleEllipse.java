import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color; 

/**
 *  This is an example how to display a circle and an ellipse.
 *
 *  The example is adapted from
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_2d_shapes.htm">
 *  www.tutorialspoint.com</a>.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class DrawCircleEllipse extends Application{

    /**
     *  A circle is defined by the coordinates of the center and the
     *  radius.
     *
     *  Likewise an ellipse is defined by the coordinates of the
     *  centre the radiusX and the radiusY.
     *
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Circle circle = new Circle(200, 150, 100);
        circle.setFill(Color.BLUE);
        Ellipse ellipse = new Ellipse(400, 150, 150, 100);
        // Without the following command the ellipse would be fully black.
        ellipse.setFill(null);
        /* After setting the fill colour to null the polygon would be
         * invisible unless the stroke colour is explicitly set.
         */
        ellipse.setStroke(Color.BLACK);

        //Creating a scene graph, consisting of the circle and the ellipse. 
        Group root = new Group(circle, ellipse);
        //The scene containing one scene graph
        Scene scene = new Scene(root, 600, 300);
        stage.setTitle("Circle and Ellipse");
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



