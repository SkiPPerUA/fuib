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

    public void positiveTest() throws InterruptedException {
               a2a = new A2A("{\n" +
                        "    \"service\": \"internal_fuib_uah\",\n" + //a2sep
                        "    \"amount\": 722,\n" +
                        "    \"fee_amount\": 12,\n" +
                        "    \"currency\": \"UAH\",\n" +
                        "    \"description\": \"test\",\n" +
                        "    \"purpose\": \"some purpose\",\n" +
                        "    \"sender\": {\n" +
                        "        \"source\": \"ACCOUNT_ID\",\n" +
                        "        \"card_id\": \"100501\",\n" +
                        "        \"value\": \"126856282\"\n" +
//                        "        \"client\": {\n" +
//                        "            \"source\": \"EKB\",\n" +
//                        "            \"id\": \"1299125\"\n" +
//                        "        }\n" +
                        "    },\n" +
                        "    \"recipient\": {\n" +
                        "        \"source\": \"IBAN\",\n" +
                        "        \"card_id\": \"100500\",\n" +
                        "        \"card_mask\": \"444111******1111\",\n" +
                        "        \"phone\": \"3809876543211\",\n" +
                        "        \"tax_id\":\"3462406451\"," +
                        "        \"value\": \"UA563348510000026201113488937\"\n" + //UA973220010000026203303699802 моно
                        "    },\n" +
                        "    \"authentication\": {\n" +
                        "        \"device_id\": \"test1\",\n" +
                        "        \"session_id\": \"test22\",\n" +
                        "        \"ip\": \"192.168.0.1\",\n" +
                        "        \"event_type\": \"APP_A2P\"\n" +
                        "    }\n" +
                        "}");
        Thread.sleep(30000);
        a2a.getStatus(a2a.getTransactionId());
        a2a.getDetails(a2a.getTransactionId(),"8531524");
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

    public void positiveTest_own_fuib() throws InterruptedException {
       a2a.setToken(new Trans_token_payhub(6241781));
       a2a.setBodyRequest("{\n" +
               "    \"service\": \"own_fuib\",\n" +
               "    \"amount\": 123,\n" +
               "    \"fee_amount\": 100,\n" +
               "    \"currency\": \"UAH\",\n" +
               "    \"purpose\": \"own_fuib\",\n" +
               "    \"description\": \"test\",\n" +
               "    \"sender\": {\n" +
               "        \"source\": \"IBAN\",\n" +
               "        \"card_id\": \"100501\",\n" +
               "        \"value\": \"UA953348510000026201112609803\"\n" + //UA953348510000026201112609803
               "    },\n" +
               "    \"recipient\": {\n" +
               "        \"source\": \"IBAN\",\n" +
               "        \"card_id\": \"100500\",\n" +
               "        \"card_mask\": \"444111******1111\",\n" +
               "        \"phone\": \"3809876543211\",\n" +
               "        \"value\": \"UA323348510000026208119209027\"\n" + //UA323348510000026208119209027
               "    }\n" +
               "}");
       a2a.makeTrans();
       Thread.sleep(30000);
       a2a.getStatus(a2a.getTransactionId());
       a2a.getDetails(a2a.getTransactionId(),"8531524");
    }

    public void positiveTest_privatBank(){
        a2a.setToken(new Trans_token_payhub(2189387));
        a2a.setBodyRequest("{\n" +
                "    \"service\": \"out_direct_acc\",\n" + //A2SMP  out_direct_acc
                "    \"amount\": 1000,\n" +
                "    \"fee_amount\": 100,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"purpose\": \"Private24\",\n" +
                "    \"description\": \"Паєвський Сергій Сергійович - 20/12/1982\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA903348510000026204404198193\"\n" +
                "    },\n" +
                "    \"jwt\": \""+a2a.getToken()+"\",\n" +
                "    \"authentication\": {\n" +
                "        \"jwt\": \""+a2a.getToken()+"\",\n" +
                "        \"session_id\": \"225e79ce\",\n" +
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

    public void oneStep_toPrivatA2A_create() throws InterruptedException {
        String external = Uuid_helper.generate_uuid4();
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("createA2ATransfer","A2A.transfers.input");
        A2A a2a = new A2A();
        a2a.setToken(new Trans_token_payhub(6241781));
        rabbitMQHttp.sendHttp("{" +
                "   \"external_id\":\""+external+"\"," +
                "   \"merchant_id\":\"10546197-0d2f-4059-b9a2-d01cb97eba61\"," +
                "   \"amount\":300," +
                "   \"currency\":\"UAH\"," +
                "   \"purpose\":\"ssss\"," +
                "   \"service\":\"OUT_A2DIRECT\"," +
                "   \"client_ip\":\"192.168.76.13\"," +
                "   \"sender\":{" +
                "      \"iban\":\"UA953348510000026201112609803\"," +
                "      \"sirius_client_id\":6241781" +
                "   }," +
                "   \"recipient\":{" +
                "      \"iban\":\"UA253052990000026207671635945\"," +
                "      \"tax_id\":\"3462406451\"" +
                "   }" +
                "}");

        Thread.sleep(30000);

        oneStep_toPrivatA2A_confirm(external);
    }

    private void oneStep_toPrivatA2A_confirm(String external_id){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("confirmA2ATransfer","A2A.transfers.input");
        A2A a2a = new A2A();
        a2a.setToken(new Trans_token_payhub(6241781));
        rabbitMQHttp.sendHttp("{" +
                "   \"external_id\":\""+external_id+"\"," +
                "   \"merchant_id\":\"10546197-0d2f-4059-b9a2-d01cb97eba61\"," +
                "   \"routing_key\":\"a2a_transfers.status\"," +
                "   \"commission_amount\":300," +
                "   \"commission_currency\":\"UAH\"," +
                "   \"commission_purpose\":\"Комісійна винагорода за переказ (#PH)\"," +
                "   \"purpose\":\"тест влад\"," +
                "   \"recipient\":{" +
                "      \"iban\":\"UA253052990000026207671635945\"," +
                "      \"moniker\":\"\"," +
                "      \"full_name\":\"Test VLADYSLAV\"," +
                "      \"tax_id\":\"3462406451\"," +
                "      \"passport_series\":\"\"," +
                "      \"passport_number\":\"\"" +
                "   }," +
                "   \"ultimate_debtor\":{" +
                "      \"name\":\"\"," +
                "      \"tax_id\":\"\"," +
                "      \"passport_series\":\"\"," +
                "      \"passport_number\":\"\"" +
                "   }," +
                "   \"initiating_party\":{" +
                "      \"tax_id\":\"\"," +
                "      \"name\":\"\"," +
                "      \"passport_series\":\"\"," +
                "      \"passport_number\":\"\"" +
                "   }" +
                "}");
    }

    public void getStatus_rpc(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("getStatus","A2A.transfers.input");
        rabbitMQHttp.sendHttp("{\"id\":\"e2c90c33-23db-479f-b06a-3b405201fe31\"," +
                "                \"merchant_id\":\"10546197-0d2f-4059-b9a2-d01cb97eba61\"}");
    }
}
