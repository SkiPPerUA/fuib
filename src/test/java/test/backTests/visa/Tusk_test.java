package test.backTests.visa;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.visa.requestToPay.Tusk;
import org.testng.annotations.Test;

@Test
public class Tusk_test extends BaseTest {

    Tusk tusk = new Tusk();

    public void positive_init(){
        tusk.setExpectedResponseCode(201);
        tusk.init("{\n" +
                "    \"product\": \"VD\",\n" +
                "    \"use_case\": \"P2P\",\n" +
                "    \"payment_requests\": [\n" +
                "        {\n" +
                "            \"debtor_agent_country\": \"UA\",\n" +
                "            \"debtor_country\": \"UA\",\n" +
                "            \"debtor_alias\": \"380933933333\",\n" +
                "            \"requested_amount\": 100,\n" +
                "            \"requested_amount_currency\": \"UAH\",\n" +
                "            \"end_to_end_id\": \"RFPid0001\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"request_message_id\": \"GG9983636387737JH\",\n" +
                "    \"settlement_options\": [\n" +
                "        {\n" +
                "            \"settlement_system\": \"VISA_DIRECT\",\n" +
                "            \"primary_account_number\": \""+Cards_data.getData(Card.FUIB_VISA,Card_param.pan)+"\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"creditor\": {\n" +
                "        \"creditor_agent_id\": \"12379879797989\",\n" +
                "        \"creditor_agent_country\": \"UA\",\n" +
                "        \"creditor_country\": \"UA\",\n" +
                "        \"creditor_id\": \"BL1234567890\",\n" +
                "        \"creditor_id_type\": \"AGENT\"\n" +
                "    },\n" +
                "    \"creation_date_time\": \"2020-12-17T09:30:47Z\"\n" +
                "}");
    }
}
