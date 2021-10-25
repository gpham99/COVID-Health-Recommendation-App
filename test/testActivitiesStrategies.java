import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import activitesRecommendationStrategies.ActivitiesStrategy;
import activitesRecommendationStrategies.HighRiskActivitiesStrategy;
import activitesRecommendationStrategies.LowRiskActivitiesStrategy;
import activitesRecommendationStrategies.MediumHighRiskActivitiesStrategy;
import activitesRecommendationStrategies.MediumRiskActivitiesStrategy;
import activitesRecommendationStrategies.VeryHighRiskActivitiesStrategy;
import activitesRecommendationStrategies.VeryLowRiskActivitiesStrategy;
import dataStorage.Activity;

public class testActivitiesStrategies {
	ArrayList<Activity> listOfAllActivities;
	ArrayList<Activity> programResult;
	
	ArrayList<Activity> shouldBeResultVeryLowRiskStrat;
	ArrayList<Activity> shouldBeResultLowRiskStrat;
	ArrayList<Activity> shouldBeResultMediumRiskStrat;
	ArrayList<Activity> shouldBeResultMediumHighRiskStrat;
	ArrayList<Activity> shouldBeResultHighRiskStrat;
	ArrayList<Activity> shouldBeResultVeryHighRiskStrat;
	
	@Before
	public void setUp() throws Exception {
		listOfAllActivities = new ArrayList<Activity>();
		programResult = null;
		
		shouldBeResultLowRiskStrat = new ArrayList<Activity>();
		shouldBeResultVeryLowRiskStrat = new ArrayList<Activity>();
		shouldBeResultMediumRiskStrat = new ArrayList<Activity>();
		shouldBeResultMediumHighRiskStrat = new ArrayList<Activity>();
		shouldBeResultHighRiskStrat = new ArrayList<Activity>();
		shouldBeResultVeryHighRiskStrat = new ArrayList<Activity>();
		
		ArrayList<String> riskLevel1 = new ArrayList<String>();
		riskLevel1.add("low");
		riskLevel1.add("very l");
		Activity activity1 = new Activity("MU301 Composition Class Concert", "Packard", "Concert put on by the MU301 Composition Class", "9/21/2021", riskLevel1);
		
		ArrayList<String> riskLevel2 = new ArrayList<String>();
		riskLevel2.add("low");
		riskLevel2.add("very l");
		riskLevel2.add("medium");
		Activity activity2 = new Activity("Excel at CC - Sustainability 101", 
		"Timothy Fuller Event Space", "Gain a better understanding of the breadth of sustainability topics... ", 
		"9/24/2021", riskLevel2);
		
		ArrayList<String> riskLevel3 = new ArrayList<String>();
		riskLevel3.add("low");
		Activity activity3 = new Activity("First Monday Block 2-Darnell Moore", "Packard", 
		"a conversation with Darnell Moore, Director of Inclusion for Content and Marketing at Netflix", 
		"", riskLevel3);
		
		ArrayList<String> riskLevel4 = new ArrayList<String>();
		riskLevel4.add("medium");
		riskLevel4.add("m/h");
		Activity activity4 = new Activity("LGBTQ+ Oral History Project Launch", "Packard", "Concert put on by the MU301 Composition Class", "9/21/2021", riskLevel4);
		
		ArrayList<String> riskLevel5 = new ArrayList<String>();
		riskLevel5.add("very h");
		riskLevel5.add("high");
		riskLevel5.add("m/h");
		Activity activity5 = new Activity("Dismantling Anti-Black Racism", "Packard", "Concert put on by the MU301 Composition Class", "9/21/2021", riskLevel5);
		
		
		listOfAllActivities.add(activity1);
		listOfAllActivities.add(activity2);
		listOfAllActivities.add(activity3);
		listOfAllActivities.add(activity4);
		listOfAllActivities.add(activity5);
		
		// very low risk
		shouldBeResultVeryLowRiskStrat.add(activity1);
		shouldBeResultVeryLowRiskStrat.add(activity2);

		// low risk
		shouldBeResultLowRiskStrat.add(activity1);
		shouldBeResultLowRiskStrat.add(activity2);
		shouldBeResultLowRiskStrat.add(activity3);
		
		// medium
		shouldBeResultMediumRiskStrat.add(activity2);
		shouldBeResultMediumRiskStrat.add(activity4);
		
		// medium/high
		shouldBeResultMediumHighRiskStrat.add(activity4);
		shouldBeResultMediumHighRiskStrat.add(activity5);
		
		// high
		shouldBeResultHighRiskStrat.add(activity5);
		
		// very high
		shouldBeResultVeryHighRiskStrat.add(activity5);
	}
	
	@Test
	public void testVeryLowRiskStrat() {
		ActivitiesStrategy veryLowRiskStrat = new VeryLowRiskActivitiesStrategy();
		programResult = veryLowRiskStrat.recommendActivities(listOfAllActivities);
		assertEquals(shouldBeResultVeryLowRiskStrat, programResult);
	}
	
	@Test
	public void testLowRiskStrat() {
		ActivitiesStrategy lowRiskStrat = new LowRiskActivitiesStrategy();
		programResult = lowRiskStrat.recommendActivities(listOfAllActivities);
		assertEquals(shouldBeResultLowRiskStrat, programResult);
	}
	
	@Test
	public void testMediumRiskStrat() {
		ActivitiesStrategy mediumRiskStrat = new MediumRiskActivitiesStrategy();
		programResult = mediumRiskStrat.recommendActivities(listOfAllActivities);
		assertEquals(shouldBeResultMediumRiskStrat, programResult);
	}
	
	@Test 
	public void testMediumHighRiskStrat() {
		ActivitiesStrategy mediumHighRiskStrat = new MediumHighRiskActivitiesStrategy();
		programResult = mediumHighRiskStrat.recommendActivities(listOfAllActivities);
		assertEquals(shouldBeResultMediumHighRiskStrat, programResult);
	}
	
	@Test
	public void testHighRiskStrat() {
		ActivitiesStrategy highRiskStrat = new HighRiskActivitiesStrategy();
		programResult = highRiskStrat.recommendActivities(listOfAllActivities);
		assertEquals(shouldBeResultHighRiskStrat, programResult);
	}
	
	@Test
	public void testVeryHighRiskStrat() {
		ActivitiesStrategy veryHighRiskStrat = new VeryHighRiskActivitiesStrategy();
		programResult = veryHighRiskStrat.recommendActivities(listOfAllActivities);
		assertEquals(shouldBeResultVeryHighRiskStrat, programResult);
	}
}