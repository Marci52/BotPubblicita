/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APITelegram;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
//import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author marcello
 */
public class ApiTelegram {

    private String APIToken;
    public String baseURL;
    public JSONObject obj;
    public ClassJson cj;
    public List_ClassJson listaUtenti;
    public long update_id;

    public ApiTelegram() {
    }

    public ApiTelegram(String APIToken) {
	this.APIToken = APIToken;
	baseURL = "https://api.telegram.org/bot" + APIToken + "/";
	obj = null;
	cj = new ClassJson();
	listaUtenti = new List_ClassJson();
    }

    // Lettura contenuto URL e cast da String a JSONObject
    public void parse(URL url) throws IOException {
	Scanner in = new Scanner(url.openStream());
	in.useDelimiter("\u001a");
	String jsonString = in.next();
	obj = new JSONObject(jsonString);
    }

    // Invio di un messaggio ad un utente
    public void sendMessage(String text) throws MalformedURLException, IOException {
	// """URL ENCODER"""
	text = text.replace(' ', '+');
	// Lettura ID
	String id = getID();
	if (!"".equals(id)) {
	    // Creazione URL per l'invio del messaggio
	    String String_sendURL = baseURL + "sendMessage?chat_id=" + id + "&text=" + text;
	    URL sendURL = new URL(String_sendURL);
	    sendURL.openStream();
	}
    }

    // Invio di un messaggio ad un utente
    public void sendMessage(String text, String id) throws MalformedURLException, IOException {
	// """URL ENCODER"""
	text = text.replace(' ', '+');
	// Lettura ID
	if (!"".equals(id)) {
	    // Creazione URL per l'invio del messaggio
	    String String_sendURL = baseURL + "sendMessage?chat_id=" + id + "&text=" + text;
	    URL sendURL = new URL(String_sendURL);
	    sendURL.openStream();
	}
    }

    public void getUpdates() throws MalformedURLException, IOException {
	// Creazione dell'URL
	String stringidUrl = baseURL + "getUpdates";
	if (update_id != 0) {
	    stringidUrl += "?offset=" + update_id;
	}
	URL idURL = new URL(stringidUrl);

	// Esecuzione parsing della risposta dell'URL
	parse(idURL);
	if (obj.getJSONArray("result").length() > 0) {
	    // Lettura update_id
	    update_id = cj.getUpdate_id(obj);
	    // Svuotamento listaUtenti
	    listaUtenti.svuota();
	    // Aggiunta degli utenti che hanno inviato un messaggio ad una list
	    for (int i = 0; i < obj.getJSONArray("result").length(); i++) {
		listaUtenti.aggiungi(cj.fromJSONArray(obj, i));
		// Incremento update_id
		incUpdate_id();
	    }
	} else {
	    // Svuotamento listaUtenti
	    listaUtenti.svuota();
	}
    }

//    public boolean exists() throws MalformedURLException, IOException {
//	String stringidUrl = baseURL + "getUpdates";
//	URL idURL = new URL(stringidUrl);
//	parse(idURL);
//	if (obj.getJSONArray("result").length() != 0) {
//	    return true;
//	} else {
//	    return false;
//	}
//    }
    public String getID() throws IOException {
	getUpdates();
	return cj.id;
    }

    public void incUpdate_id() {
	update_id += 1;
    }
}
