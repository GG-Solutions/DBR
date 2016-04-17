package DBRF;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class Results extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Results frame = new Results();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Results() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(FestivalObject.getXPos(), FestivalObject.getYPos(), FestivalObject.getWindowWidth(),
				FestivalObject.getWindowHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton rdbtnTimeTrials = new JRadioButton("Time Trials");
		buttonGroup.add(rdbtnTimeTrials);
		rdbtnTimeTrials.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		rdbtnTimeTrials.setBounds(87, 51, 109, 23);
		contentPane.add(rdbtnTimeTrials);

		JRadioButton rdbtnSemiFinals = new JRadioButton("Semi Finals");
		buttonGroup.add(rdbtnSemiFinals);
		rdbtnSemiFinals.setBounds(230, 51, 109, 23);
		contentPane.add(rdbtnSemiFinals);

		JRadioButton rdbtnFinals = new JRadioButton("Finals");
		buttonGroup.add(rdbtnFinals);
		rdbtnFinals.setBounds(359, 51, 109, 23);
		contentPane.add(rdbtnFinals);

		JRadioButton rdbtnFirstRace = new JRadioButton("First Race");
		buttonGroup_1.add(rdbtnFirstRace);
		rdbtnFirstRace.setBounds(87, 80, 109, 23);
		contentPane.add(rdbtnFirstRace);

		JRadioButton rdbtnSecondRace = new JRadioButton("Second Race");
		buttonGroup_1.add(rdbtnSecondRace);
		rdbtnSecondRace.setBounds(230, 77, 109, 23);
		contentPane.add(rdbtnSecondRace);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(87, 110, 742, 338);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		// This will Display all the results
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				if (rdbtnTimeTrials.isSelected()) {
					for (int i = 0; i < FestivalObject.teamsArray.size(); i++) {
						textArea.append("Team Name: ");
						textArea.append(FestivalObject.getTeamsArray().get(i).getTeamName());
						textArea.append(" ");
						textArea.append("|");
						textArea.append(" ");
						textArea.append("Team Category: ");
						textArea.append(FestivalObject.getTeamsArray().get(i).getCategory());
						if (rdbtnFirstRace.isSelected()) {
							textArea.append(" ");
							textArea.append("|");
							textArea.append(" ");
							textArea.append("Race Time: ");
							int temp1 = FestivalObject.getTeamsArray().get(i).getFirstRaceTime();
							String temp = Integer.toString(temp1);
							String temp3 = java.util.Arrays.toString(temp.split("(?<=\\G..)"));
							textArea.append(temp3);
							textArea.append("\n");
							textArea.append("\n");
						} else if (rdbtnSecondRace.isSelected()) {
							textArea.append(" ");
							textArea.append("|");
							textArea.append(" ");
							textArea.append("Race Time: ");
							int temp1 = FestivalObject.getTeamsArray().get(i).getSecondRaceTime();
							String temp = Integer.toString(temp1);
							String temp3 = java.util.Arrays.toString(temp.split("(?<=\\G..)"));
							textArea.append(temp3);
							textArea.append("\n");
							textArea.append("\n");
						}
					}
				} else if (rdbtnSemiFinals.isSelected()) {
					for (int i = 0; i < FestivalObject.teamsArray.size(); i++) {
						textArea.append("Team Name: ");
						textArea.append(FestivalObject.getTeamsArray().get(i).getTeamName());
						textArea.append(" ");
						textArea.append("|");
						textArea.append(" ");
						textArea.append("Team Category: ");
						textArea.append(FestivalObject.getTeamsArray().get(i).getCategory());
						textArea.append(" ");
						textArea.append("|");
						textArea.append(" ");
						textArea.append("Race Time: ");
						int temp1 = FestivalObject.getTeamsArray().get(i).getSemiFinalRaceTime();
						String temp = Integer.toString(temp1);
						String temp3 = java.util.Arrays.toString(temp.split("(?<=\\G..)"));
						textArea.append(temp3);
						textArea.append("\n");
						textArea.append("\n");
					}
				} 
				if (rdbtnFinals.isSelected()) {
					for (int i = 0; i < FestivalObject.teamsArray.size(); i++) {
						textArea.append("Team Name: ");
						textArea.append(FestivalObject.getTeamsArray().get(i).getTeamName());
						textArea.append(" ");
						textArea.append("|");
						textArea.append(" ");
						textArea.append("Team Category: ");
						textArea.append(FestivalObject.getTeamsArray().get(i).getCategory());
						textArea.append(" ");
						textArea.append("|");
						textArea.append(" ");
						textArea.append("Team Place: ");
						int temp4 = FestivalObject.getTeamsArray().get(i).getPlace();
						String temp5 = Integer.toString(temp4);
						textArea.append(temp5);
						textArea.append(" ");
						textArea.append("|");
						textArea.append(" ");
						textArea.append("Race Time: ");
						int temp1 = FestivalObject.getTeamsArray().get(i).getFinalRaceTime();
						String temp = Integer.toString(temp1);
						String temp3 = java.util.Arrays.toString(temp.split("(?<=\\G..)"));
						textArea.append(temp3);
						textArea.append("\n");
						textArea.append("\n");
					}
				}
		
			}
		});
		btnDisplay.setBounds(473, 74, 89, 23);
		contentPane.add(btnDisplay);

		JButton btnPrint = new JButton("Print");
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JFrame parentFrame = new JFrame();

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");

				int userSelection = fileChooser.showSaveDialog(parentFrame);

				if (userSelection != JFileChooser.APPROVE_OPTION) {
					return;
				}

				File fileName = new File(fileChooser.getSelectedFile() + ".txt");
				BufferedWriter outFile = null;
				try {
					outFile = new BufferedWriter(new FileWriter(fileName));

					textArea.write(outFile); // *** here: ***

				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					if (outFile != null) {
						try {
							outFile.close();
						} catch (IOException e1) {

						}
					}
				}
			}

		});
		btnPrint.setBounds(855, 425, 89, 23);
		contentPane.add(btnPrint);

		// main menu stuff is first set here since it is always where you start
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// the menu items
		JMenuItem mntmHome = new JMenuItem("Home");
		mntmHome.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				FestivalObject.setWindowPosition(getX(), getY()); // store the
																	// window
																	// position
																	// in
																	// FestivalObject
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
				SaveAndLoad.saveXML();
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
				FestivalObject.setWindowPosition(getX(), getY()); // store the
																	// window
																	// position
																	// in
																	// FestivalObject
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
				FestivalObject.setWindowPosition(getX(), getY()); // store the
																	// window
																	// position
																	// in
																	// FestivalObject
				// TODO - add help stuff
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
				FestivalObject.setWindowPosition(getX(), getY()); // store the
																	// window
																	// position
																	// in
																	// FestivalObject
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
