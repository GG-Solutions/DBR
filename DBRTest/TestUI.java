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
	int numberOfLanes = 3;
	
	ArrayList<TeamObject> teamsArray;
	ArrayList<RaceObject> racesArray;
	ArrayList<String> categorysArray;
	
	ArrayList<ArrayList<Integer>> breaksArray;
	
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
		frame.setBounds(100, 100, 887, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		teamsArray = new ArrayList<TeamObject>();
		racesArray = new ArrayList<RaceObject>();
		breaksArray = new ArrayList<ArrayList<Integer>>();
		
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
		scrollPaneTimeTrials.setBounds(44, 75, 674, 390);
		frame.getContentPane().add(scrollPaneTimeTrials);
		
		JButton btnGenerate = new JButton("generate");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//use this for testing the time generation
				//timePane.setText("generated times");
				TimeTrialRaceGeneration times = new TimeTrialRaceGeneration();
				System.out.println(teamsArray.size()+"\n");
				times.generateTimeTrailRaces(timePane, numberOfLanes, breaksArray, racesArray, teamsArray, scrollPaneTimeTrials);
				
				//testing the addition of races by printing them out to the one box
				for(int i = 0; i < racesArray.size(); i++) {
					timePane.setText(timePane.getText() + racesArray.get(i).getRaceNumber() + " " + racesArray.get(i).getRaceTime() + "\n");
				}
				
			}
		});
		btnGenerate.setBounds(761, 50, 89, 23);
		frame.getContentPane().add(btnGenerate);
		
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
