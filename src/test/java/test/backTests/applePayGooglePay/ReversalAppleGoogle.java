package test.backTests.applePayGooglePay;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.AppleGooglePay;
import org.example.qaTransactionTeam.backEnd.transaction.AppleGoogleReversalRefund;
import org.example.qaTransactionTeam.backEnd.itm.GetTransDetails;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReversalAppleGoogle extends BaseTest {

    private int amount = 200;

    private String body = "{\n" +
            "\t\"amount\":\""+amount+"\",\n" +
            "\t\t\"card_number\":\""+ Cards_data.getData(Card.FUIB_VISA, Card_param.pan) +"\",\n" +
            "\t\"experation_date\":\""+ Cards_data.getData(Card.FUIB_VISA, Card_param.expire)+"\",\n" +
            "\t\"cvv\": \""+ Cards_data.getData(Card.FUIB_VISA, Card_param.cvv)+"\", \n" +
            "\t\"ucaf\":\"AAABA5RZlAAAA5cZGFmUAAAAAAA=\",\n" +
            "\t\"ext_trans_id\": \"12wq3e49\",\n" +
            ThreeDS.with_threeDS_2_1_0_itm +
            "}\n";

    @Test (priority = 1)
    public void positiveReversalAfterRegister() throws JSONException {

        logStartTest("positiveReversalAfterRegister");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        reversal.reversal(pay.getSessionId(),String.valueOf(amount));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo());
        String actAmount = ob.getString("amount");
        Assert.assertEquals(actAmount,String.valueOf(amount));

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

        logFinishTest("positiveReversalAfterRegister");
    }

    @Test (priority = 2)
    public void positiveReversalAfterFinalize() throws JSONException {

        logStartTest("positiveReversalAfterFinalize");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount));
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        reversal.reversal(pay.getSessionId(),String.valueOf(amount));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo());
        String actAmount = ob.getString("amount");
        Assert.assertEquals(actAmount,String.valueOf(amount));

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

        logFinishTest("positiveReversalAfterFinalize");
    }

    @Test (priority = 3)
    public void negativeReversalAfterRegisterSumLess() throws JSONException {

        logStartTest("negativeReversalAfterRegisterSumLess");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        //Ожидаемый статусКод
        reversal.setStatusCode(400);

        reversal.reversal(pay.getSessionId(),String.valueOf(amount-1));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo()).getJSONObject("status");
        String actCode = ob.getString("code");
        Assert.assertEquals(actCode,"RF016");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        JSONObject ob1 = arr.getJSONObject(0);
        String actAction = ob1.getString("action");
        String actStatus = ob1.getString("status");
        String actCode1 = ob1.getString("error_code");
        String actSum = ob1.getString("sum");
        Assert.assertEquals(actAction,pay.getSessionId());
        Assert.assertEquals(actStatus,"A");
        Assert.assertEquals(actCode1,"00");
        Assert.assertEquals(actSum,String.valueOf(amount));

        logFinishTest("negativeReversalAfterRegisterSumLess");
    }

    @Test (priority = 4)
    public void negativeReversalAfterRegisterSumMore() throws JSONException {

        logStartTest("negativeReversalAfterRegisterSumMore");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        //Ожидаемый статусКод
        reversal.setStatusCode(400);

        reversal.reversal(pay.getSessionId(),String.valueOf(amount+1));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo()).getJSONObject("status");
        String actCode = ob.getString("code");
        Assert.assertEquals(actCode,"RF016");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        JSONObject ob1 = arr.getJSONObject(0);
        String actAction = ob1.getString("action");
        String actStatus = ob1.getString("status");
        String actCode1 = ob1.getString("error_code");
        String actSum = ob1.getString("sum");
        Assert.assertEquals(actAction,pay.getSessionId());
        Assert.assertEquals(actStatus,"A");
        Assert.assertEquals(actCode1,"00");
        Assert.assertEquals(actSum,String.valueOf(amount));

        logFinishTest("negativeReversalAfterRegisterSumMore");
    }

    @Test (priority = 5)
    public void negativeReversalAfterFinalizeSumLess() throws JSONException {

        logStartTest("negativeReversalAfterFinalizeSumLess");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        //Ожидаемый статусКод
        reversal.setStatusCode(400);

        reversal.reversal(pay.getSessionId(),String.valueOf(amount-1));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo()).getJSONObject("status");
        String actCode = ob.getString("code");
        Assert.assertEquals(actCode,"RF016");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        JSONObject ob1 = arr.getJSONObject(0);
        String actAction = ob1.getString("action");
        String actStatus = ob1.getString("status");
        String actCode1 = ob1.getString("error_code");
        String actSum = ob1.getString("sum");
        Assert.assertEquals(actAction,pay.getSessionId());
        Assert.assertEquals(actStatus,"A");
        Assert.assertEquals(actCode1,"00");
        Assert.assertEquals(actSum,String.valueOf(amount));

        logFinishTest("negativeReversalAfterFinalizeSumLess");
    }

    @Test (priority = 6)
    public void negativeReversalAfterFinalizeSumMore() throws JSONException {

        logStartTest("negativeReversalAfterFinalizeSumMore");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        //Ожидаемый статусКод
        reversal.setStatusCode(400);

        reversal.reversal(pay.getSessionId(),String.valueOf(amount+1));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo()).getJSONObject("status");
        String actCode = ob.getString("code");
        Assert.assertEquals(actCode,"RF016");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        JSONObject ob1 = arr.getJSONObject(0);
        String actAction = ob1.getString("action");
        String actStatus = ob1.getString("status");
        String actCode1 = ob1.getString("error_code");
        String actSum = ob1.getString("sum");
        Assert.assertEquals(actAction,pay.getSessionId());
        Assert.assertEquals(actStatus,"A");
        Assert.assertEquals(actCode1,"00");
        Assert.assertEquals(actSum,String.valueOf(amount));

        logFinishTest("negativeReversalAfterFinalizeSumMore");
    }

    @Test (priority = 7)
    public void negativeReversalAfterFinalizeSumDifferent() throws JSONException {

        logStartTest("negativeReversalAfterFinalizeSumDifferent");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount - 50));
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        //Ожидаемый статусКод
        reversal.setStatusCode(400);

        reversal.reversal(pay.getSessionId(),String.valueOf(amount));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo()).getJSONObject("status");
        String actCode = ob.getString("code");
        Assert.assertEquals(actCode,"RF016");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        JSONObject ob1 = arr.getJSONObject(0);
        String actAction = ob1.getString("action");
        String actStatus = ob1.getString("status");
        String actCode1 = ob1.getString("error_code");
        String actSum = ob1.getString("sum");
        Assert.assertEquals(actAction,pay.getSessionId());
        Assert.assertEquals(actStatus,"A");
        Assert.assertEquals(actCode1,"00");
        Assert.assertEquals(actSum,String.valueOf(amount - 50));

        logFinishTest("negativeReversalAfterFinalizeSumDifferent");
    }

    @Test (priority = 8)
    public void negativeReversalAfterFinalizeSumDifferentAndLess() throws JSONException {

        logStartTest("negativeReversalAfterFinalizeSumDifferentAndLess");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount - 50));
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        //Ожидаемый статусКод
        reversal.setStatusCode(400);

        reversal.reversal(pay.getSessionId(),String.valueOf(amount - 51));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo()).getJSONObject("status");
        String actCode = ob.getString("code");
        Assert.assertEquals(actCode,"RF016");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        JSONObject ob1 = arr.getJSONObject(0);
        String actAction = ob1.getString("action");
        String actStatus = ob1.getString("status");
        String actCode1 = ob1.getString("error_code");
        String actSum = ob1.getString("sum");
        Assert.assertEquals(actAction,pay.getSessionId());
        Assert.assertEquals(actStatus,"A");
        Assert.assertEquals(actCode1,"00");
        Assert.assertEquals(actSum,String.valueOf(amount - 50));

        logFinishTest("negativeReversalAfterFinalizeSumDifferentAndLess");
    }

    @Test (priority = 9)
    public void positiveReversalAfterFinalizeSumDifferent() throws JSONException {

        logStartTest("positiveReversalAfterFinalizeSumDifferent");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount - 50));
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        reversal.reversal(pay.getSessionId(),String.valueOf(amount - 50));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo());
        String actAmount = ob.getString("amount");
        Assert.assertEquals(actAmount,String.valueOf(amount-50));

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

        logFinishTest("positiveReversalAfterFinalizeSumDifferent");
    }

    @Test (priority = 10)
    public void positiveReversalAfterRegisterAfterOneHour() throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        logStartTest("positiveReversalAfterRegisterAfterOneHour");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        changeTime(pay.getSessionId(),21);
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        reversal.reversal(pay.getSessionId(),String.valueOf(amount));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo());
        String actAmount = ob.getString("amount");
        Assert.assertEquals(actAmount,String.valueOf(amount));

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

        logFinishTest("positiveReversalAfterRegisterAfterOneHour");
    }

    @Test (priority = 11)
    public void negativeReversalAfterFinalizeAfterOneHour() throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        logStartTest("negativeReversalAfterFinalizeAfterOneHour");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount));
        changeTime(pay.getSessionId(),25);
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        //Ожидаемый статусКод
        reversal.setStatusCode(400);

        reversal.reversal(pay.getSessionId(),String.valueOf(amount));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo()).getJSONObject("status");
        String actCode = ob.getString("code");
        Assert.assertEquals(actCode,"RF019");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        JSONObject ob1 = arr.getJSONObject(0);
        String actAction = ob1.getString("action");
        String actStatus = ob1.getString("status");
        String actCode1 = ob1.getString("error_code");
        String actSum = ob1.getString("sum");
        Assert.assertEquals(actAction,pay.getSessionId());
        Assert.assertEquals(actStatus,"A");
        Assert.assertEquals(actCode1,"00");
        Assert.assertEquals(actSum,String.valueOf(amount));

        logFinishTest("negativeReversalAfterFinalizeAfterOneHour");
    }

    @Test (priority = 12)
    public void negativeReversalTwice() throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        logStartTest("negativeReversalTwice");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount));
        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        reversal.reversal(pay.getSessionId(),String.valueOf(amount));

        //Ожидаемый статусКод
        reversal.setStatusCode(400);
        reversal.reversal(pay.getSessionId(),String.valueOf(amount));

        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo()).getJSONObject("status");
        String actCode = ob.getString("code");
        Assert.assertEquals(actCode,"RF006");

        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),1);

        JSONObject ob1 = arr.getJSONObject(0);
        String actAction = ob1.getString("action");
        String actStatus = ob1.getString("status");
        String actCode1 = ob1.getString("error_code");
        String actSum = ob1.getString("sum");
        Assert.assertEquals(actAction,pay.getSessionId());
        Assert.assertEquals(actStatus,"D");
        Assert.assertEquals(actCode1,"31");
        Assert.assertEquals(actSum,"0");

        logFinishTest("negativeReversalTwice");
    }

    @Test (priority = 13)
    public void negativeReversalAfterRefund() throws JSONException {

        logStartTest("negativeReversalAfterRefund");

        AppleGooglePay pay = new AppleGooglePay();
        //pay.register(body,0);
        pay.finish(String.valueOf(amount));

        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        refund.refund(pay.getSessionId(),"1");
        //Проверка ответа по рефанду
        JSONObject ob3 = new JSONObject(refund.getRefundInfo());
        String actAmount = ob3.getString("amount");
        String actType = ob3.getString("type");
        Assert.assertEquals(actAmount,"1");
        Assert.assertEquals(actType,"REFUND");

        AppleGoogleReversalRefund reversal = new AppleGoogleReversalRefund();
        //Ожидаемый статус код
        reversal.setStatusCode(400);

        reversal.reversal(pay.getSessionId(),String.valueOf(amount));
        //Проверка ответа по реверсалу
        JSONObject ob = new JSONObject(reversal.getReversalInfo()).getJSONObject("status");
        String actCode = ob.getString("code");
        Assert.assertEquals(actCode,"RF006");


        //Проверка Api get-transfers
        GetTransDetails details = new GetTransDetails(pay.getSessionId());
        JSONObject ob2 = new JSONObject(details.getResponse());
        JSONArray arr = ob2.getJSONObject("data").getJSONArray("transfers_table");
        Assert.assertEquals(arr.length(),2);

        for (int i = 0; i<arr.length(); i++){
            JSONObject ob1 = arr.getJSONObject(i);
            String actAction = ob1.getString("action");
            String actStatus = ob1.getString("status");
            String actCode1 = ob1.getString("error_code");
            String type = ob1.getString("transaction_type");
            Assert.assertEquals(actAction,pay.getSessionId());
            Assert.assertEquals(actStatus,"A");
            Assert.assertEquals(actCode1,"00");
            if(type.equals("23")){
                String actSum = ob1.getString("sum");
                Assert.assertEquals(actSum,"1");
                logger.info("23 транзакция проверена");
            }
        }

        logFinishTest("negativeReversalAfterRefund");
    }


    private void changeTime(String session,int type) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        BDas400.BDas400("ITMTST", Configs.ITMTST_ALL_NAME, Configs.ITMTST_ALL_PASSWORD);
        ResultSet res;
        if(type == 21){
             res = BDas400.selectSQL("SELECT * FROM itm22d.VMTCONNVLT v WHERE VCOWNER='TRANSACTION'" +
                    "AND VCKEY = 'PURCHASE_21_DATE' AND VCOWNERID='"+session+"'");
        }else {
             res = BDas400.selectSQL("SELECT * FROM itm22d.VMTCONNVLT v WHERE VCOWNER='TRANSACTION'" +
                    "AND VCKEY = 'FINALIZE_25_DATE' AND VCOWNERID='" + session + "'");
        }
        res.next();
        String old = res.getString("VCVALUE");

        char [] ch = old.toCharArray();
        int t;
        if(ch[12] == '0'){
            t = 9;
        }else {
            t = Integer.parseInt(String.valueOf(ch[12])) - 1;
        }

        StringBuilder bd = new StringBuilder(old);
        bd.replace(12,13,String.valueOf(t));

        if(type == 21){
            BDas400.updateSQL("UPDATE itm22d.VMTCONNVLT SET VCVALUE = '"+bd+"' where VCOWNER='TRANSACTION'AND VCKEY = 'PURCHASE_21_DATE' AND VCOWNERID='"+session+"'");
        }else {
            BDas400.updateSQL("UPDATE itm22d.VMTCONNVLT SET VCVALUE = '"+bd+"' where VCOWNER='TRANSACTION'AND VCKEY = 'PURCHASE_21_DATE' AND VCOWNERID='"+session+"'");
        }

        logger.info("Время транзакции "+session+" изменено с "+old+" на "+bd);
        BDas400.closeConn();
    }


}
