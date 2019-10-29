/** 
 *  Employee implements Payable, that is, we have to give
 *  an implementation of the methods getPaymentAmount() and dueDate().
 *  Likewise since Employee also implements Holiday we have to 
 *  give an implementation of getHolidays().
 *
 *  @version 2015-10-27
 *  @author  Manfred Kerber
 */
public class Employee implements Payable, Holiday {
    private static int generalDaysOfHolidays = 30;
	
    private String firstName;
    private String lastName;
    private String nI;
    private int salary;
    private int dayOfPayment;
    private int daysOfHolidays;

    /**
     *  A constructor for the Employee class.
     *  @param firstName The first name of the employee.
     *  @param lastName The last name of the employee.
     *  @param nI The national insurance number of the employee.
     *  @param salary The monthly salary of the employee.
     *  @param dayOfPayment The day of the month when the salary is paid.
     */
    public Employee(String firstName, String lastName, String nI,
                    int salary, int dayOfPayment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nI = nI;
        this.salary = salary;
        this.dayOfPayment = dayOfPayment;
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
     *  getter for the salary.
     *  @return The salary of the employee.
     */
    public int getSalary() {
        return salary;
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
     *  setter for the salary.
     *  @param salary The new monthly salary of the employee.
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     *  Implementation of the payment amount, needed since the class
     *  implements the Payable interface.
     *  @return The payment amount which corresponds to the monthly
     *  salary.
     */
    public int paymentAmount() {
        return getSalary();
    }

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
     */
    public String toString() {
        return String.format(" Name: %s, First Name: %s, NI: %s ", 
                             getLastName(),
                             getFirstName(),
                             getnI());
    }
}
