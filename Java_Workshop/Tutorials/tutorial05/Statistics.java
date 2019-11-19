/**
 * This class is part of the tutorial handout of week 6.
 * 
 * @authors Alexandros Evangelidis and Manfred Kerber.
 * @version 02-11-2018
 */
public class Statistics {

	/**
	 * This method computes the average area of an array of countries.
	 * 
	 * @param countries an array of type Country.
	 * @return the average area.
	 */
	public static double areaAverage(Country[] countries) {
		double sum = 0;
		for (int i = 0; i < countries.length; i++) {
			sum += countries[i].getArea();
		}
		return sum / countries.length;
	}

	/**
	 * This method computes the average balance of an array of bank accounts.
	 * 
	 * @param accounts an array of type BankAccount.
	 * @return the average balance.
	 */
	public static double balanceAverage(BankAccount[] bankAccounts) {
		double sum = 0;
		for (int i = 0; i < bankAccounts.length; i++) {
			sum += bankAccounts[i].getBalance();
		}
		return sum / bankAccounts.length;
	}

	/**
	 * This method computes the average measure of an array of measurable.
	 * 
	 * @param measurable an array of type Measurable.
	 * @return the average measure.
	 */
	public static double average(Measurable[] measurable) {
		double sum = 0;
		for (int i = 0; i < measurable.length; i++) {
			sum += measurable[i].getMeasure();
		}
		return sum / measurable.length;
	}

	/**
	 * main method to test class.
	 */
	public static void main(String[] args) {

		// First compute the average of an array of
		// bank accounts using the getBalance() method.
		BankAccount bk1 = new BankAccount(1000);
		BankAccount bk2 = new BankAccount(2250);
		BankAccount bk3 = new BankAccount(3845);

		BankAccount[] bankAccountArray = { bk1, bk2, bk3 };

		System.out.println((balanceAverage(bankAccountArray)));

		// First compute the average of an array of
		// countries using the getArea() method.
		Country country1 = new Country(15679);
		Country country2 = new Country(25654);
		Country country3 = new Country(54865);

		Country[] countryArray = { country1, country2, country3 };

		System.out.println((areaAverage(countryArray)));

		// Then compute the average of an array of
		// type Measurable using the getMeasure() method.
		Measurable country4 = new Country(15679);
		Measurable country5 = new Country(54865);
		Measurable bk4 = new BankAccount(25654);
		Measurable bk5 = new BankAccount(54865);

		Measurable[] measurableArray = { country4, country5, bk4, bk5 };

		System.out.println(average(measurableArray));
	}

}
