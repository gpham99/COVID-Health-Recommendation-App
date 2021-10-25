package dataStorage;
/**
 * Represents and calculates the CCRiskLevel for the week based 
 * on https://globalepidemics.org/key-metrics-for-covid-suppression/
 * @author annikapiccaro
 *
 */
public class CCRiskLevel {
	//Total CC community
	//2025-students: https://www.usnews.com/best-colleges/colorado-college-1347
	//232-faculty: https://www.coloradocollege.edu/offices/ipe/instructional-faculty.html
	//578-staff: https://www.coloradocollege.edu/offices/ipe/staff.html
	public static final double CC_TOTAL_POP= 2025+232+578;
	public static final double GREEN_LEVEL_PERCENT=.00001;
	public static final double YELLOW_LEVEL_PERCENT=.00009;
	public static final double ORANGE_LEVEL_PERCENT=.00024;
	public static final double RED_LEVEL_PERCENT=.00025;
	public static final int GREEN=0;
	public static final int YELLOW=1;
	public static final int ORANGE=2;
	public static final int RED=3;
	private String firstDayOfWeek;
	private String lastDayOfWeek;
	private int testsThisWeek;
	private int positiveTestsThisWeek;
	public CCRiskLevel(DataForWeek thisWeek) {
		firstDayOfWeek=thisWeek.getFirstDay();
		lastDayOfWeek=thisWeek.getLastDay();
		testsThisWeek=thisWeek.getTests();
		positiveTestsThisWeek=thisWeek.getPositives();
	}
	public String getFirstDay() {
		return firstDayOfWeek;
	}
	
	public String getLastDay() {
		return lastDayOfWeek;
	}
	/**
	 * Calculates CC's risk level given the positive tests this week
	 * and the entire CC population.
	 * 
	 * Because CC's population is so small 1 or more positive tests will put
	 * the school into a red risk level. The other logic is kept
	 * to ensure that it is properly returning red/green and for
	 * if the population of the school increases. 
	 * @return-int corresponding to the risk level
	 */
	public int calculateRiskLevel() {
		double posPercentage= positiveTestsThisWeek/CC_TOTAL_POP;
		if(posPercentage<=GREEN_LEVEL_PERCENT) {
			return GREEN;
		}else if(posPercentage<=YELLOW_LEVEL_PERCENT) {
			return YELLOW;
		}else if(posPercentage<=ORANGE_LEVEL_PERCENT) {
			return ORANGE;
		}else {//RED_LEVEL_PERCENT
			return RED;
		}
		
	}
}