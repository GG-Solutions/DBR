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
	public static String festivalName = "";		//default
	public static int startDayTime = 900;		//default to 9 AM
	public static int timeBetweenRaces = 20;	//default to 20 - also is set in the festival setup
	public static int numOfLanes = 3;		//default
	public static ArrayList<ArrayList<Integer>> breaksArray = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<String> categoriesArray = new ArrayList<String>();
	public static ArrayList<TeamObject> teamsArray = new ArrayList<TeamObject>();
	public static ArrayList<RaceObject> racesArray = new ArrayList<RaceObject>();
	
	public static int xPos = 480;
	public static int yPos = 270;
	public static int width = 960;
	public static int height = 540;
	
//	public static String bounds = "";	//need this??
	
	private static Font thisFont = new Font("Dialog", Font.BOLD, 12);		//default the font to this
	
//	public static JPanel userLoginPane;
//	public static JPanel mainMenuPane;
//	public static JPanel eventPageSetupPane;
//	public static JPanel schedulePane;
//	public static JPanel resultsPane;
//	public static JPanel settingsPane;
//	public static JPanel helpPane;
	
//	public static Font font = new Font();
	
	
	//call main here to start the program
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
					frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				
				//add the special category if it is not there already
				//TODO - do this here?????
				for(int i= 0; i < categoriesArray.size(); i++) {
					//if the special category is not in the categories array list yet
					if(!(categoriesArray.get(i).matches("Special")) && i == categoriesArray.size()) {
						categoriesArray.add("Special");		//always add the special category into the array list on start up
					}
				}
				
				//initializing the JPanels on start of program
//				userLoginPane = new UserLogin().contentPane;
//				userLoginPane.setVisible(false);		//set to false so we can return to this page when logging out
//				
//				mainMenuPane = new MainMenu().contentPane;
//				mainMenuPane.setVisible(false);
//				
//				eventPageSetupPane = new EventPageSetup().contentPane;
//				eventPageSetupPane.setVisible(false);
//				
//				schedulePane = new Schedule().contentPane;
//				schedulePane.setVisible(false);
//				
//				resultsPane = new Results().contentPane;
//				resultsPane.setVisible(false);
//				
//				settingsPane = new Settings().contentPane;
//				settingsPane.setVisible(false);
				
//				helpPane = new Help().contentPane;
//				helpPane.setVisible(false);
			}
		});
	}

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
		return thisFont;
	}
	
	public static ArrayList<TeamObject> getTeamsArray() {
		return teamsArray;
	}

	public static void setTeamsArray(ArrayList<TeamObject> teams) {
		teamsArray = teams;
	}
	
	public static void setXPos(int pos) {
		xPos = pos;
	}
	
	public static void setYPos(int pos) {
		yPos = pos;
	}
	
	public static void setWidth(int w) {
		width = w;
	}
	
	public static void setHeight(int h) {
		height = h;
	}
	
	public static int getXPos() {
		return xPos;
	}
	
	public static int getYPos() {
		return yPos;
	}
	
	public static int getWindowWidth() {
		return width;
	}
	
	public static int getWindowHeight() {
		return height;
	}
	
	public static void setWindowPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public static void setStartDayTime(int num) {
		startDayTime = num;
	}
	
	public static int getStartDayTime() {
		return startDayTime;
	}
	
	public static void setTimeBetweenRaces(int num) {
		timeBetweenRaces = num;
	}
	
	public static int getTimeBetweenRaces() {
		return timeBetweenRaces;
	}
	
	public static void setNumOfLanes(int num) {
		numOfLanes = num;
	}
	
	public static int getNumOfLanes() {
		return numOfLanes;
	}
}









