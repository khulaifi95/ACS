import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  fxml approach
 *  
 *  In this class the main method and the start method set off the
 *  application.  The Controller is kept in a separate class, the view
 *  is defined in an .fxml file.
 * 
 *  @version 2018-11-30
 *  @author Manfred Kerber
 */
public class ThreeButtonsMain extends Application {
    @Override
    /**
     *  The start method.
     *  @param stage The stage on which the button is displayed.
     */
    public void start(Stage stage) throws Exception {
        Controller control = new Controller();
        
        stage.setScene(new Scene(control));
        stage.setTitle("Counter");
        stage.setWidth(300);
        stage.setHeight(200);
        stage.show();
    }
    
    /**
     *  main method to call the start method.
     *  @param args Passed on to launch. Not used in the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
