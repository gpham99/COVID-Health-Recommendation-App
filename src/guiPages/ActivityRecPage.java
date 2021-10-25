package guiPages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import activitesRecommendationStrategies.ActivitiesStrategy;
import activitesRecommendationStrategies.HighRiskActivitiesStrategy;
import activitesRecommendationStrategies.LowRiskActivitiesStrategy;
import activitesRecommendationStrategies.MediumHighRiskActivitiesStrategy;
import activitesRecommendationStrategies.MediumRiskActivitiesStrategy;
import activitesRecommendationStrategies.VeryHighRiskActivitiesStrategy;
import activitesRecommendationStrategies.VeryLowRiskActivitiesStrategy;
import buttonListeners.GoBackButtonListener;
import dataStorage.Activity;
import dataStorage.SurveyAnswer;
import healthRecomendationStrategies.HealthRiskStrategy;
import healthRecomendationStrategies.HighHealthRec;
import healthRecomendationStrategies.LowHealthRec;
import healthRecomendationStrategies.MediumHealthRec;
import healthRecomendationStrategies.MediumHighHealthRec;
import healthRecomendationStrategies.VeryHighHealthRec;
import healthRecomendationStrategies.VeryLowHealthRec;

/**
 * Defines and creates the Activity Recommendation visual with a list of 
 * activities based on the users risk level that was calculated for the
 * AssessmentForm page
 * @author annikapiccaro
 *
 */
public class ActivityRecPage {
	private static final Color BACKGROUND_BLUE = new Color(176, 201, 207);
	private static final Color BUTTON_YELLOW = new Color(182,149,99);
	private static final Color CUSTOM_PINK = new Color(209, 141, 141);
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;

	private static final int BOX_WIDTH = 500;
	private static final int BOX_HEIGHT = 400;

	private static final int TITLE_WIDTH = FRAME_WIDTH;
	private static final int TITLE_HEIGHT = 50;
	private static final int SUB_TITLE_WIDTH = 500;

	private static final int BUTTON_HEIGHT = 50;
	private static final int BUTTON_WIDTH = 200;

	private static final int TOP_MARGIN = 20;

	private static final Font titleFont = new Font("Dialog", Font.BOLD, 40);
	private static final Font recommendationsFont = new Font("Dialog", Font.PLAIN, 20);
	private static final Font buttonFont = new Font("Dialog", Font.PLAIN, 20);
	private JFrame frame;
	private static JPanel p;
	private SurveyAnswer surveyAnswer;
	
	private static final int VERY_LOW = 0;
	private static final int LOW = 4;
	private static final int MEDIUM = 8;
	private static final int MEDIUM_HIGH = 12; 
	private static final int HIGH = 16; 
	private static final int VERY_HIGH = 24;
	
	ArrayList<Activity> recs = null;
	ArrayList<Activity> listOfAllActivities = new ArrayList<Activity>();

	public ActivityRecPage(JFrame frame, SurveyAnswer surveyAnswer) {
		this.frame = frame;
		this.surveyAnswer = surveyAnswer;
		p=new JPanel();
		p.setBackground(BACKGROUND_BLUE);
		p.setLayout(null);
		this.frame.add(p);
		this.createTitle();

		this.createGoBackButton();		
		getListOfAllActivities();
			
		ActivitiesStrategy activityStrategy = getRiskStrategy();
		this.recs = activityStrategy.recommendActivities(listOfAllActivities);
		this.createActivityBox();
		
		frame.setVisible(true);
	}
	
	
	/**
	 * get list of all activities
	 */
	public void getListOfAllActivities () {
		try {
			Scanner in = new Scanner(new FileInputStream("Activites_Spreadsheet.csv"));
			while (in.hasNextLine()) {
				String activity = in.nextLine();
				
				List<String> activityDetails = Arrays.asList(activity.split(","));
				
				
				String activityName = activityDetails.get(0);
				String activityLocation = activityDetails.get(1);
				String activityDescription = activityDetails.get(2);
				String activityDate = activityDetails.get(3);
				ArrayList<String> riskLevels = new ArrayList<String>();
				for(int i = 4; i < activityDetails.size(); i++) {
					riskLevels.add(activityDetails.get(i));
				}
				
				Activity individualActivity = new Activity(activityName, activityLocation, activityDescription, activityDate, riskLevels);
				listOfAllActivities.add(individualActivity);
			}
			in.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Could not open the file: " + "Activites_Spreadsheet.csv");
		}
	}
	
	
	/**
	 * get strat
	 * @author Giang Pham
	 */
	public ActivitiesStrategy getRiskStrategy() {
		int score = this.surveyAnswer.calculateRiskNumber();
		switch(score) {
			case(VERY_LOW):
				return new VeryLowRiskActivitiesStrategy(); 
			case(LOW):
				return new LowRiskActivitiesStrategy();
			case(MEDIUM):
				return new MediumRiskActivitiesStrategy();
			case(MEDIUM_HIGH):
				return new MediumHighRiskActivitiesStrategy();
			case(HIGH):
				return new HighRiskActivitiesStrategy();
			case(VERY_HIGH):
				return new VeryHighRiskActivitiesStrategy();
			default:
				return null;
		}
	}

