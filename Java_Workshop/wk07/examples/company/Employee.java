package company;

/** 
 *  Employee is an abstract class that implements Payable, that is, we
 *  have to give an implementation of the methods getPaymentAmount()
 *  and dueDate(). However, since Employee is an abstract class (unlike the
 *  corresponding class from week 5), it suffices to give abstract
 *  methods, we assume the dueDate() to be concrete (the 15th of every
 *  month), but the method getPaymentAmount() to be abstract, which
 *  would be determined differently for the sub-classes of hourly paid
 *  employees and monthly paid employees.  Likewise since Employee
 *  also implements Holiday we have to give an implementation of
 *  getHolidays(). In this case we choose to give a concrete
 *  implementation in the abstract class Employee.
 *
 *  @version 2016-11-08
 *  @author  Manfred Kerber
 */
public abstract class Employee implements Payable, Holiday {
    public static final int generalDaysOfHolidays = 30;
    public static final int generalPaymentDay = 15;
	
    private String firstName;
    private String lastName;
    private String nI;
    private int dayOfPayment;
    private int daysOfHolidays;

    /**
     *  A constructor for the Employee class.
     *  @param firstName The first name of the employee.
     *  @param lastName The last name of the employee.
     *  @param nI The national insurance number of the employee.
     */
    public Employee(String firstName, String lastName, String nI){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nI = nI;
        this.dayOfPayment = generalPaymentDay;
        this.daysOfHolidays = generalDaysOfHolidays;
    }

    /**
     *  getter for first name.
     *  @return The first name of the employee.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *  getter for last name.
     *  @return The last name of the employee.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *  getter for the national insurance number nI.
     *  @return The national insurance number of the employee.
     */
    public String getnI() {
        return nI;
    }

    /**
     *  getter for the day of payment
     *  @return The day when the salary is due.
     */
    public int getDayOfPayment() {
        return dayOfPayment;
    }
	
    /**
     *  getter for the number of holidays.
     *  @return The number of holidays the employee still has.
     */
    public int getDaysOfHolidays() {
        return daysOfHolidays;
    }
	
    /**
     *  setter for the number of holidays.
     *  @param daysOfHolidays The new number of holidays the employee still has.
     */
    public void setDaysOfHolidays(int daysOfHolidays) {
        this.daysOfHolidays = daysOfHolidays;
    }

    /**
     *  setter for the day of payment.
     *  @param dayOfPayment The new day of the month when the salary
     *  has to be paid.
     */
    public void setDayOfPayment(int dayOfPayment) {
        this.dayOfPayment = dayOfPayment;
    }

    /**
     *  The abstract method needs implementations in each concrete
     *  sub-class.
     *  @return The payment amount which corresponds to the salary.
     */
    public abstract int paymentAmount();

    /**
     *  Implementation of the due date by which the salary needs to be
     *  paid, needed since the class implements the Payable interface.
     *  @return The due date when the monthly salary is to be paid.
     */
    public int dueDate() {
        return getDayOfPayment();
    }

    /**
     *  The toString() method to display Employee objects.
     *  @return A human readable string of an Employee object.
     */
    public String toString() {
        return String.format(" Name: %s, First Name: %s, NI: %s ", 
                             getLastName(),
                             getFirstName(),
                             getnI());
    }
}
