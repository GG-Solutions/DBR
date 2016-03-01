package DBRTest;

public class TeamObject 
{
	private String teamName = "";
	private String category = "";
	private int place = -1;		//set to -1 as default?
	
	private char firstRaceTimeFlag = '*';
	private float firstRaceTime = 0.0f; 
	private char secondRaceTimeFlag = '*';
	private float secondRaceTime = 0.0f; 
	private char semiFinalRaceTimeFlag = '*';
	private float semiFinalRaceTime = 0.0f; 
	private char finalRaceTimeFlag = '*';
	private float finalRaceTime = 0.0f; 
	private float averagedRaceTime = 0.0f; 
	
	//constructors
	public TeamObject() {}
	
	//see the UI and add constructors based on that
	public TeamObject(String name) {
		teamName = name;
	}
	
	//setters
	public void setTeamName(String name) {
		teamName = name;
	}
	
	public void setCategory(String cat) {
		category = cat;
	}
	
	public void setFirstRaceTime(float time)
	{
		firstRaceTime = time;
	}
	
	public void setSecondRaceTime(float time)
	{
		secondRaceTime = time;
	}
	
	public void setSemiFinalRaceTime(float time)
	{
		semiFinalRaceTime = time;
	}
	
	public void setFinalRaceTime(float time)
	{
		finalRaceTime = time;
	}
	
	public void setAveragedRaceTime(float time)
	{
		averagedRaceTime = time;
	}
	
	//getters
	public String getTeamName() {
		return teamName;
	}
	
	public String setCategory() {
		return category;
	}
	
	public float getFirstRaceTime()
	{
		return firstRaceTime;
	}
	
	public float getSecondRaceTime()
	{
		return secondRaceTime;
	}
	
	public float getSemiFinalRaceTime()
	{
		return semiFinalRaceTime;
	}
	
	public float getFinalRaceTime()
	{
		return finalRaceTime;
	}
	
	public float getAveragedRaceTime()
	{
		return averagedRaceTime;
	}
	
}
