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
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFormattedTextField;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;
import javax.swing.event.ChangeEvent;
import java.lang.Integer;

public class TestUI {

	public static JFrame frame;
	
	//testing global variables here?
	int numberOfLanes = 6;
	
	ArrayList<TeamObject> teamsArray;
	ArrayList<RaceObject> racesArray;
	ArrayList<String> categorysArray;
	
//	int[][] breaksArray = {{1000, 1030},{1200, 1300},{1530, 1600}};		//first column has the starting times for the breaks and the second column has the endding time
	ArrayList<ArrayList<Integer>> breaksArray = new ArrayList<ArrayList<Integer>>();
//	ArrayList<Integer> breaksArray;
	
//	private JTextField timeField;	//??????

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
		frame.setBounds(100, 100, 887, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		teamsArray = new ArrayList<TeamObject>();
		racesArray = new ArrayList<RaceObject>();
		
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
		
		
		//separate the teams by similar categories
		for(int i = 0; i < teamsArray.size();i++) {
			//make it a switch statement for each type to check?
				//cant add more switch statements if a new category is added
				//use the categories array that was input
			if(teamsArray.get(i).getCategory() == "something") {
				//add it to the array with matching category
			}
			teamsArray.get(i);
			
			//problem is i dont know how many cases there are
//			switch(categorysArray.get(i)) {
//				case 1: categorysArray.get(0). = teamsArray.get(i).getCategory();
//					break;
//			}
			
			
			//need another loop inside this one to add the differnt categories to different arrays?
				//loop through the new arrays being made
					//see if they match
					//if it doesn't, add it to the end of the array
		}
		
		
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
//		System.out.println(breaksArray.get(0));
//		int thisOne = breaksArray.get(0).get(1);
//		System.out.println(thisOne);
//		breaksArray.remove(0);
//		System.out.println(breaksArray);
		
//		breaksArray = new ArrayList<Integer>();
//		breaksArray.add(1030);
//		breaksArray.add(1200);
//		breaksArray.add(1530);
		
		
		JLabel lblSchedule = new JLabel("Schedule");
		lblSchedule.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSchedule.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchedule.setBounds(10, 26, 744, 14);
		frame.getContentPane().add(lblSchedule);
		
		JTextPane breaksPane = new JTextPane();
		breaksPane.setEditable(false);
		breaksPane.setBounds(742, 282, 119, 151);
		frame.getContentPane().add(breaksPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(742, 84, 119, 151);
		frame.getContentPane().add(scrollPane);
		
		JTextPane timePane = new JTextPane();
		scrollPane.setViewportView(timePane);
		timePane.setEditable(false);
		
		JButton showBreaks = new JButton("showBreaks");
		showBreaks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				breaksPane.setText("");	//reset the breaksPane text
//				TimeTrialRaceGeneration breaks = new TimeTrialRaceGeneration();
//				breaks.showBreaks(breaksPane, breaksArray);
				
				//print the breaks
				for(int i = 0; i < breaksArray.size(); i++) {
					breaksPane.setText(breaksPane.getText() + breaksArray.get(i).get(0) + " " + breaksArray.get(i).get(1) + "\n");
				}
			}
		});
		showBreaks.setBounds(761, 248, 89, 23);
		frame.getContentPane().add(showBreaks);
		
		JScrollPane scrollPaneTimeTrials = new JScrollPane();
		scrollPaneTimeTrials.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTimeTrials.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTimeTrials.setBounds(44, 75, 674, 340);
		frame.getContentPane().add(scrollPaneTimeTrials);
		
		JButton btnGenerate = new JButton("generate");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//use this for testing the time generation
				//timePane.setText("generated times");
				TimeTrialRaceGeneration times = new TimeTrialRaceGeneration();
				times.generateTimeTrailRaces(timePane, numberOfLanes, breaksArray, racesArray, teamsArray, scrollPaneTimeTrials);
				
				//testing the addition of races by printing them out to the one box
				for(int i = 0; i < racesArray.size(); i++) {
					timePane.setText(timePane.getText() + racesArray.get(i).getRaceNumber() + " " + racesArray.get(i).getRaceTime() + "\n");
				}
				
			}
		});
		btnGenerate.setBounds(761, 50, 89, 23);
		frame.getContentPane().add(btnGenerate);
		
//		MaskFormatter timeMask = null;
//		try {
//			timeMask = new MaskFormatter(" ##h:##m");
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		JFormattedTextField fTxtField = new JFormattedTextField(timeMask);
//		fTxtField.setBounds(602, 51, 64, 20);
//		frame.getContentPane().add(fTxtField);
		
