package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2A;
import org.testng.annotations.Test;

@Test
public class VisaRequestToPay extends BaseTest {

    public void positive(){
        A2A a2a = new A2A();
        a2a.setToken(new Trans_token_payhub(7025246));
        a2a.initTransfers("{\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"service\": \"visa_request_to_pay\",\n" +
                "    \"amount\": 100,\n" +
                "    \"fee_amount\": 12,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"purpose\": \"some purpose\",\n" +
                "    \"sender\": {\n" +
                "      \"source\":\"ACCOUNT_ID\",\n" +
                "      \"value\":\"167436207\",\n" +
                "      \"instrument\":\"OWN_CARD\",\n" +
                "      \"card_id\":\"020605755871\",\n" +
                "      \"client\":{\n" +
                "         \"id\":\"10618081\",\n" +
                "         \"source\":\"EKB\"\n" +
                "      }\n" +
                "   },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"PHONE\",\n" +
                "        \"instrument\": \"PAYMENT_CARD\",\n" +
                "        \"value\": \"380951254889\"\n" +
                "    },\n" +
                "    \"authentication\": {\n" +
                "        \"device_id\": \"test\",\n" +
                "        \"session_id\": \"test\",\n" +
                "        \"ip\": \"192.168.0.1\",\n" +
                "        \"event_type\": \"APP_A2P\"\n" +
                "    },\n" +
                "    \"service_params\": {\n" +
                "    \"visa_request_to_pay_id\": \"123456789\"\n" +
                "  }"+
                "}");
    }
}
