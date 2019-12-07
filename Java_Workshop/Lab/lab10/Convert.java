import java.util.function.Function;
import java.util.*;

/**
 *  The class is to write a general conversion method between units
 *  such as metre and mile, or kilogram and pound. Only conversions in
 *  the same category are admissible. The approach is that a
 *  conversion from one unit to another is via converting amount first
 *  from the fromUnit to a baseUnit ("metre" for length, "kilogram"
 *  for mass, and "kelvin" for temperature) and then from the baseUnit
 *  to the toUnit.
 *
 *  @author Manfred Kerber
 *  @version 2019-12-05
 */

public class Convert {

    /**
     *  An ArrayList of units to measure lengths.
     */
    public static final ArrayList<String> lengthsArrayList =
        new ArrayList<String>(Arrays.asList(new String[]
            {"metre", "mile", "inch", "kilometre",
             "centimetre", "millimetre"}));

    /**
     *  An ArrayList of units to measure masses.
     */
    public static final ArrayList<String> massesArrayList =
        new ArrayList<String>(Arrays.asList(new String[]
            {"kilogram", "gram", "pound", "ton"}));

    /**
     *  An ArrayList of units to measure temperatures.
     */
    public static final ArrayList<String> temperaturesArrayList =
        new ArrayList<String>(Arrays.asList(new String[]
            {"kelvin", "celsius", "fahrenheit"}));

    /**
     *  An (initially empty) ArrayList of ArrayLists of units. The
     *  ArrayList is initialized by the initialize method.
     */
    public static ArrayList<ArrayList<String>> conversionUnits =
        new ArrayList<ArrayList<String>>();

    /**
     *  An (initially empty) ArrayList of ArrayLists of Functions
     *  (from Double to Double), where the functions represent the
     *  conversion from the base units (such as "metre", "kilogram",
     *  and "kelvin") to other units (such as "mile", "gram", and
     *  "celsius").
     */
    public static ArrayList<ArrayList<Function<Double,Double>>>
        conversionsFromBaseToOther =
        new ArrayList<ArrayList<Function<Double, Double>>>();

    /**
     *  An (initially empty) ArrayList of ArrayLists of Functions
     *  (from Double to Double), where the functions represent the
     *  conversion from other units (such as "mile", "gram", and
     *  "celsius") to base units (such as "metre", "kilogram", and
     *  "kelvin").
     */
    public static ArrayList<ArrayList<Function<Double,Double>>>
        conversionsFromOtherToBase =
        new ArrayList<ArrayList<Function<Double, Double>>>();

    /**
     *  The method initializes the ArrayLists conversionUnits,
     *  conversionsFromBaseToOther, and conversionsFromOtherToBase.
     */
    public static void initialize() {
        conversionUnits.add(lengthsArrayList);
        conversionUnits.add(massesArrayList);
        conversionUnits.add(temperaturesArrayList);
        initializeToOther();
        initializeFromOther();
    }

    /**
     *  For a given unit it is found which position the corresponding
     *  ArrayList has in the ArrayList of all conversionUnits, e.g., 0
     *  for length, 1 for mass, and 2 for temperature in the concrete
     *  implementation below. If the unit is not found, -1 is
     *  returned.
     *  @param unit A unit as a String.
     *  @return The position of the corresponding ArrayList has in the
     *  ArrayList of all conversionUnits.
     */
    public static int findDimension(String unit) {
        // Go through all ArrayLists in ConversionUnits
        for (int i = 0; i < conversionUnits.size(); i++) {
            // Go through each unit in the corresponding ArrayList
            for (int j = 0; j < conversionUnits.get(i).size(); j++) {
                // If the unit is found return the index of the ArrayList
                if (conversionUnits.get(i).get(j).equals(unit)) {
                    return i;
                }
            }
        }
        // If the unit is not found return -1.
        return -1;
    }

