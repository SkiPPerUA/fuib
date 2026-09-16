package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C_legion;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Random;

@Test
public class A2C_legion_regress extends BaseTest {

    public void positiveTest_ACCOUNT_ID_to_PAN() throws InterruptedException {
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(6241781));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": 100,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"sender\": {\n" +
                "        \"source\":\"ACCOUNT_ID\",\n" +
                "        \"value\":\"232786741\",\n" + //232786741 232226008-кредит. 126856282-дебет
                "        \"instrument\":\"OWN_CARD\",\n" +
                "        \"card_id\":\"025342752026\"," + //025342752026-кредит. 019117788042-дебет
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"8531524\"\n" +
                "        }\n"+
                "    },\n" +
                "    \"recipient_data\": {\n" +
                "       \"general\": {\n" +
                "           \"first_name\":\"test13йцукенгшщзхъёэждлорпавыфячсмитьбю\",\n" +
                "           \"tax_id\":\"1029138712\",\n" +
                "           \"birthday\":\"2012-12-12\",\n" +
                "           \"last_name\":\"testt2213вфвы\"\n" +
                "       }\n" +
                "    },\n" +
                "   \"authentication\":{\n" +
                "      \"device_id\":\"61f105c38fe68531c4fd1248\",\n" +
                "      \"session_id\":\"1775563181\",\n" +
                "      \"ip\":\"79.110.129.12\",\n" +
                "      \"event_type\":\"APP_A2C\",\n" +
                "      \"login\":\"0665767084\",\n" +
                "      \"application\":\"IOS\"\n" +
                "   },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC,Card_param.pan)+"\"\n" +
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
        Thread.sleep(30000);
        a2C_legion.getStatus(a2C_legion.getTransactionId());
        a2C_legion.getDetails(a2C_legion.getTransactionId(),"8531524");
    }

    public void positiveTest_IBAN_to_PAN() {
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(6241781));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": 1000,\n" +
                "    \"fee_amount\": 102,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"authentication\":{\n" +
                        "      \"device_id\":\"device_idVladTest\",\n" +
                        "      \"session_id\":\"VladTest\",\n" +
                        "      \"ip\":\"79.110.129.18\",\n" +
                        "      \"event_type\":\"APP_A2C\"\n" +
                        "   }," +
                "    \"description\": \"3041309906\",\n" +
                "    \"external_id\": \"" + Uuid_helper.generate_uuid() + "\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"card_id\": \"100501\",\n" +
                "        \"value\": \"UA953348510000026201112609803\",\n" + //UA323348510000026208119209027  UA953348510000026201112609803
                "        \"card_id\":\"019117787643\"," +                       //025342752026               019117787643
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"8531524\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"recipient_data\": {\n" +
                "       \"general\": {\n" +
                "           \"first_name\":\"test1\",\n" +
                "           \"tax_id\":\"1029138712\",\n" +
                "           \"birthday\":\"1985-01-02\",\n" +
                "           \"last_name\":\"testt2\"\n" +
                "       }\n" +
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"card_id\": \"100500\",\n" +
                "        \"card_mask\": \"444111******1111\",\n" +
                "        \"phone\": \"3809876543211\",\n" +
                "        \"value\": \"5168745611327906\"\n" + //приват 5168745611327906     приват 4149497548321415
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
    }

    public void positiveTest_IBANcredit_to_PAN() {
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(6241781));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": 1000,\n" +
                "    \"fee_amount\": 102,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"authentication\":{\n" +
                "      \"device_id\":\"device_idVladTest\",\n" +
                "      \"session_id\":\"VladTest\",\n" +
                "      \"ip\":\"79.110.129.18\",\n" +
                "      \"event_type\":\"APP_A2C\"\n" +
                "   }," +
                "    \"description\": \"3041309906\",\n" +
                "    \"external_id\": \"" + Uuid_helper.generate_uuid() + "\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"card_id\": \"100501\",\n" +
                "        \"value\": \"UA323348510000026208119209027\",\n" + //UA323348510000026208119209027  UA953348510000026201112609803
                "        \"card_id\":\"025342752026\"," +                       //025342752026               019117787643
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"8531524\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"recipient_data\": {\n" +
                "       \"general\": {\n" +
                "           \"first_name\":\"test1\",\n" +
                "           \"tax_id\":\"1029138712\",\n" +
                "           \"birthday\":\"1985-01-02\",\n" +
                "           \"last_name\":\"testt2\"\n" +
                "       }\n" +
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"card_id\": \"100500\",\n" +
                "        \"card_mask\": \"444111******1111\",\n" +
                "        \"phone\": \"3809876543211\",\n" +
                "        \"value\": \"5168745611327906\"\n" + //приват 5168745611327906     приват 4149497548321415
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
    }

    public void positiveTest_ACCOUNT_ID_to_PHONE() throws InterruptedException {
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(6241781));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": 300,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"sender\": {\n" +
                "        \"source\":\"ACCOUNT_ID\",\n" +
                "        \"value\":\"126856282\",\n" + //232786741 232226008-кредит. 126856282-дебет
                "        \"instrument\":\"OWN_CARD\",\n" +
                "        \"card_id\":\"019117788042\"," + //025342752026-кредит. 019117788042-дебет
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"8531524\"\n" +
                "        }\n"+
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"PHONE\",\n" +
                "        \"value\": \"380509402340\"\n" +
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
        Thread.sleep(30000);
        a2C_legion.getStatus(a2C_legion.getTransactionId());
        a2C_legion.getDetails(a2C_legion.getTransactionId(),"8531524");
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
                        "        \"session_id\": \"225e7e87\"," +
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
                "    \"amount\": 707,\n" +
                "    \"service\": \"crossborder_direct\",\n" +
                "    \"currency\": \"USD\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"authentication\": {" +
                "        \"jwt\": \""+a2C_legion.getToken()+"\"," +
                "        \"session_id\": \"225e709ce\"," +
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
                "        \"value\": \"028417738202\",\n" +  //028417738202 usd       031799270259 eur
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

    public void positiveTest_visaAlias(){
        A2C_legion a2C_legion = new A2C_legion(new Trans_token_payhub(7559165));
        a2C_legion.initTransfers("{\n" +
                "   \"external_id\":\"5f22deaf-ba55-4b7d-b1ff-7fd729df8b83\",\n" +
                "   \"service\":\"visa_request_to_pay\",\n" +
                "   \"service_params\":{\n" +
                "      \"visa_request_to_pay_id\":\"FF"+String.valueOf(new Random().nextLong()).substring(1,14)+"TT\"\n" +
                "   },\n" +
                "   \"amount\":125,\n" +
                "   \"date\":\"2026-04-07T14:59:55.193996749\",\n" +
                "   \"currency\":\"UAH\",\n" +
                "   \"fee_amount\":0,\n" +
                "   \"sender\":{\n" +
                "      \"source\":\"ACCOUNT_ID\",\n" +
                "      \"value\":\"195575874\",\n" +
                "      \"instrument\":\"OWN_CARD\",\n" +
                "      \"card_id\":\"035381999017\",\n" +
                "      \"client\":{\n" +
                "         \"id\":\"13322877\",\n" +
                "         \"source\":\"EKB\"\n" +
                "      }\n" +
                "   },\n" +
                "   \"recipient\":{\n" +
                "      \"source\":\"ITM\",\n" +
                "      \"value\":\""+Cards_data.getData(Card.FUIB_VISA, Card_param.token)+"\",\n" +
                "      \"instrument\":\"PAYMENT_CARD\"\n" +
                "   },\n" +
                "   \"recipient_data\":{\n" +
                "      \"general\":{\n" +
                "         \n" +
                "      }\n" +
                "   },\n" +
                "   \"authentication\":{\n" +
                "      \"device_id\":\"61f105c38fe68531c4fd1248\",\n" +
                "      \"session_id\":\"1775563180\",\n" +
                "      \"ip\":\"79.110.129.12\",\n" +
                "      \"event_type\":\"APP_A2C\",\n" +
                "      \"login\":\"0665767084\",\n" +
                "      \"application\":\"IOS\"\n" +
                "   },\n" +
                "   \"lang\":\"UK\"\n" +
                "}");
        a2C_legion.confirmTransfers("{\n" +
                "  \"lang\": \"UK\",\n" +
                "  \"authentication\": {\n" +
                "    \"otp_code\": \"1111\",\n" +
                "    \"jwt\": \""+a2C_legion.getToken()+"\"\n" +
                "  }\n" +
                "}");
    }

    public void positiveTest_moneyBox() {
        A2C_legion a2C_legion = new A2C_legion();
        a2C_legion.setToken(new Trans_token_payhub(6241781));
        a2C_legion.setBodyRequest("{\n" +
                "    \"amount\": 3000000,\n" +
                "    \"fee_amount\": 0,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"authentication\":{\n" +
                "      \"device_id\":\"device_idVladTest\",\n" +
                "      \"session_id\":\"VladTest\",\n" +
                "      \"ip\":\"79.110.129.18\",\n" +
                "      \"event_type\":\"APP_A2T\"\n" +
                "   }," +
                "    \"description\": \"3041309906\",\n" +
                "    \"external_id\": \"" + Uuid_helper.generate_uuid() + "\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA953348510000026201112609803\",\n" +
                "        \"card_id\":\"019117787643\"," +
                "        \"client\": {\n" +
                "            \"source\": \"EKB\",\n" +
                "            \"id\": \"8531524\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"ITM\",\n" +
                "        \"value\": \"?C91RNEEV4CHTEN2\"\n" +
                "    }\n" +
                "}");
        a2C_legion.makeTrans();
    }
}
