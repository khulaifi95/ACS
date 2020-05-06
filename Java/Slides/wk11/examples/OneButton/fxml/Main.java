import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  Realization of an EventHandler in the Main class with a start
 *  method, a controller, and an fxml file for the view.
 * 
 *  This class contains a start method that puts a controler on a new
 *  scene.
 *
 *  The example is adapted from the 4th edition of "Absolute Java" by
 *  Walter Savitch, p.971ff
 *  
 *  @version 2018-11-29
 *  @author Manfred Kerber
 */
public class Main extends Application {
    @Override
    /**
     *  The start method.
     *  @param stage The stage on which the button is displayed.
     */
    public void start(Stage stage) throws Exception {
        Controller endControl = new Controller();

        /* The endControl Controller is added to the scene.  Its
         * functionality is defined in the Contoller class, its view
         * is defined in the end.fxml file (which is called from the
         * Controller class).
         */
        stage.setScene(new Scene(endControl));
        stage.setTitle("End Control");
        stage.setWidth(300);
        stage.setHeight(200);
        stage.show();
    }

    /**
     *  Just launches the start method
     *  @param args Not used.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
