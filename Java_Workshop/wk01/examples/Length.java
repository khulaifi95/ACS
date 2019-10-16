/* Java is a strictly typed language, that is, all variables must be
 * declared with type or a class.  For now we look at two, int for
 * integers in the range -2,147,483,648 to 2,147,483,647, and String
 * for strings such as "Hello".
 */

public class Length

/* We declare a variable by the type/class name and its name,
 * e.g., int a;   or    String str;
 */

{
   public static void main(String[] args)
   {
	int l;
        String str;

        str = args[0];
	l = str.length();
	System.out.println("The length of the string \"" + str + "\" is " + l + ".");
   }
}
