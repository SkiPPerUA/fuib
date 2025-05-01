package test.backTests.ronan;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;

@Test
public class LocalTestRPC extends BaseTest {

    String host = "http://localhost:8020";
    String card = "5375414111595988"; // 4056213118365364 5575191548185686
    int amount = 100;
    int acquirer_id = 103001;
    String body1 = "{\n" +
            "\"acquirer_id\": "+acquirer_id+",\n" +
            "\"amount\": "+amount+",\n" +
            "\"currency\": \"UAH\",\n" +
            "\"custom_fee\": 0,\n" +
            "\"exp_date\": \"2511\",\n" +
            "\"sender_country\": \"UKR\",\n" +
            "\"1sender_state\": \"AB\",\n" +
            "\"sender_city\": \"Kyiv\",\n" +
            "\"sender_address\": \"vul. Marka Vovchka, bud. 26, 3\",\n" +
            "\"sender_postal_code\": \"35604\",\n" +
            "\"sender_first_name\": \"Taras\",\n" +
            "\"sender_last_name\": \"Ivashchuk\",\n" +
            "\"sender_phone\": \"380973396172\",\n" +
            "\"receiver_country\": \"POL\",\n" +
            "\"1receiver_state\": \"AB\",\n" +
            "\"receiver_first_name\": \"Denys\",\n" +
            "\"receiver_last_name\": \"Ishchenko\",\n" +
            "\"receiver_city\": \"Warsaw\",\n" +
            "\"receiver_address\": \"vul. Leipzig, bud. 21, 3\",\n" +
            "\"receiver_postal_code\": \"53406\",\n" +
            "\"receiver_phone_number\": \"380969931627\",\n" +
            "\"sender_document\": \"AD2343232\",\n" +
            "\"sender_document_type\": \"02\",\n" +
            "\"sender_ipn\": \"0123456789\",\n" +
            "\"sender_birthday\": \"01-01-1995\",\n" +
            "\"add_data\": {\n" +
            "\"test_card\": \"4056213118365364\",\n" +
            "\"sender_account_number\": \"UA193348510000026200111677871\",\n" +
            "\"receiver_account_number\": \"UA193348510000026200111677871\",\n" +
            "\"sender_reference_number\": \"0123456789123456\",\n" +
            "\"source\": \"03\",\n" + //03 счет       02 кэш       01 карта
            "\"independent_sales_organization_id\": \"322313\",\n" +
            "\"merchant_url\": \"merchantUrl\",\n" +
            "\"payment_url\": \"paymentUrl\",\n" +
            "\"additional_message\": \"23456787654ertghjhfds\",\n" +
            "\"card_NUMBER_VASI\": \"4056213118365364\"\n" +
            "}\n" +
            "}";

    //DE121 Recipient Account NumberType DE121 Sender Account Number Type - нету

