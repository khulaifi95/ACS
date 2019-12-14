import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color; 
import javafx.scene.text.Text;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight;


/**
 *  We produce a BarChart out of an ArrayList of type Measure so that
 *  for ArrayLists that are not too long (less equal 10) the bars have
 *  a fixed width of 30 pixels. If the length between 31 and 200 the
 *  width is adjusted down to 3 pixels of the width of the bars, and
 *  between 200 and 600 the chart is displayed as a Polygon.  
 * For more than 600 values no display is given but a warning that
 * there are too many values.
 *
 *  @version 2018-11-24
 *  @author Manfred Kerber
 */

public class DisplayBarChart extends Application {

    /** We define static values for the height of the display and a
     *  corresponding offset.
     */
    public static int yNumberOfPixels = 400;
    public static final int yOffset   =  10;

    /**
     *  @param barGap The width of a bar is computed in dependence of
     *  the gap between two bars.
     *  @return The value of the width of a bar, which is three times
     *  the value of the barGap.
     */
    public static int barWidth(int barGap) {
        return 3 * barGap;
    }

    /**
     *  @param barGap The offset is computed in dependence of the gap
     *  between two bars.
     *  @return The value of the xOffset between one bar and the next
     *  bar, which is the width of a bar plus the gap between two
     *  bars.
     */
    public static int xOffset(int barGap) {
        return barGap + barWidth(barGap);
    }

    /** 
     *  The method computes the maximal value in the (non-empty)
     *  ArrayList of measures.
     *  @param measures The ArrayList to be displayed.
     *  @return The maximal value in the list.
     *  @exception IllegalArgumentException if the ArrayList measures is empty.
     */ 
    public static double getMaximal(ArrayList<Measure> measures) {
        if (measures.size() == 0) {
            throw new IllegalArgumentException();
        } else {
            double maximum = measures.get(0).getValue();
            // maximum is the maximal element seen so far.
            for (int i = 1; i < measures.size(); i++) {
                if (measures.get(i).getValue() > maximum){
                    maximum = measures.get(i).getValue();
                }
            }
            return maximum;
        }
    }
    
    /**
     *  The method normalizes the bars in the range from 0 to
     *  yNumberOfPixels so that it makes use of all the pixels for the
     *  maximum and correspondingly fewer for smaller values.
     *  @param measure A particular measurement to be normalized.
     *  @param maximum The maximal value for the normalization.
     *  @return The value between 0 and the yNumberOfPixels.
     */
    public static double normalize(double measure, double maximum) {
        if (maximum == 0) {
            return measure;
        } else {
            return yNumberOfPixels * measure / maximum;
        }
    }
    
    /**
     *  Bars are added to the root group assumed there are at most 200
     *  elements in measures.
     *  @param root The Group to which the bars are added.
     *  @param barGap The width between two bars.
     *  @param measures The ArrayList of measures to be displayed in the scene.
     */
    public static void displayBarChartBars(Group root, int barGap,
                                           ArrayList<Measure> measures){
        // The maximal value in measures.
        double max = getMaximal(measures);
        // Length of the ArrayList
        int numberOfMeasures = measures.size(); 
        /* Loop: Display each of the measures with offsets increasing with i
         */
        for (int i = 0; i < numberOfMeasures; i++) {
            double normalizedY = normalize(measures.get(i).getValue(), max);
            Rectangle rectangle = new Rectangle(barGap + i * xOffset(barGap),
                                                yOffset + yNumberOfPixels - normalizedY,
                                                barWidth(barGap),
                                                normalizedY);
            root.getChildren().add(rectangle);
            
            //We write captions only if there are fewer than 31 elements
            if (numberOfMeasures <= 30) {
                //Text text = new Text(barGap + i*xOffset(barGap), or better:
                Text text = new Text(rectangle.getX(),                                                               3 * yOffset + yNumberOfPixels,
                                     measures.get(i).getDescription());
                text.setFont(Font.font("verdana", 12));
                root.getChildren().add(text);
            }
        }
    }

    /**
     *  For more than 200 up to 600 elements in measures we display
     *  the data as a polygon. The method returns the corresponding
     *  polygon.
     *  @param measures The ArrayList of measures to be displayed in the scene.
     *  @return The polygon of the values to be displayed. For each
     *  element in measures one point is added to the polygon (Plus
     *  one for the start and end).
     */
    public static Polygon makePolygon(ArrayList<Measure> measures){
        double max = getMaximal(measures);
        Polygon polygon = new Polygon(); 
        int numberOfMeasures = measures.size(); //length of the ArrayList
        double[] xValues = new double[numberOfMeasures + 2];
        double[] yValues = new double[numberOfMeasures + 2];
        /* Loop: Add a point for each measure*/
        for (int i = 0; i < numberOfMeasures; i++) {
            xValues[i+1] = i;
            yValues[i+1] = yOffset + yNumberOfPixels -
                normalize(measures.get(i).getValue(), max);
        }
        xValues[0] = 0;
        yValues[0] = yNumberOfPixels;
        xValues[numberOfMeasures+1] = numberOfMeasures+1;
        yValues[numberOfMeasures+1] = yNumberOfPixels;

        // Add the points to the polygon.
        for (int i = 0; i< numberOfMeasures + 2; i++){
            polygon.getPoints().addAll(new Double[]{xValues[i], yValues[i]});
        }
        return polygon;
    }

