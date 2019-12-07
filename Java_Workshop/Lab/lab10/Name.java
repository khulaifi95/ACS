/**
 *  This class is an exercise in writing a for-loop to create objects
 *  of type Name. A name consists of a firstName and a surname.
 *  @author Manfred Kerber
 *  @version 2019-11-05
 */

public class Name {

    /**
     * The field variables are firstName and surname.
     */ 
    private String firstName;
    private String surname;

    /** 
     *  Standard constructor for the class
     *  @param firstName The first name in the name.
     *  @param surname The surname in the name.
     */
    public Name(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     *  The method take two arrays of the same lengths, the first with
     *  the first names, the second with the surnames and returns a
     *  corresponding array of names.
     *  @param firstNames An array of first names.
     *  @param surnames An array of surnames.
     *  @return An array of the names corresponding to the names and surnames.
     *  @exception The method throws an exception if the lengths of
     *  the two input arrays differ.
     */
    public static Name[] makeNames(String[] firstNames, String[] surnames) {
        if (firstNames.length != surnames.length) {
            throw new IllegalArgumentException();
        } else {
            Name[] result = new Name[firstNames.length];
            /* Up to index i the names in the result array are initialized.
             */
            for (int i = 0; i < firstNames.length; i++) {
                result[i] = new Name(firstNames[i], surnames[i]);
            }
            return result;
        }
    }

    /**
     *  Standard toString method
     *  @return The object in a human readable form.
     */
    public String toString() {
        return firstName + " " + surname;
    }

    /**
     *  main method for an initial test.
     */
    public static void main(String[] args) {
        String[] firstNames = {"Mary", "John", "Sam"};
        String[] surnames = {"Smith", "Smith", "Miller"};

        Name[] members = makeNames(firstNames, surnames);

        for (int i = 0; i < members.length; i++) {
            System.out.println(members[i]);
        }
    }
}
