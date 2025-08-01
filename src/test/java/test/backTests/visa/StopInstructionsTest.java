package test.backTests.visa;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.visa.visaAllias.StopInstructions;
import org.testng.annotations.Test;

@Test
public class StopInstructionsTest extends BaseTest {

    String body = "";
    StopInstructions stopInstructions = new StopInstructions();

    public void test_addMerchants(){
        logStartTest("test_addMerchants");
        body = "{\n" +
                "  \"duration\": 1,\n" +
                "  \"merchant_identifiers\": {\n" +
                "    \"card_acceptor_ids\": [\n" +
                "      \"022569990001111\",\n" +
                "      \"022569990001123\"\n" +
                "    ],\n" +
                "    \"merchant_names\": [\n" +
                "      \"FLIXNET\",\n" +
                "      \"FLIXNET2\"\n" +
                "    ],\n" +
                "    \"payment_facilitators\": [\n" +
                "      {\n" +
                "        \"payment_facilitator_id\": \"PAIPAL\",\n" +
                "        \"sub_merchant_id\": \"002010101010102\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"pan\": \"4111111111111111\",\n" +
                "  \"start_date\": \"2021-02-01\",\n" +
                "  \"additional\": {\n" +
                "    \"additional_notes\": \"Test FUIB\",\n" +
                "    \"payment_frequency\": \"One off\"\n" +
                "  },\n" +
                "  \"merchant_category_code\": \"7801\",\n" +
                "  \"one_time_stop\": false,\n" +
                "  \"transaction_amount\": {\n" +
                "    \"exact_amount\": 100,\n" +
                "    \"max_amount\": 500,\n" +
                "    \"min_amount\": 50\n" +
                "  }\n" +
                "}";
        stopInstructions.addMerchants(body);
        logFinishTest("test_addMerchants");
    }

    public void test_findInstructionsByPan(){
        logStartTest("test_findInstructionsByPan");
        body = "{\n" +
                "  \"pan\": \"4111111111111111\",\n" +
                "  \"include_inactive\": false\n" +
                "}";
        stopInstructions.findInstructionsByPan(body);
        logFinishTest("test_findInstructionsByPan");
    }

    public void test_deleteInstructions(){
        logStartTest("test_deleteInstructions");
        body = "{\n" +
                "  \"cancel_stop_instructions\": [\n" +
                "    \"987654321\"\n" +
                "  ]\n" +
                "}";
        stopInstructions.deleteInstructions(body);
        logFinishTest("test_deleteInstructions");
    }

    public void test_continueInstructions(){
        logStartTest("test_continueInstructions");
        body = "{\n" +
                "  \"extend_stop_instructions\": [\n" +
                "    {\n" +
                "      \"duration\": 2,\n" +
                "      \"stop_instruction_id\": \"1234568\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        stopInstructions.continueInstructions(body);
        logFinishTest("test_continueInstructions");
    }

    public void test_getAllTransactions(){
        logStartTest("test_getAllTransactions");
        body = "{\n" +
                "  \"pan\": \"4111111111111111\",\n" +
                "  \"range\": 30,\n" +
                "  \"start_date\": \"2023-09-10\"\n" +
                "}";
        stopInstructions.getAllTransactions(body);
        System.out.println(body);
        logFinishTest("test_getAllTransactions");
    }

    public void test_getInstructions(){
        logStartTest("test_getInstructions");
        stopInstructions.getInstructions("1234568");
        logFinishTest("test_getInstructions");
    }
}
