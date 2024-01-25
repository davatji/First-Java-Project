import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;

/*JFrame interface, providing user the game preceding interface to allow users
 * set the parameters to instantiate the game class in high level control
 */
public class InitialInterface extends JFrame implements ActionListener{

	//initialize instance variables: Initial Interface components
	private RadioBtn chooseSizeRadioBtn;
	private RadioBtn chooseSwiftnessRadioBtn;
	private JPanel playButtonPanel;
	private JButton playButton;
	
	public InitialInterface(){
		
		//configure the frame
		this.setSize(500, 500);
		this.setLayout(new GridLayout(3, 1, 10, 10));
		
		//create two radio buttons groups that consist options of initial game parameters
		String[] sizeOptions = {"Enormous", "Large", "Medium", "Small"};
		this.chooseSizeRadioBtn = new RadioBtn("Choose Player Size", sizeOptions, 2);
		
		String[] swiftnessOptions = {"Lightning", "Fast", "Medium", "Slow"};
		this.chooseSwiftnessRadioBtn = new RadioBtn("Choose Player Swiftness", swiftnessOptions, 2);
		
		//creating a play button which will be packed in a panel
		this.playButton = new JButton();
		this.playButton.setText("Play");
		
		//binding play button with actionPerformed function
		this.playButton.addActionListener(this);
		
		//additional sub-panel in play button panel to proportionate the play button size
		this.playButtonPanel = new JPanel();
		this.playButtonPanel.setLayout(new GridLayout(1, 3, 0, 0));
		
		//placeholder
		this.playButtonPanel.add(new JPanel());
		
		this.playButtonPanel.add(playButton);
		
		//placeholder
		this.playButtonPanel.add(new JPanel());
		
		//adding components into the frame
		this.add(chooseSizeRadioBtn);
		this.add(chooseSwiftnessRadioBtn);
		this.add(playButtonPanel);
		
		this.setVisible(true);
	}
	//invoked when the play button is pressed
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*since this function is used by every button in this instance, we need to evaluate the click 
		 *event as a way to variate the performed action based on which button is clicked.
		*/
		Object sourceObject = e.getSource();
		
		if (sourceObject instanceof JButton) {
			JButton button = (JButton) sourceObject;
			
			//if the source of the action is the play button, invoke play function
			if (button.getText() == "Play") {
				Play();
			}
		}
	}
	//getting values from the radiobuttons. using them as arguments to instantiate the game
	public void Play() {
		String chosenSize = this.chooseSizeRadioBtn.getValue();
		String chosenSwiftness = this.chooseSwiftnessRadioBtn.getValue();
		
		new Game(chosenSize, chosenSwiftness);
	}
}

//subclass of JPanel: panel that functions as radiobutton panel
class RadioBtn extends JPanel{
	
	//grouping buttons so that only one button in a radiobutton group is active
	private ButtonGroup radioButtonGroup = new ButtonGroup();
	
	//storing radio button in an array for ease in getting the active button
	private JRadioButton[] radioButtons;
	
	/*constructor that adds additional components to JPanel such as:
	 * 1. creating radiobuttons
	 * 2. setting the radiobuttons' text based on the list 'options'
	 * 3. managing button placement
	*/
	public RadioBtn(String title, String[] options, int defaultIndex){
		
		Color defaultColor = new Color(200, 200, 200);
		this.setBackground(defaultColor);
		
		int buttonsCount = options.length;
		
		this.radioButtons = new JRadioButton[buttonsCount]; 
		
		final int btnColumns = 3;
		final int titleRow = 1;
		
		//using ceiling division to add additional row for radio button remainder
		int btnRows = Math.ceilDiv(buttonsCount, btnColumns);
		
		//setting the rows for the title and radio buttons
		this.setLayout(new GridLayout(btnRows + titleRow, btnColumns));
		
		JLabel titleLabel = new JLabel();
		titleLabel.setText(title);
		
		//placeholder
		this.add(new JLabel());
		
		this.add(titleLabel);
		
		//placeholder
		this.add(new JLabel());
		
		int totalNonLabelGrid = btnColumns * btnRows;
		
		for (int i = 0; i < totalNonLabelGrid; i++) {
			
			//creating radio buttons with their text based on the options
			if (i < buttonsCount) {
				JRadioButton radioBtn = new JRadioButton();
				radioBtn.setText(options[i]);
				
				this.add(radioBtn);
				
				radioButtonGroup.add(radioBtn);
				this.radioButtons[i] = radioBtn;
			}
			else {
				//add panel instead for placeholder (since total non label grid != total radio button)
				this.add(new JPanel());
			}
		}
		//setting the radiobutton default value
		this.radioButtons[defaultIndex].setSelected(true);
	}
	
	//overloaded constructor to allow setting for the radio button background color
	public RadioBtn(String title, String[] options, int defaultIndex, Color color){
		
		this.setBackground(color);
		
		int buttonsCount = options.length;
		
		this.radioButtons = new JRadioButton[buttonsCount]; 
		
		final int btnColumns = 3;
		final int titleRow = 1;
		
		int btnRows = Math.ceilDiv(buttonsCount, btnColumns);
		int totalGrid = btnColumns * btnRows;
		
		this.setLayout(new GridLayout(btnRows + titleRow, btnColumns));
		
		JLabel titleLabel = new JLabel();
		titleLabel.setText(title);
		
		this.add(new JLabel());
		
		this.add(titleLabel);

		this.add(new JLabel());
		
		for (int i = 0; i < totalGrid; i++) {
			if (i < buttonsCount) {
				JRadioButton radioBtn = new JRadioButton();
				radioBtn.setText(options[i]);
				
				this.add(radioBtn);
				
				radioButtonGroup.add(radioBtn);
				this.radioButtons[i] = radioBtn;
			}
			else {
				this.add(new JPanel());
			}
		}
		this.radioButtons[defaultIndex].setSelected(true);
	}
	
	public String getValue() {
		//seek for active radio button within the frame and return the text attribute
		for (JRadioButton radioButton : this.radioButtons){
			if (radioButton.isSelected()) {
				return radioButton.getText();
			}
		}
		
		//returning null by default
		return null;
	}
}
