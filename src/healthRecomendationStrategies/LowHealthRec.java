package healthRecomendationStrategies;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import dataStorage.HealthRecommendations;


/*
 * This class is a subclass of the healthriskStrategy which is responsible for all low risk health recommendation
 * @author Nick Bohm
 */

public class LowHealthRec implements HealthRiskStrategy{
	private final String RISK_LEVEL = "low";
	
	@Override
	public ArrayList<String> getHealthRecs() throws FileNotFoundException {
			HealthRecommendations rec= new HealthRecommendations();
			return rec.getSpecificRecList(RISK_LEVEL);	
	}
}
