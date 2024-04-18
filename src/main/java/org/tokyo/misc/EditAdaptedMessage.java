package org.tokyo.misc;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.tokyo.settings.Parameters;

import java.util.HashMap;

public class EditAdaptedMessage extends ListenerAdapter {

    public static HashMap<String, String> pendingEdits = new HashMap<>();
    public static HashMap<String, String> guilds = new HashMap<>();

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        User user = e.getUser();
        MessageHistory messageHistory;
        String sentBy;
        String authorName;
        String adaptedMessage;

        if (e.getMessageAuthorId().equals(e.getJDA().getSelfUser().getId()) && e.getChannel().getId().equals(Parameters.getAdapterChannelId())) {
            messageHistory = MessageHistory.getHistoryBefore(e.getChannel(), e.getMessageId()).limit(3).complete();
            sentBy = messageHistory.retrievePast(2).complete().get(1)
                    .getContentDisplay();
            authorName = sentBy.substring(23, sentBy.length() - 1).replace("***", "");
            adaptedMessage = e.getChannel().retrieveMessageById(e.getMessageId()).complete().getContentDisplay();
        } else {
            return;
        }

        if (pendingEdits.get(user.getId()) != null) {
            sendPrivateMessage(user, "Termina primero de editar el mensaje anterior!");
            return;
        }

        if (user.getName().equals(authorName)) {
            pendingEdits.put(user.getId(), e.getMessageId());
            guilds.put(e.getMessageId(), e.getGuild().getId());
            sendPrivateMessage(user, "Estas editando el siguiente mensaje:");
            sendPrivateMessage(user, adaptedMessage);
            sendPrivateMessage(user, "Envía lo que quieres que sea el nuevo contenido del mensaje.");
        }
    }

    private void sendPrivateMessage(User user, String content) {
        user.openPrivateChannel()
                .flatMap(dmChannel -> dmChannel.sendMessage(content))
                .queue();
    }

}
