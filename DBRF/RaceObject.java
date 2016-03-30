package DBRF;

import java.util.ArrayList;

import javax.swing.JFormattedTextField;

public class RaceObject {
	private int raceNumber;
	private int raceTime;		//the time at which the race will take place
	private String category;	//category variable from the array
	private ArrayList<TeamObject> teamsRacing = new ArrayList<TeamObject>();	//array of teams that are in this race
	private ArrayList<JFormattedTextField> teamRaceTimes = new ArrayList<JFormattedTextField>();	//store the UI that was created?
	
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
