package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2A;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.testng.annotations.Test;

@Test
public class A2Aregress extends BaseTest {
    A2A a2a = new A2A();

    String [] services = {"internal_fuib_uah","internal_relatives","internal_relatives_currency","out_direct_acc","in_direct_acc","own_fuib","A2P_alias"};

    public void positiveTest(){
                new A2A("{\n" +
                        "    \"service\": \"internal_fuib_uah\",\n" +
                        "    \"amount\": 991,\n" +
                        "    \"fee_amount\": 12,\n" +
                        "    \"currency\": \"UAH\",\n" +
                        "    \"description\": \"test\",\n" +
                        "    \"purpose\": \"some purpose\",\n" +
                        "    \"sender\": {\n" +
                        "        \"source\": \"ACCOUNT_ID\",\n" +
                        "        \"value\": \"126856282\"\n" +
//                        "        \"client\": {\n" +
//                        "            \"source\": \"EKB\",\n" +
//                        "            \"id\": \"1299125\"\n" +
//                        "        }\n" +
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
                        "    }\n" +
                        "}");
    }

    public void positiveAllTest(){
        for (String service : services) {
            if (!service.equals("own_fuib")) {
                new A2A("{\n" +
                        "    \"service\": \"" + service + "\",\n" +
                        "    \"amount\": 1000,\n" +
                        "    \"fee_amount\": 100,\n" +
                        "    \"currency\": \"UAH\",\n" +
                        "    \"description\": \"test\",\n" +
                        "    \"purpose\": \"some purpose\",\n" +
                        "    \"sender\": {\n" +
                        "        \"source\": \"ACCOUNT_ID\",\n" +
                        "        \"value\": \"178874672\",\n" +
                        "        \"client\": {\n" +
                        "            \"source\": \"EKB\",\n" +
                        "            \"id\": \"1299125\"\n" +
                        "        }\n" +
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
                        "    }\n" +
                        "}");
            }
        }
    }

    public void positiveTest_own_fuib(){
       a2a.setToken(new Trans_token_payhub(6241781));
       a2a.setBodyRequest("{\n" +
               "    \"service\": \"own_fuib\",\n" +
               "    \"amount\": 100,\n" +
               "    \"fee_amount\": 100,\n" +
               "    \"currency\": \"UAH\",\n" +
               "    \"purpose\": \"own_fuib\",\n" +
               "    \"description\": \"test\",\n" +
               "    \"sender\": {\n" +
               "        \"source\": \"IBAN\",\n" +
               "        \"value\": \"UA323348510000026208119209027\"\n" +
               "    },\n" +
               "    \"recipient\": {\n" +
               "        \"source\": \"IBAN\",\n" +
               "        \"value\": \"UA953348510000026201112609803\"\n" +
               "    }\n" +
               "}");

       a2a.makeTrans();
    }

    public void positiveTest_privatBank(){
        a2a.setToken(new Trans_token_payhub(2189387));
        a2a.setBodyRequest("{\n" +
                "    \"service\": \"out_direct_acc\",\n" + //A2SMP  out_direct_acc
                "    \"amount\": 200,\n" +
                "    \"fee_amount\": 100,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"purpose\": \"Private24\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA903348510000026204404198193\"\n" +
                "    },\n" +
                "    \"jwt\": \""+a2a.getToken()+"\",\n" +
                "    \"authentication\": {\n" +
                "        \"jwt\": \""+a2a.getToken()+"\",\n" +
                "        \"session_id\": \"225e7e87-8555-486d-aefd-9ed2af8109ce\",\n" +
                "        \"device_id\": \"60c30bbc4dd94b4fdaf7398f\",\n" +
                "        \"ip\": \"45.90.16.93\",\n" +
                "        \"event_type\": \"APP_A2A\",\n" +
                "        \"login\": \"zubkovam\",\n" +
                "        \"application\": \"ANDROID\",\n" +
                "        \"app_version\": \"1.1.1.1\"\n" +
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA253052990000026207671635945\"\n" + //UA963052990262056400970225920  UA253052990000026207671635945
                "    }\n" +
                "}");
        a2a.makeTrans();
    }

