package test.backTests.applePayGooglePay;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.BodyHelper;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.MobilePay;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test
public class MakeAGP extends BaseTest {

    private final Card card = Card.MONO_MC;
    private String body;
    private Transaction transaction;

    public void makeAGP_with_confirmation(){
        body = "{\n" +
                "\t\"amount\":\"101\",\n" +
                "\t\"card_number\":\""+ Cards_data.getData(card, Card_param.pan) +"\",\n" +
                "\t\"experation_date\":\""+Cards_data.getData(card, Card_param.expire)+"\",\n" +
                "\t\"cvv\": \""+Cards_data.getData(card, Card_param.cvv)+"\", \n" +
                "\t\"ucaf\":\"AAABA5RZlAAAA5cZGFmUAAAAAAA=\",\n" +
                "\t\"ext_trans_id\": \"12wq3e50\",\n" +
                "\t\"info_3ds\":{\n" +
                "\t\t\"ip\":\"0.0.0.127\",\n" +
                "\t\t\"fingerprint\":\"1q2w3e4r5t\",\n" +
                "\t\t\"without_3ds\": false,\n" +
                "\t\t\"version\": \"2.2.0\",\n" +
                "    \"javascript_enabled\": true,\n" +
                "\t\t\"channel\":\"BRW\",\n" +
                "\t\t\"accept_header\":\"text/html\",\n" +
                "\t\t\"java_enabled\":false,\n" +
                "\t\t\"language\":\"RU-ru\",\n" +
                "\t\t\"color_depth\":32,\n" +
                "\t\t\"screen_height\":800,\n" +
                "\t\t\"screen_width\":900, \n" +
                "\t\t\"time_zone\":-180,\n" +
                "\t\t\"user_agent\":\"Gecko\",\n" +
                "\t\t\"challenge_window_size\":\"04\",\n" +
                "\t\t\"return_url\":\"https://service.fuib.com\"\n" +
                "\t}\n" +
                "}\n";

        transaction = new MobilePay(body,2);
    }

    public void makeAGP(){
        body = "{\n" +
                "\t\"amount\":\"101\",\n" +
                "\t\"card_number\":\""+Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "\t\"experation_date\":\""+Cards_data.getData(card, Card_param.expire)+"\",\n" +
                "\t\"cvv\": \""+Cards_data.getData(card, Card_param.cvv)+"\", \n" +
                "\t\"ucaf\":\"AAABA5RZlAAAA5cZGFmUAAAAAAA=\",\n" +
                "\t\"ext_trans_id\": \"12wq3e50\",\n" +
                "\t\"info_3ds\":{\n" +
                "\t\t\"ip\":\"0.0.0.127\",\n" +
                "\t\t\"fingerprint\":\"1q2w3e4r5t\",\n" +
                "\t\t\"without_3ds\": true,\n" +
                "\t\t\"version\": \"2.1.0\",\n" +
                //"    \"javascript_enabled\": true,\n" +
                "\t\t\"channel\":\"BRW\",\n" +
                "\t\t\"accept_header\":\"text/html\",\n" +
                "\t\t\"java_enabled\":false,\n" +
                "\t\t\"language\":\"RU-ru\",\n" +
                "\t\t\"color_depth\":32,\n" +
                "\t\t\"screen_height\":800,\n" +
                "\t\t\"screen_width\":900, \n" +
                "\t\t\"time_zone\":-180,\n" +
                "\t\t\"user_agent\":\"Gecko\",\n" +
                "\t\t\"challenge_window_size\":\"04\",\n" +
                "\t\t\"return_url\":\"https://service.fuib.com\"\n" +
                "\t}}";
        transaction = new MobilePay(body,0);
    }
}
