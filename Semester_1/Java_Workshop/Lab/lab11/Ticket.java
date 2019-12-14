/**
 *  In this class we describe a Ticket scenario in a cinema which may
 *  have 3D films on offer. We do not use inheritance for 3D films
 *  here, but have an additional boolean variable to store the
 *  corresponding information. The class consists of the field
 *  variables, constructors and the usual getters and setters.
 *
 *  @version 2016-12-08
 *  @author Manfred Kerber
 */
public class Ticket {
    //static variable as fee for the 3D glasses
    private static int glassesFee = 2;

    /**
     *  Field variables to store the screen in which the film is
     *  shown, the price for the film, whether it is a 3D film, and
     *  whether the customer needs 3D glasses.
     */
    private String screen;
    private int price;
    private boolean film3D;
    private boolean needGlasses;

    /**
     *  Constructor for Ticket
     *  @param screen  The screen in which the film is shown.
     *  @param price  The entrance price for the film.
     */
    public Ticket(String screen, int price) {
        this.screen = screen;
        this.price = price;
        this.film3D = false;
        this.needGlasses = false;
    }
    /**
     *  Constructor for Ticket
     *  @param screen  The screen in which the film is shown.
     *  @param price   The entrance price for the film.
     *  @param film3D  true if and only if the film is a 3D film.
     *  @param needGlasses  true if and only if the customer needs 3D glasses.
     */
    public Ticket(String screen, int price,
                  boolean film3D, boolean needGlasses) {
        this.screen = screen;
        this.price = price;
        this.film3D = film3D;
        this.needGlasses = needGlasses;
    }

    /**
     *  getter for screen
     *  @return The screen 
     */
    public String getScreen() {
        return screen;
    }

    /**
     *  getter for price
     *  @return The price with the fee for the glasses included if the
     *  customer needs them.
     */
    public int getPrice() {
        if (needGlasses) {
            return price + glassesFee;
        } else {
            return price;
        }
    }
    
    /**
     *  getter for film3D
     *  @return true if and only if the film is a 3D film.
     */
    public boolean getFilm3D() {
        return film3D;
    }

    /**
     *  getter for needGlasses
     *  @return true if and only if the customer needs glasses.
     */
    public boolean getNeedGlasses() {
        return needGlasses;
    }

    /**
     *  setter for screen
     *  @param screen The new screen.
     */
    public void setScreen(String screen) {
        this.screen = screen;
    }

    /**
     *  setter for price
     *  @param price The new price.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *  setter for needGlasses
     *  @param needGlasses The new value whether the customer needs glasses.
     */
    public void setNeedGlasses(boolean needGlasses) {
        this.needGlasses = needGlasses;
    }

    /**
     *  setter for film3D
     *  @param film3D The new value whether the film is a 3D film.
     */
    public void setFilm3D(boolean film3D) {
        this.film3D = film3D;
    }

    /**
     *  toString method to produce a printable String for the film.
     *  @return A printable String for the ticket object.
     */    
    public String toString(){
        if (getNeedGlasses()) {
        return "Screen: " + getScreen() + ". Total Price: " + getPrice() + " Needs Glasses";
        }
        return "Screen: " + getScreen() + ". Total Price: " + getPrice();
    }
}
