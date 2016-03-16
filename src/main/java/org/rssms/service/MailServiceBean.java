package org.rssms.service;

import org.rssms.service.interfaces.MailService;

import javax.ejb.Singleton;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Created by WRKSPACE2 on 3/13/2016.
 */

@Singleton
public class MailServiceBean implements MailService {

    // Method used to send email messages using SMTP protocol and gmail as host
    @Override
    public void sendMail(String address, String subject, String body) {
        Properties properties = System.getProperties();

        String host = "smtp.gmail.com";
        String from = "user@gmail.com";
        String password = "123456";

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
            message.setSubject(subject);
            message.setText(body);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
