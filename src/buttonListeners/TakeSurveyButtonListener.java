package buttonListeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import guiPages.AssessmentForm;
import guiPages.HomePage;

/**
 * Defines the listener class that runs when a button is pressed
 * @author nickbohm555
 *
 */

public class TakeSurveyButtonListener implements ActionListener{
	private JFrame frame;
	private boolean disclaimerMessage;
	private int count = 0;

	/**
	 * Creates the take Survey button
	 * @param frame - the JFrame object
	 * @param disclaimerMessage - decides if a disclaimer needs to be shown
	 */
	public TakeSurveyButtonListener(JFrame frame, boolean disclaimerMessage) {
		this.frame = frame;
		this.disclaimerMessage = disclaimerMessage;
	}

	/**
	 * Used when button is pressed.If diclaimerMessage is true, prompt message. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(disclaimerMessage) {
			int a = HomePage.createDialog();
			if (a == 0) {
				setSurveyScreen();
			}
		}
		
		else {
			setSurveyScreen();
		}
	}
	
	/**
	 * Sets the confirmation screen with the survey data
	 */
	private void setSurveyScreen() {
		frame.getContentPane().removeAll();
		AssessmentForm assessmentForm = new AssessmentForm(frame);
		frame.repaint();
		frame.revalidate();
	}
}