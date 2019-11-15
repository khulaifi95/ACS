package company;

/** 
 *  @author Manfred Kerber
 *  @version 2016-11-06 
 *
 * The interface provides only the skeleton of a class, that is, here,
 * that there is a method which takes no arguments and gives back an
 * int, the daysOfHolidays that somebody still has available to take.
 */
public interface Holiday {
    /**
     *  @return The number of days of holidays that somebody still has.
     */
   int getDaysOfHolidays();
}
