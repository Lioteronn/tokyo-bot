package org.tokyo.commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.tokyo.settings.Parameters;

public class ChangeAdapterChannelCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        User user = e.getUser();
        Channel channel;

        if (e.getName().equals("cambiarcanal")) {
            channel = e.getOption("canal").getAsChannel();
            Parameters.setAdapterChannelId(channel.getId());

            e.getChannel().sendMessage("Canal cambiado a " + channel.getName() + ".").queue();
        }
    }

}
