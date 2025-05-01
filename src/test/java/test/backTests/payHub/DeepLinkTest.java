package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payByLink.InvoiceBusiness;
import org.example.qaTransactionTeam.backEnd.dazzler.DeepLink;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DeepLinkTest extends BaseTest {
    String body;
    JSONObject json;
    InvoiceBusiness invoiceBusiness;
    String merchant_config_id = "fd4af5db-2a6a-4175-b6b6-41249ad7c70a";
    int amount = 274;
    DeepLink deepLink = new DeepLink();

    @Test
    void testDeepLink(){
        String timeStamp = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());

        body = "\"amount\": "+amount+",\n" +
                "  \"description\": \"Title Deep Link Влад\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \""+(Integer.parseInt(timeStamp)+1)+"-10-10T16:58:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \""+merchant_config_id+"\"\n";

        invoiceBusiness = new InvoiceBusiness(body,false);
        json = new JSONObject(invoiceBusiness.getResponse());
//        deepLink.createDeppLink(json.getString("link_id"), 432);
//        json = new JSONObject(deepLink.getResponse());
        System.out.println(json.getString("deep_link"));
    }

    @Test
    void refundDeepLink(){
//       deepLink.refundDeppLink("6653eba5-430b-414c-b744-b6048f4bec02", 100);
//       json = new JSONObject(deepLink.getResponse());
//       deepLink.refundStatusDeppLink("6653eba5-430b-414c-b744-b6048f4bec02", json.getString("refund_id"));
    }

    @Test
    void rejectdDeepLinkTest(){
        String timeStamp = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());

        body = "\"amount\": "+amount+",\n" +
                "  \"description\": \"Title Deep Link Влад\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \""+(Integer.parseInt(timeStamp)+1)+"-10-10T16:58:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \""+merchant_config_id+"\"\n";

        invoiceBusiness = new InvoiceBusiness(body,false);
        json = new JSONObject(invoiceBusiness.getResponse());
//        deepLink.createDeppLink(json.getString("link_id"), 432);
//        json = new JSONObject(deepLink.getResponse());
//        deepLink.rejectDeppLink(json.getString("id"));
    }
}
