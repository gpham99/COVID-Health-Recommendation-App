package buttonListeners;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import dataStorage.SurveyAnswer;
import guiPages.AssessmentForm;
import guiPages.AssessmentResults;

/**
 * This class is the submit button listener for the AssesmentForm Submit button
 * A SurveyAnswer object is created and the screen is repainted
 * @author annikapiccaro
 *
 */
public class SubmitFormButtonListener implements ActionListener {

	private JFrame frame;

	private ArrayList<ButtonGroup> groupList;
	private ArrayList<JRadioButton> buttonList;
	private ArrayList<JRadioButton> chosenButtonList;
	private ArrayList<Boolean> submitButtonList = new ArrayList<Boolean>();

	/**
	 * The constructor for the Submit Form Button Listener
	 * @param groupList- the list of the radio button groups
	 * @param buttonList - the list of all radio buttons
	 * @param frame - the frame to put the contentPane in
	 */
	public SubmitFormButtonListener(ArrayList<ButtonGroup> groupList, ArrayList<JRadioButton> buttonList, JFrame frame) {
		this.groupList = groupList;

		this.frame = frame;

		this.buttonList = buttonList;
	}

	/**
	 * A method that save the buttons chosen by the user to a list
	 * to be accessed later
	 */
	public void getSurveyAnswers() {
		chosenButtonList = new ArrayList<JRadioButton>();
		for (JRadioButton button : buttonList) {
			if (button.isSelected() == true) {
				chosenButtonList.add(button);
			}
		}
	}

	/**
	 * This method is called when the user clicks the submit button
	 * If the form is complete, it records their answers and shows them a confirmation page
	 * If not, it shows them a dialogue to prompt them to complete the form 
	 */
	public void actionPerformed(ActionEvent e) {
		boolean formFinished = formComplete();

		if (formFinished) {

			getSurveyAnswers();
			for (JRadioButton button : chosenButtonList) {

				if (button.getText().equals("Yes")) {
					submitButtonList.add(true);
				} else {
					submitButtonList.add(false);
				}

			}
			setAssessmentResultsScreen();

		} else {
			AssessmentForm.createDialog();
		}

	}
	
	/**
	 * If the user selected all of the buttons
	 * @return-true
	 */
	public boolean formComplete() {
		for (ButtonGroup button : groupList) {
			if (button.getSelection() == null) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Sets the confirmation screen with the survey data
	 */
	private void setAssessmentResultsScreen() {

		frame.getContentPane().removeAll();
		CardLayout cardLayout = new CardLayout();
		JPanel confirmationPane = new JPanel(cardLayout);
		JTextArea surveyConfirmation = new JTextArea(15, 15);
		SurveyAnswer surveyAnswer = new SurveyAnswer(submitButtonList);
		AssessmentResults assessmentResults = new AssessmentResults(frame, surveyAnswer );

	}
}