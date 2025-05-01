package test.backTests.itm;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.A2C;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.C2A;
import org.example.qaTransactionTeam.backEnd.itm.GetTransDetails;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.P4P;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MT3097 extends BaseTest {
    //API статуса - Статус зачислений переводов Р4Р для компании

    Map<String, String> bodyP4P = new HashMap<>();
    private String summAll = "1000";
    private String summ1 = "300";
    private String summ2 = "400";

    @BeforeTest
    public void createDate() {
        BDMongo.BDMongo("vmtdb");

        bodyP4P.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.token));
        bodyP4P.put("amount", summAll);
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.token));
        bodyP4P.put("expDate", "2212");

    }

    @Test
    public void positiveP4PwithFundId() throws JSONException {
        logStartTest("positiveP4PwithFundId");

        //Выполнение списание средств
        P4P p4p = new P4P(bodyP4P, Cards_data1.getData(Card.FUIB_MC, Card_param.token));

        //Выполнение зачисление средств
        p4p.P4Ppayment(summ1,true);
        String p4pFund_1 = p4p.getP4pFundId();

        //Подсчет количества записей в Монго
        Assert.assertEquals(countResult(getResponse(p4p.getSessionId(),p4pFund_1)),2);

        //Выполнение зачисление средств
        p4p.P4Ppayment(summ2,true);
        String p4pFund_2 = p4p.getP4pFundId();

        //Подсчет количества записей в Монго
        Assert.assertEquals(countResult(getResponse(p4p.getSessionId(),p4pFund_2)),2);

        //Подсчет количества записей в Монго
        Assert.assertEquals(countResult(getResponse(p4p.getSessionId())),7);


        logFinishTest("positiveP4PwithFundId");
    }

    @Test
    public void regressP4PwithOutFundId() throws JSONException {

        logStartTest("regressP4PwithOutFundId");

        //Выполнение списание средств
        P4P p4p = new P4P(bodyP4P, Cards_data1.getData(Card.FUIB_MC, Card_param.token));

        //Выполнение зачисление средств
        p4p.P4Ppayment(summ1,false);
        //Выполнение зачисление средств
        p4p.P4Ppayment(summ2,false);

        //Подсчет количества записей в Монго
        Assert.assertEquals(countResult(getResponse(p4p.getSessionId())),7);

        logFinishTest("regressP4PwithOutFundId");
    }

    @Test
    public void regressA2CwithOutFundId() throws JSONException {

        logStartTest("regressA2CwithOutFundId");

        Map<String, String> bodyA2C = new HashMap<>();
        bodyA2C.put("senderAccount","26206111172132");
        bodyA2C.put("amount",summAll);
        bodyA2C.put("operationId","1");
        bodyA2C.put("customFee","0");
        bodyA2C.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.token));

        A2C a2c = new A2C(bodyA2C);

        //Подсчет количества записей в Монго
        Assert.assertEquals(countResult(getResponse(a2c.getSessionId())),3);

        logFinishTest("regressA2CwithOutFundId");
    }

    @Test
    public void regressC2AwithOutFundId() throws JSONException {

        logStartTest("regressC2AwithOutFundId");

        Map<String, String> bodyC2A = new HashMap<>();
        bodyC2A.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.token));
        bodyC2A.put("amount",summAll);
        bodyC2A.put("operationId","2");
        bodyC2A.put("customFee","0");
        bodyC2A.put("receiverAccount","26206111172132");
        bodyC2A.put("expDate","2212");
        bodyC2A.put("ip","127.0.0.1");
        bodyC2A.put("fingerprint","tests25");

        C2A c2a = new C2A(bodyC2A, Cards_data1.getData(Card.FUIB_MC, Card_param.token));

        //Подсчет количества записей в Монго
        Assert.assertEquals(countResult(getResponse(c2a.getSessionId())),3);

        logFinishTest("regressC2AwithOutFundId");
    }

    @AfterTest
    public void closeBD() throws SQLException {
        BDMongo.closeConn();
    }

    private String getResponse(String session, String fundId) throws JSONException {
        GetTransDetails det1 = new GetTransDetails(session,fundId);
        return det1.getResponse();
    }

    private String getResponse(String session) throws JSONException {
        GetTransDetails det1 = new GetTransDetails(session);
        return det1.getResponse();
    }

    private int countResult(String json) throws JSONException {
        JSONObject ob = new JSONObject(json);
        JSONArray arr = ob.getJSONObject("data").getJSONArray("transfers_table");
        return arr.length();
    }

}
