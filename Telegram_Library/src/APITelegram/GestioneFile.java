/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APITelegram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author marcello
 */
public class GestioneFile {

    private File file;
    private BufferedWriter out;
    private BufferedReader in;
    private String contenuto;
    public Utenti listaUtenti;

    public GestioneFile() throws IOException {
	listaUtenti = new Utenti();
	leggi();
    }

    public void leggi() throws IOException {
	file = new File("dati.csv");
	// Se il file non esiste viene creato
	if (!file.exists()) {
	    file.createNewFile();
	}

	// Viene letto il file .csv e vengono copiati i suoi contenuti nella variabile 'contenuto'
	in = new BufferedReader(new FileReader(file));
	contenuto = "";
	String temp = "";
	while (temp != null) {
	    temp = in.readLine();
	    if (temp != null) {
		contenuto += temp + "\n";
	    }
	}
	if (!"".equals(contenuto)) {
	    listaUtenti.utentiFromCSV(contenuto);
	}
	in.close();
    }

    // Viene aggiunta una string al file .csv
    public void salva(String input) throws IOException {
	leggi();
	out = new BufferedWriter(new FileWriter(file));
	// Se l'username è già presente, elimino i vecchi dati e inserisco i nuovi
	Utente utente = new Utente();
	// Cast da CSV a Utente
	utente = utente.utenteFromCSV(input);
	// Controllo username gia presente
	int i = listaUtenti.trova(utente.username);
	if (i >= 0) {
	    // Eliminazione vecchio utente e viene aggiunto il nuovo
	    listaUtenti.aggiorna(i, utente);
	} else {
	    listaUtenti.add(utente);
	}

	// Cast da Utenti a String
	String dati = listaUtenti.toCSV();

	// Scrittura su file
	out.write(dati);
	out.close();
    }
}
