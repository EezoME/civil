package org.rssms.service;

import org.rssms.service.interfaces.IPropertyService;

import javax.ejb.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by WRKSPACE2 on 3/18/2016.
 */

@Singleton
public class PropertyService implements IPropertyService {
    @Override
    public Properties getProperties(String propertyFile) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {   // T_T
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
