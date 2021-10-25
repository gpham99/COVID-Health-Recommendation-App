import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class TestData {

	private ArrayList<JRadioButton> buttonList = new ArrayList<>();
	private ArrayList<ButtonGroup> buttonGroups = new ArrayList<>();
	
	protected ArrayList<JRadioButton> getButtonList() {
		buttonList.add(new JRadioButton("testYes"));
		buttonList.add(new JRadioButton("testNo"));
		
		buttonList.add(new JRadioButton("testYes"));
		buttonList.add(new JRadioButton("testNo"));
		
		buttonList.add(new JRadioButton("testYes"));
		buttonList.add(new JRadioButton("testNo"));
		
		buttonList.add(new JRadioButton("testYes"));
		buttonList.add(new JRadioButton("testNo"));
		return buttonList;
	}
	protected ArrayList<ButtonGroup> getButtonGroups() {
		ButtonGroup testGroup1 = new ButtonGroup();
		testGroup1.add(buttonList.get(0));
		testGroup1.add(buttonList.get(1));
		ButtonGroup testGroup2 = new ButtonGroup();
		testGroup2.add(buttonList.get(2));
		testGroup2.add(buttonList.get(3));
		ButtonGroup testGroup3 = new ButtonGroup();
		testGroup3.add(buttonList.get(4));
		testGroup3.add(buttonList.get(5));
		ButtonGroup testGroup4 = new ButtonGroup();
		testGroup4.add(buttonList.get(6));
		testGroup4.add(buttonList.get(7));
		
		buttonGroups.add(testGroup1);
		buttonGroups.add(testGroup2);
		buttonGroups.add(testGroup3);
		buttonGroups.add(testGroup4);
		return buttonGroups;
	}
}
