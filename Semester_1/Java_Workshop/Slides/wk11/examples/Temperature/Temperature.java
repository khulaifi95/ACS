/**
 *   Converting between celsius and fahrenheit, the basic program
 *   without GUI which - in a big system - the team working on the GUI
 *   could not change it. The class is taken from Jon Rowe's example
 *   teaching the model-view-control approach in Swing. It has been
 *   adapted to JavaFX. However, the Temperature class itself did not
 *   change in the transition (except for this comment, here).
 *
 *   @version 2015-11-27
 *   @author Jon Rowe
 */

public class Temperature {
    private double celsius;
    private double fahrenheit;

    /**
     *  Construct temperature in degrees celsius
     *  @param celsius The temperature in degrees Celsius
     */
    public Temperature(double celsius) {
        this.celsius = celsius;
        this.fahrenheit = 1.8 * celsius + 32.0;
    }
	
    /**
     *  getter for the temperature in celsius
     * @return temperature in celsius
     */
    public double getCelsius() {
        return celsius;
    }
	
	
    /**
     *  getter for the temperature in fahrenheit
     *  @return temperature in fahrenheit
     */
    public double getFahrenheit() {
        return fahrenheit;
    }
	
	
    /**
     *  setter to change temperature (celsius)
     *  @param celsius new temperature in celsius
     */
    public void setCelsius(double celsius){
        this.celsius = celsius;
        this.fahrenheit = 1.8 * celsius + 32.0;
     }


    /**
     *  change temperature (fahrenheit)
     *  @param fahrenheit new temperature in fahrenheit
     */
    public void setFahrenheit(double fahrenheit){
        this.fahrenheit = fahrenheit;
        this.celsius = (fahrenheit - 32.0) / 1.8;
    }
}
