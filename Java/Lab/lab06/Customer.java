/**
 *  The class represents a bank customer by name, address, and
 *  telephone number.
 */
public class Customer {   

    private String customerName;
    private String address;
    private String telephoneNumber;

    /**
     *  Constructor for Customer setting the three field variables.
     *  @param customerName The name of the customer as a String.
     *  @param address The postal address of the customer.
     *  @param telephoneNumber A telephone number of the customer.
     */
    public Customer (String customerName, String address,
                     String telephoneNumber){
        this.customerName = customerName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    /**
     *  Getter for the name of a customer
     *  @return The name of the customer. 
     */
    public String getCustomerName(){
        return customerName;
    }

    /**
     *  Getter for the address of a customer
     *  @return The address of the customer. 
     */
    public String getAddress(){
        return address;
    }

    /**
     *  Getter for the telephone number of a customer
     *  @return The phone of the customer. 
     */
    public String getTelephoneNumber(){
        return telephoneNumber;
    }
}

