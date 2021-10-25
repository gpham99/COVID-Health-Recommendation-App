package guiPages;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import buttonListeners.ActivityRecButtonListener;
import buttonListeners.HealthStepsButtonListener;
import buttonListeners.TakeSurveyButtonListener;
import buttonListeners.LearnMoreButtonListener;
import dataStorage.SurveyAnswer;

/**
 * Defines and creates the Assessment Result visual with a calculated risk level.
 * The box that houses the result corresponds in color to which risk level
 * the user is in. A Health Recommendation Button, Activity Recommendation Button
 * and Learn More Button are created
 *
 */

public class AssessmentResults {
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;
	private static final int LEFT_MARGIN = 300;
	private static final int BOX_WIDTH = 400;
	private static final int BOX_HEIGHT = 400;
	
	private static final int TITLE_WIDTH = FRAME_WIDTH;
	private static final int TITLE_HEIGHT = 50;
	
	private static final int BUTTON_HEIGHT = 50;
	
	private static final int TOP_MARGIN = 20;
	
	// font values
	private static final Font titleFont = new Font("Dialog", Font.BOLD, 40);
	private static final Font riskLevelBoxFont = new Font("Dialog", Font.PLAIN, 30);
	private static final Font buttonFont = new Font("Dialog", Font.PLAIN, 20);
	
	
	private static final Color BACKGROUND_BLUE = new Color(176, 201, 207);
	private static final Color BUTTON_YELLOW = new Color(182,149,99);
	private static final Color CUSTOM_PINK = new Color(209, 141, 141);
	
	private static JFrame frame;
	private static SurveyAnswer surveyAnswer;
	private static JPanel p;
	private static Dimension size;
	private ArrayList<ButtonGroup> buttonGroups = new ArrayList<>();
	
	/**
	 * Constructs a new assessmentResults page that displays the user's risk level
	 * @param frame - the Jframe object
	 * @param surveyAnswer - the user's answer to the survey
	 */
	public AssessmentResults(JFrame frame, SurveyAnswer surveyAnswer) {
		this.frame = frame;
		this.surveyAnswer = surveyAnswer;
		
		p = new JPanel();
		p.setBackground(BACKGROUND_BLUE);
		p.setLayout(null);

		this.createTitle();
		this.createLearnMoreButton();
		this.createRiskLevelBox();
		this.createRetakeButton();
		this.createHealthStepsButton();
		this.createCampusActivitesButton();
		

		this.frame.add(p);
		frame.setVisible(true);
	}
	
	/**
	 * Creates a learn more button
	 */
	private void createLearnMoreButton() {
		
			JButton learnMore = new JButton("Learn More ");
			learnMore.setFont(buttonFont);
				
			learnMore.setBackground(BUTTON_YELLOW);
			size = learnMore.getPreferredSize();
			learnMore.setBounds((FRAME_WIDTH-size.width)/2, (TOP_MARGIN*2) + TITLE_HEIGHT + (BOX_HEIGHT-size.height-TOP_MARGIN), size.width, size.height);
			
			
			LearnMoreButtonListener learnMoreListener = new LearnMoreButtonListener(frame);
			learnMore.addActionListener(learnMoreListener);
				
			p.add(learnMore);
		
	}

	/**
	 * Labels the form - Personalized Risk Assessment
	 */
	public void createTitle() {
		JLabel title = new JLabel("Risk Assessment Results", SwingConstants.CENTER);
		title.setFont(titleFont);
		title.setBounds(0, TOP_MARGIN, TITLE_WIDTH, TITLE_HEIGHT);
		p.add(title);
	}

	/**
	 * Create a text box to display the user's risk level
	 */
	public void createRiskLevelBox() {
		JTextPane riskLevelBoxText = new JTextPane();
		riskLevelBoxText.setText("Your risk Level is: \n "+ surveyAnswer.calculateRiskNumber()+ " / 24\n"+ this.surveyAnswer.calculateRiskGroup()
		+ "\n" + surveyAnswer.getMessage());
		riskLevelBoxText.setFont(riskLevelBoxFont);
		riskLevelBoxText.setMargin(new Insets(20, 20, 20, 20));
		
		// center the text inside the box
		StyledDocument doc = riskLevelBoxText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		// set the background color for the risk level box
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane.background", new ColorUIResource(surveyAnswer.getDisplayColor()));
        defaults.put("TextPane[Enabled].backgroundPainter", surveyAnswer.getDisplayColor());
        riskLevelBoxText.putClientProperty("Nimbus.Overrides", defaults);
        riskLevelBoxText.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        riskLevelBoxText.setBackground(surveyAnswer.getDisplayColor());
		
		riskLevelBoxText.setEditable(false);
		riskLevelBoxText.setBounds((FRAME_WIDTH - BOX_WIDTH) / 2, TOP_MARGIN * 2 + TITLE_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
		p.add(riskLevelBoxText);
	}

	
	/**
	 * Creates the retake button at the end of the form
	 */
	public void createRetakeButton() {
		// JButton
		JButton retakeButton = new JButton("Retake the assessment");
		retakeButton.setFont(buttonFont);
		retakeButton.setBackground(BUTTON_YELLOW);
		
		size = retakeButton.getPreferredSize();
		retakeButton.setBounds((FRAME_WIDTH - size.width - 100) / 2, TOP_MARGIN * 3 + TITLE_HEIGHT + BOX_HEIGHT, size.width + 100, BUTTON_HEIGHT);

		TakeSurveyButtonListener surveyListener = new TakeSurveyButtonListener(frame, false);
		retakeButton.addActionListener(surveyListener);
		p.add(retakeButton);
	}
	
	/**
	 * Creates the health steps button at the bottom of the page
	 */
	public void createHealthStepsButton() {
		JButton healthStepsButton = new JButton("Health Steps Recommended For You");
		healthStepsButton.setFont(buttonFont);
		healthStepsButton.setBackground(BUTTON_YELLOW);
		
		size = healthStepsButton.getPreferredSize();
		healthStepsButton.setBounds(((FRAME_WIDTH / 2 - size.width - 100) / 2), TOP_MARGIN * 4 + TITLE_HEIGHT + BOX_HEIGHT + BUTTON_HEIGHT, size.width + 100, BUTTON_HEIGHT);
		HealthStepsButtonListener healthListener = new HealthStepsButtonListener(frame, surveyAnswer);
		healthStepsButton.addActionListener(healthListener);
		p.add(healthStepsButton);
	}
	
	/**
	 * Creates the campus activities button at the bottom of the page
	 */
	public void createCampusActivitesButton() {

		JButton campusActivitesButton = new JButton("Recommended On Campus Activites");
		campusActivitesButton.setFont(buttonFont);
		
		campusActivitesButton.setBackground(BUTTON_YELLOW);
		size = campusActivitesButton.getPreferredSize();
		campusActivitesButton.setBounds(((FRAME_WIDTH / 2 + (FRAME_WIDTH / 2 - size.width - 100) / 2)), TOP_MARGIN * 4 + TITLE_HEIGHT + BOX_HEIGHT + BUTTON_HEIGHT, size.width + 100, BUTTON_HEIGHT);
		ActivityRecButtonListener activityListener = new ActivityRecButtonListener(frame, surveyAnswer);
		campusActivitesButton.addActionListener(activityListener);
		p.add(campusActivitesButton);
	}

	

	/**
	 * Returns the button groups for this assessment 
	 */
	public ArrayList<ButtonGroup> getButtons(){
		return buttonGroups;
	}
	
}