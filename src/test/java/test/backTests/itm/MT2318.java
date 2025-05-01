package test.backTests.itm;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.A2C;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.C2A;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.P2P;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.P4P;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MT2318 extends BaseTest {
    //Добавление в API приложения VMT возможности проведения транзакций p2p с кошелька MasterPass

    @BeforeTest
    public void connBD() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        BDas400.BDas400("ITMTST", Configs1.ITMTST_ALL_NAME, Configs1.ITMTST_ALL_PASSWORD);
    }

    @Test
    public void positiveC2A_mc_CVV() throws SQLException, JSONException {
        logStartTest("positiveC2A_mc_CVV");
        activeMasterpass(2101,true);

        Map<String, String> bodyC2A = new HashMap<>();
        bodyC2A.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.token));
        bodyC2A.put("amount","100");
        bodyC2A.put("operationId","2");
        bodyC2A.put("customFee","0");
        bodyC2A.put("receiverAccount","26206111172132");
        bodyC2A.put("expDate","2212");
        bodyC2A.put("ip","127.0.0.1");
        bodyC2A.put("fingerprint","tests25");

        C2A c2a = new C2A(bodyC2A, Cards_data1.getData(Card.FUIB_MC, Card_param.token));

        Assert.assertEquals(c2a.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(c2a.getSessionId()),true);

        logFinishTest("positiveC2A_mc_CVV");
    }

    @Test
    public void regressC2A_mc_CVV() throws SQLException, JSONException {
        logStartTest("regressC2A_mc_CVV");
        activeMasterpass(2101,false);

        Map<String, String> bodyC2A = new HashMap<>();
        bodyC2A.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.token));
        bodyC2A.put("amount","100");
        bodyC2A.put("operationId","2");
        bodyC2A.put("customFee","0");
        bodyC2A.put("receiverAccount","26206111172132");
        bodyC2A.put("expDate","2212");
        bodyC2A.put("ip","127.0.0.1");
        bodyC2A.put("fingerprint","tests25");

        C2A c2a = new C2A(bodyC2A, Cards_data1.getData(Card.FUIB_MC, Card_param.token));

        Assert.assertEquals(c2a.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(c2a.getSessionId()),true);

        logFinishTest("regressC2A_mc_CVV");
    }

    @Test
    public void positiveC2A_visa() throws SQLException, JSONException {
        logStartTest("positiveC2A_visa");
        activeMasterpass(2101,true);

        Map<String, String> bodyC2A = new HashMap<>();
        bodyC2A.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.token));
        bodyC2A.put("amount","100");
        bodyC2A.put("operationId","2");
        bodyC2A.put("customFee","0");
        bodyC2A.put("receiverAccount","26206111172132");
        bodyC2A.put("expDate","2211");
        bodyC2A.put("ip","127.0.0.1");
        bodyC2A.put("fingerprint","tests25");

        C2A c2a = new C2A(bodyC2A, Cards_data1.getData(Card.FUIB_VISA, Card_param.token));

        Assert.assertEquals(c2a.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(c2a.getSessionId()),true);

        logFinishTest("positiveC2A_visa");
    }

    @Test
    public void regressC2A_visa() throws SQLException, JSONException {
        logStartTest("regressC2A_visa");
        activeMasterpass(2101,false);

        Map<String, String> bodyC2A = new HashMap<>();
        bodyC2A.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.token));
        bodyC2A.put("amount","100");
        bodyC2A.put("operationId","2");
        bodyC2A.put("customFee","0");
        bodyC2A.put("receiverAccount","26206111172132");
        bodyC2A.put("expDate","2211");
        bodyC2A.put("ip","127.0.0.1");
        bodyC2A.put("fingerprint","tests25");

        C2A c2a = new C2A(bodyC2A, Cards_data1.getData(Card.FUIB_VISA, Card_param.token));

        Assert.assertEquals(c2a.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(c2a.getSessionId()),true);

        logFinishTest("regressC2A_visa");
    }

    @Test
    public void positiveC2A_visa_CVV() throws SQLException, JSONException {
        logStartTest("positiveC2A_visa_CVV");
        activeMasterpass(2101,true);

        Map<String, String> bodyC2A = new HashMap<>();
        bodyC2A.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.token));
        bodyC2A.put("amount","100");
        bodyC2A.put("operationId","2");
        bodyC2A.put("customFee","0");
        bodyC2A.put("receiverAccount","26206111172132");
        bodyC2A.put("expDate","2211");
        bodyC2A.put("ip","127.0.0.1");
        bodyC2A.put("fingerprint","tests25");

        C2A c2a = new C2A(bodyC2A, Cards_data1.getData(Card.FUIB_VISA, Card_param.token));

        Assert.assertEquals(c2a.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(c2a.getSessionId()),true);

        logFinishTest("positiveC2A_visa_CVV");
    }

    @Test
    public void regressC2A_visa_CVV() throws SQLException, JSONException {
        logStartTest("regressC2A_visa_CVV");
        activeMasterpass(2101,false);

        Map<String, String> bodyC2A = new HashMap<>();
        bodyC2A.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.token));
        bodyC2A.put("amount","100");
        bodyC2A.put("operationId","2");
        bodyC2A.put("customFee","0");
        bodyC2A.put("receiverAccount","26206111172132");
        bodyC2A.put("expDate","2211");
        bodyC2A.put("ip","127.0.0.1");
        bodyC2A.put("fingerprint","tests25");

        C2A c2a = new C2A(bodyC2A, Cards_data1.getData(Card.FUIB_VISA, Card_param.token));

        Assert.assertEquals(c2a.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(c2a.getSessionId()),true);

        logFinishTest("regressC2A_visa_CVV");
    }

    @Test
    public void positiveC2A_mc() throws SQLException, JSONException {
        logStartTest("positiveC2A_mc");
        activeMasterpass(2101,true);

        Map<String, String> bodyC2A = new HashMap<>();
        bodyC2A.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.token));
        bodyC2A.put("amount","100");
        bodyC2A.put("operationId","2");
        bodyC2A.put("customFee","0");
        bodyC2A.put("receiverAccount","26206111172132");
        bodyC2A.put("expDate","2212");
        bodyC2A.put("ip","127.0.0.1");
        bodyC2A.put("fingerprint","tests25");

        C2A c2a = new C2A(bodyC2A, Cards_data1.getData(Card.FUIB_MC, Card_param.token));

        Assert.assertEquals(c2a.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(c2a.getSessionId()),true);

        logFinishTest("positiveC2A_mc");
    }

    @Test
    public void regressC2A_mc() throws SQLException, JSONException {
        logStartTest("regressC2A_mc");
        activeMasterpass(2101,false);

        Map<String, String> bodyC2A = new HashMap<>();
        bodyC2A.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.token));
        bodyC2A.put("amount","100");
        bodyC2A.put("operationId","2");
        bodyC2A.put("customFee","0");
        bodyC2A.put("receiverAccount","26206111172132");
        bodyC2A.put("expDate","2212");
        bodyC2A.put("ip","127.0.0.1");
        bodyC2A.put("fingerprint","tests25");

        C2A c2a = new C2A(bodyC2A, Cards_data1.getData(Card.FUIB_MC, Card_param.token));

        Assert.assertEquals(c2a.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(c2a.getSessionId()),true);

        logFinishTest("regressC2A_mc");
    }

    @Test
    public void regressA2C_visa() throws JSONException, SQLException {

        logStartTest("regressA2C_visa");
        activeMasterpass(2101,false);

        Map<String, String> bodyA2C = new HashMap<>();
        bodyA2C.put("senderAccount","26201112609803");
        bodyA2C.put("amount","100");
        bodyA2C.put("operationId","1");
        bodyA2C.put("customFee","0");
        bodyA2C.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.token));

        A2C a2c = new A2C(bodyA2C);

        Assert.assertEquals(a2c.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(a2c.getSessionId()),false);

        logFinishTest("regressA2C_visa");
    }

    @Test
    public void regressA2C_mc() throws JSONException, SQLException {

        logStartTest("regressA2C_mc");
        activeMasterpass(2101,false);

        Map<String, String> bodyA2C = new HashMap<>();
        bodyA2C.put("senderAccount","26201112609803");
        bodyA2C.put("amount","100");
        bodyA2C.put("operationId","1");
        bodyA2C.put("customFee","0");
        bodyA2C.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));

        A2C a2c = new A2C(bodyA2C);

        Assert.assertEquals(a2c.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(a2c.getSessionId()),false);

        logFinishTest("regressA2C_mc");
    }

    @Test
    public void regressP4P_visa() throws JSONException, SQLException {

        logStartTest("regressP4P_visa");
        activeMasterpass(2101,false);

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        bodyP4P.put("expDate", "2211");

        P4P p4p = new P4P(bodyP4P, Cards_data1.getData(Card.FUIB_VISA, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("300",true);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("400",true);
        Assert.assertEquals(p4p.getErrorCode(),"00");

        Assert.assertEquals(checkCF10(p4p.getSessionId()),false);

        logFinishTest("regressP4P_visa");
    }

    @Test
    public void regressP4P_visa_CVV() throws JSONException, SQLException {

        logStartTest("regressP4P_visa_CVV");
        activeMasterpass(2101,false);

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        bodyP4P.put("expDate", "2211");

        P4P p4p = new P4P(bodyP4P, Cards_data1.getData(Card.FUIB_VISA, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("300",true);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("400",true);
        Assert.assertEquals(p4p.getErrorCode(),"00");

        Assert.assertEquals(checkCF10(p4p.getSessionId()),false);

        logFinishTest("regressP4P_visa_CVV");
    }

    @Test
    public void regressP4P_mc_CVV() throws JSONException, SQLException {

        logStartTest("regressP4P_mc_CVV");
        activeMasterpass(2101,false);

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        bodyP4P.put("expDate", "2212");

        P4P p4p = new P4P(bodyP4P, Cards_data1.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("300",true);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("400",true);
        Assert.assertEquals(p4p.getErrorCode(),"00");

        Assert.assertEquals(checkCF10(p4p.getSessionId()),false);

        logFinishTest("regressP4P_mc_CVV");
    }

    @Test
    public void regressP4P_mc() throws JSONException, SQLException {

        logStartTest("regressP4P_mc");
        activeMasterpass(2101,false);

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        bodyP4P.put("expDate", "2212");

        P4P p4p = new P4P(bodyP4P, Cards_data1.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("300",true);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("400",true);
        Assert.assertEquals(p4p.getErrorCode(),"00");

        Assert.assertEquals(checkCF10(p4p.getSessionId()),false);

        logFinishTest("regressP4P_mc");
    }

    @Test
    public void positiveP2P_mc_CVV() throws JSONException, SQLException, IOException, InterruptedException {
        logStartTest("positiveP2P_mc_CVV");

        activeMasterpass(2101,true);

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        body.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        body.put("expDate", "2212");
        body.put("amount", "100");
        P2P p2p = new P2P(body, Cards_data1.getData(Card.FUIB_MC, Card_param.token),2);

        Assert.assertEquals(p2p.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(p2p.getSessionId()),true);
        logFinishTest("positiveP2P_mc_CVV");

    }

    @Test
    public void regressP2P_mc_CVV() throws JSONException, SQLException, IOException, InterruptedException {
        logStartTest("regressP2P_mc_CVV");

        activeMasterpass(2101,false);

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        body.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        body.put("expDate", "2212");
        body.put("amount", "100");
        P2P p2p = new P2P(body, Cards_data1.getData(Card.FUIB_MC, Card_param.token),2);

        Assert.assertEquals(p2p.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(p2p.getSessionId()),false);
        logFinishTest("regressP2P_mc_CVV");

    }

    @Test
    public void positiveP2P_mc() throws JSONException, SQLException, IOException, InterruptedException {
        logStartTest("positiveP2P_mc");

        activeMasterpass(2101,true);

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        body.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        body.put("expDate", "2212");
        body.put("amount", "100");
        P2P p2p = new P2P(body, Cards_data1.getData(Card.FUIB_MC, Card_param.token),2);

        Assert.assertEquals(p2p.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(p2p.getSessionId()),true);
        logFinishTest("positiveP2P_mc");

    }

    @Test
    public void regressP2P_mc() throws JSONException, SQLException, IOException, InterruptedException {
        logStartTest("regressP2P_mc");

        activeMasterpass(2101,false);

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        body.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        body.put("expDate", "2212");
        body.put("amount", "100");
        P2P p2p = new P2P(body, Cards_data1.getData(Card.FUIB_MC, Card_param.token),2);

        Assert.assertEquals(p2p.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(p2p.getSessionId()),false);
        logFinishTest("regressP2P_mc");

    }

    @Test
    public void positiveP2P_visa() throws JSONException, SQLException, IOException, InterruptedException {
        logStartTest("positiveP2P_visa");

        activeMasterpass(2101,true);

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("expDate", "2211");
        body.put("amount", "100");
        P2P p2p = new P2P(body,2);

        Assert.assertEquals(p2p.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(p2p.getSessionId()),true);
        logFinishTest("positiveP2P_visa");

    }

    @Test
    public void regressP2P_visa() throws JSONException, SQLException, IOException, InterruptedException {
        logStartTest("regressP2P_visa");

        activeMasterpass(2101,false);

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("expDate", "2211");
        body.put("amount", "100");
        P2P p2p = new P2P(body,2);

        Assert.assertEquals(p2p.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(p2p.getSessionId()),false);
        logFinishTest("regressP2P_visa");

    }

    @Test
    public void positiveP2P_visa_CVV() throws JSONException, SQLException, IOException, InterruptedException {
        logStartTest("positiveP2P_visa_CVV");

        activeMasterpass(2101,true);

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("expDate", "2211");
        body.put("amount", "100");
        P2P p2p = new P2P(body, Cards_data1.getData(Card.FUIB_MC, Card_param.token),2);

        Assert.assertEquals(p2p.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(p2p.getSessionId()),true);
        logFinishTest("positiveP2P_visa_CVV");

    }

    @Test
    public void regressP2P_visa_CVV() throws JSONException, SQLException, IOException, InterruptedException {
        logStartTest("regressP2P_visa_CVV");

        activeMasterpass(2101,false);

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("expDate", "2211");
        body.put("amount", "100");
        P2P p2p = new P2P(body, Cards_data1.getData(Card.FUIB_VISA, Card_param.token),2);

        Assert.assertEquals(p2p.getErrorCode(),"00");
        Assert.assertEquals(checkCF10(p2p.getSessionId()),false);
        logFinishTest("regressP2P_visa_CVV");

    }

    @Test
    public void positiveP4P_visa() throws JSONException, SQLException {

        logStartTest("positiveP4P_visa");
        activeMasterpass(2101,true);

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        bodyP4P.put("expDate", "2211");

        P4P p4p = new P4P(bodyP4P);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("300",true);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("400",true);
        Assert.assertEquals(p4p.getErrorCode(),"00");

        Assert.assertEquals(checkCF10(p4p.getSessionId()),true);

        logFinishTest("positiveP4P_visa");
    }

    @Test
    public void positiveP4P_visa_CVV() throws JSONException, SQLException {

        logStartTest("positiveP4P_visa_CVV");
        activeMasterpass(2101,true);

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        bodyP4P.put("expDate", "2211");

        P4P p4p = new P4P(bodyP4P);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("300",true);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("400",true);
        Assert.assertEquals(p4p.getErrorCode(),"00");

        Assert.assertEquals(checkCF10(p4p.getSessionId()),false);

        logFinishTest("positiveP4P_visa_CVV");
    }

    @Test
    public void positiveP4P_mc_CVV() throws JSONException, SQLException {

        logStartTest("positiveP4P_mc_CVV");
        activeMasterpass(2101,true);

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        bodyP4P.put("expDate", "2212");

        P4P p4p = new P4P(bodyP4P, Cards_data1.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("300",true);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("400",true);
        Assert.assertEquals(p4p.getErrorCode(),"00");

        Assert.assertEquals(checkCF10(p4p.getSessionId()),false);

        logFinishTest("positiveP4P_mc_CVV");
    }

    @Test
    public void positiveP4P_mc() throws JSONException, SQLException {

        logStartTest("positiveP4P_mc");
        activeMasterpass(2101,true);

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        bodyP4P.put("expDate", "2212");

        P4P p4p = new P4P(bodyP4P);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("300",true);
        Assert.assertEquals("00",p4p.getErrorCode());

        p4p.P4Ppayment("400",true);
        Assert.assertEquals(p4p.getErrorCode(),"00");

        Assert.assertEquals(checkCF10(p4p.getSessionId()),true);

        logFinishTest("positiveP4P_mc");
    }

    @AfterTest
    public void closeConn() throws SQLException {
        BDas400.closeConn();
    }

    private void activeMasterpass(int acqId, boolean action) throws SQLException {

        //Проверка активации МастерПасса
        ResultSet res = BDas400.selectSQL("SELECT VCVALUE FROM itm22d.VMTCONNVL WHERE VCOWNERID = "+acqId+" AND VCKEY = 'MASTERPASS'");
        res.next();
        String value = res.getString(1);

        if(value.equals("true") && action){
            logger.info("МастерПасс уже был активирован для "+acqId);
        }else if(value.equals("false") && !action){
            logger.info("МастерПасс уже был де-активирован для "+acqId);
        }else{
            BDas400.updateSQL("UPDATE itm22d.VMTCONNVL\n" +
                    "SET vcvalue='"+action+"'\n" +
                    "WHERE VCOWNER = 'ACQUIRER'\n" +
                    "AND VCKEY='MASTERPASS'\n" +
                    "AND VCOWNERID="+acqId+"");
            logger.info("Значение МастерПасса установлено - "+action+" для "+acqId);
        }

    }

    private boolean checkCF10(String sessionId) throws SQLException {

        ResultSet res = BDas400.selectSQL("SELECT substr(zp.WSPARM, 15,2),zp.WSPARM FROM ASID144402.ZWSPLG0P zp\n" +
                "WHERE substr(zp.WSPARM, 15,2) = 'FX'\n" +
                "AND zp.WSPARM LIKE '%"+sessionId+"%'\n" +
                "ORDER BY zp.WSDATE DESC, zp.WSTIME DESC");
        res.next();
        String wsparm = res.getString("WSPARM");

        char [] ch = wsparm.toCharArray();
        char [] word = new char[10];
        int i = 517;
        int j = i;
        int t = 0;

        while (j<i+10){
            word[t] = ch[j];
            j++;
            t++;
        }
        String check = new String(word);
        return check.contains("CF10");

    }
}
