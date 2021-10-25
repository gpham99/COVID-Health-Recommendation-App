package guiPages;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import buttonListeners.SubmitFormButtonListener;

/**
 * Defines and creates the Assessment Form visual with 3 questions, corresponding
 * yes/no buttons and a submit button as well as an prompt when form isn't
 * completed fully.
 * @author Annika Piccaro, Elle Triplett, Giang Pham and Nick Bohm
 *
 */
public class AssessmentForm {
	private static final int TOP_MARGIN = 20;
	
	private static final int QUESTION_WIDTH = 550;
	private static final int QUESTION_HEIGHT = 50;
	
	private static final int LEFT_MARGIN = 100;
	private static final int MARGIN_BUTTON_YES = 700;
	private static final int MARGIN_BUTTON_NO = 750;
	private static final int TO_ADD_WIDTH = 20;
	
	private static final Color BACKGROUND_BLUE = new Color(176, 201, 207);
	private static final Color BUTTON_YELLOW = new Color(182,149,99);
	
	// frame size
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;
		
	// title's size
	private static final int TITLE_HEIGHT = 50;
	private static final int TITLE_WIDTH = FRAME_WIDTH;
	
	// font
	private static final Font titleFont = new Font("Dialog", Font.BOLD, 40);
	private static final Font riskLevelBoxFont = new Font("Dialog", Font.PLAIN, 30);
	private static final Font radioButtonFont = new Font("Dialog", Font.PLAIN, 20);
	private static final Font questionFont = new Font("Dialog", Font.PLAIN, 18);
	
	private JRadioButton yesButtonQuestionOne;
	private JRadioButton noButtonQuestionOne;
	private JRadioButton yesButtonQuestionTwo;
	private JRadioButton noButtonQuestionTwo;
	private JRadioButton yesButtonQuestionThree;
	private JRadioButton noButtonQuestionThree;
	private JRadioButton yesButtonQuestionFour;
	private JRadioButton noButtonQuestionFour;
	
	private ArrayList<JRadioButton> buttonList = new ArrayList<JRadioButton>();
	private ArrayList<ButtonGroup> buttonGroups = new ArrayList<>();
	
	private static JFrame frame;
	private static JPanel p;
	private static Dimension size;
	
