package test.backTests.nbu.nbu_21;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.AcquiringTrans;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

@Test
public class NBU_21_pga extends BaseTest {

    AcquiringTrans a2c;

    public void testA2CwithAllRequirementsArr(){
        logStartTest("testA2CwithAllRequirementsArr");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"c949382b-ddd0-4b13-94d3-713dfa3e8cca\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":[{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakк\",\n" +
                "         \"amount\":\"1212\",\n" +
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
        logFinishTest("testA2CwithAllRequirementsArr");
    }

    public void testA2CwithAllRequirements(){
        logStartTest("testA2CwithAllRequirements");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakк\",\n" +
                "         \"amount\":\"1212\",\n" +
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
                "         \"submerchant_url\":\"https://jira.fuib.com/projects/PAYH/issues/PAYH-23149?filter=myopenissues\",\n" +
                "         \"independent_sales_organization_id\":\"3016715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithAllRequirements");
    }

    public void testA2CwithAllRequirementsFAIL(){
        logStartTest("testA2CwithAllRequirementsFAIL");
        String body ="\"amount\": 25000000,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "FAILED");
        logFinishTest("testA2CwithAllRequirementsFAIL");
    }

    public void testA2CwithoutRecipient(){
        logStartTest("testA2CwithoutRecipient");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient");
    }

    public void testA2CwithoutSender(){
        logStartTest("testA2CwithoutSender");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender");
    }

    public void testA2CwithoutDetails(){
        logStartTest("testA2CwithoutDetails");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails");
    }

    public void testA2CwithoutRecipient_first_name(){
        logStartTest("testA2CwithoutRecipient_first_name");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_first_name");
    }

    public void testA2CwithoutRecipient_last_name(){
        logStartTest("testA2CwithoutRecipient_last_name");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_last_name");
    }

    public void testA2CwithoutRecipient_account_number(){
        logStartTest("testA2CwithoutRecipient_account_number");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_account_number");
    }

    public void testA2CwithoutRecipient_reference_number(){
        logStartTest("testA2CwithoutRecipient_reference_number");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_reference_number");
    }

    public void testA2CwithoutSender_first_name(){
        logStartTest("testA2CwithoutSender_first_name");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_first_name");
    }

    public void testA2CwithoutSender_last_name(){
        logStartTest("testA2CwithoutSender_last_name");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_last_name");
    }

    public void testA2CwithoutSender_account_number(){
        logStartTest("testA2CwithoutSender_account_number");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_account_number");
    }

    public void testA2CwithoutDetails_additional_message(){
        logStartTest("testA2CwithoutDetails_additional_message");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_additional_message");
    }

    public void testA2CwithoutDetails_source(){
        logStartTest("testA2CwithoutDetails_source");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_source");
    }

    public void testA2CwithoutDetails_independent_sales_organization_id(){
        logStartTest("testA2CwithoutDetails_independent_sales_organization_id");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_independent_sales_organization_id");
    }

    public void testA2Cregress(){
        logStartTest("testA2Cregress");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2Cregress");
    }

    public void testA2CNegativeSourceInt(){
        logStartTest("testA2CNegativeSourceInt");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":01,\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "FAIL");
        logFinishTest("testA2CNegativeSourceInt");
    }

    public void testA2CNegativeIndependent_sales_organization_idInt(){
        logStartTest("testA2CNegativeIndependent_sales_organization_idInt");
        String body ="\"amount\": 2500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"127.0.0.1\"," +
                "    \"3ds2_supported\": true," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"Ivan5555555555\",\n" +
                "         \"last_name\":\"Ivan55555555555\",\n" +
                "         \"account_number\":\"UA21322313000002600723356600100001\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"Ivan5555555551\",\n" +
                "         \"last_name\":\"Ivan55555555556\",\n" +
                "         \"account_number\":\"UA21322313000002600723356601000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111122222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"01\",\n" +
                "         \"independent_sales_organization_id\":3056715233\n" +
                "      }\n" +
                "      }\n" +
                "      }\n";
        makeA2c(body, "FAIL");
        logFinishTest("testA2CNegativeIndependent_sales_organization_idInt");
    }

    void makeA2c(String body, String result){
        a2c = new AcquiringTrans(body);
        JSONObject json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"CREATED");
    }

    public void getStatus(){
        AcquiringTrans a2c = new AcquiringTrans();
        a2c.status("ef46f750-a591-4b20-956d-50b90e030a40");
    }
}
