import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.geometry.Orientation;

/**
 *  The classcontains two sliders to increase/decrease the x-radius
 *  and the y-radius of an ellipse from the Ellipse class.
 *
 *  Example adapted from Deitel and Deitel, 8th edition, p.1015-1017 to JavaFX
 *  to illustrate sliders.
 *  See also, https://docs.oracle.com/javafx/2/ui_controls/slider.htm
 *  @version 2018-11-30
 *  @author Manfred Kerber
 */
public class TwoSlider extends Application {
    @Override
    /**
     *  The start method.
     *  @param stage The stage on which the button is displayed.
     */
    public void start(Stage stage) throws Exception {
        double initialValue = 10;
        /*
         *  A slider object is created with values of 0 as minimum,
         *  100 as maximum, and an initial value. It is to
         *  represent the x-radius of the ellipse.
         */
        Slider sliderX = new Slider(0, 100, initialValue);
        /* The slider should have ticks, a major one at ever 25 units
         * and minor ticks every 5. It should be scaled by a factor of
         * 1.5 in x- and in y-direction. It should put at position
         * (70, 250) on the scene. labels
         */
        sliderX.setShowTickLabels(true);
        sliderX.setShowTickMarks(true);
        sliderX.setMajorTickUnit(25);
        sliderX.setMinorTickCount(5);
        sliderX.setBlockIncrement(10);
        sliderX.setScaleX(1.5);
        sliderX.setScaleY(1.5);
        sliderX.setTranslateX(100);
        sliderX.setTranslateY(280);

        /*
         *  A slider object is created with values of 0 as minimum,
         *  100 as maximum, and an initial value. It is to
         *  represent the y-radius of the ellipse.
         */
        Slider sliderY = new Slider(0, 100, initialValue);
        /* The slider should have ticks, a major one at ever 25 units
         * and minor ticks every 5. It should be scaled by a factor of
         * 1.5 in x- and in y-direction. It should put at position
         * (70, 250) on the scene. labels
         */
        sliderY.setShowTickLabels(true);
        sliderY.setShowTickMarks(true);
        sliderY.setMajorTickUnit(25);
        sliderY.setMinorTickCount(5);
        sliderY.setBlockIncrement(10);
        sliderY.setScaleX(1.5);
        sliderY.setScaleY(1.5);
        sliderY.setTranslateX(20);
        sliderY.setTranslateY(70);
        // The slider is positioned vertically.
        sliderY.setOrientation(Orientation.VERTICAL);

        /* Creating a scene graph, consisting of the two sliders and
         * the ellipse.
         */
        Ellipse ellipse = new Ellipse(170, 150, initialValue, initialValue);
        Group root = new Group(sliderX, sliderY, ellipse);
        //The scene containing one scene graph
        Scene scene = new Scene(root, 350, 350);
        stage.setTitle("End");
        stage.setScene(scene);
        stage.show();
    
        /*
         *  From sliderX we read with valueProperty() its value and
         *  update the x-radius of the ellipse accordingly.  This is
         *  realized via an inner class in which the changed method is
         *  defined.
         */
        sliderX.valueProperty().addListener(new ChangeListener<Number>() {
                /**
                 *  The method changed updates the radius of the circle.
                 *  @param ov An observable event, that is, here
                 *  concretely the moving of the slider bar.
                 *  @param old_val The value before moving the slider.
                 *  @param new_val The value after moving the slider.
                 */
                public void changed(ObservableValue<? extends Number> ov,
                                    Number old_val, Number new_val) {
                    ellipse.setRadiusX(new_val.doubleValue());
                }
            }
            );
        /*
         *  From sliderY we read with valueProperty() its value and
         *  update the y-radius of the ellipse accordingly.  This is
         *  realized via an inner class in which the changed method is
         *  defined.
         */
        sliderY.valueProperty().addListener(new ChangeListener<Number>() {
                /**
                 *  The method changed updates the radius of the circle.
                 *  @param ov An observable event, that is, here
                 *  concretely the moving of the slider bar.
                 *  @param old_val The value before moving the slider.
                 *  @param new_val The value after moving the slider.
                 */
                public void changed(ObservableValue<? extends Number> ov,
                                    Number old_val, Number new_val) {
                    ellipse.setRadiusY(new_val.doubleValue());
                }
            }
            );
    }
}
