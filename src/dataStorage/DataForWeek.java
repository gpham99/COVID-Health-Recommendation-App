package dataStorage;
/**
 * This class represents the covid data for one week 
 * at Colorado College
 * @author elle
 *
 */
public class DataForWeek {
	private String firstDayOfWeek;
	private String lastDayOfWeek;
	private int testsThisWeek;
	private int positiveTestsThisWeek;
	
	/**
	 * This is the constructor for the class
	 * It takes in all the relavent data 
	 * @param firstDay - the first day of the given week
	 * @param lastDay - the last day of the given week
	 * @param numTests - the number of tests performed that week
	 * @param numPositive - the number of positive tests that week
	 */
	public DataForWeek(String firstDay, String lastDay, int numTests, int numPositive) {
		this.firstDayOfWeek = firstDay;
		this.lastDayOfWeek = lastDay;
		this.testsThisWeek = numTests;
		this.positiveTestsThisWeek = numPositive;
	}
	
	/**
	 * This method nicely prints out the information on the given week
	 */
	public String toString() {
		String toPrint = "From " + firstDayOfWeek + " to " + lastDayOfWeek
				+ " " + testsThisWeek + " tests were administered and "
				+ positiveTestsThisWeek + " were positive";
		return toPrint;
	}

	public String getFirstDay() {
		// TODO Auto-generated method stub
		return firstDayOfWeek;
	}

	public String getLastDay() {
		// TODO Auto-generated method stub
		return lastDayOfWeek;
	}

	public int getTests() {
		// TODO Auto-generated method stub
		return testsThisWeek;
	}

	public int getPositives() {
		// TODO Auto-generated method stub
		return positiveTestsThisWeek;
	}
	
}
