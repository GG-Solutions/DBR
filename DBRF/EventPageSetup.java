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
	private JTextField BreakList;
	private JFormattedTextField CatList;
	private JFormattedTextField CatList2;
	private JFormattedTextField teamList;
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
	 */
	public EventPageSetup() {
		//page getting setup!
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
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFestival();
			}
		});
		btnCreate.setBounds(241, 308, 84, 29);
		confirmation.add(btnCreate);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toTeamSetup();
			}
		});
		btnPrevious.setBounds(6, 308, 84, 29);
		confirmation.add(btnPrevious);
		
		conFestName = new JLabel("FestName: ");
		conFestName.setBounds(6, 6, 319, 16);
		confirmation.add(conFestName);
		
		conTimeBetweenRaces = new JLabel("Time Between Races:");
		conTimeBetweenRaces.setBounds(6, 33, 319, 16);
		confirmation.add(conTimeBetweenRaces);
		
		conLanes = new JLabel("Lanes per race:");
		conLanes.setBounds(6, 61, 319, 16);
		confirmation.add(conLanes);
		
		conCategories = new JLabel("Categories that will be used:");
		conCategories.setBounds(6, 221, 319, 75);
		confirmation.add(conCategories);
		
		conTeams = new JLabel("Teams entered: ");
		conTeams.setBounds(6, 134, 319, 75);
		confirmation.add(conTeams);
		
		conBreaks = new JLabel("Breaks at:");
		conBreaks.setBounds(6, 89, 319, 46);
		confirmation.add(conBreaks);
		teamSetup.setLayout(null);
		teamSetup.setBorder(new EmptyBorder(5, 5, 5, 5));
		teamSetup.setBackground(Color.LIGHT_GRAY);
		teamSetup.setBounds(0, 0, 960, 518);
		contentPane.add(teamSetup);
		
		JLabel label = new JLabel("Team Setup");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setBounds(211, 17, 121, 25);
		teamSetup.add(label);
		
		JLabel label_1 = new JLabel("Team Name");
		label_1.setBounds(6, 57, 74, 16);
		teamSetup.add(label_1);
		
		teamName = new JTextField();
		teamName.setColumns(10);
		teamName.setBounds(6, 73, 228, 26);
		teamSetup.add(teamName);
		
		JLabel label_2 = new JLabel("Category");
		label_2.setBounds(271, 57, 61, 16);
		teamSetup.add(label_2);
		
		CatBox = new JComboBox<String>();
		CatBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		CatBox.setBounds(263, 74, 121, 27);
		teamSetup.add(CatBox);
		
		JButton addTeam = new JButton("Add Team");
		addTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTeam(teamName.getText());
				}
		});
		addTeam.setBounds(148, 52, 90, 29);
		teamSetup.add(addTeam);
		
		teamList = new JFormattedTextField();
		teamList.setBounds(6, 100, 528, 162);
		teamSetup.add(teamList);
		
		JButton teamDelete = new JButton("Delete");
		teamDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTeam(teamList.getSelectedText());
				}
		});
		teamDelete.setBounds(202, 260, 84, 29);
		teamSetup.add(teamDelete);
		
		JButton undoTeamDelete = new JButton("Undo");
		undoTeamDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamUndoDelete();
				}
		});
		undoTeamDelete.setBounds(287, 260, 74, 29);
		teamSetup.add(undoTeamDelete);
		
		JButton previous = new JButton("Previous");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevPage();
			}
		});
		previous.setBounds(6, 331, 117, 29);
		teamSetup.add(previous);
		
		JButton finish = new JButton("Finish");
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finish();
			}
		});
		finish.setBounds(417, 331, 117, 29);
		teamSetup.add(finish);
		
		JButton addCatToTeam = new JButton("+");
		addCatToTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCatToTeam(CatBox.getSelectedItem().toString(), teamName.getText());
			}
		});
		addCatToTeam.setBounds(381, 73, 43, 29);
		teamSetup.add(addCatToTeam);
		
		JLabel lblEventSetup = new JLabel("Event Setup");
		lblEventSetup.setBounds(214, 6, 111, 25);
		lblEventSetup.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblEventSetup);
		
		JLabel lblFestivalName = new JLabel("Festival Name");
		lblFestivalName.setBounds(6, 41, 94, 16);
		contentPane.add(lblFestivalName);
		
		FestName = new JTextField();
		FestName.setBounds(99, 36, 130, 26);
		contentPane.add(FestName);
		FestName.setColumns(10);
		
		JLabel lblTimeBetweenRaces = new JLabel("Time Between Races");
		lblTimeBetweenRaces.setBounds(6, 83, 130, 16);
		contentPane.add(lblTimeBetweenRaces);
		
		TBR = new JTextField();
		TBR.setColumns(10);
		TBR.setBounds(136, 78, 51, 26);
		contentPane.add(TBR);
		
		JLabel lblMinutes = new JLabel("Minutes");
		lblMinutes.setBounds(193, 83, 51, 16);
		contentPane.add(lblMinutes);
		
		JLabel lblMaxNumberOf = new JLabel("Max Number of Lanes");
		lblMaxNumberOf.setBounds(6, 116, 142, 16);
		contentPane.add(lblMaxNumberOf);
		
		LaneInput = new JTextField();
		LaneInput.setColumns(10);
		LaneInput.setBounds(146, 111, 44, 26);
		contentPane.add(LaneInput);
		
		JLabel lblBreakTimes = new JLabel("Break Time(s)");
		lblBreakTimes.setBounds(274, 48, 84, 16);
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
		BreakTime.setBounds(359, 45, 51, 26);
		contentPane.add(BreakTime);
		
		JButton AddBreak = new JButton("+");
		AddBreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToBreak(BreakTime.getText(), BreakEndTime.getText());
			}
		});
		AddBreak.setBounds(453, 45, 51, 29);
		contentPane.add(AddBreak);
		
		BreakList = new JFormattedTextField();//broken);
		BreakList.setBounds(282, 83, 214, 63);
		contentPane.add(BreakList);
		
		JButton DeleteBreak = new JButton("Delete");
		DeleteBreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBreak(BreakList.getSelectedText());
				}
		});
		DeleteBreak.setBounds(363, 152, 69, 29);
		contentPane.add(DeleteBreak);
		
		JButton UndoDelBreak = new JButton("Undo");
		UndoDelBreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBreakUndo();
				}
		});
		UndoDelBreak.setBounds(427, 152, 69, 29);
		contentPane.add(UndoDelBreak);
		
		JLabel lblCategories = new JLabel("Categories");
		lblCategories.setBounds(6, 186, 76, 16);
		contentPane.add(lblCategories);
		
		AddCat = new JTextField();
		AddCat.setColumns(10);
		AddCat.setBounds(88, 181, 84, 26);
		contentPane.add(AddCat);
		
		JButton AddCatButton = new JButton("+");
		AddCatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCat(AddCat.getText());
			}
		});
		AddCatButton.setBounds(169, 181, 51, 29);
		contentPane.add(AddCatButton);
		
		CatList = new JFormattedTextField();
		CatList.setBounds(6, 228, 214, 63);
		contentPane.add(CatList);
		
		JButton DelCat = new JButton("Delete");
		DelCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCat(CatList.getSelectedText());
				}
		});
		DelCat.setBounds(87, 297, 69, 29);
		contentPane.add(DelCat);
		
		JButton UndoDelCat = new JButton("Undo");
		UndoDelCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCatUndo();
				}
		});
		UndoDelCat.setBounds(151, 297, 69, 29);
		contentPane.add(UndoDelCat);
		
		JLabel lblUnused = new JLabel("Unused");
		lblUnused.setBounds(6, 210, 61, 16);
		contentPane.add(lblUnused);
		
		CatList2 = new JFormattedTextField();
		CatList2.setBounds(282, 228, 214, 63);
		contentPane.add(CatList2);
		
		JLabel lblUsed = new JLabel("Used");
		lblUsed.setBounds(282, 210, 61, 16);
		contentPane.add(lblUsed);
		
		JButton UseCat = new JButton(">");
		UseCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveCatUse(CatList.getSelectedText());
				}
		});
		UseCat.setBounds(226, 228, 44, 29);
		contentPane.add(UseCat);
		
		JButton UnuseCat = new JButton("<");
		UnuseCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveCatBack(CatList2.getSelectedText());
				}
		});
		UnuseCat.setBounds(226, 262, 44, 29);
		contentPane.add(UnuseCat);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextPage();
			}
		});
		btnNext.setBounds(417, 331, 117, 29);
		contentPane.add(btnNext);
		
		BreakEndTime = new JFormattedTextField();//broken);
		BreakEndTime.setBounds(407, 45, 51, 26);
		BreakEndTime.setText("");
		contentPane.add(BreakEndTime);
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setBounds(363, 29, 34, 16);
		contentPane.add(lblStart);
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setBounds(407, 29, 34, 16);
		contentPane.add(lblEnd);
	}
	
	//breaks for event setup
	//adds break to list
	public void addToBreak(String start, String end){
		ArrayList<Integer> w = new ArrayList<Integer>();
		int that = Integer.parseInt(start);
		int that1 = Integer.parseInt(end);
		w.add(that);
		w.add(that1);
		FestivalObject.breaksArray.add(w);
		BreakList.setText(FestivalObject.getBreakList().toString());
	}
	//deletes break form list
	public void deleteBreak(String selected){
		tempBreak = selected;
		System.out.println(tempBreak);
		try{
		FestivalObject.breaksArray.remove(tempBreak);}catch(NumberFormatException e){ e.getStackTrace();}
		BreakList.setText(String.valueOf(FestivalObject.getBreakList()));
	}
	//undo delete
	public void deleteBreakUndo(){
		//need to fix // don't know how yet
		FestivalObject.breaksArray.add(tempBreak);
		BreakList.setText(String.valueOf(FestivalObject.getBreakList()));
	}
	
	//actions for categories during event setup
	//adds category
	public void addCat(String cat){
		ArrayList<String> c = new ArrayList<String>();
		c.add(cat);
		FestivalObject.categoriesArray.addAll(c);
		CatList.setText(String.valueOf(FestivalObject.getCategory()));
	}
	//deletes category
	public void deleteCat(String deadCat){
		tempCat = deadCat;
		System.out.println(tempCat);
		FestivalObject.categoriesArray.remove(tempCat);
		CatList.setText(String.valueOf(FestivalObject.getCategory()));
	}
	//undo delete
	public void deleteCatUndo(){
		FestivalObject.categoriesArray.add(tempCat);
		CatList.setText(String.valueOf(FestivalObject.getCategory()));
	}
	//moves category to be used and add to team setup combobox
	public void moveCatUse(String useCat){
		tempCat2 = useCat;
		ArrayList<String> uc = new ArrayList<String>();
		uc.add(useCat);
		FestivalObject.Category_Use.addAll(uc);
		if(FestivalObject.categoriesArray.contains(tempCat2)){
		FestivalObject.categoriesArray.remove(useCat);}
		
		CatList.setText(String.valueOf(FestivalObject.getCategory()));
		CatList2.setText(String.valueOf(FestivalObject.getCategory_Use()));
		try{
		CatBox.addItem(tempCat2);}catch(NullPointerException e){e.getStackTrace();}
	}
	//moves category back and removes form combobox on team setup
	public void moveCatBack(String noUseCat){
		tempCat3 = noUseCat;
		ArrayList<String> nuc = new ArrayList<String>();
		nuc.add(noUseCat);
		FestivalObject.categoriesArray.addAll(nuc);
		if(FestivalObject.Category_Use.contains(tempCat3)){
			FestivalObject.Category_Use.remove(noUseCat);}
		
		CatList2.setText(String.valueOf(FestivalObject.getCategory_Use()));
		CatList.setText(String.valueOf(FestivalObject.getCategory()));
		try{
		CatBox.removeItem(tempCat3);}catch(NullPointerException e){e.getStackTrace();}
	}
	
	//page buttons
	//goes to team setup
	public void nextPage(){
		FestivalObject.festName = FestName.getText();
		FestivalObject.timeBetweenRaces = Integer.parseInt(TBR.getText());
		FestivalObject.lanes = Integer.parseInt(LaneInput.getText());
		teamSetup.setVisible(true);
	}
	//goes back to event setup
	public void prevPage(){
		teamSetup.setVisible(false);
	}
	
	public void toTeamSetup(){
		confirmation.setVisible(false);
		//conFestName.setText("");
	}
	
	public void finish(){//will bring up popup with entered information
		confirmation.setVisible(true);
		//teamSetup.setVisible(false);
		//contentPane.setVisible(false);
		
		conFestName.setText("Festival Name: " + FestivalObject.getFestName());
		conTimeBetweenRaces.setText("Time Between Races: " + FestivalObject.getTBR() + " mins");
		conLanes.setText("Lanes per race: " + FestivalObject.getLanes());
		conBreaks.setText("Breaks at: " + FestivalObject.getBreakList());
		conTeams.setText("Teams Entered: " + FestivalObject.getTeam());
		conCategories.setText("Categories: " + FestivalObject.getCategory_Use());
		//following prints are to test if the variables are getting information
		System.out.println(FestivalObject.getFestName());
		System.out.println(FestivalObject.getTBR());
		System.out.println(FestivalObject.getLanes());
		System.out.println(FestivalObject.getCategory());
		System.out.println(FestivalObject.getCategory_Use());
		System.out.println(FestivalObject.getBreakList());
		System.out.println(FestivalObject.getTeam());
	}
	
	public void createFestival(){
		//passes festival information
	}
	
	//actions dealing w/ teams
	public void addTeam(String name){
		tempName = name;
		if(FestivalObject.teamsArray.contains(name)){
			System.out.println("Name Already Exists\n");
		}else{
			FestivalObject.teamsArray.add(tempName);
			teamList.setText(String.valueOf(FestivalObject.getTeam()));
		}
	}
	//if team name exists and the names match up(as in it's not a new team) add the category to the team
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
		teamList.setText(String.valueOf(FestivalObject.getTeam()));
		}
		
					
		
		
		
		
	}
	//remove team from arraylist
	public void deleteTeam(String noTeam){
		tempTeam = noTeam;
		System.out.println(tempTeam);
		FestivalObject.teamsArray.remove(noTeam);
		teamList.setText(String.valueOf(FestivalObject.getTeam()));
	}
	//undo delete
	public void teamUndoDelete(){
		FestivalObject.teamsArray.add(tempTeam);
		teamList.setText(String.valueOf(FestivalObject.getTeam()));
	}
}






