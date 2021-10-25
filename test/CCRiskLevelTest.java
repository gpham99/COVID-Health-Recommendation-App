import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dataStorage.CCRiskLevel;
import dataStorage.DataForWeek;

/**
 * This class defines tests for the CCRiskLevel class to confirm it works
 * correctly.
 * 
 * @author annikapiccaro
 *
 */
public class CCRiskLevelTest {
	private CCRiskLevel week1Test;
	private CCRiskLevel week2Test;
	@Before
	public void setUp() {
		week1Test = new CCRiskLevel(new DataForWeek("9/15/2021", "9/22/2021", 252, 0));
		week2Test = new CCRiskLevel(new DataForWeek("9/15/2021", "9/22/2021", 252, 1));
	}
	//Should return Green level because there are no positives
	@Test
	public void greenLevelTest() {
		assertEquals(week1Test.calculateRiskLevel(), CCRiskLevel.GREEN);
	}
	//Should return Red level because 1 positive test in CC's pop 
	//makes it a red zone.
	@Test
	public void redLevelTest() {
		assertEquals(week2Test.calculateRiskLevel(), CCRiskLevel.RED);
	}
}
