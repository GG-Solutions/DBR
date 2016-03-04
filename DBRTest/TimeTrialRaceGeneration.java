package DBRTest;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;	//used for proper time formatting in HH:MM format?

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;	//creating 
//import java.util.Date;	//main java date class for Date information
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class TimeTrialRaceGeneration 
{
	private int timeBetweenRaces = 40;	//stored in minutes
	private int currentTime;	//stores the current time to generate the schedule times
	private int startTime = 900;	//day starting time	
	private boolean first = true;
	
	//test function to print out the breaks
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
	public void generateTimeTrailRaces(JTextPane textPane, int numOfLanes, int[][] breaksArray, RaceObject[] raceCard, JScrollPane scrollPaneTimeTrials) {	//this should be called on click of the finish button 
		
		//used for placing the just for fun races at certain times? - or are they placed at the end of the other races
			//algorithm to figure out how many time trial races there will be. 
			//(numOfTeams * 2) / number of lanes per race?
			//what about decimal places? * decimal by number of lanes? - need to get a whole number to put the rameaingin people into races.
		
		int[][] breaks = breaksArray;	//duplicate the breaksArray so I can modify the new array
		
		//add a new panel to the UI
		JPanel panel = new JPanel();
		scrollPaneTimeTrials.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[555px][100px:n,right]", "[25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px]"));
		
		for(int i = 0; i < 5; i++) {
			
			//figure out the raceTime
			if(first) {
				currentTime = startTime;
			}
			else {
				if((currentTime + timeBetweenRaces) >= breaks[0][0]) {	//check if the time is over the next break time
					//loop to go through the breaks array to find the next break time that is not -1
					for(int j = 0; j < breaks.length; j++) {
						//check if the value of the position in the array == -1
						if(breaks[j][0] == -1) {
							//used to continue the time generation after all the breaks were passed
//							if(breaks[j] >= breaks.length) {
//								
//							}
//							else {
								continue;
//							}
						}
						//else, set 
						else {
							currentTime = breaks[j][1];		//set the currentTime to the end of the break
							System.out.println("for loop broke at " + breaks[j][0]);
							breaks[j][0] = -1;
							break;
						}
					}
				}
				else {
					currentTime += timeBetweenRaces;
				}
			}
			
			
			//add a new panel to the UI
//			JPanel panel = new JPanel();
//			scrollPaneTimeTrials.setViewportView(panel);
//			panel.setLayout(new MigLayout("", "[555px][100px:n,right]", "[25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px]"));
			
			//add the race label "Race # _ at"
			JLabel raceNumberLabel = new JLabel("Race # 1 at");
			raceNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(raceNumberLabel, "flowx,cell 0 " + i + ",aligny center");
			
			//the time field set to non-editable in the beginning
			JTextField timeField = new JTextField(Integer.toString(currentTime));
			timeField.setEditable(false);
			panel.add(timeField, "cell 0 " + i);
			timeField.setColumns(10);
			
			//edit button for the time field
			JButton editButton = new JButton("edit");
			editButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
			editButton.setForeground(Color.BLUE);
			panel.add(editButton, "cell 0 " + i);
			
			//place label
			JLabel lblPlace = new JLabel("Place");
			lblPlace.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblPlace, "flowx,cell 0 " + (i+1) + ",growx,aligny center");
			
			//team name label
			JLabel lblTeamName = new JLabel("Team Name");
			panel.add(lblTeamName, "cell 0 " + (i+1) + ",growx,aligny center");
			
			//lane label
			JLabel lblLane = new JLabel("Lane");
			lblLane.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblLane, "cell 0 " + (i+1) + ",growx,aligny center");
			
			//category label
			JLabel lblCategory = new JLabel("Category");
			panel.add(lblCategory, "cell 0 " + (i+1) + ",growx,aligny center");
			
			//flag label
			JLabel lblFlag = new JLabel("*");
			panel.add(lblFlag, "cell 0 " + (i+1) + ",aligny center");
			
			//time label
			JLabel lblTime = new JLabel("Time");
			panel.add(lblTime, "cell 0 " + (i+1) + ",growx,aligny center");
			
			//START OF THE LOOP --------------------------------- generate the teams
			//use a while loop instead? then i can have the condition set to false to break from the loop once all the races have been generated
			//and all the conditions were met
				//every team raced twice, etc.
			for(int k = 0; k < 5; k++) {		//need algorithm to figure out how many races there will be? - wont know how many races there are supposed to be
				
				//RaceObject raceCard = new RaceObject();	//create a new raceCard to change
							
				textPane.setText(textPane.getText() + "\n" + currentTime);
				
				//i need to set up each raceObject in this loop
				//raceCard[i].setRaceNumber(i);		//set the race number
				//raceCard[i].setRaceTime(currentTime);		//set the race time
				
				JLabel lblNewLabel = new JLabel("n");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel, "flowx,cell 0 " + (k+2) + ",growx,aligny center");
				
				JLabel lblMyTeamName = new JLabel("My Team Name");
				panel.add(lblMyTeamName, "cell 0 " + (k+2) + ",growx,aligny center");
				
				JLabel label_1 = new JLabel("1");
				label_1.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(label_1, "cell 0 " + (k+2) + ",growx,aligny center");
				
				JLabel lblMixed = new JLabel("Mixed");
				panel.add(lblMixed, "cell 0 " + (k+2) + ",growx,aligny center");
				
				JLabel label_3 = new JLabel("*");
				panel.add(label_3, "cell 0 " + (k+2) + ",aligny center");
				
				JTextField label_2 = new JTextField("");
				panel.add(label_2, "cell 0 " + (k+2) + ",growx,aligny center");
				
				if(k == 0) {
					JButton btnNewButton = new JButton("Lock");
					btnNewButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							label_2.setEnabled(false);
						}
					});
					panel.add(btnNewButton, "cell 1 " + (k+2) + ",alignx center,aligny center");
				}
				
				if(k == 1) {
					JButton btnNewButton = new JButton("Print");
					panel.add(btnNewButton, "cell 1 " + (k+2) + ",alignx center,aligny center");
				}
				
				//put the lock button on the first line that has a team
				//put the print button on the second line
				
				
				//raceArray.length; 		//save the new RaceObject in the global array of RacerObjects
			}
			//END OF FOR LOOP FOR THE TEAMS ----------------------------------------------
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
	
	//deallocate stuff here?
		//i guess on system close
	
}
