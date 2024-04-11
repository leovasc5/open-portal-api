package com.open.portal.integration.channel;

import com.open.portal.api.exception.http.ServiceUnavailableException;
import com.open.portal.domain.form.Form;
import com.open.portal.domain.user.User;
import com.open.portal.integration.NotificationChannel;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SlackChannel implements NotificationChannel {
    private static String slackToken;        
    private static String slackChannelCode;

    @Value("${slack.authorization}")
    private void setSlackToken(String slackToken) {
        this.slackToken = slackToken;
    }

    @Value("${slack.channel.code}")
    private void setSlackChannelCode(String slackChannelCode) {
        this.slackChannelCode = slackChannelCode;
    }

    @Override
    public void sendNotification(Form form, List<User> receivers) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://slack.com/api/chat.postMessage");

        httpPost.addHeader("Authorization", slackToken);

        StringBuilder messageText = new StringBuilder();
        messageText.append("New message from " + form.getAuthorName()  + "\n\n" );
        messageText.append("Role: " + form.getAuthorRole() + "\n");
        messageText.append("Company: " + form.getBusinessName() + "\n");
        messageText.append("Category: " + form.getCategory().getName() + "\n");
        messageText.append("City: " + form.getCity().getName() + "\n");
        messageText.append("E-mail: " + form.getContactEmail() + "\n\n");
        messageText.append("Message: " + form.getDescription());

        String jsonBody = "{\"channel\":\"" + slackChannelCode + "\",\"text\":\"" + messageText + "\"}";

        httpPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

        try {
            HttpResponse response = httpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
            log.error("Error executing HTTP request: " + e.getMessage());
            e.printStackTrace();

            throw new ServiceUnavailableException("Serviço indisponível.");
        } catch (java.io.IOException e) {
            log.error("Error executing HTTP request: " + e.getMessage());
            e.printStackTrace();

            throw new ServiceUnavailableException("Serviço indisponível.");
        }

        return;
    }
}
