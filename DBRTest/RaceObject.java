package DBRTest;

public class RaceObject 
{
	private int raceNumber;;		//auto-incremented value 
	private int raceTime;			//the time at which the race will take place
	private int numberOfLanes;		//numberOfLanes reference from the setup variable
	//array of teams that are in this race
	//category variable from the array
	
	/**
	 * This function auto-increments the race number by one every time it is called. 
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
	
	public int getRaceNumber() {
		return raceNumber;
	}
	
	public int getRaceTime() {
		return raceTime;
	}
	
	public int getNumberOfLanes() {
		return numberOfLanes;
	}
}
