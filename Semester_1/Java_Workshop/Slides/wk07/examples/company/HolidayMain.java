package company;

import java.util.ArrayList;
/** 
 *  This class is to test the Holiday interface. In the main method an
 *  ArrayList of Holiday is created and the total number of holidays
 *  of the employees in this ArrayList is computed
 *
 *  @version 2016-11-08
 *  @author Manfred Kerber
 */
public class HolidayMain{
    /*
     * main method with an example
     */
    public static void main(String[] args) {
        int total = 0;

        ArrayList<Holiday> employees = new ArrayList<Holiday>();

        Employee john = new MonthlyEmployee("John", "Smith", "AA112312", 1999);
        employees.add(john);
        Employee mary = new HourlyEmployee("Smith", "Mary", "AB 12346 C", 13);
        employees.add(mary);
        mary.setDaysOfHolidays(25);

        
        for (Holiday employee : employees){
            total += employee.getDaysOfHolidays();
            System.out.println("*"+ employee);
            System.out.println(" has days of holidays left:" + 
                               employee.getDaysOfHolidays());
        }
        System.out.println("The workforce has in total " + total +
                           " days of holidays left.");
    }
}
