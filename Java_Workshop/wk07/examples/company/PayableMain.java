package company;

import java.util.ArrayList;
/** 
 *  This class is to test the Payable interface. In the main method an
 *  ArrayList of Payable is created and the total expenditure in this
 *  ArrayList is computed
 *
 *  @version 2016-11-08
 *  @author Manfred Kerber
 */
public class PayableMain {
    /*
     * main method with an example
     */
    public static void main(String[] args) {
        int total = 0;
        // Payable will contain two invoices and two employees
        ArrayList<Payable> payableObjects = new ArrayList<Payable>();
        
        payableObjects.add(new Invoice(29,6,"printer cartridge","222333", 15));
        payableObjects.add(new Invoice(3, 20, "1000 sheet of a4 paper", "234567", 15));
        payableObjects.add(new MonthlyEmployee("John", "Smith", "AA112312", 1999));
                
        HourlyEmployee mary = new HourlyEmployee("Smith", "Mary", "AB 12346 C", 13);
        mary.setWorkedHoursLastMonth(180);
        payableObjects.add(mary);
        
        for (Payable currentPayable : payableObjects){
            total += currentPayable.paymentAmount();
            System.out.println("*"+ currentPayable);
            System.out.println("TO PAY:" + 
                               currentPayable.paymentAmount());
        }
        System.out.println("THE TOTAL EXPENDITURE IN THE" +
                           " CURRENT PERIOD IS: " + total);
    }
}
