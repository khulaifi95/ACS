import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.collections.FXCollections; 
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Insets;


import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.layout.VBox; 
import javafx.scene.layout.HBox; 
import javafx.scene.control.TextField; 
import javafx.scene.control.DatePicker; 
import javafx.scene.control.RadioButton; 
import javafx.scene.control.ToggleGroup; 
import javafx.scene.control.CheckBox; 
import javafx.scene.control.ChoiceBox; 
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ListView; 
import javafx.scene.text.Text; 
import javafx.scene.text.Font; 
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;  
import javafx.event.EventHandler;

/**
 *  In this class introduces eight controls: TextField, PasswordField,
 *  DatePicker, RadioButton, CheckBox, ToggleButton, ListView, and
 *  ChoiceBox as well as two Buttons.
 *
 *  See also 
 *  <pre>
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/TextField.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/TextField.html</a>
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/PasswordField.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/PasswordField.html</a>  
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/DatePicker.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/DatePicker.html</a>
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/RadioButton.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/RadioButton.html</a>
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/CheckBox.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/CheckBox.html</a>
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/ToggleButton.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/ToggleButton.html</a>  
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/ListView.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/ListView.html</a>
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/ChoiceBox.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/control/ChoiceBox.html</a>
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_ui_controls.htm">https://www.tutorialspoint.com/javafx/javafx_ui_controls.htm</a>
 *  </pre>
 *
 *  @version 2018-12-10
 *  @author  Manfred Kerber
 */
