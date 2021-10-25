import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dataStorage.SurveyAnswer;

/**
 * Defines the test that runs whether or not the calculated Risk scores are accurate
 * @author nickbohm555
 *
 */


public class AssessmentResultsTest {

	private ArrayList<SurveyAnswer> listOfUsers;
	private DummyData data;
	
	@Before
	public void setup() {
		data = new DummyData();
		listOfUsers = new ArrayList<SurveyAnswer>(); 
		
	}
	
	@Test
	public void testScoreFirst() {
		listOfUsers = data.GetdummyList();
		assertEquals(12, listOfUsers.get(0).calculateRiskNumber());
	}
	
	@Test
	public void testGroupFirst() {
		listOfUsers = data.GetdummyList();
		assertEquals("Medium / High", listOfUsers.get(0).calculateRiskGroup());
	}

	
	
	@Test
	public void testScoreSecond() {
		listOfUsers = data.GetdummyList();
		assertEquals(4, listOfUsers.get(1).calculateRiskNumber());
	}
	
	@Test
	public void testGroupSecond() {
		listOfUsers = data.GetdummyList();
		assertEquals("Low", listOfUsers.get(1).calculateRiskGroup());
	}
	
	@Test
	public void testScoreThird() {
		listOfUsers = data.GetdummyList();
		assertEquals(0, listOfUsers.get(2).calculateRiskNumber());
	}
	
	@Test
	public void testGroupThird() {
		listOfUsers = data.GetdummyList();
		assertEquals("Very Low", listOfUsers.get(2).calculateRiskGroup());
	}
	
	@Test
	public void testScoreFourth() {
		listOfUsers = data.GetdummyList();
		assertEquals(8, listOfUsers.get(3).calculateRiskNumber());
	}
	
	@Test
	public void testGroupFourth() {
		listOfUsers = data.GetdummyList();
		assertEquals("Medium", listOfUsers.get(3).calculateRiskGroup());
	}
	
	@Test
	public void testScoreFifth() {
		listOfUsers = data.GetdummyList();
		assertEquals(16, listOfUsers.get(4).calculateRiskNumber());
	}
	
	@Test
	public void testGroupFifth() {
		listOfUsers = data.GetdummyList();
		assertEquals("High", listOfUsers.get(4).calculateRiskGroup());
	}

	@Test
	public void testScoreSix() {
		listOfUsers = data.GetdummyList();
		assertEquals(24, listOfUsers.get(5).calculateRiskNumber());
	}
	
	@Test
	public void testGroupSix() {
		listOfUsers = data.GetdummyList();
		assertEquals("Very High", listOfUsers.get(5).calculateRiskGroup());
	}
	

}
