/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APITelegram;

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

    public Utente getUtente(int i) {
	return lista.get(i);
    }

    public void cancella(int i) {
	lista.remove(i);
    }

    @Override
    public String toString() {
	return "Utenti{" + "lista=" + lista + '}';
    }

    public void utentiFromCSV(String csv) {
	String campi[] = csv.split("\n");
	Utente u = new Utente();
	for (int i = 0; i < campi.length; i++) {
	    u = u.utenteFromCSV(campi[i]);
	    if (!lista.isEmpty()) {
		int j = trova(u.username);
		if (i >= 0) {
		    // Eliminazione vecchio utente e viene aggiunto il nuovo
		    aggiorna(i, u);
		} else {
		    add(u);
		}
	    } else {
		add(u);
	    }
	}
    }

    // Ricerca di un username all'interno della lista
    public int trova(String username) {
	boolean trovato = false;
	int i = 0;
	while (i < lista.size() && !trovato) {
	    if (username.equals(lista.get(i).username)) {
		trovato = true;
	    } else {
		i++;
	    }
	}
	if (trovato) {
	    return i;
	} else {
	    return -1;
	}
    }

    public void aggiorna(int i, Utente u) {
	lista.remove(i);
	add(u);
    }

    public String toCSV() {
	String dati = "";
	for (int i = 0; i < lista.size(); i++) {
	    dati += lista.get(i).toCSV() + "\n";
	}
	return dati;
    }

    public boolean vuota() {
	return lista.isEmpty();
    }
}
