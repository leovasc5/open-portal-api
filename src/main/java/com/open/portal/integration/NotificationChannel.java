package com.open.portal.integration;

import java.util.List;

import com.open.portal.domain.form.Form;
import com.open.portal.domain.user.User;

public interface NotificationChannel {
    void sendNotification(Form content, List<User> receivers);
}