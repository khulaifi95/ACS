import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 *  The contoller says what the actions related to ruler should be. It
 *  extends VBox, a vertical box is put on the Scene. The exact shape
 *  is read in from the ui.fxml file.
 *  
 *  Further information can be found in the oracle online tutorials, e.g.
 *  https://docs.oracle.com/javafx/2/fxml_get_started/custom_control.htm#BABDAAHE
 *  @version 2018-11-30
 *  @author Manfred Kerber
 */
public class Controller extends VBox {

    /**
     *  Three field variables are defined and they are linked to
     *  corresponding attributes in the ui.fxml file.  The variables
     *  are to represent the three components in the view, the field
     *  displaying the temperature in degrees Celsius, the slider for
     *  changing the temperature, and the field displaying the
     *  temperature in degrees Fahrenheit.
     */
    @FXML private Label celsiusField;
    @FXML private Label fahrenheitField;
    @FXML private Slider temperatureSlider;

    /**
     *  A new temperature object is created with initial temperature
     *  of 0 degrees Celsius.
     */
    private Temperature temperature = new Temperature(0);
    
    /**
     *  The constructor loads the definitions from the
     *  threeButtons.fxml file by creating an FXMLLoader.
     */
    public Controller() {
        FXMLLoader fxmlLoader =
            new FXMLLoader(getClass().getResource("ui.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            // The fxmlLoader is loaded
            fxmlLoader.load();            
            // The celsiusField is given an initial value.
            celsiusField.setText(Math.floor(10 * temperature.getCelsius())/10
                                 + " Celsius");
            // The fahrenheitField is given an initial value.
            fahrenheitField.setText(Math.floor(10 * temperature.getFahrenheit())/10 +
                                    " Fahrenheit");
            /* A listener is added to the temperatureSlider. If there
             * are changes in the position of the slider then three
             * things will happen. First, the temperature is updated
             * accordingly. Second, the celsiusApplication is
             * called. Third, the fahrenheitApplication is called.
             */
            temperatureSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                    temperature.setCelsius(temperatureSlider.getValue());
                    celsiusApplication();
                    fahrenheitApplication();
                });
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     *  The fahrenheitApplication updates the fahrenheitField to the
     *  most up-to-date value of the temperature in degrees Fahrenheit
     *  rounded down to one place after the decimal point.
     */
    public void fahrenheitApplication() {
        double value = Math.floor(10 * temperature.getFahrenheit())/10;
        fahrenheitField.setText(value + " Fahrenheit");
        
    }

    /**
     *  The celsiusApplication updates the celsiusField to the most
     *  up-to-date value of the temperature in degrees Celsius rounded
     *  down to one place after the decimal point.
     */
    public void celsiusApplication() {
        double value = Math.floor(10 * temperature.getCelsius())/10;
        celsiusField.setText(value + " Celsius");
    }

    /**
     *  The sliderApplication updates the temperature in degrees
     *  Celsius as read from the slider.
     */
    public void sliderApplication() {
        temperature.setCelsius(temperatureSlider.getValue());
    }
}
