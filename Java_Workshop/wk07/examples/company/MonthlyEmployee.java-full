package company;

/** 
 *  This class is concrete and inherits from the abstract superclass
 *  Employee. We specialize the toString method by indicating the
 *  monthly salary.  Since in the superclass Employee the method
 *  getPaymentAmount() is abstract, we must give in the
 *  MonthlyEmployee class an implementation for paymentAmount(). It
 *  just returns the monthly salary.
 *
 *  @version 2016-11-08
 *  @author Manfred Kerber
 */

public class MonthlyEmployee extends Employee{
    /**
     *  Additional field variable monthlySalary.
     */
    private int monthlySalary;

    /**
     *  The constructor for an employee with a monthly salary has the
     *  three fields of an Employee plus the field of the
     *  monthlySalary, which is set at construction.
     *  @param firstName The first name of the employee.
     *  @param lastName The last name of the employee.
     *  @param nI The national insurance number of the employee.
     *  @param monthlySalary The monthly salary of the employee.
     */
    public MonthlyEmployee(String firstName, String lastName, 
                           String nI, int monthlySalary) {
        super(firstName, lastName, nI); // must come first
        this.monthlySalary = monthlySalary;
    }

    /**
     *  getter for monthlySalary.
     *  @return The monthly salary of the employee.
     */
    public int getMonthlySalary() {
        return monthlySalary;
    }

    /**
     *  setter for monthlySalary.
     *  @param monthlySalary The new monthly salary of the employee.
     */
    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    /**
     *  The toString() method to display MonthlyEmployee objects. In
     *  addition to the details of an Employee, the monthly salary is
     *  displayed.  Note that the "@Override" statement is
     *  optional. However, it is good practice to write it; in this
     *  case the compiler checks whether the method actually overrides
     *  some other, if not en error will occur.
     *  @return A human readable string of a MonthlyEmployee object.
     */
    @Override
    public String toString() {
        return "MonthlyEmployee [" + super.toString() + 
            "monthlySalary=" + monthlySalary + "]";
    }
         
    /**
     *   An implementation of the getPaymentAmount() method
     *   @return A monthly paid employee has to receive their monthly
     *   salary, which is returned as the payment amount.
     */
    public int paymentAmount(){
        return getMonthlySalary();
    }
}
