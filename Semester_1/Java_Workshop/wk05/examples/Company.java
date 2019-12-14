/** 
 *  @author Manfred Kerber
 *  @version 2015-10-27
 *
 *  This class implements the interface Payable. Since in the
 *  interface we have two methods in order to be an implementation of
 *  Payable, this class must give implementations of the two methods
 *  in the interface Payable.java.
 */

public class Company implements Payable {

    private String name;
    private String url;
    private double revenue;

    /**
     *  Constructor for the Company class
     *  @param name The company name.
     *  @param url  The url for the company's web site.
     *  @param revenue The annual revenue of the company.
     */
    public Company(String name, String url, double revenue) {
        this.name = name;
        this.url  = url;
        this.revenue = revenue;
    }
    
    /**
     *  Getter for the name.
     *  @return  The name of the company.
     */
    public String getName() {
        return name;
    }
    
    /**
     *  Getter for the url.
     *  @return  The url for the company's web site.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     *  Getter for the revenue.
     *  @return  The revenue for the company's web site.
     */
    public double getRevenue() {
        return this.revenue;
    }

    /**
     *  Setter for the name of the company.
     *  @param name The new name of the company.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  The usual toString method for printing relevant information about the company.
     *  @return A string representing the company.
     */
    public String toString() {
        return name + " @: " + url;
    }

    /**
     *  An extended toString method for printing relevant information
     *  about the company with more information if verbose is true.
     *  @param verbose A flag to indicate of whether extended information is requested.
     *  @return A string representing the company in more detail if verbose is true.
     */
    public String toString(boolean verbose) {
        if (verbose) {
            return toString() + " revenue: " + revenue;
        } else {
            return toString();
        }
    }

    /**
     *  An equals method.
     *  @param company A company to compare the object to.
     *  @return true if the companies agree in name, url, and revenue (the last, up to a small number), false else.
     */
    public boolean equals(Company company) {
        return this.getName().equals(company.getName()) &&
            this.getUrl().equals(company.getUrl()) &&
            Math.abs(this.getRevenue() - company.getRevenue()) < 0.001;
    }
    
    /** 
     *  A fee that the company has to pay for a particular service (such as accountancy).
     *  @return payable fee.
     */
    public double fee() {
        if (this.getRevenue() <= 500000) {
            return 2000.0;
        } else if (this.getRevenue() <= 1000000) {
            return 0.005 * this.getRevenue();
        } else {
            return 0.004 * this.getRevenue();
        }
    }

    
    /** 
     *  The payment amount is the fee that the company has to pay.
     *  @return payable fee.
     */
    public int paymentAmount() {
        return (int) fee();
    };

    /** 
     *  The due date be always the mid of the month.
     *  @return payable fee.
     */
    public int dueDate() {
        return 15;
    };
    
    /**
     *  Assume the charges for an array of companies has to be cleared
     *  by a bank.
     *  @param companies An array of different companies.
     *  @return The accumulated fee to be paid.
     */
    public static double charges(Company[] companies) {
        double sum = 0;
        for (Company company : companies){
            sum += company.fee();
        }
        return sum;
    }

    /*
     * A main method for testing
     */
    public static void main(String[] args) {
        
        Company c0 = new Company("a", "http://a", 2000000);
        Company c1 = new Company("b", "http://b", 20);
        Company c2 = new Company("c", "http://c", 700000);

        Company[] companies = {c0, c1, c2};

        System.out.println(c0.equals(c0));
        System.out.println(c0.equals(c1));
        System.out.println(c0);
        System.out.println(c0.toString(true));
        System.out.println(charges(companies));

    }
}
