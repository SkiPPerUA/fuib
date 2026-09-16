package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C2A_legion;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class C2A_legion_regress extends BaseTest {

    Card card = Card.MONO_VISA ;

    public void c2a_legion() throws InterruptedException {
        C2A_legion c2A_legion = new C2A_legion();
        c2A_legion.setToken(new Trans_token_payhub(6241781));
        c2A_legion.setBodyRequest("{\n" +
                "    \"service\": \"c2a_domestic\",\n" +
                "    \"amount\": 123,\n" +
                "    \"fee_amount\": 10,\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"purpose\": \"some purpose\",\n" +
                "    \"sender\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(card,Card_param.pan)+"\",\n" +
                "        \"expire_date\": \""+Cards_data.getData(card,Card_param.expire)+"\",\n" +
                "        \"cvv\": \""+Cards_data.getData(card,Card_param.cvv)+"\",\n" +
                "        \"client\":{"+
                "           \"source\":\"EKB\","+
                "           \"id\":\"8531524\""+
                "         }"+
                "    },\n" +
                "    \"recipient\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA023348510000026208117953223\"\n" +
                "    },\n" +
                "    \"authentication\": {\n" +
                "        \"device_id\": \"test\",\n" +
                "        \"session_id\": \"test\",\n" +
                "        \"ip\": \"192.168.0.1\",\n" +
                "        \"event_type\": \"APP_A2P\"\n" +
                "    },\n" +
                "        \"sender_data\": {\n" +
                "        \"general\": {\n" +
                "            \"first_name\": \"test\",\n" +
                "            \"last_name\": \"test\",\n" +
                "            \"tax_id\": \"0000000000\"\n" +
                "        },\n" +
                "        \"identification\": {\n" +
                "            \"series\": \"AA\",\n" +
                "            \"number\": \"11222333\",\n" +
                "            \"name\": \"passport\"\n" +
                "        },\n" +
                "        \"details\": {\n" +
                "            \"additional_message\": \"test\",\n" +
                "            \"submerchant_url\": \"https://test.fuib.com\",\n" +
                "            \"independent_sales_organization_id\": \"123456\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"recipient_data\": {\n" +
                "        \"general\": {\n" +
//                "         \"first_name\": \"test\",\n" +
//                "            \"last_name\": \"test\",\n" +
//                "            \"tax_id\": \"0000000000\",\n" +
//                "            \"birth_data\": \"2000-10-10\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"amount\":\"1213\",\n" +
                "         \"account_number\":\"UA953348510000026201112609803\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"account_number\":\"UA953348510000026201112609803\",\n" +
                "         \"city\":\"Kyiv\",\n" +
                "         \"country\":\"UKR\",\n" +
                "         \"address\":\"street\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111221222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"07\",\n" +
                "         \"submerchant_url\":\"https://jira.fuib.com/projects/PAYH/issues/PAYH-23149?filter=myopenissues\",\n" +
                "         \"independent_sales_organization_id\":\"3016715233\"\n" +
                "      }\n" +
                "        }\n" +
                "    },\n" +
                ThreeDS.threeDS_2_2_0 +
                "}");
        c2A_legion.makeTrans();
        c2A_legion.status();
        Assert.assertEquals(new JSONObject(c2A_legion.getResponse()).getString("status"),"PROCESSED");
    }

}
