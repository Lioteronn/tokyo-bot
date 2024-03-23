package org.tokyo.misc;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CustomChannel extends ListenerAdapter {

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {

        AudioChannel voiceChannelJoined = event.getChannelJoined();
        AudioChannel voiceChannelLeft = event.getChannelLeft();
        Member user = event.getMember();

        if (voiceChannelJoined != null && voiceChannelJoined.getId().equals("1220376289786069175")) {
            String username = user.getEffectiveName();

            voiceChannelJoined.createCopy()
                    .setName("Canal de " + username)
                    .setUserlimit(5)
                    .queue((channel) -> {
                        user.getGuild().moveVoiceMember(user, (VoiceChannel) channel)
                                .queue();
                    });

        }

        if (voiceChannelLeft != null && !voiceChannelLeft.getId().equals("1220376289786069175")
                && voiceChannelLeft.getName().startsWith("Canal de")
                && voiceChannelLeft.getMembers().isEmpty()) {
            voiceChannelLeft.delete().queue();
        }

    }

}