    /**
     *  The gap between two bar is computed.
     *  @param numberOfMeasures The number of bars to be displayed.
     *  @return The gap between two bars: 10 pixels for ArrayLists
     *  between 1 and 20 elements. Between 21 and 200 elements, it is
     *  computed as 200 divided by the number of elements.
     */
    public static int barGap(int numberOfMeasures) {
        // We have full size of 10 pixels for short ArrayLists.
        int barGap = 10; 
        // We adjust the size for ArrayLists between 21 and 200.
        if (numberOfMeasures > 20 && numberOfMeasures <= 200) {
            barGap   = 200 / numberOfMeasures;
        }
        return barGap;
    }
    
    /**
     *  Bars are added to the root group.
     *  @param measures The ArrayList of measures to be displayed in the scene.
     *  @param root The Group to which the bars are added.
     */
    public static void displayBarChart(Group root, ArrayList<Measure> measures){
        if (measures.size() > 600){
            System.out.println("Panel too small to display");
        } else {
            int numberOfMeasures = measures.size(); //length of the ArrayList
            int barGap = barGap(numberOfMeasures);
            // We draw a BarChart only for up to 200 elements
            if (numberOfMeasures <= 200) {
                displayBarChartBars(root, barGap, measures);
                // We create a Polygon if there are between 201 and 600 elements
            } else if (numberOfMeasures <= 600) {
                Polygon polygon = makePolygon(measures);
                root.getChildren().add(polygon);
            } else {
                /* We do nothing for more than 600 elements.
                 * The else part is redundant and only written to make
                 * it explicit that nothing is done in this case.
                 */
            }
        }
    }

    /**
     *  The method creates a random integer in the range between low and high
     *  @param low The lower bound for the random number.
     *  @param high The upper bound for the random number.
     *  @return The random number between low and high.
     */
    public static double randomRange(double low, double high) {
        return (low + (high - low) * Math.random());
    }

    /** 
     *  A random String of the form "A.B."
     *  @return A random String consisting of two characters in the
     *  range A-Z, each following by a dot.
     */
    public static String randomName() {
        int r1 = (int) (26 * Math.random());
        int r2 = (int) (26 * Math.random());
        int offset = (int) 'A';
        return "" + (char) (offset + r1) + "." +
            (char) (offset + r2) + ".";
    }

    /**
     *  The method creates an ArrayList of n measures with random
     *  names and values between low and high.
     *  @param n The length of the ArrayList to be created.
     *  @param low The lower bound of the values in the measures to be
     *  created.
     *  @param high The higher bound of the values in the measures to be
     *  created.
     *  @return A randomly generated ArrayList of Measure.
     */
    public static ArrayList<Measure> randomMeasures(int n,
                                                    double low, double high) {
        ArrayList<Measure> result = new ArrayList<Measure>();
        /* Add n randomly generated values */
        for (int i = 0; i < n; i++) {
            result.add(new Measure(randomName(), randomRange(low, high)));
        }
        return result;
    }

    
    /**
     *  Note the class does not contain a main method. The scene is
     *  displayed by the start method.
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Measure m1 = new Measure("John", 100);
        Measure m2 = new Measure("Mary", 200);
        Measure m3 = new Measure("Sam",  150);
        Measure m4 = new Measure("Al",   350);
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(m1); measures.add(m2); measures.add(m3); measures.add(m4);

        // A group is generated and the trees are added to it.
        Group root = new Group();

        // Some values for randomly generated ArrayLists
        double lowValue = 100;
        double highValue = 1000;
        int numberOfValues = 10; // 10, 20, 30, 31, 200, 201, 600, 601

        //displayBarChart(root,measures);
        displayBarChart(root, randomMeasures(numberOfValues,
                                             lowValue, highValue));

        // The scene consists of just one group.
        Scene scene =
            new Scene(root,
                      (numberOfValues <= 200) ? (numberOfValues + 1) * xOffset(barGap(numberOfValues)) : (numberOfValues + 3) ,
                      yNumberOfPixels + 4 * yOffset);
        // Give the stage (window) a title and add the scene.
        stage.setTitle("Bar Chart");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *  main method to call start method.
     *  @param args Handed on.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