	/**
	 * This is the constructor for the Assessment form
	 * It calls the method to initialize and display the form 
	 * @param the same java frame that was being used for the home screen
	 */
	public AssessmentForm(JFrame frame) {
		this.frame = frame;
		this.initializeForm(frame);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * This method initializes the assessment form by creating all
	 * of its components and adding them to the frame
	 */
	public void initializeForm(JFrame frame) {
		p = new JPanel();
		p.setBackground(BACKGROUND_BLUE);
		p.setLayout(null);
	
		this.createTitle();
		this.createQuestions();
		this.createRadioButtons();
		this.createSubmitButton();
		this.createRadioButtonList();
		
		this.frame.add(p);
		frame.setVisible(true);
	}
	
	/**
	 * Labels the form-Personalized Risk Assessment
	 */
	public void createTitle() {
		JLabel title = new JLabel("Personalized Risk Assessment", SwingConstants.CENTER);
		title.setFont(titleFont);
		title.setBounds(0, TOP_MARGIN, TITLE_WIDTH, TITLE_HEIGHT);
		p.add(title);
	}
	
	/**
	 * Creates the submit button at the end of the form
	 */
	public void createSubmitButton() {
		// JButton
		JButton submit = new JButton("Submit");
		submit.setFont(radioButtonFont);
		submit.setBackground(BUTTON_YELLOW);
		size = submit.getPreferredSize();
		submit.setBounds((FRAME_WIDTH - size.width - 100) / 2, 550, size.width + 100, size.height);
		SubmitFormButtonListener submitListener = new SubmitFormButtonListener(buttonGroups, buttonList, frame);
		submit.addActionListener(submitListener);
		p.add(submit);
	}
	
	/**
	 * Creates the yes/no buttons for every question
	 */
	public void createRadioButtons() {
		yesButtonQuestionOne = new JRadioButton("Yes");
		yesButtonQuestionOne.setFont(radioButtonFont);
		yesButtonQuestionOne.setBackground(BACKGROUND_BLUE);
		Dimension yesButtonSizeOne = yesButtonQuestionOne.getPreferredSize();
		yesButtonQuestionOne.setBounds(MARGIN_BUTTON_YES, 100, yesButtonSizeOne.width + TO_ADD_WIDTH, yesButtonSizeOne.height);

		noButtonQuestionOne = new JRadioButton("No");
		noButtonQuestionOne.setFont(radioButtonFont);
		noButtonQuestionOne.setBackground(BACKGROUND_BLUE);
		Dimension noButtonSizeOne = noButtonQuestionOne.getPreferredSize();
		noButtonQuestionOne.setBounds(MARGIN_BUTTON_NO + yesButtonSizeOne.width, 100, noButtonSizeOne.width + TO_ADD_WIDTH, noButtonSizeOne.height);
		
		yesButtonQuestionTwo = new JRadioButton("Yes");
		yesButtonQuestionTwo.setFont(radioButtonFont);
		yesButtonQuestionTwo.setBackground(BACKGROUND_BLUE);
		Dimension yesButtonSizeTwo = yesButtonQuestionTwo.getPreferredSize();
		yesButtonQuestionTwo.setBounds(MARGIN_BUTTON_YES, 250, yesButtonSizeTwo.width + TO_ADD_WIDTH, yesButtonSizeTwo.height);
		
		noButtonQuestionTwo = new JRadioButton("No");
		noButtonQuestionTwo.setFont(radioButtonFont);
		noButtonQuestionTwo.setBackground(BACKGROUND_BLUE);
		Dimension noButtonSizeTwo = noButtonQuestionTwo.getPreferredSize();
		noButtonQuestionTwo.setBounds(MARGIN_BUTTON_NO + yesButtonSizeTwo.width, 250, noButtonSizeTwo.width + TO_ADD_WIDTH, noButtonSizeTwo.height);
		
		yesButtonQuestionThree = new JRadioButton("Yes");
		yesButtonQuestionThree.setFont(radioButtonFont);
		yesButtonQuestionThree.setBackground(BACKGROUND_BLUE);
		Dimension yesButtonSizeThree = yesButtonQuestionThree.getPreferredSize();
		yesButtonQuestionThree.setBounds(MARGIN_BUTTON_YES, 350, yesButtonSizeThree.width + TO_ADD_WIDTH, yesButtonSizeThree.height);
		
		noButtonQuestionThree = new JRadioButton("No");
		noButtonQuestionThree.setFont(radioButtonFont);
		noButtonQuestionThree.setBackground(BACKGROUND_BLUE);
		Dimension noButtonSizeThree = noButtonQuestionThree.getPreferredSize();
		noButtonQuestionThree.setBounds(MARGIN_BUTTON_NO + yesButtonSizeThree.width, 350, noButtonSizeThree.width + TO_ADD_WIDTH, noButtonSizeThree.height);
		
		yesButtonQuestionFour = new JRadioButton("Yes");
		yesButtonQuestionFour.setFont(radioButtonFont);
		yesButtonQuestionFour.setBackground(BACKGROUND_BLUE);
		Dimension yesButtonSizeFour = yesButtonQuestionFour.getPreferredSize();
		yesButtonQuestionFour.setBounds(MARGIN_BUTTON_YES, 450, yesButtonSizeFour.width + TO_ADD_WIDTH, yesButtonSizeFour.height);
		
		noButtonQuestionFour = new JRadioButton("No");
		noButtonQuestionFour.setFont(radioButtonFont);
		noButtonQuestionFour.setBackground(BACKGROUND_BLUE);
		Dimension noButtonSizeFour = noButtonQuestionFour.getPreferredSize();
		noButtonQuestionFour.setBounds(MARGIN_BUTTON_NO + yesButtonSizeFour.width, 450, noButtonSizeFour.width + TO_ADD_WIDTH, noButtonSizeFour.height);
	    
		final ButtonGroup question1Group = new ButtonGroup();
		question1Group.add(yesButtonQuestionOne);
		question1Group.add(noButtonQuestionOne);
		p.add(yesButtonQuestionOne);
		p.add(noButtonQuestionOne);
		
		final ButtonGroup question2Group = new ButtonGroup();
		question2Group.add(yesButtonQuestionTwo);
		question2Group.add(noButtonQuestionTwo);
		p.add(yesButtonQuestionTwo);
		p.add(noButtonQuestionTwo);
		
		final ButtonGroup question3Group = new ButtonGroup();
		question3Group.add(yesButtonQuestionThree);
		question3Group.add(noButtonQuestionThree);
		p.add(yesButtonQuestionThree);
		p.add(noButtonQuestionThree);
		
		final ButtonGroup question4Group = new ButtonGroup();
		question4Group.add(yesButtonQuestionFour);
		question4Group.add(noButtonQuestionFour);
		p.add(yesButtonQuestionFour);
		p.add(noButtonQuestionFour);
		
		buttonGroups.add(question1Group);
		buttonGroups.add(question2Group);
		buttonGroups.add(question3Group);
		buttonGroups.add(question4Group);
	}
	
	/**
	 * Creates the Covid Risk Assessment questions
	 */
	public void createQuestions() {
	    // Question 1
	    JTextArea questionOne = new JTextArea("Are you experiencing any of these symptoms: fever, chills, cough, shortness of breath, difficulty breathing, loss of taste or smell?");
	    questionOne.setFont(questionFont);
	    questionOne.setEditable(false);
	    questionOne.setLineWrap(true);
	    questionOne.setWrapStyleWord(true);
	    questionOne.setBackground(BACKGROUND_BLUE);

		size = questionOne.getPreferredSize();
		questionOne.setBounds(LEFT_MARGIN, 100, QUESTION_WIDTH, QUESTION_HEIGHT);
		
		// Question 2
		JTextArea questionTwo = new JTextArea("Were you directly exposed to COVID-19 in the last 2 weeks?");
		questionTwo.setFont(questionFont);
		questionTwo.setEditable(false);
	    questionTwo.setLineWrap(true);
	    questionTwo.setWrapStyleWord(true);
	    questionTwo.setBackground(BACKGROUND_BLUE);

		size = questionTwo.getPreferredSize();
		questionTwo.setBounds(LEFT_MARGIN, 250, QUESTION_WIDTH, QUESTION_HEIGHT);

		// Question 3
		JTextArea questionThree = new JTextArea("Are you fully vaccinated? (Two weeks from your last dose)");
		questionThree.setFont(questionFont);
		questionThree.setEditable(false);
	    questionThree.setLineWrap(true);
	    questionThree.setWrapStyleWord(true);
	    questionThree.setBackground(BACKGROUND_BLUE);

		size = questionThree.getPreferredSize();
		questionThree.setBounds(LEFT_MARGIN, 350, QUESTION_WIDTH, QUESTION_HEIGHT);

		// Question 4
		JTextArea questionFour = new JTextArea("Are you in a high risk group? (Including the elderly, immunocompromised, living in a congregate setting etc.)");
		questionFour.setFont(questionFont);
		questionFour.setEditable(false);
	    questionFour.setLineWrap(true);
	    questionFour.setWrapStyleWord(true);
	    questionFour.setBackground(BACKGROUND_BLUE);

		size = questionFour.getPreferredSize();
		questionFour.setBounds(LEFT_MARGIN, 450, QUESTION_WIDTH, QUESTION_HEIGHT);   
		
		JRadioButton yesButtonQuestionFour = new JRadioButton();
		Dimension yesButtonSizeFour = yesButtonQuestionFour.getPreferredSize();
		yesButtonQuestionFour.setBounds(100, 500, yesButtonSizeFour.width, yesButtonSizeFour.height);
		
		JRadioButton noButtonQuestionFour = new JRadioButton();
		Dimension noButtonSizeFour = noButtonQuestionFour.getPreferredSize();
		noButtonQuestionFour.setBounds(150, 500, noButtonSizeFour.width, noButtonSizeFour.height);
		
		// JButton
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Dialog", Font.PLAIN, 30));
		size = submit.getPreferredSize();
		submit.setBounds(300, 550, size.width + 100, size.height);		
		
		p.setLayout(null);
		
		//Add buttons to panel
	    p.add(questionOne);
	    p.add(questionTwo);
	    p.add(questionThree);
	    p.add(questionFour);
	}
	
	/**
	 * Creates the formatted list of yes/no buttons
	 */
	public void createRadioButtonList() {
		buttonList.add( yesButtonQuestionOne);
		buttonList.add( noButtonQuestionOne);
		
		buttonList.add( yesButtonQuestionTwo);
		buttonList.add( noButtonQuestionTwo);
		
		buttonList.add( yesButtonQuestionThree);
		buttonList.add( noButtonQuestionThree);
		
		buttonList.add( yesButtonQuestionFour);
		buttonList.add( noButtonQuestionFour);
	}

	/**
	 * Prompts the user if they do not answer all questions for the covid assessment
	 */
	public static void createDialog() {
		JOptionPane.showMessageDialog(frame,
			    "You must answer every question in order to continue",
			    "Form Incomplete",
			    JOptionPane.WARNING_MESSAGE);
	}
}