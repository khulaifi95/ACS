import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color; 

/**
 *  This is an example of to display a two-dimensional array of zeroes, ones, and twos as big coloured dots.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class Grid extends Application{
    /**
     *  grid is the static variable that is used to store the
     *  2-dimensional array to be displayed by coloured dots.
     */
    private static int[][] grid;
    /** OFFSET moves the board on the x-axis and y-axis out of the left upper corner.
     *  It is also used as the distance between the centres of two adjacent circles.
     */
    public static final int OFFSET = 90;
    /** RADIUS is the radius of the circles.
     */
    public static final int radius = 40;

    /**
     *  @param stage The window to be displayed.
     */
   @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        for (int i = 0; i< grid.length; i++) {
            for (int j = 0; j< grid[i].length; j++) {
                Circle circle = new Circle(OFFSET+j*OFFSET,OFFSET+i*OFFSET, radius);
                if (grid[i][j] == 1) {
                    circle.setFill(Color.GREEN);
                } else if (grid[i][j] == 2) {
                    circle.setFill(Color.RED);
                }
                root.getChildren().add(circle);
            }
        }

        //Creating a scene graph, consisting of the circles. 
        //The scene containing one scene graph
        int SCENE_WIDTH  = OFFSET + OFFSET * (grid[0].length + 1);
        int SCENE_HEIGHT = OFFSET + OFFSET * (grid.length + 2);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("Dots");
        stage.setScene(scene);
        stage.show();
    }

    /*
     *  main method to initialize a matrix and to launch the application.
     */
    public static void main(String[] args) {
                int[][] grid1 = {{0,1,0,1,1},
                                 {1,2,0,0,1},
                                 {1,1,0,1,2,1}};
                grid = grid1;
        launch(args);
     }
}
