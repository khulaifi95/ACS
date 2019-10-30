/**
 *   The Date class here is used to pick up on the simpler Date class
 *   from week 2 in order to show how we can restrict the creation of
 *   dates to admissible dates. To this end we write a method
 *   admissible which checks whether inputs of the constructor form a
 *   valid date. If not on constuction an IllegalArgumentException is
 *   thrown. Likewise the setters will have to be restricted to
 *   guarantee the class invariant that the dates are always
 *   admissible.
 *   
 *   @version 2019-10-22
 *   @author Manfred Kerber
 */
public class Date {

    /**
     * Three field variables for day, month, and year of types int,
     * String and int, respectively.
     */
    private int day;
    private String month;
    private int year;

    /**
     *  Array that contains all the month names. Note that MONTHS[0]
     *  corresponds to "January", that is, there is a set-off compared
     *  to the usual abbreviation of months by numbers.
     */
    public static final String[] MONTHS = 
    	{"January", "February", "March", "April",
    	 "May", "June", "July", "August",
    	 "September", "October", "November", "December"};

    /**
     *  The lenghts of the months in the order above. We assume here a
     *  non-leap year, that is, "February" is down with 28 days.
     */
    public static final int[] MONTH_LENGTHS = 
    	{31, 28, 31, 30,
    	 31, 30, 31, 31,
    	 30, 31, 30, 31};

    /**
     *  A year is admissible if it is a positive number
     *  @param year A number potentially representing a year.
     *  @return true if the year is a positive number, false else.
     */
    public static boolean admissibleYear(int year) {
    	return year > 0;
    }

