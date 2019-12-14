import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 

import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

/**
 *  This class shows a very simple login page with username and
 *  password fields as TextField and PasswordField and a submit and
 *  clear button.
 *
 *  See also:
 *  <pre>
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_ui_controls.htm">https://www.tutorialspoint.com/javafx/javafx_ui_controls.htm</a>
 *  </pre>
 */
         
public class LoginPage extends Application { 
    /**
     *  start method
     * 
     *  The fields and buttons are added to a GridPane, which is added
     *  to the Scene.
     *  @param stage The window to be displayed.
     */
    @Override 
    public void start(Stage stage) {      
       
        Text usernameText = new Text("Username");       
      
        //creating label password 
        Text passwordText = new Text("Password"); 
       
        //Creating text field for userame
        TextField usernameTextField = new TextField();       
      
        //Creating text field for password        
        PasswordField passwordTextField = new PasswordField();  
       
        //Creating buttons 
        Button submitButton = new Button("Submit"); 
        Button clearButton = new Button("Clear");  

        /* The event handler for the event of clicking the endButton is
         * defined by a constant function that maps an event to two
         * commands: printing out the final value of the counter to the
         * command line and exiting the program.
         */
        final EventHandler<MouseEvent> eventHandlerClear = e -> {
            passwordTextField.setText("");
            usernameTextField.setText("");
        };
        clearButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerClear);

        final EventHandler<MouseEvent> eventHandlerSubmit = e -> {
            System.out.println(usernameTextField.getText() + " tried to log in with the password " + passwordTextField.getText());
            System.exit(1);
        };
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerSubmit);
        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    
      
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        gridPane.setPadding(new Insets(10, 10, 10, 10));       
      
        //Adding the nodes to the grid 
        gridPane.add(usernameText, 0, 0); 
        gridPane.add(usernameTextField, 1, 0); 
        gridPane.add(passwordText, 0, 1);       
        gridPane.add(passwordTextField, 1, 1); 
        gridPane.add(clearButton, 0, 2); 
        gridPane.add(submitButton, 1, 2); 
       
        //Creating a scene object 
        Scene scene = new Scene(gridPane); 
       
        //Setting title to the Stage 
        stage.setTitle("Login page"); 
         
        //Adding scene to the stage 
        stage.setScene(scene);
      
        //Displaying the contents of the stage 
        stage.show(); 
    }      

    /*
     *  main method to launch the application.
     */
    public static void main(String args[]){ 
        launch(args); 
    } 
}
