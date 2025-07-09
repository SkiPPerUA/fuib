package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C_legion;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.annotations.Test;

import java.sql.SQLException;

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
                "        \"value\": \"5168745611327906\"\n" +
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
    }

    public void positiveTest_IBAN_to_PAN() {
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(6241781));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": 20000,\n" +
                "    \"fee_amount\": 100,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"3041309906\",\n" +
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
                "        \"value\": \"5168745611327906\"\n" +
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
//            try {
//                BDpostgre.BDpostgre("ph-ms-db.test-fuib.com:5000/","transacter_test", "dev","password");
//                BDpostgre.updateSQL("update transactions.transactions set created_at = '2025-04-20 10:58:17.081 +0300' where created_at > '2025-05-21 11:58:18.963 +0300'");
//            } catch (Throwable e) {
//                throw new RuntimeException(e);
//            } finally {
//                try {
//                    BDpostgre.closeConn();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
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

    public void positiveTest_crossborderUAH(){
        A2C_legion a2C_legion = new A2C_legion(new Trans_token_payhub(6241781));
        a2C_legion.initTransfers("{\n" +
                "    \"amount\": 1000,\n" +
                        "    \"service\": \"crossborder_direct\",\n" +
                        "    \"currency\": \"USD\",\n" +
                        "    \"description\": \"test\",\n" +
                        "    \"authentication\": {" +
                        "        \"jwt\": \""+a2C_legion.getToken()+"\"," +
                        "        \"session_id\": \"225e7e87-8555-486d-aefd-9ed2af8109ce\"," +
                        "        \"device_id\": \"60c30bbc4dd94b4fdaf7398f\"," +
                        "        \"ip\": \"45.90.16.93\"," +
                        "        \"event_type\": \"APP_A2CSBRD\"," +
                        "        \"login\": \"zubkovam\"," +
                        "        \"application\": \"ANDROID\"," +
                        "        \"app_version\": \"1.1.1.1\"" +
                        "    }," +
                        "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                        "    \"sender_exchange\":{\n" +
                        "           \"source\":\"CARD_ID\",\n" +
                        "           \"value\":\"021129845704\"\n" +
                        "       },"+
                        "       \"currency_exchange_data\": {\n" +
                        "               \"attributes\": {\n" +
                        "               \"sell_currency\": \"UAH\",\n" +
                        "               \"buy_currency\": \"USD\",\n" +
                        "               \"rate\": 41.5200000,\n" +
                        "               \"base_currency\": \"USD\"\n" +
                        "       },\n" +
                        "       \"sell\": {\n" +
                        "           \"currency_code\": \"UAH\",\n" +
                        "           \"amount\": 417000\n" +
                        "           },\n" +
                        "       \"buy\": {\n" +
                        "           \"currency_code\": \"USD\",\n" +
                        "           \"amount\": 100\n" +
                        "       }}," +
                        "    \"sender\": {\n" +
                        "        \"source\": \"CARD_ID\",\n" +
                        "        \"value\": \"028417738202\",\n" +
                        "        \"instrument\": \"OWN_CARD\",\n" +
                        "        \"client\": {\n" +
                        "            \"source\": \"EKB\",\n" +
                        "            \"id\": \"2290175\"\n" +
                        "        }\n"+
                        "    },\n" +
                        "      \"recipient_data\": {\n" +
                        "    \"general\": {\n" +
                        "      \"first_name\": \"Bill\",\n" +
                        "      \"last_name\": \"Clinton\",\n" +
                        "      \"middle_name\": \"middle\"\n" +
                        "    }\n" +
                        "  },"+
                        "    \"recipient\": {\n" +
                        "        \"source\": \"PAN\",\n" +
                        "        \"value\": \"5575191548185686\"\n" +
                        "    }\n" +
                        "}");
        a2C_legion.confirmTransfers("{\n" +
                "  \"lang\": \"UK\",\n" +
                "  \"authentication\": {\n" +
                "    \"otp_code\": \"1111\",\n" +
                "    \"jwt\": \""+a2C_legion.getToken()+"\"\n" +
                "  }\n" +
                "}");
    }

    public void positiveTest_crossborder(){
        A2C_legion a2C_legion = new A2C_legion(new Trans_token_payhub(6241781));
        a2C_legion.initTransfers("{\n" +
                "    \"amount\": 900,\n" +
                "    \"service\": \"crossborder_direct\",\n" +
                "    \"currency\": \"USD\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"authentication\": {" +
                "        \"jwt\": \""+a2C_legion.getToken()+"\"," +
                "        \"session_id\": \"225e7e87-8555-486d-aefd-9ed2af8109ce\"," +
                "        \"device_id\": \"60c30bbc4dd94b4fdaf7398f\"," +
                "        \"ip\": \"45.90.16.93\"," +
                "        \"event_type\": \"APP_A2CSBRD\"," +
                "        \"login\": \"zubkovam\"," +
                "        \"application\": \"ANDROID\"," +
                "        \"app_version\": \"1.1.1.1\"" +
                "    }," +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"CARD_ID\",\n" +
                "        \"value\": \"028417738202\",\n" +
                "        \"instrument\": \"OWN_CARD\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"2290175\"\n" +
                "        }\n"+
                "    },\n" +
                "      \"recipient_data\": {\n" +
                "    \"general\": {\n" +
                "      \"first_name\": \"Bill\",\n" +
                "      \"last_name\": \"Clinton\",\n" +
                "      \"middle_name\": \"middle\"\n" +
                "    }\n" +
                "  },"+
                "    \"recipient\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"5575191548185686\"\n" +
                "    }\n" +
                "}");
        a2C_legion.confirmTransfers("{\n" +
                "  \"lang\": \"UK\",\n" +
                "  \"authentication\": {\n" +
                "    \"otp_code\": \"1111\",\n" +
                "    \"jwt\": \""+a2C_legion.getToken()+"\"\n" +
                "  }\n" +
                "}");
    }
}