	/**
	 * This method initializes the activity page by creating all
	 * of its components and adding them to the frame
	 */
	public void initializeForm(JFrame frame) {
		p = new JPanel();
		p.setBackground(BACKGROUND_BLUE);
		p.setLayout(null);
		this.frame.add(p);
		this.createTitle();
		frame.setVisible(true);
	}



	/**
	 * Labels the form-Activities Recommended For You
	 */
	public void createTitle() {
		JLabel title = new JLabel("Activities Recommended For You",SwingConstants.CENTER);
		title.setFont(titleFont);
		Dimension size = title.getPreferredSize();
		title.setBounds(0, TOP_MARGIN, TITLE_WIDTH, TITLE_HEIGHT);
		p.add(title);
	}

	/**
	 * Creates the text box that matches the risk level color and holds
	 * the activities.
	 */
	public void createActivityBox() {
		JTextPane activityBoxText = new JTextPane();
		String activitiesBoxContent = "";
		
		for (Activity activity: recs) {
			activitiesBoxContent += activity.toString() + "\n";
		}
		
		activityBoxText.setText("Recommended Activities: \n" + activitiesBoxContent);
		
		
		activityBoxText.setFont(recommendationsFont);
		activityBoxText.setMargin(new Insets(20, 20, 20, 20));
		
		// center the text inside the box
		StyledDocument doc = activityBoxText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		// set the background color for the risk level box
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane.background", new ColorUIResource(surveyAnswer.getDisplayColor()));
        defaults.put("TextPane[Enabled].backgroundPainter", surveyAnswer.getDisplayColor());
        activityBoxText.putClientProperty("Nimbus.Overrides", defaults);
        activityBoxText.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        activityBoxText.setBackground(surveyAnswer.getDisplayColor());
		
		activityBoxText.setEditable(false);
		
		JPanel noWrapPanel = new JPanel( new BorderLayout() );
		noWrapPanel.add( activityBoxText );
		JScrollPane scrollPane = new JScrollPane( noWrapPanel );
		scrollPane.setBounds((FRAME_WIDTH - BOX_WIDTH) / 2, TOP_MARGIN * 2 + TITLE_HEIGHT * 2, BOX_WIDTH, BOX_HEIGHT);

		p.add(scrollPane);
	}

	/**
	 * Creates the go back button at the end of the form
	 */
	public void createGoBackButton() {
		// JButton
		JButton goBack = new JButton("Go Back");
		goBack.setFont(buttonFont);
		goBack.setBackground(BUTTON_YELLOW);
		Dimension size = goBack.getPreferredSize();
		goBack.setBounds((FRAME_WIDTH-BUTTON_WIDTH)/2, (TOP_MARGIN * 3) + (TITLE_HEIGHT * 2) + BOX_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		GoBackButtonListener goBackListener = new GoBackButtonListener(frame,surveyAnswer);
		goBack.addActionListener(goBackListener);
		p.add(goBack);
	}

}

