import java.util.ArrayList;
/**
 *   The class contains a main method to make experiments with the two
 *   methods from AboveMin.
 *   @version 2017-11-23
 *   @author Alexandros Evangelidis, Manfred Kerber
 */
public class AboveMinMain {
    /*
     * main method to generate tests and measure the run times of the
     * two methods aboveMin1 and aboveMin2 from the class AboveMin.
     */
    public static void main(String[] args) {
        int size = 100000;
        ArrayList<Measurable> p = new ArrayList<Measurable>();
        Patient p1 = new Patient("John", 20, 82);
        Patient p2 = new Patient("Mary", 22, 67);
        Patient p3 = new Patient("Sam", 22,  68);

        p.add(p1); p.add(p2); p.add(p3);

        System.out.println("above min: " + AboveMin.aboveMin1(p));
        System.out.println("above min: " + AboveMin.aboveMin2(p));

        long before, after;
        
        ArrayList<Measurable> big = new ArrayList<Measurable>();

        /* Loop invariant: randomly generated objects of type Patient
         * are added up to the number i to the ArrayList big.
         */
        for (int i = 0; i < size; i++) {
            big.add(new Patient("NN", 0, 50 * (1 + Math.random())));
        }
        before = System.nanoTime();
        System.out.println("Length: " + AboveMin.aboveMin1(big).size());
        after = System.nanoTime();
        System.out.println((after - before)/1000000 + "ms");

        before = System.nanoTime();
        System.out.println("Length: " + AboveMin.aboveMin2(big).size());
        after = System.nanoTime();
        System.out.println((after - before)/1000000 + "ms");

    }
}