    public void positiveTest_FROMprivatBank_TOfuib_oneStep(){
        a2a.setToken(new Trans_token_payhub(6567363));
        a2a.setBodyRequest("{\n" +
                "    \"amount\": 100,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"external_id\": \""+Uuid_helper.generate_uuid()+"\",\n" +
                "    \"service\": \"in_direct_acc\",\n" +
                "    \"purpose\": \"Private24\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA253052990000026207671635945\"\n" +
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA323348510000026208119209027\"\n" + //UA953348510000026201112609803
                "    }"+
                "}");
        a2a.makeTrans();
    }

    public void positiveTest_FROMprivatBank_TOfuib_twoStep(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("initTransfer","A2A.transfers.input");
        A2A a2a = new A2A();
        a2a.setToken(new Trans_token_payhub(237368));
        rabbitMQHttp.sendHttp("{" +
                "   \"amount\":100," +
                "   \"currency\":\"UAH\"," +
                "   \"service\":\"IN_DIRECT_ACC\"," +
                "   \"commission\":0," +
                "   \"merchant_id\":\"10546197-0d2f-4059-b9a2-d01cb97eba61\"," +
                "   \"description\":\"Переказ по родинному зв`язку\"," +
                "   \"purpose\":\"Поповнення рахунку в ПУМБ\"," +
                "   \"client_ip\":\"172.28.178.71\"," +
                "   \"payer\":{" +
                "      \"source\":\"IBAN\"," +
                "      \"value\":\"UA253052990000026207671635945\"" +
                "   }," +
                "   \"receiver\":{" +
                "      \"source\":\"IBAN\"," +
                "      \"value\":\"UA713348510000026201116887159\"" +
                "   }," +
                "   \"jwt\":\""+a2a.getToken()+"\"," +
                "    \"authentication\": {" +
                "        \"jwt\": \""+a2a.getToken()+"\"," +
                "        \"session_id\": \"225e7e87-8555-486d-aefd-9ed2af8109ce\"," +
                "        \"device_id\": \"60c30bbc4dd94b4fdaf7398f\"," +
                "        \"ip\": \"45.90.16.93\"," +
                "        \"event_type\": \"APP_A2A\"," +
                "        \"login\": \"zubkovam\"," +
                "        \"application\": \"ANDROID\"," +
                "        \"app_version\": \"1.1.1.1\"" +
                "    }" +
                "}");
    }

    public void confirm_FROMprivatBank_TOfuib_twoStep(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("confirmTransfer","A2A.transfers.input");
        rabbitMQHttp.sendHttp("{" +
                "   \"merchant_id\":\"10546197-0d2f-4059-b9a2-d01cb97eba61\"," +
                "   \"id\":\"7781d35c-6a69-42df-9654-190d5a3b23ab\"" +
                "}");
    }

    public void oneStep_toPrivat(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("createTransfer","A2A.transfers.input");
        A2A a2a = new A2A();
        a2a.setToken(new Trans_token_payhub(237368));
        rabbitMQHttp.sendHttp("{" +
                "   \"service\":\"OUT_DIRECT_ACC\"," +
                "   \"merchant_id\":\"10546197-0d2f-4059-b9a2-d01cb97eba61\"," +
                "   \"amount\":1001," +
                "   \"fee_amount\":100," +
                "   \"currency\":\"UAH\"," +
                "   \"purpose\":\"Private24\"," +
                "   \"description\":\"test\"," +
                "   \"client_ip\":\"45.90.16.93\"," +
                "   \"payer\":{" +
                "      \"source\":\"IBAN\"," +
                "      \"value\":\"UA713348510000026201116887159\"" +
                "   }," +
                "\"jwt\":\""+a2a.getToken()+"\"," +
                "   \"receiver\":{" +
                "      \"source\":\"IBAN\"," +
                "      \"value\":\"UA253052990000026207671635945\"" +
                "   }" +
                "}");
    }
}
