import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.animation.RotateTransition; 
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

/**
 *  This is an example how to create and display regular polygons.
 *
 *  The example is adapted from
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_animations.htm">
 *  www.tutorialspoint.com</a>.
 *
 *  @version 2018-08-29
 *  @author Manfred Kerber
 */
public class AnimationExample extends Application{

    /**
     *  polygons must contain at least two elements, the zeroth with
     *  be rotating, the first shrinking and expanding.  We define it
     *  at a static variable so that it can be used in the start
     *  methods, but be defined in the main method.
     */
    public static Polygon[] polygons;

    /**
     *  A regular polygon with vertices number edges and corners is created.
     *  @param vertices The number of vertices (which is also the number of edges).
     *  @param rotation The counterclockwise rotation of the polygon, e.g., with Math.PI/2 the polygon is rotated by 90 degrees.
     *  @param scale Without a scale greater than 1 the whole polygon is only a single pixel.
     *  @param xOffset The amount of pixels the polygon is moved on the x-axis to the right.
     *  @param yOffset The amount of pixels the polygon is moved on the y-axis down.
     *  @return The regular polygon defined by the parameters above.
     */   
    public static Polygon makePolygon(int vertices, double rotation, double scale, double xOffset, double yOffset) {
	Double[] coordinates = new Double[2*vertices];

        /* Define a one-dimensional array that can be used to make
         * a polygon (or a polyline). In a polygon the edges are closed.
         * Here all vertices are equally distributed on a circle.
         */
	for (int i = 0; i< vertices; i++){
            coordinates[2*i] = xOffset + 
                scale * Math.cos(rotation + 2*i*Math.PI/vertices);
            coordinates[2*i+1] = yOffset + 
                scale * Math.sin(rotation + 2*i*Math.PI/vertices);
        }

        // Create an empty polygon
        Polygon polygon = new Polygon();
        // Add the points specified by the coordinates
        polygon.getPoints().addAll(coordinates);
        // Without the following command the polygon would be fully black.
        polygon.setFill(null);
        /* After setting the fill colour to null the polygon would be
         * invisible unless the stroke colour is explicitly set.
         */
        polygon.setStroke(Color.BLACK);
        return polygon;
    }
    
    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Creating a rotate transition    
        RotateTransition rotateTransition = new RotateTransition(); 
      
        //Setting the duration for the transition 
        rotateTransition.setDuration(Duration.millis(10000)); 
      
        //Setting the angle of the rotation 
        rotateTransition.setByAngle(360); 
      
        //Setting the cycle count for the transition 
        rotateTransition.setCycleCount(5); 
      
        //Setting auto reverse value to false 
        rotateTransition.setAutoReverse(false); 
      
        //Setting the nodes for the transition 
        rotateTransition.setNode(polygons[0]);       
        //Playing the animation 
        rotateTransition.play();

        //Creating scale Transition 
        ScaleTransition scaleTransition = new ScaleTransition(); 
      
        //Setting the duration for the transition 
        scaleTransition.setDuration(Duration.millis(1000)); 
      
        //Setting the node for the transition 
        scaleTransition.setNode(polygons[1]); 
      
        //Setting the dimensions for scaling 
        scaleTransition.setByY(-0.5); 
        scaleTransition.setByX(-0.5); 
      
        //Setting the cycle count for the translation 
        scaleTransition.setCycleCount(50); 
      
        //Setting auto reverse value to true 
        scaleTransition.setAutoReverse(true); 
      
  
      
        // Create a Group (scene graph) with the polygons as elements.
        Group root = new Group();
        for (Polygon polygon : polygons) {
            root.getChildren().add(polygon);
        }

        //Playing the rotate animation 
        rotateTransition.play();

        //Playing the scale animation 
        scaleTransition.play(); 
      
        // The scene consists of just one group.
        Scene scene = new Scene(root, 600, 300);

        // Give the stage (window) a title and add the scene.
        stage.setTitle("Animated Polygons");
        stage.setScene(scene);
        stage.show();
    }

    /*
     *  main method to launch the application.
     */
    public static void main(String[] args) { 
        Polygon polygon1 = makePolygon( 7, 0.0, 100.0, 130.0, 130.0);
        Polygon polygon2 = makePolygon(12, 0.3,  80.0, 330.0, 130.0);
        Polygon[] poly = {polygon1, polygon2};
        polygons = poly;
        launch(args);
    }
}
