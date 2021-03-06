package DBRF;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.lang.Integer;

public class Schedule extends JFrame {

//	public static JFrame frame;
	public JPanel contentPane;
	
	//booleans for generating the races only once
//	static boolean timeTrialRacesEh = false;
//	static boolean semiFinalRacesEh = false;
//	static boolean finalRacesEh = false;
//	static boolean firstPassEh = true;		//used to generate the time trial races on startup of this frame
	
	static public JPanel panel1 = new JPanel();
	static public JPanel panel2 = new JPanel();
	static public JPanel panel3 = new JPanel();
	
	private JLabel lblSchedule = new JLabel("Schedule");
	private JScrollPane scrollPane = new JScrollPane();
	
	//initialize them first so they can be referenced in the mouseClicked method
	static JRadioButton timeTrialsRadioButton = new JRadioButton("Time-Trials");
	static JRadioButton semiFinalsRadioButton = new JRadioButton("Semi-Finals");
	static JRadioButton finalsRadioButton = new JRadioButton("Finals");
	static JButton btnGenerate = new JButton("Regenerate");
	
	
	/**
	 * Launch the application.
	 * Used for testing the UI.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Schedule frame = new Schedule();
					frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * Inputs - None.
	 * Outputs 	- Creates a new JFrame and adds the necessary UI components.
	 * 			- Calls the race generation scripts.
	 */
	public Schedule() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(FestivalObject.getXPos(), FestivalObject.getYPos(), FestivalObject.getWindowWidth(), FestivalObject.getWindowHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		FestivalObject.setFestName("Lethbridge DBR Festival 2016");		//TODO - just for testing
//		
//		//setting some stuff for testing - all teams from Kelowna Race Grid 2015
//		FestivalObject.teamsArray.add(new TeamObject("KDBC High Frequency", "Womens"));
//		FestivalObject.teamsArray.add(new TeamObject("ODBRC Rogue Dragons", "Womens"));
//		FestivalObject.teamsArray.add(new TeamObject("KDBC Sonar Dragons", "Womens"));
//		FestivalObject.teamsArray.add(new TeamObject("A'Breast of Bridge", "Special"));
//		FestivalObject.teamsArray.add(new TeamObject("KDBC Knotty Pacemakers", "Mixed"));
//		FestivalObject.teamsArray.add(new TeamObject("Bust n Loose", "Special"));
//		FestivalObject.teamsArray.add(new TeamObject("KDBC Dragonflies", "Mixed"));
//		FestivalObject.teamsArray.add(new TeamObject("KDBC Stroke of Luck", "Mixed"));
//		FestivalObject.teamsArray.add(new TeamObject("Women on Fire", "Mixed"));
//		FestivalObject.teamsArray.add(new TeamObject("KDBC Dragon in the Drink", "Mixed"));
//		FestivalObject.teamsArray.add(new TeamObject("KDBC Valley Vixens", "Mixed"));
//		FestivalObject.teamsArray.add(new TeamObject("KDBC Flowriders", "Mixed"));
//		FestivalObject.teamsArray.add(new TeamObject("ODBRC DragonFire", "Mixed"));
//		FestivalObject.teamsArray.add(new TeamObject("Fire On Water", "Mixed"));
//		FestivalObject.teamsArray.add(new TeamObject("Despirit Housewives", "Mixed"));
//		
//		//testing for the breaks
//		ArrayList<Integer> q = new ArrayList<Integer>();
//		q.add(1030);
//		q.add(1100);		
//		
//		ArrayList<Integer> w = new ArrayList<Integer>();
//		w.add(1200);
//		w.add(1300);		
//		
//		ArrayList<Integer> e = new ArrayList<Integer>();
//		e.add(1530);
//		e.add(1600);		
//		
//		//adding the breaks to the main breaks array
//		FestivalObject.breaksArray.add(q);
//		FestivalObject.breaksArray.add(w);
//		FestivalObject.breaksArray.add(e);
//		
//		FestivalObject.categoriesArray.add("Mixed");
//		FestivalObject.categoriesArray.add("Womens");
//		FestivalObject.categoriesArray.add("Special");
//		FestivalObject.categoriesArray.add("Mens");
//		
//		for(int i = 0; i < FestivalObject.teamsArray.size(); i++) {
//			FestivalObject.teamsArray.get(i).setTeamID(i);
//		}
		
		
		lblSchedule.setFont(FestivalObject.getFont());
		lblSchedule.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchedule.setBounds(10, 26, 934, 14);
		contentPane.add(lblSchedule);
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 75, 924, 388);
		contentPane.add(scrollPane);
		
