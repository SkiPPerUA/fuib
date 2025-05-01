package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.VisaAlias;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C_tax;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class A2C_tax_regress extends BaseTest {

    int sleep = 15000;
    Transaction a2c;

    @Test
    public void testA2C_tax() throws JSONException, InterruptedException {
            String body = "\"amount\": 1200,\n" +
                    "    \"currency\": \"UAH\",\n" +
                    "    \"identification\": {\n" +
                    "            \"general\": {\n" +
                    "                \"tax_id\": \"0000000000\"\n" +
                    "            },\n" +
                    "            \"requirements\": {\n" +
                    "                \"recipient\": {\n" +
                    "                    \"amount\": 1550,\n" +
                    "                    \"first_name\": \"test\",\n" +
                    "                    \"last_name\": \"Головчук\",\n" +
                    "                    \"account_number\": \"UA313052990000026007006103521\"\n" +
                    "                },\n" +
                    "                \"sender\": {\n" +
                    "                    \"last_name\": \"ТОВ СЛ СЕЙЛС\",\n" +
                    "                    \"account_number\": \"UA783281680000026007000005743\",\n" +
                    "                    \"reference_number\": \"52562954\"\n" +
                    "                },\n" +
                    "                \"details\": {\n" +
                    "                    \"additional_message\": \" \",\n" +
                    "                    \"source\": \"04\",\n" +
                    "                    \"submerchant_url\": \"https://easypay.ua\",\n" +
                    "                    \"independent_sales_organization_id\": \"39837729\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"taxes\": {\n" +
                    "            \"income\": 200,\n" +
                    "            \"military\": 10\n" +
                    "        }," +
                    "    \"receiver\": {\n" +
                    "        \"source\": \"PAN\",\n" +
                    "        \"value\": \"5168745022396342\"\n" +
                    "    }";

            a2c = new A2C_tax(body);
        JSONObject json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PENDING");
        Thread.sleep(sleep);
        a2c.status();
        json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("testA2CPanOnlyMandatoryFields");
    }

    @Test
    void getStatus(){
        A2C_tax a2c = new A2C_tax();
        a2c.status("a556f80d-f0e0-4025-8115-fe5f307f050c");
    }
}
