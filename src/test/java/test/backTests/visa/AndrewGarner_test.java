package test.backTests.visa;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.visa.requestToPay.AndrewGarner;
import org.testng.annotations.Test;

@Test
public class AndrewGarner_test extends BaseTest {

    AndrewGarner andrewGarner = new AndrewGarner();

    public void positive_init(){
        andrewGarner.setExpectedResponseCode(201);
        andrewGarner.init("{\n" +
                "    \"product\": \"VD\",\n" +
                "    \"use_case\": \"P2P\",\n" +
//                "    \"request_reason\": {\n" +
//                "        \"message\": \"test\"\n" +
//                "    },\n" +
                "    \"payment_requests\": [\n" +
                "        {\n" +
                "            \"debtor_alias\": \"+380939917659\",\n" +
                "            \"debtor_first_name\": \"Tolo\",\n" +
                "            \"debtor_last_name\": \"P.\",\n" +
                "            \"debtor_agent_country\": \"UA\",\n" +
                "            \"debtor_country\": \"UA\",\n" +
                "            \"requested_amount\": 100,\n" +
                "            \"requested_amount_currency\": \"UAH\",\n" +
                "            \"end_to_end_id\": \""+Uuid_helper.generate_uuid4()+"\",\n" +
                "            \"debtor_agent_id\": \"38042668479\",\n" +
                "            \"debtor_alias_type\": \"MOBL\"\n" +
                "        }\n" +
                "    ],\n" +
                //"    \"due_date\": \"2025-09-29\",\n" +
                "    \"request_message_id\": \""+ Uuid_helper.generate_uuid4() +"\",\n" +
                "    \"settlement_options\": [\n" +
                "        {\n" +
                "            \"settlement_system\": \"MASTERCARD\",\n" +
                "            \"primary_account_number\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\"\n" +
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

    public void positive_referenceData(){
        andrewGarner.referenceData("{\n" +
                "  \"reference_data_types\": [\n" +
                "    \"available_participants\"\n" +
                "  ],\n" +
                "  \"request_message_id\": \"ad244fssx6b43188f7ce5d5f93471db\",\n" +
                "  \"creation_date_time\": \"2020-08-08T09:30:47.001Z\"\n" +
                "}");
    }

    public void positive_refund(){
        andrewGarner.refund("","{\n" +
                "  \"payment_requests\": [\n" +
                "    {\n" +
                "      \"requested_amount\": 100,\n" +
                "      \"end_to_end_id\": \"RFPid0001\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"request_message_id\": \"GG9983636387737JH\",\n" +
                "  \"settlement_options\": [\n" +
                "    {\n" +
                "      \"settlement_system\": \"VISA_DIRECT\",\n" +
                "      \"primary_account_number\": \"4145124125553228\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"creditor\": {\n" +
                "    \"creditor_id\": \"BL1234567890\",\n" +
                "    \"creditor_id_type\": \"AGENT\"\n" +
                "  },\n" +
                "  \"creation_date_time\": \"2020-12-17T09:30:47Z\"\n" +
                "}");
    }

    public void positive_retrieve(){
        andrewGarner.retrieve("");
    }

    public void positive_amend(){
        andrewGarner.amend("","{\n" +
                "  \"request_reason\": {\n" +
                "    \"message\": \"For lunch\",\n" +
                "    \"unicode_emoji\": \"U+1F382\"\n" +
                "  },\n" +
                "  \"due_date\": \"2023-10-10\",\n" +
                "  \"request_message_id\": \"GG9983636387737JH\",\n" +
                "  \"settlement_options\": [\n" +
                "    {\n" +
                "      \"settlement_system\": \"VISA_DIRECT\",\n" +
                "      \"primary_account_number\": \"4145124125553222\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"request_options\": {\n" +
                "    \"reminder_schedule\": [\n" +
                "      \"2023-05-30T09:30:47Z\",\n" +
                "      \"2023-06-11T11:22:33Z\",\n" +
                "      \"2023-07-11T11:45:13Z\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"payment_request\": {\n" +
                "    \"requested_amount\": 100.11\n" +
                "  },\n" +
                "  \"creation_date_time\": \"2020-12-17T09:30:47Z\"\n" +
                "}");
    }

    public void positive_tag(){
        andrewGarner.tag("{\n" +
                "  \"messageEvent\": {\n" +
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

    public void positive_confirm(){
        positive_init();
        andrewGarner.confirm("{\n" +
                //"  \"status_reason\": \"RJ02\",\n" +
                "  \"transaction_status\": \"RJCT\",\n" +
                "  \"payment_request_id\": \""+andrewGarner.getPayment_request_id()+"\",\n" +
                "  \"request_message_id\": \"GG9983636387737JH\",\n" +
                //"  \"message\": \"Rejected by Payer\",\n" +
                "  \"end_to_end_id\": \""+andrewGarner.getEnd_to_end_id()+"\",\n" +
                "  \"creation_date_time\": \"2020-12-17T09:30:47Z\"\n" +
                "}");
    }

    public void positive_cancel(){
        andrewGarner.cancel("","{\n" +
                "  \"cancellation_reason\": \"PAID\",\n" +
                "  \"request_message_id\": \"2c253f102c50-493b-b2c5-847d7f50b029\",\n" +
                "  \"creation_date_time\": \"2020-12-17T09:30:47Z\"\n" +
                "}");
    }

    public void positive_retrieves(){
        andrewGarner.retrieves(" {\n" +
                "   \"agent_id\": \"VD123445\",\n" +
                "   \"end_to_end_ids\": [\n" +
                "        \"RFPid0001\",\n" +
                "        \"RFPid0002\"\n" +
                "    ]\n" +
                " }");
    }

    public void positive_viewBlock(){
        andrewGarner.viewBlock("{\n" +
                "  \"debtor_id\": \"VD123445\",\n" +
                "  \"debtor_id_type\": \"AGENT\",\n" +
                "  \"debtor_agent_id\": \"455200923457\",\n" +
                "  \"creation_date_time\": \"2020-12-17T09:30:47Z\",\n" +
                "  \"request_message_id\": \"GG9983636387737JH\"\n" +
                "}");
    }

    public void positive_removeBlock(){
        andrewGarner.removeBlock("","{\n" +
                "  \"debtor_id\": \"VD123445\",\n" +
                "  \"debtor_id_type\": \"AGENT\",\n" +
                "  \"debtor_agent_id\": \"455200923457\",\n" +
                "  \"creation_date_time\": \"2020-12-17T09:30:47Z\",\n" +
                "  \"request_message_id\": \"GG9983636387737JH\"\n" +
                "}");
    }

}