		//set the 3 different panels
		panel1.setVisible(true);
		scrollPane.setViewportView(panel1);
		panel1.setLayout(new MigLayout("", "[70px][300px][50px][150px][10px][120px][140px]", "[25px:25px:25px]"));
		
		panel2.setVisible(false);
		scrollPane.setViewportView(panel2);
		panel2.setLayout(new MigLayout("", "[70px][300px][50px][150px][10px][120px][140px]", "[25px:25px:25px]"));
		
		panel3.setVisible(false);
		scrollPane.setViewportView(panel3);
		panel3.setLayout(new MigLayout("", "[70px][300px][50px][150px][10px][120px][140px]", "[25px:25px:25px]"));
		
		timeTrialsRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//need to set the other panels to invisible
				scrollPane.setViewportView(panel1);		//set the view of the scrollPane
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
				timeTrialsRadioButton.setSelected(true);
				semiFinalsRadioButton.setSelected(false);
				finalsRadioButton.setSelected(false);
				btnGenerate.setEnabled(true);
			}
		});
		timeTrialsRadioButton.setFont(FestivalObject.getFont());
		timeTrialsRadioButton.setEnabled(true);
		timeTrialsRadioButton.setSelected(true);
		timeTrialsRadioButton.setBounds(44, 50, 134, 23);
		timeTrialsRadioButton.setFocusable(false);
		contentPane.add(timeTrialsRadioButton);
		
		semiFinalsRadioButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(semiFinalsRadioButton.isEnabled() == true) {
					//need to set the other panels to invisible and then 
					scrollPane.setViewportView(panel2);		//set the view of the scrollPane
					panel1.setVisible(false);
					panel2.setVisible(true);
					panel3.setVisible(false);
					timeTrialsRadioButton.setSelected(false);
					semiFinalsRadioButton.setSelected(true);
					finalsRadioButton.setSelected(false);
					btnGenerate.setEnabled(false);
				}
			}
		});
		semiFinalsRadioButton.setFont(FestivalObject.getFont());
		semiFinalsRadioButton.setEnabled(false);
		semiFinalsRadioButton.setSelected(false);
		semiFinalsRadioButton.setBounds(180, 50, 134, 23);
		semiFinalsRadioButton.setFocusable(false);
		contentPane.add(semiFinalsRadioButton);
		
		finalsRadioButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(finalsRadioButton.isEnabled() == true) {
					//need to set the other panels to invisible and then 
					scrollPane.setViewportView(panel3);		//set the view of the scrollPane
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(true);
					timeTrialsRadioButton.setSelected(false);
					semiFinalsRadioButton.setSelected(false);
					finalsRadioButton.setSelected(true);
					btnGenerate.setEnabled(false);
				}
			}
		});
		finalsRadioButton.setFont(FestivalObject.getFont());
		finalsRadioButton.setEnabled(false);
		finalsRadioButton.setSelected(false);
		finalsRadioButton.setBounds(316, 50, 134, 23);
		finalsRadioButton.setFocusable(false);
		contentPane.add(finalsRadioButton);
		
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//pop up that tells the user if they like to regenerate the time trial races. all input info will be lost.
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, 
						"Are you sure you like to regenerate the time trial races? \nAll race times entered will be lost.", "Regenerate", dialogButton);
				//set things to regenerate the time trial races is yes was selected
				if(dialogResult == 0) {
					FestivalObject.racesArray = new ArrayList<RaceObject>();	//reset the ArrayList of races
					
					//remove all compenents from the panels
					panel1.removeAll();
					panel2.removeAll();
					panel3.removeAll();
					
					//reset the booleans
					FestivalObject.generatedTimeTrialRacesEh = false;
					FestivalObject.generatedSemiFinalRacesEh = false;
					FestivalObject.generatedFinalRacesEh = false;
					
					panel1.setVisible(true);
					scrollPane.setViewportView(panel1);
					panel1.setLayout(new MigLayout("", "[70px][300px][50px][150px][10px][120px][140px]", "[25px:25px:25px]"));
					
					semiFinalsRadioButton.setEnabled(false);	//disable semi finals radio button
					finalsRadioButton.setEnabled(false);		//disable finals radio button
					
					TimeTrialRaceGeneration.generateTimeTrailRaces(panel1);
				} 
				else {
					//do nothing if no was selected
				}
			}
		});
		btnGenerate.setFont(FestivalObject.getFont());
		btnGenerate.setFocusable(false);
		btnGenerate.setBounds(814, 51, 120, 20);
		contentPane.add(btnGenerate);
		
		scrollPane.setViewportView(panel1);		//set the view to the panel1 inside the scroll box
		TimeTrialRaceGeneration.generateTimeTrailRaces(panel1);
		
