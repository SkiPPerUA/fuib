package test.backTests.nbu.nbu_21;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C2A;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.testng.annotations.Test;

@Test
public class NBU_21_c2a extends BaseTest {

    C2A a2c;

    public void testA2CwithAllRequirementsArr(){
        logStartTest("testA2CwithAllRequirementsArr");

        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
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
                "         \"submerchant_url\":\"https://jira.fuib.com/projects/test\",\n" +
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

        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
                "   \"requirements\": " +
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
                "      }\n"+
                "      }\n";

        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithAllRequirements");
    }

    public void testA2CwithAllRequirementsFAIL(){
        logStartTest("testA2CwithAllRequirementsFAIL");
        String body = "\"amount\": 10000000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "FAILED");
        logFinishTest("testA2CwithAllRequirementsFAIL");
    }

    public void testA2CwithoutRecipient(){
        logStartTest("testA2CwithoutRecipient");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient");
    }

    public void testA2CwithoutSender(){
        logStartTest("testA2CwithoutSender");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender");
    }

    public void testA2CwithoutDetails(){
        logStartTest("testA2CwithoutDetails");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails");
    }

    public void testA2CwithoutRecipient_first_name(){
        logStartTest("testA2CwithoutRecipient_first_name");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_first_name");
    }

    public void testA2CwithoutRecipient_last_name(){
        logStartTest("testA2CwithoutRecipient_last_name");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_last_name");
    }

    public void testA2CwithoutRecipient_account_number(){
        logStartTest("testA2CwithoutRecipient_account_number");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_account_number");
    }

    public void testA2CwithoutRecipient_reference_number(){
        logStartTest("testA2CwithoutRecipient_reference_number");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_reference_number");
    }

    public void testA2CwithoutSender_first_name(){
        logStartTest("testA2CwithoutSender_first_name");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_first_name");
    }

    public void testA2CwithoutSender_last_name(){
        logStartTest("testA2CwithoutSender_last_name");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_last_name");
    }

    public void testA2CwithoutSender_account_number(){
        logStartTest("testA2CwithoutSender_account_number");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_account_number");
    }

    public void testA2CwithoutDetails_additional_message(){
        logStartTest("testA2CwithoutDetails_additional_message");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_additional_message");
    }

    public void testA2CwithoutDetails_source(){
        logStartTest("testA2CwithoutDetails_source");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_source");
    }

    public void testA2CwithoutDetails_independent_sales_organization_id(){
        logStartTest("testA2CwithoutDetails_independent_sales_organization_id");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "      }\n" +
                "}\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_independent_sales_organization_id");
    }

    public void testA2Cregress(){
        logStartTest("testA2Cregress");
        String body = " \"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },"+
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    }";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2Cregress");
    }

    public void testA2CNegativeSourceInt(){
        logStartTest("testA2CNegativeSourceInt");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "         \"source\": 01,\n" +
                "         \"independent_sales_organization_id\":\"3056715233\"\n" +
                "      }\n" +
                "      }\n" +
                "}\n";
        makeA2c(body, "FAIL");
        logFinishTest("testA2CNegativeSourceInt");
    }

    public void testA2CNegativeIndependent_sales_organization_idInt(){
        logStartTest("testA2CNegativeIndependent_sales_organization_idInt");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 33,\n" +
                "\"threed\": {\n" +
                "        \"version\": \"2.1.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"accept_header\": \"*\",\n" +
                "        \"lang\": \"RU\",\n" +
                "        \"color_depth\": 24,\n" +
                "        \"screen_width\": 1920,\n" +
                "        \"screen_height\": 1080,\n" +
                "        \"tz\": 120,\n" +
                "        \"challenge_window_size\": \"02\",\n" +
                "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
                "        \"user_agent\": \"Gecko\"\n" +
                "    },    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
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
                "         \"independent_sales_organization_id\": 3056715233\n" +
                "      }\n" +
                "      }\n" +
                "}\n";
        makeA2c(body, "FAIL");
        logFinishTest("testA2CNegativeIndependent_sales_organization_idInt");
    }

    void makeA2c(String body, String result){
        a2c = new C2A(body,true);
    }

    public void getStatus(){
        C2A a2c = new C2A();
        a2c.status("26ff31b1-b086-4550-92e5-292ee8b8135a");
    }
}
