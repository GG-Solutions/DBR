package DBRF;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;


public class FestivalObject {

	
	/**
	 * 
	 */
	//festival object that will contain needed information about the festival
	public static final long serialVersionUID = 1L;

	public static String festivalName;
	
	public static int timeBetweenRaces;
	
	public static int numOfLanes;
	
	public static ArrayList<ArrayList<Integer>> breaksArray = new ArrayList<ArrayList<Integer>>();
	
	public static ArrayList<String> categoriesArray = new ArrayList<String>();
	
	public static ArrayList<String> categoryUse = new ArrayList<String>();
	
	public static ArrayList<TeamObject> teamsArray = new ArrayList<TeamObject>();
	public static ArrayList<RaceObject> racesArray = new ArrayList<RaceObject>();
	
//	public static Font font = new Font();

	
	public FestivalObject() {
		
	};
	
	public static String getFestivalName() {
		return festivalName;
	}

	public static void setFestName(String festName) {
		FestivalObject.festivalName = festName;
	}

	public static int getTBR() {
		return timeBetweenRaces;
	}

	public static void setTBR(int tbr) {
		timeBetweenRaces = tbr;
	}

	public static int getLanes() {
		return numOfLanes;
	}

	public static void setLanes(int lanes) {
		FestivalObject.numOfLanes = lanes;
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
	
	public static ArrayList<String> getCategoryUse() {
		return categoryUse;
	}

	public void setCategory_Use(ArrayList<String> categoryUse) {
		FestivalObject.categoryUse = categoryUse;
	}
	
	public static ArrayList<TeamObject> getTeamsArray() {
		return teamsArray;
	}

	public void setTeamsArray(ArrayList<TeamObject> teams) {
		teamsArray = teams;
	}
	
	//call main here to start the program
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Schedule.initialize();		//testing my thing
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		//setting some stuff for testing - all teams from Kelowna Race Grid 2015
		teamsArray.add(new TeamObject("KDBC High Frequency", "Womens"));
		teamsArray.add(new TeamObject("ODBRC Rogue Dragons", "Womens"));
		teamsArray.add(new TeamObject("KDBC Sonar Dragons", "Womens"));
		teamsArray.add(new TeamObject("A'Breast of Bridge", "Special"));
		teamsArray.add(new TeamObject("KDBC Knotty Pacemakers", "Mixed"));
		teamsArray.add(new TeamObject("Bust n Loose", "Special"));
		teamsArray.add(new TeamObject("KDBC Dragonflies", "Mixed"));
		teamsArray.add(new TeamObject("KDBC Stroke of Luck", "Mixed"));
		teamsArray.add(new TeamObject("Women on Fire", "Mixed"));
		teamsArray.add(new TeamObject("KDBC Dragon in the Drink", "Mixed"));
		teamsArray.add(new TeamObject("KDBC Valley Vixens", "Mixed"));
		teamsArray.add(new TeamObject("KDBC Flowriders", "Mixed"));
		teamsArray.add(new TeamObject("ODBRC DragonFire", "Mixed"));
		teamsArray.add(new TeamObject("Fire On Water", "Mixed"));
		teamsArray.add(new TeamObject("Despirit Housewives", "Mixed"));
		
		//testing for the breaks
		ArrayList<Integer> q = new ArrayList<Integer>();
		q.add(1030);
		q.add(1100);		
		
		ArrayList<Integer> w = new ArrayList<Integer>();
		w.add(1200);
		w.add(1300);		
		
		ArrayList<Integer> e = new ArrayList<Integer>();
		e.add(1530);
		e.add(1600);		
		
		//adding the breaks to the main breaks array
		breaksArray.add(q);
		breaksArray.add(w);
		breaksArray.add(e);
		
		categoriesArray.add("Mixed");
		categoriesArray.add("Womens");
		categoriesArray.add("Special");
		categoriesArray.add("Mens");
	}
}
