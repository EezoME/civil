package org.rssms.service.interfaces;

/**
 * Created by WRKSPACE2 on 3/13/2016.
 */
public interface IMailService {

    void sendMail(String address, String subject, String body);
}
