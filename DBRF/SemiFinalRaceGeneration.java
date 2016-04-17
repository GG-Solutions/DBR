package DBRF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SemiFinalRaceGeneration {
	
	private static int currentTime = -1;	//stores the current time to generate the schedule times
	private static int rowCounter = 0;		//counting the rows for proper placement while generating UI in the mig layout
	
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
	public static void generateSemiFinalRaces(JPanel panel) {
		
		//process all the time trial races to figure out the averaged race time for each team.
		for(int i = 0; i < FestivalObject.teamsArray.size(); i++) {
			//get both races times
			int tempTime1 = FestivalObject.teamsArray.get(i).getFirstRaceTime();
			int tempTime2 = FestivalObject.teamsArray.get(i).getSecondRaceTime();
			
			//if the race time is over 5999 change it so it is not over 10000 instead do 6000 for averaging properly
			//ex. 1:14.57 == 11457, 11457/1000=11, 11/10=1, 1*4=4, 11-4=6, 6000+457=6457.
			if(tempTime1 > 5999) {
				int temp1 = tempTime1 / 1000;	//trim the number to get the digits minus last 3
				int tmp1 = temp1 / 10;	//then get the digit in the 1's column assuming the time is never greater than 59 minutes
				tmp1 *= 4;		//multiply that by 4
				temp1 -= tmp1;		//subtract the result by the digits minus last 3
				temp1 *= 1000;		//multiply by 1000 to get the right digit placement
				String tempS1 = String.format("%06d", tempTime1);		//format a string for the correct about of "digits" - length of 6
				temp1 += Integer.parseInt(tempS1.substring(tempS1.length() - 3, tempS1.length()));		//add to the original race times last 3 digits
			}
			if(tempTime2 > 5999) {
				int temp2 = tempTime2 / 1000;	//trim the number to get the digits minus last 3
				int tmp2 = temp2 / 10;	//then get the digit in the 1's column assuming the time is never greater than 59 minutes
				tmp2 *= 4;		//multiply that by 4
				temp2 -= tmp2;		//subtract the result by the digits minus last 3
				temp2 *= 1000;		//multiply by 1000 to get the right digit placement
				String tempS2 = String.format("%06d", tempTime2);		//format a string for the correct about of "digits" - length of 6
				temp2 += Integer.parseInt(tempS2.substring(tempS2.length() - 3, tempS2.length()));		//add to the original race times last 3 digits
			}
			
			FestivalObject.teamsArray.get(i).setAveragedRaceTime(Math.round((float)(tempTime1 + tempTime2) / 2));	//calculate the average time and round it to closest whole number
			
//			System.out.println(FestivalObject.teamsArray.get(i).getTeamName() + " - " + FestivalObject.teamsArray.get(i).getAveragedRaceTime());
		}
		
		ArrayList<TeamObject> tm = new ArrayList<TeamObject>(FestivalObject.teamsArray);		//duplicate teamsArray
		
//		for(int i = 0; i < teams.size(); i++) {
//			System.out.println(teams.get(i).getTeamName() + " - " + tm.get(i).getAveragedRaceTime());
//		}
		
		//sort the duplicated tm ArrayList based on the averagedRaceTime in ascending order before separating by category
		//this makes them stay sorted before separation
		Collections.sort(tm, new Comparator<TeamObject>() {
			public int compare(TeamObject o1, TeamObject o2) {
				return String.format("%06d", o1.getAveragedRaceTime()).compareTo(String.format("%06d", o2.getAveragedRaceTime()));
			}
		});
		
		//loop to set th eplace that the team is in
		//teams are sorted in order by best time right above this already
		for(int i = 0; i< FestivalObject.teamsArray.size(); i++) {
			FestivalObject.teamsArray.get(i).setPlace(i + 1);		//set the teams place
		}
		
//		System.out.println();
//		System.out.println();
//		
//		for(int i = 0; i < teams.size(); i++) {
//			System.out.println(tm.get(i).getTeamName() + " - " + tm.get(i).getAveragedRaceTime());
//		}
		
		//empty multidimensional arraylist to separate the teams by category
		ArrayList<ArrayList<TeamObject>> tmCat	= new ArrayList<ArrayList<TeamObject>>();	//do i need the whole teamobject stored? - prob easiest
		
		//loop through the teams and pre-add the correct amount of spots based on the category name
		//TODO - creating a race for all categories even if only one is used which isnt good. its making extra races
		for(int i = 0; i < FestivalObject.categoriesArray.size() - 1; i++) {
			tmCat.add(new ArrayList<TeamObject>());
		}
		
		//if the teams category is not in in the tmCat arraylist yet, add it
		
		//loop through all the teams and separate them by categories
		for(int i = 0; i < FestivalObject.teamsArray.size(); i++) {
			//loop through the categories to find a match
			for(int j = 0; j < FestivalObject.categoriesArray.size(); j++) {
				//check if the teams category matches the one at the index of the categoriesArray
				if(tm.get(0).getCategory() == FestivalObject.categoriesArray.get(j)) {
					//do some stuff and add to the tmCat arraylist
					TeamObject temp1 = new TeamObject();
					temp1 = tm.get(0);
					tmCat.get(j).add(temp1);
					tm.remove(0);
					break;
				}
			}
		}
		
		//remove if too many TeamObject ArrayLists were added so there is no extra generated races
		for(int i = 0; i < tmCat.size(); i++) {
			if(tmCat.get(i).isEmpty()) {
				tmCat.remove(i);
				i--; 	//subtract one because the array list got shorter
			}
		}
		
		//print out everything in tmCat for TESTING
//		for(int i = 0; i < tmCat.size(); i++) {
//			for(int j = 0; j < tmCat.get(i).size(); j++) {
//				System.out.println(i + " - " + tmCat.get(i).get(j).getCategory() + " - " + tmCat.get(i).size());
//			}
//		}
		
		ArrayList<ArrayList<Integer>> breaks = new ArrayList<ArrayList<Integer>>(FestivalObject.breaksArray);	//duplicate the breaks array so the duplicate can be modified
		
		boolean doneGenEh = false;		//set to true when done generating enough races
		int i = 0;	//do i need this? - changed from next for loop to while loop
		rowCounter = 0;		//reset the row counter
		
		//main loop to generate the complete race
		while(doneGenEh == false) {
			
			RaceObject race = new RaceObject();		//create a new raceCard to change
			
			//figure out the raceTime
			if(i == 0) {
				currentTime = FestivalObject.getStartDayTime();
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
			if(time >= 60) {
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
			
			rowCounter++;
			
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
			
			rowCounter++;
			
//			for(int k = 0; k < tmCat.size(); k++) {
////				for(int j = 0; j < tmCat.get(k).size(); j++) {
//					System.out.println(k + " - " + tmCat.get(k) + " - " + tmCat.get(k).size());
////				}
//			}
			
			ArrayList<TeamObject> theseTeams = new ArrayList<TeamObject>();		//new array list to populate - temporarily stores the teams in each race
			
			//populate the theseTeams arraylist for each race
			for(int k = 0; k < FestivalObject.numOfLanes; k++) {
				//if there are still team objects left in the arraylist
				if(tmCat.get(0).size() > 0) {	// && tmCat.get(0).size() <= numOfLanes
					//if the amount of teams left minus the amount of lanes is less than or equal to 1 and only if this is the first loop, k == 0
					if(((tmCat.get(0).size() - FestivalObject.numOfLanes) <= 1) && (k == 0)) {		//always results in 2 or more?
						
//						if(tmCat.get(0).size() == 0) {
//							tmCat.remove(0);	//remove if there is none left at the index
//						}
						
						//add all of the teams to the current race except the last one
							//pair the last one with the last reamaining team in the arraylist
						
						//if ther are the smae amount of teams left in the arrylist than the number of lanes
						if((tmCat.get(0).size() - FestivalObject.numOfLanes) == 0) {
							theseTeams.addAll(tmCat.get(0));
							tmCat.remove(0);
							break;
						}
						
						//if same amount of teams as lanes just add them all to the race
//						if((teams.size() - FestivalObject.numOfLanes) == 0) {
//							for(int j = 0; j < FestivalObject.numOfLanes;) {
//								theseTeams.add(teams.get(0));
//								teams.remove(0);	//remove itself?
//								break;
//							}
//						}
						
						//loop to add teams 
						for(int j = 0; j < FestivalObject.numOfLanes - 1; j++) {
//							System.out.println(tmCat.get(0).get(0).getTeamName());
							theseTeams.add(tmCat.get(0).get(0));	//get the next team
							tmCat.get(0).remove(0);		//remove that team
							
							//break if there are no teams left to add for the category
							if(tmCat.get(0).size() == 0) {
								tmCat.remove(0);	//remove if there is none left at the index
								break;
							}
						}
						
						break;	//break adding teams
					}
					//do this if no checking needs to be done
					else {
						theseTeams.add(tmCat.get(0).get(0));	//get the object at the first index all the time
						tmCat.get(0).remove(0);
					}
				}
				//delete the index 0
				else {		//idk if you need this here - kinda a safety?
					tmCat.remove(0);	//remove the first dimension
					break;	//no more teams left to take out
				}
			}
			
			//if there are no more teams to add to races then finish the race generation after this round
			if(tmCat.isEmpty()) {
				doneGenEh = true;	//finished generating races after this loop
			}
			
			//other variables needed for placing the teams in the correct spots
			boolean invertKEh = false;		//create new boolean for switching the index for choosing the team order - start as false
			int tempK = 1;
			TeamObject tempTeam = new TeamObject();
			int size = theseTeams.size();
			
			//put the teams into the correct lanes
			//loop through the team size - 2
			for(int k = 0; k < (size - 2); k++) {		//TODO - is this good even though removing an index and adding again?
				
				if(invertKEh == true) {
					tempK = size - (k + 1);
					invertKEh = false;
				}
				else if(invertKEh == false) {
					tempK = size - 1;
					invertKEh = true;
				}
			
				tempTeam = theseTeams.get(0);	//get the first team from the array list to move
				theseTeams.add(tempK, tempTeam);	//put the team in the correct spot
				theseTeams.remove(0);		//remove the team from the array
			}
			
			int tempSize = theseTeams.size();	//not sure why i need this to make it work yet
			
			//start of the loop to add teams to the race 
			for(int k = 0; k < tempSize; k++) {
				
				race = new RaceObject();	//reset the race object to a new one
				
				//check if the loop just started for proper row placment
				if(k == 0) {
					rowCounter += 0;	//dont add anything to the row count
				}
				else {
					rowCounter++;	//add one to the row count
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
				
				panel.add(theseTeams.get(0).getTimeFlag(3), "cell 4 " + rowCounter + ",aligny center");		//add the time change flag
				
				panel.add(theseTeams.get(0).getTimeInputField(3), "cell 5 " + rowCounter + ",growx,aligny center");	//add the time inoput field
				
				panel.add(theseTeams.get(0).getLockButton(3), "cell 6 " + rowCounter);		//add the lock button
				
				//add the print button on the first loop only TODO - add to second and get locks on first again
				if(k == 0) {
					panel.add(race.getPrintButton(), "cell 6 " + rowCounter);
				}
				
				//set everything in the race object
				race.setCategory(FestivalObject.teamsArray.get(0).getCategory());	//get the category from the team
				
				race.addTeamToRace(theseTeams.get(0));	//store the team to the ArrayList in the race object
				
				theseTeams.remove(0);	//remove the team from the duplicated array list so the index will always be 0 to get information
			}
			//end of loop for adding the teams to the race
			
			race.setRaceNumber(FestivalObject.racesArray.size() + 1);		//set the race number
			race.setRaceTime(currentTime);		//set the race time
			FestivalObject.racesArray.add(race);		//lastly, add the created RaceObject to the global ArrayList
			
			rowCounter++;	//add one to the row count
			i++;	//add one because this is a while loop
		}
	}
}
