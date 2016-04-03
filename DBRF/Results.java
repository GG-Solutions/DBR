package DBRF;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.ScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Results extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnTimeTrials = new JRadioButton("Time Trials");
		rdbtnTimeTrials.setBounds(77, 74, 109, 23);
		contentPane.add(rdbtnTimeTrials);
		
		JRadioButton rdbtnSemiFinals = new JRadioButton("Semi Finals");
		rdbtnSemiFinals.setBounds(229, 74, 109, 23);
		contentPane.add(rdbtnSemiFinals);
		
		JRadioButton rdbtnFinals = new JRadioButton("Finals");
		rdbtnFinals.setBounds(357, 74, 109, 23);
		contentPane.add(rdbtnFinals);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(87, 110, 742, 338);
		contentPane.add(textArea);
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < FestivalObject.teamsArray.size(); i++){
					textArea.append(FestivalObject.getTeamsArray().get(i).getTeamName());
					textArea.append(FestivalObject.getTeamsArray().get(i).getCategory());
					textArea.append(FestivalObject.getTeamsArray().get(i).getPlace());
					int temp1 = FestivalObject.getTeamsArray().get(i).getFirstRaceTime();
					String temp = Integer.toString(temp1);
					String temp3 = java.util.Arrays.toString(temp.split("(?<=\\G..)"));
					textArea.append(temp3);
					textArea.append("\n");
				}
				
			}
		});
		btnDisplay.setBounds(473, 74, 89, 23);
		contentPane.add(btnDisplay);
	}
}
