package buttonListeners;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dataStorage.SurveyAnswer;
import guiPages.HealthRecPage;

/**
 * This class is the health rec button listener for the AssessmentResults
 *  Health Steps Recommended For You button
 * A HealthRecommendation object is created and the screen is repainted
 * @author annikapiccaro
 */
public class HealthStepsButtonListener implements ActionListener {
	private JFrame frame;
	private SurveyAnswer surveyAnswer;
	/**
	 * Constructor for HealthStepsButtonListener
	 * @param frame-the entire assessment Jframe
	 * @param surveyAnswer-the Users survey answer object
	 */
	public HealthStepsButtonListener(JFrame frame, SurveyAnswer surveyAnswer) {
		this.frame=frame;
		this.surveyAnswer=surveyAnswer;
;
	}
	/**
	 * When the button is clicked set the Health Results screen
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			setHealthResultsScreen();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Repaints the screen and creates an HealthRecommendation screen
	 * @throws FileNotFoundException 
	 */
	private void setHealthResultsScreen() throws FileNotFoundException {

		frame.getContentPane().removeAll();
		CardLayout cardLayout = new CardLayout();
		JPanel confirmationPane = new JPanel(cardLayout);
		JTextArea surveyConfirmation = new JTextArea(15, 15);
		HealthRecPage healthRec= new HealthRecPage(frame, surveyAnswer);

	}

}
