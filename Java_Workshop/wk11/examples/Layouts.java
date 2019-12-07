import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 

import javafx.scene.Scene; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox; 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  
         
/**
 *  The class is to show four different layouts, HBox, VBox,
 *  BorderPane, and GridPane.
 *
 *  <pre>
 *  For more details, see
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/layout/HBox.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/layout/HBox.html</a>
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/layout/VBox.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/layout/VBox.html</a>
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/layout/BorderPane.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/layout/BorderPane.html</a>
 *  <a href="https://docs.oracle.com/javase/10/docs/api/javafx/scene/layout/GridPane.html">https://docs.oracle.com/javase/10/docs/api/javafx/scene/layout/GridPane.html</a>
 *  <a href="https://www.tutorialspoint.com/javafx/javafx_layout_panes.htm">https://www.tutorialspoint.com/javafx/javafx_layout_panes.htm</a>
 *  </pre>
 *
 *  @version 2018-12-10
 *  @author  Manfred Kerber
 */
public class Layouts extends Application { 
    /**
     *  start method 
     *
     *  An HBox is put on the scene with a TextField and two VBoxes
     *  inside, one with a BorderPane and one with a GridPane.
     *  @param stage The window to be displayed.
     */
    @Override 
    public void start(Stage stage) {      

        // A new HBox is created to which a title and two VBoxes will be added.
        Text titleHBox = new Text("HBox");
        titleHBox.setFont(Font.font("roman", 70));
        HBox hbox = new HBox(30);
        hbox.getChildren().add(titleHBox);
        // The HBox is given a blue background and some padding.
        hbox.setStyle("-fx-background-color: blue; -fx-padding: 10");

        // The first VBox is created to which a BorderPane will be added.
        Text titleVBox1 = new Text("VBox1");
        titleVBox1.setFont(Font.font("roman", 70));
        Text addVBox1 = new Text("containing a BorderPane");
        addVBox1.setFont(Font.font("roman", 30));
        VBox vbox1 = new VBox(30);
        vbox1.getChildren().add(titleVBox1);
        vbox1.getChildren().add(addVBox1);
        // The VBox is given a red background and some padding.
        vbox1.setStyle("-fx-background-color: red; -fx-padding: 10");

        /* The BorderPane is created and populated with the five text
         * elements left, right, top, bottom, and center.
         */
        BorderPane borderPane = new BorderPane();
        Text textLeft = new Text("Left");
        textLeft.setFont(Font.font("roman", 30));
        Text textRight = new Text("Right");
        textRight.setFont(Font.font("roman", 30));
        Text textTop = new Text("Top");
        textTop.setFont(Font.font("roman", 30));
        Text textBottom = new Text("Bottom");
        textBottom.setFont(Font.font("roman", 30));
        Text textCenter = new Text("Center");
        textCenter.setFont(Font.font("roman", 30));
        borderPane.setLeft(textLeft);
        borderPane.setRight(textRight);
        borderPane.setTop(textTop);
        borderPane.setBottom(textBottom);
        borderPane.setCenter(textCenter);
        // The background of the BorderPane is set to yellow.
        borderPane.setStyle("-fx-background-color: yellow");
        // Distances are added to the four outer element of the BorderPane.
        borderPane.setMargin(borderPane.getLeft(), new Insets(10,10,10,10));
        borderPane.setMargin(borderPane.getRight(), new Insets(10,10,10,10));
        borderPane.setMargin(borderPane.getTop(), new Insets(10,10,10,10));
        borderPane.setMargin(borderPane.getBottom(), new Insets(10,10,10,10));
        // The BorderPane is given a preferred size.
        borderPane.setPrefSize(200,200);
        // The four outer elements are centred.
        borderPane.setAlignment(borderPane.getTop(),    Pos.TOP_CENTER);
        borderPane.setAlignment(borderPane.getBottom(), Pos.BOTTOM_CENTER);
        borderPane.setAlignment(borderPane.getLeft(),   Pos.CENTER_LEFT);
        borderPane.setAlignment(borderPane.getRight(),  Pos.CENTER_RIGHT);
        // The BorderPane is added to vbox1.
        vbox1.getChildren().add(borderPane);
        // vbox1 is added to hbox.
        hbox.getChildren().add(vbox1);

        // The second VBox is created to which a GidPane will be added.
        Text titleVBox2 = new Text("VBox2");
        titleVBox2.setFont(Font.font("roman", 70));
        Text addVBox2 = new Text("containing a GridPane");
        addVBox2.setFont(Font.font("roman", 30));
        VBox vbox2 = new VBox(30);
        vbox2.getChildren().add(titleVBox2);
        vbox2.getChildren().add(addVBox2);
        vbox2.setStyle("-fx-background-color: red; -fx-padding: 10; -fx-grid-lines-visible: true");
        hbox.getChildren().add(vbox2);

        // The GridPane is created and populated with 2x3 elements.
        GridPane gridPane = new GridPane();    
      
        /* Add vertical and horizontal gaps between the columns and
         * padding around the fields.
         */
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        gridPane.setPadding(new Insets(10, 10, 10, 10));       
        gridPane.setStyle("-fx-background-color: yellow; -fx-padding: 20; -fx-grid-lines-visible: true");
        
        //Adding the nodes to the grid. First the texts are created.
        Text textGridPane00 = new Text("Some");
        textGridPane00.setFont(Font.font("roman", 70));
        Text textGridPane01 = new Text("for the");
        textGridPane01.setFont(Font.font("roman", 70));
        Text textGridPane02 = new Text("of a");
        textGridPane02.setFont(Font.font("roman", 70));
        Text textGridPane10 = new Text("text");
        textGridPane10.setFont(Font.font("roman", 70));
        Text textGridPane11 = new Text("fields of");
        textGridPane11.setFont(Font.font("roman", 70));
        Text textGridPane12 = new Text("GridPane");
        textGridPane12.setFont(Font.font("roman", 70));
        /*  Note that the order to add nodes to a GridPane is Column,
         *  Row, that is, different from an array, where the order is
         *  row, column.
         */
        gridPane.add(textGridPane00, 0, 0); // Column, Row
        gridPane.add(textGridPane10, 1, 0); // Column, Row
        gridPane.add(textGridPane01, 0, 1); // Column, Row
        gridPane.add(textGridPane11, 1, 1); // Column, Row
        gridPane.add(textGridPane02, 0, 2); // Column, Row
        gridPane.add(textGridPane12, 1, 2); // Column, Row
        // The GridPane is added to vbox2.
        vbox2.getChildren().add(gridPane);
       
        //Creating a scene object 
        Scene scene = new Scene(hbox); 
       
        //Setting title of the Stage 
        stage.setTitle("Layouts"); 
         
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
