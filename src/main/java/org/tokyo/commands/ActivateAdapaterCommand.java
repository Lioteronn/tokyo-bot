package org.tokyo.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.tokyo.settings.Parameters;

public class ActivateAdapaterCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        MessageChannel channel = e.getChannel();
        Guild guild = e.getGuild();
        User user = e.getUser();

        if (e.getName().equals("activar") && user.getId().equals(guild.getOwnerId())) {
            if (Parameters.isAdapterOn()) {
                channel.sendMessage("El adaptador de mensajes ya está activado.").queue();

                return;
            }

            Parameters.setAdapterOn(true);
            channel.sendMessage("Adaptador de mensajes activado.").queue();
        }

        if (e.getName().equals("desactivar") && user.getId().equals("179652367971516416")) {
            if (!Parameters.isAdapterOn()) {
                channel.sendMessage("El adaptador de mensajes ya está desactivado.").queue();

                return;
            }

            Parameters.setAdapterOn(false);
            channel.sendMessage("Adaptador de mensajes desactivado.").queue();
        }
    }

}
