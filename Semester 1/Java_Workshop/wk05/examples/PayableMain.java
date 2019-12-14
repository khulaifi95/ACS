import java.util.ArrayList;
/** 
 *  This class is to test the Payable interface. In the main method an
 *  ArrayList of Holiday is created and the total number of holidays
 *  of the employees in this ArrayList is computed
 *
 *  @version 2015-10-27
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
        payableObjects.add(new Employee("John", "Smith", "AA112312", 1999, 22));
		
        Company c0 = new Company("a", "http://a", 2000000);
        payableObjects.add(c0);
        Employee mary = new Employee("Smith", "Mary", "AB 12346 C", 1800, 15);
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
