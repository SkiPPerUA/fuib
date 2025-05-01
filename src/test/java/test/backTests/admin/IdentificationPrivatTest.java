package test.backTests.admin;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.IdentificationPrivat;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class IdentificationPrivatTest extends BaseTest {
    IdentificationPrivat identification = new IdentificationPrivat();
    String body;

    @DataProvider
    public Object[][] other_bank(){
        return new Object[][] {{Card.FUIB_MC},{Card.FUIB_VISA},{Card.MONO_MC}};
    }

    public void check_positive_privat(){
        identification.setStatusCode(200);
        body = "{\n" +
                "\"currency\": \"980\",\n" +
                "\"amount\": 100,\n" +
                "\"receivercardnumber\": \"5169332430115298\",\n" +
                "\"sender_account\": \"UA613348510000026203405297856\"\n" +
                "}";
        identification.check_identification(body);
        JSONObject json = new JSONObject(identification.getResponse());
        Assert.assertEquals(json.getString("fullName"), "KALASHNYKOV OLEKSII");
        Assert.assertEquals(json.getString("fullNameUa"), "КАЛАШНИКОВ ОЛЕКСІЙ СЕРГІЙОВИЧ");
    }

    @Test(dataProvider = "other_bank")
    public void check_negative_other_bank(Card card){
        identification.setStatusCode(503);
        body = "{\n" +
                "\"currency\": \"980\",\n" +
                "\"amount\": 100,\n" +
                "\"receivercardnumber\": \""+ Cards_data1.getData(card, Card_param.pan) +"\",\n" +
                "\"sender_account\": \"UA613348510000026203405297856\"\n" +
                "}";
        identification.check_identification(body);
    }
}
