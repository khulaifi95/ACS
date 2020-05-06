import java.util.ArrayList;
import java.util.function.Function;

/**
 * @version 2019-11-08
 * @author Alexandros Evangelidis, Manfred Kerber
 *
 * The class contains a method to compute the average money owed by a
 * list of credit card holders. Furthermore it contains a filter
 * method to determine sublists of the full list of those credit cards
 * that are gold cards and those that are not.
 */
public class CreditCardMain {

    /**
     *  The method computes the average of the amounts on a list of credit cards.
     *  @param cList An ArrayList of type CreditCard.
     *  @exception An IllegalArgumentException is thrown if the list of credit cards is empty.
     *  @return The average debt on the credit cards in the ArrayList.
     */
    public static double average(ArrayList<CreditCard> cList) {
        if (cList.size() == 0) {
            throw new IllegalArgumentException();
        } else {
            double result = 0;
            for (CreditCard c : cList) {
                result += c.getAmount();
            }
            return result/cList.size();
        }
    }

    /**
     * The method determines the sublist of the input list of CreditCards that satisfy a predicate.
     * 
     * @param cList An ArrayList of type CreditCard.
     * @param predicate A function from CreditCard to Boolean with which the new
     *                  ArrayList is created.
     * @return A new ArrayList with all those credit cards from the original list for
     *         which the predicate returns true.
     */
    public static ArrayList<CreditCard> filter(ArrayList<CreditCard> cList, Function<CreditCard, Boolean> predicate) {
        ArrayList<CreditCard> result = new ArrayList<CreditCard>();

        /*
         * The loop invariant is that the variable result always contains all those
         * credit cards seen so far which satisfy the predicate.
         */
        for (CreditCard c : cList) {
            if (predicate.apply(c)) {
                result.add(c);
            }
        }
        return result;
    }


    /*
     * main method to test the methods written above.
     */
    public static void main(String[] args) {

        GoldCard   g1 = new GoldCard("MasterCard Gold", "Gold1", -200, 20);
        CreditCard c1 = new CreditCard("Visa", "Credit1", -100);
        GoldCard   g2 = new GoldCard("Visa Gold", "Gold2", -400, 25);
        CreditCard c2 = new CreditCard("American Express", "Credit2", 0);
        CreditCard c3 = new CreditCard("MasterCard", "Credit3", -100);
		
        ArrayList<CreditCard> list = new ArrayList<CreditCard>();
        list.add(g1);
        list.add(c1);
        list.add(g2);
        list.add(c2);
        list.add(c3);

        System.out.println("Average amount owed by GoldCard holder: " + average(filter(list, x -> x instanceof GoldCard)));
        System.out.println("Average amount owed by non-GoldCard holder: " + average(filter(list, x -> !(x instanceof GoldCard))));
        System.out.println("Average amount owed by any credit card holder: " + average(list));
    }
}

