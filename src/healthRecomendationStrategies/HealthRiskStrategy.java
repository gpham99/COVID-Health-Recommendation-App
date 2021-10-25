package healthRecomendationStrategies;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface HealthRiskStrategy {


	/**
	 * Gets a list of health recommendations for the user
	 * @throws FileNotFoundException
	 */
	public ArrayList<String> getHealthRecs() throws FileNotFoundException;
	
	
}
