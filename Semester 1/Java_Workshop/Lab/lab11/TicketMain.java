import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.stage.Stage;  

/** 
 *  Class with the main method to demonstrate the Ticket GUI.
 *
 *  @version 2018-12-13
 *  @author Manfred Kerber
 */
public class TicketMain extends Application { 
    private static String[] screens;
    private static int[]    prices;
    private static boolean[] screen3D;

    /**
     *  start method
     *  
     *  We use a GridPane to have two TextFields in the top row.  The
     *  next row consists of a ChoiceBox for the selection of the
     *  screen.  The final row has two buttons, one to clear the data,
     *  another to produce a printable form of the data entered.
     *  @param stage The window to be displayed.
     */
    @Override 
    public void start(Stage stage) {
        // A new controller is created with the three arrays as parameters.
        Controller control = new Controller(screens, prices, screen3D);
        
        //Creating a scene object with the contoller
        Scene scene = new Scene(control); 
       
        //Setting title of the Stage 
        stage.setTitle("Ticket Issuer"); 
         
        //Adding the scene to the stage 
        stage.setScene(scene);
      
        //Displaying the contents of the stage 
        stage.show(); 
    }

    /*
     *  main method to launch the application.
     */
    public static void main(String args[]){ 
        // The screens from which the user can choose.
        screens = new String[] {"Screen 1", "Screen 2", "Screen 3", "Isense",
                                "Screen 4", "Screen 5", "Screen 6", "Screen 7"};

        // The prices associated with the corresponding screens.
        prices = new int[] {7,8,6,9,6,6,6,10};

        // Information of whether the film shown is a 3D film or not.
        screen3D = new boolean[] {false,false,true,true,false,false,false,false};
        launch(args); 
    } 
}
