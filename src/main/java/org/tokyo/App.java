package org.tokyo;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.tokyo.misc.CustomChannel;
import org.tokyo.misc.MessageAdapter;

public class App {

    public static void main( String[] args ) throws InterruptedException {

            JDA jda = JDABuilder.createDefault("MTIyMDA3OTUyMjk0NzUzNDg4OA.GK2iGN.DItdNJkEUqFyuVwRAVa8pHKu8zdWXskLiQVGXg")
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new MessageAdapter())
                    .addEventListeners(new CustomChannel())
                    .build()
                    .awaitReady();

    }



}
