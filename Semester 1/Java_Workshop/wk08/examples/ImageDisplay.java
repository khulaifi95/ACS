import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *  This is an example how to display an image
 *
 *  The example is adapted from
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_images.htm">
 *  www.tutorialspoint.com</a>.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class ImageDisplay extends Application{

    /**
     *  The static variable contains the image to be displayed, either
     *  as a file name or a fully specified URL. We use a variable
     *  here so that it can be used in the start method, but will be
     *  initialized in the main method only.
     */
    private static Image image;
    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Setting the image view 
        ImageView imageView = new ImageView(image); 
        imageView.setX(150); 
        imageView.setY(100);       

        // Create a Group (scene graph) with the image as single element.
        Group root = new Group(imageView);
        // The scene consists of just one group.
        Scene scene = new Scene(root, 900, 900);

        // Give the stage (window) a title and add the scene.
        stage.setTitle("Image");
        stage.setScene(scene);
        stage.show();
    }

    /*
     *  main method to launch the application.
     */
    public static void main(String[] args) { 
        //Initializing the image 
        image = new Image("images/firstCar.jpg");
        //image = new Image("http://www.cs.bham.ac.uk/~mmk/pictures/kerber.jpg");
        launch(args);
    }
}
