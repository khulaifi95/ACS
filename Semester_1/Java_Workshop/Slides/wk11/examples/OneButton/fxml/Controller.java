import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;


/**
 *  In writing this class use was made of Oracle's online tutorial on
 *  fxml, see
 *  https://docs.oracle.com/javafx/2/fxml_get_started/custom_control.htm#BABDAAHE
 *  The Controller class extends a VBox, that is, a vertical box that
 *  defines the display. Details of the display can be found in the
 *  end.fxml file.
 * 
 *  @version 2018-11-29
 *  @author Manfred Kerber
 */
public class Controller extends VBox {

    /**
     *  Standard constructor
     */
    public Controller() {
        /*
         *  The end.fxml is loaded into the class. The root and the
         *  controller are set via the fxml file.
         */
        FXMLLoader fxmlLoader =
            new FXMLLoader(getClass().getResource("end.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            /* It is tried to load the loader. If this fails a
             * RuntimeException is thrown.
             */
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
        
    @FXML
    /**
     *  The method that is called in case of a MouseEvent on the
     *  Button executes the two commands to firstly print something
     *  and secondly to terminate the program.
     */
    public void endApplication() {
        System.out.println("This is the end."); 
        System.exit(1);
    }
}
