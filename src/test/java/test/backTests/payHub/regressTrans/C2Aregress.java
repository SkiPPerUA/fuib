package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.Payer_constructor;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C2A;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class C2Aregress extends BaseTest {

    int sleep = 20000;
    Transaction c2a;

    @Test
    public void testC2APan() throws JSONException, InterruptedException, IOException {
        String body = " \"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"client_ip\": \"0.1.1.4\",\n" +
                "    \"authentication\":{\n" +
                "      \"device_id\":\"device_idVladTest\",\n" +
                "      \"session_id\":\"idVladTest\",\n" +
                "      \"ip\":\"79.110.129.18\",\n" +
                "      \"event_type\":\"APP_A2C\"\n" +
                "   }," +
                "    \"description\": \"c2a - пумб мастер\",\n" +
                "    \"threed\": {\n" +
                "        \"version\": \"2.2.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"javascript_enabled\": true,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },"+
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA953348510000026201112609803\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "   \"requirements\": " +
                "     {\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"amount\":\"1213\",\n" +
                "         \"account_number\":\"UA953348510000026201112609803\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"account_number\":\"UA953348510000026201112609803\",\n" +
                "         \"city\":\"Kyiv\",\n" +
                "         \"country\":\"UKR\",\n" +
                "         \"address\":\"street\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111221222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"07\",\n" +
                "         \"submerchant_url\":\"https://jira.fuib.com/projects/PAYH/issues/PAYH-23149?filter=myopenissues\",\n" +
                "         \"independent_sales_organization_id\":\"3016715233\"\n" +
                "      }\n" +
                "      }},\n"+
                Payer_constructor.PAN_payer(Cards_data.getData(Card.PROSTIR));

        c2a = new C2A(body,0);
        Thread.sleep(sleep);
        c2a.status();
        JSONObject json = new JSONObject(c2a.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        //c2a.refund(50);
        //Thread.sleep(10000);
        //c2a.statusRefund();
    }

    @Test
    public void testC2APan3DS2() throws JSONException, InterruptedException, IOException {
        logStartTest("testC2APan3DS2");
        String body = " \"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },"+
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.MONO_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2409\",\n" +
                "        \"cvv\": \"210\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    }";

        C2A c2a = new C2A(body,2);
        Thread.sleep(sleep);
        c2a.status();
        JSONObject json = new JSONObject(c2a.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("testC2APan3DS2");
    }
}
