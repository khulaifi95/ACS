/** 
 *  Some examples of unicode characters as strings
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 *
 */

public class String4 { 
    public static void main(String[] args) {

        System.out.print("u0026: ");System.out.println("\u0026"); // ampersand
        System.out.print("u00A1: ");System.out.println("\u00A1"); // inverted exclamation mark
        System.out.print("u00A3: ");System.out.println("\u00A3"); // pound sign
        System.out.print("u20AC: ");System.out.println("\u20AC"); // euro sign
        System.out.print("u00A2: ");System.out.println("\u00A2"); // cent sign
        System.out.print("u00A5: ");System.out.println("\u00A5"); // yen sign
        System.out.print("u00A4: ");System.out.println("\u00A4"); // currency sign
        System.out.print("u00A6: ");System.out.println("\u00A6"); // broken bar
        System.out.print("u00A7: ");System.out.println("\u00A7"); // section sign
        System.out.print("u00A8: ");System.out.println("\u00A8"); // dieresis
        System.out.print("u00A9: ");System.out.println("\u00A9"); // copyright sign
        System.out.print("u00AE: ");System.out.println("\u00AE"); // registered sign
        System.out.print("u00B1: ");System.out.println("\u00B1"); // plus-minus sign
        System.out.print("u05D0: ");System.out.println("\u05D0"); // Hebrew letter alef
        System.out.print("u0662: ");System.out.println("\u0662"); // arabic-indic digit two
        System.out.print("u00B0: ");System.out.println("\u00B0"); // degree sign
        System.out.print("u0025: ");System.out.println("\u0025"); // percent sign
        System.out.print("u212E: ");System.out.println("\u212E"); // estimate sign
        System.out.print("u2193: ");System.out.println("\u2193"); // down arrow

        System.out.println("The exchange rates between \u20AC and \u00A3 are not fixed.");
    }
}

