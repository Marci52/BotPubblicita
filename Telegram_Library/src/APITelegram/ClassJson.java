/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APITelegram;

import org.json.JSONObject;

/**
 *
 * @author marcello
 */
public class ClassJson {

    public String id;
    public String username;
    public String text;

    public ClassJson() {
	id = "";
	username = "";
	text = "";
    }

    public ClassJson(String id, String username, String text) {
	this.id = id;
	this.username = username;
	this.text = text;
    }

    // Assegnazione valori letti da un oggetto JSON
    public void setClassJson(JSONObject obj) {
	id = obj.getJSONObject("result").getJSONObject("chat").getString("id");
	username = obj.getJSONObject("result").getJSONObject("chat").getString("username");
	text = obj.getJSONObject("result").getString("text");
    }

    // Assegnazione valori letti da un vettore JSON
    public ClassJson fromJSONArray(JSONObject obj, int i) {
	id = String.valueOf((obj.getJSONArray("result").getJSONObject(i).getJSONObject("message").getJSONObject("chat").getInt("id")));
	username = obj.getJSONArray("result").getJSONObject(i).getJSONObject("message").getJSONObject("chat").getString("username");
	text = obj.getJSONArray("result").getJSONObject(i).getJSONObject("message").getString("text");
	return new ClassJson(id, username, text);
    }

    // Restituizione update_id
    public long getUpdate_id(JSONObject obj) {
	return obj.getJSONArray("result").getJSONObject(0).getLong("update_id");
    }
}
