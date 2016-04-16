package DBRF;

import java.io.File;

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
		
		//start with outputting the festival name
		Element festivalName = doc.createElement("festivalName");
		festivalName.appendChild(doc.createTextNode(FestivalObject.getFestivalName()));
		doc.appendChild(festivalName);
		
		//output the start time day
		Element startTimeDay = doc.createElement("startTimeDay");
		startTimeDay.appendChild(doc.createTextNode(Integer.toString(FestivalObject.startDayTime)));
		festivalName.appendChild(startTimeDay);
		
		//output the time between races
		Element timeBetweenRaces = doc.createElement("timeBetweenRaces");
		timeBetweenRaces.appendChild(doc.createTextNode(Integer.toString(FestivalObject.timeBetweenRaces)));
		festivalName.appendChild(timeBetweenRaces);
		
		//output the number of lanes
		Element numOfLanes = doc.createElement("numOfLanes");
		numOfLanes.appendChild(doc.createTextNode(Integer.toString(FestivalObject.numOfLanes)));
		festivalName.appendChild(numOfLanes);
		
		//output the breaks array
		Element breaksArray = doc.createElement("breaksArray");
		
		
		for(int i = 0; i < FestivalObject.breaksArray.size(); i++) {
			breaksArray.appendChild(doc.createTextNode(Integer.toString(FestivalObject.getBreakList().get(i).get(0))));		//break start time
//					.appendChild(doc.createTextNode(Integer.toString(FestivalObject.getBreakList().get(i).get(1)))));		//break end time
		}
		
		festivalName.appendChild(breaksArray);
		
		//output the categories array
		Element categoriesArray = doc.createElement("categoriesArray");
		
		for(int i = 0; i < FestivalObject.categoriesArray.size(); i ++) {
			categoriesArray.appendChild(doc.createTextNode(FestivalObject.categoriesArray.get(i)));
		}
		
		festivalName.appendChild(categoriesArray);
		
		//what else do i have to output?????
		//the file path? - no, it should always be the same
		
		//build the teamsArray information
		Element teamsArray = doc.createElement("teamsArray");
		
		
		for(int i = 0; i < FestivalObject.teamsArray.size(); i++) {
			Element teamID = doc.createElement("teamID");
			teamID.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getTeamID())));
			teamsArray.appendChild(teamID);
			
			Element teamName = doc.createElement("teamName");
			teamName.appendChild(doc.createTextNode(FestivalObject.teamsArray.get(i).getTeamName()));
			teamID.appendChild(teamName);
			
			Element category = doc.createElement("category");
			category.appendChild(doc.createTextNode(FestivalObject.teamsArray.get(i).getCategory()));
			teamID.appendChild(category);
			
			Element place = doc.createElement("place");
			place.appendChild(doc.createTextNode(FestivalObject.teamsArray.get(i).getPlace()));
			teamID.appendChild(place);
			
			Element firstRaceTime = doc.createElement("firstRaceTime");
			firstRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getFirstRaceTime())));
			teamID.appendChild(firstRaceTime);
			
			Element secondRaceTime = doc.createElement("secondRaceTime");
			secondRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getSecondRaceTime())));
			teamID.appendChild(secondRaceTime);
			
			Element semiFinalRaceTime = doc.createElement("semiFinalRaceTime");
			semiFinalRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getSemiFinalRaceTime())));
			teamID.appendChild(semiFinalRaceTime);
			
			Element finalRaceTime = doc.createElement("finalRaceTime");
			finalRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getFinalRaceTime())));
			teamID.appendChild(finalRaceTime);
			
			Element averagedRaceTime = doc.createElement("averagedRaceTime");
			averagedRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getAveragedRaceTime())));
			teamID.appendChild(averagedRaceTime);
		}
		
		festivalName.appendChild(teamsArray);
		
		//output the races array
		Element racesArray = doc.createElement("racesArray");
		
		
		for(int i =0; i < FestivalObject.racesArray.size(); i ++) {
			Element raceNumber = doc.createElement("raceNumber");
			raceNumber.appendChild(doc.createTextNode(Integer.toString(FestivalObject.racesArray.get(i).getRaceNumber())));
			racesArray.appendChild(raceNumber);
			
			Element raceTime = doc.createElement("raceTime");
			raceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.racesArray.get(i).getRaceTime())));
			raceNumber.appendChild(raceTime);
			
			Element category = doc.createElement("category");
			category.appendChild(doc.createTextNode(FestivalObject.racesArray.get(i).getCategory()));
			raceNumber.appendChild(category);
			
			Element teamsRacing = doc.createElement("teamsRacing");
			
			for(int j = 0; j < FestivalObject.racesArray.get(i).getTeamsRacing().size() ; j++) {
				//all the same as outputing a regular team's information
				Element teamID = doc.createElement("teamID");
				teamID.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getTeamID())));
				teamsRacing.appendChild(teamID);
				
				Element teamName = doc.createElement("teamName");
				teamName.appendChild(doc.createTextNode(FestivalObject.teamsArray.get(i).getTeamName()));
				teamID.appendChild(teamName);
				
				Element category2 = doc.createElement("category");
				category.appendChild(doc.createTextNode(FestivalObject.teamsArray.get(i).getCategory()));
				teamID.appendChild(category);
				
				Element place = doc.createElement("place");
				place.appendChild(doc.createTextNode(FestivalObject.teamsArray.get(i).getPlace()));
				teamID.appendChild(place);
				
				Element firstRaceTime = doc.createElement("firstRaceTime");
				firstRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getFirstRaceTime())));
				teamID.appendChild(firstRaceTime);
				
				Element secondRaceTime = doc.createElement("secondRaceTime");
				secondRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getSecondRaceTime())));
				teamID.appendChild(secondRaceTime);
				
				Element semiFinalRaceTime = doc.createElement("semiFinalRaceTime");
				semiFinalRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getSemiFinalRaceTime())));
				teamID.appendChild(semiFinalRaceTime);
				
				Element finalRaceTime = doc.createElement("finalRaceTime");
				finalRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getFinalRaceTime())));
				teamID.appendChild(finalRaceTime);
				
				Element averagedRaceTime = doc.createElement("averagedRaceTime");
				averagedRaceTime.appendChild(doc.createTextNode(Integer.toString(FestivalObject.teamsArray.get(i).getAveragedRaceTime())));
				teamID.appendChild(averagedRaceTime);
			}
			
			racesArray.appendChild(teamsRacing);
		}
		
		festivalName.appendChild(racesArray);
		
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
		
		StreamResult sR = new StreamResult(new File("C:\\Users\\David van de Kamp\\Desktop\\festival.xml"));	//TODO - change to the festivalName?
		
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
		File xmlFile = new File("C:\\Users\\David van de Kamp\\Desktop\\festival.xml");
		
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dBF.newDocumentBuilder();
		Document doc = dB.parse(xmlFile);
		
//		NodeList list = doc.getElementsByTagName("Dev");
		
//		for(int i =0; i < list.getLength(); i++) {
//			Node node = list.item(i);
//			
//			if(node.getNodeType() == Node.ELEMENT_NODE) {
//				Element el = (Element) node;
//				System.out.println("ID: " + el.getAttribute("ID"));
//				System.out.println("Name: " + el.getElementsByTagName("Name").item(0).getTextContent());
//				System.out.println("SurName: " + el.getElementsByTagName("SurName").item(0).getTextContent());
//				System.out.println("Age: " + el.getElementsByTagName("Age").item(0).getTextContent());
//			}
//		}
	}
	
}
