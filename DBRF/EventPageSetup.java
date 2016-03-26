package DBRF;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import javax.swing.JTextPane;


public class EventPageSetup extends JFrame {

	/**
	 * 
	 */
	//variables jumbled up b/c i added them as needed
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel teamSetup;
	private JPanel confirmation;
	private JTextField FestName;
	private JTextField TBR;
	private JTextField LaneInput;
	private JFormattedTextField BreakTime;
	private JTextField AddCat;
	private JFormattedTextField BreakEndTime;
	private JTextField teamName;
	private JTextPane BreakList;
	private JTextPane CatList;
	private JTextPane CatList2;
	private JTextPane teamList;
	private JTextPane BreakPane;
	private JTextPane TeamPane;
	private JTextPane CategoryPane;
	private JComboBox<String> CatBox;
	private String tempCat;
	private String tempCat2;
	private String tempCat3;
	private String tempName;
	private String tempTeam;
	private JLabel conFestName;
	private JLabel conTimeBetweenRaces;
	private JLabel conLanes;
	private JLabel conBreaks;
	private JLabel conTeams;
	private JLabel conCategories;
	private JLabel lblTimeBetweenRaces;
	private String tempBreak;
		
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					EventPageSetup frame = new EventPageSetup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * Creates the whole window and anything that is needed in it.
	 */
	public EventPageSetup() {
		//page getting setup!
		setResizable(false);
		setTitle("Setup");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		teamSetup = new JPanel();
		teamSetup.setVisible(false);
		
		confirmation = new JPanel();
		confirmation.setVisible(false);
		confirmation.setBounds(0, 0, 960, 518);
		contentPane.add(confirmation);
		confirmation.setLayout(null);
		confirmation.setBorder(new EmptyBorder(5, 5, 5, 5));
		confirmation.setBackground(new Color(211, 211, 211));
		
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFestival();
			}
		});
		btnCreate.setBounds(724, 463, 150, 49);
		confirmation.add(btnCreate);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toTeamSetup();
			}
		});
		btnPrevious.setBounds(116, 463, 84, 29);
		confirmation.add(btnPrevious);
		
		conFestName = new JLabel("FestName: ");
		conFestName.setBounds(217, 6, 567, 16);
		confirmation.add(conFestName);
		
		conTimeBetweenRaces = new JLabel("Time Between Races:");
		conTimeBetweenRaces.setBounds(217, 33, 319, 16);
		confirmation.add(conTimeBetweenRaces);
		
		conLanes = new JLabel("Lanes per race:");
		conLanes.setBounds(217, 61, 319, 16);
		confirmation.add(conLanes);
		
		conCategories = new JLabel("Categories that will be used:");
		conCategories.setBounds(492, 326, 200, 16);
		confirmation.add(conCategories);
		
		conTeams = new JLabel("Teams entered: ");
		conTeams.setBounds(217, 89, 100, 16);
		confirmation.add(conTeams);
		
		conBreaks = new JLabel("Breaks at:");
		conBreaks.setBounds(116, 326, 73, 16);
		confirmation.add(conBreaks);
		
		BreakPane = new JTextPane();
		BreakPane.setEditable(false);
		BreakPane.setBounds(116, 354, 375, 97);
		confirmation.add(BreakPane);
		
		TeamPane = new JTextPane();
		TeamPane.setEditable(false);
		TeamPane.setBounds(217, 107, 567, 207);
		confirmation.add(TeamPane);
		
		CategoryPane = new JTextPane();
		CategoryPane.setEditable(false);
		CategoryPane.setBounds(499, 354, 375, 97);
		confirmation.add(CategoryPane);
		teamSetup.setLayout(null);
		teamSetup.setBorder(new EmptyBorder(5, 5, 5, 5));
		teamSetup.setBackground(Color.LIGHT_GRAY);
		teamSetup.setBounds(0, 0, 960, 518);
		contentPane.add(teamSetup);
		
		JLabel label = new JLabel("Team Setup");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setBounds(424, 6, 121, 25);
		teamSetup.add(label);
		
		JLabel label_1 = new JLabel("Team Name");
		label_1.setBounds(219, 46, 74, 16);
		teamSetup.add(label_1);
		
		teamName = new JTextField();
		teamName.setColumns(10);
		teamName.setBounds(219, 62, 228, 26);
		teamSetup.add(teamName);
		
		JLabel label_2 = new JLabel("Category");
		label_2.setBounds(594, 41, 61, 16);
		teamSetup.add(label_2);
		
		CatBox = new JComboBox<String>();
		CatBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		CatBox.setBounds(586, 58, 121, 27);
		teamSetup.add(CatBox);
		
		JButton addTeam = new JButton("Add Team");
		addTeam.setEnabled(false);
		addTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTeam(teamName.getText());
				}
		});
		addTeam.setBounds(361, 41, 90, 29);
		teamSetup.add(addTeam);
		
		teamList = new JTextPane();
		teamList.setEditable(false);
		teamList.setBounds(219, 89, 528, 288);
		teamSetup.add(teamList);
		
		JButton teamDelete = new JButton("Delete");
		teamDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTeam(teamList.getSelectedText());
				}
		});
		teamDelete.setBounds(415, 389, 84, 29);
		teamSetup.add(teamDelete);
		
		JButton undoTeamDelete = new JButton("Undo");
		undoTeamDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamUndoDelete();
				}
		});
		undoTeamDelete.setBounds(500, 389, 74, 29);
		teamSetup.add(undoTeamDelete);
		
		JButton previous = new JButton("Previous");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevPage();
			}
		});
		previous.setBounds(219, 460, 117, 29);
		teamSetup.add(previous);
		
		JButton finish = new JButton("Finish");
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finish();
			}
		});
		finish.setBounds(630, 460, 117, 29);
		teamSetup.add(finish);
		
		JButton addCatToTeam = new JButton("+");
		addCatToTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCatToTeam(CatBox.getSelectedItem().toString(), teamName.getText());
			}
		});
		addCatToTeam.setBounds(704, 57, 43, 29);
		teamSetup.add(addCatToTeam);
		
		JLabel lblEventSetup = new JLabel("Event Setup");
		lblEventSetup.setBounds(424, 6, 111, 25);
		lblEventSetup.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblEventSetup);
		
		JLabel lblFestivalName = new JLabel("Festival Name");
		lblFestivalName.setBounds(192, 93, 94, 16);
		contentPane.add(lblFestivalName);
		
		FestName = new JTextField();
		FestName.setBounds(285, 88, 130, 26);
		contentPane.add(FestName);
		FestName.setColumns(10);
		
		lblTimeBetweenRaces = new JLabel("Time Between Races");
		lblTimeBetweenRaces.setBounds(192, 126, 130, 16);
		contentPane.add(lblTimeBetweenRaces);
		
		TBR = new JTextField();
		TBR.setColumns(10);
		TBR.setBounds(322, 121, 51, 26);
		contentPane.add(TBR);
		
		JLabel lblMinutes = new JLabel("Minutes");
		lblMinutes.setBounds(379, 126, 51, 16);
		contentPane.add(lblMinutes);
		
		JLabel lblMaxNumberOf = new JLabel("Max Number of Lanes");
		lblMaxNumberOf.setBounds(192, 159, 142, 16);
		contentPane.add(lblMaxNumberOf);
		
		LaneInput = new JTextField();
		LaneInput.setColumns(10);
		LaneInput.setBounds(332, 154, 44, 26);
		contentPane.add(LaneInput);
		
		JLabel lblBreakTimes = new JLabel("Break Time(s)");
		lblBreakTimes.setBounds(519, 88, 84, 16);
		contentPane.add(lblBreakTimes);
		
		//code i thought i'd use, but found a different way. might need to refer back to this
		//at some point
