package activitesRecommendationStrategies;
import java.util.ArrayList;

import dataStorage.Activity;

public class MediumRiskActivitiesStrategy implements ActivitiesStrategy {
	/**
	 * Defines the activity strategy for users that are medium risk based on the assessment. 
	 */
	public MediumRiskActivitiesStrategy() {
	}

	/**
	 * Filters through the list of of all activities to find the activities that are for users with
	 * "Medium" risk level
	 * @param- Arraylist of All activities regardless of risk level
	 * @return- ArrayList of activities
	 */
	public ArrayList<Activity> recommendActivities(ArrayList<Activity> listOfAllActivities) {
		ArrayList<Activity> recommendedActivities = new ArrayList<Activity>();
		for (Activity activity: listOfAllActivities) {
			ArrayList<String> riskLevels = activity.getRiskLevel();
			if (riskLevels.contains("medium") == true) {
				recommendedActivities.add(activity);
			}
		}
		return recommendedActivities;
	}
}