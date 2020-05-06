import java.util.function.Function;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color; 
import javafx.scene.text.Text; 
import javafx.scene.text.Font; 
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
    
/** 
 *  The application is to play a game of memory. It is made of a
 *  NUMBER_OF_ROWS and a NUMBER_OF_COLUMNS. To each position there is
 *  one picture associated, each picture exactly two times. The
 *  product of NUMBER_OF_ROWS and NUMBER_OF_COLUMNS must be even,
 *  otherwise one image is unmatched. The whole application depends on
 *  the presence of images 0.jpg, 1.jpg, ... up to NUMBER_OF_ROWS *
 *  NUMBER_OF_COLUMNS/2 in a resource folder.
 *
 *  @version 2018-12-08
 *  @author Manfred Kerber
 */

public class Memory extends  Application {
    // Some static variables
    // The number of rows in the Memory.
    public static final int NUMBER_OF_ROWS = 4;

    // The number of columns in the Memory.
    public static final int NUMBER_OF_COLUMNS = 4;

    // The width of a single tile.
    public static final int SMALL_WIDTH = 150;

    // The height of a single tile.
    public static final int SMALL_HEIGHT = 150;

    /* The total number of tiles in the Memory, it must be an even
     * number, otherwise one tile will be unmatched.
     */
    public static final int NUMBER_OF_TILES =
        NUMBER_OF_ROWS * NUMBER_OF_COLUMNS; 

    // The total number of horizontal pixels in the Memory.
    public static final int WIDTH = SMALL_WIDTH * NUMBER_OF_COLUMNS;

    // The total number of vertical pixels in the Memory.
    public static final int HEIGHT = SMALL_HEIGHT * NUMBER_OF_ROWS;
    
    // We see a defaultImage before a card is turned over.
    public static final ImagePattern defaultImage =
        new ImagePattern(new Image("memory/default.jpg"));

    /* The array contains the order in which the images will be
     * associated to rectangles.
     */
    private static int[] imageOrder = new int[NUMBER_OF_TILES];

    /* The array contains ImagePatterns associated with the 
     * NUMBER_OF_ROWS * NUMBER_OF_COLUMNS many rectangles.
     */
    private static ImagePattern[][] imagePatterns =
        new ImagePattern[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];

    /** 
     *  The method first determines the order in which the images
     *  should be displayed, each image will be displayed exactly two
     *  times. The array imagePatterns is populated with the
     *  corresponding ImagePatterns.
     */
    public static void randomizeImages() {
        /* There are only half as many images than number of
         * puzzle tiles, since every image has to occur exactly
         * two times. That is, each image is added twice, e.g., in
         * images[0] and images[1]. Later they are shuffled.
         */
        for (int i = 0; i < NUMBER_OF_TILES; i++) {
            imageOrder[i] = i/2;
        }
        /* Shuffle the images. Down to number i-1 the images
         * are already randomly selected after leaving the loop.
         */
        for (int i = NUMBER_OF_TILES; i > 0; i--) {
            // Select a random number between 0 incl. and i excl.
            int selected = (int) (i * Math.random());

            // Swap the last element with the randomly chosen one.
            int tmp = imageOrder[i-1];
            imageOrder[i-1] = imageOrder[selected];
            imageOrder[selected] = tmp;
        }
        /* Add the imagePatterns in the order given by the shuffling
         * above to the imagePatterns array.
         * Do this row by row
         */
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            // Add in each row, column by column.
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                imagePatterns[i][j] = new ImagePattern(
                     new Image("memory/" +
                               imageOrder[i * NUMBER_OF_COLUMNS + j]
                               + ".jpg"));
            }
        }
    }

    /**
     *   The method changes the picture showing on the rectangle
     *   between the default image and the image associated with the
     *   position of the rectangle.
     *   @param rectangle The rectangle on which the image is displayed.
     *   @param i The row position of the rectangle.
     *   @param j The column position of the rectangle.
     */
    public static void toggle(Rectangle rectangle, int i, int j) {
        if (rectangle.getFill().equals(defaultImage)) {
            rectangle.setFill(imagePatterns[i][j]);
        } else {
            rectangle.setFill(defaultImage);
        }
    }
    
    /**
     *  The method adds rectangles to the root, showing initally the
     *  defaultImage. On clicking on a tile the defaultImage and the
     *  picture associated with the tile are toggled.
     *  @param root The root to which the rectangles are added.
     */
    public static void addTiles(Group root) {
        // For each row we do
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            // For each column we
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                // create a rectangle at the corresponding position.
                Rectangle rectangle = new Rectangle(j * SMALL_WIDTH,
                                                    i * SMALL_HEIGHT,
                                                    SMALL_WIDTH,SMALL_HEIGHT);
                // We put a border around the rectangle.
                rectangle.setStroke(Color.BLACK);
                // We associate the rectangle with the default image.
                rectangle.setFill(defaultImage);
                /* We create two auxiliary variables, since in the
                 * function below only final variables are allowed.
                 */
                final int fi = i;
                final int fj = j;
                // We create an event handler for clicking on the rectangle.
                final EventHandler<MouseEvent> clickHandle = e -> {
                    /* The variables fi and fj state which picture the
                     * image should be toggled to, assumed the default
                     * image is showing. If another image is
                     * displayed, it will be toggled to the default
                     * image.
                     */
                    toggle(rectangle, fi, fj);
                };
                // The handle is added to the rectangle.
                rectangle.setOnMouseClicked(clickHandle);
                // The rectangle is added to the root.
                root.getChildren().add(rectangle);
            }
        }
    }

    /**
     *  start method
     * 
     *  The method first randomizes the Memory from the NUMBER_OF_ROWS
     *  and NUMBER_OF_COLUMNS variables by allocating randomly
     *  positions to the images in the memory directory. It displays
     *  rectangles with a default image. Furthermore, handlers are
     *  added to the rectangles: on click the image displayed toggles
     *  between the default image and the image allocated to the
     *  position. The root is added to a scene, the scene to a stage.
     *  The whole application depends on the presence of images 0.jpg,
     *  1.jpg, ... up to NUMBER_OF_ROWS * NUMBER_OF_COLUMNS/2. The
     *  product has to be even.
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Create a Group (scene graph) to which the Memory will be added.
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        randomizeImages();
        addTiles(root);

        // Give the stage (window) a title and add the scene.
        stage.setTitle("Memory");
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
