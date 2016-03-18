package org.rssms.service.interfaces;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by WRKSPACE2 on 3/18/2016.
 */
public interface IPropertyService {

    Properties getProperties(String propertyFile) throws IOException;
}
