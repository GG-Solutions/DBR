package DBRF;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

public class TeamObject implements MouseListener
{
	private int teamID = -1;
	private String teamName = "";
	private String category = "";
	private int place = -1;		//set to -1 as default? global place that the team is in
	
	//move the flags to the race object instead?
	private char firstRaceTimeFlag = '*';
	private int firstRaceTime = -1; 
	private char secondRaceTimeFlag = '*';
	private int secondRaceTime = -1; 
	private char semiFinalRaceTimeFlag = '*';
	private int semiFinalRaceTime = -1;
	private char finalRaceTimeFlag = '*';
	private int finalRaceTime = -1; 		//not every team will have a final race time?
	private int averagedRaceTime = -1; 
	
	private JFormattedTextField timeInputField;
	
	private JButton btnLockButton = new JButton("Lock");
	
	//build and return the JFormattedTextField?
	public JFormattedTextField getTimeField() throws ParseException {
		MaskFormatter timeMask = new MaskFormatter("##m:##s.##ms");
		try {
			timeMask = new MaskFormatter("##m:##s.##ms");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		timeInputField = new JFormattedTextField(timeMask);
		
		return timeInputField;
	}
	
//	btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
//	btnNewButton.addMouseListener(new MouseAdapter());
//	@Override
//	btnLockButton.addMouseListener(this);
//	addMouseListener(this);
//	public void mouseClicked(MouseEvent arg0) {
//		if(btnLockButton.getText() == "Lock") {
////			this.setFirstRaceTime(time);
//			System.out.print(this.getFirstRaceTime());
//			btnLockButton.setText("Unlock");
//		}
//		else {
//			btnLockButton.setText("Lock");
//		}
//	}
//	btnNewButton.setBounds(0, 0, 100, 20);
//	panel.add(btnNewButton, "cell 6 " + rowCounter);
	
	/**
	 * Default constructor.
	 * Inputs - None.
	 * Outputs - Creates a new TeamObject object.
	 */
	public TeamObject() {
//		btnLockButton.addMouseListener(this);
	}
	
	/**
	 * Constructor that sets the teamName.
	 * Inputs - String name - used to set the private teamName string.
	 * Outputs - Creates a new TeamObject object with set teamName
	 */
	public TeamObject(String name) {
//		btnLockButton.addMouseListener(this);
		teamName = name;
	}
	
	/**
	 * Constructor that sets the name and category. TESTING CONSTRUCTOR?
	 * Inputs 	- String name - used to set the private teamName string.
	 * 			- String cat - sets the private category string.
	 * Outputs - Creates a new TeamObject object with set teamName and category.
	 */
	public TeamObject(String name, String cat) {
//		btnLockButton.addMouseListener(this);
		teamName = name;
		category = cat;
	}
	
	/**
	 * Sets the private String teamName variable.
	 * Inputs - String name - String to set the teamName variable.
	 * Outputs - None.
	 */
	public void setTeamID(int num) {
		teamID = num;
	}
	
	/**
	 * Sets the private int teamID variable.
	 * Inputs - int name - int to set the teamID variable.
	 * Outputs - None.
	 */
	public void setTeamName(String name) {
		teamName = name;
	}
	
	/**
	 * Sets the private int place variable.
	 * Inputs - String cat - String to set the category variable.
	 * Outputs - None.
	 */
	public void setCategory(String cat) {
		category = cat;
	}
	
	/**
	 * Sets the private String category variable.
	 * Inputs - int place - integer to set the place variable.
	 * Outputs - None.
	 */
	public void setPlace(int num) {
		place = num;
	}
	
	/**
	 * Sets the priavte int firstRaceTime variable.
	 * Inputs - int time - integer to set the firstRaceTime varible.
	 * Outputs - None.
	 */
	public void setFirstRaceTime(int time)
	{
		firstRaceTime = time;
	}
	
	/**
	 * Sets the private int secondRaceTime variable.
	 * Inputs - int time - integer to set the secondRaceTime variable. 
	 * Outputs - None.
	 */
	public void setSecondRaceTime(int time)
	{
		secondRaceTime = time;
	}
	
	/**
	 * Sets the private int semifinalRaceTime variable.
	 * Inputs - int time - integer to set the semiFinalRaceTime variable.
	 * Outputs - None.
	 */
	public void setSemiFinalRaceTime(int time)
	{
		semiFinalRaceTime = time;
	}
	
	/**
	 * Sets the private int finalRaceTime variable.
	 * Inputs - int time - integer to set the finalRaceTime variable.
	 * Outputs - None.
	 */
	public void setFinalRaceTime(int time)
	{
		finalRaceTime = time;
	}
	
	/**
	 * Sets the private int averagedRaceTime variable.
	 * Inputs - int time - integer to set the averagedraceTime variable.
	 * Outputs - None.
	 */
	public void setAveragedRaceTime(int time)
	{
		averagedRaceTime = time;
	}
	
	/**
	 * Gets the private int teamID variable. 
	 * Inputs - None.
	 * Outputs - Returns the teamID variable. 
	 */
	public int getTeamID() {
		return teamID;
	}
	
	/**
	 * Gets the private String teamName variable. 
	 * Inputs - None.
	 * Outputs - Returns the teamName variable. 
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Gets the private String category variable.
	 * Inputs - None.
	 * Outputs - Returns the category variable.
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Gets the private int place variable.
	 * Inputs - None.
	 * Outputs - Returns the place variable.
	 */
	public String getPlace() {
		return category;
	}
	
	/**
	 * Gets the private int firstRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the firstRaceTime variable.
	 */
	public int getFirstRaceTime()
	{
		return firstRaceTime;
	}
	
	/**
	 * Gets the private int secondRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the secondRaceTime variable.
	 */
	public int getSecondRaceTime()
	{
		return secondRaceTime;
	}
	
	/**
	 * Gets the private int semiFinalraceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the semiFinalRaceTime variable.
	 */
	public int getSemiFinalRaceTime()
	{
		return semiFinalRaceTime;
	}
	
	/**
	 * Gets the private int finalRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the finalRaceTime variable.
	 */
	public int getFinalRaceTime()
	{
		return finalRaceTime;
	}
	
	/**
	 * Gets the private int averageRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the averagedRaceTime variable.
	 */
	public int getAveragedRaceTime()
	{
		return averagedRaceTime;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
//		Thread t = new Thread(new Runnable()) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if(btnLockButton.getText() == "Lock") {
		//				this.setFirstRaceTime(time);
						timeInputField.setEditable(false);
		//				timeInputField.setEnabled(false);
						
						try {
							getTimeField().setEditable(false);
						} catch (ParseException e) {
							e.printStackTrace();
						}
		//				System.out.print(this.getFirstRaceTime());
						btnLockButton.setText("Unlock");
					}
					else {
						timeInputField.setEditable(true);
		//				timeInputField.setEnabled(true);
						
						try {
							getTimeField().setEditable(false);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						btnLockButton.setText("Lock");
					}
				}
			});
//		}
//		t.start();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public JButton getLockButton() {
//		setFirstRaceTime(time);		//set the race time
		//need to add if statments to identity which race it is???
		btnLockButton.addMouseListener(this);
		btnLockButton.setHorizontalAlignment(SwingConstants.CENTER);
		btnLockButton.setBounds(0, 0, 100, 20);
		return btnLockButton;
	}
	
}
