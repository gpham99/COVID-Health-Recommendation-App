package healthRecomendationStrategies;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import dataStorage.HealthRecommendations;

/*
 * This class is a subclass of the healthriskStrategy which is responsible for all very low risk health recommendation
 * @author Nick Bohm
 */

public class VeryLowHealthRec implements HealthRiskStrategy{
	private final String RISK_LEVEL = "very l";
	@Override
	public ArrayList<String> getHealthRecs() throws FileNotFoundException {
		HealthRecommendations rec= new HealthRecommendations();
		return rec.getSpecificRecList(RISK_LEVEL);	
	}
}