/**
 * With this class we want to demonstrate some problems with double.
 * In particular, we argue that double is not a good type for
 * implementing the amount in a bank account
 * @author mmk
 * @version 2013-10-15
 */
public class DoubleMain {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        double amount = 124.16;
        double withdraw = 11.48;
        amount = amount - withdraw;
		
        System.out.println("New amount " + amount);
    }
}
