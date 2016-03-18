package org.rssms.service;

import org.rssms.service.interfaces.IMailService;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by WRKSPACE2 on 3/13/2016.
 */

@Singleton
public class MailService implements IMailService {

    private PropertyService propertyService;

    @Inject
    public void setPropertyService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Method used to send email messages using SMTP protocol and gmail as host
    @Override
    public void sendMail(String address, String subject, String body) {
        Properties properties = propertyService.getProperties("mail.properties");
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.user")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
            message.setSubject(subject);
            message.setText(body);

            Transport transport = session.getTransport("smtp");
            transport.connect(properties.getProperty("mail.smtp.host"),
                                properties.getProperty("mail.smtp.user"),
                                properties.getProperty("mail.smtp.password"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
