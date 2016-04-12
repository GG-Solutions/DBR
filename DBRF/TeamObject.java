package DBRF;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class TeamObject {
	
	private int teamID = -1;
	private String teamName = "";
	private String category = "";
	private int place = -1;		//set to -1 as default? global place that the team is in
	
	//move the flags to the race object instead?
//	private char firstRaceTimeFlag = '*';
	private int firstRaceTime = -1; 
//	private char secondRaceTimeFlag = '*';
	private int secondRaceTime = -1; 
//	private char semiFinalRaceTimeFlag = '*';
	private int semiFinalRaceTime = -1;
//	private char finalRaceTimeFlag = '*';
	private int finalRaceTime = -1; 		//not every team will have a final race time?
	private int averagedRaceTime = -1; 
	
	private JFormattedTextField timeFirstRaceInputField = null;
	private JFormattedTextField timeSecondRaceInputField = null;
	private JFormattedTextField timeSemiFinalRaceInputField = null;
	private JFormattedTextField timeFinalRaceInputField = null;
	
	private JLabel flagFirstRaceTime = new JLabel("");
	private JLabel flagSecondRaceTime = new JLabel("");
	private JLabel flagSemiFinalRaceTime = new JLabel("");
	private JLabel flagFinalRaceTime = new JLabel("");
	
	//use these variables to keep track of the input time to see if they changed to set the flag
	private int firstRaceTimeTracker = 0;
	private int secondLockButtonPressCount = 0;
	private int semiFinalLockButtonPressCount = 0;
	private int finalLockButtonPressCount = 0;
	
	private JButton firstRaceLockButton = new JButton("Lock");
	private JButton secondRaceLockButton = new JButton("Lock");
	private JButton semiFinalRaceLockButton = new JButton("Lock");
	private JButton finalRaceLockButton = new JButton("Lock");
	
	//build and return the JFormattedTextField?
	public JFormattedTextField getTimeInputField(int round) {
		
		JFormattedTextField tempField = null;
		MaskFormatter timeMask = null;
		
		try {
			timeMask = new MaskFormatter("##:##.##");
			timeMask.setValueContainsLiteralCharacters(false);
			
			tempField = new JFormattedTextField(timeMask);
			tempField.setValue("000000");
			tempField.setFont(FestivalObject.getFont());	//set the correct font
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(round == 1) {
			timeFirstRaceInputField = tempField;
		}
		if(round == 2) {
			timeSecondRaceInputField = tempField;
		}
		if(round == 3) {
			timeSemiFinalRaceInputField = tempField;
		}
		if(round == 4) {
			timeFinalRaceInputField = tempField;
		}
		
		return tempField;
	}
	
	//get the time flag
	public JLabel getTimeFlag(int round) {
		
		JLabel tempLabel = new JLabel("");
		
		if(round == 1) {
			flagFirstRaceTime = tempLabel;
		}
		if(round == 2) {
			flagSecondRaceTime = tempLabel;
		}
		if(round == 3) {
			flagSemiFinalRaceTime = tempLabel;
		}
		if(round == 4) {
			flagFinalRaceTime = tempLabel;
		}
		
		return tempLabel;
	}
	
	//returns the correct lock back to the pane depending on what round is passed to this function
	public JButton getLockButton(int round) {
		
		JButton tempButton = null;	//create a temp variable to return depending on which integer is passed to this function
		
		if(round == 1) {
			tempButton = firstRaceLockButton;
		}
		
		if(round == 2) {
			tempButton = secondRaceLockButton;
		}
		
		if(round == 3) {
			tempButton = semiFinalRaceLockButton;
		}
		
		if(round == 4) {
			tempButton = finalRaceLockButton;
		}
		
		return tempButton;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Default constructor.
	 * Inputs - None.
	 * Outputs - Creates a new TeamObject object and builds the 4 lock buttons.
	 */
	public TeamObject() {
		//build the firstRaceLockButton variable
		firstRaceLockButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(firstRaceLockButton.getText() == "Lock") {
					firstRaceTimeTracker = Integer.parseInt((String)timeFirstRaceInputField.getValue());	//set the varible to keep track if the time changed
					firstRaceTime = Integer.parseInt((String)timeFirstRaceInputField.getValue());	//set the firstRaceTime variable
					timeFirstRaceInputField.setEditable(false);
					firstRaceLockButton.setText("Unlock");
					
					//if the time changes set the flag
					if(firstRaceTimeTracker != firstRaceTime) {
						flagFirstRaceTime.setText("*");		//set the time change flag
					}
					
					//check if the button was clicked enough times to change the time change flag
//					if(firstLockButtonPressCount > 1) {
//						flagFirstRaceTime.setText("*");		//set the time change flag
//					}
//					firstLockButtonPressCount++;	//add one to the button click count
				}
				else if(firstRaceLockButton.getText() == "Unlock") {
					timeFirstRaceInputField.setEditable(true);
					firstRaceLockButton.setText("Lock");
					
//					firstLockButtonPressCount++;	//add one to the button click count
					
					//loop through all the teams array and check if all their first race and second race times are not -1 to open the semi final race radio button
					for(int i = 0; i < FestivalObject.teamsArray.size(); i++) {
						//check the teams first race time
						if(FestivalObject.teamsArray.get(i).getFirstRaceTime() == -1) {
							break;
						}
						//check the teams second race time
						else if(FestivalObject.teamsArray.get(i).getSecondRaceTime() == -1) {
							break;	//not all times are set so dont open the semi finals radio button
						}
						//if the last index is equal to the teamsArray size +1 and the time != -1
						else if(i + 1 == FestivalObject.teamsArray.size() && FestivalObject.teamsArray.get(i).getSecondRaceTime() != -1) {
							Schedule.semiFinalsRadioButton.setEnabled(true);
						}
					}
				}
			}
		});
		firstRaceLockButton.setFont(FestivalObject.getFont());
		firstRaceLockButton.setHorizontalAlignment(SwingConstants.CENTER);
		firstRaceLockButton.setBounds(0, 0, 100, 20);
		firstRaceLockButton.setFocusable(false);
		
		//build the secondRaceLockButton variable
		secondRaceLockButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//for the second race lock button click
				if(secondRaceLockButton.getText() == "Lock") {
					secondRaceTime = Integer.parseInt((String)timeSecondRaceInputField.getValue());	//set the secondRaceTime variable
					timeSecondRaceInputField.setEditable(false);
					secondRaceLockButton.setText("Unlock");
					
					//check if the button was clicked enough times to change the time change flag
//					if(secondLockButtonPressCount > 1) {
//						flagSecondRaceTime.setText("*");		//set the time change flag
//					}
//					secondLockButtonPressCount++;	//add one to the button click count
				}
				else if(secondRaceLockButton.getText() == "Unlock") {
					timeSecondRaceInputField.setEditable(true);
					secondRaceLockButton.setText("Lock");
					
//					secondLockButtonPressCount++;	//add one to the button click count
					
					//loop through all the teams array and check if all their first race and second race times are not -1 to open the semi final race radio button
					for(int i = 0; i < FestivalObject.teamsArray.size(); i++) {
						//check the teams first race time
						if(FestivalObject.teamsArray.get(i).getFirstRaceTime() == -1) {
							break;
						}
						//check the teams second race time
						else if(FestivalObject.teamsArray.get(i).getSecondRaceTime() == -1) {
							break;	//not all times are set so dont open the semi finals radio button
						}
						//if the last index is equal to the teamsArray size +1 and the time != -1
						else if(i + 1 == FestivalObject.teamsArray.size() && FestivalObject.teamsArray.get(i).getSecondRaceTime() != -1) {
							Schedule.semiFinalsRadioButton.setEnabled(true);
						}
					}
				}
			}
		});
		secondRaceLockButton.setFont(FestivalObject.getFont());
		secondRaceLockButton.setHorizontalAlignment(SwingConstants.CENTER);
		secondRaceLockButton.setBounds(0, 0, 100, 20);
		secondRaceLockButton.setFocusable(false);
		
		//build the semiFinalRaceLockButton variable
		semiFinalRaceLockButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//for the semi final race button click
				if(semiFinalRaceLockButton.getText() == "Lock") {
					semiFinalRaceTime = Integer.parseInt((String)timeSemiFinalRaceInputField.getValue());	//set the semiFinalRaceTime variable
					timeSemiFinalRaceInputField.setEditable(false);
					semiFinalRaceLockButton.setText("Unlock");
					
					//check if the button was clicked enough times to change the time change flag
//					if(semiFinalLockButtonPressCount > 1) {
//						flagSemiFinalRaceTime.setText("*");		//set the time change flag
//					}
//					semiFinalLockButtonPressCount++;	//add one to the button click count
				}
				else if(semiFinalRaceLockButton.getText() == "Unlock") {
					timeSemiFinalRaceInputField.setEditable(true);
					semiFinalRaceLockButton.setText("Lock");
					flagSemiFinalRaceTime.setText("*");
					
//					semiFinalLockButtonPressCount++;	//add one to the button click count
					
					//loop through all the teams array and check if all their semi final race times are not -1 to open the finals race radio button
					for(int i = 0; i < FestivalObject.teamsArray.size(); i++) {
						if(FestivalObject.teamsArray.get(i).getSemiFinalRaceTime() == -1) {
							break;	//not all times are set so dont open the finals radio button
						}
						//if the last index is equal to the teamsArray size +1 and the time != -1
						else if(i + 1 == FestivalObject.teamsArray.size() && FestivalObject.teamsArray.get(i).getSemiFinalRaceTime() != -1) {
							Schedule.finalsRadioButton.setEnabled(true);
						}
					}
				}
			}
		});
		semiFinalRaceLockButton.setFont(FestivalObject.getFont());
		semiFinalRaceLockButton.setHorizontalAlignment(SwingConstants.CENTER);
		semiFinalRaceLockButton.setBounds(0, 0, 100, 20);
		semiFinalRaceLockButton.setFocusable(false);
		
		//build the finalRaceLockButton variable
		finalRaceLockButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//for the final race button click
				if(finalRaceLockButton.getText() == "Lock") {
					finalRaceTime = Integer.parseInt((String)timeFinalRaceInputField.getValue());	//set the finalRaceTime variable
					timeFinalRaceInputField.setEditable(false);
					finalRaceLockButton.setText("Unlock");
					
					//check if the button was clicked enough times to change the time change flag
//					if(finalLockButtonPressCount > 1) {
//						flagFinalRaceTime.setText("*");		//set the time change flag
//					}
//					finalLockButtonPressCount++;	//add one to the button click count
				}
				else if(finalRaceLockButton.getText() == "Unlock") {
					timeFinalRaceInputField.setEditable(true);
					finalRaceLockButton.setText("Lock");
					
//					finalLockButtonPressCount++;	//add one to the button click count
				}
			}
		});
		finalRaceLockButton.setFont(FestivalObject.getFont());
		finalRaceLockButton.setHorizontalAlignment(SwingConstants.CENTER);
		finalRaceLockButton.setBounds(0, 0, 100, 20);
		finalRaceLockButton.setFocusable(false);
		
		//build the time change flag labels
		flagFirstRaceTime.setHorizontalAlignment(SwingConstants.CENTER);
		flagFirstRaceTime.setFont(FestivalObject.getFont());
		
		flagSecondRaceTime.setHorizontalAlignment(SwingConstants.CENTER);
		flagSecondRaceTime.setFont(FestivalObject.getFont());
		
		flagSemiFinalRaceTime.setHorizontalAlignment(SwingConstants.CENTER);
		flagSemiFinalRaceTime.setFont(FestivalObject.getFont());
		
		flagFinalRaceTime.setHorizontalAlignment(SwingConstants.CENTER);
		flagFinalRaceTime.setFont(FestivalObject.getFont());
	}
	
	/**
	 * Constructor that sets the teamName.
	 * Inputs - String name - used to set the private teamName string.
	 * Outputs - Creates a new TeamObject object with set teamName
	 */
	public TeamObject(String name) {
		this();		//call the first constructor to build the lock buttons
		teamName = name;
	}
	
	/**
	 * Constructor that sets the name and category. TESTING CONSTRUCTOR?
	 * Inputs 	- String name - used to set the private teamName string.
	 * 			- String cat - sets the private category string.
	 * Outputs - Creates a new TeamObject object with set teamName and category.
	 */
	public TeamObject(String name, String cat) {
		this();		//call the first constructor to build the lock buttons
		teamName = name;
		category = cat;
	}
	
	/**
	 * Sets the private String teamName variable.
	 * Inputs - String name - String to set the teamName variable.
	 * Outputs - None.
	 */
	public void setTeamID(int num) {
		teamID = num;
	}
	
	/**
	 * Sets the private int teamID variable.
	 * Inputs - int name - int to set the teamID variable.
	 * Outputs - None.
	 */
	public void setTeamName(String name) {
		teamName = name;
	}
	
	/**
	 * Sets the private int place variable.
	 * Inputs - String cat - String to set the category variable.
	 * Outputs - None.
	 */
	public void setCategory(String cat) {
		category = cat;
	}
	
	/**
	 * Sets the private String category variable.
	 * Inputs - int place - integer to set the place variable.
	 * Outputs - None.
	 */
	public void setPlace(int num) {
		place = num;
	}
	
	/**
	 * Sets the priavte int firstRaceTime variable.
	 * Inputs - int time - integer to set the firstRaceTime varible.
	 * Outputs - None.
	 */
	public void setFirstRaceTime(int time)
	{
		firstRaceTime = time;
	}
	
	/**
	 * Sets the private int secondRaceTime variable.
	 * Inputs - int time - integer to set the secondRaceTime variable. 
	 * Outputs - None.
	 */
	public void setSecondRaceTime(int time)
	{
		secondRaceTime = time;
	}
	
	/**
	 * Sets the private int semifinalRaceTime variable.
	 * Inputs - int time - integer to set the semiFinalRaceTime variable.
	 * Outputs - None.
	 */
	public void setSemiFinalRaceTime(int time)
	{
		semiFinalRaceTime = time;
	}
	
	/**
	 * Sets the private int finalRaceTime variable.
	 * Inputs - int time - integer to set the finalRaceTime variable.
	 * Outputs - None.
	 */
	public void setFinalRaceTime(int time)
	{
		finalRaceTime = time;
	}
	
	/**
	 * Sets the private int averagedRaceTime variable.
	 * Inputs - int time - integer to set the averagedraceTime variable.
	 * Outputs - None.
	 */
	public void setAveragedRaceTime(int time)
	{
		averagedRaceTime = time;
	}
	
	/**
	 * Gets the private int teamID variable. 
	 * Inputs - None.
	 * Outputs - Returns the teamID variable. 
	 */
	public int getTeamID() {
		return teamID;
	}
	
	/**
	 * Gets the private String teamName variable. 
	 * Inputs - None.
	 * Outputs - Returns the teamName variable. 
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Gets the private String category variable.
	 * Inputs - None.
	 * Outputs - Returns the category variable.
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Gets the private int place variable.
	 * Inputs - None.
	 * Outputs - Returns the place variable.
	 */
	public String getPlace() {
		return category;
	}
	
	/**
	 * Gets the private int firstRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the firstRaceTime variable.
	 */
	public int getFirstRaceTime()
	{
		return firstRaceTime;
	}
	
	/**
	 * Gets the private int secondRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the secondRaceTime variable.
	 */
	public int getSecondRaceTime()
	{
		return secondRaceTime;
	}
	
	/**
	 * Gets the private int semiFinalraceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the semiFinalRaceTime variable.
	 */
	public int getSemiFinalRaceTime()
	{
		return semiFinalRaceTime;
	}
	
	/**
	 * Gets the private int finalRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the finalRaceTime variable.
	 */
	public int getFinalRaceTime()
	{
		return finalRaceTime;
	}
	
	/**
	 * Gets the private int averageRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the averagedRaceTime variable.
	 */
	public int getAveragedRaceTime()
	{
		return averagedRaceTime;
	}
}
