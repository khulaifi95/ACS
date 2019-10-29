/**
 *  A Customer is characterized by the fields name, address, and
 *  telephoneNumber all of type String.
 *  @version 2017-10-24
 *  @author Manfred Kerber
 */

public class Customer{

    private String name;
    private String gender;
    private String address;
    private String telephoneNumber;

    /** Constructor for a Customer
     *
     *  @param name The name of the customer.
     *  @param gender The gender of the customer, which must be either
     *  "M", "F", or "X". For any other value an exception is thrown.
     *  @param address The address of the customer.
     *  @param telephoneNumber The phone number of the customer.
     */
    public Customer (String name, String gender, String address, String telephoneNumber) {
        if (gender.equals("M") ||
            gender.equals("F") ||
            gender.equals("X")){

            this.name = name;
            this.gender = gender;
            this.address = address;
            this.telephoneNumber = telephoneNumber;
        } else {
            throw new IllegalArgumentException("Gender must be \"M\", \"F\", or \"X\"");
        }
    }
    
    /**
     * Getter for the name.
     * @return The name of the Customer.
     */
    public String getName(){
        return name;
    }

    /**
     * Getter for the gender.
     * @return The gender of the Customer.
     */
    public String getGender(){
        return gender;
    }

    /**
     * Getter for the address.
     * @return The address of the Customer.
     */
    public String getAddress(){
        return address;
    }
    
    /**
     * Getter for the telephone number.
     * @return The telephoneNumber of the Customer.
     */
    public String getTelephoneNumber(){
        return telephoneNumber;
    }

    /**
     *  @return Either "Mr.", "Ms.", or "" depending on gender.
     */
    public String salutation(){
        if (this.getGender().equals("M")) {
            return "Mr.";
        } else if (this.getGender().equals("F")) {
            return "Ms.";
        } else {
            return "";
        }
    }
        
    /**
     * toString method for printing
     * @return The Customer represented as a String.
     */
    public String toString() {
        return salutation() +  getName() + " @" +
            getAddress() + "(Tel: " + getTelephoneNumber() + ")";
    }
}
