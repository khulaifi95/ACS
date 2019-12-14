/**
 * Country class part of the tutorial handout of week 6.
 * 
 * @authors Alexandros Evangelidis and Manfred Kerber.
 * @version 02-11-2018
 */
public class Country implements Measurable {

	private double area;

	/**
	 * Constructor.
	 * 
	 * @param area the area of a country.
	 */
	public Country(double area) {
		this.area = area;
	}

	/**
	 * @return the area of a country.
	 */
	public double getArea() {
		return area;
	}

	@Override
	public double getMeasure() {
		return area;
	}

}
