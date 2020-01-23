import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color; 

/**
 *  This is a minimal example how to start a new window.
 *  We define a colour for it and give it a title.
 *
 *  The example is adapted from
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_application.htm">
 *  www.tutorialspoint.com</a>.
 *
 *  The stage (that is, the window) is very simple, it contains a
 *  scene with a scene graph consisting only of an empty root.
 *  It has a size of horizontally 600 and vertically 300 pixels.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class Minimal extends Application{

    @Override
    /**
     *  The method starts the graphics for a particular window.
     *  The constructor for the scene takes as arguments
     *  a scene graph, the width (in pixels), and the height (in pixels).
     *  @param stage The window to be displayed.
     */
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 300);
        // Scene scene = new Scene(root);
        
        stage.setTitle("Minimal");
        stage.setScene(scene);
        scene.setFill(Color.RED);
        stage.show();
    }
    
    /*
     *  main method to launch the application.
     */
    public static void main(String[] args) { 
        launch(args);
    }
}