//		MaskFormatter broken = null;
//		try {
//			broken = new MaskFormatter("##:##");
//			broken.setAllowsInvalid(false);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		BreakTime = new JFormattedTextField();//broken);
		BreakTime.setText("");
		BreakTime.setBounds(675, 83, 51, 26);
		contentPane.add(BreakTime);
		
		JButton AddBreak = new JButton("+");
		AddBreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToBreak(BreakTime.getText(), BreakEndTime.getText());
			}
		});
		AddBreak.setBounds(769, 83, 51, 29);
		contentPane.add(AddBreak);
		
		BreakList = new JTextPane();//broken);
		BreakList.setEditable(false);
		BreakList.setBounds(519, 115, 301, 99);
		contentPane.add(BreakList);
		
		JButton DeleteBreak = new JButton("Delete");
		DeleteBreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBreak(BreakList.getSelectedText());
				}
		});
		DeleteBreak.setBounds(687, 213, 69, 29);
		contentPane.add(DeleteBreak);
		
		JButton UndoDelBreak = new JButton("Undo");
		UndoDelBreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBreakUndo();
				}
		});
		UndoDelBreak.setBounds(751, 213, 69, 29);
		contentPane.add(UndoDelBreak);
		
		JLabel lblCategories = new JLabel("Categories");
		lblCategories.setBounds(223, 253, 76, 16);
		contentPane.add(lblCategories);
		
		AddCat = new JTextField();
		AddCat.setColumns(10);
		AddCat.setBounds(305, 248, 84, 26);
		contentPane.add(AddCat);
		
		JButton AddCatButton = new JButton("+");
		AddCatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCat(AddCat.getText());
			}
		});
		AddCatButton.setBounds(386, 248, 51, 29);
		contentPane.add(AddCatButton);
		
		CatList = new JTextPane();
		CatList.setEditable(false);
		CatList.setBounds(223, 295, 214, 63);
		contentPane.add(CatList);
		
		JButton DelCat = new JButton("Delete");
		DelCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCat(CatList.getSelectedText());
				}
		});
		DelCat.setBounds(304, 370, 69, 29);
		contentPane.add(DelCat);
		
		JButton UndoDelCat = new JButton("Undo");
		UndoDelCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCatUndo();
				}
		});
		UndoDelCat.setBounds(368, 370, 69, 29);
		contentPane.add(UndoDelCat);
		
		JLabel lblUnused = new JLabel("Unused");
		lblUnused.setBounds(223, 277, 61, 16);
		contentPane.add(lblUnused);
		
		CatList2 = new JTextPane();
		CatList2.setEditable(false);
		CatList2.setBounds(499, 295, 214, 63);
		contentPane.add(CatList2);
		
		JLabel lblUsed = new JLabel("Used");
		lblUsed.setBounds(499, 277, 61, 16);
		contentPane.add(lblUsed);
		
		JButton UseCat = new JButton(">");
		UseCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveCatUse(CatList.getSelectedText());
				}
		});
		UseCat.setBounds(443, 295, 44, 29);
		contentPane.add(UseCat);
		
		JButton UnuseCat = new JButton("<");
		UnuseCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveCatBack(CatList2.getSelectedText());
				}
		});
		UnuseCat.setBounds(443, 329, 44, 29);
		contentPane.add(UnuseCat);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextPage();
			}
		});
		btnNext.setBounds(703, 414, 117, 29);
		contentPane.add(btnNext);
		
		BreakEndTime = new JFormattedTextField();//broken);
		BreakEndTime.setBounds(723, 83, 51, 26);
		BreakEndTime.setText("");
		contentPane.add(BreakEndTime);
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setBounds(679, 67, 34, 16);
		contentPane.add(lblStart);
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setBounds(723, 67, 34, 16);
		contentPane.add(lblEnd);
	}
	
	//breaks for event setup
	/**
	 * addToBreak will take 2 integers as strings and enter them into an list, then enter that list into 
	 * the breaks list. Finally, it will populate a textpane with the list of lists
	 * 
	 * Input: string of integers for the start and end times of a break
	 * 
	 * Output: ArrayList of breaks
	 */
	public void addToBreak(String start, String end){
		ArrayList<Integer> w = new ArrayList<Integer>();
		int that = Integer.parseInt(start);
		int that1 = Integer.parseInt(end);
		w.add(that);
		w.add(that1);
		FestivalObject.breaksArray.add(w);
		BreakList.setText(String.valueOf(FestivalObject.getBreakList()));
	}
	
	/**
	 * This will take the selected(highlighted) text from the textpane, find it in the list of lists, store it in a variable,
	 * and then try to remove it.
	 * 
	 * Input: selected(highlighted) string
	 * Output: list of breaks
	 */
	public void deleteBreak(String selected){
		tempBreak = selected;
		System.out.println(tempBreak);
		try{//won't remove 
		FestivalObject.breaksArray.remove(tempBreak);}catch(IndexOutOfBoundsException e){ e.getStackTrace();}
		BreakList.setText(String.valueOf(FestivalObject.getBreakList()));
	}
	
	/**
	 * This will try to undo what the delete did by taking the stored temporary variable and adding it back
	 * into the list of list
	 * 
	 * Input: none
	 * Output: list
	 */
	public void deleteBreakUndo(){
		//need to fix // don't know how to fix // help // stopped it from being error, but still don't know what to do
		ArrayList<Integer> br = new ArrayList<Integer>();
		br.add(Integer.valueOf(tempBreak));
		FestivalObject.breaksArray.add(br);
		BreakList.setText(String.valueOf(FestivalObject.getBreakList()));
	}
	
	//actions for categories during event setup
	/**
	 * This will add a string to an list and display it in a text pane
	 * 
	 * Input: string
	 * 
	 * Output: list into a list
	 */
	public void addCat(String cat){
		ArrayList<String> c = new ArrayList<String>();
		c.add(cat);
		FestivalObject.categoriesArray.addAll(c);
		CatList.setText(String.valueOf(FestivalObject.getCategory()));
	}
	
	/**
	 * This will take selected text, find in the list, and remove it while storing it in a temporary variable
	 * 
	 * Input: string
	 * 
	 * Output: list into textpane
	 */
	public void deleteCat(String deadCat){
		tempCat = deadCat;
		System.out.println(tempCat);
		FestivalObject.categoriesArray.remove(tempCat);
		CatList.setText(String.valueOf(FestivalObject.getCategory()));
	}
	
	/**
	 * This will try to undo what the delete function did by adding the temporary variable back into the list
	 * 
	 * Input: none
	 * 
	 * Output: list in a textpane
	 */
	public void deleteCatUndo(){
		FestivalObject.categoriesArray.add(tempCat);
		CatList.setText(String.valueOf(FestivalObject.getCategory()));
	}
	
	/**
	 * This method will move the selected text over to the "will be used" textpane
	 * as well as update/populate a dropdown list on the team setup page
	 * 
	 * Input: Selected text as a string
	 * 
	 * Output: updated lists to textpanes
	 */
	public void moveCatUse(String useCat){
		tempCat2 = useCat;
		ArrayList<String> uc = new ArrayList<String>();
		uc.add(useCat);
		FestivalObject.categoryUse.addAll(uc);
		if(FestivalObject.categoriesArray.contains(tempCat2)){
		FestivalObject.categoriesArray.remove(useCat);}
		
		CatList.setText(String.valueOf(FestivalObject.getCategory()));
		CatList2.setText(String.valueOf(FestivalObject.getCategoryUse()));
		try{
		CatBox.addItem(tempCat2);}catch(NullPointerException e){e.getStackTrace();}
	}
	
	/**
	 * This will basically undo the move function above. Taking the selected text and moving it back to the
	 * "unused" textpane and will update the dropdown on the team setup page
	 * 
	 * Input: string of selected text
	 * 
	 * Output: updated lists to textpanes
	 */
	public void moveCatBack(String noUseCat){
		tempCat3 = noUseCat;
		ArrayList<String> nuc = new ArrayList<String>();
		nuc.add(noUseCat);
		FestivalObject.categoriesArray.addAll(nuc);
		if(FestivalObject.categoryUse.contains(tempCat3)){
			FestivalObject.categoryUse.remove(noUseCat);}
		
		CatList2.setText(String.valueOf(FestivalObject.getCategoryUse()));
		CatList.setText(String.valueOf(FestivalObject.getCategory()));
		try{
		CatBox.removeItem(tempCat3);}catch(NullPointerException e){e.getStackTrace();}
	}
	
	//page buttons
	/**
	 * Will move forward in the festival setup process
	 * 
	 * Input: none
	 * 
	 * Output: stores festival name, time between races, and number of lanes to variables
	 */
	public void nextPage(){
		FestivalObject.festivalName = FestName.getText();
		FestivalObject.timeBetweenRaces = Integer.parseInt(TBR.getText());
		FestivalObject.numOfLanes = Integer.parseInt(LaneInput.getText());
		teamSetup.setVisible(true);
		contentPane.setEnabled(false);
	}
	
	/**
	 * Will go backwards in the festival setup process
	 * 
	 * Input: none
	 * 
	 * Output: none
	 */
	public void prevPage(){
		teamSetup.setEnabled(false);
		teamSetup.setVisible(false);
		contentPane.setEnabled(true);
	}
	
	/**
	 * Will go backwards in the festival process
	 * 
	 * Input: none
	 * 
	 * Output: none
	 */
	public void toTeamSetup(){
		teamSetup.setEnabled(true);
		teamSetup.setVisible(true);
		confirmation.setEnabled(false);
		confirmation.setVisible(false);
	}
	
	/**
	 * Will go forwards in the festival setup process to the confirmation page
	 * 
	 * Input: none
	 * 
	 * Output: displays information to be confirmed by the user
	 */
	public void finish(){
		confirmation.setVisible(true);
		confirmation.setEnabled(true);
		teamSetup.setEnabled(false);
		teamSetup.setVisible(false);
		
		conFestName.setText("Festival Name: " + FestivalObject.getFestivalName());
		conTimeBetweenRaces.setText("Time Between Races: " + FestivalObject.getTBR() + " mins");
		conLanes.setText("Lanes per race: " + FestivalObject.getLanes());
		conBreaks.setText("Breaks at: ");
		BreakPane.setText(String.valueOf(FestivalObject.getBreakList()));
		conTeams.setText("Teams Entered: ");
		TeamPane.setText(FestivalObject.getTeamsArray().toString());
		conCategories.setText("Categories that will be used: ");
		CategoryPane.setText(FestivalObject.getCategoryUse().toString());
		
		//following prints are to test if the variables are getting information
		System.out.println(FestivalObject.getFestivalName());
		System.out.println(FestivalObject.getTBR());
		System.out.println(FestivalObject.getLanes());
		System.out.println(FestivalObject.getCategory());
		System.out.println(FestivalObject.getCategoryUse());
		System.out.println(FestivalObject.getBreakList());
		System.out.println(FestivalObject.getTeamsArray());
	}
	
	/**
	 * WIP
	 * 
	 * Input: none
	 * 
	 * Output: pass information for race generation
	 */
	public void createFestival(){
		//passes festival information
	}
	
	//actions dealing with teams
	public void addTeam(String name){
		//currently not being used!
		tempName = name;
		if(FestivalObject.teamsArray.contains(name)){
			System.out.println("Name Already Exists\n");
		}else{
			FestivalObject.teamsArray.add(tempName);
			teamList.setText(String.valueOf(FestivalObject.getTeamsArray()));
		}
	}
	
	//if team name exists and the names match up(as in it's not a new team) add the category to the team
	/**
	 * This will take a team add it to a list, then take the catgory add that to a separate list, then add the category list to the team
	 * list.
	 * 
	 * Input: team name string and category string
	 * 
	 * Output: teams with a category(s) in a list to a textpane
	 */
	public void addCatToTeam(String Cat, String name){
		//if(FestivalObject.teamsArray.contains(tempName)){// && teamName.getText() == tempName){
			//add category to team
			//FestivalObject.teamsArray.add(Cat);
			ArrayList<String> t = new ArrayList<String>();
			tempName = name;
			if(FestivalObject.teamsArray.contains(tempName)){System.out.println("Team already in category.");}else{
			t.add(Cat);
			//FestivalObject.teamsArray.addAll(t);}
			
		//}else{
			//nothing
		//	System.out.println("team doesn't exist");
		//}
		
		FestivalObject.teamsArray.add(tempName + t.toString());
		teamList.setText(String.valueOf(FestivalObject.getTeamsArray()));
			}
	}
	
	/**
	 * This will try to delete a team/category pair from the list, stored in a temporary variable, and update the textpane
	 * 
	 * Input: selected text
	 * 
	 * Output: updated team/category list and updates the textpane it's in
	 */
	public void deleteTeam(String noTeam){
		tempTeam = noTeam;
		System.out.println(tempTeam);
		FestivalObject.teamsArray.remove(FestivalObject.teamsArray.indexOf(noTeam));
		teamList.setText(String.valueOf(FestivalObject.getTeamsArray()));
	}
	
	/**
	 * Will undo the deletion from above using a temporary variable to add it back in
	 * 
	 * Input: none
	 * 
	 * Output: updated team/category list
	 */
	public void teamUndoDelete(){
		FestivalObject.teamsArray.add(tempTeam);
		teamList.setText(String.valueOf(FestivalObject.getTeamsArray()));
	}
}






