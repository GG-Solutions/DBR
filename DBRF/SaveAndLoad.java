package DBRF;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SaveAndLoad {
	
	/**
	 * Builds and exports an XML file that svaes all needed variables.
	 * Inputs - None.
	 * Outputs - An XML file.
	 */
	public static void saveXML() {
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = null;
		try {
			dB = dBF.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		Document doc = dB.newDocument();
		
		//start with a general festival tag and add childs to this
		Element festival = doc.createElement("festival");
		doc.appendChild(festival);
		
		//output the festival name
		Element festivalName = doc.createElement("festivalName");
		festivalName.appendChild(doc.createTextNode(FestivalObject.getFestivalName()));
		festival.appendChild(festivalName);
		
		//output the start time day
		Element startTimeDay = doc.createElement("startTimeDay");
		startTimeDay.appendChild(doc.createTextNode(Integer.toString(FestivalObject.startDayTime)));
		festival.appendChild(startTimeDay);
		
		//output the time between races
		Element timeBetweenRaces = doc.createElement("timeBetweenRaces");
		timeBetweenRaces.appendChild(doc.createTextNode(Integer.toString(FestivalObject.timeBetweenRaces)));
		festival.appendChild(timeBetweenRaces);
		
		//output the number of lanes
		Element numOfLanes = doc.createElement("numOfLanes");
		numOfLanes.appendChild(doc.createTextNode(Integer.toString(FestivalObject.numOfLanes)));
		festival.appendChild(numOfLanes);
		
		//output the font
		Element font = doc.createElement("font");
		Element fontName = doc.createElement("fontName");
		Element fontStyle = doc.createElement("fontStyle");
		Element fontSize = doc.createElement("fontSize");
		fontName.appendChild(doc.createTextNode(FestivalObject.getFont().getFontName()));
		fontStyle.appendChild(doc.createTextNode(Integer.toString(FestivalObject.getFont().getStyle())));
		fontSize.appendChild(doc.createTextNode(Integer.toString(FestivalObject.getFont().getSize())));
		font.appendChild(fontName);
		font.appendChild(fontStyle);
		font.appendChild(fontSize);
		festival.appendChild(font);		//add the font to the document
		
		//output the breaks array
		Element breaksArray = doc.createElement("breaksArray");
		
		for(int i = 0; i < FestivalObject.breaksArray.size(); i++) {
			Element breakTime = doc.createElement("breakTime");		//create a break element to add the start and end times to
			Element start = doc.createElement("start");		//element for the start time
			Element end = doc.createElement("end");		//element for the end time
			start.appendChild(doc.createTextNode(Integer.toString(FestivalObject.getBreakList().get(i).get(0))));		//break start time
			end.appendChild(doc.createTextNode(Integer.toString(FestivalObject.getBreakList().get(i).get(1))));		//break end time
			
			breakTime.appendChild(start);
			breakTime.appendChild(end);
			breaksArray.appendChild(breakTime);
		}
		
		festival.appendChild(breaksArray);	//add the breaks array to the document
		
		//output the categories array
		Element categoriesArray = doc.createElement("categoriesArray");
		
		for(int i = 0; i < FestivalObject.categoriesArray.size(); i ++) {
			Element category = doc.createElement("category");
			category.appendChild(doc.createTextNode(FestivalObject.categoriesArray.get(i)));
			categoriesArray.appendChild(category);
		}
		
		festival.appendChild(categoriesArray);
		
		//build the teamsArray information
		Element teamsArray = doc.createElement("teamsArray");
		
		for(int i = 0; i < FestivalObject.teamsArray.size(); i++) {
			
			Element team = doc.createElement("team");	//create a team element to build
			
			Element teamID = doc.createElement("teamID");
			teamID.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getTeamID())));
			team.appendChild(teamID);
			
			Element teamName = doc.createElement("teamName");
			teamName.appendChild(doc.createTextNode(FestivalObject.teamsArray.get(i).getTeamName()));
			team.appendChild(teamName);
			
			Element category = doc.createElement("category");
			category.appendChild(doc.createTextNode(FestivalObject.teamsArray.get(i).getCategory()));
			team.appendChild(category);
			
			Element place = doc.createElement("place");
			place.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getPlace())));
			team.appendChild(place);
			
			Element firstRaceTime = doc.createElement("firstRaceTime");
			firstRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getFirstRaceTime())));
			team.appendChild(firstRaceTime);
			
			Element secondRaceTime = doc.createElement("secondRaceTime");
			secondRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getSecondRaceTime())));
			team.appendChild(secondRaceTime);
			
			Element semiFinalRaceTime = doc.createElement("semiFinalRaceTime");
			semiFinalRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getSemiFinalRaceTime())));
			team.appendChild(semiFinalRaceTime);
			
			Element finalRaceTime = doc.createElement("finalRaceTime");
			finalRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getFinalRaceTime())));
			team.appendChild(finalRaceTime);
			
			Element averagedRaceTime = doc.createElement("averagedRaceTime");
			averagedRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getAveragedRaceTime())));
			team.appendChild(averagedRaceTime);
			
			teamsArray.appendChild(team);	//add the team to the array
		}
		
		festival.appendChild(teamsArray);	//add the whole teams array
		
		//output the races array
		Element racesArray = doc.createElement("racesArray");
		
		for(int i =0; i < FestivalObject.racesArray.size(); i ++) {
			
			Element race = doc.createElement("race");	//create a race to add the race information to
			
			Element raceNumber = doc.createElement("raceNumber");
			raceNumber.appendChild(doc.createTextNode(Integer.toString(FestivalObject.racesArray.get(i).getRaceNumber())));
			race.appendChild(raceNumber);
			
			Element raceTime = doc.createElement("raceTime");
			raceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.racesArray.get(i).getRaceTime())));
			race.appendChild(raceTime);
			
			Element category = doc.createElement("category");
			category.appendChild(doc.createTextNode(FestivalObject.racesArray.get(i).getCategory()));
			race.appendChild(category);
			
			Element teamsRacing = doc.createElement("teamsRacing");
			
			//output all the teams that raced in this race
			for(int j = 0; j < FestivalObject.racesArray.get(i).getTeamsRacing().size() ; j++) {
				//all the same as outputing a regular team's information
				Element team = doc.createElement("team");	//create a team element to build
				
				Element teamID = doc.createElement("teamID");
				teamID.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getTeamID())));
				team.appendChild(teamID);
				
				Element teamName = doc.createElement("teamName");
				teamName.appendChild(doc.createTextNode(FestivalObject.racesArray.get(i).getTeamsRacing().get(j).getTeamName()));
				team.appendChild(teamName);
				
				Element category2 = doc.createElement("category");
				category2.appendChild(doc.createTextNode(FestivalObject.racesArray.get(i).getTeamsRacing().get(j).getCategory()));
				team.appendChild(category2);
				
				Element place = doc.createElement("place");
				place.appendChild(doc.createTextNode(Integer.toString(FestivalObject.racesArray.get(i).getTeamsRacing().get(j).getPlace())));
				team.appendChild(place);
				
				Element firstRaceTime = doc.createElement("firstRaceTime");
				firstRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.racesArray.get(i).getTeamsRacing().get(j).getFirstRaceTime())));
				team.appendChild(firstRaceTime);
				
				Element secondRaceTime = doc.createElement("secondRaceTime");
				secondRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.racesArray.get(i).getTeamsRacing().get(j).getSecondRaceTime())));
				team.appendChild(secondRaceTime);
				
				Element semiFinalRaceTime = doc.createElement("semiFinalRaceTime");
				semiFinalRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.racesArray.get(i).getTeamsRacing().get(j).getSemiFinalRaceTime())));
				team.appendChild(semiFinalRaceTime);
				
				Element finalRaceTime = doc.createElement("finalRaceTime");
				finalRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.racesArray.get(i).getTeamsRacing().get(j).getFinalRaceTime())));
				team.appendChild(finalRaceTime);
				
				Element averagedRaceTime = doc.createElement("averagedRaceTime");
				averagedRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.racesArray.get(i).getTeamsRacing().get(j).getAveragedRaceTime())));
				team.appendChild(averagedRaceTime);
				
				teamsRacing.appendChild(team);	//add the created team to the teamsRacing
			}
			
			race.appendChild(teamsRacing);	//add the teams in that race to the race element
			racesArray.appendChild(race);	//add the race to the racesArray
		}
		
		festival.appendChild(racesArray);
		
		//transform the built information into a formatted xml file
		TransformerFactory tF = TransformerFactory.newInstance();
		Transformer tran = null;
		try {
			tran = tF.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		tran.setOutputProperty(OutputKeys.INDENT, "yes");	//add indenting to output xml file
		tran.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");	//adds 4 spaces to each indexed xml line
		DOMSource src = new DOMSource(doc);
		
		StreamResult sR = new StreamResult(new File(System.getProperty("user.home") + "\\Desktop\\festival.xml"));	//TODO - change to the festivalName?
		
		//output the file
		try {
			tran.transform(src, sR);
		} catch (TransformerException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Loads information from an XML file.
	 * Inputs - None.
	 * Outputs - Builds the variables in the FestivalObject class.
	 */
	public static void loadXML() throws Exception {
		File xmlFile = new File(System.getProperty("user.home") + "\\Desktop\\festival.xml");	//TODO - change the path
		
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dBF.newDocumentBuilder();
		Document doc = dB.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
//		NodeList festival = doc.getElementsByTagName("festival");
		
		//get all the information in node lists from the document
		NodeList listFestivalName = doc.getElementsByTagName("festivalName");
		NodeList listStartTimeDay = doc.getElementsByTagName("startTimeDay");
		NodeList listTimeBetweenRaces = doc.getElementsByTagName("timeBetweenRaces");
		NodeList listNumOflanes = doc.getElementsByTagName("numOfLanes");
		NodeList listFont = doc.getElementsByTagName("font");
		NodeList listBreaksArray = doc.getElementsByTagName("breaksArray");
		NodeList listCategoriesArray = doc.getElementsByTagName("categoriesArray");
		NodeList listTeamsArray = doc.getElementsByTagName("teamsArray");
		NodeList listRacesArray = doc.getElementsByTagName("racesArray");
		
		Node node = null;	//create a node to store current information in
		Element el = null; 	//create an element to store current information in
		
		//get the festival name
		node = listFestivalName.item(0);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			el = (Element) node;
			FestivalObject.setFestName(el.getTextContent());
		}
		
//		System.out.println(FestivalObject.getFestivalName());
		
		node = listStartTimeDay.item(0);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			el = (Element) node;
			FestivalObject.setStartDayTime(Integer.parseInt(el.getTextContent()));
		}
		
		//get the time between races
		node = listTimeBetweenRaces.item(0);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			el = (Element) node;
			FestivalObject.setTimeBetweenRaces(Integer.parseInt(el.getTextContent()));
		}
		
		//get the number of lanes
		node = listNumOflanes.item(0);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			el = (Element) node;
			FestivalObject.setNumOfLanes(Integer.parseInt(el.getTextContent()));
		}
		
		//get the font
		node = listFont.item(0);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			el = (Element) node;
			//build the font
			String fName = el.getElementsByTagName("fontName").item(0).getTextContent();
			int fStyle = Integer.parseInt(el.getElementsByTagName("fontStyle").item(0).getTextContent());
			int fSize = Integer.parseInt(el.getElementsByTagName("fontSize").item(0).getTextContent());
			Font f = new Font(fName, fStyle, fSize);
			FestivalObject.setFont(f);		//set the font in FestivalObject
		}
		
		//get the breaks
		for(int i = 0; i < listBreaksArray.getLength(); i++) {
			node = listBreaksArray.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				el = (Element) node;
				ArrayList<Integer> breakTime = new ArrayList<Integer>();	//create an arry list to populate
				breakTime.add(Integer.parseInt(el.getElementsByTagName("start").item(0).getTextContent()));		//get the start time
				breakTime.add(Integer.parseInt(el.getElementsByTagName("end").item(0).getTextContent()));		//get the end time
				FestivalObject.breaksArray.add(breakTime);		//add the break to the FestivalObject
			}
		}
		
		//get the categories
		for(int i = 0; i < listCategoriesArray.getLength(); i++) {
			node = listCategoriesArray.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				el = (Element) node;
				FestivalObject.categoriesArray.add(el.getTextContent());	//add the break to the FestivalObject
			}
		}
		
		//get the teams and add them to the FestivalObject.teamsArray
		for(int i = 0; i < listTeamsArray.getLength(); i++) {
			node = listTeamsArray.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				el = (Element) node;
				TeamObject team = new TeamObject();		//create a new TeamObject to build
				team.setTeamID(Integer.parseInt(el.getElementsByTagName("teamID").item(0).getTextContent()));
				team.setTeamName(el.getElementsByTagName("teamName").item(0).getTextContent());
				team.setCategory(el.getElementsByTagName("category").item(0).getTextContent());
				team.setPlace(Integer.parseInt(el.getElementsByTagName("place").item(0).getTextContent()));
				team.setFirstRaceTime(Integer.parseInt(el.getElementsByTagName("firstRaceTime").item(0).getTextContent()));
				team.setSecondRaceTime(Integer.parseInt(el.getElementsByTagName("secondRaceTime").item(0).getTextContent()));
				team.setSemiFinalRaceTime(Integer.parseInt(el.getElementsByTagName("semiFinalRaceTime").item(0).getTextContent()));
				team.setFinalRaceTime(Integer.parseInt(el.getElementsByTagName("finalRaceTime").item(0).getTextContent()));
				team.setAveragedRaceTime(Integer.parseInt(el.getElementsByTagName("averagedRaceTime").item(0).getTextContent()));
				FestivalObject.teamsArray.add(team);	//add the created team to the array list in FestivalObject
			}
		}
		
		//get the races
		for(int i = 0; i < listRacesArray.getLength(); i++) {
			node = listRacesArray.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				el = (Element) node;
				RaceObject race = new RaceObject();
				race.setRaceNumber(Integer.parseInt(el.getElementsByTagName("raceNumber").item(0).getTextContent()));
				race.setRaceTime(Integer.parseInt(el.getElementsByTagName("raceTime").item(0).getTextContent()));	//set the race time
				race.setCategory(el.getElementsByTagName("category").item(0).getTextContent());		//set the category
				
				FestivalObject.racesArray.add(race);		//add the race
				
				NodeList nL = doc.getElementsByTagName("teamsRacing"); 
				
				//build the teams that raced in the race
				for(int j = 0; j < nL.getLength(); j++) {
					Node n = nL.item(j);
					Element elem = (Element) n;
					TeamObject team = new TeamObject();
					team.setTeamID(Integer.parseInt(elem.getElementsByTagName("teamID").item(0).getTextContent()));
					team.setTeamName(elem.getElementsByTagName("teamName").item(0).getTextContent());
					team.setCategory(elem.getElementsByTagName("category").item(0).getTextContent());
					team.setPlace(Integer.parseInt(elem.getElementsByTagName("place").item(0).getTextContent()));
					team.setFirstRaceTime(Integer.parseInt(elem.getElementsByTagName("firstRaceTime").item(0).getTextContent()));
					team.setSecondRaceTime(Integer.parseInt(elem.getElementsByTagName("secondRaceTime").item(0).getTextContent()));
					team.setSemiFinalRaceTime(Integer.parseInt(elem.getElementsByTagName("semiFinalRaceTime").item(0).getTextContent()));
					team.setFinalRaceTime(Integer.parseInt(elem.getElementsByTagName("finalRaceTime").item(0).getTextContent()));
					team.setAveragedRaceTime(Integer.parseInt(elem.getElementsByTagName("averagedRaceTime").item(0).getTextContent()));
					FestivalObject.racesArray.get(i).getTeamsRacing().add(team);	//add the created team to the race teams
				}
			}
		}
	}
	
}
