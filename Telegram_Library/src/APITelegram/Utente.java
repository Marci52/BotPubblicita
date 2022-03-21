/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APITelegram;

/**
 *
 * @author marcello
 */
public class Utente {

    public String id;
    public String username;
    public String lat;
    public String lon;

    public Utente() {
    }

    public Utente(String id, String username, String lat, String lon) {
	this.id = id;
	this.username = username;
	this.lat = lat;
	this.lon = lon;
    }

    public String toCSV() {
	return id + ";" + username + ";" + lat + ";" + lon + ";";
    }

    public Utente utenteFromCSV(String linea) {
	String campi[] = linea.split(";");
	return new Utente(campi[0], campi[1], campi[2], campi[3]);
    }

    @Override
    public String toString() {
	return "Utente{" + "id=" + id + ", username=" + username + ", lat=" + lat + ", lon=" + lon + '}';
    }

}
