package org.rssms.service.interfaces;

import org.rssms.entity.Project;
import org.rssms.entity.User;

import java.util.HashMap;

/**
 * Created by WRKSPACE2 on 5/25/2016.
 */
public interface LiqPayService {
    HashMap<String, String> generateLiqPayParams(Project project);
    void checkPayment(String orderId, User user, Project project) throws Exception;
}
