import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  Realization of an EventHandler. The TemperatureMain class contains
 *  a main method and the start method. It calls the Controller and
 *  creates a control object, creates a scene from it, and puts the
 *  scene on a stage which is then displayed.The controller itself is
 *  in a separate class. The view is given via an fxml file.
 *  
 *  @version 2018-11-30
 *  @author Manfred Kerber
 */
public class TemperatureMain extends Application {
    @Override
    /**
     *  The start method.
     *  @param stage The stage on which the ruler and the
     *  corresponding Celsius and Fahrenheit values are displayed.
     */
    public void start(Stage stage) throws Exception {
        Controller control = new Controller();
        stage.setScene(new Scene(control));
        stage.setTitle("Temperature");
        stage.setWidth(800);
        stage.setHeight(250);
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
