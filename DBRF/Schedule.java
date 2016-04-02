package DBRF;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;
import javax.swing.event.ChangeEvent;
import java.lang.Integer;
import java.text.ParseException;

import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JFormattedTextField;

public class Schedule {

	public static JFrame frame;
	
	//booleans for generating the races only once
	static boolean timeTrialRacesEh = false;
	static boolean semiFinalRacesEh = false;
	static boolean finalRacesEh = false;

	/**
	 * Launch the application.
	 * Used for testing the UI.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Schedule window = new Schedule();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * Inputs - None.
	 * Outputs - Goes through the initialize method.
	 */
	public Schedule() {
		initialize();
	}

	/**
	 * Initializes the contents of the frame.
	 * Inputs - None.
	 * Outputs 	- Creates a new JFrame and adds the necessary UI components.
	 * 			- Calls the race generation scripts.
	 */
	public static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 960, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		//setting some stuff for testing - all teams from Kelowna Race Grid 2015
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
		
		
		JLabel lblSchedule = new JLabel("Schedule");
		lblSchedule.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSchedule.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchedule.setBounds(10, 26, 924, 14);
		frame.getContentPane().add(lblSchedule);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 75, 924, 415);
		frame.getContentPane().add(scrollPane);
		
		//add the 3 differnt panels
		JPanel panel1 = new JPanel();
//		panel1.setVisible(true);
//		scrollPane.setViewportView(panel1);
		panel1.setLayout(new MigLayout("", "[70px][300px][50px][150px][10px][120px][140px]", "[25px:25px:25px]"));
		
		JPanel panel2 = new JPanel();
//		panel2.setVisible(false);
//		scrollPane.setViewportView(panel2);
		panel2.setLayout(new MigLayout("", "[70px][300px][50px][150px][10px][120px][140px]", "[25px:25px:25px]"));
		
		JPanel panel3 = new JPanel();
//		panel3.setVisible(false);
//		scrollPane.setViewportView(panel3);
		panel3.setLayout(new MigLayout("", "[70px][300px][50px][150px][10px][120px][140px]", "[25px:25px:25px]"));
		
		//initialize them first so they can be referenced in the stateChanged method
		JRadioButton rdbtnTimeTrials = new JRadioButton("Time-Trials");
//		rdbtnTimeTrials.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				
//			}
//		});
		JRadioButton rdbtnSemiFinals = new JRadioButton("Semi-Finals");
		JRadioButton rdbtnFinals = new JRadioButton("Finals");
		
		rdbtnTimeTrials.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//need to set the other panels to invisible
				scrollPane.setViewportView(panel1);		//set the view of the scrollPane
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
				rdbtnTimeTrials.setSelected(true);
				rdbtnSemiFinals.setSelected(false);	//cant initialize it before it is declared :/
				rdbtnFinals.setSelected(false);
			}
		});
		rdbtnTimeTrials.setEnabled(true);
		rdbtnTimeTrials.setSelected(true);
		rdbtnTimeTrials.setBounds(44, 50, 134, 23);
		frame.getContentPane().add(rdbtnTimeTrials);
		
		rdbtnSemiFinals.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//need to set the other panels to invisible and then 
				scrollPane.setViewportView(panel2);		//set the view of the scrollPane
				panel1.setVisible(false);
				panel2.setVisible(true);
				panel3.setVisible(false);
				rdbtnTimeTrials.setSelected(false);
				rdbtnSemiFinals.setSelected(true);
				rdbtnFinals.setSelected(false);
			}
		});
		rdbtnSemiFinals.setEnabled(true);
		rdbtnSemiFinals.setSelected(false);
		rdbtnSemiFinals.setBounds(180, 50, 134, 23);
		frame.getContentPane().add(rdbtnSemiFinals);
		
		rdbtnFinals.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//need to set the other panels to invisible and then 
				scrollPane.setViewportView(panel3);		//set the view of the scrollPane
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(true);
				rdbtnTimeTrials.setSelected(false);
				rdbtnSemiFinals.setSelected(false);
				rdbtnFinals.setSelected(true);
			}
		});
		rdbtnFinals.setEnabled(true);
		rdbtnFinals.setSelected(false);
		rdbtnFinals.setBounds(316, 50, 134, 23);
		frame.getContentPane().add(rdbtnFinals);
		
		JButton btnGenerate = new JButton("generate");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnTimeTrials.isSelected()) {
					scrollPane.setViewportView(panel1);		//set the view of the scrollPane
					//protection from generating twice
					if(timeTrialRacesEh == false) {
						TimeTrialRaceGeneration.generateTimeTrailRaces(panel1);	//call generateTimeTrialRaces
						timeTrialRacesEh = true;
					}
				}
				if(rdbtnSemiFinals.isSelected()) {
					//generate the semi-final races
					scrollPane.setViewportView(panel2);		//set the view of the scrollPane
					//protection from generating twice
					if(semiFinalRacesEh == false) {
						SemiFinalRaceGeneration.generateSemiFinalRaces(panel2);
						semiFinalRacesEh = true;
					}
				}
				if(rdbtnFinals.isSelected()) {
					//generate the final races
					scrollPane.setViewportView(panel3);		//set the view of the scrollPane
					//protection from generating twice
					if(finalRacesEh == false) {
						FinalRaceGeneration.generateFinalRaces(panel3);
						finalRacesEh = true;
					}
				}
