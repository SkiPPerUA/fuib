package test.backTests.payoneer;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.payoneer.Payoneer;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.json.JSONObject;
import org.testng.annotations.Test;

@Test
public class PayoneerTest extends BaseTest {

    Payoneer payoneer = new Payoneer();
    String ekb_id = "1299125";

    public void positive_registrationLinks(){
        payoneer.registrationLinks("{\n" +
                "    \"ekb_id\": "+ekb_id+",\n" +
                "    \"already_have_an_account\": true\n" +
                "}");
    }

    public void positive_getBalance(){
        payoneer.getBalance("{\"session\": " +
                "{\n" +
                "        \"session_id\":\"1719213628\",\n" +
                "        \"customer_id\":2282708,\n" +
                "        \"ekb_id\":2055142,\n" +
                "        \"phone\":\"+380680200004\",\n" +
                "        \"login\":\"0680200004\",\n" +
                "        \"agreement_id\":12690897\n" +
                        "},\n" +
                " \"request_data\":{\n" +
                "        \"lang\":\"UK\"}" +
                "}");
    }

    public void positive_makeTransaction() throws InterruptedException {
        payoneer.makeTransaction("{\n" +
                "\"ekb_id\": "+ekb_id+",\n" +
                "\"amount\": 100,\n" +
                "\"currency\": \"USD\",\n" +
                "\"payoneer_account_id\": \"4366181893242508\",\n" +
                "\"receiver\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA063348510000026209404298281\"\n" +
                "}\n" +
                "}");
        System.out.println(new JSONObject(payoneer.getResponse()).getString("challenge_url"));
        System.out.println("Ввести 123456");
        Thread.sleep(30000);
        payoneer.statusTransaction(ekb_id);
    }

    public void positive_accountDetails(){
        payoneer.accountDetails(ekb_id);
    }

    public void positive_getProgramBalance(){
        payoneer.getProgramBalance();
    }

}
