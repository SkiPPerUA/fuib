package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2A;
import org.testng.annotations.Test;

@Test
public class VisaRequestToPay extends BaseTest {

    public void positive(){
        new A2A("{\n" +
                "    \"service\": \"visa_request_to_pay\",\n" +
                "    \"amount\": 100,\n" +
                "    \"fee_amount\": 12,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"purpose\": \"some purpose\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"ACCOUNT_ID\",\n" +
                "        \"value\": \"126856282\"\n" +
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA563348510000026201113488937\"\n" +
                "    },\n" +
                "    \"authentication\": {\n" +
                "        \"device_id\": \"test\",\n" +
                "        \"session_id\": \"test\",\n" +
                "        \"ip\": \"192.168.0.1\",\n" +
                "        \"event_type\": \"APP_A2P\"\n" +
                "    },\n" +
                "    \"service_params\": {\n" +
                "    \"visa_request_to_pay_id\": \"visa_request_to_payId\"\n" +
                "  }"+
                "}");
    }
}
