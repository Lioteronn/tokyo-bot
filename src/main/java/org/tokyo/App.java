package org.tokyo;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.tokyo.commands.ActivateAdapaterCommand;
import org.tokyo.commands.ChangeAdapterChannelCommand;
import org.tokyo.misc.CustomChannel;
import org.tokyo.misc.MessageAdapter;
import org.tokyo.settings.Token;

import java.sql.Connection;

public class App {

    public static void main( String[] args ) throws InterruptedException {

            JDA jda = JDABuilder.createDefault(Token.getToken())
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new MessageAdapter())
                    .addEventListeners(new CustomChannel())
                    .addEventListeners(new ActivateAdapaterCommand())
                    .addEventListeners(new ChangeAdapterChannelCommand())
                    .build()
                    .awaitReady();

            jda.getGuildById("1157231481765232711").updateCommands().addCommands(
                    Commands.slash("activar", "Activar adaptador de mensajes a formato SQL."),
                    Commands.slash("desactivar", "Desactivar adaptador de mensajes a formato SQL."),
                    Commands.slash("cambiarcanal", "Cambiar canal en el que se ejecutara el adaptador de mensajes a formato SQL.")
                            .addOption(OptionType.CHANNEL, "canal", "Canal en el que quieres que se ejecute.")
            ).queue();

    }



}
