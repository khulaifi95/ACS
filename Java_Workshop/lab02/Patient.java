/**
 *   Class to store patient data with the fields name, id, address,
 *   phoneNumber all of type String.
 *   @author Manfred Kerber
 *   @version   2016-10-07
 */

public class Patient {
    private String name;
    private String id;
    private String address;
    private String phoneNumber;
	
	
    /**
     *   Constructor reuses all four field variables as parameters
     *   @param name The name of the patient.
     *   @param id The id number of the patient.
     *   @param address The address of the patient.
     *   @param phoneNumber The telephone number of the patient.
     */
    public Patient(String name, String id, String address, String phoneNumber) {
        super();
        this.name = name;
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    /**
     *   getter for the name
     *   @return The name of the patient.
     */
    public String getName() {
        return name;
    }

    /**
     *   @param name The new name of the patient.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     *   getter for the id
     *   @return The id of the patient.
     */
    public String getId() {
        return id;
    }


    /**
     *   @param id The new id of the patient.
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     *   getter for the address
     *   @return The address of the patient.
     */
    public String getAddress() {
        return address;
    }


    /**
     *   @param address The new address of the patient.
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     *   getter for the phone number
     *   @return The phone number of the patient.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     *   @param phoneNumber The new phone number of the patient.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     *   toString method
     *   @return The patient data in a readable format
     */
    @Override
    public String toString() {
        return name + " (" + id + ")\n" + address + " (Tel:" + phoneNumber + ")";
    }
	
    /**
     *   equals method
     *   @param p A second patient to compare to.
     *   @return true if and only if the this object corresponds to
     *   the argument in all components.
     */
    public boolean equals(Patient p) {
        return this.getName().equals(p.getName()) &&
            this.getId().equals(p.getId()) &&
            this.getAddress().equals(p.getAddress()) &&
            this.getPhoneNumber().equals(p.getPhoneNumber());
    }
	
    public static void main(String[] args) {
    	Patient p1 = new Patient("John Smith", "123", "SoCS", "0121414");
    	Patient p2 = new Patient("Mary Miller", "124", "SoCS", "0121414");
    	System.out.println(p1);
    	System.out.println(p2);
    	System.out.println(p1.equals(p1));
    	System.out.println(p1.equals(p2));
    }
}
