package DBRTest;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;	//used for proper time formatting in HH:MM format?
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
//import java.util.Date;	//main java date class for Date information
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import java.util.ArrayList;		//allows resizable arrays
import java.util.Collections;

import net.miginfocom.swing.MigLayout;

public class TimeTrialRaceGeneration 
{
	private int timeBetweenRaces = 40;	//stored in minutes
	private int currentTime;	//stores the current time to generate the schedule times
	private int startTime = 900;	//day starting time	
	private boolean firstPass = true;
	
	private int rowCounter = 0;		//counting the rows for proper placement
	
	//test function to print out the breaks using the [][] array
	public void showBreaks(JTextPane breaksPane, int[][] breaksArray) {
		for(int i = 0; i < breaksArray.length; i++) {
			breaksPane.setText(breaksPane.getText() + "\n" + breaksArray[i][0] + ", " + breaksArray[i][1]);
		}
	}
	
	/**
	 * This function completely generates the Time Trial Races.
	 * inputs - none
	 * outputs - magically creates stuff
	 */
	public void generateTimeTrailRaces(JTextPane textPane, int numOfLanes, ArrayList<ArrayList<Integer>> breaksArray, ArrayList<RaceObject> raceCard, ArrayList<TeamObject> teams, JScrollPane scrollPaneTimeTrials) {	//this should be called on click of the finish button 
		
		//used for placing the just for fun races at certain times? - or are they placed at the end of the other races
			//algorithm to figure out how many time trial races there will be. 
				//do i even need this?
			//(numOfTeams * 2) / number of lanes per race?
			//what about decimal places? * decimal by number of lanes? - need to get a whole number to put the rameaingin people into races.
		
		RaceObject race = new RaceObject();		//used for a temp RaceObject to add to the raceCard ArrayList
		
		//build an array that has all the teams race twice
		ArrayList<TeamObject> teams1 = new ArrayList<TeamObject>(teams);
		ArrayList<TeamObject> teams2 = new ArrayList<TeamObject>(teams);
		
		//mix the teams2 array
		Collections.shuffle(teams2);
		
		//append the mixed teams to the end of the first duplicated array (teams1)
		teams1.addAll(teams2);
		
		ArrayList<ArrayList<Integer>> breaks = new ArrayList<ArrayList<Integer>>(breaksArray);
		
		//add a new panel to the UI
		//add it here so you can deactivate it when moving to other tabs.
		JPanel panel = new JPanel();
		scrollPaneTimeTrials.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[555px][100px:n,right]", "[25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px]"));
		
		//main loop
		//round the number up cause you will always need that
		for(int i = 0; i < Math.ceil(((teams.size() * 2) / numOfLanes)); i++) {
			
			race = new RaceObject();	//resets the RaceObject
			
			//figure out the raceTime
			if(firstPass) {
				currentTime = startTime;
				firstPass = false;
			}
			else {
				//race time generation
				if((currentTime + timeBetweenRaces) >= breaks.get(0).get(0)) {
					currentTime = breaks.get(0).get(1);
					breaks.remove(0);
					//add ability to recommend a time change of the break?
						//refer to programming notes doc
				}
				//if no breaks were detected so just add to the current time
				else {
					currentTime += timeBetweenRaces;
				}
			}
			
			//add the race label "Race # _ at"
			JLabel raceNumberLabel = new JLabel("Race # " + (i+1) + " at");
			raceNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(raceNumberLabel, "flowx,cell 0 " + rowCounter + ",aligny center");
			
			//input mask for the time input
			MaskFormatter raceTimeMask = null;
			try {
				raceTimeMask = new MaskFormatter(" ##h:##m");
			} catch (ParseException e1) {
				//TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//the time field set to non-editable in the beginning
			JFormattedTextField timeField = new JFormattedTextField(raceTimeMask);
			timeField.setText(String.format("%04d", currentTime));	//format output to 4 0s
			timeField.setEditable(false);
			panel.add(timeField, "cell 0 " + rowCounter);
			timeField.setColumns(8);
			
			//edit button for the time field
			JButton editButton = new JButton("edit");
			editButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(editButton.getText() == "edit") {
						editButton.setText("done");
						timeField.setEditable(true);
					}
					else {
						
						//get the race number
						//loop through the remainding races and change the times
							//also change the text boxes
						
//						currentTime = Integer.valueOf(timeField.getText());
//						
//						for(int j = raceCard.get().getRaceNumber(); j < raceCard.size(); j++) {
//							
//						}
						
						editButton.setText("edit");
						timeField.setEditable(false);
						//change all the times on all the other races here
					}
				}
			});
			editButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
			editButton.setForeground(Color.BLUE);
			panel.add(editButton, "cell 0 " + rowCounter);
			
			rowCounter += 1;
			
