/**
 * The class describes a gold card as a subclass of
 * a credit card.
 * @author Alexandros Evangelidis and Manfred Kerber
 * @version 2019-11-10
 */
public class GoldCard extends CreditCard {

    private int fee;

    /**
     *@param name the name of the gold card 
     *@param accountNumber the account number of the gold card
     *@param amount the amount of money available
     *@param fee a fee to be subtracted from the amount
     */
    public GoldCard (String name, String accountNumber, int amount, int fee){
        super(name, accountNumber, amount);
        this.fee = fee;
	setAmount(getAmount()-fee);
    }
}
