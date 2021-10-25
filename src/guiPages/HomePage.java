package guiPages;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import buttonListeners.TakeSurveyButtonListener;
import dataStorage.CCRiskLevel;
import webScraping.COVIDData;
import webScraping.DatabaseHandler;
import webScraping.WebScrapeProxy;

/**
 * Defines the Home Page of the CC COVID Assessment tool. 
 * Calculates the current risk level of CC and cites where the equation
 * is pulled from. Has a Take Assessment button that prompts that this tool
 * is not medical advice and once accepted moves you to the assessment
 * @author annikapiccaro
 *
 */
public class HomePage {
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;
	
	private static final int BOX_WIDTH = 400;
	private static final int TOP_BOX_HEIGHT = 150;
	private static final int BOTTOM_BOX_HEIGHT = 275;
	
	private static final int TITLE_WIDTH = FRAME_WIDTH;
	private static final int TITLE_HEIGHT = 100;

	private static final int TOP_MARGIN = 20;
	
	private static final Color BACKGROUND_BLUE = new Color(176, 201, 207);
	private static final Color CUSTOM_PINK = new Color(209, 141, 141);
	private static final Color BUTTON_YELLOW = new Color(182,149,99);

	private Color riskLevelColor = new Color(255,255,255);
	private String riskLevelMeaning;	
	private CCRiskLevel currentLevel;
	
	private static final Font titleFont = new Font("Dialog", Font.BOLD, 40);
	private static final Font riskLevelBoxAndButtonFont = new Font("Dialog", Font.PLAIN, 30);
	private static final Font riskLevelExplanationFont = new Font("Dialog", Font.PLAIN, 19);
	private static JFrame frame;
	private static JPanel p;
	private static Dimension size;
	
	/**
	 * Initializes the frame of the home page along with the Panel
	 * Initializes the createTitle and buttons methods
	 */
	public HomePage() {
		// Create the frame
		frame = new JFrame("COVID Assessment Tool");
		Image icon = Toolkit.getDefaultToolkit().getImage("CCLogo.png"); 
		frame.setIconImage(icon);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		p = new JPanel();
		p.setBackground(BACKGROUND_BLUE);
		p.setLayout(null);
		this.createTitle();
		this.createSubmitButton();
		this.createRiskLevelBox();
		this.createRiskLevelExplanation();
		frame.add(p);
		frame.setVisible(true);
	}
	
