package test.backTests.mobyPay;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.mobyPay.MobyTrans;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PAYH_8221 extends BaseTest {
    //Поддержка ApplePay и GooglePay на стороне бэкенда Банка

    String payer;
    JSONObject json;
    JSONObject json1;
    MobyTrans trans;

    @Test
    public void allTypePayerWithoutHold() throws JSONException, InterruptedException {
        logStartTest("allTypePayerWithoutHold");


        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2212\"";
        trans = new MobyTrans("100",payer,false);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSING");
        Thread.sleep(5000);

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logger.info("GOOGLE_PAN - ОК");


        payer = "\"source\": \"GOOGLE_CRYPTOGRAM\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_VISA, Card_param.pan) +"\",\n" +
                "\t\t\t\t\t\t  \"cryptogram\": \"dsgfsdgdfg\",\n" +
                "              \"expire\": \"2211\"";
        trans = new MobyTrans("100",payer,false);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSING");
        Thread.sleep(5000);

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logger.info("GOOGLE_CRYPTOGRAM - ОК");


        payer = "\"source\": \"APPLE_CRYPTOGRAM\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "\t\t\t\t\t\t  \"cryptogram\": \"dsgfsdgdfg\",\n" +
                "              \"expire\": \"2212\"";
        trans = new MobyTrans("100",payer,false);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSING");
        Thread.sleep(5000);

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logger.info("APPLE_CRYPTOGRAM - ОК");


        logFinishTest("allTypePayerWithoutHold");
    }

    @Test
    public void positiveTransWithHold() throws JSONException {
        logStartTest("positiveTransWithHold");

        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2212\"";
        trans = new MobyTrans("100",payer,true);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.get("preauth"),true);
        Assert.assertEquals(json.get("amount"),100);

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.get("preauth"),true);
        Assert.assertEquals(json.get("amount"),100);

        logFinishTest("positiveTransWithHold");
    }

    @Test
    public void negativeTransWithHoldSum29() throws JSONException {
        logStartTest("negativeTransWithHoldSum29");

        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2212\"";

        trans = new MobyTrans("29",payer,true);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"FAILED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"29");

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"FAILED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"29");

        logFinishTest("negativeTransWithHoldSum29");
    }

    @Test
    public void positiveCompleteHoldAllSum() throws JSONException {
        logStartTest("positiveCompleteHoldAllSum");

        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2212\"";
        trans = new MobyTrans("100",payer,true);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"100");

        trans.complete_hold("100");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"100");

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"100");
        json = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json.getString("error_code"),"00");

        logFinishTest("positiveCompleteHoldAllSum");
    }

    @Test
    public void positiveCompleteHoldSummLess() throws JSONException {
        logStartTest("positiveCompleteHoldSummLess");

        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2212\"";
        trans = new MobyTrans("100",payer,true);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"100");

        trans.complete_hold("80");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"80");

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"80");
        json = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json.getString("error_code"),"00");

        logFinishTest("positiveCompleteHoldSummLess");
    }

    @Test
    public void negativeCompleteHoldSummMore() throws JSONException {
        logStartTest("negativeCompleteHoldSummMore");

        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2212\"";
        trans = new MobyTrans("100",payer,true);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"100");

        MobyTrans.responseCode = 400;
        trans.complete_hold("101");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("name"),"ValidationError");
        Assert.assertEquals(json.getString("code"),"AMOUNT_HIGHER_THAN_ALLOWED");

        MobyTrans.responseCode = 200;
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"100");

        logFinishTest("negativeCompleteHoldSummMore");
    }

    @Test
    public void positiveRefund21Trans() throws JSONException {
        logStartTest("positiveRefund21Trans");

        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2212\"";
        trans = new MobyTrans("100",payer,true);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertTrue(json.getBoolean("preauth"));
        Assert.assertEquals(json.getInt("amount"),100);

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertTrue(json.getBoolean("preauth"));
        Assert.assertEquals(json.getInt("amount"),100);

        trans.refund("100");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertTrue(json.getBoolean("preauth"));
        Assert.assertEquals(json.getInt("amount"),100);
        JSONArray array = new JSONObject(trans.getResponse()).getJSONArray("transfers");
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("transaction_type"),"25");
        Assert.assertEquals(json.getString("status"),"D");
        Assert.assertEquals(json.getString("error_code"),"31");
        Assert.assertEquals(json.getString("sum"),"0");

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertTrue(json.getBoolean("preauth"));
        Assert.assertEquals(json.getInt("amount"),100);
        json = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json.getString("error_code"),"31");
        Assert.assertEquals(json.getString("error_description"),"Hardware Fault");

        logFinishTest("positiveRefund21Trans");
    }

    @Test
    public void negativeRefund21Trans() throws JSONException {
        logStartTest("negativeRefund21Trans");

        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2212\"";
        trans = new MobyTrans("100",payer,true);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"100");

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"100");


        MobyTrans.responseCode = 400;
        trans.refund("99");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("name"),"ValidationError");
        Assert.assertEquals(json.getString("code"),"INCORRECT_AMOUNT");

        trans.refund("101");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("name"),"ValidationError");
        Assert.assertEquals(json.getString("code"),"AMOUNT_HIGHER_THAN_ALLOWED");

        MobyTrans.responseCode = 200;

        logFinishTest("negativeRefund21Trans");
    }

    @Test
    public void positiveRefund25Trans() throws JSONException, InterruptedException {
        logStartTest("positiveRefund25Trans");

        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_VISA, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2211\"";
        trans = new MobyTrans("1000",payer,false);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSING");
        Assert.assertEquals(json.getString("preauth"),"false");

        Thread.sleep(5000);
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        Assert.assertEquals(json.getString("preauth"),"false");
        Assert.assertEquals(json.getString("amount"),"1000");
        json = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json.getString("error_code"),"00");

        trans.refund("234");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"false");
        Assert.assertEquals(json.getString("amount"),"1000");
        JSONArray array = json.getJSONArray("vmt_refund_status");
        json1 = array.getJSONObject(0);
        Assert.assertEquals(json1.getString("error_code"),"00");
        array = json.getJSONArray("transfers");
        for (int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
            Assert.assertEquals(json.getString("status"),"A");
            if(json.getString("transaction_type").equals("25")){
                Assert.assertEquals(json.getString("sum"),"1000");
            }else {
                Assert.assertEquals(json.getString("sum"),"234");
            }
        }

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"false");
        Assert.assertEquals(json.getString("amount"),"1000");
        json1 = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json1.getString("error_code"),"00");
        array = json.getJSONArray("vmt_refund_status");
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("error_code"),"00");

        trans.refund("354");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"false");
        Assert.assertEquals(json.getString("amount"),"1000");
        array = json.getJSONArray("vmt_refund_status");
            for(int i = 0; i<array.length();i++){
            json1 = array.getJSONObject(i);
            Assert.assertEquals(json1.getString("error_code"),"00");
            }
        array = json.getJSONArray("transfers");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
            Assert.assertEquals(json.getString("status"),"A");
            String amount = json.getString("sum");
            Assert.assertTrue(amount.equals("354")||amount.equals("234")||amount.equals("1000"));
        }
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"false");
        Assert.assertEquals(json.getString("amount"),"1000");
        json1 = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json1.getString("error_code"),"00");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
        }

        trans.refund("100");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"false");
        Assert.assertEquals(json.getString("amount"),"1000");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json1 = array.getJSONObject(i);
            Assert.assertEquals(json1.getString("error_code"),"00");
        }
        array = json.getJSONArray("transfers");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
            Assert.assertEquals(json.getString("status"),"A");
            String amount = json.getString("sum");
            Assert.assertTrue(amount.equals("354")||amount.equals("234")||amount.equals("1000")||amount.equals("100"));
        }
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"false");
        Assert.assertEquals(json.getString("amount"),"1000");
        json1 = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json1.getString("error_code"),"00");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
        }

        trans.refund("500");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"false");
        Assert.assertEquals(json.getString("amount"),"1000");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json1 = array.getJSONObject(i);
            Assert.assertEquals(json1.getString("error_code"),"00");
        }
        array = json.getJSONArray("transfers");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
            Assert.assertEquals(json.getString("status"),"A");
            String amount = json.getString("sum");
            Assert.assertTrue(amount.equals("354")||amount.equals("234")||amount.equals("1000")||amount.equals("100"));
        }
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"false");
        Assert.assertEquals(json.getString("amount"),"1000");
        json1 = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json1.getString("error_code"),"00");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
        }


        logFinishTest("positiveRefund25Trans");
    }

    @Test
    public void positiveRefundAfterCompleteHold() throws JSONException, InterruptedException {
        logStartTest("positiveRefundAfterCompleteHold");

        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2212\"";
        trans = new MobyTrans("1000",payer,true);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");

        trans.complete_hold("1000");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");
        json = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json.getString("error_code"),"00");

        trans.refund("234");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");
        JSONArray array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json1 = array.getJSONObject(i);
            Assert.assertEquals(json1.getString("error_code"),"00");
        }
        array = json.getJSONArray("transfers");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
            Assert.assertEquals(json.getString("status"),"A");
            String amount = json.getString("sum");
            Assert.assertTrue(amount.equals("234")||amount.equals("1000"));
        }
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");
        json1 = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json1.getString("error_code"),"00");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
        }

        trans.refund("354");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json1 = array.getJSONObject(i);
            Assert.assertEquals(json1.getString("error_code"),"00");
        }
        array = json.getJSONArray("transfers");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
            Assert.assertEquals(json.getString("status"),"A");
            String amount = json.getString("sum");
            Assert.assertTrue(amount.equals("234")||amount.equals("354")||amount.equals("1000"));
        }
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");
        json1 = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json1.getString("error_code"),"00");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
        }

        trans.refund("100");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json1 = array.getJSONObject(i);
            Assert.assertEquals(json1.getString("error_code"),"00");
        }
        array = json.getJSONArray("transfers");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
            Assert.assertEquals(json.getString("status"),"A");
            String amount = json.getString("sum");
            Assert.assertTrue(amount.equals("234")||amount.equals("354")||amount.equals("100")||amount.equals("1000"));
        }
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");
        json1 = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json1.getString("error_code"),"00");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
        }

        trans.refund("500");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json1 = array.getJSONObject(i);
            Assert.assertEquals(json1.getString("error_code"),"00");
        }
        array = json.getJSONArray("transfers");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
            Assert.assertEquals(json.getString("status"),"A");
            String amount = json.getString("sum");
            Assert.assertTrue(amount.equals("234")||amount.equals("354")||amount.equals("100")||amount.equals("1000"));
        }
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");
        json1 = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json1.getString("error_code"),"00");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
        }

        logFinishTest("positiveRefundAfterCompleteHold");
    }

    @Test
    public void negativeRefundAfterCompleteHold() throws JSONException, InterruptedException {
        logStartTest("negativeRefundAfterCompleteHold");

        payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_VISA, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2211\"";
        trans = new MobyTrans("1000",payer,true);
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"1000");

        trans.complete_hold("500");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"500");

        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"500");
        json = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json.getString("error_code"),"00");

        MobyTrans.responseCode = 400;
        trans.refund("501");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("name"),"ValidationError");
        Assert.assertEquals(json.getString("code"),"AMOUNT_HIGHER_THAN_ALLOWED");

        MobyTrans.responseCode = 200;
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"500");
        json = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json.getString("error_code"),"00");

        trans.refund("500");
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"500");
        JSONArray array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json1 = array.getJSONObject(i);
            Assert.assertEquals(json1.getString("error_code"),"00");
        }
        array = json.getJSONArray("transfers");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
            Assert.assertEquals(json.getString("status"),"A");
            Assert.assertEquals(json.getString("sum"),"500");
        }
        trans.status();
        json = new JSONObject(trans.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        Assert.assertEquals(json.getString("preauth"),"true");
        Assert.assertEquals(json.getString("amount"),"500");
        json1 = new JSONObject(trans.getResponse()).getJSONObject("vmt_payment_status");
        Assert.assertEquals(json1.getString("error_code"),"00");
        array = json.getJSONArray("vmt_refund_status");
        for(int i = 0; i<array.length();i++){
            json = array.getJSONObject(i);
            Assert.assertEquals(json.getString("error_code"),"00");
        }

        logFinishTest("negativeRefundAfterCompleteHold");
    }
}
