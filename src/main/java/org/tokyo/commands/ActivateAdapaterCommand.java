package org.tokyo.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.tokyo.settings.Parameters;

import java.awt.*;

public class ActivateAdapaterCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        Guild guild = e.getGuild();
        User user = e.getUser();

        if (e.getName().equals("activar") && user.getId().equals(guild.getOwnerId())) {
            if (Parameters.isAdapterOn()) {
                e.reply("El adaptador de mensajes ya está activado.").queue();

                return;
            }

            Parameters.setAdapterOn(true);

            e.replyEmbeds(createEmbed("ADAPTADOR DE MENSAJES ACTIVADO", new Color(102, 255, 153), "Para desactivar use el comando /desactivar."))
                    .queue();
        }

        if (e.getName().equals("desactivar") && user.getId().equals("179652367971516416")) {
            if (!Parameters.isAdapterOn()) {
                e.reply("El adaptador de mensajes ya está desactivado.").queue();

                return;
            }

            Parameters.setAdapterOn(false);
            e.replyEmbeds(createEmbed("ADAPTADOR DE MENSAJES DESACTIVADO", new Color(255, 80, 80), "Para activar use el comando /activar."))
                    .queue();
        }
    }

    private MessageEmbed createEmbed(String title, Color color, String footer) {
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle(title)
                .setColor(color)
                .setFooter(footer);

        return eb.build();
    }

}