	/**
	 * Labels the form-Welcome to the CC COVID Risk Assessment and adds the CC logo at the top of the page
	 */
	public void createTitle() {
		ImageIcon icon = new ImageIcon("CCMountainLogo.png", "The Colorado College logo");
		JLabel title = new JLabel("Welcome to the CC COVID Risk Assessment",icon, JLabel.CENTER);

        title.setVerticalTextPosition(SwingConstants.BOTTOM);
        title.setHorizontalTextPosition(SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 40));
		Dimension size = title.getPreferredSize();
		title.setFont(titleFont);
		title.setBounds(0, TOP_MARGIN, TITLE_WIDTH, TITLE_HEIGHT);
		p.add(title);
	}

	/**
	 * Creates a box to display CC's current risk level in
	 */
	public void createRiskLevelBox() {
		String riskLevel = createCCRiskLevel();
		
		JTextPane riskLevelBoxText = new JTextPane();
		riskLevelBoxText.setText("Colorado College's Current Risk Level: \n" + riskLevel);
		riskLevelBoxText.setFont(riskLevelBoxAndButtonFont);
		riskLevelBoxText.setMargin(new Insets(20, 20, 20, 20));
		
		// center the text inside the box
		StyledDocument doc = riskLevelBoxText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		// set the background color for the risk level box
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane.background", new ColorUIResource(riskLevelColor));
        defaults.put("TextPane[Enabled].backgroundPainter", riskLevelColor);
        riskLevelBoxText.putClientProperty("Nimbus.Overrides", defaults);
        riskLevelBoxText.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        riskLevelBoxText.setBackground(riskLevelColor);
		
		riskLevelBoxText.setEditable(false);
		riskLevelBoxText.setBounds((FRAME_WIDTH - BOX_WIDTH) / 2, TOP_MARGIN * 2 + TITLE_HEIGHT, BOX_WIDTH, TOP_BOX_HEIGHT);
		p.add(riskLevelBoxText);
	}
	
	/**
	 * Creates a box to display the explanation of how CC's current risk level is derived
	 * and what it means
	 */
	public void createRiskLevelExplanation() {
		JTextArea riskLevelExplanation = new JTextArea("This risk level is calculated using Brown University's Covid Supression Framework "
				+ "which calculates the number of cases per 100,000 people. The current risk level means that there " + riskLevelMeaning
				+ "\n(Most recent data from the week " + currentLevel.getFirstDay() + " to "+ currentLevel.getLastDay()+")");
		riskLevelExplanation.setFont(riskLevelExplanationFont);
		riskLevelExplanation.setEditable(false);
		riskLevelExplanation.setLineWrap(true);
		riskLevelExplanation.setWrapStyleWord(true);
		riskLevelExplanation.setBackground(riskLevelColor);
		riskLevelExplanation.setMargin(new Insets(20, 20, 20, 20));
		size = riskLevelExplanation.getPreferredSize();
		riskLevelExplanation.setBounds((FRAME_WIDTH - BOX_WIDTH) / 2,TOP_MARGIN * 2 + TITLE_HEIGHT +TOP_BOX_HEIGHT, BOX_WIDTH, BOTTOM_BOX_HEIGHT);
		
		p.add(riskLevelExplanation);
	}
	/**
	 * Creates the take assessment button at (surveyButton) the end of the form
	 */
	public void createSubmitButton() {
		JButton surveyButton = new JButton("Get Personalized Risk Assessment");
		surveyButton.setFont(riskLevelBoxAndButtonFont);
		surveyButton.setBackground(BUTTON_YELLOW);
		size = surveyButton.getPreferredSize();
		surveyButton.setBounds((FRAME_WIDTH - size.width - 100) / 2, (TOP_MARGIN * 3) + TITLE_HEIGHT + TOP_BOX_HEIGHT + BOTTOM_BOX_HEIGHT, size.width + 100, size.height);
	
		TakeSurveyButtonListener surveyListener = new TakeSurveyButtonListener(frame, true);
		surveyButton.addActionListener(surveyListener);	
		p.add(surveyButton);
	}
	
	/**
	 * Creates the current CC risk level 
	 * @return-string representing what risk level
	 */
	public String createCCRiskLevel() {
		COVIDData readIn = new WebScrapeProxy(null);
		readIn.scrapeCCCovidData();
		DatabaseHandler database = new DatabaseHandler();
		database.createDatabase();
		database.fillDatabase((WebScrapeProxy)readIn);
		database.collectData();
		currentLevel = new CCRiskLevel(database.getMostRecentWeek());
		int riskLevel = currentLevel.calculateRiskLevel();
		if(riskLevel==CCRiskLevel.GREEN) {
			riskLevelColor = new Color(184, 235, 167);
			riskLevelMeaning = " is less than 1 case per 100,000 and the community is on track for containment";
			return "Green Risk Level";
		} else if(riskLevel==CCRiskLevel.YELLOW) {
			riskLevelMeaning = " are 1-9 cases per 100,000 and community spread is occuring";
			riskLevelColor = new Color(237,203,89);
			return "Yellow Risk Level";
		} else if(riskLevel==CCRiskLevel.ORANGE) {
			riskLevelMeaning = " are 10-24 cases per 100,000 and there is accelerated spread in the community";
			riskLevelColor = new Color(224, 174, 108);
			return "Orange Risk Level";
		} else {
			riskLevelMeaning = "are more than 25 cases per 100,000 and the community is at a tipping point";
			riskLevelColor = new Color(209, 141, 141);
			return "Red Risk Level";
		}
	}
	/**
	 * Prompts the user if they do not answer all questions
	 */
	public static int createDialog() {
		int a = JOptionPane.showConfirmDialog(frame, "The information provided by this assessment is not medical advice and cannot be used to diagnose or treat any medical condition. Is that OK?", "Disclaimer", JOptionPane.YES_NO_OPTION);
		return a;
	}
}