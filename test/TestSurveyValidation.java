import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import org.junit.Before;
import org.junit.Test;

import buttonListeners.SubmitFormButtonListener;
public class TestSurveyValidation {

	SubmitFormButtonListener listener;
	ArrayList<ButtonGroup> groupList;
	ArrayList<JRadioButton> buttonList;
	JFrame frame;
	TestData data;
	@Before
	public void setUp() throws Exception {
		data = new TestData();
		buttonList = data.getButtonList();
		groupList = data.getButtonGroups();
		frame = new JFrame();
		listener = new SubmitFormButtonListener(groupList, buttonList, frame);
	}

	@Test
	public void testFormNotFinished() {
		buttonList.get(0).setSelected(true);
		boolean formFinished = listener.formComplete();
		assertFalse(formFinished);
	}
	
	@Test
	public void testFormFinished() {
		buttonList.get(0).setSelected(true);
		buttonList.get(2).setSelected(true);
		buttonList.get(4).setSelected(true);
		buttonList.get(6).setSelected(true);
		boolean formFinished = listener.formComplete();
		assertTrue(formFinished);
	}
}