public class Controls extends Application { 
    /**
     *  start method
     *  
     *  We use a GridPane to present eight different types of GUI
     *  controls: TextField, PasswordField, DatePicker, RadioButton,
     *  CheckBox, ToggleButton, ListView, and ChoiceBox; as well as
     *  two Buttons.
     *  @param stage The window to be displayed.
     */
    @Override 
    public void start(Stage stage) {      
        // The GridPane is created and populated with different
        // control elements.
        GridPane gridPane = new GridPane();    
        Font titleFont = Font.font("roman", 30);
        /* Add vertical and horizontal gaps between the columns and
         * padding around the fields.
         */
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        gridPane.setPadding(new Insets(10, 10, 10, 10));       
        gridPane.setStyle("-fx-background-color: red; -fx-padding: 20; -fx-grid-lines-visible: true");

        // A TextField is added to the GridPane
        Text title1 = new Text("TextField");
        title1.setFont(titleFont);
        TextField textField = new TextField();
        textField.setFont(Font.font("roman", 30));
        VBox vBox1 = new VBox(30);
        vBox1.getChildren().add(title1);
        vBox1.getChildren().add(textField);
        // The VBox is given a yellow background and some padding.
        vBox1.setStyle("-fx-background-color: yellow; -fx-padding: 10");
        gridPane.add(vBox1, 0, 0); // Column, Row


        // A PasswordField is added to the GridPane
        Text title2 = new Text("PasswordField");
        title2.setFont(titleFont);
        PasswordField passwordField = new PasswordField();
        passwordField.setFont(Font.font("roman", 30));
        VBox vBox2 = new VBox(30);
        vBox2.getChildren().add(title2);
        vBox2.getChildren().add(passwordField);
        // The VBox is given a yellow background and some padding.
        vBox2.setStyle("-fx-background-color: yellow; -fx-padding: 10");
        gridPane.add(vBox2, 1, 0); // Column, Row

        // A date picker is added to the GridPane
        Text title3 = new Text("DatePicker");
        title3.setFont(titleFont);
        DatePicker datePicker = new DatePicker();
        VBox vBox3 = new VBox(30);
        vBox3.getChildren().add(title3);
        vBox3.getChildren().add(datePicker);
        // The VBox is given a yellow background and some padding.
        vBox3.setStyle("-fx-background-color: yellow; -fx-padding: 10");
        gridPane.add(vBox3, 0, 1); // Column, Row

        //Toggle group of radio buttons       
        Text title4 = new Text("RadioButton");
        title4.setFont(titleFont);
        VBox vBox4 = new VBox(30);
        vBox4.getChildren().add(title4);
        ToggleGroup groupClass = new ToggleGroup(); 
        RadioButton standardRadio = new RadioButton("STANDARD");
        standardRadio.setUserData("STANDARD");
        standardRadio.setFont(Font.font("roman", 20));
        standardRadio.setToggleGroup(groupClass); 
        RadioButton firstRadio = new RadioButton("FIRST"); 
        firstRadio.setUserData("FIRST");
        firstRadio.setToggleGroup(groupClass); 
        firstRadio.setFont(Font.font("roman", 20));
        RadioButton premiumRadio = new RadioButton("PREMIUM"); 
        premiumRadio.setUserData("PREMIUM");
        premiumRadio.setToggleGroup(groupClass);
        premiumRadio.setFont(Font.font("roman", 20));
        HBox hbox = new HBox(30);
        hbox.getChildren().add(standardRadio);
        hbox.getChildren().add(firstRadio);
        hbox.getChildren().add(premiumRadio);
        vBox4.setStyle("-fx-background-color: yellow; -fx-padding: 30");
        vBox4.getChildren().add(hbox);
        gridPane.add(vBox4, 1, 1); // Column, Row


        //CheckBox
        Text title5 = new Text("CheckBox");
        title5.setFont(titleFont);
        VBox vBox5 = new VBox(30);
        vBox5.getChildren().add(title5);
        CheckBox checkBox = new CheckBox("Newspaper?");
        checkBox.setFont(Font.font("roman", 30));
        vBox5.getChildren().add(checkBox);
        vBox5.setStyle("-fx-background-color: yellow; -fx-padding: 10");
        gridPane.add(vBox5, 0, 2); // Column, Row

        //ToggleButton
        Text title6 = new Text("ToggleButton");
        title6.setFont(titleFont);
        VBox vBox6 = new VBox(30);
        vBox6.getChildren().add(title6);
        ToggleButton windowSeat = new ToggleButton("Window Seat");
        windowSeat.setFont(Font.font("roman", 30));
        windowSeat.setUserData("Window Seat");
        ToggleButton aisleSeat = new ToggleButton("Aisle Seat");
        aisleSeat.setFont(Font.font("roman", 30));
        aisleSeat.setUserData("Aisle Seat");
        HBox hbox6 = new HBox(30);
        hbox6.getChildren().add(windowSeat);
        hbox6.getChildren().add(aisleSeat);
        vBox6.getChildren().add(hbox6);
        ToggleGroup windowAisleToggleGroup = new ToggleGroup(); 
        aisleSeat.setToggleGroup(windowAisleToggleGroup);
        windowSeat.setToggleGroup(windowAisleToggleGroup);
        vBox6.setStyle("-fx-background-color: yellow; -fx-padding: 10");
        gridPane.add(vBox6, 1, 2); // Column, Row

        //ListView
        Text title7 = new Text("ListView");
        title7.setFont(titleFont);
        VBox vBox7 = new VBox(30);
        vBox7.getChildren().add(title7);
        ObservableList<String> destinations = FXCollections.observableArrayList(
         "Leeds", "Lincoln", "Liverpool", "Llandudno", "London", "Loughborough");
        ListView<String> listView = new ListView<String>(destinations);
        vBox7.getChildren().add(listView);
        vBox7.setStyle("-fx-background-color: yellow; -fx-padding: 10");
        gridPane.add(vBox7, 0, 3); // Column, Row

        //ChoiceBox
        Text title8 = new Text("ChoiceBox");
        title8.setFont(titleFont);
        VBox vBox8 = new VBox(30);
        vBox8.getChildren().add(title8);
        ChoiceBox timeChoiceBox =
            //see: https://docs.oracle.com/javafx/2/ui_controls/choice-box.htm
            new ChoiceBox<String>(FXCollections.observableArrayList(
                "6:00", "8:00", "10:00", "12:00", "14:00", "16:00"));
        vBox8.getChildren().add(timeChoiceBox);
        vBox8.setStyle("-fx-background-color: yellow; -fx-padding: 10");
        gridPane.add(vBox8, 1, 3); // Column, Row

        /* An event handler is defined that prints out the values read
         * from the GUI controls.
         */
        Button submitButton = new Button("Submit");
        final EventHandler<MouseEvent> eventHandlerSubmit = e -> {
            if (!textField.getText().equals("")) {
                System.out.println(textField.getText());
            }
            if (!passwordField.getText().equals("")) {
                System.out.println(passwordField.getText());
            }
            if (datePicker.getValue() != null) {
                System.out.println(datePicker.getValue());
            }
            //see https://stackoverflow.com/questions/32424915/how-to-get-selected-radio-button-from-togglegroup
            if (groupClass.getSelectedToggle() != null) {
                System.out.println(groupClass.getSelectedToggle().getUserData());
            }
            if (checkBox.isSelected()) {
                System.out.println("Newspaper");
            }
            if (windowAisleToggleGroup.getSelectedToggle() != null) {
                System.out.println(windowAisleToggleGroup.getSelectedToggle().getUserData());
            }
            //see https://stackoverflow.com/questions/13264017/getting-selected-element-from-listview
            if (listView.getSelectionModel().getSelectedItem() != null) {
                System.out.println(listView.getSelectionModel().getSelectedItem());
            }
            if (timeChoiceBox.getSelectionModel().getSelectedItem() != null) {
                System.out.println(timeChoiceBox.getSelectionModel().getSelectedItem());
            }
        };
        // The event handler is associated with the button.
        submitButton.setOnMouseClicked(eventHandlerSubmit);
        gridPane.add(submitButton, 0, 4); // Column, Rox

        /* An event handler is defined that clears all the values set
         * from the GUI controls.
         */
        Button clearButton = new Button("Clear");
        final EventHandler<MouseEvent> eventHandlerClear = e -> {
            textField.setText("");
            passwordField.setText("");
            datePicker.getEditor().clear();
            datePicker.setValue(null);
            standardRadio.setSelected(false);
            firstRadio.setSelected(false);
            premiumRadio.setSelected(false);
            checkBox.setSelected(false);
            windowAisleToggleGroup.selectToggle(null);
            //see https://stackoverflow.com/questions/24206854/javafx-clearing-the-listview
            listView.getSelectionModel().clearSelection();
            timeChoiceBox.getSelectionModel().clearSelection();
        };
        // The event handler is associated with the button.
        clearButton.setOnMouseClicked(eventHandlerClear);
        gridPane.add(clearButton, 1, 4); // Column, Row
        
        //Creating a scene object
        Scene scene = new Scene(gridPane); 
       
        //Setting title of the Stage 
        stage.setTitle("GUI Controls"); 
         
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
