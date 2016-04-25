package org.rssms.service;

import javax.ejb.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by WRKSPACE2 on 3/18/2016.
 */

@Singleton
public class PropertyServiceBean implements org.rssms.service.interfaces.PropertyService {

    @Override
    public Properties getProperties(String propertyFile) {
        Properties properties = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
