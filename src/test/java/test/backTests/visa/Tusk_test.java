package test.backTests.visa;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.visa.requestToPay.Tusk;
import org.testng.annotations.Test;

import java.util.Random;

@Test
public class Tusk_test extends BaseTest {

    Tusk tusk = new Tusk();

    public void positive_init(){
        tusk.setExpectedResponseCode(201);
        tusk.init("{\n" +
                "    \"product\": \"VD\",\n" +
                "    \"use_case\": \"P2P\",\n" +
//                "    \"request_reason\": {\n" +
//                "        \"message\": \"test121212\"\n" +
//                "    },\n" +
                "    \"payment_requests\": [\n" +
                "        {\n" +
                "            \"debtor_agent_country\": \"UA\",\n" +
                "            \"debtor_country\": \"UA\",\n" +
                "            \"debtor_alias\": \"380665767084\",\n" +
                "            \"payment_request_id\": \"FF"+String.valueOf(new Random().nextLong()).substring(1,14)+"TT\",\n" +
                "            \"requested_amount\": 1.25,\n" +
                "            \"requested_amount_currency\": \"UAH\",\n" +
                "            \"end_to_end_id\": \"RFPid0001\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"due_date\": \"2025-09-29\",\n" +
                "    \"request_message_id\": \"GG"+String.valueOf(new Random().nextLong()).substring(1,14)+"JA\",\n" +
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
                "        \"creditor_first_name\": \"first\",\n" +
                "        \"creditor_last_name\": \"last\",\n" +
                "        \"creditor_alias\": \"380665767084\",\n" +
                "        \"creditor_id\": \"BL1234567890\",\n" +
                "        \"creditor_id_type\": \"AGENT\"\n" +
                "    },\n" +
                "    \"creation_date_time\": \"2020-12-17T09:30:47Z\"\n" +
                "}");
    }

    public void positive_tags(){
        tusk.setExpectedResponseCode(200);
        tusk.tags("{\n" +
                "  \"message_event\": {\n" +
                "    \"creditor_ack_emoji\": \"string\",\n" +
                "    \"creditor_ack_message\": \"string\"\n" +
                "  },\n" +
                "  \"tagged_transaction\": {\n" +
                "    \"transaction_id\": \"RFP01314\",\n" +
                "    \"transaction_id_type\": \"R2P\",\n" +
                "    \"settlement_system_reference_id\": \"VIP1234567890\",\n" +
                "    \"settlment_system_reference_type\": \"RRN\"\n" +
                "  }\n" +
                "}");
    }

    public void positive_notifications(){
        tusk.setExpectedResponseCode(200);
        tusk.notifications("{\n" +
                "  \"events\": [\n" +
                "    {\n" +
                "      \"event_type\": \"REJECTED\",\n" +
                "      \"payment_request_id\": \"087e40d4a77b48359324bbf990621057\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"agent_id\": \"VD123445\",\n" +
                "  \"creation_date_time\": \"2020-12-17T09:30:47Z\",\n" +
                "  \"request_message_id\": \"GG9983636387737JH\"\n" +
                "}");
    }

    public void positive_confirms(){
        tusk.confirms("FF0484304153861TT","{\n" +
                "  \"status_reason\": \"RJ02\",\n" +
                "  \"transaction_status\": \"RJCT\",\n" +
                "  \"accepted_amount\": 100,\n" +
                "  \"accepted_amount_currency\": \"UAH\",\n" +
                "  \"payment_request_id\": \"FG"+String.valueOf(new Random().nextLong()).substring(1,14)+"TT\",\n" +
                "  \"request_message_id\": \"GG2576348185492JA\",\n" +
                "  \"message\": \"Rejected by Payer\",\n" +
                "  \"end_to_end_id\": \"RFPid0001\",\n" +
                "  \"creation_date_time\": \"2020-12-17T09:30:47Z\"\n" +
                "}");
    }

