package buttonListeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LearnMoreButtonListener implements ActionListener {

	private JFrame frame;
	/**
	 * Constructor for LearnMoreButtonListener
	 * @param frame-the entire assessment Jframe
	 * @param surveyAnswer-the Users survey answer object
	 */
	public LearnMoreButtonListener(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * When the button is clicked creates a LearnMore Dialog
	 */
	public void actionPerformed(ActionEvent e) {
		createLearnMoreDialog();
		
	}
	
	/**
	 * Creates a learn more dialog that describes how the risk score was calculated
	 */
	public void createLearnMoreDialog() {
		JOptionPane.showMessageDialog(frame, "Here is how the score was calculated:"
				+ " \n - If a user has both symptoms of covid and was exposed then they have 12 points \n"
				+ "- However, if a user has one of those two then they only have 4 points to their score \n"
				+ "- Users with neither have 0 points \n"
				+ "- If a user is both unvaccinated and in a high risk group then they add 12 points \n"
				+ "- However, if a user has one of those two then they only have 4 points added");
	}

	
	
}