//		JButton btnTest = new JButton("test");
//		btnTest.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent arg0) {
//				for(int i = 0; i < FestivalObject.breaksArray.size(); i++) {
////					System.out.println(FestivalObject.breaksArray.get(i).get(0) + " - " + FestivalObject.breaksArray.get(i).get(1));
//					System.out.println(FestivalObject.teamsArray.size());
//				}
//			}
//		});
//		btnTest.setBounds(698, 51, 89, 23);
//		contentPane.add(btnTest);
		
		//main menu stuff is first set here since it is always where you start
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//the menu items
		JMenuItem mntmHome = new JMenuItem("Home");
		mntmHome.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FestivalObject.setWindowPosition(getX(), getY());	//store the window position in FestivalObject
				new MainMenu().setVisible(true);
				dispose();
			}
		});
		mntmHome.setFont(FestivalObject.getFont());
		mntmHome.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmHome.setHorizontalAlignment(SwingConstants.CENTER);
		mntmHome.setPreferredSize(new Dimension(100, 25));
		mntmHome.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(128, 128, 128), null, null));
		mntmHome.setBackground(Color.WHITE);
		menuBar.add(mntmHome);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Save the festival?", "Festival Save", dialogButton);
				if(dialogResult == 0) { 
					SaveAndLoad.saveXML();
				}
			}
		});
		mntmSave.setFont(FestivalObject.getFont());
		mntmSave.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmSave.setHorizontalAlignment(SwingConstants.CENTER);
		mntmSave.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(128, 128, 128), null, null));
		mntmSave.setPreferredSize(new Dimension(100, 25));
		mntmSave.setBackground(Color.WHITE);
		menuBar.add(mntmSave);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FestivalObject.setWindowPosition(getX(), getY());	//store the window position in FestivalObject
				new Settings().setVisible(true);
				dispose();
			}
		});
		mntmSettings.setFont(FestivalObject.getFont());
		mntmSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmSettings.setHorizontalAlignment(SwingConstants.CENTER);
		mntmSettings.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(128, 128, 128), null, null));
		mntmSettings.setPreferredSize(new Dimension(100, 25));
		mntmSettings.setBackground(Color.WHITE);
		menuBar.add(mntmSettings);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FestivalObject.setWindowPosition(getX(), getY());	//store the window position in FestivalObject
				//TODO - add help stuff
			}
		});
		mntmHelp.setFont(FestivalObject.getFont());
		mntmHelp.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmHelp.setHorizontalAlignment(SwingConstants.CENTER);
		mntmHelp.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(128, 128, 128), null, null));
		mntmHelp.setPreferredSize(new Dimension(100, 25));
		mntmHelp.setBackground(Color.WHITE);
		menuBar.add(mntmHelp);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("");
		mntmNewMenuItem.setBorderPainted(true);
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setOpaque(true);
		mntmNewMenuItem.setPreferredSize(new Dimension(600, 25));
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FestivalObject.setWindowPosition(getX(), getY());
				new UserLogin().setVisible(true);
				dispose();
			}
		});
		mntmLogout.setFont(FestivalObject.getFont());
		mntmLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmLogout.setHorizontalAlignment(SwingConstants.CENTER);
		mntmLogout.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(128, 128, 128), null, null));
		mntmLogout.setPreferredSize(new Dimension(100, 25));
		mntmLogout.setBackground(Color.WHITE);
		menuBar.add(mntmLogout);
	}
}







