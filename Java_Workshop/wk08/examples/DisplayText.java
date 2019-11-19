import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight;

/**
 *  This is an example of how to add text to a group.
 *
 *  The example is adapted from
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_text.htm">
 *  www.tutorialspoint.com</a>.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class DisplayText extends Application{

    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Creating a text object at position (100,150)
        Text text = new Text(100.0,150.0, "Hello World");

        //Changing the font to "verdana" at a size of 70 pt
        text.setFont(Font.font("verdana", 70));

        /* FontWeight accepts nine values: BLACK,BOLD,EXTRA_BOLD,
         * EXTRA_LIGHT,MEDIUM, NORMAL, SEMI_BOLD, and THIN. 
         * FontPosture two values: REGULAR and ITALIC.
         */
        //text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 100));
        //The text gets a horizontal line in the middle through it.
        //text.setStrikethrough(true);

        //The text is underlined.
        text.setUnderline(true); 
         
        // Create a Group (scene graph) with the text as single element.
        Group root = new Group(text);
        // The scene consists of just one group.
        Scene scene = new Scene(root, 900, 300);

        // Give the stage (window) a title and add the scene.
        stage.setTitle("Text");
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
