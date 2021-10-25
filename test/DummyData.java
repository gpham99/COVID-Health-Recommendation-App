import java.util.ArrayList;

import dataStorage.SurveyAnswer;

/**
 * Defines the dummy data that stores the data of the survey inputs.
 * @author nickbohm555
 *
 */

public class DummyData {

	private ArrayList<SurveyAnswer> listOfUsers;
	
	
	public DummyData() {
		listOfUsers = new ArrayList<SurveyAnswer>();
		ArrayList<Boolean> answers = new ArrayList<Boolean>(); 
		answers.add(false);
		answers.add(false);
		answers.add(false);
		answers.add(true);
		
		SurveyAnswer surveyAnswers = new SurveyAnswer(answers);
		listOfUsers.add(surveyAnswers);
		
		answers = new ArrayList<Boolean>(); 
		answers.add(true);
		answers.add(false);
		answers.add(true);
		answers.add(false);
		
		surveyAnswers = new SurveyAnswer(answers);
		listOfUsers.add(surveyAnswers);
		
		answers = new ArrayList<Boolean>(); 
		answers.add(false);
		answers.add(false);
		answers.add(true);
		answers.add(false);
		
		surveyAnswers = new SurveyAnswer(answers);
		listOfUsers.add(surveyAnswers);
		
		answers = new ArrayList<Boolean>(); 
		answers.add(true);
		answers.add(false);
		answers.add(true);
		answers.add(true);
		
		surveyAnswers = new SurveyAnswer(answers);
		listOfUsers.add(surveyAnswers);
		
		answers = new ArrayList<Boolean>(); 
		answers.add(true);
		answers.add(true);
		answers.add(true);
		answers.add(true);
		
		surveyAnswers = new SurveyAnswer(answers);
		listOfUsers.add(surveyAnswers);
		
		answers = new ArrayList<Boolean>(); 
		answers.add(true);
		answers.add(true);
		answers.add(false);
		answers.add(true);
		
		surveyAnswers = new SurveyAnswer(answers);
		
		listOfUsers.add(surveyAnswers);
		
		
	}
	
	public ArrayList<SurveyAnswer> GetdummyList(){
		return this.listOfUsers;
	}
	
	
	
}
