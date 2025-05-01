package test.backTests.itm;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.P4P;
import org.example.qaTransactionTeam.backEnd.itm.Refund;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

//Отмена полная или части списания разорванного P2P (P4P)
public class PAYH_7940 extends BaseTest {

    private String card = Cards_data.getData(Card.FUIB_VISA, Card_param.pan);
    private String expDate = Cards_data.getData(Card.FUIB_VISA, Card_param.expire);;
    private JSONObject responseJson;

    @Test
    public void positiveOneRefundAndTwoTrans() throws JSONException {
        logStartTest("positiveOneRefundAndTwoTrans");

        Map<String, String> body = new HashMap<>();
        body.put("senderCardNumber", card);
        body.put("receiverCardNumber", card);
        body.put("expDate", expDate);
        body.put("amount", "1000");
        body.put("operationId", "119");
        body.put("details.source","01");
        body.put("details.merchantUrl","merchantUrl");
        body.put("details.paymentUrl","paymentUrl");
        body.put("details.additionalMessage","additionalMessage");
        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");

        //Регистрация Р4Р
        P4P p4p = new P4P(body, Cards_data.getData(Card.FUIB_VISA, Card_param.cvv));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Выполнение рефанда
        Refund refund = new Refund(p4p.getSessionId());
        refund.makeRefund("100",expDate,Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("amount"),"100");
        //Assert.assertEquals(responseJson.getString("type"),"REFUND");

        //Зачисление по Р4Р
        p4p.P4Ppayment("400",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"400");
        p4p.P4Ppayment("500",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"500");

        logFinishTest("positiveOneRefundAndTwoTrans");
    }

    @Test
    public void positiveThreeRefundsAndTwoTrans() throws JSONException {
        logStartTest("positiveThreeRefundsAndTwoTrans");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Выполнение рефанда
        Refund refund = new Refund(p4p.getSessionId());
        refund.makeRefund("150","2211",Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("amount"),"150");
        Assert.assertEquals(responseJson.getString("type"),"REFUND");
        refund.makeRefund("200","2211",Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("amount"),"200");
        Assert.assertEquals(responseJson.getString("type"),"REFUND");
        refund.makeRefund("250","2211",Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("amount"),"250");
        Assert.assertEquals(responseJson.getString("type"),"REFUND");

        //Зачисление по Р4Р
        p4p.P4Ppayment("100",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"100");
        p4p.P4Ppayment("300",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"300");

        logFinishTest("positiveThreeRefundsAndTwoTrans");
    }

    @Test
    public void positiveRegress() throws JSONException {
        logStartTest("positiveRegress");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Зачисление по Р4Р
        p4p.P4Ppayment("400",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"400");
        p4p.P4Ppayment("500",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"500");

        logFinishTest("positiveRegress");
    }

    @Test
    public void negativeOneRefundAndOneTransSummMore() throws JSONException {
        logStartTest("negativeOneRefundAndOneTransSummMore");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Выполнение рефанда
        Refund refund = new Refund(p4p.getSessionId());
        refund.makeRefund("100","2211",Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("amount"),"100");
        Assert.assertEquals(responseJson.getString("type"),"REFUND");

        //Зачисление по Р4Р
        p4p.P4Ppayment("950",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"IS007");
        Assert.assertEquals(responseJson.getString("errorMessage"),"Not enough credit amount");

        logFinishTest("negativeOneRefundAndOneTransSummMore");
    }

