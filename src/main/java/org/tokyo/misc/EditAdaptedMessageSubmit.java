package org.tokyo.misc;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.tokyo.settings.Parameters;
import net.dv8tion.jda.api.JDA;
public class EditAdaptedMessageSubmit extends ListenerAdapter {

    public static JDA jda;

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String authorId = e.getAuthor().getId();
        String message;
        String messageId;
        Guild guild;

        if (e.getChannel().getType() == ChannelType.PRIVATE && !e.getAuthor().isBot()) {
            message = e.getMessage().getContentDisplay();
            messageId = EditAdaptedMessage.pendingEdits.get(e.getAuthor().getId());
            guild = jda.getGuildById(EditAdaptedMessage.guilds.get(EditAdaptedMessage.pendingEdits.get(authorId)));
            guild.getTextChannelById(Parameters.getAdapterChannelId()).retrieveMessageById(messageId).queue((msg) -> {
                msg.editMessage(message).queue();
            });
        }
    }

}
