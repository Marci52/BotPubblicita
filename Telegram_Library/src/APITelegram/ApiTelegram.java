/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APITelegram;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
//import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Marcello
 */
public class ApiTelegram {

    private String APIToken;
    public String baseURL;
    public JSONObject obj;
    public boolean exists;
    private ClassJson cj;

    public ApiTelegram() {
    }

    public ApiTelegram(String APIToken) {
	this.APIToken = APIToken;
	baseURL = "https://api.telegram.org/bot" + APIToken + "/";
	obj = null;
	exists = false;
	cj = new ClassJson();
    }

    public void parse(URL url) throws IOException {
	Scanner in = new Scanner(url.openStream());
	in.useDelimiter("\u001a");
	String jsonString = in.next();
	obj = new JSONObject(jsonString);
    }

    public void sendMessage(String text) throws MalformedURLException, IOException {
	text = text.replace(' ', '+');
	String id = getID();
	String String_sendURL = baseURL + "sendMessage?chat_id=" + id + "&text=" + text;
	URL sendURL = new URL(String_sendURL);
	sendURL.openStream();
    }

    public String getID() throws MalformedURLException, IOException {
	String stringidUrl = baseURL + "getUpdates";
	URL idURL = new URL(stringidUrl);
	parse(idURL);
	if (obj.getJSONArray("result").length() != 0) {
	    exists = true;
	    cj.fromJSONArray(obj);
	    return cj.id;
	} else {
	    return null;
	}
    }
}
