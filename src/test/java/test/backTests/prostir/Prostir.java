package test.backTests.prostir;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.Payer_constructor;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C2A;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.SQLException;

public class Prostir extends BaseTest {

    int acquirer_id = 2204;
    String bd_table = "itm22d.vmtconnvl";

    @Test
    public void test() throws SQLException {
//        ur_Notpumb();
//        ur_pumb();
//        fiz_Notpumb();
        fiz_pumb();
    }


    private void ur_pumb() throws SQLException {
        System.out.print("Юр - пумб -> ");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        tran();
    }

    private void fiz_pumb() throws SQLException {
        System.out.print("Физ - пумб -> ");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        tran();
    }

    private void ur_Notpumb() throws SQLException {
        System.out.print("Юр - партнер -> ");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        tran();
    }

    private void fiz_Notpumb() throws SQLException {
        System.out.print("Физ - партнер -> ");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        tran();
    }

    private void tran(){
        String body = " \"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"client_ip\": \"0.1.1.4\",\n" +
                "    \"description\": \"c2a - пумб мастер\",\n" +
                "    \"threed\": {\n" +
                "        \"version\": \"2.2.0\",\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"java_enabled\": false,\n" +
                "        \"javascript_enabled\": true,\n" +
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
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA953348510000026201112609803\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "   \"requirements\": " +
                "     {\n" +
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
                "      }},\n"+
                Payer_constructor.PAN_payer(Cards_data.getData(Card.TEST_CARD));

        C2A trans = new C2A(body,0);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        trans.status();
        JSONObject json = new JSONObject(trans.getResponse()).getJSONObject("data");
        Assert.assertEquals(json.getString("status"),"PROCESSED");


//        A2C_legion trans = new A2C_legion();
//        trans.setToken(new Trans_token_payhub(6241781));
//        trans.setBodyRequest("{\n" +
//                "    \"amount\": 100,\n" +
//                "    \"commission\": 10,\n" +
//                "    \"currency\": \"UAH\",\n" +
//                "    \"description\": \"test1\",\n" +
//                "    \"external_id\": \"" + Uuid_helper.generate_uuid() + "\",\n" +
//                "    \"sender\": {\n" +
//                "        \"source\": \"IBAN\",\n" +
//                "        \"value\": \"UA953348510000026201112609803\",\n" +
//                "        \"client\": {\n" +
//                "            \"source\": \"EKB\",\n" +
//                "            \"id\": \"8531524\"\n" +
//                "        }\n" +
//                "    },\n" +
//                "    \"recipient\": {\n" +
//                "        \"source\": \"PAN\",\n" +
//                "        \"value\": \""+Cards_data.getData(Card.TEST_CARD,Card_param.pan)+"\"\n" +
//                "    }\n" +
//                "}");
//        trans.makeTrans();
//
//
//        String body = "\"amount\": 100,\n" +
//                "    \"commission\": 0,\n" +
//                "    \"client_ip\": \"0.1.1.4\",\n" +
//                "    \"currency\": 980,\n" +
//                "    \"description\": \"description\",\n" +
//                Payer_constructor.PAN_payer(Cards_data.getData(Card.PROSTIR))+","+
//                "    \"receiver\": {\n" +
//                "        \"source\": \"PAN\",\n" +
//                "        \"value\": \""+Cards_data.getData(Card.PROSTIR,Card_param.pan)+"\"\n" +
//                "    },\n" +
//                ThreeDS.threeDS_2_2_0;
//
//        C2C trans = new C2C(body, 0);
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        trans.status();
//        JSONObject json = new JSONObject(trans.getResponse()).getJSONObject("data");
//        Assert.assertEquals(json.getString("status"),"PROCESSED");

        System.out.println(trans.getTransactionId());
    }

    @BeforeTest
    void open() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDas400.BDas400("ITMTST", "savchukv", "skipper11223");
    }

    @AfterTest
    void close(){
        try {
            BDas400.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