//				System.out.println(teamsArray.size()+"\n");
			}
		});
		btnGenerate.setFocusable(false);
		btnGenerate.setBounds(834, 51, 100, 20);
		frame.getContentPane().add(btnGenerate);
		
		JButton btnSave = new JButton("save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					SaveAndLoad.saveXML();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
//				for(int i = 0; i < FestivalObject.teamsArray.size(); i++) {
//					System.out.println(FestivalObject.teamsArray.get(i).getFirstRaceTime() 
//							+ " - " + FestivalObject.teamsArray.get(i).getSecondRaceTime()
//							+ " - " + FestivalObject.teamsArray.get(i).getSemiFinalRaceTime()
//							+ " - " + FestivalObject.teamsArray.get(i).getFinalRaceTime());
//				}
			}
		});
		btnSave.setFocusable(false);
		btnSave.setBounds(709, 51, 100, 20);
		frame.getContentPane().add(btnSave);
		
		//main menu objects will be added later
//		JMenuBar menuBar = new JMenuBar();
//		frame.setJMenuBar(menuBar);
//		
//		JMenuItem mntmHome = new JMenuItem("Home");
//		mntmHome.setBackground(Color.WHITE);
//		menuBar.add(mntmHome);
//		
//		JMenuItem mntmNewMenuItem = new JMenuItem("Save");
//		mntmNewMenuItem.setBackground(Color.WHITE);
//		menuBar.add(mntmNewMenuItem);
//		
//		JMenuItem mntmSettings = new JMenuItem("Settings");
//		mntmSettings.setBackground(Color.WHITE);
//		menuBar.add(mntmSettings);
//		
//		JMenuItem mntmPrint = new JMenuItem("Print");
//		mntmPrint.setBackground(Color.WHITE);
//		menuBar.add(mntmPrint);
//		
//		JMenuItem mntmBackuprestore = new JMenuItem("BackUp/Restore");
//		mntmBackuprestore.setBackground(Color.WHITE);
//		menuBar.add(mntmBackuprestore);
//		
//		JMenuItem mntmHelp = new JMenuItem("Help");
//		mntmHelp.setBackground(Color.WHITE);
//		menuBar.add(mntmHelp);
//		
//		JMenuItem mntmLogout = new JMenuItem("Logout");
//		mntmLogout.setHorizontalAlignment(SwingConstants.TRAILING);
//		mntmLogout.setBackground(Color.WHITE);
//		menuBar.add(mntmLogout);
	}
	
	//i'm not sure if i need this here
	public static void changeRaceTimes(int changedTime, int index) {
		for(int i = index; i < FestivalObject.racesArray.size(); i++) {
			FestivalObject.racesArray.get(i).setRaceTime(changedTime + FestivalObject.timeBetweenRaces);
		}
	}
}







