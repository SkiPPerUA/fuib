package test.backTests.payHub.regressTrans;

import io.restassured.RestAssured;
import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.Payer_constructor;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C2C;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C2Cregress extends BaseTest {

    int sleep = 20000;

    @Test
    public void testC2CPan3DS2OnlyMandatoryFieldsReceiverPan() throws JSONException, InterruptedException {
        String body = "\"amount\": 333,\n" +
                "    \"commission\": 0,\n" +
                "    \"client_ip\": \"0.1.1.4\",\n" +
                "    \"currency\": 980,\n" +
                "    \"authentication\":{\n" +
                "      \"device_id\":\"device_idVladTest\",\n" +
                "      \"session_id\":\"idVladTest\",\n" +
                "      \"ip\":\"79.110.129.18\",\n" +
                "      \"event_type\":\"APP_A2C\"\n" +
                "   }," +
                "    \"description\": \"description\",\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(Card.FUIB_MC))+","+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                //"        \"iban\": \"UA973348510100026201112609802\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.PROSTIR,Card_param.pan)+"\"\n" +
                "    },\n" +
                ThreeDS.threeDS_2_2_0;

        C2C c2c = new C2C(body, 2);
        Thread.sleep(sleep);
        c2c.status();
        JSONObject json = new JSONObject(c2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PROCESSED");
    }

    C2C c2c = new C2C();

    @Test
    public void hidden_frame() throws InterruptedException {
        String trans = "507fd3c2-c399-40bc-b0a9-bd408364d22e";
        String ss = "eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImQwYjIyNjYwLTFmN2ItNDFmMC05MDIxLWIxNzljZTA2OTQ5ZiJ9";

        RestAssured.useRelaxedHTTPSValidation();
        c2c.agreeHidden(trans, ss);
        Map data = wait_hidden_frame(trans);
        c2c.setTransactionId(trans);
        ThreeDS.createIFrame((String) data.get("acs_url"), (String) data.get("c_req"));
        c2c.agree3DS();
    }

    private Map<String,String> wait_hidden_frame(String trans) throws InterruptedException {
        Thread.sleep(5000);
        String creq = "";
        String url = "";
        Map<String,String> data = new HashMap<>();
        JSONObject json = new JSONObject(c2c.getStatus(trans));
        creq = json.getJSONObject("data").getJSONObject("3ds_info").getString("c_req");
        url = json.getJSONObject("data").getJSONObject("3ds_info").getString("acs_url");
        data.put("acs_url", url);
        data.put("c_req", creq);
        return data;
    }

    @Test
    public void testC2CPanOnlyMandatoryFieldsReceiverPan() throws JSONException, InterruptedException {
        String body = "\"amount\": 220,\n" +
                "    \"commission\": 0,\n" +
                "    \"currency\": 980,\n" +
                "    \"description\": \"description\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.expire)+"\",\n" +
                "        \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    },\n" +
                ThreeDS.threeDS_2_1_0;

        C2C c2c = new C2C(body, 0);
        JSONObject json = new JSONObject(c2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c2c.status();
    }

    @Test
    public void testC2CWallet3DS2OnlyMandatoryFieldsReceiverPan() throws JSONException, InterruptedException {
        logStartTest("testC2CWallet3DS2OnlyMandatoryFieldsReceiverPan");
        String body = " \"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "       \"source\": \"WALLET\",\n" +
                "        \"value\": \"90b8aa53-f927-4a03-ac00-d4728b36404b\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "    \"threed\": {\n" +
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
                "    }"+
                "    }";

        C2C c2c = new C2C(body);
        JSONObject json = new JSONObject(c2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c2c.status();
    }

    @Test
    public void status(){
        C2C c2c = new C2C();
        c2c.status("6490b30b-65f0-45be-bcea-fe87dc393da1");
    }

}
