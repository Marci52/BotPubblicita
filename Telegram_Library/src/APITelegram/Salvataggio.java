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
public class Salvataggio {

    private File file;
    private BufferedWriter out;
    private BufferedReader in;
    private String contenuto;

    public Salvataggio() throws IOException {
	file = new File("dati.csv");
	if (!file.exists()) {
	    file.createNewFile();
	}
	in = new BufferedReader(new FileReader(file));
	contenuto = "";
	String temp = "";
	while (temp != null) {
	    temp = in.readLine();
	    if (temp != null) {
		contenuto += temp + "\n";
	    }
	}
	in.close();
    }

    public void salva(String input) throws IOException {
	out = new BufferedWriter(new FileWriter(file));
	if (contenuto != null) {
	    input += "\n" + contenuto;
	}
	out.write(input);
	out.close();
    }

    public Utenti getUtenti() throws IOException {
	Utenti listaUtenti = new Utenti();

	in = new BufferedReader(new FileReader(file));
	
	String temp = "";
	while (temp != null) {
	    temp = in.readLine();
	    if (temp != null) {
		Utente u = fromCSV(temp);
		listaUtenti.add(u);
    	    }
	}	
	
	return listaUtenti;
    }

    public Utente fromCSV(String linea) {
	String campi[] = linea.split(";");

	return new Utente(campi[0], campi[1], campi[2], campi[3]);
    }
}
