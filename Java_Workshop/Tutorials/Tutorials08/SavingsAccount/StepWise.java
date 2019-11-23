import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Polyline;

/**
 *  Class to display the balance in SavingsAccount as a graph of a
 *  piece-wise constant function. The x-axis corresponds to the days
 *  for which the amount is given and the y-axis to the balance.
 *
 *  @version 2018-11-28
 *  @author Manfred Kerber and Alexandros Evangelidis
 */
public class StepWise extends Application {
    public static final int OFFSET = 20;
    public static final int X_NUMBER_OF_PIXELS = 370;
    public static final int Y_NUMBER_OF_PIXELS = 400;

    /**
     *  One variable, ArrayList of transactions.
     */
    private static ArrayList<Transaction> transactions;

    /**
     *  The method computes the maximal balance in an ArrayList of
     *  transactions.
     *  @param transactions The ArrayList of transactions.
     *  @return The maximal balance found in an ArrayList of transactions.
     */
    public static long getMaximal(ArrayList<Transaction> transactions) {
        long maximum = transactions.get(0).getBalance();
        /* Loop invariant: maximum always contains the maximal balance
         * of all transactions considered so far.
         */
        for (int i = 1; i < transactions.size(); i++) {
            if (transactions.get(i).getBalance() > maximum){
                maximum = transactions.get(i).getBalance();
            }
        }
        return maximum;
    }

    /**
     *  We normalize a balance so that it fits with the number of
     *  pixels available between 0 and the maximal balance.
     *  @param balance The balance to be displayed.
     *  @param maximum The maximal balance occuring.
     *  @return The normalized value of the balance.
     */
    public static double normalize(long balance, long maximum) {
        return (((double) Y_NUMBER_OF_PIXELS * (maximum - balance)) / maximum);
    }

    
    /**
     *    The method creates a polyline corresponding to the balances
     *    of the transactions.  For each (but the last) transaction we
     *    add a horizontal line to the next transaction time and then
     *    a vertical line to the transaction balance to the polyline.
     *    @param transactions The array list of transactions.
     *    @return The created polyline.
     */
    public static Polyline createPolyline(ArrayList<Transaction> transactions){

        int numberOfTransactions = transactions.size();
        /* The vertices are 2 times the number of transactions but
         * the last plus plus one line from the last transaction to
         * the end of the year.
         */
        int vertices = 2 * numberOfTransactions;
        /* We declare arrays to store the x-positions and y-positions
         * for the polyline to be drawn.
         */
        double[] positions = new double[2*vertices];
        long maximalAmount = getMaximal(transactions) + OFFSET;

        /* For each transaction but the last we draw a horizontal line
         * to the next transaction time and then a vertical line to
         * the new balance. To this end we add two points, that is,
         * four values (first x-value, first y-value, second x-value,
         * second y-value) to the corresponding positions array (for
         * each i). The two points have both the same y-value, namely
         * the balance at position i, but different x-values, the
         * first day with the corresponding balance (from position i)
         * and the last day (from position i+1).  The y-values are all
         * normalized using the normalize method.
         */
        for(int i = 0; i < numberOfTransactions -1 ; i++) {
            positions[4*i] = (int) transactions.get(i).getDayOfYear();
            positions[4*i+1] = (int) normalize(transactions.get(i).getBalance(),
                                        maximalAmount);
            positions[4*i+2] = (int) transactions.get(i+1).getDayOfYear();
            positions[4*i+3] = (int) normalize(transactions.get(i).getBalance(),
                                        maximalAmount);
        }

        /* The last two points form a horizontal line from the last
         * transaction to the final day of the year.
         */
        positions[2*vertices - 4] = (int) transactions.get(numberOfTransactions-1).getDayOfYear();
        positions[2*vertices - 3] = (int) normalize(transactions.get(numberOfTransactions-1).getBalance(),
                                                           maximalAmount);

        positions[2*vertices - 2] = 365;
        positions[2*vertices - 1] =
            (int) normalize(transactions.get(numberOfTransactions-1).getBalance(), maximalAmount);

        // The polyline is returned.
        return new Polyline(positions);
    }

    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
         
        /* Create a Group (scene graph) with the polyline
         *  corresponding the transactions on the savings account.
         */
        Polyline polyline = createPolyline(transactions);
        Group root = new Group(polyline);
        // The scene consists of just one group.
        Scene scene = new Scene(root, X_NUMBER_OF_PIXELS, Y_NUMBER_OF_PIXELS);

        // Give the stage (window) a title and add the scene.
        stage.setTitle("Savings account display");
        stage.setScene(scene);
        stage.show();
    }


    /*
     * Main method to initialize a SavingsAccount and visualize it.
     */
    public static void main(String[] args) {
        Customer catheryn = new Customer("Catheryn", "CS", "012144444");
        SavingsAccount catherynsSavings = 
            new SavingsAccount(catheryn, "Savings111", 
                               "sesame", 0.01);
        int day = 1;
        int amount;
        for (int i = 0; i < 40; i++) {
            day += (int) (Math.random() * 7) + 1;
            amount = 3 + ((int) (5 * Math.random()));
            catherynsSavings.deposit (amount, day);
        }

        transactions = catherynsSavings.getTransactions();
        launch(args);
    }
}
