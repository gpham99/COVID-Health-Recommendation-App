package activitesRecommendationStrategies;
import java.util.ArrayList;

import dataStorage.Activity;
/**
 * Interface that defines the activities the user gets based on their score
 * @author annikapiccaro
 *
 */
public interface ActivitiesStrategy {
	public ArrayList<Activity> recommendActivities(ArrayList<Activity> listOfAllActivities);
}