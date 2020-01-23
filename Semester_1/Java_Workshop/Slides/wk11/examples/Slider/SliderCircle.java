import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.shape.Circle;


/**
 *  Class that contains a slider to increase/decrease the circle from
 *  the Circle class.
 *
 *  Example adapted from Deitel and Deitel, 8th edition, p.1015-1017 to JavaFX
 *  to illustrate a slider.
 *  See also, https://docs.oracle.com/javafx/2/ui_controls/slider.htm
 *  @version 2018-11-30
 *  @author Manfred Kerber
 */
public class SliderCircle extends Application {
    @Override
    /**
     *  The start method.
     *  @param stage The stage on which the button is displayed.
     */
    public void start(Stage stage) throws Exception {
        double initialValue = 10;
        /*
         *  A slider object is created with values of 0 as minimum,
         *  100 as maximum, and an initial value.
         */
        Slider slider = new Slider(0, 100, initialValue); 
        /* The slider should ticks, a major one at ever 25 units and
         * minor ticks every 5. It should be scaled by a factor of 1.5
         * in x- and in y-direction. It should put at position (70,
         * 250) on the scene. labels
         */
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        slider.setScaleX(1.5);
        slider.setScaleY(1.5);
        slider.setTranslateX(70);
        slider.setTranslateY(250);

        //Creating a scene graph, consisting of the slider and a circle. 
        Circle circle = new Circle(140, 120, initialValue);
        Group root = new Group(slider, circle);
        //The scene containing one scene graph
        Scene scene = new Scene(root, 300, 300);
        stage.setTitle("End");
        stage.setScene(scene);
        stage.show();

        /*
         *  From the slider we read with valueProperty() its value and
         *  update the radius of the circle accordingly.  This is
         *  realized via an inner class in which the changed method is
         *  defined.
         */
        slider.valueProperty().addListener(new ChangeListener<Number>() {
                /**
                 *  The method changed updates the radius of the circle.
                 *  @param ov An observable event, that is, here
                 *  concretely the moving of the slider bar.
                 *  @param old_val The value before moving the slider.
                 *  @param new_val The value after moving the slider.
                 */
                public void changed(ObservableValue<? extends Number> ov,
                                    Number old_val, Number new_val) {
                    circle.setRadius(new_val.doubleValue());
                }
            }
            );
    }
}
