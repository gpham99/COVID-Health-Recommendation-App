package dataStorage;
import java.awt.Color;
import java.util.ArrayList;
/**
 * Defines the SurveyAnswer class that holds the answers the user put into the survey
 * @author annikapiccaro
 *
 */
public class SurveyAnswer {
	private ArrayList<Boolean> answers;
	
	private String questionOne = "Are you experiencing any of these symptoms: fever, chills, cough, shortness of breath, difficulty breathing, loss of taste or smell?";
	private String questionTwo = "Were you directly exposed to COVID-19 in the last 2 weeks?";
	private String questionThree = "Are you fully vaccinated? (Two weeks from your last dose)";
	private String questionFour = "Are you in a high risk group? (Including the elderly, immunocompromised, living in a congregate setting etc.)";
	private String[] questions = {questionOne, questionTwo, questionThree, questionFour};
	

	private static final Color GREEN = new Color(168, 230, 169);
	private static final Color YELLOW_GREEN=new Color(184, 235, 167);
	private static final Color YELLOW= new Color(237,203,89);
	private static final Color YELLOW_ORANGE= new Color(224, 174, 108);
	private static final Color LIGHT_RED= new Color(209, 141, 141);
	private static final Color RED=  new Color(201, 99, 99);
	
	private static final int LOW_RISK_ADDED= 4;
	private static final int HIGH_RISK_ADDED = 12;
	
	private static final int VERY_LOW_RISK = 0;
	private static final int LOW_RISK = 4;
	private static final int MEDIUM_RISK = 8;
	private static final int MEDIUM_HIGH_RISK = 12;
	private static final int HIGH_RISK = 16;
	private static final int VERY_HIGH_RISK = 24;
	/**
	 * The constructor for the surveyAnswer
	 * @param answers - the list of answers to the questions
	 */
	public SurveyAnswer( ArrayList<Boolean> answers) {
		this.answers = answers;
	}
	
	/**
	 * Creates the string so the Q&A can be printed.
	 */
	public String toString() {
		
		String surveyAnswers="";
		int count = 0;
		for (boolean answer : answers) {
			surveyAnswers+=questions[count]+"\n";
			surveyAnswers+=("response is: "+ answer+"\n\n");
			count+=1;
		}
		surveyAnswers+=("Your Risk Level is: "+ this.calculateRiskGroup() );
		return surveyAnswers;
	}

	
	/**
	 * Calculates a score between 0-24 indicating the risk score
	 * @return an integer between 0-24
	 */
	public int calculateRiskNumber( ) {
		int score = 0;
		
		if (answers.get(0) == true && answers.get(1) == true) {
			score += HIGH_RISK_ADDED;
						
		}
		else if (answers.get(0) == true || answers.get(1) == true) {
			score += LOW_RISK_ADDED;
		}
			
		if (answers.get(2)== false && answers.get(3)== true   ) {
			score += HIGH_RISK_ADDED;
		}	
		else if (answers.get(2)== false || answers.get(3)== true  ) {
			score += LOW_RISK_ADDED;
		}	
		return score;
	}
	
	/**
	 * Calculates the risk group based off the risk score between 0-24
	 * @return a String of the risk group
	 */
	public String calculateRiskGroup() {
		int score = this.calculateRiskNumber() ;
		
		if (score == VERY_LOW_RISK ) {
			return "Very Low";
		}
		if (score == LOW_RISK ) {
			return "Low";
		}
		if (score == MEDIUM_RISK ) {
			return "Medium";
		}
		if (score == MEDIUM_HIGH_RISK) {
			return "Medium / High";
		}
		if (score == HIGH_RISK) {
			return "High";
		}
		if (score == VERY_HIGH_RISK) {
			return "Very High";
		}
		else {
			return null;
		}
	}
	
	public String getMessage() {
		int score = this.calculateRiskNumber() ;
		
		if (score == VERY_LOW_RISK) {
			return "\nGood job! Just keep on doing what you're doing and you'll be in good shape";
		}
		if (score == LOW_RISK ) {
			return "\nWay to go! Don't forget to keep wearing your mask in public to be extra safe";
		}
		if (score == MEDIUM_RISK ) {
			return "\nDon't give up on safety precautions yet! Keep social distancing and wearing a mask";
		}
		if (score == MEDIUM_HIGH_RISK ) {
			return "\nWe're still in a pandemic. Don't forget to be careful and maintian social distancing";
		}
		if (score == HIGH_RISK ) {
			return "\nConsider getting vaccinated if you haven't already to keep yourself and others safe";
		}
		if (score == VERY_HIGH_RISK ) {
			return "\nFeel better soon! In the meantime, check out some safe activities";
		}
		else {
			return null;
		}
	}
	/**
	 * This method returns the color associated with the user's risk level
	 * @return color of risk level
	 */
	public Color getDisplayColor() {
		int score = this.calculateRiskNumber() ;
		
		if (score == VERY_LOW_RISK ) {
			return GREEN;
		}
		if (score == LOW_RISK ) {
			return YELLOW_GREEN;
		}
		if (score == MEDIUM_RISK ) {
			return YELLOW;
		}
		if (score == MEDIUM_HIGH_RISK ) {
			return YELLOW_ORANGE;
		}
		if (score == HIGH_RISK ) {
			return LIGHT_RED;
		}
		if (score == VERY_HIGH_RISK ) {
			return RED;
		}
		else {
			return null;
		}
	}
}