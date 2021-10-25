package dataStorage;
import java.util.ArrayList;
import java.util.Date;
/**
 * Class that defines an activity given a users risk level, Name, Location, Date and 
 * @author annikapiccaro
 *
 */
public class Activity {
	private String activityName;
	private String activityLocation;
	private String activityDate;
	private String activityDescription;
	private ArrayList<String> riskLevel;
	
	public Activity(String activityName, String activityLocation, String activityDescription, String activityDate, ArrayList<String> riskLevel) {
		this.activityName = activityName;
		this.activityLocation = activityLocation;
		this.activityDate = activityDate;
		this.riskLevel = riskLevel;
		this.activityDescription = activityDescription;
	}
	
	public ArrayList<String> getRiskLevel() {
		return riskLevel;
	}
	
	/**
	 * Creates a string so an Activity object can be printed to the user
	 * @return-String 
	 */
	public String toString() {
		String activitySection = "";
		activitySection += "Name: " + activityName + "\n" + "Location: " + activityLocation + "\n";
		return activitySection;
	}
}