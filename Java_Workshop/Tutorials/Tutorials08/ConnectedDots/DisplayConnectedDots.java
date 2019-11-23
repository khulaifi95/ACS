import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *  The class displays two rows of dots and connects all dots in the
 *  upper row with all dots in the lower row.
 *
 *  @version 2018-11-28
 *  @author Alexandros Evangelidis, Manfred Kerber
 */
public class DisplayConnectedDots extends Application{
    public static final int xSize = 1000;//x dimension of the scence
    public static final int ySize =  600;//y dimension of the scene

    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
       int m = 7;         //number of dots in the upper row
       int n = 3;         //number of dots in the lower row
       double radius = 40;//radius of all the dots

       ConnectedDots cd = new ConnectedDots(m, n, radius, xSize, ySize);
       
       Group root = new Group();
       // For each dot in the lower row a corresponding circle is added to the the group.
       for (Circle circle : cd.downRowCircles()) {
           root.getChildren().add(circle);
       }
       // For each dot in the upper row a corresponding circle is added to the the group.
       for (Circle circle : cd.upperRowCircles()) {
           root.getChildren().add(circle);
       }

       /*
        *  The loop invariant is that each dot in upper row visited so
        *  far is connected to all dots in the lower row.
        */
       for (Circle circleUp : cd.upperRowCircles()) {
           /*
            *  The loop invariant is that each dot in the lower row visited so
            *  far is connected to circleUp.
            */
           for (Circle circleDown : cd.downRowCircles()) {
               /* 
                *  A line is created with the endpoints as the centres
                *  of circleUp and circleDown.
                */
               Line line = new Line(circleUp.getCenterX(),circleUp.getCenterY(),
                                    circleDown.getCenterX(),circleDown.getCenterY());
               //The line is added to the group.
               root.getChildren().add(line);
           }
       }
        // The scene consists of just one group.
        Scene scene = new Scene(root, xSize, ySize);
        
        // Give the stage (window) a title and add the scene.
        stage.setTitle("Connected Dots");
        stage.setScene(scene);
        stage.show();
    }
}
