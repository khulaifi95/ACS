import java.util.ArrayList;

/**
 *  main method to test the different User classes of a library.
 *  @version 2016-10-31
 *  @author Manfred Kerber
 */
public class BookMain {
	public static void main(String[] args) {
		// TODO public class Book {
		Book deitel = new Book("Java - How to Program", "Deitel & Deitel", (byte) 1);
		Book horstmann = new Book("Big Java", "Horstmann", (byte) 1);
		Book austen = new Book("Emma", "Austen", (byte) 0);
		
		ArrayList<BookOnLoan> alexBooksOnLoan = new ArrayList<BookOnLoan>();
		PhDUser alex = new PhDUser("Alex", "Evangelidis", "66666", alexBooksOnLoan);
		alex.takeOutBook(deitel);
		System.out.println(alex);
		System.out.println(" Mary's Books --------------------------------------------------");
		
		ArrayList<BookOnLoan> marysBooksOnLoan = new ArrayList<BookOnLoan>();
		MScUser mary = new MScUser("Mary", "Smith", "1111111", marysBooksOnLoan); 
		
		System.out.println(mary);
                mary.takeOutBook(horstmann);
		System.out.println(mary);
		System.out.println("--------------------------------------------------");
      
		ArrayList<BookOnLoan> ahmedBooksOnLoan = new ArrayList<BookOnLoan>();
		MScUser ahmed = new MScUser("Ahmed", "Singh", "2222222", ahmedBooksOnLoan); 
		
		System.out.println(ahmed);
        ahmed.takeOutBook(horstmann);
        ahmed.takeOutBook(deitel);
		System.out.println(ahmed);		
		
		ArrayList<BookOnLoan> emmaBooksOnLoan = new ArrayList<BookOnLoan>();
		CommunityUser emma = 
				new CommunityUser("Emma", "Miller", "0121 333333", emmaBooksOnLoan, 1);
		System.out.println(emma);
		emma.takeOutBook(austen);
		System.out.println(emma);
		System.out.println(emma.getFee());
		
		ArrayList<User> allUsers = new ArrayList<User>();
		
		allUsers.add(alex);
		allUsers.add(mary);
		allUsers.add(emma);
		allUsers.add(ahmed);
		
		System.out.println(allUsers);
	}
}
