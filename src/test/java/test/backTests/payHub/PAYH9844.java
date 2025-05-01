package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.AcquiringTrans;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PAYH9844 extends BaseTest {
    //Поддержка реализованного Интервейлом проведения 3DS после подтверждения

    JSONObject json;

    @Test
    public void testConfirmAcquiring(){
        logStartTest("testConfirmAcquiring");
        String body = "    \"amount\": 100,\n" +
                "    \"commission\": 20,\n" +
                "    \"merchant_config_id\":\"8fbf596b-2aae-4718-b4d1-3c37bfc46243\",\n" +
                "    \"payer\": {\n" +
                "       \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "      \"expire\": \"2212\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    },\n" +
                "     \"description\": \"тестовый платёж\",\n" +
                "    \"short_description\": \"тестовый платёж\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\",\n" +
                "\t\"3ds2_supported\": true\n";

        AcquiringTrans trans = new AcquiringTrans(body);
        trans.accept3ds2();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"APPROVAL_REQUIRED");
        trans.confirmTrans();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("testConfirmAcquiring");
    }

    @Test
    public void testDeclineAcquiring(){
        logStartTest("testDeclineAcquiring");
        String body = "    \"amount\": 100,\n" +
                "    \"commission\": 20,\n" +
                "    \"merchant_config_id\":\"8fbf596b-2aae-4718-b4d1-3c37bfc46243\",\n" +
                "    \"payer\": {\n" +
                "       \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "      \"expire\": \"2212\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    },\n" +
                "     \"description\": \"тестовый платёж\",\n" +
                "    \"short_description\": \"тестовый платёж\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\",\n" +
                "\t\"3ds2_supported\": true\n";

        AcquiringTrans trans = new AcquiringTrans(body);
        trans.accept3ds2();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"APPROVAL_REQUIRED");
        trans.declineTrans();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"FAILED");
        Assert.assertEquals(json.getJSONObject("internal").getString("extended_code"),"DECLINED_BY_EXTERNAL_SYSTEM");
        logFinishTest("testDeclineAcquiring");
    }
}
