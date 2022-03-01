/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bot_telegram;

import APITelegram.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcello
 */
public class ThreadListen extends Thread {

    private ApiTelegram bot;
    private ClassJson[] listCj;

    public ThreadListen() {
        bot = new ApiTelegram();
        listCj = new ClassJson[5];
    }

    @Override
    public void run() {
//        try {
//            bot.getUpdates();
//        } catch (IOException ex) {
//            Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        System.out.println(bot.cj.text);
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadListen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