    /**
     *  Given the dimension, the method returns the position of the
     *  unit in the corresponding ArrayList if it exists, else it
     *  returns -1.
     *  @param unitDimension The position of the dimension in the
     *  ArrayList of all conversionUnits.
     *  @param unit The unit whose position we try to find.
     *  @return The position of the unit in the corresponding
     *  ArrayList if it exists, else -1.
     */
    public static int findUnit(int unitDimension, String unit) {
        // Loop through the corresponding ArrayList
        for (int i = 0; i < conversionUnits.get(unitDimension).size(); i++) {
            // If the unit is found return the corresponding index.
            if (conversionUnits.get(unitDimension).get(i).equals(unit)) {
                return i;
            }
        }
        // If the unit is not found return -1.
        return -1;
    }

    /**
     *  Initialize the conversion from a baseUnit to otherUnits by an
     *  ArrayList of ArrayLists of Functions from Double to
     *  Double. The outer ArrayList is to represent the dimension
     *  (such as length, mass, and temperature), the inner ArrayLists
     *  are for the conversion between a baseUnit and another unit in
     *  the same dimension. Each conversion is represented by a
     *  function to represent how to convert from the baseUnit to
     *  another unit (of the same dimension); which unit this other
     *  unit is is determined by the corresponding sub-ArrayList in
     *  conversionUnits, the first one (position 0) always being the
     *  base unit).
     */
    public static void initializeToOther() {
        // Conversion of lengths
        ArrayList<Function<Double,Double>> metreToOther =
            new ArrayList<Function<Double, Double>>();
        metreToOther.add(x -> {return x;});                  // metre to metre
        metreToOther.add(x -> {return 0.0006213712 * x;});   // metre to mile
        metreToOther.add(x -> {return 39.37008 * x;});       // metre to inch
        metreToOther.add(x -> {return 0.001 * x;});          // metre to kilometre
        metreToOther.add(x -> {return 100 * x;});            // metre to centimetre
        metreToOther.add(x -> {return 1000 * x;});           // metre to millimetre
        conversionsFromBaseToOther.add(metreToOther);

        // Conversion of masses
        ArrayList<Function<Double,Double>> kilogramToOther =
            new ArrayList<Function<Double, Double>>();
        kilogramToOther.add(x -> {return x;});               // kilogram to kilogram
        kilogramToOther.add(x -> {return 1000 * x;});        // kilogram to gram
        kilogramToOther.add(x -> {return 2.204623 * x;});    // kilogram to pound
        kilogramToOther.add(x -> {return 0.001102311 * x;}); // kilogram to ton
        conversionsFromBaseToOther.add(kilogramToOther);

        // Conversion of temperatures
        ArrayList<Function<Double,Double>> kelvinToOther =
            new ArrayList<Function<Double, Double>>();
        kelvinToOther.add(x -> {return x;});                       // kelvin to kelvin
        kelvinToOther.add(x -> {return x - 273.15;});              // kelvin to celsius
        kelvinToOther.add(x -> {return (x - 273.15)* 9/ 5 + 32;}); // kelvin to fahrenheit
        conversionsFromBaseToOther.add(kelvinToOther);
    }

    
    /**
     *  Initialize the conversion from a fromUnit to the corresponding
     *  baseUnit by an ArrayList of ArrayLists of Functions from
     *  Double to Double. The outer ArrayList is to represent the
     *  dimension (such as length, mass, and temperature), the inner
     *  ArrayLists are for the conversion between a unit and the
     *  corresponding base unit. Each conversion is represented by a
     *  function to represent how to convert from the unit to the
     *  corresponding base unit (of the same dimension), which unit
     *  this other unit is is determined by the corresponding
     *  sub-ArrayList in conversionUnits, the first one (position 0)
     *  always being the base unit).
     */
    public static void initializeFromOther() {
        // Conversion of lengths
        ArrayList<Function<Double,Double>> otherToMetre =
            new ArrayList<Function<Double, Double>>();
        otherToMetre.add(x -> {return x;});              // metre to metre
        otherToMetre.add(x -> {return 1609.344 * x;});   // mile to metre
        otherToMetre.add(x -> {return 0.0254 * x;});     // inch to metre
        otherToMetre.add(x -> {return 1000 * x;});       // kilometre to metre
        otherToMetre.add(x -> {return 0.01 * x;});       // centimetre to metre
        otherToMetre.add(x -> {return 0.001 * x;});      // millimetre to metre
        conversionsFromOtherToBase.add(otherToMetre);
        
        // Conversion of masses
        ArrayList<Function<Double,Double>> otherToKilogram =
            new ArrayList<Function<Double, Double>>();
        otherToKilogram.add(x -> {return x;});              // kilogram to kilogram
        otherToKilogram.add(x -> {return 0.001 * x;});      // gram to kilogram
        otherToKilogram.add(x -> {return 0.4535924 * x;});  // pound to kilogram
        otherToKilogram.add(x -> {return 907.1847 * x;});   // kilogram to ton
        conversionsFromOtherToBase.add(otherToKilogram);

        // Conversion of temperatures
        ArrayList<Function<Double,Double>> otherToKelvin =
            new ArrayList<Function<Double, Double>>();
        otherToKelvin.add(x -> {return x;});                       // kelvin to kelvin
        otherToKelvin.add(x -> {return x + 273.15;});              // celsius to kelvin
        otherToKelvin.add(x -> {return (x - 32)* 5/ 9 + 273.15;}); // kelvin to fahrenheit
        conversionsFromOtherToBase.add(otherToKelvin);
    }
    
