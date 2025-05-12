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
                "            \"value\": \"UA293052990262086400964529495\",\n" +
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
                "\"operation_id\": \""+operations.getOperation_id()+"\",\n" +
                "\"total_amount\": 112,\n" +
                "\"create_template\": false,\n" +
                "    \"confirm_only\": true,\n" +
                "\"payer\": {\n" +
                "   \"source\": \"IBAN\",\n" +
                "   \"value\": \"UA903348510000026204404198193\"\n" +
                "}\n" +
                "}");

//        operations.pay("{\n" +
//                "    \"operation_id\": "+operations.getOperation_id()+",\n" +
//                "    \"point_id\": 3329,\n" +
//                "    \"client\": {\n" +
//                "        \"id\": \"2189387\",\n" +
//                "        \"source\": \"PH\",\n" +
//                "        \"tax_id\": \"3283410791\",\n" +
//                "        \"full_name\": \"Марусенко Дмитро Андрійович\"\n" +
//                "    },\n" +
//                "    \"payer\": {\n" +
//                "        \"source\": \"IBAN\",\n" +
//                "        \"value\": \"UA903348510000026204404198193\"\n" +
//                "    }\n" +
//                "}");
    }
}
