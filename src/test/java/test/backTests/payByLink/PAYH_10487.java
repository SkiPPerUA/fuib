package test.backTests.payByLink;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payByLink.CardPayByLink;
import org.example.qaTransactionTeam.backEnd.payByLink.InvoiceBusiness;
import org.example.qaTransactionTeam.backEnd.payByLink.InvoiceP4P;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PAYH_10487 extends BaseTest {
    //Протестировать реализацию редактирования полей в Pay-by-Link

    InvoiceBusiness invoiceBusiness;
    InvoiceP4P invoiceP4P;
    String body;
    String merchant = "fd4af5db-2a6a-4175-b6b6-41249ad7c70a";
    JSONObject jsonObject;

    @Test
    public void positiveBusiness(){
        logStartTest("positiveBusiness");
        body = "\"amount\": 243,\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": null,\n" +
                "  \"disposable\": false,\n" +
                "  \"merchant_config_id\": \""+merchant+"\"\n";

        invoiceBusiness = new InvoiceBusiness(body,false);
        jsonObject = new JSONObject(invoiceBusiness.getResponse());
        String id = jsonObject.getString("id");
        String link_id = jsonObject.getString("link_id");
        String kind = jsonObject.getString("kind");
        String payment_url = jsonObject.getString("payment_url");
        String external_id = jsonObject.getString("external_id");
        boolean disposable = jsonObject.getBoolean("disposable");
        boolean is_fast_pay = jsonObject.getBoolean("is_fast_pay");
        String created_at = jsonObject.getString("created_at");
        invoiceP4P.setStatusCode(200);
        invoiceBusiness.changeInvoice("test","300000");
        jsonObject = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(jsonObject.getString("description"),"test");
        Assert.assertEquals(jsonObject.getInt("amount"),300000);
        Assert.assertEquals(jsonObject.getString("id"),id);
        Assert.assertEquals(jsonObject.getString("link_id"),link_id);
        Assert.assertEquals(jsonObject.getString("kind"),kind);
        Assert.assertEquals(jsonObject.getString("payment_url"),payment_url);
        Assert.assertEquals(jsonObject.getString("external_id"),external_id);
        Assert.assertEquals(jsonObject.getString("created_at"),created_at);
        Assert.assertEquals(jsonObject.getBoolean("disposable"),disposable);
        Assert.assertEquals(jsonObject.getBoolean("is_fast_pay"),is_fast_pay);

        logFinishTest("positiveBusiness");

    }

    @Test
    public void negativeBusinessDisposable(){
        logStartTest("negativeBusinessDisposable");
        body = "\"amount\": 243,\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \"2022-11-16T16:39:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \""+merchant+"\"\n";

        invoiceBusiness = new InvoiceBusiness(body,false);
        invoiceBusiness.setStatusCode(400);
        invoiceBusiness.changeInvoice("test","300000");

        logFinishTest("negativeBusinessDisposable");

    }

    @Test
    public void positiveP4P(){
        logStartTest("positiveP4P");
        CardPayByLink card = new CardPayByLink();
        card.statusCard();
        JSONArray array = new JSONArray(card.getResponse());
        String cardTo = array.getJSONObject(0).getString("token");

        body = "\"amount\": 243,\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"valid_till\": null,\n" +
                "  \"disposable\": false,\n" +
                "  \"card_to\": \""+cardTo+"\"\n";

        invoiceP4P = new InvoiceP4P(body);
        jsonObject = new JSONObject(invoiceP4P.getResponse());
        String id = jsonObject.getString("id");
        String link_id = jsonObject.getString("link_id");
        String kind = jsonObject.getString("kind");
        String payment_url = jsonObject.getString("payment_url");
        String external_id = jsonObject.getString("external_id");
        boolean disposable = jsonObject.getBoolean("disposable");
        boolean is_fast_pay = jsonObject.getBoolean("is_fast_pay");
        String created_at = jsonObject.getString("created_at");
        invoiceP4P.setStatusCode(200);
        invoiceP4P.changeInvoice("test","300000");
        jsonObject = new JSONObject(invoiceP4P.getResponse());
        Assert.assertEquals(jsonObject.getString("description"),"test");
        Assert.assertEquals(jsonObject.getInt("amount"),300000);
        Assert.assertEquals(jsonObject.getString("id"),id);
        Assert.assertEquals(jsonObject.getString("link_id"),link_id);
        Assert.assertEquals(jsonObject.getString("kind"),kind);
        Assert.assertEquals(jsonObject.getString("payment_url"),payment_url);
        Assert.assertEquals(jsonObject.getString("external_id"),external_id);
        Assert.assertEquals(jsonObject.getString("created_at"),created_at);
        Assert.assertEquals(jsonObject.getBoolean("disposable"),disposable);
        Assert.assertEquals(jsonObject.getBoolean("is_fast_pay"),is_fast_pay);

        logFinishTest("positiveP4P");

    }

    @Test
    public void negativeP4PDisposable(){
        logStartTest("negativeP4PDisposable");
        CardPayByLink card = new CardPayByLink();
        card.statusCard();
        JSONArray array = new JSONArray(card.getResponse());
        String cardTo = array.getJSONObject(0).getString("token");

        body = "\"amount\": 243,\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"valid_till\": \"2022-11-16T16:39:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"card_to\": \""+cardTo+"\"\n";

        invoiceP4P = new InvoiceP4P(body);
        invoiceP4P.setStatusCode(400);
        invoiceP4P.changeInvoice("test","300000");

        logFinishTest("negativeP4PDisposable");

    }

    @Test
    public void negativeP4PSumMore30000(){
        logStartTest("negativeP4PSumMore30000");
        CardPayByLink card = new CardPayByLink();
        card.statusCard();
        JSONArray array = new JSONArray(card.getResponse());
        String cardTo = array.getJSONObject(0).getString("token");

        body = "\"amount\": 243,\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"valid_till\": null,\n" +
                "  \"disposable\": false,\n" +
                "  \"card_to\": \""+cardTo+"\"\n";

        invoiceP4P = new InvoiceP4P(body);
        invoiceP4P.setStatusCode(500);
        invoiceP4P.changeInvoice("test","3000001");

        logFinishTest("negativeP4PSumMore30000");

    }

}
