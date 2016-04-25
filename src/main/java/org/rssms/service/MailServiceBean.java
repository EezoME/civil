package org.rssms.service;

import org.rssms.service.interfaces.PropertyService;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by WRKSPACE2 on 3/13/2016.
 */

@Singleton
public class MailServiceBean implements org.rssms.service.interfaces.MailService {

    private PropertyService propertyService;

    @EJB
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
