package company;

/** 
 *  This class is concrete and inherits from the abstract superclass
 *  Employee. We specialize the toString method by indicating the
 *  hourly salary.  Since in the superclass Employee the method
 *  getPaymentAmount() is abstract, we must give in the HourlyEmployee
 *  class an implementation for paymentAmount(). It returns the hourly
 *  salary times the number of hours worked in the last month.
 *
 *  @version 2016-11-08
 *  @author Manfred Kerber
 */

public class HourlyEmployee extends Employee { 

    /**
     *  Additional field variable hourlySalary and
     *  workedHoursLastMonth (the latter initialized in the constructor to 0.
     */
    private int hourlySalary;
    private int workedHoursLastMonth;
    
    /**
     *  The constructor for an employee with an hourly salary has the
     *  three fields of an Employee plus the fields of the
     *  hourlySalary, which is set at construction, and
     *  workedHoursLastMonth, which is initialized to 0.
     *  @param firstName The first name of the employee.
     *  @param lastName The last name of the employee.
     *  @param nI The national insurance number of the employee.
     *  @param hourlySalary The hourly salary of the employee.
     */
    public HourlyEmployee(String firstName, String lastName, 
                          String nI, int hourlySalary) {
        super(firstName, lastName, nI); // must be called first
        this.hourlySalary         = hourlySalary;
        this.workedHoursLastMonth = 0;
    }

    /**
     *  getter for hourlySalary.
     *  @return The hourly salary of the employee.
     */
    public int getHourlySalary() {
        return this.hourlySalary;
    }

    /**
     *  getter for workedHoursLastMonth.
     *  @return The number of hours the employee worked last month.
     */
    public int getWorkedHoursLastMonth() {
        return this.workedHoursLastMonth;
    }

    /**
     *  setter for hourlySalary.
     *  @param hourlySalary The new hourly salary of the employee.
     */
    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    /**
     *  setter for workedHoursLastMonth.
     *  @param workedHoursLastMonth The new number of hours the
     *  employee worked last month.
     */
    public void setWorkedHoursLastMonth(int workedHoursLastMonth) {
        this.workedHoursLastMonth = workedHoursLastMonth;
    }

    
    /**
     *  The toString() method to display HourlyEmployee objects. In
     *  addition to the details of an Employee, the hourly salary is
     *  displayed. Note that the "@Override" statement is
     *  optional. However, it is good practice to write it; in this
     *  case the compiler checks whether the method actually overrides
     *  some other, if not en error will occur.
     *  @return A human readable string of a MonthlyEmployee object.
     */
    @Override
    public String toString() {
        return String.format("%s,\n hourly salary: %d, worked hours: %d, total salary: %d",
                             super.toString(), 
                             getHourlySalary(),
                             getWorkedHoursLastMonth(),
                             paymentAmount());
    }

    /**
     *   An implementation of the getPaymentAmount() method
     *   @return A hourly paid employee has to receive their monthly
     *   salary, which is computed as the hourly salary times the
     *   hours they worked.
     */
    public int paymentAmount() {
        return getHourlySalary() * getWorkedHoursLastMonth();
    }
}
