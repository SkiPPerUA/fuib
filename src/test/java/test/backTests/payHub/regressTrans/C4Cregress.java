package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.Payer_constructor;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C4C;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C4Cregress extends BaseTest {

    int summ = 143;
    int commissoin = 0;
    int sleep = 20000;
    C4C c4c;

    @Test
    public void testC4CPan3DS2AllFieldsRecieverPAN() throws JSONException, InterruptedException {
        String body =
                "    \"amount\": 1000,\n" +
                "    \"client_ip\": \"0.1.1.4\",\n" +
                "    \"commission\": 100,\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "\t\"without_confirmation\":true,\n" +
                "    \"currency\": 980,\n" +
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
                ThreeDS.threeDS_2_2_0;

        C4C c4c = new C4C(body,2);
        Thread.sleep(sleep);
        c4c.status();
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        c4c.enroll(500);
        Thread.sleep(sleep);
        c4c.statusEnroll();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ENROLLED");
        c4c.refund(600);
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        c4c.status();
    }

    @Test
    public void testC4CWallet3DS2AllFieldsRecieverPAN() throws JSONException, InterruptedException {
        logStartTest("testC4CWallet3DS2AllFieldsRecieverPAN");
        String body = " \"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "\"commission\": 10,\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\","+
                "    \"payer\": {\n" +
                "       \"source\": \"WALLET\",\n" +
                "        \"value\": \"90b8aa53-f927-4a03-ac00-d4728b36404b\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        },\n" +
                " \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    },"+
                " \"threed\": {\n" +
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

        C4C c4c = new C4C(body);
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Assert.assertEquals(json.getString("validation_type"),"3DS");
        logFinishTest("testC4CWallet3DS2AllFieldsRecieverPAN");
    }

    @Test
    public void testC4CPanAllFieldsRecieverPAN() throws JSONException, InterruptedException {
        String body = "\"amount\": 147,\n" +
                "    \"commission\": 0,\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"AGP\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC,Card_param.pan)+"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(Card.FUIB_MC,Card_param.expire)+"\",\n" +
                "        \"ucaf\": \"AQAAAAAAASY5OZRQCvVSQSYAAAA=\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_VISA, Card_param.pan)+"\"\n" +
                "    },\n" +
                ThreeDS.threeDS_2_1_0;

        C4C c4c = new C4C(body,2);
        JSONObject json = new JSONObject(c4c.getResponse());
        //Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        //json = new JSONObject(c4c.getResponse());
        //Assert.assertEquals(json.getString("status"),"ACTIVE");
        //Assert.assertEquals(json.getString("validation_type"),"NONE");
        Thread.sleep(sleep);
        c4c.enroll();
        Thread.sleep(sleep);
        c4c.statusEnroll();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ENROLLED");
        logFinishTest("testC4CPanAllFieldsRecieverPAN");
    }

    @Test
    public void testC4CRefundAmount() throws JSONException, InterruptedException {
        logStartTest("testC4CRefundAmount");
        String body = "\"amount\": 143,\n" +
                "    \"commission\": 10,\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "    \"currency\": 980,\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(Card.FUIB_VISA),"EXTERNAL","test")+","+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC,Card_param.pan)+"\"\n" +
                "    },\n" +
                ThreeDS.threeDS_2_1_0;

        c4c = new C4C(body,true);
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        Assert.assertEquals(json.getString("validation_type"),"NONE");
        Assert.assertEquals(json.getInt("available_amount"),153);
        c4c.refund(153);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        try {
            checkRefund();
        }catch (AssertionError e){
            checkRefund();
        }
        logFinishTest("testC4CRefundAmount");
    }

    @Test
    public void testC4CRefundAmountAfterEnroll() throws JSONException, InterruptedException {
        logStartTest("testC4CRefundAmountAfterEnroll");
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": "+commissoin+",\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "    \"currency\": 980,\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(Card.FUIB_VISA),"EXTERNAL","test")+","+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC,Card_param.pan)+"\"\n" +
                "    },\n" +
                ThreeDS.threeDS_2_1_0;

        c4c = new C4C(body,true);
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        Assert.assertEquals(json.getString("validation_type"),"NONE");
        Assert.assertEquals(json.getInt("available_amount"),summ+commissoin);
        c4c.enroll();
        Thread.sleep(sleep);
        c4c.statusEnroll();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ENROLLED");
        c4c.refund(summ+commissoin);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        try {
            checkRefund();
        }catch (AssertionError e){
            checkRefund();
        }
        logFinishTest("testC4CRefundAmountAfterEnroll");
    }

    private void checkRefund(){
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c4c.statusRefund();
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
    }
}
