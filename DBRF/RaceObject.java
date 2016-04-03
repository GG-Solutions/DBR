package DBRF;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

public class RaceObject {
	
	private int raceNumber = -1;
	private int raceTime = -1;		//the time at which the race will take place
	private String category = "";	//category variable from the array
	private ArrayList<TeamObject> teamsRacing = new ArrayList<TeamObject>();	//array of teams that are in this race
	private ArrayList<JFormattedTextField> teamRaceTimes = new ArrayList<JFormattedTextField>();	//store the UI that was created?
	
	private JFormattedTextField timeEditField = null;
	private JButton editTimeButton = new JButton("edit");
	private JButton printButton = new JButton("Print");
	
	
	public JFormattedTextField setTimeInputField(int time) {
		
		MaskFormatter timeMask = null;
		
		try {
			timeMask = new MaskFormatter("##h:##m");
			timeMask.setValueContainsLiteralCharacters(false);
			
			timeEditField = new JFormattedTextField(timeMask);
			timeEditField.setValue(String.format("%04d", time));	//format output to four 0's
			timeEditField.setEditable(false);
			timeEditField.setColumns(8);
			timeEditField.setFont(FestivalObject.getFont());
			setRaceTime(time);		//set the raceTime variable
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return timeEditField;
	}
	
	public JFormattedTextField getTimeInputField() {
		return timeEditField;
	}
	
	public JButton getEditTimeButton() {
		return editTimeButton;
	}
	
	public JButton getPrintButton() {
		return printButton;
	}
	
	
	//constructor
	public RaceObject() {
		//build the editTimeButton 
		editTimeButton.addMouseListener(new MouseAdapter() {
			//TODO - will an invokelater function work for this instead?
			public void mouseClicked(MouseEvent e) {
				if(editTimeButton.getText() == "edit") {
					editTimeButton.setText("done");
					timeEditField.setEditable(true);
				}
				else if(editTimeButton.getText() == "done") {
					
					//get the race number
					//loop through the remaining races and change the times
						//also change the text boxes
					
					setRaceTime(Integer.parseInt((String)timeEditField.getValue()));
					
					int tempTime = getRaceTime();	//get this race number for the index
					
					ArrayList<ArrayList<Integer>> breaks = new ArrayList<ArrayList<Integer>>(FestivalObject.breaksArray);	//duplicate the breaks array so the duplicate can be modified
					
					//duplicate the breaksArray
					//loop through and remove the breaks that already passed
					//loop to change the times from the current race on
						//set the race time
						//break when the race time = the start day time
					
					//loop through the breaks to remove any breaks before this raceTime
					for(int i = 0; i < breaks.size(); i++) {
						//if the current break end in the ArrayList is less than this object's race time, remove it
						if(breaks.get(0).get(1) < tempTime) {
							breaks.remove(0);
							System.out.println("took break out, left: " + breaks.size());
						}
						//enough breaks have been removed
						else {
							break;
						}
					}
					
					System.out.println(breaks.size());
					System.out.println("changing race # " + getRaceNumber());
					
					//loop to change all the next race times depending on this object's raceID
					for(int j = getRaceNumber() + 1; j < FestivalObject.racesArray.size(); j++) {
						
						//change the race time
						if((tempTime + FestivalObject.timeBetweenRaces) >= breaks.get(0).get(0)) {
							tempTime = breaks.get(0).get(1);
							breaks.remove(0);
							//add ability to recommend a time change of the break?
								//refer to programming notes doc
						}
						//if no breaks were detected so just add to the current time
						else {
							tempTime += FestivalObject.timeBetweenRaces;
						}
						
						//format to the correct time by doing mod 60
						String t = Integer.toString(tempTime);
						int tempTime2 = Integer.parseInt(Integer.toString(tempTime).substring(t.length() - 2));	//get the last 2 digits of currentTime
						tempTime /= 100;		//abruptly cut off the last 2 digits
						tempTime *= 100;		//add two 0's back on to the currentTime
						//mod 60 if the last two digits are above or equal to 60
						if(tempTime2 >= 60 ) {
							tempTime += 100;
							tempTime2 %= 60;
						}
						tempTime += tempTime2;	//add the formatted minutes back to the currentTime
						
						FestivalObject.racesArray.get(j).setTimeInputField(tempTime);
						FestivalObject.racesArray.get(j).setRaceTime(tempTime);
					}
					
					editTimeButton.setText("edit");
					timeEditField.setEditable(false);
					
					
//					for(int k = 0; k < FestivalObject.breaksArray.size(); k ++) {
//						System.out.println(FestivalObject.breaksArray.get(k).get(0) + " - " + FestivalObject.breaksArray.get(k).get(1));
//					}
					
					//test print out all the race times
					for(int i = 0; i < FestivalObject.racesArray.size(); i++) {
						System.out.println(FestivalObject.racesArray.get(i).getRaceNumber()
								+ " - " + FestivalObject.racesArray.get(i).getRaceTime());
					}
				}
			}
		});
		editTimeButton.setFont(FestivalObject.getFont());
		editTimeButton.setForeground(Color.BLUE);
//		editTimeButton.setBounds(0, 0, 40, 15);
		editTimeButton.setFocusable(false);
		
		//set up the print button
		printButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				System.out.println("clicked print button with race number of: " + getRaceNumber());
				
				//TODO - export a pdf to print out
				JFileChooser saving = new JFileChooser();
				saving.showSaveDialog(null);
			}
		});
		printButton.setFont(FestivalObject.getFont());
		printButton.setHorizontalAlignment(SwingConstants.CENTER);
		printButton.setBounds(0, 0, 100, 20);
		printButton.setFocusable(false);
	}
	
	/**
	 * Sets the private int raceNumber variable.
	 * Inputs - int num - integer to set the racenumber variable.
	 * Outputs - None.
	 */
	public void setRaceNumber(int num) {
		raceNumber = num;
	}
	
	/**
	 * Sets the private int raceTime variable.
	 * Inputs - int time - integer to set the raceTime variable.
	 * Outputs - None.
	 */
	public void setRaceTime(int time) {
		raceTime = time;
	}
	
	/**
	 * Sets the private String category variable for the race.
	 * Inputs - String cat - String to set the category variable.
	 * Outputs - None.
	 */
	public void setCategory(String cat) {
		category = cat;
	}
	
	/**
	 * Gets the private int raceNumber variable.
	 * Inputs - None.
	 * Outputs - Returns the raceNumber variable.
	 */
	public int getRaceNumber() {
		return raceNumber;
	}
	
	/**
	 * Gets the private int raceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the raceTime variable.
	 */
	public int getRaceTime() {
		return raceTime;
	}
	
	/**
	 * Gets the private String category variable.
	 * Inputs - None.
	 * Outputs - Returns the category variable.
	 */
	public String setCategory() {
		return category;
	}
	
	/**
	 * Adds a team to the RaceObject array list of teams that raced in the race
	 * Inputs - TeamObject team - used to add to the private teamsracing ArrayList.
	 * Outputs - None.
	 */
	public void addTeamToRace(TeamObject team) {
		teamsRacing.add(team);
	}
	
	/**
	 * Adds the JFormattedTextField to the private teamRaceTimes Arraylist.
	 * Inputs - JFormattedTextField box - contains the time from the race generation.
	 * Outputs - None.
	 */
	public void addTeamRaceTime(JFormattedTextField box) {
		teamRaceTimes.add(box);
	}
}
