/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bot_telegram;

import APITelegram.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author marcello
 */
public class ThreadListen extends Thread {

    private ApiTelegram bot;
    private String APIToken;
    private ClassJson[] listCj;
    private String input;
    private String citta;
    private XMLClass xml;
    private String username;
    private String id;
    private String latitudine;
    private String longitudine;
    private Salvataggio fileDati;

    public ThreadListen() throws IOException {
        APIToken = "5118962240:AAEEF9fGjOdebIFacVwx3Tnwhtc8Oh5aiug";
        bot = new ApiTelegram(APIToken);
        listCj = new ClassJson[5];
        input = "";
        citta = "";
        xml = new XMLClass();
        username = "";
        id = "";
        latitudine = "";
        longitudine = "";
        fileDati = new Salvataggio();
    }

    @Override
    public void run() {
        while (true) {
            try {
                bot.getUpdates();
            } catch (IOException ex) {
                Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!input.equals(bot.cj.text)) {

                input = bot.cj.text;
                if (input.length() > 5 && "/citta".equals(input.substring(0, 6))) {
                    citta = input.substring(7, input.length());
                    username = bot.cj.username;
                    id = bot.cj.id;

                    try {
                        xml.sendMessage(citta);
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SAXException ex) {
                        Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    latitudine = xml.latitudine;
                    longitudine = xml.longitudine;
                    Utente u = new Utente(id, username, latitudine, longitudine);

                    try {
                        // Salvatagio su file
                        fileDati.salva(u.toCSV());
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                        bot.sendMessage("Sintassi non corretta");
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
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
