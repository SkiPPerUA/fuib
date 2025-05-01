package test.backTests.applePayGooglePay;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.AppleGooglePay;
import org.example.qaTransactionTeam.backEnd.transaction.AppleGoogleReversalRefund;
import org.example.qaTransactionTeam.backEnd.itm.GetTransDetails;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RefundAppleGoogle extends BaseTest {

    private int amount = 200;
    private String body = "{\n" +
            "\t\"amount\":\""+amount+"\",\n" +
            "\t\t\"card_number\":\""+ Cards_data.getData(Card.FUIB_VISA, Card_param.pan) +"\",\n" +
            "\t\"experation_date\":\""+Cards_data.getData(Card.FUIB_VISA, Card_param.expire)+"\",\n" +
            "\t\"cvv\": \""+Cards_data.getData(Card.FUIB_VISA, Card_param.cvv)+"\", \n" +
            "\t\"ucaf\":\"AAABA5RZlAAAA5cZGFmUAAAAAAA=\",\n" +
            "\t\"ext_trans_id\": \"12wq3e49\",\n" +
            ThreeDS.with_threeDS_2_1_0_itm +
            "}\n";

    @Test
    public void positiveRefund_one_trans() throws JSONException {
        logStartTest("positiveRefund_one_trans");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        String session = pay.getSessionId();
        pay.finish(String.valueOf(amount));

        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        refund.refund(session,String.valueOf(amount));

        //Проверка ответа по рефанду
        JSONObject ob = new JSONObject(refund.getRefundInfo());
        String actAmount = ob.getString("amount");
        String actType = ob.getString("type");
        Assert.assertEquals(actAmount,String.valueOf(amount));
        Assert.assertEquals(actType,"REFUND");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(session);
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),2);

        for (int i = 0; i<arr.length(); i++){
            JSONObject ob1 = arr.getJSONObject(i);
            String actAction = ob1.getString("action");
            String actStatus = ob1.getString("status");
            String actCode = ob1.getString("error_code");
            String type = ob1.getString("transaction_type");
            Assert.assertEquals(actAction,session);
            Assert.assertEquals(actStatus,"A");
            Assert.assertEquals(actCode,"00");
            if(type.equals("23")){
                String actSum = ob1.getString("sum");
                Assert.assertEquals(actSum,String.valueOf(amount));
                logger.info("23 транзакция проверена");
            }
        }

        logFinishTest("positiveRefund_one_trans");

    }

    @Test
    public void positiveRefund_many_trans() throws JSONException {
        logStartTest("positiveRefund_many_trans");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount));

        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        for(int i = 0;i <4;i++) {
            refund.refund(pay.getSessionId(), String.valueOf(amount / 4));
        }

        //Проверка ответа по рефанду
        JSONObject ob = new JSONObject(refund.getRefundInfo());
        String actAmount = ob.getString("amount");
        String actType = ob.getString("type");
        Assert.assertEquals(actAmount,String.valueOf(amount/4));
        Assert.assertEquals(actType,"REFUND");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONArray arr = new JSONObject(details.getResponse()).getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),5);

        for (int i = 0; i<arr.length(); i++){
            JSONObject ob1 = arr.getJSONObject(i);
            String actAction = ob1.getString("action");
            String actStatus = ob1.getString("status");
            String actCode = ob1.getString("error_code");
            String type = ob1.getString("transaction_type");
            Assert.assertEquals(actAction,pay.getSessionId());
            Assert.assertEquals(actStatus,"A");
            Assert.assertEquals(actCode,"00");
            if(type.equals("23")){
                String actSum = ob1.getString("sum");
                Assert.assertEquals(actSum,String.valueOf(amount/4));
                logger.info("23 транзакция проверена");
            }
        }

        logFinishTest("positiveRefund_many_trans");

    }

    @Test
    public void negativeRefundSumMore() throws JSONException {
        logStartTest("negativeRefundSumMore");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount));

        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        //Ожидаемый статусКод
        refund.setStatusCode(400);
        refund.refund(pay.getSessionId(),String.valueOf(amount+1));

        //Проверка ответа по рефанду
        JSONObject ob = new JSONObject(refund.getRefundInfo()).getJSONObject("status");
        String actCode1 = ob.getString("code");
        String actDescription = ob.getString("description");
        Assert.assertEquals(actCode1,"RF006");
        Assert.assertEquals(actDescription,"Invalid amount");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONArray arr = new JSONObject(details.getResponse()).getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        for (int i = 0; i<arr.length(); i++){
            JSONObject ob1 = arr.getJSONObject(i);
            String actAction = ob1.getString("action");
            String actStatus = ob1.getString("status");
            String actCode = ob1.getString("error_code");
            String type = ob1.getString("transaction_type");
            Assert.assertEquals(actAction,pay.getSessionId());
            Assert.assertEquals(actStatus,"A");
            Assert.assertEquals(actCode,"00");
            Assert.assertEquals(type,"25");

        }

        logFinishTest("negativeRefundSumMore");

    }

    @Test
    public void negativeRefund_many_trans() throws JSONException {
        logStartTest("negativeRefund_many_trans");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount));

        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        for(int i = 0;i <3;i++) {
            if(i == 2){
                //Ожидаемый статусКод
                refund.setStatusCode(400);
                refund.refund(pay.getSessionId(), String.valueOf(amount / 2));
            }else {
                refund.refund(pay.getSessionId(), String.valueOf(amount / 2));
            }
        }

        //Проверка ответа по рефанду
        JSONObject ob = new JSONObject(refund.getRefundInfo()).getJSONObject("status");
        String actCode1 = ob.getString("code");
        String actDescription = ob.getString("description");
        Assert.assertEquals(actCode1,"RF006");
        Assert.assertEquals(actDescription,"Invalid amount");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONArray arr = new JSONObject(details.getResponse()).getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),3);

        for (int i = 0; i<arr.length(); i++){
            JSONObject ob1 = arr.getJSONObject(i);
            String actAction = ob1.getString("action");
            String actStatus = ob1.getString("status");
            String actCode = ob1.getString("error_code");
            String type = ob1.getString("transaction_type");
            Assert.assertEquals(actAction,pay.getSessionId());
            Assert.assertEquals(actStatus,"A");
            Assert.assertEquals(actCode,"00");
            if(type.equals("23")){
                String actSum = ob1.getString("sum");
                Assert.assertEquals(actSum,String.valueOf(amount/2));
                logger.info("23 транзакция проверена");
            }
        }

        logFinishTest("negativeRefund_many_trans");
    }

    @Test
    public void negativeRefundWithOutFinalize() throws JSONException {
        logStartTest("negativeRefundWithOutFinalize");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);

        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        //Ожидаемый статусКод
        refund.setStatusCode(400);
        refund.refund(pay.getSessionId(),String.valueOf(amount));

        //Проверка ответа по рефанду
        JSONObject ob = new JSONObject(refund.getRefundInfo()).getJSONObject("status");
        String actCode1 = ob.getString("code");
        String actDescription = ob.getString("description");
        Assert.assertEquals(actCode1,"RF004");
        Assert.assertEquals(actDescription,"Conditions are not meet to finalize");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONArray arr = new JSONObject(details.getResponse()).getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        for (int i = 0; i<arr.length(); i++){
            JSONObject ob1 = arr.getJSONObject(i);
            String actAction = ob1.getString("action");
            String actSum = ob1.getString("sum");
            String actStatus = ob1.getString("status");
            String actCode = ob1.getString("error_code");
            String type = ob1.getString("transaction_type");
            Assert.assertEquals(actAction,pay.getSessionId());
            Assert.assertEquals(actStatus,"A");
            Assert.assertEquals(actCode,"00");
            Assert.assertEquals(type,"21");
            Assert.assertEquals(actSum,String.valueOf(amount));

        }

        logFinishTest("negativeRefundWithOutFinalize");

    }

    @Test
    public void positiveRefundOtherCard() throws JSONException {
        logStartTest("positiveRefundOtherCard");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount));

        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        refund.refund(pay.getSessionId(),String.valueOf(amount),Cards_data.getData(Card.FUIB_VISA, Card_param.pan),"2211");

        //Проверка ответа по рефанду
        JSONObject ob = new JSONObject(refund.getRefundInfo());
        String actAmount = ob.getString("amount");
        String actType = ob.getString("type");
        Assert.assertEquals(actAmount,String.valueOf(amount));
        Assert.assertEquals(actType,"REFUND");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONArray arr = new JSONObject(details.getResponse()).getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),2);

        for (int i = 0; i<arr.length(); i++){
            JSONObject ob1 = arr.getJSONObject(i);
            String actAction = ob1.getString("action");
            String actStatus = ob1.getString("status");
            String actCode = ob1.getString("error_code");
            String type = ob1.getString("transaction_type");
            Assert.assertEquals(actAction,pay.getSessionId());
            Assert.assertEquals(actStatus,"A");
            Assert.assertEquals(actCode,"00");
            if(type.equals("23")){
                String actSum = ob1.getString("sum");
                String actCrd = ob1.getString("card_number");
                Assert.assertEquals(actCrd,"************9605");
                Assert.assertEquals(actSum,String.valueOf(amount));
                logger.info("23 транзакция проверена");
            }
        }

        logFinishTest("positiveRefundOtherCard");

    }

    @Test
    public void positiveRefundDifferentCard() throws JSONException {
        logStartTest("positiveRefundDifferentCard");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount));

        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        refund.refund(pay.getSessionId(),String.valueOf(amount/2));
        refund.refund(pay.getSessionId(),String.valueOf(amount/2),Cards_data.getData(Card.FUIB_MC, Card_param.pan),"2212");

        //Проверка ответа по рефанду
        JSONObject ob = new JSONObject(refund.getRefundInfo());
        String actAmount = ob.getString("amount");
        String actType = ob.getString("type");
        Assert.assertEquals(actAmount,String.valueOf(amount/2));
        Assert.assertEquals(actType,"REFUND");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONArray arr = new JSONObject(details.getResponse()).getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),3);

        for (int i = 0; i<arr.length(); i++){
            JSONObject ob1 = arr.getJSONObject(i);
            String type = ob1.getString("transaction_type");
            if(type.equals("23")){
                String actSum = ob1.getString("sum");
                String actCrd = ob1.getString("card_number");
                boolean visa = false;
                boolean mc = false;
                if(actCrd.equals("************9605")){
                    visa = true;
                }else if(actCrd.equals("************1447")){
                    mc = true;
                }else {
                    logger.error("Карты не найдены Виза - "+visa+" Mc - "+mc);
                }
                Assert.assertEquals(actSum,String.valueOf(amount/2));
                logger.info("23 транзакция проверена");
            }
        }

        logFinishTest("positiveRefundDifferentCard");

    }

    @Test
    public void negativeRefundAfterReversal() throws JSONException {

        logStartTest("negativeRefundAfterReversal");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount));

        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        reversal.reversal(pay.getSessionId(),String.valueOf(amount));
        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo());
        String actAmount = ob.getString("amount");
        Assert.assertEquals(actAmount,String.valueOf(amount));


        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        refund.setStatusCode(400);
        refund.refund(pay.getSessionId(),"1");
        //Проверка ответа по рефанду
        JSONObject ob3 = new JSONObject(refund.getRefundInfo()).getJSONObject("status");
        String actCode3 = ob3.getString("code");
        String actDescription = ob3.getString("description");
        Assert.assertEquals(actCode3,"RF006");
        Assert.assertEquals(actDescription,"Invalid amount");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        JSONObject ob1 = arr.getJSONObject(0);
        String actAction = ob1.getString("action");
        String actStatus = ob1.getString("status");
        String actCode = ob1.getString("error_code");
        String actSum = ob1.getString("sum");
        Assert.assertEquals(actAction,pay.getSessionId());
        Assert.assertEquals(actStatus,"D");
        Assert.assertEquals(actCode,"31");
        Assert.assertEquals(actSum,"0");

        logFinishTest("negativeRefundAfterReversal");
    }


}
