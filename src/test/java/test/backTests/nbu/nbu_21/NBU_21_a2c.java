package test.backTests.nbu.nbu_21;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class NBU_21_a2c extends BaseTest {

    A2C a2c;

    public void testA2CwithAllRequirementsArr(){
        String body =
                "   \"amount\":1000,\n" +
                "   \"currency\":980,\n" +
                "   \"description\": \"description\",\n" +
                "   \"receiver\":{\n" +
                "      \"source\":\"PAN\",\n" +
                "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
//                        "      \"source\":\"ITM\",\n" +
//                        "      \"value\":\"?C8RL8XT8A5FV6J7\"\n" +
                "   },\n" +
                        "    \"identification\": {\n" +
//                        "       \"general\": {" +
//                        "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
//                        "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
//                        "               \"tax_id\": \"1234567890\"\n" +
//                        "               },"+
                        "   \"requirements\": {\n" +
                        "      \"recipient\":{\n" +
                        "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                        "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                        "         \"amount\":\"1213\",\n" +
                        "         \"account_number\":\"4441111234562806\"\n" +
                        "      },\n" +
                        "      \"sender\":{\n" +
                        "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                        "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                        "         \"account_number\":\"UA953348510000026201112609803\",\n" +
                        "         \"city\":\"City\",\n" +
                        "         \"postal_code\":\"423214\",\n" +
                        "         \"address\":\"address\",\n" +
                        "         \"country\":\"Country\",\n" +
                        "         \"tax_id\":\"3032411164\",\n" +
                        "         \"document_number\":\"111111\",\n" +
                        "         \"document_type\":\"01\",\n" +
                        "         \"birthday\":\"10-10-1992\",\n" +
                        "         \"reference_number\":\"1111111111111111\"\n" +
                        "      },\n" +
                        "      \"details\":{\n" +
                        "         \"additional_message\":\"11111111221222222233333333334444444444555555555666666666677777111\",\n" +
                        "         \"source\":\"03\",\n" +
                        "         \"independent_sales_organization_id\":\"3016715233\",\n" +
                        "         \"submerchant_url\": \"https://merchant111.com?com=12345\",\n" +
                        "         \"payment_url\": \"https://payment111.com?com=12345\"\n" +
                        "      }\n" +
                        "      }\n"+
                        "      }\n";
//        String body =   "   \"amount\":520,\n" +
//                        "   \"currency\":\"980\",\n" +
//                        "   \"description\": \"description\",\n" +
//                        "   \"receiver\":{\n" +
//                        "      \"source\":\"PAN\",\n" +
//                        "      \"value\":\""+Cards_data.getData(Card.FUIB_MC,Card_param.pan)+"\"\n" +
//                        "   },\n" +
//                        "   \"identification\":{\n" +
//                        "      \"general\":{\n" +
//                        "         \"first_name\":\"ВЛАДИСЛАВ\",\n" +
//                        "         \"last_name\":\"ВЛАСЕНКО\",\n" +
//                        "         \"middle_name\":\"РУСЛАНОВИЧ\",\n" +
//                        "         \"tax_id\":\"3453109435\",\n" +
//                        "         \"birthday\":\"2023-12-01\"\n" +
//                        "      },\n" +
//                        "      \"address\":{\n" +
//                        "         \"street\":\"СЕНТЯБРЬСКАЯ\",\n" +
//                        "         \"locality\":\"\"\n" +
//                        "      },\n" +
//                        "      \"requirements\":{\n" +
//                        "         \"details\":{\n" +
//                        "            \"source\":\"02\",\n" +
//                        "            \"additional_message\":\"UA,UA,TS212415,місто Київ,вулиця Андрія Малишка,3А\"\n" +
//                        "         }\n" +
//                        "      }\n" +
//                        "   },\n" +
//                        "   \"action\":\"a2cTransaction\"\n";
        makeA2c(body, "PROCESSED");
    }

    public void testA2CwithAllRequirements(){
        logStartTest("testA2CwithAllRequirements");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"description\": \"description\",\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
                        "    \"identification\": {\n" +
                        "   \"requirements\":{\n" +
                        "      \"recipient\":{\n" +
                        "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                        "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakк\"\n" +
                        //"         \"amount\":\"1212\",\n" +
                        //"         \"account_number\":\"UA21322313000006007233566001000011\"\n" +
                        "      },\n" +
                        "      \"sender\":{\n" +
                        "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                        "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                        "         \"account_number\":\"UA953348510000026201112609803\",\n" +
                        "         \"reference_number\":\"1111111111111111\"\n" +
                        "      },\n" +
                        "      \"details\":{\n" +
                        "         \"additional_message\":\"11111111221222222233333333334444444444555555555666666666677777111\",\n" +
                        "         \"source\":\"07\",\n" +
                        "         \"submerchant_url\":\"https://jira.fuib.com/projects/PAYH/issues/PAYH-23149?filter=myopenissues\",\n" +
                        "         \"independent_sales_organization_id\":\"3016715233\"\n" +
                        "      }\n" +
                        "      }\n"+
                        "      }\n";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithAllRequirements");
    }

    public void testA2CwithAllRequirementsFAIL(){
        logStartTest("testA2CwithAllRequirementsFAIL");
        String body =
                "   \"amount\":100000000,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "FAILED");
        logFinishTest("testA2CwithAllRequirementsFAIL");
    }

    public void testA2CwithoutRecipient(){
        logStartTest("testA2CwithoutRecipient");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient");
    }

    public void testA2CwithoutSender(){
        logStartTest("testA2CwithoutSender");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender");
    }

    public void testA2CwithoutDetails(){
        logStartTest("testA2CwithoutDetails");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
                        "    \"identification\": {\n" +
                        "   \"requirements\":{\n" +
                        "      \"recipient\":{\n" +
                        "         \"first_name\":\"Ivan\",\n" +
                        "         \"last_name\":\"Ivanov\",\n" +
                        "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                        "         \"reference_number\":\"111111\",\n" +
                        "         \"merchant_url\": \"https://merchant111.com?com=12345\",\n" +
                        "         \"payment_url\": \"https://payment111.com?com=12345\"\n" +
                        "      },\n" +
                        "      \"sender\":{\n" +
                        "         \"first_name\":\"Ivan\",\n" +
                        "         \"last_name\":\"Ivanov\",\n" +
                        "         \"account_number\":\"UA213223130000026007233566001\",\n" +
                        "        \"merchant_url\": \"https://merchant111.com?com=12345\",\n" +
                        "        \"payment_url\": \"https://payment111.com?com=12345\"\n" +
                        "      }\n" +
                        "      }\n" +
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails");
    }

    public void testA2CwithoutRecipient_first_name(){
        logStartTest("testA2CwithoutRecipient_first_name");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_first_name");
    }

    public void testA2CwithoutRecipient_last_name(){
        logStartTest("testA2CwithoutRecipient_last_name");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_last_name");
    }

    public void testA2CwithoutRecipient_account_number(){
        logStartTest("testA2CwithoutRecipient_account_number");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_account_number");
    }

    public void testA2CwithoutRecipient_reference_number(){
        logStartTest("testA2CwithoutRecipient_reference_number");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutRecipient_reference_number");
    }

    public void testA2CwithoutSender_first_name(){
        logStartTest("testA2CwithoutSender_first_name");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_first_name");
    }

    public void testA2CwithoutSender_last_name(){
        logStartTest("testA2CwithoutSender_last_name");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_last_name");
    }

    public void testA2CwithoutSender_account_number(){
        logStartTest("testA2CwithoutSender_account_number");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutSender_account_number");
    }

    public void testA2CwithoutDetails_additional_message(){
        logStartTest("testA2CwithoutDetails_additional_message");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_additional_message");
    }

    public void testA2CwithoutDetails_source(){
        logStartTest("testA2CwithoutDetails_source");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_source");
    }

    public void testA2CwithoutDetails_independent_sales_organization_id(){
        logStartTest("testA2CwithoutDetails_independent_sales_organization_id");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2CwithoutDetails_independent_sales_organization_id");
    }

    public void testA2Cregress(){
        logStartTest("testA2Cregress");
        String body = "\"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                "    }";
        makeA2c(body, "PROCESSED");
        logFinishTest("testA2Cregress");
    }

    public void testA2CNegativeSourceInt(){
        logStartTest("testA2CNegativeSourceInt");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "FAIL");
        logFinishTest("testA2CNegativeSourceInt");
    }

    public void testA2CNegativeIndependent_sales_organization_idInt(){
        logStartTest("testA2CNegativeIndependent_sales_organization_idInt");
        String body =
                "   \"amount\":100,\n" +
                        "   \"currency\":980,\n" +
                        "   \"receiver\":{\n" +
                        "      \"source\":\"PAN\",\n" +
                        "      \"value\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\"\n" +
                        "   },\n" +
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
                        "}";
        makeA2c(body, "FAIL");
        logFinishTest("testA2CNegativeIndependent_sales_organization_idInt");
    }

    void makeA2c(String body, String result){
        a2c = new A2C(body);
        JSONObject json = new JSONObject(a2c.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"CREATED");
    }

    @Test
    void getStatus(){
        A2C a2c = new A2C();
        a2c.status("5b21775a-59dc-4c15-82f7-d7870e78ed3a");
    }
}
