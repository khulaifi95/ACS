import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color; 


/**
 * In this class, we test predefined colours. Some are predefined by
 * constants such such as BLACK, RED and so on. They can also be
 * defined by Color(r,g,b) where r,g,b are values between 0 and
 * 255. r=red, g=green, and b=blue. 0,0,0 stands for black, 255,0,0
 * for red, 0,255,0 for green, and 0,0,255 blue with other values in
 * between. Values such as 100,100,100 represent different levels of grey.
 *
 *  The example is adapted from
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_colors.htm">
 *  www.tutorialspoint.com</a>.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class Colour extends Application{

    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Setting text colours to blue, red, and green.
        Text blue = new Text(20.0,30.0, "Blue");
        Text red = new Text(20.0,70.0, "Red");
        Text green = new Text(20.0,110.0, "Green");
        blue.setFill(Color.BLUE); blue.setFont(Font.font("verdana", 30));
        red.setFill(Color.RED); red.setFont(Font.font("verdana", 30));
        green.setFill(Color.GREEN); green.setFont(Font.font("verdana", 30));
        // Create a Group (scene graph) with the text as single element.
        Group root = new Group(blue,red,green);

        int[]      r = {0,255,  0,  0,255,255,  0,255,255,255,200,128, 64, 32,164};
        int[]      g = {0,  0,255,  0,200,175,255,  0,255,255,200,128, 64, 32,255};
        int[]      b = {0,  0,  0,255,  0,175,255,255,  0,255,200,128, 64, 32, 64};
        boolean[] bg = {false,false,false,false,false,false,false,false,true,true,false,false,false,false,false};

        for (int i = 0; i < 15; i++){
            Text text = new Text(120.0,30.0+ 40*i, String.format("rgb(%d,%d,%d)", r[i], g[i], b[i]));
            Color colour = Color.rgb(r[i],g[i],b[i]);
            text.setFill(colour); text.setFont(Font.font("verdana", 30));
            if (bg[i]) {
                Rectangle rect = new Rectangle(120.0,40*i, 2000, 40);
                rect.setFill(Color.BLACK);
                root.getChildren().add(rect);

            }
            root.getChildren().add(text);
        }

         
        // The scene consists of just one group.
        Scene scene = new Scene(root, 600, 700);

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

     
