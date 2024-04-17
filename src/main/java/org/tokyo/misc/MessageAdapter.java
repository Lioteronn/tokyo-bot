package org.tokyo.misc;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.tokyo.settings.Parameters;

import java.util.EventListener;

public class MessageAdapter extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        Message receivedMessage = event.getMessage();
        MessageChannel channel = event.getChannel();

        String rawMessage = receivedMessage.getContentRaw();

        if (channel.getId().equals(Parameters.getAdapterChannelId()) && !event.getAuthor().isBot() && Parameters.isAdapterOn()) {

            event.getMessage().delete().queue();

            String sentBy = "Sentencia enviada por: ***" + event.getAuthor().getName() + "***.";
            String formattedMessage = "```sql\n" + rawMessage + "\n```";

            channel.sendMessage(sentBy).queue();
            channel.sendMessage(formattedMessage).queue();

        }
    }

}
