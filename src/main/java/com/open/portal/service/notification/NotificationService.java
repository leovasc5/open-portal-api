package com.open.portal.service.notification;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.open.portal.domain.channel.Channel;
import com.open.portal.domain.form.Form;
import com.open.portal.domain.notification.Notification;
import com.open.portal.domain.user.User;
import com.open.portal.repository.NotificationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification(Form form, User user, Channel channel) {
        String receiverName = user != null ? user.getName() : "E-mail";

        log.info("Starting creation of notification from : " + receiverName + " at channel: " 
                + channel.getName() + " from form: " + form.getId());

        Notification notification = Notification.builder()
                .dateTime(LocalDateTime.now())
                .form(form)
                .user(user)
                .channel(channel)
                .build();

        notificationRepository.save(notification);
    }
}