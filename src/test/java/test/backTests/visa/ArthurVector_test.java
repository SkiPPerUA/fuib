package test.backTests.visa;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.visa.requestToPay.ArthurVector;
import org.testng.annotations.Test;

@Test
public class ArthurVector_test extends BaseTest {

    ArthurVector arthurVector = new ArthurVector();

    public void positive_init(){
        arthurVector.init("{\n" +
                "    \"product\": \"VD\",\n" +
                        "    \"use_case\": \"P2P\",\n" +
//                "    \"request_reason\": {\n" +
//                "        \"message\": \"test\"\n" +
//                "    },\n" +
                        "    \"payment_requests\": [\n" +
                        "        {\n" +
                        "            \"debtor_alias\": \"+380665767084\",\n" +
                        "            \"debtor_first_name\": \"Tolo\",\n" +
                        "            \"debtor_last_name\": \"P.\",\n" +
                        "            \"debtor_agent_country\": \"UA\",\n" +
                        "            \"debtor_country\": \"UA\",\n" +
                        "            \"requested_amount\": 125,\n" +
                        "            \"requested_amount_currency\": \"UAH\",\n" +
                        "            \"end_to_end_id\": \""+ Uuid_helper.generate_uuid4()+"\",\n" +
                        "            \"debtor_agent_id\": \"38042668479\",\n" +
                        "            \"debtor_alias_type\": \"MOBL\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        //"    \"due_date\": \"2025-09-29\",\n" +
                        "    \"request_message_id\": \""+ Uuid_helper.generate_uuid4() +"\",\n" +
                        "    \"settlement_options\": [\n" +
                        "        {\n" +
                        "            \"settlement_system\": \"VISA_DIRECT\",\n" +
                        "            \"primary_account_number\": \""+ Cards_data.getData(Card.FUIB_VISA, Card_param.pan) +"\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"creditor\": {\n" +
                        "        \"creditor_country\": \"UA\",\n" +
                        "        \"creditor_agent_id\": \"38042668479\",\n" +
                        "        \"creditor_agent_country\": \"UA\",\n" +
                        "        \"creditor_alias\": \"+380939917659\",\n" +
                        "        \"creditor_last_name\": \"P.\",\n" +
                        "        \"creditor_alias_type\": \"MOBL\",\n" +
                        "        \"creditor_id\": \"VD10512707\",\n" +
                        "        \"creditor_id_type\": \"AGENT\",\n" +
                        "        \"creditor_first_name\": \"Oleksandr\"\n" +
                        "    },\n" +
                        "    \"creation_date_time\": \"2025-07-31T13:30:47.626Z\"\n" +
                        "}");
    }

}
