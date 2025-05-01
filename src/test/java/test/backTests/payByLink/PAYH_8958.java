package test.backTests.payByLink;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payByLink.CardPayByLink;
import org.example.qaTransactionTeam.backEnd.payByLink.InvoiceBusiness;
import org.example.qaTransactionTeam.backEnd.payByLink.InvoiceP4P;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PAYH_8958 extends BaseTest {

    String body;
    JSONObject json;
    JSONArray array;
    InvoiceBusiness invoiceBusiness;
    InvoiceP4P invoiceP4P;
    String merchant_config_id = "fd4af5db-2a6a-4175-b6b6-41249ad7c70a";
    int amount = 274;
    CardPayByLink card = new CardPayByLink();

    @Test
    public void createInvoiceBusinessDisposable(){
        logStartTest("createInvoiceBusinessDisposable");
        String timeStamp = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());

        body = "\"amount\": "+amount+",\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \""+(Integer.parseInt(timeStamp)+1)+"-10-10T16:58:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \""+merchant_config_id+"\"\n";

        invoiceBusiness = new InvoiceBusiness(body,false);
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("id"),invoiceBusiness.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"ACQUIRING");
        Assert.assertEquals(json.getString("description"),"VladAutomation");
        logFinishTest("createInvoiceBusinessDisposable");
    }

    @Test
    public void createInvoiceBusinessDisposableFalse(){
        logStartTest("createInvoiceBusinessDisposableFalse");
        body = "\"amount\": "+amount+",\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": null,\n" +
                "  \"disposable\": false,\n" +
                "  \"merchant_config_id\": \""+merchant_config_id+"\"\n";
        invoiceBusiness = new InvoiceBusiness(body,false);
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("id"),invoiceBusiness.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"ACQUIRING");
        Assert.assertEquals(json.getString("description"),"VladAutomation");
        logFinishTest("createInvoiceBusinessDisposableFalse");
    }

    @Test
    public void getInvoiceStatusBusiness(){
        logStartTest("getInvoiceStatusBusiness");
        createInvoiceBusinessDisposable();

        invoiceBusiness.statusInvoice("/"+invoiceBusiness.getInvoiceId()+"");
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("id"),invoiceBusiness.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"ACQUIRING");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        invoiceBusiness.statusInvoice("?status=CREATED");
        array = new JSONArray(invoiceBusiness.getResponse());
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("id"),invoiceBusiness.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"ACQUIRING");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        invoiceBusiness.statusInvoice("?amount=274");
        array = new JSONArray(invoiceBusiness.getResponse());
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("id"),invoiceBusiness.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"ACQUIRING");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        invoiceBusiness.statusInvoice("?external_id=VladAuto:"+invoiceBusiness.getExternalId()+"");
        array = new JSONArray(invoiceBusiness.getResponse());
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("id"),invoiceBusiness.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"ACQUIRING");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        invoiceBusiness.statusInvoice("?merchant_config_id="+merchant_config_id+"");
        array = new JSONArray(invoiceBusiness.getResponse());
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("id"),invoiceBusiness.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"ACQUIRING");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        invoiceBusiness.statusInvoice("");
        array = new JSONArray(invoiceBusiness.getResponse());
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("id"),invoiceBusiness.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"ACQUIRING");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        logFinishTest("getInvoiceStatusBusiness");
    }

    @Test
    public void changeTTLBusiness(){
        logStartTest("changeTTLBusiness");
        String timeStamp = new SimpleDateFormat("yyyy-MM-").format(Calendar.getInstance().getTime());
        String dayStamp = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        String day;
        if(Integer.parseInt(dayStamp) < 9){
            day = "0"+(Integer.parseInt(dayStamp)+1);
        }else if (Integer.parseInt(dayStamp) > 8 && Integer.parseInt(dayStamp) < 30){
            day = String.valueOf(Integer.parseInt(dayStamp) + 1);
        }else {
            day = "05";
        }
        createInvoiceBusinessDisposable();
        invoiceBusiness.statusInvoice("/"+invoiceBusiness.getInvoiceId()+"");
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("id"),invoiceBusiness.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"ACQUIRING");
        Assert.assertEquals(json.getString("description"),"VladAutomation");
        Assert.assertEquals(json.getString("valid_till"),""+timeStamp+day+"T16:58:00.000Z");
        day = String.valueOf(Integer.parseInt(day)+1);
        invoiceBusiness.changeTTL(""+timeStamp+day+"T16:58:00.000Z");
        invoiceBusiness.statusInvoice("/"+invoiceBusiness.getInvoiceId()+"");
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("id"),invoiceBusiness.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"ACQUIRING");
        Assert.assertEquals(json.getString("description"),"VladAutomation");
        Assert.assertEquals(json.getString("valid_till"),""+timeStamp+day+"T16:58:00.000Z");
        logFinishTest("changeTTLBusiness");
    }

    @Test
    public void partiallyRefundBusiness(){
        logStartTest("partiallyRefundBusiness");
        createInvoiceBusinessDisposable();
        invoiceBusiness.payInvoice();
        invoiceBusiness.statusTransactionByInvoice();
        array = invoiceBusiness.getTransactions();
        String transId = array.getJSONObject(0).getString("id");
        logger.info("Trans ID = "+transId);
        body = "{\n" +
                "  \"transaction_id\": \""+transId+"\",\n" +
                "  \"amount\": 200,\n" +
                "  \"comment\": \"частичное\"\n" +
                "}";
        invoiceBusiness.refundInvoice(body);
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getInt("amount"),200);
        invoiceBusiness.statusInvoice("/"+invoiceBusiness.getInvoiceId()+"");
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("status"),"PARTIALLY_REFUNDED");
        logFinishTest("partiallyRefundBusiness");
    }

    @Test
    public void allRefundBusiness(){
        logStartTest("allRefundBusiness");
        createInvoiceBusinessDisposable();
        invoiceBusiness.payInvoice();
        invoiceBusiness.statusTransactionByInvoice();
        array = invoiceBusiness.getTransactions();
        String transId = array.getJSONObject(0).getString("id");
        logger.info("Trans ID = "+transId);
        body = "{\n" +
                "  \"transaction_id\": \""+transId+"\",\n" +
                "  \"amount\": "+amount+",\n" +
                "  \"comment\": \"частичное\"\n" +
                "}";
        invoiceBusiness.refundInvoice(body);
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getInt("amount"), amount);
        invoiceBusiness.statusInvoice("/"+invoiceBusiness.getInvoiceId()+"");
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        logFinishTest("allRefundBusiness");
    }

    @Test
    public void cloneInvoiceBusiness(){
        logStartTest("cloneInvoiceBusiness");
        createInvoiceBusinessDisposable();
        json = new JSONObject(invoiceBusiness.getResponse());
        int amount = json.getInt("amount");
        String kind = json.getString("kind");
        String description = json.getString("description");
        invoiceBusiness.payInvoice();
        invoiceBusiness.cloneInvoice();
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getInt("amount"),amount);
        Assert.assertEquals(json.getString("kind"),kind);
        Assert.assertEquals(json.getString("description"),description);
        Assert.assertEquals(json.getString("external_id"),"Clone:"+invoiceBusiness.getExternalId());
        logFinishTest("cloneInvoiceBusiness");
    }

    @Test
    public void playStopInvoiceBusiness(){
        logStartTest("playStopInvoiceBusiness");
        createInvoiceBusinessDisposableFalse();
        invoiceBusiness.blockInvoice();
        invoiceBusiness.statusInvoice("/"+invoiceBusiness.getInvoiceId()+"");
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("status"),"BLOCKED");
        invoiceBusiness.unblockInvoice();
        invoiceBusiness.statusInvoice("/"+invoiceBusiness.getInvoiceId()+"");
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        logFinishTest("playStopInvoiceBusiness");
    }

    @Test
    public void payInvoiceDisposableBusiness(){
        logStartTest("payInvoiceDisposableBusiness");
        createInvoiceBusinessDisposable();
        invoiceBusiness.payInvoice();
        invoiceBusiness.statusInvoice("/"+invoiceBusiness.getInvoiceId()+"");
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        invoiceBusiness.statusTransactionByInvoice();
        array = new JSONArray(invoiceBusiness.getResponse());
        Assert.assertEquals(array.getJSONObject(0).getString("status"),"PROCESSED");
        logFinishTest("payInvoiceDisposableBusiness");
    }

    @Test
    public void payInvoiceDisposableFalseBusiness(){
        logStartTest("payInvoiceDisposableFalseBusiness");
        createInvoiceBusinessDisposableFalse();
        invoiceBusiness.payInvoice();
        invoiceBusiness.statusInvoice("/"+invoiceBusiness.getInvoiceId()+"");
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        invoiceBusiness.statusTransactionByInvoice();
        array = new JSONArray(invoiceBusiness.getResponse());
        Assert.assertEquals(array.getJSONObject(0).getString("status"),"CREATED");
        logFinishTest("payInvoiceDisposableFalseBusiness");
    }

    @Test
    public void createInvoiceP4PDisposable(){
        logStartTest("createInvoiceP4PDisposable");
        String timeStamp = new SimpleDateFormat("yyyy-MM-").format(Calendar.getInstance().getTime());
        String dayStamp = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        String day;
        if(Integer.parseInt(dayStamp) < 9){
            day = "0"+(Integer.parseInt(dayStamp)+1);
        }else if (Integer.parseInt(dayStamp) > 8 && Integer.parseInt(dayStamp) < 30){
            day = String.valueOf(Integer.parseInt(dayStamp) + 1);
        }else {
            day = "05";
        }
        card.statusCard();
        array = new JSONArray(card.getResponse());
        String cardTo = array.getJSONObject(0).getString("token");
        logger.info("Card_to = "+cardTo);

        body = "\"amount\": "+amount+",\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"valid_till\": \""+timeStamp+day+"T16:58:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"card_to\": \""+cardTo+"\"\n";
        invoiceP4P = new InvoiceP4P(body);
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("id"),invoiceP4P.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"P4P");
        Assert.assertEquals(json.getString("description"),"VladAutomation");
        logFinishTest("createInvoiceP4PDisposable");
    }

    @Test
    public void createInvoiceP4PDisposableFalse(){
        logStartTest("createInvoiceP4PDisposableFalse");
        card.statusCard();
        array = new JSONArray(card.getResponse());
        String cardTo = array.getJSONObject(0).getString("token");
        logger.info("Card_to = "+cardTo);
        body = "\"amount\": "+amount+",\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"valid_till\": null,\n" +
                "  \"disposable\": false,\n" +
                "  \"card_to\": \""+cardTo+"\"\n";
        invoiceP4P = new InvoiceP4P(body);
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("id"),invoiceP4P.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"P4P");
        Assert.assertEquals(json.getString("description"),"VladAutomation");
        logFinishTest("createInvoiceP4PDisposableFalse");
    }

    @Test
    public void getInvoiceStatusP4P(){
        logStartTest("getInvoiceStatusP4P");
        createInvoiceP4PDisposable();

        invoiceP4P.statusInvoice("/"+invoiceP4P.getInvoiceId()+"");
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("id"),invoiceP4P.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"P4P");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        invoiceP4P.statusInvoice("?status=CREATED");
        array = new JSONArray(invoiceP4P.getResponse());
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("id"),invoiceP4P.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"P4P");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        invoiceP4P.statusInvoice("?amount=274");
        array = new JSONArray(invoiceP4P.getResponse());
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("id"),invoiceP4P.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"P4P");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        invoiceP4P.statusInvoice("?external_id=VladAuto:"+invoiceP4P.getExternalId()+"");
        array = new JSONArray(invoiceP4P.getResponse());
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("id"),invoiceP4P.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"P4P");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        invoiceP4P.statusInvoice("");
        array = new JSONArray(invoiceP4P.getResponse());
        json = array.getJSONObject(0);
        Assert.assertEquals(json.getString("id"),invoiceP4P.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"P4P");
        Assert.assertEquals(json.getString("description"),"VladAutomation");

        logFinishTest("getInvoiceStatusP4P");
    }

    @Test
    public void changeTTLP4P(){
        logStartTest("changeTTLP4P");
        String timeStamp = new SimpleDateFormat("yyyy-MM-").format(Calendar.getInstance().getTime());
        String dayStamp = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        String day;
        if(Integer.parseInt(dayStamp) < 9){
            day = "0"+(Integer.parseInt(dayStamp)+1);
        }else if (Integer.parseInt(dayStamp) > 8 && Integer.parseInt(dayStamp) < 30){
            day = String.valueOf(Integer.parseInt(dayStamp) + 1);
        }else {
            day = "05";
        }
        createInvoiceP4PDisposable();
        invoiceP4P.statusInvoice("/"+invoiceP4P.getInvoiceId()+"");
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("id"),invoiceP4P.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"P4P");
        Assert.assertEquals(json.getString("description"),"VladAutomation");
        Assert.assertEquals(json.getString("valid_till"),""+timeStamp+day+"T16:58:00.000Z");
        day = String.valueOf(Integer.parseInt(day)+1);
        invoiceP4P.changeTTL(""+timeStamp+day+"T16:58:00.000Z");
        invoiceP4P.statusInvoice("/"+invoiceP4P.getInvoiceId()+"");
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("id"),invoiceP4P.getInvoiceId());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Assert.assertEquals(json.getString("kind"),"P4P");
        Assert.assertEquals(json.getString("description"),"VladAutomation");
        Assert.assertEquals(json.getString("valid_till"),""+timeStamp+day+"T16:58:00.000Z");
        logFinishTest("changeTTLP4P");
    }

    @Test
    public void allRefundP4P() throws InterruptedException {
        logStartTest("allRefundP4P");
        createInvoiceP4PDisposable();
        invoiceP4P.payInvoice();
        invoiceP4P.statusTransactionByInvoice();
        array = invoiceP4P.getTransactions();
        String transId = array.getJSONObject(0).getString("id");
        logger.info("Trans ID = "+transId);
        body = "{\n" +
                "  \"transaction_id\": \""+transId+"\"\n" +
                "}";
        invoiceP4P.refundInvoice(body);
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        Thread.sleep(10000);
        invoiceP4P.statusInvoice("/"+invoiceP4P.getInvoiceId()+"");
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("status"),"REFUNDED");
        logFinishTest("allRefundP4P");
    }

    @Test
    public void cloneInvoiceP4P(){
        logStartTest("cloneInvoiceP4P");
        createInvoiceP4PDisposable();
        invoiceP4P.payInvoice();
        json = new JSONObject(invoiceP4P.getResponse());
        int amount = json.getInt("amount");
        String kind = json.getString("kind");
        String description = json.getString("description");
        invoiceP4P.cloneInvoice();
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getInt("amount"),amount);
        Assert.assertEquals(json.getString("kind"),kind);
        Assert.assertEquals(json.getString("description"),description);
        Assert.assertEquals(json.getString("external_id"),"Clone:"+invoiceP4P.getExternalId());
        logFinishTest("cloneInvoiceP4P");
    }

    @Test
    public void playStopInvoiceP4P(){
        logStartTest("playStopInvoiceP4P");
        createInvoiceP4PDisposableFalse();
        invoiceP4P.blockInvoice();
        invoiceP4P.statusInvoice("/"+invoiceP4P.getInvoiceId()+"");
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("status"),"BLOCKED");
        invoiceP4P.unblockInvoice();
        invoiceP4P.statusInvoice("/"+invoiceP4P.getInvoiceId()+"");
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        logFinishTest("playStopInvoiceP4P");
    }

    @Test
    public void payInvoiceDisposableP4P(){
        logStartTest("payInvoiceDisposableP4P");
        createInvoiceP4PDisposable();
        invoiceP4P.payInvoice();
        invoiceP4P.statusInvoice("/"+invoiceP4P.getInvoiceId()+"");
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("status"),"HOLDED");
        invoiceP4P.statusTransactionByInvoice();
        array = new JSONArray(invoiceP4P.getResponse());
        Assert.assertEquals(array.getJSONObject(0).getString("status"),"HOLDED");
        logFinishTest("payInvoiceDisposableP4P");
    }

    @Test
    public void payInvoiceDisposableFalseP4P(){
        logStartTest("payInvoiceDisposableFalseP4P");
        createInvoiceP4PDisposableFalse();
        invoiceP4P.payInvoice();
        invoiceP4P.statusInvoice("/"+invoiceP4P.getInvoiceId()+"");
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("status"),"CREATED");
        invoiceP4P.statusTransactionByInvoice();
        array = new JSONArray(invoiceP4P.getResponse());
        Assert.assertEquals(array.getJSONObject(0).getString("status"),"CREATED");
        logFinishTest("payInvoiceDisposableFalseP4P");
    }

    @Test
    public void enrollP4P() throws InterruptedException {
        logStartTest("enrollP4P");
        createInvoiceP4PDisposable();
        invoiceP4P.payInvoice();
        invoiceP4P.statusTransactionByInvoice();
        array = new JSONArray(invoiceP4P.getResponse());
        invoiceP4P.enrollInvoice(array.getJSONObject(0).getString("id"));
        invoiceP4P.statusInvoice("/"+invoiceP4P.getInvoiceId()+"");
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSING");
        Thread.sleep(5000);
        invoiceP4P.statusInvoice("/"+invoiceP4P.getInvoiceId()+"");
        json = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(json.getString("status"),"PROCESSED");
        logFinishTest("enrollP4P");
    }

    @Test
    public void addCard(){
        logStartTest("addCard");
        body = "{\n" +
                "  \"alias\": \"TestAutoVlad\",\n" +
                "  \"card\": {\n" +
                "    \"pan\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "    \"expire\": \"2212\",\n" +
                "    \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "  }\n" +
                "}";
        card.addCard(body);
        json = new JSONObject(card.getResponse());
        Assert.assertEquals(json.getString("status"),"UNCONFIRMED");
        logFinishTest("addCard");
    }

    @Test
    public void confirmCard(){
        logStartTest("confirmCard");
        String token = "";
        String otp = "";
        body = "{\n" +
                "  \"token\": \""+token+"\",\n" +
                "  \"otp\": \""+otp+"\"\n" +
                "}";
        card.confirmCard(body);
        json = new JSONObject(card.getResponse());
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        logFinishTest("confirmCard");
    }

    @Test
    public void renameAndStatusCard(){
        logStartTest("renameAndStatusCard");
        card.statusCard();
        array = new JSONArray(card.getResponse());
        String token = array.getJSONObject(0).getString("token");
        String oldAlias = array.getJSONObject(0).getString("alias");
        Assert.assertNotEquals(oldAlias,"RenameTestVladAuto");
        logger.info("Токен карты = "+token+"; Old alias = "+oldAlias);
        body = "{\n" +
                "  \"token\": \""+token+"\",\n" +
                "  \"alias\": \"RenameTestVladAuto\"\n" +
                "}";
        card.renameCard(body);
        card.statusCard();
        array = new JSONArray(card.getResponse());
            for (int i = 0; i<array.length();i++){
            String findToken = array.getJSONObject(i).getString("token");
                if(findToken.equals(token)){
                String newAlias = array.getJSONObject(i).getString("alias");
                Assert.assertEquals(newAlias,"RenameTestVladAuto");
                logger.info("Rename работает");
                }
            }
        logFinishTest("renameAndStatusCard");
    }

    @Test
    public void deleteAndStatusCard(){
        logStartTest("deleteAndStatusCard");
        card.statusCard();
        array = new JSONArray(card.getResponse());
        String token = array.getJSONObject(0).getString("token");
        card.deleteCard(token);
        card.statusCard();
        array = new JSONArray(card.getResponse());
            for (int i = 0; i<array.length();i++) {
                String findToken = array.getJSONObject(i).getString("token");
                    if(findToken.equals(token)){
                        logger.error("Delete НЕ рабоатет");
                    }
            }
        logFinishTest("deleteAndStatusCard");
    }

    @Test
    public void defaultAndStatusCard(){
        logStartTest("defaultAndStatusCard");
        card.statusCard();
        String token = null;
        array = new JSONArray(card.getResponse());
        for (int i = 0; i<array.length();i++) {
            boolean oldDefault = array.getJSONObject(i).getBoolean("is_default");
                if(!oldDefault){
                    token = array.getJSONObject(i).getString("token");
                    logger.info("Token Card = "+token);
                }
        }
        body = "{\n" +
                "  \"token\": \""+token+"\",\n" +
                "  \"terminal_id\": null\n" +
                "}";
        card.makeDefaultCard(body);
        card.statusCard();
        array = new JSONArray(card.getResponse());
        for (int i = 0; i<array.length();i++) {
                String findToken = array.getJSONObject(i).getString("token");
                    if(findToken.equals(token)){
                        Assert.assertTrue(array.getJSONObject(i).getBoolean("is_default"));
                        logger.info("Default работает");
                    }
        }
        logFinishTest("defaultAndStatusCard");
    }
}
