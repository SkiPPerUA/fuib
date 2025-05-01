package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.CardHolder;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CardHolderTest extends BaseTest {

    CardHolder cardHolder = new CardHolder();
    JSONObject json;

    @Test
    public void testShortNamePositive(){
        logStartTest("testShortNamePositive");
        cardHolder.nameShort(Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        json = new JSONObject(cardHolder.getResponse());
        Assert.assertEquals(json.getBoolean("success"),true);
        Assert.assertEquals(json.getString("cardholder"),"Владислав С");

        logFinishTest("testShortNamePositive");
    }

    @Test
    public void testFullNamePositive(){
        logStartTest("testFullNamePositive");

        cardHolder.nameFull(Cards_data.getData(Card.FUIB_MC,Card_param.pan));
        json = new JSONObject(cardHolder.getResponse());
        Assert.assertEquals(json.getBoolean("success"),true);
        Assert.assertEquals(json.getJSONObject("cardholder").getString("first_name"),"Владислав");
        Assert.assertEquals(json.getJSONObject("cardholder").getString("last_name"),"Савчук");
        Assert.assertEquals(json.getJSONObject("cardholder").getString("middle_name"),"Ігорович");

        logFinishTest("testFullNamePositive");
    }

    @Test
    public void testMatchTax(){
        logStartTest("testMatchTax");
        cardHolder.matchTax(Cards_data.getData(Card.FUIB_VISA, Card_param.pan),Configs.TaxCode);
        json = new JSONObject(cardHolder.getResponse());
        Assert.assertEquals(json.getBoolean("success"),true);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        cardHolder.matchTax(Cards_data.getData(Card.FUIB_MC, Card_param.pan),Configs.TaxCode);
        json = new JSONObject(cardHolder.getResponse());
        Assert.assertEquals(json.getBoolean("success"),true);
        logFinishTest("testMatchTax");
    }

    @Test
    public void testShortNameNegaive(){
        logStartTest("testShortNameNegaive");
        cardHolder.nameShort(Cards_data.getData(Card.MONO_VISA,Card_param.pan));
        json = new JSONObject(cardHolder.getResponse());
        Assert.assertEquals(json.getBoolean("success"),false);

        logFinishTest("testShortNameNegaive");
    }

    @Test
    public void testFullNameNegative(){
        logStartTest("testFullNameNegative");
        cardHolder.nameFull(Cards_data.getData(Card.MONO_VISA,Card_param.pan));
        json = new JSONObject(cardHolder.getResponse());
        Assert.assertEquals(json.getBoolean("success"),false);

        logFinishTest("testFullNameNegative");
    }

    @Test
    public void testMatchTaxNegative() {
        logStartTest("testMatchTaxNegative");
        cardHolder.matchTax(Cards_data.getData(Card.MONO_VISA,Card_param.pan),Configs.TaxCode);
        json = new JSONObject(cardHolder.getResponse());
        Assert.assertEquals(json.getBoolean("success"),false);
        logFinishTest("testMatchTaxNegative");
    }

    @Test
    public void testFullDetails(){
        //select * from transactions.transactions where card_to_id is not null and status = 'PROCESSED'
        String trans_id = "bce86a78-24ab-4cf0-86eb-25242ea55c69";

        cardHolder.setExpectedResponseCode(400);
        String body = "{\n" +
                "   \"transaction_id\": \"126378bc-d73c-4245-b4b1-ab0476d1e103\",\n" +
                "   \"destination_type\": \"CARD_ID\"\n" +
                "}";
        cardHolder.full_details(body);
        Assert.assertTrue(cardHolder.getResponse().contains("NOT_FOUND"));

        cardHolder.setExpectedResponseCode(200);
        logStartTest("Success only transaction_id");
        body = "{\n" +
                "   \"transaction_id\": \""+trans_id+"\"\n" +
                "}";
        cardHolder.full_details(body);
        Assert.assertTrue(cardHolder.getResponse().contains("\"success\":true"));

        logStartTest("Success CARD_ID");
        body = "{\n" +
                "   \"transaction_id\": \""+trans_id+"\",\n" +
                "   \"destination_type\": \"CARD_ID\"\n" +
                "}";
        cardHolder.full_details(body);
        Assert.assertTrue(cardHolder.getResponse().contains("\"success\":true"));

        logStartTest("Success PAN");
        body = "{\n" +
                "   \"transaction_id\": \""+trans_id+"\",\n" +
                "   \"destination_type\": \"PAN\"\n" +
                "}";
        cardHolder.full_details(body);

        logStartTest("Success ACCOUNT");
        body = "{\n" +
                "   \"transaction_id\": \""+trans_id+"\",\n" +
                "   \"destination_type\": \"ACCOUNT\"\n" +
                "}";
        cardHolder.full_details(body);

    }

}
