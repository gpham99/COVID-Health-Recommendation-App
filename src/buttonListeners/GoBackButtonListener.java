package buttonListeners;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dataStorage.SurveyAnswer;
import guiPages.AssessmentResults;

/**
 * This class is the Go Back button listener for the Health Recommendation
 *  and Activities Recommendation page's Go Back button
 * An AssessmentResults object is created and the screen is repainted
 * @author annikapiccaro
 */
public class GoBackButtonListener implements ActionListener {

	private SurveyAnswer surveyAnswer;
	private JFrame frame;
	/**
	 * Constructor for GoBackButtonListener
	 * @param frame-the entire assessment Jframe
	 * @param surveyAnswer-the Users survey answer object
	 */
	public GoBackButtonListener(JFrame frame, SurveyAnswer surveyAnswer) {
		this.frame=frame;
		this.surveyAnswer=surveyAnswer;
	}
	@Override
	/**
	 * When the button is clicked it sets the AssessmentPage screen
	 */
	public void actionPerformed(ActionEvent e) {
		setAssessmentPage();
		
	}
	/**
	 * Repaints the screen and creates an Assessment Results screen
	 */
	private void setAssessmentPage() {
		frame.getContentPane().removeAll();
		CardLayout cardLayout = new CardLayout();
		JPanel confirmationPane = new JPanel(cardLayout);
		JTextArea surveyConfirmation = new JTextArea(15, 15);
		AssessmentResults assessmentResults = new AssessmentResults(frame, surveyAnswer );
		
	}

}
