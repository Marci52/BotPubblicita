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
	    if (dist < Double.parseDouble(raggio)) {
		bot.sendMessage(toString());
	    }
	}
    }
    
    public float calcoloDistanza(double latUtente, double lonUtente) {
	float dist = 0;
	
	return dist;
    }
    
    @Override
    public String toString() {
	return "Pubblicita:\n" + "nome:" + nome + "\ncitta:" + citta + "\ntesto:" + testo + "\n";
    }
    
}
