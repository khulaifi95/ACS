import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ChoiceBox; 
import javafx.scene.control.CheckBox; 
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.collections.FXCollections; 
import javafx.collections.ObservableList;

/**
 *  The contoller says what the actions related to the different
 *  elements are.  The shape of the elements on a GridPane is read in
 *  from the tickets.fxml file.  Controllers are written for the two
 *  buttons (clear and print), as well as for the ChoiceBox and the
 *  Checkbox. Details are explained in the corresponding methods.
 *
 *  @version 2019-12-13
 *  @author Manfred Kerber
 */
public class Controller extends GridPane {

    /* A new Label is created to display price of the ticket (plus
     * potentially 3D glasses. With the @FXML tag it is attached to
     * the corresponding Label in the tickets.fxml file.
     */
    @FXML private Label priceText;

    // Label for a text whether the customer needs 3D glasses.
    @FXML private Label glassesText;

    // ChoiceBox for the selection of the screen.
    @FXML private ChoiceBox<String> choiceBoxScreen;

    // A checkbox for the choice 3D glasses yes/no.
    @FXML private CheckBox glassesCheckBox;
    // Some text next to the checkBox.
    @FXML private Label checkBoxText;

    /** 
     *  The class has 4 field variables. The first is the ticket to be
     *  created.
     */
    private Ticket ticket;

    /** 
     *  The screens array contains the names of the different screens
     *  to choose from.
     */
    private String[] screens;

    /** 
     *  The prices array contains the prices for a single ticket in
     *  the corresponding screen.
     */
    private int[] prices;

    /**
     *  The screen3D say for each screen whether a 3D film is shown or not.
     */
    private boolean[] screen3D;

    /**
     *  The constructor initializes the field variable and loads the
     *  definitions from the tickets.fxml file by creating an
     *  FXMLLoader.
     *  @param screens The names of the different screens to choose
     *  from.
     *  @param prices The prices associated with the different screens.
     *  @param screen3D Information of whether the screens show 3D
     *  films or not.
     */
    public Controller(String[] screens, int[] prices, boolean[] screen3D){
    	this.screens = screens;
    	this.prices  = prices;
    	this.screen3D = screen3D;
    	this.ticket = new Ticket("",0);

        
        FXMLLoader fxmlLoader =
            new FXMLLoader(getClass().getResource("tickets.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            // The fxmlLoader is loaded
            fxmlLoader.load();
            ObservableList<String> screenList =
                FXCollections.observableArrayList(Arrays.asList(screens));
            choiceBoxScreen.setItems((ObservableList<String>) screenList);
            glassesCheckBox.setVisible(false);
            
        } catch (IOException exception) {
            /* If the loader cannot be loaded, a RuntimeException is
             * thrown.
             */
            throw new RuntimeException(exception);
        }
    }

    /** The event handler for the event of clicking the Clear Button
     * is defined by a method that does the following: It resets the
     * price to 0, the name of the screen to an empty string, the
     * choice for 3D glasses to false, It clears the selection of the
     * ChoiceBox for the screen, resets the text in the priceText
     * label to its initial value, likewise the text for the glasses
     * and checkbox. The glassesCheckBox is made invisible.
     */
    /* The tag @FXML links the method to the corresponding buttons in
     * the tickets.fxml file.
     */
    @FXML
    public void clearApplication() {
    	this.ticket.setScreen("");
    	this.ticket.setPrice(0);
    	this.ticket.setFilm3D(false);
    	this.ticket.setNeedGlasses(false);
        this.priceText.setText("TOTAL:");

        glassesCheckBox.setVisible(false);
        checkBoxText.setText("");
        glassesText.setText("");
        choiceBoxScreen.getSelectionModel().clearSelection();
        glassesCheckBox.setSelected(false);

    }

    /** The event handler for making a new choice with the
     * choiceBoxScreen updates the price, the selected screen makes
     * the CheckBox for 3D glasses and the accompanying text visible
     * if the screen is a 3D screen (and undoes this if thereafter a
     * non-3D screen is selected). Care has to be taken here, since
     * some interactions between values of variables may take place.
     */
    @FXML
    public void updateOnSelection() {
    	if (choiceBoxScreen.getSelectionModel().getSelectedItem() != null) {
            int selected = choiceBoxScreen.getSelectionModel().getSelectedIndex();
            this.ticket.setScreen(screens[selected]);
            this.ticket.setPrice(prices[selected]);
            this.ticket.setFilm3D(screen3D[selected]);
            glassesCheckBox.setSelected(false);
            this.ticket.setNeedGlasses(false);
            this.priceText.setText("TOTAL: \u00A3" + this.ticket.getPrice());
    		
            if (this.ticket.getFilm3D()) {
                checkBoxText.setText("Glasses:");
                glassesCheckBox.setVisible(true);
                glassesText.setText("Need glasses?");
            } else {
                checkBoxText.setText("");
                glassesCheckBox.setVisible(false);
                glassesText.setText("");
 			
            }
    	}
    }

    /** 
     *  The event handler for the event of clicking the
     *  glassesCheckBox updates the corresponding value in the Ticket
     *  class and updates the texts in the two TextFields.
     */
    @FXML
    public void glassesBoxChecked() {
    	if (glassesCheckBox.isSelected()) {
            this.ticket.setNeedGlasses(true);
            glassesText.setText("HAND OUT GLASSES");
    	} else {
            this.ticket.setNeedGlasses(false);
            glassesText.setText("NEED GLASSES?");
    	}
        priceText.setText("TOTAL: \u00A3" + this.ticket.getPrice());
    }

    
    /** 
     *  The event handler for the event of clicking the Print Button
     *  creates an html file to which the relevant information is
     *  written. Furthermore the firefox web browser is opened with
     *  the corresponding file and can then be printed from firefox.
     */
    @FXML
    public void printApplication() {
    	if (! this.ticket.getScreen().equals("")) {
            try {
                BufferedWriter out = 
                    new BufferedWriter(new FileWriter("ticket.html"));
                out.write("<html>\n<body>\n");
                out.write("<h1>Price: &pound;" + this.ticket.getPrice() + "</h1>\n");
                out.write("<h1>Screen: " + this.ticket.getScreen() + "</h1>\n");
                if (this.ticket.getNeedGlasses()) {
                    out.write("<h1>3D Glasses given</h1>\n");
                }
                out.write("</body>\n</html>\n");
                out.close();
                Runtime.getRuntime().exec("/usr/bin/firefox ticket.html");
            }
            catch (IOException exception) {
                System.out.print("ERROR in writing file.");
            }
    	}
    }
}
