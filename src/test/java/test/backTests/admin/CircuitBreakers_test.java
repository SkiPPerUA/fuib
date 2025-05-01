package test.backTests.admin;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.Direct_CircuitBreakers;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C_legion;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class CircuitBreakers_test extends BaseTest {

    Direct_CircuitBreakers directCircuitBreakers = new Direct_CircuitBreakers();
    JSONObject json;

    String bankName = "OSCHAD";
    int initFail_amount = 40000;
    int confirmFail_amount = 50000;
    int success_amount = 20000;

    public void get_circuitBreakers(){
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
    }

    public void test_circuitBreakers() throws InterruptedException {
        confirm();
    }

    private void confirm() throws InterruptedException {
        //confirm
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        makeTrans(confirmFail_amount,json.getJSONObject("details").getJSONObject("confirm").getInt("error_threshold")); //make confirm fail
        Thread.sleep(30000);
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("confirm").getString("state"), "RED");
        Thread.sleep(json.getJSONObject("details").getJSONObject("confirm").getInt("timeout")*1000);
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("confirm").getString("state"), "YELLOW");
        makeTrans(success_amount,2); //make confirm success
        Thread.sleep(30000);
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("confirm").getString("state"), "YELLOW");
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("confirm").getInt("success_count"), 2);
        makeTrans(confirmFail_amount,1); //make confirm fail
        Thread.sleep(30000);
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("confirm").getString("state"), "RED");
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("confirm").getInt("success_count"), 0);
        Thread.sleep(json.getJSONObject("details").getJSONObject("confirm").getInt("timeout")*1000);
        makeTrans(success_amount,json.getJSONObject("details").getJSONObject("confirm").getInt("success_threshold")); //make confirm success
        Thread.sleep(30000);
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("confirm").getString("state"), "GREEN");
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("confirm").getInt("success_count"), 0);
        makeTrans(confirmFail_amount,json.getJSONObject("details").getJSONObject("confirm").getInt("error_threshold")); //make confirm fail
        Thread.sleep(30000);
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("confirm").getString("state"), "RED");
    }

    private void init() throws InterruptedException {
        //init
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        makeTrans(initFail_amount,json.getJSONObject("details").getJSONObject("init").getInt("error_threshold")); //make init fail
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("init").getString("state"), "RED");
        Thread.sleep(json.getJSONObject("details").getJSONObject("init").getInt("timeout")*1000);
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("init").getString("state"), "YELLOW");
        makeTrans(success_amount,2); //make init success
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("init").getString("state"), "YELLOW");
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("init").getInt("success_count"), 2);
        makeTrans(initFail_amount,1); //make init fail
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("init").getString("state"), "RED");
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("init").getInt("success_count"), 0);
        Thread.sleep(json.getJSONObject("details").getJSONObject("init").getInt("timeout")*1000);
        makeTrans(success_amount,json.getJSONObject("details").getJSONObject("init").getInt("success_threshold")); //make init success
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("init").getString("state"), "GREEN");
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("init").getInt("success_count"), 0);
        makeTrans(initFail_amount,json.getJSONObject("details").getJSONObject("init").getInt("error_threshold")); //make init fail
        directCircuitBreakers.get_circuitBreakers("A2C",bankName);
        json = new JSONObject(directCircuitBreakers.getResponse());
        Assert.assertEquals(json.getJSONObject("details").getJSONObject("init").getString("state"), "RED");
    }

    private void makeTrans(int amount, int count){
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(237368));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": "+amount+",\n" +
                "    \"commission\": 10,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"test1\",\n" +
                "    \"external_id\": \"" + Uuid_helper.generate_uuid() + "\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA713348510000026201116887159\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"736451\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.OSCHAD_MC, Card_param.pan) +"\"\n" +
                "    }\n" +
                "}");
        for (int i = 0; i < count; i++){
            a2C_legion.makeTrans();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
