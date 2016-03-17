package DBRF;

import java.util.ArrayList;


public class FestivalObject extends EventPageSetup{

	
	/**
	 * 
	 */
	//festival object that will contain needed information about the festival
	private static final long serialVersionUID = 1L;

	protected static String festName;
	
	public static int timeBetweenRaces;
	
	public static int lanes;
	
	public static ArrayList<ArrayList<Integer>> breaksArray = new ArrayList<ArrayList<Integer>>();
	
	public static ArrayList<String> categoriesArray = new ArrayList<String>();
	
	protected static ArrayList<String> Category_Use = new ArrayList<String>();
	
	//should be TeamObject along w/ the getter and setter
	public static ArrayList<String> teamsArray = new ArrayList<String>();

	
	public FestivalObject() {};
	
	
	public static String getFestName() {
		return festName;
	}

	public static void setFestName(String festName) {
		FestivalObject.festName = festName;
	}

	public static int getTBR() {
		return timeBetweenRaces;
	}

	public static void setTBR(int tbr) {
		FestivalObject.timeBetweenRaces = tbr;
	}

	public static int getLanes() {
		return lanes;
	}

	public static void setLanes(int lanes) {
		FestivalObject.lanes = lanes;
	}

	public static ArrayList<ArrayList<Integer>> getBreakList() {
		return breaksArray;
	}

	public void setBreakList(ArrayList<ArrayList<Integer>> breakList) {
		FestivalObject.breaksArray = breakList;
	}

	public static ArrayList<String> getCategory() {
		return categoriesArray;
	}

	public void setCategory(ArrayList<String> category) {
		categoriesArray = category;
	}
	
	public static ArrayList<String> getCategory_Use() {
		return Category_Use;
	}

	public void setCategory_Use(ArrayList<String> categoryUse) {
		Category_Use = categoryUse;
	}
	
	public static ArrayList<String> getTeam() {
		return teamsArray;
	}

	public void setTeam(ArrayList<String> team) {
		teamsArray = team;
	}
	
}
