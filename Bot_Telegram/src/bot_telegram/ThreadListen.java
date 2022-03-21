/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bot_telegram;

import APITelegram.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author marcello
 */
public class ThreadListen extends Thread {

    private final ApiTelegram bot;
    private final String APIToken;
    private final List_ClassJson listCj;
    private String input;
    private String citta;
    private final XMLClass xml;
    private String username;
    private String id;
    private String latitudine;
    private String longitudine;
    private final GestioneFile fileDati;

    public ThreadListen() throws IOException {
        APIToken = "5118962240:AAEEF9fGjOdebIFacVwx3Tnwhtc8Oh5aiug";
        bot = new ApiTelegram(APIToken);
        listCj = new List_ClassJson();
        input = "";
        citta = "";
        xml = new XMLClass();
        username = "";
        id = "";
        latitudine = "";
        longitudine = "";
        fileDati = new GestioneFile();
    }

    @Override
    public void run() {
        while (true) {
            // getUpdates per controllare se sono arrivati messaggi
            try {
                bot.getUpdates();
            } catch (IOException ex) {
                Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (!bot.listaUtenti.vuota()) {  // Se ci sono nuovi messaggi
                // Invio citta inserite a openstreetmap e ricevo la latitudine e la longitudine
                for (int i = 0; i < bot.listaUtenti.lunghezza(); i++) {
                    input = bot.listaUtenti.getOggetto(i).text;

                    // Controllo sintassi comando
                    if (input.length() > 5 && "/citta".equals(input.substring(0, 6))) {
                        citta = input.substring(7, input.length());
                        username = bot.cj.username;
                        id = bot.cj.id;

                        // Invio citta ad openstreetmap
                        try {
                            xml.sendMessage(citta);
                        } catch (IOException | ParserConfigurationException | SAXException ex) {
                            Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        // ricevo latitudine e longitudine
                        latitudine = xml.latitudine;
                        longitudine = xml.longitudine;

                        // Salvataggio dati su file .csv
                        Utente u = new Utente(id, username, latitudine, longitudine);
                        try {
                            fileDati.salva(u.toCSV());
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        bot.incUpdate_id();

                    } else if (input.equals("/start")) {
                        try {
                            bot.sendMessage("Benvenuto, per iniziare inserire il comando '/citta' seguito dal nome della citta che si desidera");
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        bot.incUpdate_id();
                    } else {
                        try {
                            bot.sendMessage("Sintassi non corretta");
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            } else {
                try {
                    bot.sendMessage("Nessun nuovo messaggio");
                } catch (IOException ex) {
                    Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
