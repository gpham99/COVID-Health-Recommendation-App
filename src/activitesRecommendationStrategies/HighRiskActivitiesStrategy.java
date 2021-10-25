package activitesRecommendationStrategies;
import java.util.ArrayList;

import dataStorage.Activity;
/**
 * Defines the activity strategy for users that are high risk based on the assessment. 
 * 
 *
 */
public class HighRiskActivitiesStrategy implements ActivitiesStrategy {

	public HighRiskActivitiesStrategy() {
	}
	/**
	 * Filters through the list of of all activities to find the activities that are for users with
	 * "High" risk level
	 * @param- Arraylist of All activities regardless of risk level
	 * @return- ArrayList of activities
	 */
	@Override
	public ArrayList<Activity> recommendActivities(ArrayList<Activity> listOfAllActivities) {
		ArrayList<Activity> recommendedActivities = new ArrayList<Activity>();
		for (Activity activity: listOfAllActivities) {
			ArrayList<String> riskLevels = activity.getRiskLevel();
			if (riskLevels.contains("high") == true) {
				recommendedActivities.add(activity);
			}
		}
		return recommendedActivities;
	}
}