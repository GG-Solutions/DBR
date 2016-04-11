package DBRF;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class TeamObject {
	
	private int teamID = -1;
	private String teamName = "";
	private String category = "";
	private int place = -1;		//set to -1 as default? global place that the team is in
	
	//move the flags to the race object instead?
	private char firstRaceTimeFlag = '*';
	private int firstRaceTime = -1; 
	private char secondRaceTimeFlag = '*';
	private int secondRaceTime = -1; 
	private char semiFinalRaceTimeFlag = '*';
	private int semiFinalRaceTime = -1;
	private char finalRaceTimeFlag = '*';
	private int finalRaceTime = -1; 		//not every team will have a final race time?
	private int averagedRaceTime = -1; 
	
	private JFormattedTextField timeFirstRaceInputField = null;
	private JFormattedTextField timeSecondRaceInputField = null;
	private JFormattedTextField timeSemiFinalRaceInputField = null;
	private JFormattedTextField timeFinalRaceInputField = null;
	
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
				if(firstRaceLockButton.getText() == "Lock" && timeFirstRaceInputField.getValue() != null) {
					firstRaceTime = Integer.parseInt((String)timeFirstRaceInputField.getValue());	//set the firstRaceTime variable
					timeFirstRaceInputField.setEditable(false);
					firstRaceLockButton.setText("Unlock");
					
					
					//loop to check if all the races are locked and not 000000 so that the semi finals radaio button can be unlocked 
//					for(int i = 0; i < FestivalObject.racesArray.size(); i++) {
//						for(int j = 0; j < FestivalObject.racesArray.get(i).getTeamsRacing().size(); j++) {
//							//if the time is 000000 break cause not all have been set
//							if(FestivalObject.racesArray.get(i).get)
//							//else if all are not 000000 then unlock the semi finals radio button and build the panel?
//						}
//					}
					
					
				}
				else if(firstRaceLockButton.getText() == "Unlock") {
					timeFirstRaceInputField.setEditable(true);
					firstRaceLockButton.setText("Lock");
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
				if(secondRaceLockButton.getText() == "Lock" && timeSecondRaceInputField.getValue() != null) {
					secondRaceTime = Integer.parseInt((String)timeSecondRaceInputField.getValue());	//set the secondRaceTime variable
					timeSecondRaceInputField.setEditable(false);
					secondRaceLockButton.setText("Unlock");
				}
				else if(secondRaceLockButton.getText() == "Unlock") {
					timeSecondRaceInputField.setEditable(true);
					secondRaceLockButton.setText("Lock");
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
				if(semiFinalRaceLockButton.getText() == "Lock" && timeSemiFinalRaceInputField.getValue() != null) {
					semiFinalRaceTime = Integer.parseInt((String)timeSemiFinalRaceInputField.getValue());	//set the semiFinalRaceTime variable
					timeSemiFinalRaceInputField.setEditable(false);
					semiFinalRaceLockButton.setText("Unlock");
				}
				else if(semiFinalRaceLockButton.getText() == "Unlock") {
					timeSemiFinalRaceInputField.setEditable(true);
					semiFinalRaceLockButton.setText("Lock");
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
				if(finalRaceLockButton.getText() == "Lock" && timeFinalRaceInputField.getValue() != null) {
					finalRaceTime = Integer.parseInt((String)timeFinalRaceInputField.getValue());	//set the finalRaceTime variable
					timeFinalRaceInputField.setEditable(false);
					finalRaceLockButton.setText("Unlock");
				}
				else if(finalRaceLockButton.getText() == "Unlock") {
					timeFinalRaceInputField.setEditable(true);
					finalRaceLockButton.setText("Lock");
				}
			}
		});
		finalRaceLockButton.setFont(FestivalObject.getFont());
		finalRaceLockButton.setHorizontalAlignment(SwingConstants.CENTER);
		finalRaceLockButton.setBounds(0, 0, 100, 20);
		finalRaceLockButton.setFocusable(false);
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
