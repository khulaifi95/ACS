package company;

/**
 * Invoice implements Payable, that is, we have to give an
 * implementation for the methods PaymentAmount() and
 * dueDate(). That is done in the first case by returning the number of
 * items times the cost per item, in the second case by returning the
 * due date.
 *
 *  @author Manfred Kerber
 *  @version 2016-11-08 
 */
public class Invoice implements Payable {
	private int pricePerItem;
	private int quantity;
	private String description;
	private String idNumber;
	private int dueDate;

    /**
     *  An Invoice object is created by this constructor.
     *  @param pricePerItem The price that a single item costs.
     *  @param quantity The number of items that have been shipped.
     *  @param description A verbal description of the item.
     *  @param idNumber A unique identifier of the item.
     *  @param dueDate A time (for simplicity represented as a number)
     *  by which the invoice has to be paid.
     */
    public Invoice(int pricePerItem, int quantity, String description,
                   String idNumber, int dueDate) {
        this.pricePerItem = pricePerItem;
        this.quantity = quantity;
        this.description = description;
        this.idNumber = idNumber;
        this.dueDate = dueDate;
    }

    /** 
     *  getter for the dueDate.
     *  @return The due date by when the invoice must be paid. 
     */
    public int getDueDate() {
        return dueDate;
    }
	
    /** 
     *  This is doing exactly the same as the getDueDate
     *  getter. However, we have to provide this method, since the
     *  Invoice class implements the Payable interface, hence we need
     *  an explicit implementation for dueDate().
     *  @return The due date by when the invoice must be paid. 
     */
    public int dueDate() {
        return getDueDate();
    }

    /** 
     *  setter for the dueDate.
     *  @param dueDate The new due date by when the invoice must be paid. 
     */
    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    /** 
     *  getter for pricePerItem.
     *  @return The price that must be paid for a single item.
     */
    public int getPricePerItem() {
        return pricePerItem;
    }

    /** 
     *  setter for the pricePerItem.
     *  @param pricePerItem The new price per item.
     */
    public void setPricePerItem(int pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    /** 
     *  getter for quantity.
     *  @return The quantity of items that the invoice is about.
     */
    public int getQuantity() {
        return quantity;
    }

    /** 
     *  setter for the quantity.
     *  @param quantity The new quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /** 
     *  getter for description.
     *  @return The description of the item that the invoice is about.
     */
    public String getDescription() {
        return description;
    }

    /** 
     *  setter for description.
     *  @param description The new description of the item that the
     *  invoice is about.
     */
	public void setDescription(String description) {
		this.description = description;
	}

    /** 
     *  getter for idNumber.
     *  @return The idNumber of the item that the invoice is about.
     */
    public String getIdNumber() {
        return idNumber;
    }

    /** 
     *  setter for idNumber.
     *  @param idNumber The new idNumber of the item that the
     *  invoice is about.
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     *  The toString() method to print the invoice.
     *  @return A printable String for the invoice. 
     */
    public String toString() {
        return "Invoice for " + description + 
            " (id=" + idNumber
            + ") " + quantity + 
            " item(s) at a price of " + pricePerItem +  " per piece.\n" +
            "due at " + this.getDueDate();
    }

    /**
     *  The paymentAmount() method computes the payment to be done as
     *  the price times the items.
     *  @return The price to be paid as product of price per item
     *  times number of items.
     */
    public int paymentAmount(){
        return (this.getPricePerItem() * this.getQuantity());
    }
}
