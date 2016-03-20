package DBRF;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SaveAndLoad {
	public static void saveXML() throws Exception {
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dBF.newDocumentBuilder();
		
		Document doc = dB.newDocument();
		
		//build the teamsArray information
		Element el = doc.createElement("teamsArray");
		doc.appendChild(el);
		
		for(int i = 0; i < TestUI.teamsArray.size(); i++) {
			
//			Attr attr = doc.createAttribute("TeamObject");
//			attr.setValue("hi");
//			el.setAttributeNode(attr);
			
			Element teamName = doc.createElement("teamName");
			teamName.appendChild(doc.createTextNode(TestUI.teamsArray.get(i).getTeamName()));
			el.appendChild(teamName);
			
			Element category = doc.createElement("category");
			category.appendChild(doc.createTextNode(TestUI.teamsArray.get(i).getCategory()));
			teamName.appendChild(category);
			
			Element place = doc.createElement("place");
			place.appendChild(doc.createTextNode(TestUI.teamsArray.get(i).getPlace()));
			teamName.appendChild(place);
			
			Element firstRaceTime = doc.createElement("firstRaceTime");
			firstRaceTime.appendChild(doc.createTextNode(Integer.toString(TestUI.teamsArray.get(i).getFirstRaceTime())));
			teamName.appendChild(firstRaceTime);
			
			Element secondRaceTime = doc.createElement("secondRaceTime");
			secondRaceTime.appendChild(doc.createTextNode(Integer.toString(TestUI.teamsArray.get(i).getSecondRaceTime())));
			teamName.appendChild(secondRaceTime);
			
			Element semiFinalRaceTime = doc.createElement("semiFinalRaceTime");
			semiFinalRaceTime.appendChild(doc.createTextNode(Integer.toString(TestUI.teamsArray.get(i).getSemiFinalRaceTime())));
			teamName.appendChild(semiFinalRaceTime);
			
			Element finalRaceTime = doc.createElement("finalRaceTime");
			finalRaceTime.appendChild(doc.createTextNode(Integer.toString(TestUI.teamsArray.get(i).getFinalRaceTime())));
			teamName.appendChild(finalRaceTime);
			
			Element averagedRaceTime = doc.createElement("averagedRaceTime");
			averagedRaceTime.appendChild(doc.createTextNode(Integer.toString(TestUI.teamsArray.get(i).getAveragedRaceTime())));
			teamName.appendChild(averagedRaceTime);
		
		}
		
		//transfor the built information into a formatted xml file
		TransformerFactory tF = TransformerFactory.newInstance();
		Transformer tran = tF.newTransformer();
		tran.setOutputProperty(OutputKeys.INDENT, "yes");	//add indenting to output xml file
		tran.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");	//adds 2 spaces to each indexed xml line
		DOMSource src = new DOMSource(doc);
		
		StreamResult sR = new StreamResult(new File("C:\\Users\\David van de Kamp\\Desktop\\festival.xml"));	//TODO - change to the festivalName
		
		tran.transform(src, sR);	//output the file
	}
	
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
