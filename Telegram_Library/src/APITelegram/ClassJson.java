/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APITelegram;

import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author cattaneo_marcello
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

    public void setClassJson(JSONObject obj) {
	id = obj.getJSONObject("result").getJSONObject("chat").getString("id");
	username = obj.getJSONObject("result").getJSONObject("chat").getString("username");
	text = obj.getJSONObject("result").getString("text");
    }

    public void fromJSONArray(JSONObject obj) {
	id = String.valueOf((obj.getJSONArray("result").getJSONObject(0).getJSONObject("message").getJSONObject("chat").getInt("id")));
	username = obj.getJSONArray("result").getJSONObject(0).getJSONObject("message").getJSONObject("chat").getString("username");
	text = obj.getJSONArray("result").getJSONObject(0).getJSONObject("message").getString("text");
    }
}
