/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APITelegram;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author marcello
 */
public class Pubblicita {
    
    private String nome;
    private String citta;
    private String raggio;
    private String testo;
    private double lat;
    private double lon;
    private ApiTelegram bot;
    
    public Pubblicita() {
    }
    
    public Pubblicita(String nome, String citta, String raggio, String testo) throws IOException, ParserConfigurationException, SAXException {
	this.nome = nome;
	this.citta = citta;
	this.raggio = raggio;
	this.testo = testo;
	
	XMLClass xml = new XMLClass();
	xml.sendMessage(citta);
	lat = Double.parseDouble(xml.latitudine);
	lon = Double.parseDouble(xml.longitudine);
	
	bot = new ApiTelegram("5118962240:AAEEF9fGjOdebIFacVwx3Tnwhtc8Oh5aiug");
    }
    
    public void invio(Utenti listaUtenti) throws IOException {
	for (int i = 0; i < listaUtenti.lista.size(); i++) {
	    Utente u = listaUtenti.getUtente(i);
	    double latUtente = Double.parseDouble(u.lat);
	    double lonUtente = Double.parseDouble(u.lon);
	    
	    double dist = calcoloDistanza(latUtente, lonUtente);
	    bot.sendMessage(String.valueOf("Distanza: " + dist));
//	    if (dist < Double.parseDouble(raggio)) {
//		bot.sendMessage(testo);
//	    }
	}
    }
    
    public double calcoloDistanza(double latUtente, double lonUtente) {
	
	double p = 0.017453292519943295;    // Math.PI / 180
	double a = 0.5 - Math.cos((latUtente - lat) * p) / 2
		+ Math.cos(lat * p) * Math.cos(latUtente * p)
		* (1 - Math.cos((lonUtente - lon) * p)) / 2;
	
	return 12742 * Math.asin(Math.sqrt(a)); // 2 * R; R = 6371 km
    }
    
    @Override
    public String toString() {
	return "Pubblicita:\n" + "nome:" + nome + "\ncitta:" + citta + "\ntesto:" + testo + "\n";
    }

//    public double gradiToRadianti(double gradi) {
//	return deg * (Math.PI / 180);
//    }
    // VERSIONE 1
    /*double r = 6371;
	double gLat = gradiToRadianti(latUtente - lat);
	double gLon = gradiToRadianti(lonUtente - lon);

	double a = Math.sin(gLat / 2) * Math.sin(dLat / 2)
		+ Math.cos(gradiToRadianti(lat)) * Math.cos(gradiToRadianti(latUtente))
		* Math.sin(gLat / 2) * Math.sin(gLon / 2);

	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	double d = r * c; // Distance in km
	return d;

function deg2rad(deg) {
  return deg * (Math.PI/180)
}
     */
    // VERSIONE 2
    /*
    var p = 0.017453292519943295;    // Math.PI / 180
  var c = Math.cos;
  var a = 0.5 - c((lat2 - lat1) * p)/2 + 
          c(lat1 * p) * c(lat2 * p) * 
          (1 - c((lon2 - lon1) * p))/2;

  return 12742 * Math.asin(Math.sqrt(a)); // 2 * R; R = 6371 km
     */
}
