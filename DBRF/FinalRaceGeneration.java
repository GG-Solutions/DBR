package DBRF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FinalRaceGeneration {
	
	private static int currentTime;	//stores the current time to generate the schedule times
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
	public static void generateFinalRaces(JPanel panel) {
		if(FestivalObject.generatedFinalRacesEh == true) {
		ArrayList<TeamObject> tm = new ArrayList<TeamObject>(FestivalObject.teamsArray);		//duplicate the teams array
		
		//sort the duplicated tm ArrayList based on the semiFinalRaceTime in ascending order before separating by category
			//this makes them stay sorted before separation
		Collections.sort(tm, new Comparator<TeamObject>() {
			public int compare(TeamObject o1, TeamObject o2) {
				return String.format("%06d", o1.getSemiFinalRaceTime()).compareTo(String.format("%06d", o2.getSemiFinalRaceTime()));
			}
		});
		
		//loop to set th eplace that the team is in
		//teams are sorted in order by best time right above this already
		for(int i = 0; i< FestivalObject.teamsArray.size(); i++) {
			FestivalObject.teamsArray.get(i).setPlace(i + 1);		//set the teams place
		}
		
		//empty multidimensional arraylist to separate the teams by category
		ArrayList<ArrayList<TeamObject>> tmCat	= new ArrayList<ArrayList<TeamObject>>();	//do I need the whole teamobject stored? - prob easiest
		
		//loop through the teams and pre add the correct amount of spots based on the category name
		for(int i = 0; i < FestivalObject.categoriesArray.size() - 1; i++) {
			tmCat.add(new ArrayList<TeamObject>());
		}
//		String thisOne = "";
		//loop through all the teams and separate them by categories
		for(int i = 0; i < FestivalObject.teamsArray.size(); i++) {
			//loop through the categories to find a match
			for(int j = 0; j < FestivalObject.categoriesArray.size(); j++) {
				//check if the teams category matches the one at the index of the categoriesArray and is not the Special category
				if(tm.get(0).getCategory() == FestivalObject.categoriesArray.get(j) && 
//						(tmCat.get(j).size() < FestivalObject.numOfLanes * 2) && 
						!(tm.get(j).getCategory() == "Special")) {	//only get enough teams from each category for 2 races?
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
		
//		System.out.println(thisOne);
		//print out everything in tmCat for TESTING
//		for(int i = 0; i < tmCat.size(); i++) {
//			for(int j = 0; j < tmCat.get(i).size(); j++) {
//				System.out.println(i + " - " + tmCat.get(i).size());
//			}
//		}
		
		ArrayList<ArrayList<Integer>> breaks = new ArrayList<ArrayList<Integer>>(FestivalObject.breaksArray);	//duplicate the breaks array so the duplicate can be modified
		
		boolean doneGenEh = false;		//set to true when do generating races
		int i = 0;	//do i need this? - changed from next for loop to while loop
		rowCounter = 0;		//reset the row counter
		
		//main loop ------------------------------------------------------------------------------------------------------------------------
		//each team races once
//		for(int i = 0; i < Math.ceil(teams.size() / numOfLanes); i++) {
		while(doneGenEh == false) {
			
			RaceObject race = new RaceObject();	//create a new raceCard to change
			
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
			
			//get the numOfLanes amount of teams from then multi-dimensional arraylist for this race
			
			ArrayList<TeamObject> theseTeams = new ArrayList<TeamObject>();		//new array list to populate - temporarily stores the teams in each race
			
			//populate the theseTeams arraylist for each race
			for(int k = 0; k < FestivalObject.numOfLanes; k++) {
				//if there are still team objects left in the arraylist
				if(tmCat.get(0).size() > 0) {
					//only check this if k == 0
					if(((tmCat.get(0).size() - FestivalObject.numOfLanes) <= 1) && (k == 0)) {		//always results in 2 or less?
						
						//add all of the teams to the current race except the last one
							//pair the last one with the last reamaining team in the arraylist
						
						if((tmCat.get(0).size() - FestivalObject.numOfLanes) == 0) {
							theseTeams.addAll(tmCat.get(0));
							tmCat.remove(0);
							break;
						}
						
						for(int j = 0; j < FestivalObject.numOfLanes - 1; j++) {
							theseTeams.add(tmCat.get(0).get(0));
							tmCat.get(0).remove(0);
						}
						
						if(tmCat.get(0).size() == 0) {
							tmCat.remove(0);
						}
						break;	//break generation if 2 or less teams are left so that there is always 2 teams racing, never 1
					}
					//do this if no checking needs to be done
					else {
						theseTeams.add(tmCat.get(0).get(0));	//get the object at the first index all the time
						tmCat.get(0).remove(0);
					}
				}
				//delete the index 0
				else {		//idk if you need this here - kinda a safety
					tmCat.remove(0);	//remove the first dimension
					break;	//no more teams left to take out
				}
			}
			//if there are no more teams to add to races
			if(tmCat.isEmpty()) {
				doneGenEh = true;
			}
			
			//other variables needed for placing the teams in the correct spots
			boolean invertKEh = false;		//create new boolean for switching the index for choosing the team order - start as false
			int tempK = 1;
			TeamObject tempTeam = new TeamObject();
			int size = theseTeams.size();
			
			//put the teams into the correct lanes
			//loop through the team size - 2
			for(int k = 0; k < (size - 2); k++) {		//is this good even though removing an idex and adding again?
				
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
			
			//START OF THE LOOP ------------------------------------------------------------------------------------------------------- 
			for(int k = 0; k < tempSize; k++) {
				
				race = new RaceObject();	//does this refresh the last RaceObject?
				
				if(k == 0) {
					rowCounter += 0;
				}
				else {
					rowCounter++;
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
				
				panel.add(theseTeams.get(0).getTimeFlag(4), "cell 4 " + rowCounter + ",aligny center");		//add the time change flag
				
				panel.add(theseTeams.get(0).getTimeInputField(4), "cell 5 " + rowCounter + ",growx,aligny center");
				
				panel.add(theseTeams.get(0).getLockButton(4), "cell 6 " + rowCounter);
				
				//add the print button on the second loop
				if(k == 0) {
					panel.add(race.getPrintButton(), "cell 6 " + rowCounter);
				}
				
				//set everything in the race object
				race.setCategory(FestivalObject.teamsArray.get(0).getCategory());	//get the category from the team
				
				race.addTeamToRace(theseTeams.get(0));	//store the team to the ArrayList in the race object
				
//				System.out.println(label_3.getName());
				
				theseTeams.remove(0);	//remove the team from the duplicated array list so the index will always be 0 to get information
			}
			//END OF FOR LOOP FOR THE TEAMS --------------------------------------------------------------------------------------------------------
			
			race.setRaceNumber(FestivalObject.racesArray.size() + 1);		//set the race number
			race.setRaceTime(currentTime);		//set the race time
			FestivalObject.racesArray.add(race);		//lastly, add the created RaceObject to the global ArrayList
			
			rowCounter++;
			i++;
		}
		//END OF FOR LOOP FOR THE RACES ----------------------------------------------
		}
		//if the final races have already been generated before, just add the UI components again
		else if(FestivalObject.generatedFinalRacesEh == false) {
			
			int temp = (int)Math.ceil((double)FestivalObject.teamsArray.size() / (double)FestivalObject.numOfLanes) * 2 + 1;	//get the race number that is the start of the semi finals
			int temp2 = temp + (int)Math.ceil((double)FestivalObject.teamsArray.size() / (double)FestivalObject.numOfLanes) + 1;	//get the race number that is the end of the semi finals
			
			//loop through the rest of the races in the array
			for(int i = temp2; i < FestivalObject.racesArray.size(); i++) {
				
				rowCounter = 0;		//reset the counter for row placement
				
				//add the race label "Race # _ at"
				JLabel raceNumberLabel = new JLabel("Race # " + FestivalObject.racesArray.get(i).getRaceNumber() + " at");
				raceNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
				raceNumberLabel.setFont(FestivalObject.getFont());
				panel.add(raceNumberLabel, "flowx,cell 0 " + rowCounter + ",aligny center");
				
				panel.add(FestivalObject.racesArray.get(i).getTimeInputField(), "cell 1 " + rowCounter);
				
				panel.add(FestivalObject.racesArray.get(i).getEditTimeButton(), "cell 1 " + rowCounter);
				
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
				
				//START OF THE LOOP ------------------------------------------------------------------------------------------------------- generate the teams
				//use a while loop instead? then i can have the condition set to false to break from the loop once all the races have been generated
				//and all the conditions were met
					//every team raced twice, etc.
				for(int k = 0; k < FestivalObject.racesArray.get(i).getTeamsRacing().size(); k++) {
					
					if(k == 0) {
						rowCounter += 0;
					}
					else {
						rowCounter += 1;
					}
					
					panel.add(FestivalObject.racesArray.get(i).getTeamsRacing().get(k).getPlaceLabel(3), "flowx,cell 0 " + rowCounter + ",growx,aligny center");	//add the place label
					
					//adding the team name label under the Team Name heading
					JLabel lblMyTeamName = new JLabel(FestivalObject.racesArray.get(i).getTeamsRacing().get(k).getTeamName());
					lblMyTeamName.setHorizontalAlignment(SwingConstants.LEADING);
					lblMyTeamName.setFont(FestivalObject.getFont());
					panel.add(lblMyTeamName, "cell 1 " + rowCounter + ",growx,aligny center");
					
					//adding the lane number label under the Lane heading
					JLabel label_1 = new JLabel(Integer.toString(k + 1));
					label_1.setHorizontalAlignment(SwingConstants.LEADING);
					label_1.setFont(FestivalObject.getFont());
					panel.add(label_1, "cell 2 " + rowCounter + ",growx,aligny center");
					
					//adding the teams category label under the Category heading
					JLabel lblMixed = new JLabel(FestivalObject.racesArray.get(i).getTeamsRacing().get(k).getCategory());
					lblMixed.setHorizontalAlignment(SwingConstants.LEADING);
					lblMixed.setFont(FestivalObject.getFont());
					panel.add(lblMixed, "cell 3 " + rowCounter + ",growx,aligny center");
					
					panel.add(FestivalObject.racesArray.get(i).getTeamsRacing().get(k).getTimeFlag(3), "cell 4 " + rowCounter + ",aligny center");		//add the time change flag
					
					panel.add(FestivalObject.racesArray.get(i).getTeamsRacing().get(k).getTimeInputField(3), "cell 5 " + rowCounter + ",growx,aligny center");	//add the time input field
					
					panel.add(FestivalObject.racesArray.get(i).getTeamsRacing().get(k).getLockButton(3), "cell 6 " + rowCounter);		//add the lock button
					
					//add the print button on the second loop
					if(k == 0) {
						panel.add(FestivalObject.racesArray.get(i).getPrintButton(), "cell 6 " + rowCounter);
					}
				}
				//END OF FOR LOOP FOR THE TEAMS --------------------------------------------------------------------------------------------------------
				
				rowCounter += 1;
				//END OF FOR LOOP FOR ONE RACE ----------------------------------------------
			}
		}
	}
}
