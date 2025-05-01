package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.VisaAlias;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C_direct;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class A2Cregress extends BaseTest {

    int sleep = 15000;
    Transaction a2c;

    @Test
    public void testA2CPanOnlyMandatoryFields() throws JSONException, InterruptedException {
            String body = "\"amount\": 100,\n" +
                    "    \"currency\": \"UAH\",\n" +
                    "    \"client_ip\": \"0.1.1.4\",\n" +
                    "    \"description\": \"description_тест\", \n" +
                    "    \"destination\": \"test21312\",\n" +
                    "    \"identification\": {\n" +
                    "         \"requirements\":{\n" +
                    "             \"recipient\":{\n" +
                    "                 \"first_name\":\"IvanRecipient\",\n" +
                    "                 \"last_name\":\"IvanovRecipient\"\n" +
                    "              },\n" +
                    "             \"sender\":{\n" +
                    "                 \"first_name\":\"IvanSender\",\n" +
                    "                 \"last_name\":\"IvanovSender\",\n" +
                    "                 \"account_number\":\"UA953348510000026201112609803\"\n" +
                    "              }\n" +
                    "        },"+
                    "       \"address\": {" +
                    "               \"locality\": \"11111\",\n" +
                    "               \"street\": \"22222\"\n" +
                    "               }"+
                    "    },"+
                    "    \"receiver\": {\n" +
                    "        \"source\": \"PAN\",\n" +
                    "        \"value\": \"" + Cards_data.getData(Card.FUIB_MC, Card_param.pan) + "\"\n" +
                    "    }";

            a2c = new A2C(body);
        JSONObject json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        a2c.status();
        json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PROCESSED");
    }

    @Test
    public void testA2CPanAllFields() throws JSONException, InterruptedException {
        logStartTest("testA2CPanAllFields");
        String body = "\"amount\": 110, \n" +
                "        \"commission\": 0, \n" +
                "        \"currency\": \"UAH\", \n" +
                "        \"z270\": 4, \n" +
                "        \"date\": \"2023-05-09\", \n" +
                "        \"description\": \"description_тест\", \n" +
                "        \"destination\": \"Переказ коштів на картку ***6420\", \n" +
                "        \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"address\":\"sender_address\",\n" +
                "                 \"country\":\"sender_country\",\n" +
                "                 \"city\":\"sender_city\",\n" +
                "                 \"account_number\":\"UA953348510000026201112609803\"\n" +
                "              }\n" +
                "        },"+
                "       \"address\": {" +
                "               \"locality\": \"11111\",\n" +
                "               \"street\": \"22222\"\n" +
                "               }"+
                "    },"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.TEST_CARD, Card_param.pan) +"\",\n" +
                "        \"recipientFirstName\":\"Fir's-tNam e ыв\",\n" +
                "        \"recipientLastName\":\"L'as-tNa.me\"\n" +
                "    }";

        A2C a2c = new A2C(body);
        JSONObject json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        a2c.status();
        json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("testA2CPanAllFields");
    }

    @Test
    public void testA2CVisaAliasOnlyMandatoryFields() throws JSONException, InterruptedException {
        logStartTest("testA2CVisaAliasOnlyMandatoryFields");
        VisaAlias alias = new VisaAlias();
        alias.findAlias("380937101512");

        String body = "\"amount\": 101,\n" +
                "    \"currency\": 980,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"VISA_ALIAS\",\n" +
                "        \"value\": \""+alias.getUid()+"\"\n" +
                "    }";

        A2C a2c = new A2C(body);
        JSONObject json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(sleep);
        a2c.status();
        json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("testA2CVisaAliasOnlyMandatoryFields");
    }

    @Test
    public void testA2CAccountIDOnlyMandatoryFields() throws JSONException, InterruptedException {
        String body = "\"amount\": 100,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"client_ip\": \"0.1.1.4\",\n" +
                "    \"description\": \"description_тест\", \n" +
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"account_number\":\"UA953348510000026201112609803\"\n" +
                "              }\n" +
                "        }," +
                "       \"address\": {" +
                "               \"locality\": \"11111\",\n" +
                "               \"street\": \"22222\"\n" +
                "               }" +
                "    }," +
                "    \"sender\": {\n" +
                "        \"source\": \"ACCOUNT_ID\",\n" +
                "        \"value\": \"100509886\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"1299125\"\n" +
                "        }\n" +
                "    },\n " +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"" + Cards_data.getData(Card.MONO_MC, Card_param.pan) + "\"\n" +
                "    }";

        a2c = new A2C(body);
        JSONObject json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"), "CREATED");
        Thread.sleep(sleep);
        a2c.status();
        json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"), "PROCESSED");
    }

    @Test
    void getStatus(){
        A2C a2c = new A2C();
        a2c.status("acdf9bb1-3535-4e71-9e0b-951b62d25a98");
    }
}
