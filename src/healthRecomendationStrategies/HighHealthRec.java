package healthRecomendationStrategies;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import dataStorage.HealthRecommendations;

/*
 * This class is a subclass of the healthriskStrategy which is responsible for all high risk health recommendation
 * @author Nick Bohm
 */


public class HighHealthRec implements HealthRiskStrategy{
	private final String RISK_LEVEL = "high";
	
	@Override
	public ArrayList<String> getHealthRecs() throws FileNotFoundException {

		HealthRecommendations rec= new HealthRecommendations();
		return rec.getSpecificRecList(RISK_LEVEL);
	}
}