    @Test
    public void negativeRefundSummMoreAndOneTrans() throws JSONException {
        logStartTest("negativeRefundSummMoreAndOneTrans");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Выполнение рефанда
        Refund refund = new Refund(p4p.getSessionId());
        refund.setStatusCode(400);
        refund.makeRefund("2000","2211",Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        responseJson = new JSONObject(refund.getRefundInfo()).getJSONObject("status");
        Assert.assertEquals(responseJson.getString("code"),"RF006");
        Assert.assertEquals(responseJson.getString("description"),"Invalid amount");

        //Зачисление по Р4Р
        p4p.P4Ppayment("1000",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"1000");

        logFinishTest("negativeRefundSummMoreAndOneTrans");
    }

    @Test
    public void negativeRegressThreeTrans() throws JSONException {
        logStartTest("negativeRegressThreeTrans");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Зачисление по Р4Р
        p4p.P4Ppayment("400",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"400");
        p4p.P4Ppayment("500",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"500");
        p4p.P4Ppayment("50",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"IS999");
        Assert.assertEquals(responseJson.getString("errorMessage"),"01: getRecordFromSession - technical error.");

        logFinishTest("negativeRegressThreeTrans");
    }

    @Test
    public void positiveOneRefundOnOtherCard() throws JSONException {
        logStartTest("positiveOneRefundOnOtherCard");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Выполнение рефанда
        Refund refund = new Refund(p4p.getSessionId());
        if(card.equals(Cards_data.getData(Card.FUIB_MC, Card_param.pan))){
            refund.makeRefund("100","2211",Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        }else if(card.equals(Cards_data.getData(Card.FUIB_VISA, Card_param.pan))) {
            refund.makeRefund("100", "2212", Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        }

        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("amount"),"100");
        Assert.assertEquals(responseJson.getString("type"),"REFUND");

        //Зачисление по Р4Р
        p4p.P4Ppayment("400",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"400");
        p4p.P4Ppayment("450",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"450");

        logFinishTest("positiveOneRefundOnOtherCard");
    }

    @Test
    public void negativeOneRefundOnOtherCard() throws JSONException {
        logStartTest("negativeOneRefundOnOtherCard");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Выполнение рефанда
        Refund refund = new Refund(p4p.getSessionId());
        refund.setStatusCode(400);
        if(card.equals(Cards_data.getData(Card.FUIB_MC, Card_param.pan))){
            refund.makeRefund("100","2311",Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        }else if(card.equals(Cards_data.getData(Card.FUIB_VISA, Card_param.pan))) {
            refund.makeRefund("100", "2312", Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        }

        responseJson = new JSONObject(refund.getRefundInfo()).getJSONObject("status");
        Assert.assertEquals(responseJson.getString("code"),"RFT56");
        Assert.assertEquals(responseJson.getString("description"),"Fail to make refund");

        //Зачисление по Р4Р
        p4p.P4Ppayment("400",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"400");
        p4p.P4Ppayment("450",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"450");

        logFinishTest("negativeOneRefundOnOtherCard");
    }

    @Test
    public void positiveOneRefundWithoutCard() throws JSONException {
        logStartTest("positiveOneRefundWithoutCard");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Выполнение рефанда
        Refund refund = new Refund(p4p.getSessionId());
        refund.makeRefund("100");
        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("amount"),"100");
        Assert.assertEquals(responseJson.getString("type"),"REFUND");

        //Зачисление по Р4Р
        p4p.P4Ppayment("400",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"400");
        p4p.P4Ppayment("450",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"450");

        logFinishTest("positiveOneRefundWithoutCard");
    }

    @Test
    public void positiveFirstTransThenRefund() throws JSONException {
        logStartTest("positiveFirstTransThenRefund");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Регистрация рефанда
        Refund refund = new Refund(p4p.getSessionId());

        //Зачисление по Р4Р
        p4p.P4Ppayment("400",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"400");

        //Выполнение рефанда
        refund.makeRefund("100");
        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("amount"),"100");
        Assert.assertEquals(responseJson.getString("type"),"REFUND");

        //Зачисление по Р4Р
        p4p.P4Ppayment("500",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"500");

        logFinishTest("positiveFirstTransThenRefund");
    }

    @Test
    public void positiveFirstTwoTransThenRefund() throws JSONException {
        logStartTest("positiveFirstTwoTransThenRefund");

        Map<String, String> body = new HashMap<>();
        body.put("senderCardNumber", card);
        body.put("receiverCardNumber", card);
        body.put("expDate", expDate);
        body.put("amount", "1000");
        body.put("operationId", "119");
        body.put("details.source","01");
        body.put("details.merchantUrl","merchantUrl");
        body.put("details.paymentUrl","paymentUrl");
        body.put("details.additionalMessage","additionalMessage");
        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");

        //Регистрация Р4Р
        P4P p4p = new P4P(body,Cards_data.getData(Card.FUIB_VISA, Card_param.cvv));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Регистрация рефанда
        Refund refund = new Refund(p4p.getSessionId());

        //Зачисление по Р4Р
        p4p.P4Ppayment("400",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"400");
        p4p.P4Ppayment("500",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"500");

        //Выполнение рефанда
        refund.makeRefund("100");
        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("amount"),"100");
        Assert.assertEquals(responseJson.getString("type"),"REFUND");

        logFinishTest("positiveFirstTwoTransThenRefund");
    }

    @Test
    public void negativeFirstTwoTransThenRefund() throws JSONException {
        logStartTest("negativeFirstTwoTransThenRefund");

        Map<String, String> body = new HashMap<>();
        body.put("senderCardNumber", card);
        body.put("receiverCardNumber", card);
        body.put("expDate", expDate);
        body.put("amount", "1000");
        body.put("operationId", "119");
        body.put("details.source","01");
        body.put("details.merchantUrl","merchantUrl");
        body.put("details.paymentUrl","paymentUrl");
        body.put("details.additionalMessage","additionalMessage");
        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");

        //Регистрация Р4Р
        P4P p4p = new P4P(body,Cards_data.getData(Card.FUIB_VISA, Card_param.cvv));
        Assert.assertEquals(p4p.getErrorCode(),"00");

        //Регистрация рефанда
        Refund refund = new Refund(p4p.getSessionId());
        refund.setStatusCode(400);

        //Зачисление по Р4Р
        p4p.P4Ppayment("400",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"400");
        p4p.P4Ppayment("500",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"500");

        //Выполнение рефанда
        refund.makeRefund("200");
        responseJson = new JSONObject(refund.getRefundInfo()).getJSONObject("status");
        Assert.assertEquals(responseJson.getString("code"),"RF006");
        Assert.assertEquals(responseJson.getString("description"),"Invalid amount");

        logFinishTest("negativeFirstTwoTransThenRefund");
    }

    @Test
    public void negativeFirstOneTransThenRefundThenTwoTransAgain() throws JSONException {
        logStartTest("negativeFirstOneTransThenRefundThenTwoTransAgain");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals(p4p.getErrorCode(),"00");

        //Регистрация рефанда
        Refund refund = new Refund(p4p.getSessionId());

        //Зачисление по Р4Р
        p4p.P4Ppayment("100",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"100");

        //Выполнение рефанда
        refund.makeRefund("200");
        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("amount"),"200");
        Assert.assertEquals(responseJson.getString("type"),"REFUND");


        //Зачисление по Р4Р
        p4p.P4Ppayment("300",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"00");
        Assert.assertEquals(responseJson.getString("paymentNumber"),p4p.getSessionId());
        Assert.assertEquals(responseJson.getString("paymentAmount"),"300");
        p4p.P4Ppayment("350",false);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"IS999");
        Assert.assertEquals(responseJson.getString("errorMessage"),"01: getRecordFromSession - technical error.");