//		//add a new panel to the UI
//		JPanel panel = new JPanel();
//		scrollPaneTimeTrials.setViewportView(panel);
//		panel.setLayout(new MigLayout("", "[555px][100px:n,right]", "[25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px]"));
//		
//		//add the race label "Race # _ at"
//		JLabel raceNumberLabel = new JLabel("Race # 1 at");
//		raceNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
//		panel.add(raceNumberLabel, "flowx,cell 0 0,aligny center");
//		
//		//the time field set to non-editable in the beginning
//		JTextField timeField = new JTextField();
//		timeField.setEditable(false);
//		timeField.setText("9:00");
//		panel.add(timeField, "cell 0 0");
//		timeField.setColumns(10);
//		
//		//edit button for the time field
//		JButton editButton = new JButton("edit");
//		editButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
//		editButton.setForeground(Color.BLUE);
//		panel.add(editButton, "cell 0 0");
//		
//		//place label
//		JLabel lblPlace = new JLabel("Place");
//		lblPlace.setHorizontalAlignment(SwingConstants.CENTER);
//		panel.add(lblPlace, "flowx,cell 0 1,growx,aligny center");
//		
//		//team name label
//		JLabel lblTeamName = new JLabel("Team Name");
//		panel.add(lblTeamName, "cell 0 1,growx,aligny center");
//		
//		//lane label
//		JLabel lblLane = new JLabel("Lane");
//		lblLane.setHorizontalAlignment(SwingConstants.CENTER);
//		panel.add(lblLane, "cell 0 1,growx,aligny center");
//		
//		//category label
//		JLabel lblCategory = new JLabel("Category");
//		panel.add(lblCategory, "cell 0 1,growx,aligny center");
//		
//		//flag label
//		JLabel lblFlag = new JLabel("*");
//		panel.add(lblFlag, "cell 0 1,aligny center");
//		
//		//time label
//		JLabel lblTime = new JLabel("Time");
//		panel.add(lblTime, "cell 0 1,growx,aligny center");
//		
//		JLabel lblNewLabel = new JLabel("3");
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		panel.add(lblNewLabel, "flowx,cell 0 2,growx,aligny center");
//		
//		JLabel lblMyTeamName = new JLabel("My Team Name");
//		panel.add(lblMyTeamName, "cell 0 2,growx,aligny center");
//		
//		JLabel label_1 = new JLabel("1");
//		label_1.setHorizontalAlignment(SwingConstants.CENTER);
//		panel.add(label_1, "cell 0 2,growx,aligny center");
//		
//		JLabel lblMixed = new JLabel("Mixed");
//		panel.add(lblMixed, "cell 0 2,growx,aligny center");
//		
//		JLabel label_3 = new JLabel("*");
//		panel.add(label_3, "cell 0 2,aligny center");
//		
//		JLabel label_2 = new JLabel("5:23");
//		panel.add(label_2, "cell 0 2,growx,aligny center");
//		
//		JButton btnNewButton = new JButton("Lock");
//		btnNewButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//			}
//		});
//		panel.add(btnNewButton, "cell 1 2,alignx center,aligny center");
//		
//		JLabel label_4 = new JLabel("2");
//		label_4.setHorizontalAlignment(SwingConstants.CENTER);
//		panel.add(label_4, "flowx,cell 0 3,growx,aligny center");
//		
//		JLabel lblThisOne = new JLabel("This One");
//		panel.add(lblThisOne, "cell 0 3,growx,aligny center");
//		
//		JLabel label_5 = new JLabel("2");
//		label_5.setHorizontalAlignment(SwingConstants.CENTER);
//		panel.add(label_5, "cell 0 3,growx,aligny center");
//		
//		JLabel lblMens = new JLabel("Mens");
//		panel.add(lblMens, "cell 0 3,growx,aligny center");
//		
//		JLabel label_6 = new JLabel("*");
//		panel.add(label_6, "cell 0 3,aligny center");
//		
//		JLabel label_7 = new JLabel("5:05");
//		panel.add(label_7, "cell 0 3,growx,aligny center");
//		
//		JButton btnNewButton_1 = new JButton("Print");
//		panel.add(btnNewButton_1, "cell 1 3,alignx center,aligny center");
//		
//		JLabel label_8 = new JLabel("1");
//		label_8.setHorizontalAlignment(SwingConstants.CENTER);
//		panel.add(label_8, "flowx,cell 0 4,growx,aligny center");
//		
//		JLabel lblWeRektYou = new JLabel("We Rekt You Guys");
//		panel.add(lblWeRektYou, "cell 0 4,growx,aligny center");
//		
//		JLabel label_9 = new JLabel("3");
//		label_9.setHorizontalAlignment(SwingConstants.CENTER);
//		panel.add(label_9, "cell 0 4,growx,aligny center");
//		
//		JLabel lblMens_1 = new JLabel("Mixed");
//		panel.add(lblMens_1, "cell 0 4,growx,aligny center");
//		
//		JLabel label_10 = new JLabel("*");
//		panel.add(label_10, "cell 0 4,aligny center");
//		
//		JLabel label_11 = new JLabel("4:51");
//		panel.add(label_11, "cell 0 4,growx,aligny center");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Time-Trials");
		rdbtnNewRadioButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnNewRadioButton.isSelected()) {
					//need to set the other panels to invisible and then 
//					panel.setVisible(true);
					//rdbtnSemifinals.setSelected(false);	//cant initialize it before it is declared :/
				}
			}
		});
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(44, 50, 83, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnSemifinals = new JRadioButton("Semi-Finals");
		rdbtnSemifinals.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnSemifinals.isSelected()) {
					//need to set the other panels to invisible and then 
//					panel.setVisible(false);
					rdbtnNewRadioButton.setSelected(false);
				}
			}
		});
		rdbtnSemifinals.setBounds(141, 50, 83, 23);
		frame.getContentPane().add(rdbtnSemifinals);
		
		JRadioButton rdbtnFinals = new JRadioButton("Finals");
		rdbtnFinals.setEnabled(false);
		rdbtnFinals.setBounds(243, 50, 83, 23);
		frame.getContentPane().add(rdbtnFinals);
		
		
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
