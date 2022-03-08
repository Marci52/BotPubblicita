/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APITelegram;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author marcello
 */
public class XMLClass {

    public String latitudine;
    public String longitudine;
    private String url;
    private String ris_xml;

    public XMLClass() {
	latitudine = "";
	longitudine = "";
	url = "https://nominatim.openstreetmap.org/search?q=";
	ris_xml = "";
    }

    public String getXMLfromURL(String url) throws MalformedURLException, IOException {
	Scanner in;
	String ris = "";
	URL u = new URL(url);

	in = new Scanner(u.openStream());
	in.useDelimiter("\u001a"); 

	ris = in.next();
	in.close();

	return ris;
    }

    public void sendMessage(String citta) throws IOException, ParserConfigurationException, SAXException {
	citta = citta.replace(' ', '+');
	url += citta + "&format=xml&addressdetails=1";
	ris_xml = getXMLfromURL(url);
	parseXML();
    }
    public void parseXML() throws ParserConfigurationException, org.xml.sax.SAXException, IOException {

	DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	StringReader sr = new StringReader(ris_xml);
	Document document = docBuilder.parse(new InputSource(sr));

	Element root = document.getDocumentElement();
	NodeList nl1 = root.getElementsByTagName("place");
	
	Element element1;
	element1 = (Element) nl1.item(0);
	latitudine = element1.getAttribute("lat");
	longitudine = element1.getAttribute("lon");
    }
}
