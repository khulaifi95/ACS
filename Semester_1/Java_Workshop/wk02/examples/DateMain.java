/** DateMain class is trying out Dates as defined in the Date.java class
 *  @author   Manfred Kerber
 *  @version  2018-08-20
 */
public class DateMain {
    public static void main(String[] args) {
	Date lectureDate = 
           new Date(10, "October", 2018);

        Date endOfTerm =
	   new Date(14, "December", 2018);

        System.out.println("The month of today's lecture is " 
                            + lectureDate.getMonth() + ".");

	//        System.out.println("The day of today's lecture is " 
        //                    + lectureDate.day + ".");

        System.out.println("The year of today's lecture is " 
                            + lectureDate.getYear() + ".");

        System.out.println("The weekday of today's lecture is " 
                            + lectureDate.weekDay() + ".");


        System.out.println("The last month of the autumn term is " 
                            + endOfTerm.getMonth() + ".");

        System.out.println("The autumn term ends still in the year " 
                            + endOfTerm.getYear() + ".");

        System.out.println("The last day of the term is " 
                            + endOfTerm.weekDay() + ".");

	System.out.println(lectureDate);
	System.out.println(endOfTerm);

        lectureDate.setDay(11);
	System.out.println(lectureDate);

    }
}
