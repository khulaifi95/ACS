/**
 *  A Customer is characterized by the fields name, address, and telephoneNumber all of type String.
 *  @version 2015-10-20
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
     *  @param gender The gender of the customer.
     *  @param address The address of the customer.
     *  @param telephoneNumber The phone number of the customer.
     */
    public Customer (String name, String gender, String address, String telephoneNumber){
        if (gender.equals("m") ||
            gender.equals("f") ||
            gender.equals("x")){

            this.name = name;
            this.gender = gender;
            this.address = address;
            this.telephoneNumber = telephoneNumber;
        } else {
            throw new IllegalArgumentException("Gender must be \"m\", \"f\", or \"x\"");
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
        if (this.getGender().equals("m")) {
            return "Mr.";
        } else if (this.getGender().equals("f")) {
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
