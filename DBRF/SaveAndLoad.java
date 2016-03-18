package DBRF;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
		
		Element el = doc.createElement("Dev");
		doc.appendChild(el);
		
		Attr attr = doc.createAttribute("ID");
		attr.setValue("1");
		el.setAttributeNode(attr);
		
		Element name = doc.createElement("Name");
		name.appendChild(doc.createTextNode("Sahil"));
		el.appendChild(name);
		
		Element surname = doc.createElement("SurName");
		surname.appendChild(doc.createTextNode("Chunky"));
		el.appendChild(surname);
		
		Element age = doc.createElement("Age");
		age.appendChild(doc.createTextNode("21"));
		el.appendChild(age);
		
		TransformerFactory tF = TransformerFactory.newInstance();
		Transformer tran = tF.newTransformer();
		DOMSource src = new DOMSource(doc);
		
		StreamResult sR = new StreamResult(new File("C:\\Users\\David van de Kamp\\Desktop\\festival.xml"));
		
		tran.transform(src, sR);
	}
	
	public static void loadXML() throws Exception {
		File xmlFile = new File("C:\\Users\\David van de Kamp\\Desktop\\festival.xml");
		
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dBF.newDocumentBuilder();
		Document doc = dB.parse(xmlFile);
		
		NodeList list = doc.getElementsByTagName("Dev");
		
		for(int i =0; i < list.getLength(); i++) {
			Node node = list.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element el = (Element) node;
				System.out.println("ID: " + el.getAttribute("ID"));
				System.out.println("Name: " + el.getElementsByTagName("Name").item(0).getTextContent());
				System.out.println("SurName: " + el.getElementsByTagName("SurName").item(0).getTextContent());
				System.out.println("Age: " + el.getElementsByTagName("Age").item(0).getTextContent());
			}
		}
	}
	
}
