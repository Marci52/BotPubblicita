/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APITelegram;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author marcello
 */
public class Utenti {

    public ArrayList<Utente> lista;

    public Utenti() {
	lista = new ArrayList();
    }

    public void add(Utente u) {
	lista.add(u);
    }
    
    public Utente getUtente(int i){
	return lista.get(i);
    }
}