    /**
     *  The method determines whether the String month is in the array
     *  of MONTHS.
     *  @param month A String potentially representing a month.
     *  @return true if the month is one of the 12 Strings in the
     *  MONTHS array, false else.
     */
    public static boolean admissibleMonth(String month) {
    	for (String m : MONTHS) {
    		if (m.equals(month)) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     *  The method returns true if the year is a leap year, that is,
     *  if it is divisible by 4 with the exception of those years
     *  divisible by 100 that are not divisible by 400.
     *  @param A year represented by a number.
     *  @return true if and only if the year is a leap year.
     */
    public static boolean leapYear(int year) {
    	if (year % 400 == 0) {
    		return true;
    	}
    	if (year % 100 == 0) {
    		return false;
    	}
    	if (year % 4 == 0) {
    		return true;
    	}
    	return false;
    }

    /**
     *  The maximal number of days in a month is determined based on
     *  the array MONTH_LENGHTS.
     *  @param month The month as a String, as one of the 12 months in
     *  MONTHS.
     *  @param year The year of the date. It is needed in order to
     *  determine whether the year is a leap year or not.
     *  @return The number of days in the given month and year. That
     *  is, the length of the given month is returned as represented
     *  in the array MONTH_LENGHTS, with the exception of the month
     *  "February" in a leap year, for which 29 is returned.
     */
    public static int maximalNumberOfDaysInMonth(String month, int year) {
        /* We represent "February" here by MONTHS[1] in order to have
         * the implementation as much as possible independent of the
         * language. If somebody wanted to use it instead of for
         * English, for French, they would have just to make a change
         * at a single place, namely the MONTHS array.
         */
    	if (leapYear(year) && month.equals(MONTHS[1])) {
    		return 29;
    	} else {
            /* We find with the following loop first the position of
             * the month in the MONTHS array and then return the
             * corresponding value in the MONTH_LENGTHS array.
             */
    		for (int i = 0; i < 12; i++)
    			if (month.equals(MONTHS[i])) {
    				return MONTH_LENGTHS[i];
    			}
    	}
        // The program should never come to this point on an admissible month.
    	return -1;
    }

    /**
     *  The method determines whether a day is admissible. It is
     *  admissible if the number is between 1 and the maximal number
     *  of days in the month (both included).
     *  @param day The day in the potential date.
     *  @param month The month in the potential date.
     *  @param year The year in the potential date.
     *  @return true if and only if the day is admissible, that is, is
     *  between 1 and the maximal number of days in the month (both
     *  included). We need the month and the year in order to
     *  determine the maximal number of days in the month. The year is
     *  needed to distinguish the length of "February" in leap years
     *  and non-leap years.
     */
    public static boolean admissibleDay(int day, String month, int year) {
    	return 1 <= day && day <= maximalNumberOfDaysInMonth(month, year);
    }
    
    /**
     *  @param day The input of a day such as 21 as an int.
     *  @param month The input of a month such as "October" as a String.
     *  @param year The input of a year such as 2016 as an int.
     *  @return true if the day, the month, and the year are all admissible
     */
    public static boolean admissible(int day, String month, int year) {
    	return admissibleYear(year) &&
            admissibleMonth(month) &&
            admissibleDay(day, month, year);
    }

    /**
     *   Standard toString method to present objects in a human
     *   readable format.
     *   @return The object as a String in a human readable format.
     */
    public String toString() {
        return this.day + " " + this.month + " " + this.year;
    }

    /**
     *  The constructor in the Date class takes in three parameters
     *  for the day, month, and year. If they form together an
     *  admissible Date, the constructor behaves like the constructor
     *  we learned to know in Week 2 of the lecture series. If not, it
     *  throws an IllegalArgumentException.
     *  @param day The input of a day such as 21 as an int.
     *  @param month The input of a month such as "October" as a String.
     *  @param year The input of a year such as 2019 as an int.
     *  @exception If the three parameters given do not form an
     *  admissible Date, the constructor will throw an
     *  IllegalArgumentException.
     */
    public Date(int day, String month, int year) {
    	if (admissible(day, month, year)) {
    		this.day = day;
    		this.month = month;
    		this.year = year;
    	} else {
    		throw new 
    		IllegalArgumentException("Invalid date in class Date.");
    	}
    }
	
    /**
     *  The setter for day behaves like the setter we have seen in
     *  Week 2 of the lecture series if the new date is admissible
     *  with the given month and year. If not, it throws an
     *  IllegalArgumentException.
     *  @param day The new day such as 22 as an int.
     *  @exception If the new day together with the old month and the
     *  old year do not form an admissible Date, the setter will throw
     *  an IllegalArgumentException.
     */
    public void setDay(int day) {
    	if (admissible(day, this.month, this.year)) {
    		this.day = day;
       	} else {
    		throw new 
    		IllegalArgumentException("Invalid date in class Date.");
    	}
    }
    
    /**
     *  The setter for month behaves like the setter we have seen in
     *  Week 2 of the lecture series if the new date is admissible
     *  with the given day and year. If not, it throws an
     *  IllegalArgumentException.
     *  @param month The new month such as "October".
     *  @exception If the new month together with the old day and the
     *  old year do not form an admissible Date, the setter will throw
     *  an IllegalArgumentException.
     */
    public void setMonth(String month) {
    	if (admissible(this.day, month, this.year)) {
    		this.month = month;
       	} else {
    		throw new 
    		IllegalArgumentException("Invalid date in class Date.");
    	}
    }

    /**
     *  The setter for year behaves like the setter we have seen in
     *  Week 2 of the lecture series if the new date is admissible
     *  with the given day and month. If not, it throws an
     *  IllegalArgumentException.
     *  @param year The new year such as 2020 as an int.
     *  @exception If the new year together with the old day and the
     *  old month do not form an admissible Date, the setter will throw
     *  an IllegalArgumentException.
     */
    public void setYear(int year) {
    	if (admissible(this.day, this.month, year)) {
    		this.year = year;
       	} else {
    		throw new 
    		IllegalArgumentException("Invalid date in class Date.");
    	}
    }

    public static void main(String[] args) {
    	try {
    		Date d1 = new Date(23, "October", 2019);
    		//System.out.println(d1);
    		d1.setDay(40);
    		System.out.println(d1);
    		Date d2 = new Date(32, "October", 2019);
    		System.out.println(d2);
    		Date d3 = new Date(20, "Friday", 2019);
    		System.out.println(d3);
    	}
    	catch (IllegalArgumentException e) {
    		System.out.println("Oops, there was a Date " + 
    	   "that was not recognized.");
    	}
    }
}
