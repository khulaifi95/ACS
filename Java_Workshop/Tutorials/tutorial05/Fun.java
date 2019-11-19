import java.util.Arrays;
import java.util.function.Function;
import java.util.ArrayList;

/**
 * This class is part of the tutorial handout of week 6.
 * 
 * @authors Alexandros Evangelidis and Manfred Kerber.
 * @version 02-11-2018
 */
public class Fun {

	/**
	 * Method which prints an array of integers.
	 * 
	 * @param numbers an array of type int.
	 */
	public static void printArray(int[] numbers) {
		int size = numbers.length;
		System.out.print("[");
		for (int i = 0; i < size - 1; i++) {
			System.out.print(numbers[i] + ", ");
		}
		System.out.println(numbers[size - 1] + "]");
	}

	/**
	 * Methods which applies a function to an array of integers.
	 * 
	 * @param a an array of type int.
	 * @param f a function whose both input and output types of the function are
	 *          integers.
	 */
	public static void arrayApply(int[] a, Function<Integer, Integer> f) {
		for (int i = 0; i < a.length; i++) {
			a[i] = f.apply(a[i]);
		}
	}

    	public static void arrayApplyExt(int[] a, Function<Integer, Integer> f) {
            for (int el : a) {
                el = f.apply(el);
            }
	}

	/**
	 * Method which filters an ArrayList of bank accounts.
	 * 
	 * @param allAccounts an ArrayList of bank accounts.
	 * @param f           a function whose input and output types are of type
	 *                    BankAccount and Boolean, respectively.
	 * @return the "filtered" ArrayList.
	 */
	public static ArrayList<BankAccount> filter(ArrayList<BankAccount> allAccounts, Function<BankAccount, Boolean> f) {
		ArrayList<BankAccount> result = new ArrayList<BankAccount>();
		for (BankAccount ba : allAccounts) {
			if (f.apply(ba)) {
				result.add(ba);
			}
		}
		return result;
	}

	/**
	 * main method to test the class.
	 */
	public static void main(String[] args) {

		int[] a = { 1, 5, 7, 4, 5 };
		printArray(a);
		arrayApply(a, x -> {
			return x * x;
		});
                System.out.println("BASIC LOOP");
                printArray(a);

		int[] b = { 1, 5, 7, 4, 5 };
		arrayApplyExt(b, x -> {
			return x * x;
		});
                System.out.println("EXTENDED LOOP");
		printArray(b);
                
		arrayApply(a, x -> {
			return x + 1;
		});
		printArray(a);

		BankAccount mary = new BankAccount(1, "Mary", 200);
		BankAccount john = new BankAccount(2, "John", -1000);
		BankAccount sam = new BankAccount(3, "Sam", 200000);
		BankAccount tony = new BankAccount(4, "Tony", 300000);
		BankAccount ed = new BankAccount(5, "Ed", -250);
		BankAccount jack = new BankAccount(6, "Jack", 500);

		BankAccount[] array = { mary, john, sam, tony, ed, jack };
		ArrayList<BankAccount> allAccounts = new ArrayList<BankAccount>(Arrays.asList(array));

		ArrayList<BankAccount> richOnes = filter(allAccounts, x -> (x.getBalance() >= 100000));
		System.out.println(richOnes);

		ArrayList<BankAccount> inDebt = filter(allAccounts, x -> (x.getBalance() < 0));
		System.out.println(inDebt);

	}
}
