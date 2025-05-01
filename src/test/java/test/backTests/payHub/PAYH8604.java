package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.AcquiringTrans;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PAYH8604 extends BaseTest {
    //Проведение транзакции через pga по токену карты

    JSONObject json;

    @Test
    public void positiveToken() throws JSONException {
        logStartTest("positiveToken");

        String body = "\"amount\": 100,\n" +
                "    \"commission\": 20,\n" +
                "    \"merchant_config_id\":\"cf31c78b-4e71-447d-8471-7ebbaeb153dd\",\n" +
                "    \"payer\": {\n" +
                "      \"source\": \"WALLET\",\n" +
                "      \"value\": \"f5c8a4e2-6149-46ea-b279-d919000e511f\",\n" +
                "\t\t\t\"expire\": \"2406\",\n" +
                "      \"client_id\":\"57014\"\n" +
                "        },\n" +
                "    \"description\": \"тестовый платёж\",\n" +
                "    \"short_description\": \"тестовый платёж\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"";
        AcquiringTrans trans = new AcquiringTrans(body);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("card_from_hash"),"5375 41** **** 0713");

        logFinishTest("positiveToken");
    }

    @Test
    public void negativeTokenWrongClientId() throws JSONException {
        logStartTest("negativeTokenWrongClientId");

        String body = "\"amount\": 100,\n" +
                "    \"commission\": 20,\n" +
                "    \"merchant_config_id\":\"cf31c78b-4e71-447d-8471-7ebbaeb153dd\",\n" +
                "    \"payer\": {\n" +
                "      \"source\": \"WALLET\",\n" +
                "      \"value\": \"f5c8a4e2-6149-46ea-b279-d919000e511f\",\n" +
                "\t\t\t\"expire\": \"2406\",\n" +
                "      \"client_id\":\"57015\"\n" +
                "        },\n" +
                "    \"description\": \"тестовый платёж\",\n" +
                "    \"short_description\": \"тестовый платёж\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"";
        AcquiringTrans.statusCode = 404;
        AcquiringTrans trans = new AcquiringTrans(body);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("code"),"NOT_FOUND");

        AcquiringTrans.statusCode = 200;

        logFinishTest("negativeTokenWrongClientId");
    }

    @Test
    public void negativeTokenWithoutClientId() throws JSONException {
        logStartTest("negativeTokenWithoutClientId");

        String body = "\"amount\": 100,\n" +
                "    \"commission\": 20,\n" +
                "    \"merchant_config_id\":\"cf31c78b-4e71-447d-8471-7ebbaeb153dd\",\n" +
                "    \"payer\": {\n" +
                "      \"source\": \"WALLET\",\n" +
                "      \"value\": \"f5c8a4e2-6149-46ea-b279-d919000e511f\",\n" +
                "\t\t\t\"expire\": \"2406\"\n" +
                "        },\n" +
                "    \"description\": \"тестовый платёж\",\n" +
                "    \"short_description\": \"тестовый платёж\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"";
        AcquiringTrans.statusCode = 400;
        AcquiringTrans trans = new AcquiringTrans(body);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("code"),"BAD_REQUEST");

        AcquiringTrans.statusCode = 200;

        logFinishTest("negativeTokenWithoutClientId");
    }

    @Test
    public void negativeTokenWrongValue() throws JSONException {
        logStartTest("negativeTokenWrongValue");

        String body = "\"amount\": 100,\n" +
                "    \"commission\": 20,\n" +
                "    \"merchant_config_id\":\"cf31c78b-4e71-447d-8471-7ebbaeb153dd\",\n" +
                "    \"payer\": {\n" +
                "      \"source\": \"WALLET\",\n" +
                "      \"value\": \"f5c8a4e2-6149-46ea-b279-d919000e512f\",\n" +
                "\t\t\t\"expire\": \"2406\",\n" +
                "      \"client_id\":\"57015\"\n" +
                "        },\n" +
                "    \"description\": \"тестовый платёж\",\n" +
                "    \"short_description\": \"тестовый платёж\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"";
        AcquiringTrans.statusCode = 404;
        AcquiringTrans trans = new AcquiringTrans(body);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("code"),"NOT_FOUND");

        AcquiringTrans.statusCode = 200;

        logFinishTest("negativeTokenWrongValue");
    }

    @Test
    public void positiveTokenWithoutExpire() throws JSONException {
        logStartTest("positiveTokenWithoutExpire");

        String body = "\"amount\": 100,\n" +
                "    \"commission\": 20,\n" +
                "    \"merchant_config_id\":\"cf31c78b-4e71-447d-8471-7ebbaeb153dd\",\n" +
                "    \"payer\": {\n" +
                "      \"source\": \"WALLET\",\n" +
                "      \"value\": \"f5c8a4e2-6149-46ea-b279-d919000e511f\",\n" +
                "      \"client_id\":\"57014\"\n" +
                "        },\n" +
                "    \"description\": \"тестовый платёж\",\n" +
                "    \"short_description\": \"тестовый платёж\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"";
        AcquiringTrans trans = new AcquiringTrans(body);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("card_from_hash"),"5375 41** **** 0713");

        logFinishTest("positiveTokenWithoutExpire");
    }

    @Test
    public void regressPan() throws JSONException {
        logStartTest("regressPan");

        String body = "\"amount\": 100,\n" +
                "    \"commission\": 20,\n" +
                "    \"merchant_config_id\":\"cf31c78b-4e71-447d-8471-7ebbaeb153dd\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \"5167542000932045\",\n" +
                "      \"expire\": \"2203\",\n" +
                "      \"cvv\": \"760\"\n" +
                "    }," +
                "    \"description\": \"тестовый платёж\",\n" +
                "    \"short_description\": \"тестовый платёж\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"";
        AcquiringTrans trans = new AcquiringTrans(body);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"3DS_VERIFICATION_NEEDED");
        Assert.assertEquals(json.getString("card_from_hash"),"5167 54** **** 2045");

        logFinishTest("regressPan");
    }

}
