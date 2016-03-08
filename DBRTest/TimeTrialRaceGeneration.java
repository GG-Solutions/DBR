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
		
//		int[][] breaks = breaksArray;	//duplicate the breaksArray so I can modify the new array
		ArrayList<ArrayList<Integer>> breaks = breaksArray;
		
		//add a new panel to the UI
		//add it here so you can deactivate it when moving to other tabs.
		JPanel panel = new JPanel();
		scrollPaneTimeTrials.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[555px][100px:n,right]", "[25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px]"));
		
		for(int i = 0; i < 10; i++) {
			
			race = new RaceObject();	//resets the RaceObject?
//			System.out.println(rowCounter);
			
			//figure out the raceTime
			if(firstPass) {
				currentTime = startTime;
				firstPass = false;
			}
			else {
				//USING THE OLD [][] BREAKS ARRAY -----------------------------
//				if((currentTime + timeBetweenRaces) >= breaks[0][0]) {	//check if the time is over the next break time
//					//loop to go through the breaks array to find the next break time that is not -1
//					for(int j = 0; j < breaks.length; j++) {
//						//check if the value of the position in the array == -1
//						if(breaks[j][0] == -1) {
//							//used to continue the time generation after all the breaks were passed
////							if(breaks[j] >= breaks.length) {
////								
////							}
////							else {
//								continue;
////							}
//						}
//						//else, set 
//						else {
//							currentTime = breaks[j][1];		//set the currentTime to the end of the break
//							System.out.println("for loop broke at " + breaks[j][0]);
//							breaks[j][0] = -1;
//							break;
//						}
//					}
//				}
//				else {
//					currentTime += timeBetweenRaces;
//				}
				// ---------------------------------------------------------------
				
				//new time generation
				if((currentTime + timeBetweenRaces) >= breaks.get(0).get(0)) {
					currentTime = breaks.get(0).get(1);
					breaks.remove(0);
					//add ability to recommend a time change?
						//refer to programming notes doc
				}
				//no breaks were detected so just add to the current time
				else {
					currentTime += timeBetweenRaces;
				}
//				System.out.println(breaks.get(0).get(0));
				
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
			timeField.setText(Integer.toString(currentTime));
			timeField.setEditable(false);
			panel.add(timeField, "cell 0 " + rowCounter);
			timeField.setColumns(10);
			
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
			for(int k = 0; k < 5; k++) {		//need algorithm to figure out how many races there will be? - wont know how many races there are supposed to be
				
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
				
				JLabel lblNewLabel = new JLabel(Integer.toString(rowCounter));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel, "flowx,cell 0 " + rowCounter + ",growx,aligny center");
				
				JLabel lblMyTeamName = new JLabel("My Team Name");
				panel.add(lblMyTeamName, "cell 0 " + rowCounter + ",growx,aligny center");
				
				JLabel label_1 = new JLabel("1");
				label_1.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(label_1, "cell 0 " + rowCounter + ",growx,aligny center");
				
				JLabel lblMixed = new JLabel("Mixed");
				panel.add(lblMixed, "cell 0 " + rowCounter + ",growx,aligny center");
				
				JLabel label_2 = new JLabel("*");
				panel.add(label_2, "cell 0 " + rowCounter + ",aligny center");
				
				//input mask for the time input for each race
				MaskFormatter timeMask = null;
				try {
					timeMask = new MaskFormatter(" ##h:##m.##s");
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
							label_3.setEnabled(false);
						}
					});
					panel.add(btnNewButton, "cell 1 " + rowCounter + ",alignx center,aligny center");
				}
				
				if(k == 1) {
					JButton btnNewButton = new JButton("Print");
					panel.add(btnNewButton, "cell 1 " + rowCounter + ",alignx center,aligny center");
				}
				
				//put the lock button on the first line that has a team
				//put the print button on the second line
				
				
				//raceArray.length; 		//save the new RaceObject in the global array of RaceObjects
			}
			//END OF FOR LOOP FOR THE TEAMS ----------------------------------------------
			
			rowCounter += 1;
			
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
	
	//check if all the times are filled in and locked
	//if they are, open up the semi-finals radio button
}
