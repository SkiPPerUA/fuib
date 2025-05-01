package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.Stanok;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PAYH10673 extends BaseTest {
    //Предоставления наличествующей в PayHub информации о транзакциях для сервиса квитанций

    Stanok stanok = new Stanok();
    JSONObject json;
    @Test
    public void byStan(){
        logStartTest("byStan");
        stanok.findByStan("17258041756");
        json = new JSONObject(stanok.getResponse());
        Assert.assertEquals(json.getString("transaction_id"),"d49d8105-5c63-43e1-991e-09f5c1b92ac5");
        Assert.assertEquals(json.getString("external_id"),"vladTest23");

        JSONArray array = json.getJSONArray("transfers_table");
        Assert.assertEquals(array.length(),3);
        for (int i = 0; i < array.length(); i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("sum"),"143");
            Assert.assertEquals(json.getString("action"),"017258041754");
        }

        logFinishTest("byStan");
    }
}