        logFinishTest("negativeFirstOneTransThenRefundThenTwoTransAgain");
    }

    @Test
    public void negativeAllSumRefundAndTrans() throws JSONException {
        logStartTest("negativeAllSumRefundAndTrans");

        Map<String, String> bodyP4P = new HashMap<>();
        bodyP4P.put("senderCardNumber", card);
        bodyP4P.put("amount", "1000");
        bodyP4P.put("operationId", "119");
        bodyP4P.put("customFee", "0");
        bodyP4P.put("receiverCardNumber", card);
        bodyP4P.put("expDate", expDate);

        //Регистрация Р4Р
        P4P p4p = new P4P(bodyP4P,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        Assert.assertEquals("00",p4p.getErrorCode());

        //Выполнение рефанда
        Refund refund = new Refund(p4p.getSessionId());
        refund.makeRefund("1000","2211",Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        responseJson = new JSONObject(refund.getRefundInfo());
        Assert.assertEquals(responseJson.getString("type"),"REVERSAL");

        //Зачисление по Р4Р
        p4p.P4Ppayment("1",true);
        responseJson = new JSONObject(p4p.getResponse());
        Assert.assertEquals(responseJson.getString("errorCode"),"IS007");
        Assert.assertEquals(responseJson.getString("errorMessage"),"Not enough credit amount");

        logFinishTest("negativeAllSumRefundAndTrans");
    }

}
