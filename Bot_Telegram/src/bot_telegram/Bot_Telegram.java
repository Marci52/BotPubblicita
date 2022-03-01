/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bot_telegram;

/**
 *
 * @author Marcello
 */
import org.json.*;
import APITelegram.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

public class Bot_Telegram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        Scanner in = new Scanner(System.in);
//        System.out.print("Inserire APIToken: ");
//        String APIToken = in.nextLine();

        String APIToken = "5118962240:AAEEF9fGjOdebIFacVwx3Tnwhtc8Oh5aiug";
        ApiTelegram bot = new ApiTelegram(APIToken);
        bot.getID();

        if (bot.exists) {
            System.out.println("Pubblicita_5BCattaneo");
            System.out.print("Inserire messaggio da inviare: ");
            String mex = in.nextLine();
            bot.sendMessage(mex);
        } else {
            System.out.println("Bot non esistente");
        }
    }
}
