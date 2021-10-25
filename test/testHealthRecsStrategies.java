import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import healthRecomendationStrategies.HealthRiskStrategy;
import healthRecomendationStrategies.HighHealthRec;
import healthRecomendationStrategies.LowHealthRec;
import healthRecomendationStrategies.MediumHealthRec;
import healthRecomendationStrategies.MediumHighHealthRec;
import healthRecomendationStrategies.VeryHighHealthRec;
import healthRecomendationStrategies.VeryLowHealthRec;

public class testHealthRecsStrategies {
	ArrayList<String> programResult;
	
	ArrayList<String> shouldBeResultVeryLowRiskStrat;
	ArrayList<String> shouldBeResultLowRiskStrat;
	ArrayList<String> shouldBeResultMediumRiskStrat;
	ArrayList<String> shouldBeResultMediumHighRiskStrat;
	ArrayList<String> shouldBeResultHighRiskStrat;
	ArrayList<String> shouldBeResultVeryHighRiskStrat;
	
	@Before
	public void setUp() throws Exception {
		programResult = null;
		
		shouldBeResultLowRiskStrat = new ArrayList<String>();
		shouldBeResultVeryLowRiskStrat = new ArrayList<String>();
		shouldBeResultMediumRiskStrat = new ArrayList<String>();
		shouldBeResultMediumHighRiskStrat = new ArrayList<String>();
		shouldBeResultHighRiskStrat = new ArrayList<String>();
		shouldBeResultVeryHighRiskStrat = new ArrayList<String>();
				
		// very low risk
		shouldBeResultVeryLowRiskStrat.add("Wear a mask indoors");
		shouldBeResultVeryLowRiskStrat.add("Avoid large indoor gatherings");

		// low risk
		shouldBeResultLowRiskStrat.add("Wear a mask indoors");
		shouldBeResultLowRiskStrat.add("Avoid large indoor gatherings");
		
		// medium
		shouldBeResultMediumRiskStrat.add("Wear a mask indoors");
		shouldBeResultMediumRiskStrat.add("Self monitor for symptoms");
		shouldBeResultMediumRiskStrat.add("Take a COVID test");
		shouldBeResultMediumRiskStrat.add("Avoid large indoor gatherings");
		shouldBeResultMediumRiskStrat.add("Practice social distancing");
		
		// medium/high
		shouldBeResultMediumHighRiskStrat.add("Wear a mask indoors");
		shouldBeResultMediumHighRiskStrat.add("Self monitor for symptoms");
		shouldBeResultMediumHighRiskStrat.add("Take a COVID test");
		
		shouldBeResultMediumHighRiskStrat.add("Avoid large outdoor gatherings");
		shouldBeResultMediumHighRiskStrat.add("Avoid large indoor gatherings");
		shouldBeResultMediumHighRiskStrat.add("Practice social distancing");
		
		// high
		shouldBeResultHighRiskStrat.add("Wear a mask indoors");
		shouldBeResultHighRiskStrat.add("Self monitor for symptoms");
		shouldBeResultHighRiskStrat.add("Take a COVID test");
		shouldBeResultHighRiskStrat.add("Avoid large outdoor gatherings");
		
		shouldBeResultHighRiskStrat.add("Avoid large indoor gatherings");
		shouldBeResultHighRiskStrat.add("Only attend virtual events");
		shouldBeResultHighRiskStrat.add("Practice social distancing");
		shouldBeResultHighRiskStrat.add("Consider quarantining for 14 days");
		
		// very high
		shouldBeResultVeryHighRiskStrat.add("Wear a mask indoors");
		shouldBeResultVeryHighRiskStrat.add("Self monitor for symptoms");
		shouldBeResultVeryHighRiskStrat.add("Take a COVID test");
		shouldBeResultVeryHighRiskStrat.add("Avoid large outdoor gatherings");
		
		shouldBeResultVeryHighRiskStrat.add("Avoid large indoor gatherings");
		shouldBeResultVeryHighRiskStrat.add("Only attend virtual events");
		shouldBeResultVeryHighRiskStrat.add("Practice social distancing");
		shouldBeResultVeryHighRiskStrat.add("Consider quarantining for 14 days");
		shouldBeResultVeryHighRiskStrat.add("Quarantine for 14 days and monitor symptoms");
	}
	
	@Test
	public void testVeryLowRiskStrat() throws FileNotFoundException {
		HealthRiskStrategy veryLowRiskStrat = new VeryLowHealthRec();
		programResult = veryLowRiskStrat.getHealthRecs();
		assertEquals(shouldBeResultVeryLowRiskStrat, programResult);
	}
	
	@Test
	public void testLowRiskStrat() throws FileNotFoundException {
		HealthRiskStrategy lowRiskStrat = new LowHealthRec();
		programResult = lowRiskStrat.getHealthRecs();
		assertEquals(shouldBeResultLowRiskStrat, programResult);
	}
	
	@Test
	public void testMediumRiskStrat() throws FileNotFoundException {
		HealthRiskStrategy mediumRiskStrat = new MediumHealthRec();
		programResult = mediumRiskStrat.getHealthRecs();
		assertEquals(shouldBeResultMediumRiskStrat, programResult);
	}
	
	@Test
	public void testMediumHighRiskStrat() throws FileNotFoundException {
		HealthRiskStrategy mediumHighRiskStrat = new MediumHighHealthRec();
		programResult = mediumHighRiskStrat.getHealthRecs();
		assertEquals(shouldBeResultMediumHighRiskStrat, programResult);
	}
	
	@Test
	public void testHighRiskStrat() throws FileNotFoundException {
		HealthRiskStrategy highRiskStrat = new HighHealthRec();
		programResult = highRiskStrat.getHealthRecs();
		assertEquals(shouldBeResultHighRiskStrat, programResult);
	}
	
	@Test
	public void testVeryHighRiskStrat() throws FileNotFoundException {
		HealthRiskStrategy veryHighRiskStrat = new VeryHighHealthRec();
		programResult = veryHighRiskStrat.getHealthRecs();
		assertEquals(shouldBeResultVeryHighRiskStrat, programResult);
	}
}