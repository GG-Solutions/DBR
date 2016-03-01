package DBRTest;

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
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestUI {

	public static JFrame frame;
	
	//global variables here?
	int numberOfLanes = 6;
	
	TeamObject[] teamsArray;
	
	int[][] breaksArray = {{1000, 1030},{1200, 1300},{1530, 1600}};		//first column has the starting times for the breaks and the second column has the endding time
	
	RaceObject[] racesArray;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestUI window = new TestUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//initailize the 10 teams
		teamsArray = new TeamObject[10];
//		teamsArray[0].setTeamName("Team1");
//		teamsArray[1].setTeamName("Team2");
//		teamsArray[2].setTeamName("Team3");
//		teamsArray[3].setTeamName("Team4");
//		teamsArray[4].setTeamName("Team5");
//		teamsArray[5].setTeamName("Team6");
//		teamsArray[6].setTeamName("Team7");
//		teamsArray[7].setTeamName("Team8");
//		teamsArray[8].setTeamName("Team9");
//		teamsArray[9].setTeamName("Team10");
		
		racesArray = new RaceObject[(teamsArray.length * 2)];		//double the length of teamsArray only for time-trial races
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Time-Trials");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(44, 50, 83, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnSemifinals = new JRadioButton("Semi-Finals");
		rdbtnSemifinals.setEnabled(false);
		rdbtnSemifinals.setBounds(141, 50, 83, 23);
		frame.getContentPane().add(rdbtnSemifinals);
		
		JRadioButton rdbtnFinals = new JRadioButton("Finals");
		rdbtnFinals.setEnabled(false);
		rdbtnFinals.setBounds(243, 50, 83, 23);
		frame.getContentPane().add(rdbtnFinals);
		
		JLabel lblSchedule = new JLabel("Schedule");
		lblSchedule.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSchedule.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchedule.setBounds(10, 26, 744, 14);
		frame.getContentPane().add(lblSchedule);
		
		JTextPane timePane = new JTextPane();
		timePane.setEditable(false);
		timePane.setBounds(69, 114, 119, 201);
		frame.getContentPane().add(timePane);
		
		JButton btnGenerate = new JButton("generate");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//use this for testing the time generation
				//timePane.setText("generated times");
				TimeTrialRaceGeneration times = new TimeTrialRaceGeneration();
				times.generateTimeTrailRaces(timePane, numberOfLanes, breaksArray, racesArray);
				
				
			}
		});
		btnGenerate.setBounds(69, 80, 89, 23);
		frame.getContentPane().add(btnGenerate);
		
		JTextPane breaksPane = new JTextPane();
		breaksPane.setEditable(false);
		breaksPane.setBounds(267, 114, 119, 201);
		frame.getContentPane().add(breaksPane);
		
		JButton showBreaks = new JButton("showBreaks");
		showBreaks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				breaksPane.setText("");	//reset the breaksPane text
				TimeTrialRaceGeneration breaks = new TimeTrialRaceGeneration();
				breaks.showBreaks(breaksPane, breaksArray);
			}
		});
		showBreaks.setBounds(267, 80, 89, 23);
		frame.getContentPane().add(showBreaks);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenuItem mntmHome = new JMenuItem("Home");
		mntmHome.setBackground(Color.WHITE);
		menuBar.add(mntmHome);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Save");
		mntmNewMenuItem.setBackground(Color.WHITE);
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.setBackground(Color.WHITE);
		menuBar.add(mntmSettings);
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.setBackground(Color.WHITE);
		menuBar.add(mntmPrint);
		
		JMenuItem mntmBackuprestore = new JMenuItem("BackUp/Restore");
		mntmBackuprestore.setBackground(Color.WHITE);
		menuBar.add(mntmBackuprestore);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setBackground(Color.WHITE);
		menuBar.add(mntmHelp);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setHorizontalAlignment(SwingConstants.TRAILING);
		mntmLogout.setBackground(Color.WHITE);
		menuBar.add(mntmLogout);
	}
}
