package com.open.portal.integration.channel;

import java.io.IOException;
import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.open.portal.domain.form.Form;
import com.open.portal.domain.user.User;
import com.open.portal.integration.NotificationChannel;

import javax.mail.*;

@Component
public class EmailChannel implements NotificationChannel {
    private static String host;
    private static String username;
    private static String password;
    private static String frontendUrl;

    @Value("${email.host}")
    private void setHost(String host) {
        this.host = host;
    }

    @Value("${email.username}")
    private void setUsername(String username) {
        this.username = username;
    }

    @Value("${email.password}")
    private void setPassword(String password) {
        this.password = password;
    }

    @Value("${frontend.url}")
    private void setFrotendUrl(String frontendUrl) {
        this.frontendUrl = frontendUrl;
    }

    @Override
    public void sendNotification(Form form, List<User> receivers) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setClassForTemplateLoading(getClass(), "/email");
        configuration.setDefaultEncoding("UTF-8");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy 'às' HH:mm", new Locale("pt", "BR"));
        String formattedDate = form.getDateTime().format(formatter);
        String link = frontendUrl + "/form/" + form.getId();

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Template template = configuration.getTemplate("form_received.html");

            for (User receiver : receivers) {
                Map<String, Object> model = new HashMap<>();
                model.put("form", form);
                model.put("date", formattedDate);
                model.put(link, "link");

                StringWriter content = new StringWriter();
                template.process(model, content);

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                
                message.setSubject("Novo formulário recebido!");
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver.getEmail()));
                message.setContent(content.toString(), "text/html; charset=utf-8");

                Transport.send(message);
            }
        } catch (IOException | TemplateException | MessagingException e) {
            throw new RuntimeException(e);
        }

        return;
    }
}