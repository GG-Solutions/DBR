package DBRF;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import javax.management.timer.TimerMBean;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import java.util.ArrayList;		//allows resizable arrays
import java.util.Collections;

public class TimeTrialRaceGeneration {
	
	private static int currentTime;	//stores the current time to generate the schedule times
	private static int startTime = 900;	//hard coded day starting time
	private static int rowCounter = 0;		//counting the rows for proper placement while generating UI in mig layout
	private static boolean firstRoundEh = true;	//used to set proper time for the races
	
	/**
	 * This function completely generates the Time Trial Races. It is called when the user first goes to the schedule from teh main menu.
	 * Inputs 	- int numOfLanes - From global variable.
	 * 			- ArrayList<ArrayList<Integer>> breaksArray - Global ArrayList that is storing the breaks for the event.
	 * 			- ArrayList<RaceObject> raceCards - Global ArrayList that has all the races.
	 * 			- ArrayList<TeamObject> teamsArray - Global ArrayList that has all the teams.
	 * 			- JPanel panel - Panel initialized in Schedule.
	 * Outputs 	- Adding UI components to the input JPanel panel.
	 * 			- Adds the generated races to the RaceCards ArrayList.
	 */
	public static void generateTimeTrailRaces(JPanel panel) {
		
		ArrayList<TeamObject> teams = new ArrayList<TeamObject>(FestivalObject.teamsArray);		//duplicate the teams array
		ArrayList<ArrayList<Integer>> breaks = new ArrayList<ArrayList<Integer>>(FestivalObject.breaksArray);	//duplicate the breaks array so the duplicate can be modified
		RaceObject race = new RaceObject();		//create a new race to populate and later send to the racesArray
		
		rowCounter = 0;		//reset the counter
		
		//main loop ------------------------------------------------------------------------------------------------------------------------
		//go through everything twice so each team races twice
		for(int o = 0; o < 2; o++) {
			
			teams = new ArrayList<TeamObject>(FestivalObject.teamsArray);		//reset the teams1 arraylist
			Collections.shuffle(teams);	//shuffle the arraylist
			
			//round the number up cause you will always need that 
			for(int i = 0; i < Math.ceil((double)FestivalObject.teamsArray.size() / (double)FestivalObject.numOfLanes); i++) {
				
				race = new RaceObject();	//create a new raceCard to change
				
				//figure out the raceTime
				if(firstRoundEh == true) {
					currentTime = startTime;
					firstRoundEh = false;
				}
				else {
					//race time generation
					if((currentTime + FestivalObject.timeBetweenRaces) >= breaks.get(0).get(0)) {
						currentTime = breaks.get(0).get(1);
						breaks.remove(0);
						//add ability to recommend a time change of the break?
							//refer to programming notes doc
					}
					//if no breaks were detected so just add to the current time
					else {
						currentTime += FestivalObject.timeBetweenRaces;
					}
				}
				
				//format to the correct time by doing mod 60
				String t = Integer.toString(currentTime);
				int time = Integer.parseInt(Integer.toString(currentTime).substring(t.length() - 2));	//get the last 2 digits of currentTime
				currentTime /= 100;		//abruptly cut off the last 2 digits
				currentTime *= 100;		//add two 0's back on to the currentTime
				//mod 60 if the last two digits are above or equal to 60
				if(time >= 60 ) {
					currentTime += 100;
					time %= 60;
				}
				currentTime += time;	//add the formatted minutes back to the currentTime
				
				
				//add the race label "Race # _ at"
				JLabel raceNumberLabel = new JLabel("Race # " + (FestivalObject.racesArray.size() + 1) + " at");	//auto-increment the race number
				raceNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
				raceNumberLabel.setFont(FestivalObject.getFont());
				panel.add(raceNumberLabel, "flowx,cell 0 " + rowCounter + ",aligny center");
				
				panel.add(race.setTimeInputField(currentTime), "cell 1 " + rowCounter);		//add the race time test field
				
				panel.add(race.getEditTimeButton(), "cell 1 " + rowCounter);
				
				rowCounter += 1;
				
				//create place label
				JLabel lblPlace = new JLabel("Place");
				lblPlace.setHorizontalAlignment(SwingConstants.CENTER);
				lblPlace.setFont(FestivalObject.getFont());
				panel.add(lblPlace, "flowx,cell 0 " + rowCounter + ",growx,aligny center");
				
				//create team name label
				JLabel lblTeamName = new JLabel("Team Name");
				lblTeamName.setHorizontalAlignment(SwingConstants.LEADING);
				lblTeamName.setFont(FestivalObject.getFont());
				panel.add(lblTeamName, "cell 1 " + rowCounter + ",growx,aligny center");
				
				//create lane label
				JLabel lblLane = new JLabel("Lane");
				lblLane.setHorizontalAlignment(SwingConstants.LEADING);
				lblLane.setFont(FestivalObject.getFont());
				panel.add(lblLane, "cell 2 " + rowCounter + ",growx,aligny center");
				
				//create category label
				JLabel lblCategory = new JLabel("Category");
				lblCategory.setHorizontalAlignment(SwingConstants.LEADING);
				lblCategory.setFont(FestivalObject.getFont());
				panel.add(lblCategory, "cell 3 " + rowCounter + ",growx,aligny center");
				
				//create flag label
				JLabel lblFlag = new JLabel("*");
				lblFlag.setHorizontalAlignment(SwingConstants.CENTER);
				lblFlag.setFont(FestivalObject.getFont());
				panel.add(lblFlag, "cell 4 " + rowCounter + ",aligny center");
				
				//create time label
				JLabel lblTime = new JLabel("Time");
				lblTime.setHorizontalAlignment(SwingConstants.LEADING);
				lblTime.setFont(FestivalObject.getFont());
				panel.add(lblTime, "cell 5 " + rowCounter + ",growx,aligny center");
				
				rowCounter += 1;
				
				//get the numOfLanes amount of teams from then multi-dimensional arraylist for this race
				ArrayList<TeamObject> theseTeams = new ArrayList<TeamObject>();		//new array list to populate - temporarily stores the teams in each race
				
				//populate the theseTeams arraylist for each race
				for(int k = 0; k < FestivalObject.numOfLanes; k++) {
					//if there are still team objects left in the arraylist
					if(teams.size() > 0) {
						//only check this if k == 0
						if(((teams.size() - FestivalObject.numOfLanes) <= 1) && (k == 0)) {		//always results in 2 or less?
							
							//add all of the teams to the current race except the last one
								//pair the last one with the last reamaining team in the arraylist
							//if same amount of teams as lanes just add them all to the race
							if((teams.size() - FestivalObject.numOfLanes) == 0) {
								for(int j = 0; j < FestivalObject.numOfLanes;) {
									theseTeams.add(teams.get(0));
									teams.remove(0);	//remove itself?
									break;
								}
							}
							
							//ex. if there is 4 teams left and 3 lanes, get 2 teams(numOfLanes - 1)
							for(int j = 0; j < FestivalObject.numOfLanes - 1; j++) {
								theseTeams.add(teams.get(0));
								teams.remove(0);
							}
							break;	//break generation if 2 or less teams are left so that there is always 2 teams racing, never 1
						}
						else {
							theseTeams.add(teams.get(0));	//get the object at the first index all the time
							teams.remove(0);
						}
					}
					//do this if no checking needs to be done
					else {		//idk if you need this here - kinda a safety
						teams.remove(0);	//remove the first dimension
						break;	//no more objects left to take out
					}
				}
				
				int tempSize = theseTeams.size();	//not sure why i need this to make it work yet
				
				//START OF THE LOOP ------------------------------------------------------------------------------------------------------- generate the teams
				//use a while loop instead? then i can have the condition set to false to break from the loop once all the races have been generated
				//and all the conditions were met
					//every team raced twice, etc.
				for(int k = 0; k < tempSize; k++) {		//need algorithm to figure out how many races there will be? - wont know how many races there are supposed to be
					
					race = new RaceObject();	//does this refresh the last RaceObject?
					
					if(k == 0) {
						rowCounter += 0;
					}
					else {
						rowCounter += 1;
					}
					
					//adding the place label under the Place heading
					JLabel lblNewLabel = new JLabel("-");	//set it to a dash and change it when the times are locked in?
					lblNewLabel.setName("lblNewLabel" + (i + 1));
					lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel.setFont(FestivalObject.getFont());
					panel.add(lblNewLabel, "flowx,cell 0 " + rowCounter + ",growx,aligny center");
					
					//adding the team name label under the Team Name heading
					JLabel lblMyTeamName = new JLabel(theseTeams.get(0).getTeamName());
					lblMyTeamName.setHorizontalAlignment(SwingConstants.LEADING);
					lblMyTeamName.setFont(FestivalObject.getFont());
					panel.add(lblMyTeamName, "cell 1 " + rowCounter + ",growx,aligny center");
					
					//adding the lane number label under the Lane heading
					JLabel label_1 = new JLabel(Integer.toString(k+1));
					label_1.setHorizontalAlignment(SwingConstants.LEADING);
					label_1.setFont(FestivalObject.getFont());
					panel.add(label_1, "cell 2 " + rowCounter + ",growx,aligny center");
					
					//adding the teams category label under the Category heading
					JLabel lblMixed = new JLabel(theseTeams.get(0).getCategory());
					lblMixed.setHorizontalAlignment(SwingConstants.LEADING);
					lblMixed.setFont(FestivalObject.getFont());
					panel.add(lblMixed, "cell 3 " + rowCounter + ",growx,aligny center");
					
					//adding the space character label under the * heading for the time change flag
					JLabel label_2 = new JLabel(" ");	//first set it to just a space character
					label_2.setHorizontalAlignment(SwingConstants.CENTER);
					label_2.setFont(FestivalObject.getFont());
					panel.add(label_2, "cell 4 " + rowCounter + ",aligny center");
					
					panel.add(theseTeams.get(0).getTimeInputField(o + 1), "cell 5 " + rowCounter + ",growx,aligny center");
					
					panel.add(theseTeams.get(0).getLockButton(o + 1), "cell 6 " + rowCounter);
					
					//add the print button on the second loop
					if(k == 0) {
						panel.add(race.getPrintButton(), "cell 6 " + rowCounter);
					}
					
					//set everything in the race object
					
					race.addTeamToRace(theseTeams.get(0));	//store the team to the ArrayList in the race object
					race.setCategory(theseTeams.get(0).getCategory());	//get the category from the team
					
					theseTeams.remove(0);	//remove the team from the duplicated array list so the index will always be 0 to get information
				}
				//END OF FOR LOOP FOR THE TEAMS --------------------------------------------------------------------------------------------------------
				
				race.setRaceNumber(FestivalObject.racesArray.size() + 1);		//set the race number
				race.setRaceTime(currentTime);		//set the race time
				FestivalObject.racesArray.add(race);		//lastly, add the created RaceObject to the global ArrayList
				
//				theseTeams.remove(0);	//remove the team from the duplicated array list so the index will always be 0 to get information
				
				rowCounter += 1;
				//END OF FOR LOOP FOR ONE RACE ----------------------------------------------
			}
			//END OF FOR LOOP FOR ALL THE RACES ----------------------------------------------
		}
	}
}
