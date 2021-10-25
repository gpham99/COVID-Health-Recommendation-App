package activitesRecommendationStrategies;
import java.util.ArrayList;

import dataStorage.Activity;
/**
 * Defines the activity strategy for users that are low risk based on the assessment. 
 * 
 *
 */
public class LowRiskActivitiesStrategy implements ActivitiesStrategy {

	public LowRiskActivitiesStrategy() {
	}

	/**
	 * Filters through the list of of all activities to find the activities that are for users with
	 * "low" risk level
	 * @param- Arraylist of All activities regardless of risk level
	 * @return- ArrayList of activities
	 */
	public ArrayList<Activity> recommendActivities(ArrayList<Activity> listOfAllActivities) {
		ArrayList<Activity> recommendedActivities = new ArrayList<Activity>();
		for (Activity activity: listOfAllActivities) {
			ArrayList<String> riskLevels = activity.getRiskLevel();
			if (riskLevels.contains("low") == true) {
				recommendedActivities.add(activity);
			}
		}
		return recommendedActivities;
	}
}