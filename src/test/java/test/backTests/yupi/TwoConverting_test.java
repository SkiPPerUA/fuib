package test.backTests.yupi;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.yupi.Cards;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

@Test
public class TwoConverting_test extends BaseTest {

    Cards cards = new Cards();
    String body = "";

    public void positive_get(){
        cards.setExpectedResponseCode(200);
        cards.getTWOconverting("012248630156", "113");
    }

    public void negative_get(){
        cards.setExpectedResponseCode(475);
        cards.getTWOconverting("112248630156", "001");

        cards.setExpectedResponseCode(400);
        cards.getTWOconverting("", "001");
        cards.getTWOconverting("fsdfsd", "001");
        cards.getTWOconverting("fsdfsd", "00123");
        cards.getTWOconverting("012248630156", "");
        cards.getTWOconverting("012248630156", "0001");
        cards.getTWOconverting("012248630156", "01");
        cards.getTWOconverting("012248630156", "rwerw");
    }

    public void positive_pacth(){
        String cart_id = "015284390754";
        cards.setExpectedResponseCode(200);
        cards.getTWOconverting(cart_id, "001");
        Assert.assertEquals(new JSONObject(cards.getResponse()).getString("dcc"),"false");
        body = "{\n" +
                "  \"description\": \"string2323\",\n" +
                "  \"end_date\": \"2025-07-04\",\n" +
                "  \"method\": \"DCC_ON\",\n" +
                "  \"start_date\": \"2024-05-04\"\n" +
                "}";
        cards.patchTWOconverting(cart_id, "001", body);
        Assert.assertEquals(new JSONObject(cards.getResponse()).getString("dcc"),"true");
        String rule_id = new JSONObject(cards.getResponse()).getString("rule_id");
        cards.getTWOconverting(cart_id, "001");
        Assert.assertEquals(new JSONObject(cards.getResponse()).getString("dcc"),"true");
        body = "{\n" +
                "  \"description\": \"string\",\n" +
                "  \"end_date\": \"2025-07-04\",\n" +
                "  \"method\": \"DCC_OFF\",\n" +
                "  \"rule_id\": \""+rule_id+"\",\n" +
                "  \"start_date\": \"2024-05-04\"\n" +
                "}";
        cards.patchTWOconverting(cart_id, "001", body);
        Assert.assertEquals(new JSONObject(cards.getResponse()).getString("dcc"),"false");
        cards.getTWOconverting(cart_id, "001");
        Assert.assertEquals(new JSONObject(cards.getResponse()).getString("dcc"),"false");
    }

    public void negative_patch(){
        cards.setExpectedResponseCode(475);

//        //double
//        body = "{\n" +
//                "  \"description\": \"string2323\",\n" +
//                "  \"end_date\": \"2025-06-04\",\n" +
//                "  \"method\": \"DCC_ON\",\n" +
//                "  \"start_date\": \"2025-06-04\"\n" +
//                "}";
//        cards.patchTWOconverting("012248630156", "001", body);

        //test date
        body = "{\n" +
                "  \"description\": \"string2323\",\n" +
                "  \"end_date\": \"2023-06-04\",\n" +
                "  \"method\": \"DCC_ON\",\n" +
                "  \"start_date\": \"2023-06-04\"\n" +
                "}";
        cards.patchTWOconverting("012248630156", "001", body);

        //without rule_id
        body = "{\n" +
                "  \"description\": \"string\",\n" +
                "  \"end_date\": \"2025-06-04\",\n" +
                "  \"method\": \"DCC_OFF\",\n" +
                "  \"start_date\": \"2025-06-04\"\n" +
                "}";
        cards.patchTWOconverting("012248630156", "001", body);

        //test rule_id
        cards.setExpectedResponseCode(400);
        String [] rule_ids = {"09224863015","","11224863016","dwdweew","0234567890123"};
        Arrays.stream(rule_ids).forEach(x-> {
        body = "{\n" +
        "  \"description\": \"string\",\n" +
                "  \"end_date\": \"2025-06-04\",\n" +
                "  \"method\": \"DCC_OFF\",\n" +
                "  \"rule_id\": \""+x+"\",\n" +
                "  \"start_date\": \"2025-06-04\"\n" +
                "}";
        cards.patchTWOconverting(x, "001", body);
        });

        //test method
        body = "{\n" +
                "  \"description\": \"string2323\",\n" +
                "  \"end_date\": \"2026-06-04\",\n" +
                "  \"method\": \"DCC_ON1\",\n" +
                "  \"start_date\": \"2026-06-04\"\n" +
                "}";
        cards.patchTWOconverting("012248630156", "001", body);
    }
}
