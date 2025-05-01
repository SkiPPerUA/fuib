package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.Payer_constructor;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C4C;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PAYH11308 extends BaseTest {
    //Настройка в логике учёта комиссии в Р4Р

    int summ = 143;
    int comm = 20;

    int sleep = 10000;
    C4C c4c;
    JSONObject json;

    @Test
    void positiveRefundSummPlusComm() throws InterruptedException {
        logStartTest("positiveRefundSummPlusComm");
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": "+comm+",\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "\t\"without_confirmation\":true,\n" +
                "    \"currency\": 980,\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(Card.FUIB_VISA),"EXTERNAL","test")+","+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    },\n" +
                ThreeDS.threeDS_2_1_0;

        c4c = new C4C(body);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        Assert.assertEquals(json.getInt("available_amount"),summ+comm);
        c4c.refund(summ+comm);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        try {
            checkRefund();
        }catch (AssertionError e){
            checkRefund();
        }

        logFinishTest("positiveRefundSummPlusComm");
    }

    @Test
    void negativeRefundSummPlusComm() throws InterruptedException {
        logStartTest("negativeRefundSummPlusComm");
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": "+comm+",\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "\t\"without_confirmation\":true,\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2212\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    },\n" +
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
                "    }";

        C4C c4c = new C4C(body);
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        Assert.assertEquals(json.getInt("available_amount"),summ+comm);
        c4c.refund(summ+comm+1);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("negativeRefundSummPlusComm");
    }

    @Test
    void positiveTwoRefundSummPlusComm() throws InterruptedException {
        logStartTest("positiveTwoRefundSummPlusComm");
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": "+comm+",\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "\t\"without_confirmation\":true,\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2212\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    },\n" +
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
                "    }";

        c4c = new C4C(body);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        Assert.assertEquals(json.getInt("available_amount"),summ+comm);
        c4c.refund(summ);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        try {
            checkRefund();
        }catch (AssertionError e){
            checkRefund();
        }
        c4c.refund(comm);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        try {
            checkRefund();
        }catch (AssertionError e){
            checkRefund();
        }
        logFinishTest("positiveTwoRefundSummPlusComm");
    }

    @Test
    void positiveThreeRefundSummPlusComm() throws InterruptedException {
        logStartTest("positiveThreeRefundSummPlusComm");
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": "+comm+",\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "\t\"without_confirmation\":true,\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2212\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    },\n" +
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
                "    }";

        C4C c4c = new C4C(body);
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        Assert.assertEquals(json.getInt("available_amount"),summ+comm);
        c4c.refund(summ-10);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        c4c.refund(20);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        c4c.refund(comm-10);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("positiveThreeRefundSummPlusComm");
    }

    @Test
    void negativeTwoRefundSummPlusComm() throws InterruptedException {
        logStartTest("negativeTwoRefundSummPlusComm");
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": "+comm+",\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "\t\"without_confirmation\":true,\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2212\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    },\n" +
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
                "    }";

        C4C c4c = new C4C(body);
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        Assert.assertEquals(json.getInt("available_amount"),summ+comm);
        c4c.refund(100);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        c4c.refund(summ+comm-99);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("negativeTwoRefundSummPlusComm");
    }

    @Test
    void positiveRefundSumm() throws InterruptedException {
        logStartTest("positiveRefundSumm");
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": 0,\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "\t\"without_confirmation\":true,\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2212\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    },\n" +
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
                "    }";

        C4C c4c = new C4C(body);
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        Assert.assertEquals(json.getInt("available_amount"),summ);
        c4c.refund(summ);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("positiveRefundSumm");
    }

    @Test
    void negativeRefundSumm() throws InterruptedException {
        logStartTest("negativeRefundSumm");
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": 0,\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "\t\"without_confirmation\":true,\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2212\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    },\n" +
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
                "    }";

        C4C c4c = new C4C(body);
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        Assert.assertEquals(json.getInt("available_amount"),summ);
        c4c.refund(summ+1);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("negativeRefundSumm");
    }

    @Test
    void positiveTwoRefundSumm() throws InterruptedException {
        logStartTest("positiveTwoRefundSumm");
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": 0,\n" +
                "\t\"destination\": \"test\",\n" +
                "\t\"description\": \"VladTest\",\n" +
                "\t\"without_confirmation\":true,\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2212\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    },\n" +
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
                "    }";

        C4C c4c = new C4C(body);
        JSONObject json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        c4c.status();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        Assert.assertEquals(json.getInt("available_amount"),summ);
        c4c.refund(summ-20);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        c4c.refund(20);
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("positiveTwoRefundSumm");
    }

    private void checkRefund(){
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c4c.statusRefund();
        json = new JSONObject(c4c.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
    }

}
