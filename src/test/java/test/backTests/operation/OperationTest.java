package test.backTests.operation;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.operations.Operations;
import org.testng.annotations.Test;

@Test
public class OperationTest extends BaseTest {

    Operations operations = new Operations();

    public void positive_test(){
        operations.create("{\n" +
                "    \"service_id\": \"6376\",\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"fields\": [\n" +
                "        {\n" +
                "            \"alias\": \"account\",\n" +
                "            \"value\": \"UA323052990000026203672535242\",\n" +
                "            \"type\": \"TEXT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"alias\": \"okpo\",\n" +
                "            \"value\": \"3283410791\",\n" +
                "            \"type\": \"TEXT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"alias\": \"passport_series\",\n" +
                "            \"value\": \"\",\n" +
                "            \"type\": \"TEXT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"alias\": \"passport_number\",\n" +
                "            \"value\": \"\",\n" +
                "            \"type\": \"TEXT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"alias\": \"receiver_name\",\n" +
                "            \"value\": \"Test test\",\n" +
                "            \"type\": \"TEXT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"alias\": \"pryznach\",\n" +
                "            \"value\": \"Поповнення рахунку в іншому банку\",\n" +
                "            \"type\": \"TEXT\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"alias\": \"TOTAL_AMOUNT\",\n" +
                "            \"value\": \"1000\",\n" +
                "            \"type\": \"AMOUNT\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");

        operations.confirm("{\n" +
                "    \"operation_id\": "+operations.getOperation_id()+",\n" +
                "    \"total_amount\": 1000,\n" +
                "    \"session\": {\n" +
                "        \"unified_client_id\": \"c6c5def0-8077-498b-899c-53a2528cfde9\",\n" +
                "        \"session_type\": \"PUMB_ONLINE\"\n" +
                "    },\n" +
                "    \"point_id\": 3329,\n" +
                "    \"client\": {\n" +
                "        \"source\": \"PH\",\n" +
                "        \"id\": \"2189387\",\n" +
                "        \"ib_branch_code\": \"KIE\",\n" +
                "        \"ekb_id\": \"2290175\",\n" +
                "        \"full_name\": \"Марусенко Дмитро Андрійович\",\n" +
                "        \"tax_id\": \"3283410791\",\n" +
                "        \"address\": {\n" +
                "            \"id\": \"a6ec9dcd-3ca4-4c9a-83f0-9b81d3b567db\",\n" +
                "            \"clientId\": \"c6c5def0-8077-498b-899c-53a2528cfde9\",\n" +
                "            \"type\": 1,\n" +
                "            \"province\": \"Київська обл.\",\n" +
                "            \"countryCode\": \"804\",\n" +
                "            \"localityType\": \"смт\",\n" +
                "            \"locality\": \"Кожанка\",\n" +
                "            \"streetType\": \"вул.\",\n" +
                "            \"street\": \"Садова\",\n" +
                "            \"dwellingType\": \"буд.\",\n" +
                "            \"house\": \"57\",\n" +
                "            \"apartment\": \"\",\n" +
                "            \"postalCode\": \"08550\"\n" +
                "        },\n" +
                "        \"user-agent\": \"Conveyor 1.0\",\n" +
                "        \"ip\": \"172.29.178.71\"\n" +
                "    },\n" +
                "    \"confirm_only\": true,\n" +
                "    \"create_template\": false\n" +
                "}");

        operations.pay("{\n" +
                "    \"operation_id\": "+operations.getOperation_id()+",\n" +
                "    \"point_id\": 3329,\n" +
                "    \"client\": {\n" +
                "        \"id\": \"2189387\",\n" +
                "        \"source\": \"PH\",\n" +
                "        \"tax_id\": \"3283410791\",\n" +
                "        \"full_name\": \"Марусенко Дмитро Андрійович\"\n" +
                "    },\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA903348510000026204404198193\"\n" +
                "    }\n" +
                "}");
    }
}
