package buttonListeners;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dataStorage.SurveyAnswer;
import guiPages.ActivityRecPage;

/**
 * This class is the activity rec button listener for the AssessmentResults
 *  Activites Recommended For You button
 * A ActivityRecommendation object is created and the screen is repainted
 * @author annikapiccaro
 *
 */
public class ActivityRecButtonListener implements ActionListener{

	private JFrame frame;
	private SurveyAnswer surveyAnswer;
	/**
	 * Constructor of ActivityRecButtonListener
	 * @param frame- the entire assessment Jframe
	 * @param surveyAnswer- the Users survey answer object
	 */
	public ActivityRecButtonListener(JFrame frame, SurveyAnswer surveyAnswer) {
		this.frame=frame;
		this.surveyAnswer=surveyAnswer;
	}
	
	/**
	 * When the button is clicked it sets the ActivityRecomemndation screen
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		setActivityRecScreen();
		
	}
	/**
	 * Repaints the screen and creates an ActivityRecommendation screen
	 */
	private void setActivityRecScreen() {

		frame.getContentPane().removeAll();
		CardLayout cardLayout = new CardLayout();
		JPanel confirmationPane = new JPanel(cardLayout);
		JTextArea surveyConfirmation = new JTextArea(15, 15);
		ActivityRecPage activityRec= new ActivityRecPage(frame, surveyAnswer);

	}

}
