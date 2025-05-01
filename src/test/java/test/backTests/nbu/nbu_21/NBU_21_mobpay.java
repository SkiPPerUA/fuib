package test.backTests.nbu.nbu_21;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.mobyPay.MobyTrans;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public class NBU_21_mobpay extends BaseTest {

    public void testMobPay(){
        String body = "\n" +
                "  \"amount\": 1107,\n" +
                "  \"client_ip\": \"127.0.0.1\",\n" +
                "  \"without_confirmation\": false,\n" +
                "  \"merchant_config_id\": \"cab290f8-5460-4cbd-a0a8-2799bdce8fed\",\n" +
                "\t\"hold\": false,\n" +
                "\"threed\": {\n" +
                "              \"channel\": \"BRW\",\n" +
                "              \"without_3ds\": false,\n" +
                "              \"ip\": \"192.168.0.2\",\n" +
                "              \"version\": \"2.1.0\",\n" +
                "              \"fingerprint\": \"test\",\n" +
                "              \"java_enabled\": false,\n" +
                "              \"accept_header\": \"*\",\n" +
                "              \"language\": \"RU\",\n" +
                "              \"color_depth\": 32,\n" +
                "              \"screen_width\": 1920,\n" +
                "              \"screen_height\": 1080,\n" +
                "              \"time_zone\": 120,\n" +
                "              \"challenge_window_size\": \"02\",\n" +
                "              \"return_url\": \"https://service.fuib.com\",\n" +
                "              \"user_agent\": \"Gecko\"\n" +
                "  },\n" +
                "  \"payer\": {\n" +
                "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "              \"expire\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.expire)+"\"  },\n" +
                "    \"identification\": {\n" +
                "   \"requirements\":[{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"СК\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakк\",\n" +
                //"         \"amount\":\"1212\",\n" +
                "         \"account_number\":\"UA21322313000006007233566001000011\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"account_number\":\"UA21323130000026007233566011000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111221222222233333333334444444444555555555666666666677777111\",\n" +
                //"         \"source\":\"07\",\n" +
                "         \"submerchant_url\":\"https://jira.fuib.com/projects/PAYH/issues/PAYH-23149?filter=myopenissues\",\n" +
                "         \"independent_sales_organization_id\":\"3016715233\"\n" +
                "      }\n" +
                "      },\n" +
                "     {\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"amount\":\"1213\",\n" +
                "         \"account_number\":\"UA21322313000006007233566001000011\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"account_number\":\"UA21323130000026007233566011000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111221222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"07\",\n" +
                "         \"independent_sales_organization_id\":\"3016715233\"\n" +
                "      }\n" +
                "      }]\n"+
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithAllRequirements");
    }

    public void testtestMobPayWithAllRequirementsFAIL(){
        logStartTest("testtestMobPayWithAllRequirementsFAIL");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "FAILED");
        logFinishTest("testA2CwithAllRequirementsFAIL");
    }

    public void testA2CwithoutRecipient(){
        logStartTest("testA2CwithoutRecipient");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient");
    }

    public void testA2CwithoutSender(){
        logStartTest("testA2CwithoutSender");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender");
    }

    public void testA2CwithoutDetails(){
        logStartTest("testA2CwithoutDetails");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails");
    }

    public void testA2CwithoutRecipient_first_name(){
        logStartTest("testA2CwithoutRecipient_first_name");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_first_name");
    }

    public void testA2CwithoutRecipient_last_name(){
        logStartTest("testA2CwithoutRecipient_last_name");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_last_name");
    }

    public void testA2CwithoutRecipient_account_number(){
        logStartTest("testA2CwithoutRecipient_account_number");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_account_number");
    }

    public void testA2CwithoutRecipient_reference_number(){
        logStartTest("testA2CwithoutRecipient_reference_number");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_reference_number");
    }

    public void testA2CwithoutSender_first_name(){
        logStartTest("testA2CwithoutSender_first_name");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_first_name");
    }

    public void testA2CwithoutSender_last_name(){
        logStartTest("testA2CwithoutSender_last_name");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_last_name");
    }

    public void testA2CwithoutSender_account_number(){
        logStartTest("testA2CwithoutSender_account_number");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_account_number");
    }

    public void testA2CwithoutDetails_additional_message(){
        logStartTest("testA2CwithoutDetails_additional_message");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_additional_message");
    }

    public void testA2Cregress() throws IOException {
        logStartTest("testA2Cregress");
        String payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data1.getData(Card.MONO_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2409\"";
        MobyTrans trans = new MobyTrans("1107",payer,true,2);
        trans.status();
        trans.complete_hold("1000");
        trans.status();

        logFinishTest("testA2Cregress");
    }

    public void testA2CwithoutDetails_source(){
        logStartTest("testA2CwithoutDetails_source");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_source");
    }

    public void testA2CwithoutDetails_independent_sales_organization_id(){
        logStartTest("testA2CwithoutDetails_independent_sales_organization_id");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_independent_sales_organization_id");
    }

    public void testA2CNegativeSourceInt(){
        logStartTest("testA2CNegativeSourceInt");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":01,\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "FAIL");
        logFinishTest("testA2CNegativeSourceInt");
    }

    public void testA2CNegativeIndependent_sales_organization_idInt(){
        logStartTest("testA2CNegativeIndependent_sales_organization_idInt");
        String body ="    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                "         \"reference_number\":\"111111\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan\",\n" +
                "         \"last_name\":\"Ivanov\",\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"Lorem ipsum dolor sit amet\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":3056715233\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "FAIL");
        logFinishTest("testA2CNegativeIndependent_sales_organization_idInt");
    }

    void makeA2c(String ind, String res){
        MobyTrans trans = null;
        try {
            trans = new MobyTrans(ind,2);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        trans.status();
//        trans.complete_hold("1000");
//        trans.status();
    }
}
