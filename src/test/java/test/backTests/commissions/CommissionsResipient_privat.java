package test.backTests.commissions;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.commissions.Сommissions;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Test
public class CommissionsResipient_privat extends BaseTest {

    Сommissions commissions = new Сommissions();
    Map<String, String> cards = new HashMap<>();

    public void test_different_bank() {
        setCards();
        for (int i = 0; i < cards.size(); i++) {
            String bank_name = cards.keySet().toArray()[i].toString();
            String pan = cards.get(cards.keySet().toArray()[i].toString());
            logger.info(bank_name+" - "+pan);
            if (bank_name.equals("Privat") || bank_name.equals("Privat2")){
                commissions.setExpectedResponseCode(200);
            }else {
                commissions.setExpectedResponseCode(400);
            }
            commissions.commissions_recipients("{\n" +
                    "    \"recipient\": {\n" +
                    "        \"validation_off\": true,\n" +
                    "        \"amount\": 100000,\n" +
                    "        \"source\": \"PAN\",\n" +
                    "        \"value\": \""+pan+"\"\n" +
                    "    }\n" +
                    "}");
        }
    }

    public void positive_Base64() {
        setCards();
        for (int i = 0; i < cards.size(); i++) {
            String bank_name = cards.keySet().toArray()[i].toString();
            String pan = cards.get(cards.keySet().toArray()[i].toString());
            logger.info(bank_name+" - "+pan);
            if (bank_name.equals("Privat") || bank_name.equals("Privat2")){
                commissions.setExpectedResponseCode(200);
            }else {
                commissions.setExpectedResponseCode(400);
            }
            commissions.commissions_recipients("{\n" +
                    "    \"recipient\": {\n" +
                    "        \"validation_off\": true,\n" +
                    "        \"amount\": 100000,\n" +
                    "        \"source\": \"PAN\",\n" +
                    "        \"value\": \""+Base64.getEncoder().encodeToString(pan.getBytes())+"\"\n" +
                    "    }\n" +
                    "}");
        }
    }

    public void positive_PAN(){
        commissions.setExpectedResponseCode(200);
        commissions.commissions_recipients("{\n" +
                "    \"recipient\": {\n" +
                "        \"validation_off\": true,\n" +
                "        \"amount\": 1002,\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"5168745022396342\"\n" +
                "    }\n" +
                "}");
    }

    public void positive_IBAN(){
        commissions.setExpectedResponseCode(200);
        commissions.commissions_recipients("{\n" +
                "    \"recipient\": {\n" +
                "        \"validation_off\": true,\n" +
                "        \"amount\": 1002,\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA523052990262026400946137195\"\n" +
                "    }\n" +
                "}");
    }

    @Test(dataProvider = "amount_negative")
    public void test_amount(String data){
        String body = "{\n" +
        "    \"recipient\": {\n" +
                "        \"validation_off\": false,\n" +
                "        \"amount\": "+data+",\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"5168745022396342\"\n" +
                "    }\n" +
                "}";
        commissions.setExpectedResponseCode(400);
        commissions.commissions_recipients(body);
    }

    public void test_fields(){
        commissions.setExpectedResponseCode(200);
        String body = "{\n" +
                "    \"recipient\": {\n" +
                //"        \"validation_off\": false,\n" +
                "        \"amount\": 10000,\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"5168745022396342\"\n" +
                "    }\n" +
                "}";
        commissions.commissions_recipients(body);
        Assert.assertTrue(commissions.getResponse().contains("fee_amount"));

        commissions.setExpectedResponseCode(400);
        body = "{\n" +
                "    \"recipient\": {\n" +
                "        \"validation_off\": false,\n" +
                //"        \"amount\": 10000,\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"5168745022396342\"\n" +
                "    }\n" +
                "}";
        commissions.commissions_recipients(body);
        Assert.assertTrue(commissions.getResponse().contains("BAD_REQUEST"));

        body = "{\n" +
                "    \"recipient\": {\n" +
                "        \"validation_off\": false,\n" +
                "        \"amount\": 10000,\n" +
                //"        \"source\": \"PAN\",\n" +
                "        \"value\": \"5168745022396342\"\n" +
                "    }\n" +
                "}";
        commissions.commissions_recipients(body);
        Assert.assertTrue(commissions.getResponse().contains("BAD_REQUEST"));

        body = "{\n" +
                "    \"recipient\": {\n" +
                "        \"validation_off\": false,\n" +
                "        \"amount\": 10000,\n" +
                "        \"source\": \"PAN\"\n" +
                //"        \"value\": \"5168745022396342\"\n" +
                "    }\n" +
                "}";
        commissions.commissions_recipients(body);
        Assert.assertTrue(commissions.getResponse().contains("BAD_REQUEST"));
    }

    @DataProvider(parallel = true)
    public Object[][] amount_negative() {
        return new Object[][] {{"-1"}, {"0"}, {"null"}, {"101.01"}, {"9999999"}};
    }

    private void setCards(){
        cards.put("MONO", Cards_data1.getData(Card.MONO_MC, Card_param.pan));
        cards.put("PUMB_master", Cards_data1.getData(Card.FUIB_MC, Card_param.pan));
        cards.put("PUMB_visa", Cards_data1.getData(Card.FUIB_VISA, Card_param.pan));
        cards.put("Privat", Cards_data1.getData(Card.PRIVAT_VISA, Card_param.pan));
        cards.put("Raif", Cards_data1.getData(Card.RAIF_VISA, Card_param.pan));
        cards.put("Privat2", "5168745022396342");
    }

}
