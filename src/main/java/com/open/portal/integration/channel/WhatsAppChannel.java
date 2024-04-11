package com.open.portal.integration.channel;

import java.util.List;

import com.open.portal.domain.form.Form;
import com.open.portal.domain.user.User;
import com.open.portal.integration.NotificationChannel;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class WhatsAppChannel implements NotificationChannel {
    @Override
    public void sendNotification(Form form, List<User> receivers) {
        StringBuilder messageText = new StringBuilder();
        messageText.append("New message from " + form.getAuthorName()  + "\n\n" );
        messageText.append("Company: " + form.getBusinessName() + "\n");
        messageText.append("Category: " + form.getCategory().getName() + "\n");
        messageText.append("City: " + form.getCity().getName() + "\n");
        messageText.append("E-mail: " + form.getContactEmail() + "\n\n");
        messageText.append("Message: " + form.getDescription());

        // receivers.stream()
        //  .map(User::getPhoneNumber)
        //  .forEach(phoneNumber -> sendWhatsAppMessage(messageText.toString(), phoneNumber));

        return;
    }

    public void sendWhatsAppMessage(String message, String phoneNumber) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://graph.facebook.com/v13.0/<YOUR PHONE NUMBER ID>/messages"))
                .header("Authorization", "Bearer <YOUR BEARER TOKEN>")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"<TARGET PHONE NUMBER>\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
                .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request,BodyHandlers.ofString());
            System.out.println(response.body());
           
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return;
    }
}