			//place label
			JLabel lblPlace = new JLabel("Place");
			lblPlace.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblPlace, "flowx,cell 0 " + rowCounter + ",growx,aligny center");
			
			//team name label
			JLabel lblTeamName = new JLabel("Team Name");
			panel.add(lblTeamName, "cell 0 " + rowCounter + ",growx,aligny center");
			
			//lane label
			JLabel lblLane = new JLabel("Lane");
			lblLane.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblLane, "cell 0 " + rowCounter + ",growx,aligny center");
			
			//category label
			JLabel lblCategory = new JLabel("Category");
			panel.add(lblCategory, "cell 0 " + rowCounter + ",growx,aligny center");
			
			//flag label
			JLabel lblFlag = new JLabel("*");
			panel.add(lblFlag, "cell 0 " + rowCounter + ",aligny center");
			
			//time label
			JLabel lblTime = new JLabel("Time");
			panel.add(lblTime, "cell 0 " + rowCounter + ",growx,aligny center");
			
			rowCounter += 1;
			
			//START OF THE LOOP --------------------------------- generate the teams
			//use a while loop instead? then i can have the condition set to false to break from the loop once all the races have been generated
			//and all the conditions were met
				//every team raced twice, etc.
			for(int k = 0; k < numOfLanes; k++) {		//need algorithm to figure out how many races there will be? - wont know how many races there are supposed to be
				
				//RaceObject raceCard = new RaceObject();	//create a new raceCard to change
							
				//textPane.setText(textPane.getText() + "\n" + currentTime);
				
				//i need to set up each raceObject in this loop
				race.setRaceNumber(i + 1);		//set the race number
				race.setRaceTime(currentTime);		//set the race time
				
				if(k == 0) {
					rowCounter += 0;
				}
				else {
					rowCounter += 1;
				}
				
				JLabel lblNewLabel = new JLabel("-");	//set it tto a dash and change it when the times are locked in?
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel, "flowx,cell 0 " + rowCounter + ",growx,aligny center");
				
				JLabel lblMyTeamName = new JLabel(teams1.get(0).getTeamName());
				panel.add(lblMyTeamName, "cell 0 " + rowCounter + ",growx,aligny center");
				
				JLabel label_1 = new JLabel(Integer.toString(k+1));
				label_1.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(label_1, "cell 0 " + rowCounter + ",growx,aligny center");
				
				JLabel lblMixed = new JLabel(teams1.get(0).getCategory());
				panel.add(lblMixed, "cell 0 " + rowCounter + ",growx,aligny center");
				
				JLabel label_2 = new JLabel(" ");	//set it to just a space first
				panel.add(label_2, "cell 0 " + rowCounter + ",aligny center");
				
				//input mask for the time input for each race
				MaskFormatter timeMask = null;
				try {
					timeMask = new MaskFormatter("##m:##s.##ms");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JFormattedTextField label_3 = new JFormattedTextField(timeMask);
				panel.add(label_3, "cell 0 " + rowCounter + ",growx,aligny center");
				
				if(k == 0) {
					JButton btnNewButton = new JButton("Lock");
					btnNewButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							if(btnNewButton.getText() == "Lock") {
								label_3.setEnabled(false);
								//TODO Change the place of the coresponding teams instead of having the dash
								btnNewButton.setText("Unlock");
							}
							else {
								label_3.setEnabled(true);
								btnNewButton.setText("Lock");
							}
						}
					});
					panel.add(btnNewButton, "cell 1 " + rowCounter + ",alignx center,aligny center");
				}
				
				if(k == 1) {
					JButton btnNewButton = new JButton("Print");
					btnNewButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							//export a pdf to print out
							//launch a save-as internal windows function or something
						}
					});
					panel.add(btnNewButton, "cell 1 " + rowCounter + ",alignx center,aligny center");
				}
				
//				race.addTeamToRace(null);		//store the created teamObject into the teamsThatRaced array list
				
				teams1.remove(0);	//remove the team from the duplicated array list
			}
			//END OF FOR LOOP FOR THE TEAMS ----------------------------------------------
			
			rowCounter += 1;
			System.out.println(i);
			raceCard.add(race);		//lastly, add the created race to the ArrayList
		}
		//END OF FOR LOOP FOR THE RACES ----------------------------------------------
		
	//loop to add one whole race block each time?
	//need to add each UI piece at a specific location?
	
	//loop starts - for just one race each time
		//temporary array of the team names for the race to use later for listing in the next loop?
		//time algorithm implementation
			//for first race get the time from the setup variable
				//use that time for the first race
			//second race at the timeBetweenRaces variable to the currentTime?
			//check if the currentTime is greater than the break time.
				//will need to delete break times once they are used? 
				//if it is over then set the time to the start of the break time + 20 min for each break? 
					//need variable for amount of times for breaks?
					//different times for just a regular break and lunch breaks?
			//set the race time and link to the edit button.
				//might need to use the raceID to link up everything since that will be unique every time. 
					//also for the lock and print buttons
		//print all the headings
		//print each team line separately
			//loop this? - needs to be random, refer to old documents to see how it was generated
				//generate the place after the times have been locked?
				//get a team name from somewhere
				//put in a lane
					//auto-increment the lane for the time-trail races
				//get the category of that team from the object
				//leave a spot for the flag position
				//editable time box for the time input
				//add a lock and print button on the top two lines?
					//make a prebuilt button with all the logic and just place it?
			//end loop
	//end loop that generates all the races in the scrollable window
	}
	
	//method to check if all the times are filled in and locked?
		//can loop through the raceCards array and use 
	//if they are, open up the semi-finals radio button
}
