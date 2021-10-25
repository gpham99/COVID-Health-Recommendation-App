package activitesRecommendationStrategies;
import java.util.ArrayList;

import dataStorage.Activity;

public class VeryLowRiskActivitiesStrategy implements ActivitiesStrategy {
	/**
	 * Defines the activity strategy for users that are very low risk based on the assessment. 
	 */
	public VeryLowRiskActivitiesStrategy() {
	}

	/**
	 * Filters through the list of of all activities to find the activities that are for users with
	 * "Very Low" risk level
	 * @param- Arraylist of All activities regardless of risk level
	 * @return- ArrayList of activities
	 */
	public ArrayList<Activity> recommendActivities(ArrayList<Activity> listOfAllActivities) {
		ArrayList<Activity> recommendedActivities = new ArrayList<Activity>();
		for (Activity activity: listOfAllActivities) {
			ArrayList<String> riskLevels = activity.getRiskLevel();
			if (riskLevels.contains("very l") == true) {
				recommendedActivities.add(activity);
			}
		}
		return recommendedActivities;
	}
}