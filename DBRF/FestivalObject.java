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

	public static String festName;
	
	public static int timeBetweenRaces;
	
	public static int lanes;
	
	public static ArrayList<ArrayList<Integer>> breaksArray = new ArrayList<ArrayList<Integer>>();
	
	public static ArrayList<String> categoriesArray = new ArrayList<String>();
	
	//public static ArrayList<String> categoryUse = new ArrayList<String>();
	
	//should be TeamObject along w/ the getter and setter
	public static ArrayList<TeamObject> teamsArray = new ArrayList<TeamObject>();
	public static ArrayList<RaceObject> racesArray = new ArrayList<RaceObject>();
	
//	public static Font font = new Font();

	
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
		timeBetweenRaces = tbr;
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
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TestUI window = new TestUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
}
