package DBRTest;

import java.util.ArrayList;

public class RaceObject 
{
	private int raceNumber;
	private int raceTime;			//the time at which the race will take place
	private int numberOfLanes;		//numberOfLanes reference from the setup variable
	private ArrayList<TeamObject> teamsRacing;	//array of teams that are in this race
	private String category;//category variable from the array
	
	/**
	 * 
	 * Inputs - 
	 * Outputs - 
	 */
	public void setRaceNumber(int num) {
		raceNumber = num;
	}
	
	public void setRaceTime(int time) {
		raceTime = time;
	}
	
	public void setNumberOfLanes(int lanes) {
		numberOfLanes = lanes;
	}
	
	public void setCategory(String cat) {
		category = cat;
	}
	
	//getters
	public int getRaceNumber() {
		return raceNumber;
	}
	
	public int getRaceTime() {
		return raceTime;
	}
	
	public int getNumberOfLanes() {
		return numberOfLanes;
	}
	
	public String setCategory() {
		return category;
	}
	
	//add a team to the RaceObject array list of teams that raced in the race
	public void addTeamToRace(TeamObject team) {
		teamsRacing.add(team);
	}
}
