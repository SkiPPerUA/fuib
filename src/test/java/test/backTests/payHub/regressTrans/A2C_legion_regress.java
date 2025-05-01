package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2A;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C_legion;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.annotations.Test;

@Test
public class A2C_legion_regress extends BaseTest {

    public void positiveTest_ACCOUNT_ID_to_PAN(){
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(6567363));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": 300,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"ACCOUNT_ID\",\n" +
                "        \"value\": \"144978212\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"1141542\"\n" +
                "        }\n"+
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.MONO_VISA,Card_param.pan)+"\"\n" +
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
    }

    public void positiveTest_IBAN_to_PAN() {
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(6241781));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": 150,\n" +
                "    \"fee_amount\": 100,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"аываыв\",\n" +
                "    \"external_id\": \"" + Uuid_helper.generate_uuid() + "\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA953348510000026201112609803\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"8531524\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"4999999999990011\"\n" +
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
    }

    public void positiveTest_ACCOUNT_ID_to_PHONE(){
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(6567363));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": 300,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"ACCOUNT_ID\",\n" +
                "        \"value\": \"144978212\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"1141542\"\n" +
                "        }\n"+
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"PHONE\",\n" +
                "        \"value\": \"380502033277\"\n" +
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
    }

    public void positiveTest_crossborder(){
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(6567363));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": 300,\n" +
                "    \"service\": \"crossborder\",\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"CARD_ID\",\n" +
                "        \"value\": \"018063400990\",\n" +
                "        \"instrument\": \"OWN_CARD\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"1141542\"\n" +
                "        }\n"+
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"5575191548185686\"\n" +
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
    }
}