    public void positive_refunds(){
        tusk.setExpectedResponseCode(201);
        tusk.refunds("087e40d4a77b48359324bbf990621057","{\n" +
                        "    \"product\": \"VD\",\n" +
                        "    \"use_case\": \"P2P\",\n" +
                        "    \"payment_requests\": [\n" +
                        "        {\n" +
                        "            \"debtor_agent_country\": \"UA\",\n" +
                        "            \"debtor_country\": \"UA\",\n" +
                        "            \"debtor_alias\": \"380665767084\",\n" +
                        "            \"payment_request_id\": \"FF"+String.valueOf(new Random().nextLong()).substring(1,14)+"TT\",\n" +
                        "            \"requested_amount\": 125,\n" +
                        "            \"requested_amount_currency\": \"UAH\",\n" +
                        "            \"end_to_end_id\": \"RFPid0001\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"request_message_id\": \"GG"+String.valueOf(new Random().nextLong()).substring(1,14)+"JA\",\n" +
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
                        "        \"creditor_first_name\": \"first\",\n" +
                        "        \"creditor_last_name\": \"last\",\n" +
                        "        \"creditor_alias\": \"380665767085\",\n" +
                        "        \"creditor_id\": \"BL1234567890\",\n" +
                        "        \"creditor_id_type\": \"AGENT\"\n" +
                        "    },\n" +
                        "    \"creation_date_time\": \"2020-12-17T09:30:47Z\"\n" +
                        "}");
    }

    public void positive_cancels(){
        tusk.cancels("RFP11755262298174fMhV","{\n" +
                "  \"creation_date_time\": \"2020-12-17T09:30:47Z\",\n" +
                "  \"request_message_id\": \"GG"+ Uuid_helper.generate_uuid4() +"\",\n" +
                "  \"cancellation_reason\": \"ERRAMT\"\n" +
                "}");
    }

    public void positive_amends(){
        tusk.amends("087e40d4a77b48359324bbf990621057","{\n" +
                "  \"due_date\": \"2025-08-14\",\n" +
                "  \"request_reason\": {\n" +
                "    \"message\": \"string\",\n" +
                "    \"unicode_emoji\": \"string\"\n" +
                "  },\n" +
                "  \"payment_request\": {\n" +
                "    \"requested_amount\": 100\n" +
                "  },\n" +
                "  \"creation_date_time\": \"2020-12-17T09:30:47Z\",\n" +
                "  \"request_message_id\": \"GG9983636387737JH\",\n" +
                "  \"settlement_options\": [\n" +
                "    {\n" +
                "      \"receiving_alias\": \"447709123457\",\n" +
                "      \"settlement_system\": \"VISA_DIRECT\",\n" +
                "      \"receiving_aliasType\": \"MOBL\",\n" +
                "      \"primary_account_number\": \"4145125553222\"\n" +
                "    }\n" +
                "  ]\n" +
                "}");
    }

    public void positive_init_allFields(){
        tusk.init("{\n" +
                        "    \"product\": \"VD\",\n" +
                        "    \"use_case\": \"P2P\",\n" +
                        "    \"request_reason\": {\n" +
                        "       \"message\": \"For lunch\",\n" +
                        "       \"unicode_emoji\": \"U+1F382\"\n" +
                        "  },"+
                        "    \"payment_requests\": [\n" +
                        "        {\n" +
                        "            \"debtor_agent_country\": \"UA\",\n" +
                        "            \"debtor_country\": \"UA\",\n" +
                        "            \"debtor_alias\": \"380665767084\",\n" +
                        "            \"payment_request_id\": \"FF"+String.valueOf(new Random().nextLong()).substring(1,14)+"TT\",\n" +
                        "            \"requested_amount\": 125,\n" +
                        "            \"requested_amount_currency\": \"UAH\",\n" +
                        "            \"end_to_end_id\": \"RFPid0001\",\n" +
                        "            \"debtor_last_name\": \"B.\",\n" +
                        "            \"debtor_first_name\": \"John\",\n" +
                        "            \"debtor_agent_id\": \"VD123445\",\n" +
                        "            \"debtor_alias_type\": \"MOBL\""+
                        "        }\n" +
                        "    ],\n" +
                        "    \"due_date\": \"2021-03-17\","+
                        "    \"request_message_id\": \"GG"+String.valueOf(new Random().nextLong()).substring(1,14)+"JA\",\n" +
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
                        "        \"creditor_first_name\": \"first\",\n" +
                        "        \"creditor_last_name\": \"last\",\n" +
                        "        \"creditor_alias\": \"380665767085\",\n" +
                        "        \"creditor_id\": \"BL1234567890\",\n" +
                        "        \"creditor_id_type\": \"AGENT\",\n" +
                        "        \"creditor_alias_type\": \"MOBL\"\n" +
                        "    },\n" +
                        "    \"request_options\": {\n" +
                        "       \"allowMultiplePayments\": true,\n" +
                        "       \"closeWithFirstPayment\": false\n" +
                        "  },"+
                        "    \"creation_date_time\": \"2020-12-17T09:30:47Z\"\n" +
                        "}");
    }

    public void positive_getCard(){
        tusk.getCardNumberByCardId("021129845704");
    }
}
