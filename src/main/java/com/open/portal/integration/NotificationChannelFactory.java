package com.open.portal.integration;

import java.util.ArrayList;
import java.util.List;

import com.open.portal.api.exception.http.PreconditionFailedException;
import com.open.portal.domain.channel.Channel;
import com.open.portal.integration.channel.EmailChannel;
import com.open.portal.integration.channel.SlackChannel;
import com.open.portal.integration.channel.WhatsAppChannel;

public class NotificationChannelFactory {

    public static List<NotificationChannel> createActiveChannels(List<Channel> activeChannelTypes) {
        List<NotificationChannel> activeChannels = new ArrayList<>();
        activeChannelTypes.forEach(channelType -> {
            activeChannels.add(NotificationChannelFactory.createChannel(channelType.getName()));
        });

        return activeChannels;
    }

    public static NotificationChannel createChannel(String channelType) {
        if ("whatsapp".equalsIgnoreCase(channelType)) {
            return new WhatsAppChannel();
        } else if ("slack".equalsIgnoreCase(channelType)) {
            return new SlackChannel();
        } else if ("e-mail".equalsIgnoreCase(channelType)) {
            return new EmailChannel();
        } else {
            throw new PreconditionFailedException(channelType + " não é um canal válido.");
        }
    }    
}
