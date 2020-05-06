/** Date is a class for the representation of dates in the form "3 October 2012"
 *  @author   Manfred Kerber
 *  @version  2017-10-04
 */
public class Date{
    private int    day;
    private String month;
    private int    year;
    private String weekDay; // should not be a field variable

    /**
     * This constructor creates a date from the three parts: day,
     * month, and year, which are an int, a String, and an int,
     * respectively.
     * @param day The day of the month in a date.
     * @param month The month as a String, e.g. "October".
     * @param year The year such as 2017.
     */
    public Date (int    day, 
                 String month, 
                 int    year){
        this.day      = day;
        this.month    = month;
        this.year     = year;
        weekDay       = "unkown"; 
    }

    /* Now we write methods to get the parts of a Date,
     * so called accessor methods.
     */

    /** 
     *  @return The day of a Date.
     */
    public int getDay(){
        return day;
    }

    /** 
     *  @return The month of a Date.
     */
    public String getMonth(){
        return month;
    }

    /** 
     *  @return The year of a Date .
     */
    public int getYear(){
        return year;
    }

    /** 
     *  @return The weekDay of a Date 
     */
    public String weekDay(){
        return weekDay;
    }

    /** 
     *  @return The print format of a Date, e.g., 
     *          European style or American style
     */
    public String toString(){
	return day + " " + month + " " + year;   // European way
	//return month + " " + day + ", " + year;   // American way
    }

    /* Now we write methods to set the parts of a Date,
     * so called setters.
     */

    /** 
     *  @param day the day is set to newDay
     */
    public void setDay(int day){
        this.day = day;
    }

    /** 
     *  @param month the month is set to newMonth
     */
    public void setMonth(String month){
        this.month = month;
    }

    /** 
     *  @param year the year is set to newYear
     */
    public void setYear(int year){
        this.year = year;
    }
}