    /**
     *  The method converts a given amount in a particular unit
     *  fromUnit to an amount in a particular unit toUnit.
     *  @param amount The amount to be converted.
     *  @param fromUnit The unit in which the amount is given.
     *  @param toUnit The unit in which the amount is sought.
     *  @return The amount in the toUnit.
     */
    public static double convert(double amount, String fromUnit, String toUnit){
        // Initialize all the conversion ArrayLists.
        initialize();
        /*  
         * Find the index of the dimension of the fromUnit (0 for
         * lengths, 1 for masses, 2 for temperatures)
         */
        int fromUnitDimension = findDimension(fromUnit);
        /*  
         * Find the index of the dimension of the toUnit (0 for
         * lengths, 1 for masses, 2 for temperatures)
         */
        int toUnitDimension = findDimension(toUnit);
        /*
         *  If the dimension of the units do not match (that is, if we
         *  try to convert kilogram to celsius, for instance) then
         *  throw an IllegalArgumentException. Likewise if a dimension
         *  is not found.
         */
        if (fromUnitDimension == -1 || fromUnitDimension != toUnitDimension) {
            throw new IllegalArgumentException("Cannot convert these units.");
        } else {
            /*
             * Find the position of the fromUnit in the ArrayList for
             * the dimension.
             */
            int fromUnitNumber = findUnit(fromUnitDimension, fromUnit);
            /*
             * Find the position of the toUnit in the ArrayList for
             * the dimension.
             */
            int toUnitNumber = findUnit(fromUnitDimension, toUnit);
            // Compute the amount in the base unit.
            Double amountInBaseUnit =
                (Double) conversionsFromOtherToBase.get(fromUnitDimension).get(fromUnitNumber).apply(amount);
            // Return the amount in the toUnit.
            return conversionsFromBaseToOther.get(fromUnitDimension).get(toUnitNumber).apply(amountInBaseUnit);
        }
    }

    /**
     *  main method for some initial tests.
     */
    public static void main(String[] args) {

        System.out.println(convert(2, "kilogram", "pound"));
        System.out.println(convert(10, "kilogram", "gram"));
        System.out.println(convert(1, "mile", "metre"));
        System.out.println(convert(10, "metre", "kilometre"));
        System.out.println(convert(10, "kilometre", "metre"));
        System.out.println(convert(70, "fahrenheit", "celsius"));
    }
}
