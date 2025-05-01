package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C_direct;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class A2C_direct_regress extends BaseTest {

    int sleep = 15000;
    Transaction a2c;

    public void testA2CFuib() throws JSONException, InterruptedException {
        String body = "\"amount\": 1000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 120,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    }," +
                "\"payer\":{\n" +
                "\t\t\"source\":\"IBAN\",\n" +
                "\t\t\"value\":\"UA613348510000026203405297856\"\n" +
                "\t}";

        a2c = new A2C_direct(body);
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"CREATED");
        Thread.sleep(sleep);
        a2c.status();
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"PROCESSED");

    }

    public void testA2CMono() throws JSONException, InterruptedException {
        String body = "\"amount\": 300,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 120,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data1.getData(Card.MONO_VISA,Card_param.pan)+"\"\n" +
                "    }," +
                "\"payer\":{\n" +
                "\t\t\"source\":\"IBAN\",\n" +
                "\t\t\"value\":\"UA613348510000026203405297856\"\n" +
                "\t}";

        a2c = new A2C_direct(body);
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"CREATED");
        Thread.sleep(sleep);
        a2c.status();
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"PROCESSED");

    }

    public void testA2CABank() throws JSONException, InterruptedException {
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"5375235201157045\"\n" + //4323347333635610
                "    }," +
                "\"payer\":{\n" +
                "\t\t\"source\":\"IBAN\",\n" +
                "\t\t\"value\":\"UA093348510000018328000001006\"\n" +
                "\t}";

        a2c = new A2C_direct(body);
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"CREATED");
        Thread.sleep(sleep);
        a2c.status();
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"PROCESSED");
    }

    public void testA2COschadBank() throws JSONException, InterruptedException {
        String body = "\"amount\": 2357,\n" +
                "    \"currency\": 980,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"5274109990480631\"\n" + //4790729951422567  5274109990480631
                "    }," +
                "\"payer\":{\n" +
                "\t\t\"source\":\"IBAN\",\n" +
                "\t\t\"value\":\"UA093348510000018328000001006\"\n" +
                "\t}";

        a2c = new A2C_direct(body);
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"CREATED");
        Thread.sleep(sleep);
        a2c.status();
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"PROCESSED");
    }

    public void testA2CAval() throws JSONException, InterruptedException {
        String body = "\"amount\": 2357,\n" +
                "    \"currency\": 980,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"4999999999990011\"\n" +
                "    }," +
                "\"payer\":{\n" +
                "\t\t\"source\":\"IBAN\",\n" +
                "\t\t\"value\":\"UA093348510000018328000001006\"\n" +
                "\t}";
        a2c = new A2C_direct(body);
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"CREATED");
        Thread.sleep(sleep);
        a2c.status();
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"PROCESSED");
    }


    public void testA2CPrivatBank() throws JSONException, InterruptedException {
        String body = "\"amount\": 100,\n" +  //для прямого привата сумма должна быть = 1 грн
                    "    \"currency\": 980,\n" +
                    "    \"commission\": 0,\n" +
                    "    \"description\": \"Тест сообщение для привата\",\n" +
                    "    \"identification\": {\n" +
                    "          \"general\": {\n" +
                    "                \"first_name\": \"firstnameTestFuib\",\n" +
                    "                \"last_name\": \"lastnameTestFuib\"\n" +
                    "            }" +
                    "      }," +
                    "    \"receiver\": {\n" +
                    "        \"source\": \"PAN\",\n" +
                    "        \"value\": \"5168745611327906\",\n" + //5168745022396342 4149629330032681 5168752082264410 5168745611327906
                    "        \"recipientFirstName\": \"FirstName\",\n" +
                    "        \"recipientLastName\": \"LastName\"\n" +
                    "    }," +
                    "\"payer\":{\n" +
                    "\t\t\"source\":\"IBAN\",\n" +
                    "\t\t\"value\":\"UA613348510000026203405297856\"\n" + //UA683348510000018322111111029  UA613348510000026203405297856 UA493220010000026209303616072
                    "\t}";
        a2c = new A2C_direct(body);
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"CREATED");
        Thread.sleep(sleep);
        a2c.status();
        Assert.assertEquals(new JSONObject(a2c.getResponse()).getJSONObject("data").getString("status"),"PROCESSED");
    }
}
