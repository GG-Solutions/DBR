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
	public static String festivalName = "";
	public static int timeBetweenRaces = 20;
	public static int numOfLanes = 3;
	public static ArrayList<ArrayList<Integer>> breaksArray = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<String> categoriesArray = new ArrayList<String>();
	public static ArrayList<TeamObject> teamsArray = new ArrayList<TeamObject>();
	public static ArrayList<RaceObject> racesArray = new ArrayList<RaceObject>();
	
	private static Font thisFont = null;
	
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

	public static void setCategorys(ArrayList<String> category) {
		categoriesArray = category;
	}
	
	public static void setFont(Font font) {
		thisFont = font;
	}
	
	public static Font getFont() {
		Font font = new Font("Dialog", Font.BOLD, 12);
		
		return font;
	}
	
//	public static ArrayList<String> getCategoryUse() {
//		return categoryUse;
//	}
//
//	public void setCategory_Use(ArrayList<String> categoryUse) {
//		FestivalObject.categoryUse = categoryUse;
//	}
	
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
					MainMenu.initialize();		//staring at the main menu for now
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
