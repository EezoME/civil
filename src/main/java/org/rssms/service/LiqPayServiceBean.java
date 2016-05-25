package org.rssms.service;

import com.liqpay.LiqPay;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.service.interfaces.DonationService;
import org.rssms.service.interfaces.LiqPayService;
import org.rssms.service.interfaces.PropertyService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by WRKSPACE2 on 5/25/2016.
 */
@Stateless
public class LiqPayServiceBean implements LiqPayService {
    private PropertyService propertyService;
    private DonationService donationService;

    @EJB
    public void setPropertyService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @EJB
    public void setDonationService(DonationService donationService) {
        this.donationService = donationService;
    }

    @Override
    public HashMap<String, String> generateLiqPayParams(Project project) {
        HashMap liqpayParams = new HashMap();
        Properties properties = propertyService.getProperties("liqpay.properties");
        HashMap params = new HashMap();
        params.put("action", "paydonate"); // User can set custom amount
        params.put("amount", "10");
        params.put("currency", "UAH");
        params.put("description", "Project support " + project.getTitle().substring(0, Math.min(project.getTitle().length(), 100)));
        params.put("order_id", "project-" + Integer.toString(project.getProjectId()) + "@" + new Date().toString());
        params.put("sandbox", "1");

        String public_key = properties.getProperty("liqpay.public_key");
        String private_key = properties.getProperty("liqpay.private_key");

        LiqPay liqpay = new LiqPay(public_key, private_key);
        String html = liqpay.cnb_form(params);

        Document doc = Jsoup.parse(html);
        Elements signature = doc.select("input[name=signature]");
        Elements data = doc.select("input[name=data]");
        liqpayParams.put("signature", signature.first().attr("value"));
        liqpayParams.put("data", data.first().attr("value"));
        return liqpayParams;
    }

    @Override
    public void checkPayment(String orderId, User user, Project project) throws Exception {
        // FIX FIX FIX !!!
        Properties properties = propertyService.getProperties("liqpay.properties");
        HashMap params = new HashMap();
        params.put("version", "3");
        params.put("order_id", orderId);

        String public_key = properties.getProperty("liqpay.public_key");
        String private_key = properties.getProperty("liqpay.private_key");

        LiqPay liqpay = new LiqPay(public_key, private_key);
        HashMap res = (HashMap) liqpay.api("payment/status", params);
        if (res.get("status").equals("sandbox") || res.get("status").equals("success")) {
            donationService.createDonation(user, (int) res.get("amount"), "", project);
        }
        //System.out.println(res.get("status"));
    }
}