    public void test() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //ur_pumb();
        fiz_pumb();
        //ur_Notpumb();
        //fiz_Notpumb();
    }

    private String getBodyMaster(){
        return "{" +
                "            \"acquirer_id\": 103001," +
                "            \"amount\": "+amount+"," +
                "            \"currency\": \"USD\"," +
                "            \"custom_fee\": 0," +
                "            \"exp_date\": \"1022\"," +
                "            \"sender_country\": \"UKR\"," +
                "            \"1sender_state\": \"AB\"," +
                "            \"sender_city\": \"Kyiv\"," +
                "            \"sender_address\": \"vul. Marka Vovchka, bud. 26, 3\"," +
//                "            \"\\t\\\"sender_postal_code\\\": \\\"35604\\\",\\n\" +\n" +
                "            \"sender_first_name\": \"Taras\"," +
                "            \"sender_last_name\": \"Ivashchuk\"," +
//                "            \"\\t\\\"sender_phone\\\": \\\"380973396172\\\",\\n\" +\n" +
//                "            \"receiver_country\": \"POL\"," +
                "            \"1receiver_state\": \"AB\"," +
                "            \"receiver_first_name\": \"Denys\"," +
                "            \"receiver_last_name\": \"Ishchenko\"," +
//                "            \"\\t\\\"receiver_city\\\": \\\"Warsaw\\\",\\n\" +\n" +
//                "            \"\\t\\\"receiver_address\\\": \\\"vul. Leipzig, bud. 21, 3\\\",\\n\" +\n" +
//                "            \"\\t\\\"receiver_postal_code\\\": \\\"53406\\\",\\n\" +\n" +
//                "            \"\\t\\\"receiver_phone_number\\\": \\\"380969931627\\\",\\n\" +\n" +
                "            \"sender_document\": \"AD2343232\"," +
                "            \"sender_document_type\": \"02\"," +
                "            \"sender_ipn\": \"0123456789\"," +
                "            \"sender_birthday\": \"01-01-1995\"," +
                "            \"add_data\": {" +
//                "            \"\\t\\t\\\"test_card\\\": \\\"5358380880152528\\\",\\n\" +\n" +
                "            \"sender_account_number\": \"UA193348510000026200111677871\"," +
                "            \"receiver_account_number\": \"UA193348510000026200111677871\"," +
                "            \"sender_reference_number\": \"0123456789123456\"," +
                "            \"source\": \"03\" "+
//                "            \"\\t\\t\\\"independent_sales_organization_id\\\": \\\"322313\\\",\\n\" +\n" +
//                "            \"\\t\\t\\\"additional_message\\\": \\\"23456787654ertghjhfds\\\",\\n\" +\n" +
//                "            \"\\t\\t\\\"card_NUMBER_VASI\\\": \\\"4056213118365364\\\"\\n\" +\n" +
                "            }" +
                "            }";
    }

    private String getBodyVisa(){
        return "{\n" +
                "\"acquirer_id\": 103001,\n" +
                "\"amount\": "+amount+",\n" +
                "\"currency\": \"USD\",\n" +
                "\"custom_fee\": 0,\n" +
                "\"exp_date\": \"1022\",\n" +
                "\"sender_country\": \"UKR\",\n" +
                "\"1sender_state\": \"AB\",\n" +
                "\"sender_city\": \"Kyiv\",\n" +
                "\"sender_address\": \"vul. Marka Vovchka, bud. 26, 3\",\n" +
//                "\"sender_postal_code\": \"35604\",\n" +
                "\"sender_first_name\": \"Taras\",\n" +
                "\"sender_last_name\": \"Ivashchuk\",\n" +
//                "\"sender_phone\": \"380973396172\",\n" +
                "\"receiver_country\": \"POL\",\n" +
                "\"1receiver_state\": \"AB\",\n" +
                "\"receiver_first_name\": \"Denys\",\n" +
                "\"receiver_last_name\": \"Ishchenko\",\n" +
                "\"receiver_city\": \"Warsaw\",\n" +
                "\"receiver_address\": \"vul. Leipzig, bud. 21, 3\",\n" +
                "\"receiver_postal_code\": \"53406\",\n" +
//                "\"receiver_phone_number\": \"380969931627\",\n" +
                "\"sender_document\": \"AD2343232\",\n" +
                "\"sender_document_type\": \"02\",\n" +
                "\"sender_ipn\": \"0123456789\",\n" +
                "\"sender_birthday\": \"01-01-1995\",\n" +
                "\"add_data\": {\n" +
//                "\"test_card\": \"5358380880152528\",\n" +
                "\"sender_account_number\": \"UA193348510000026200111677871\",\n" +
//                "\"receiver_account_number\": \"UA193348510000026200111677871\",\n" +
                "\"sender_reference_number\": \"0123456789123456\",\n" +
                "\"source\": \"03\"\n" +
//                "\"independent_sales_organization_id\": \"322313\",\n" +
//                "\"additional_message\": \"23456787654ertghjhfds\",\n" +
//                "\"card_NUMBER_VASI\": \"4056213118365364\"\n" +
                "}\n" +
                "}";
    }

    private void makeScript() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            BDpostgre.updateSQL("UPDATE tb_sir_partner_rests SET rests_confirmed = 99999999999 WHERE sir_acc_id = '114622276';");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void ur_pumb() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.print("ЮР - ПУМБ ");
        BDpostgre.updateSQL("update tb_itm_vmtconnvl set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDpostgre.updateSQL("update tb_itm_vmtconnvl set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        makeTrn();
    }

    private void fiz_pumb() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.print("ФИЗ - ПУМБ ");
        BDpostgre.updateSQL("update tb_itm_vmtconnvl set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDpostgre.updateSQL("update tb_itm_vmtconnvl set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        makeTrn();
    }

    private void ur_Notpumb() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.print("ЮР - Парт ");
        BDpostgre.updateSQL("update tb_itm_vmtconnvl set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDpostgre.updateSQL("update tb_itm_vmtconnvl set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        makeTrn();
    }

    private void fiz_Notpumb() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.print("ФИЗ - Парт ");
        BDpostgre.updateSQL("update tb_itm_vmtconnvl set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDpostgre.updateSQL("update tb_itm_vmtconnvl set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        makeTrn();
    }

    private void makeTrn() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

//        String body1 = "";
//        if (Integer.parseInt(String.valueOf(Cards_data.getData(card,Card_param.pan).toCharArray()[0])) == 5){
//            body1 = getBodyMaster();
//        }else {
//            body1 = getBodyVisa();
//        }
        logger.info(body1);
        String token = given()
                .when()
                .get(host+"/token")
                .then()
                .extract().response().asString();
        logger.info("Token = "+token);

        String responce = given()
                .contentType(ContentType.JSON)
                .header("token",token)
                .body(body1)
                .when()
                .post(host+"/registration2/"+card+"?amount="+amount)
                .then()
                .extract().response().asString();
        logger.info("Responce registration2 = "+responce);
        StringBuffer stringBuffer = new StringBuffer((responce));
        String sessionId = stringBuffer.substring(stringBuffer.lastIndexOf("Id=")+3,stringBuffer.lastIndexOf("Id=")+15);
        System.out.println("SessionId = "+sessionId);
//
//        makeScript();
//
//        String st = given()
//                .header("token",token)
//                .when()
//                .get(host+"/payment/"+sessionId)
//                .then()
//                .extract().response().asString();
//
//        logger.info("Response payment = "+st);
//        Assert.assertEquals(new JSONObject(st).getString("error_code"),"00");


    }

    @BeforeTest
    void open(){
        try {
            BDpostgre.BDpostgre("dc3-bgpg-001-vs.test-fuib.com:5432/","kreedb","svc_kree","passw0rd");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    void close(){
        try {
            BDpostgre.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
