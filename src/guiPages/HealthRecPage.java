package guiPages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import buttonListeners.GoBackButtonListener;
import dataStorage.SurveyAnswer;
import healthRecomendationStrategies.HealthRiskStrategy;
import healthRecomendationStrategies.HighHealthRec;
import healthRecomendationStrategies.LowHealthRec;
import healthRecomendationStrategies.MediumHealthRec;
import healthRecomendationStrategies.MediumHighHealthRec;
import healthRecomendationStrategies.VeryHighHealthRec;
import healthRecomendationStrategies.VeryLowHealthRec;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Defines and creates the Health Recommendation visual with a list of 
 * steps to take (according to the CDC and Colorado College)
 * based on the users risk level that was calculated for the
 * AssessmentForm page
 * @author annikapiccaro
 *
 */
public class HealthRecPage {
	private static final Color BACKGROUND_BLUE = new Color(176, 201, 207);
	private static final Color BUTTON_YELLOW = new Color(182,149,99);

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
	private JFrame frame;
	private static JPanel p;
	private SurveyAnswer surveyAnswer;

	private static final Font titleFont = new Font("Dialog", Font.BOLD, 40);
	private static final Font recommendationsFont = new Font("Dialog", Font.PLAIN, 25);
	private static final Font buttonFont = new Font("Dialog", Font.PLAIN, 20);
	private static final Font subtitleFont = new Font("Dialog", Font.PLAIN, 27);

	private static final int VERY_LOW = 0;
	private static final int LOW = 4;
	private static final int MEDIUM = 8;
	private static final int MEDIUM_HIGH = 12; 
	private static final int HIGH = 16; 
	private static final int VERY_HIGH = 24;
	private ArrayList<String> recs = null;
	
	public HealthRecPage(JFrame frame, SurveyAnswer surveyAnswer) throws FileNotFoundException {
		this.frame = frame;
		this.surveyAnswer = surveyAnswer;
		p=new JPanel();
		p.setBackground(BACKGROUND_BLUE);
		p.setLayout(null);
		this.frame.add(p);
		this.createTitle();
		this.createSubTitle();
		this.createGoBackButton();
		
		HealthRiskStrategy healthRiskStrat = this.getRiskStrategy();
		this.recs = healthRiskStrat.getHealthRecs();
		this.createHealthBox();
		
		frame.setVisible(true);
	}
	
	public HealthRiskStrategy getRiskStrategy() {
		int score = this.surveyAnswer.calculateRiskNumber();
		switch(score) {
			case(VERY_LOW):
				return new VeryLowHealthRec(); 
			case(LOW):
				return new LowHealthRec();
			case(MEDIUM):
				return new MediumHealthRec();
			case(MEDIUM_HIGH):
				return new MediumHighHealthRec();
			case(HIGH):
				return new HighHealthRec();
			case(VERY_HIGH):
				return new VeryHighHealthRec();
			default:
				return null;
		}
	}

	/**
	 * Labels the page
	 */
	public void createTitle() {
		JLabel title = new JLabel("Health Recommendations For You", SwingConstants.CENTER);
		title.setFont(titleFont);
		Dimension size = title.getPreferredSize();
		title.setBounds(0, TOP_MARGIN, TITLE_WIDTH, TITLE_HEIGHT);
		p.add(title);
	}

	/**
	 * Creates the subtitle to cite the sources of the health steps to take
	 */
	public void createSubTitle() {
		JLabel subTitle = new JLabel("(According to CDC and CC Guidelines)", SwingConstants.CENTER);
		subTitle.setFont(subtitleFont);
		Dimension size = subTitle.getPreferredSize();
		subTitle.setBounds(0, TOP_MARGIN+TITLE_HEIGHT, TITLE_WIDTH, TITLE_HEIGHT);
		p.add(subTitle);
	}
	
	/**
	 * Creates the text box that matches the risk level color and holds
	 * the health steps.
	 */
	public void createHealthBox() {
		JTextPane healthBoxText = new JTextPane();
		String healthBoxContent = "";
		for (String rec: recs) {
			healthBoxContent += rec + "\n";
		}
		healthBoxText.setText("Steps to take: \n" + healthBoxContent);
		
		healthBoxText.setFont(recommendationsFont);
		healthBoxText.setMargin(new Insets(20, 20, 20, 20));
		
		// center the text inside the box
		StyledDocument doc = healthBoxText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		// set the background color for the risk level box
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane.background", new ColorUIResource(surveyAnswer.getDisplayColor()));
        defaults.put("TextPane[Enabled].backgroundPainter", surveyAnswer.getDisplayColor());
        healthBoxText.putClientProperty("Nimbus.Overrides", defaults);
        healthBoxText.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        healthBoxText.setBackground(surveyAnswer.getDisplayColor());
		
		healthBoxText.setEditable(false);
		
		healthBoxText.setBounds((FRAME_WIDTH - BOX_WIDTH) / 2, TOP_MARGIN * 2 + TITLE_HEIGHT * 2, BOX_WIDTH, BOX_HEIGHT);
		p.add(healthBoxText);
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
		goBack.setBounds((FRAME_WIDTH- BUTTON_WIDTH)/2, (TOP_MARGIN*3) + (TITLE_HEIGHT*2) + BOX_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT );
		GoBackButtonListener goBackListener = new GoBackButtonListener(frame,surveyAnswer);
		goBack.addActionListener(goBackListener);
		p.add(goBack);
	}
}

