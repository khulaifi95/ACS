/** Careful: unicode characters are used in phishing. In this case the
 *  cyrillic o replaces the roman o and leads to a page not belonging to
 *  the assumed domain at all. 
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 *
 */


public class String5 { 
    public static void main(String[] args) {

        System.out.println("For an important security update, please check immediately the patch at");
        
        System.out.println("<a href=\"http://www.micr\uu043Esoft.com\">www.microsoft.com</a>");
        System.out.println("and follow the advice found on this page."); // Cyrillic o
   }
}
