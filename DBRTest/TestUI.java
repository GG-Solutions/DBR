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
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TestUI {

	public static JFrame frame;
	
	//global variables here?
	int numberOfLanes = 6;
	
	TeamObject[] teamsArray;
	
	int[][] breaksArray = {{1000, 1030},{1200, 1300},{1530, 1600}};		//first column has the starting times for the breaks and the second column has the endding time
	
	RaceObject[] racesArray;
	private JTextField timeField;

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
		timePane.setBounds(466, 50, 119, 151);
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
		btnGenerate.setBounds(477, 24, 89, 23);
		frame.getContentPane().add(btnGenerate);
		
		JTextPane breaksPane = new JTextPane();
		breaksPane.setEditable(false);
		breaksPane.setBounds(616, 50, 119, 151);
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
		showBreaks.setBounds(629, 24, 89, 23);
		frame.getContentPane().add(showBreaks);
		
		JScrollPane scrollPaneTimeTrials = new JScrollPane();
		scrollPaneTimeTrials.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTimeTrials.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTimeTrials.setBounds(44, 212, 674, 203);
		frame.getContentPane().add(scrollPaneTimeTrials);
		
		JPanel panel = new JPanel();
		scrollPaneTimeTrials.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{655, 0};
		gbl_panel.rowHeights = new int[]{62, 62, 62, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel headerLabel = new JLabel("Race # 1 at");
		headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_headerLabel = new GridBagConstraints();
		gbc_headerLabel.fill = GridBagConstraints.BOTH;
		gbc_headerLabel.insets = new Insets(0, 0, 5, 0);
		gbc_headerLabel.gridx = 0;
		gbc_headerLabel.gridy = 0;
		panel.add(headerLabel, gbc_headerLabel);
		
		timeField = new JTextField();
		timeField.setEditable(false);
		timeField.setText("9:00");
		GridBagConstraints gbc_timeField = new GridBagConstraints();
		gbc_timeField.fill = GridBagConstraints.BOTH;
		gbc_timeField.insets = new Insets(0, 0, 5, 0);
		gbc_timeField.gridx = 0;
		gbc_timeField.gridy = 1;
		panel.add(timeField, gbc_timeField);
		timeField.setColumns(10);
		
		JButton editButton = new JButton("edit");
		editButton.setForeground(Color.BLUE);
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.fill = GridBagConstraints.BOTH;
		gbc_editButton.gridx = 0;
		gbc_editButton.gridy = 2;
		panel.add(editButton, gbc_editButton);
		
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
