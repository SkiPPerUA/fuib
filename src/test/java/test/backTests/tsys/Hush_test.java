package test.backTests.tsys;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.tsys.Hush;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class Hush_test extends BaseTest {

    Hush hush = new Hush();

    public void positive(){
        hush.getVisaTransactions("?acquiring_bin=408999&transaction_identifier=234234322342343");
        Assert.assertEquals(new JSONObject(hush.getResponse()).getJSONArray("transactions").length(), 10);
    }

    public void positive_visaValidation(){
        hush.visaValidation("{\n" +
                "  \"acquirer_country_code\": \"804\",\n" +
                "  \"acquiring_bin\": \"406659\",\n" +
                "  \"primary_account_number\": \"4761739999000213\",\n" +
                //"  \"card_currency_code\": \"UAH\",\n" +
                "  \"card_expiry_date\": \"2031-12\",\n" +
                "  \"card_cvv2_value\": \"913\",\n" +
                "  \"card_acceptor\": {\n" +
                "    \"name\": \"FUIB\",\n" +
                "    \"id_code\": \"10036783804\"\n" +
                "  }\n" +
                "}");
    }
}
