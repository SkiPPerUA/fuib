package test.backTests.nbu.nbu_14;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.Limits;
import org.example.qaTransactionTeam.backEnd.transaction.Payer_constructor;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C2A;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Test
public class NBU_14_c2a extends BaseTest {

    Limits limits = new Limits();
    Card test_card = Card.FUIB_MC;
    Transaction c2a;

    public void testNotFullData_one_trans_negative() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        fail_c2a_short(2500000);
    }

    public void test_one_trans_positive_from_fuib() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_full_without_sender(2500000);
    }

    public void test_two_trans_positive_from_fuib() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_full_without_sender(2000000);
        set_status("PROCESSED");
        success_c2a_full_without_sender(2500000);
    }

    public void testNotFullData_one_trans_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_short(2000000);
    }

    public void testNotFullData_status_fail_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_short(2000000);
        set_status("FAILED");
        success_c2a_short(2000000);
    }

    public void testFullData_status_fail_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //delete_trans(test_card);
        success_c2a_full(100);
//        set_status("FAILED");
//        success_c2a_full(2500000);
    }

    public void testNotFullData_status_PROCESSING_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_short(2000000);
        set_status("PROCESSING");
        fail_c2a_short(2000000);
    }

    public void testFullData_status_PROCESSING_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_full(2500000);
        set_status("PROCESSING");
        success_c2a_full(2500000);
    }

    public void testNotFullData_two_trans_nagative() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_short(2000000);
        set_status("PROCESSED");
        fail_c2a_short(100);
    }

    public void testFullData_one_trans_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_full(2500000);
    }

    public void testFullData_one_trans_positive_lessSumm() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_full(200000);
    }

    public void testFullData_two_trans_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_full(2500000);
        set_status("PROCESSED");
        success_c2a_full(2500000);
    }

    public void testOneNotFullData_SecondFullDate_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_short(2000000);
        set_status("PROCESSED");
        success_c2a_full(2500000);
    }

    public void testOneFullData_SecondNotFullDate_negative() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_full(2000000);
        set_status("PROCESSED");
        fail_c2a_short(100);
    }

    public void testDate_short_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_short(2000000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data1.getData(test_card, Card_param.token)+"') order by created_at asc limit 1)");
        success_c2a_short(2000000);
    }

    public void testDate_short_full_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_short(2000000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data1.getData(test_card, Card_param.token)+"') order by created_at asc limit 1)");
        success_c2a_full(2500000);
    }

    public void testDate_full_short_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_full(2500000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data1.getData(test_card, Card_param.token)+"') order by created_at asc limit 1)");
        success_c2a_short(2000000);
    }

    public void testDate_full_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans(test_card);
        success_c2a_full(2500000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data1.getData(test_card, Card_param.token)+"') order by created_at asc limit 1)");
        success_c2a_full(2500000);
    }

    public void testCheck_different_sender() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        testNotFullData_one_trans_positive();
        Card card_different = Card.MONO_MC;
        delete_trans(card_different);
        String body = " \"amount\": 2000000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"transaction - пумб мастер\",\n" +
                ThreeDS.threeDS_2_1_0+ ","+
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              }}},\n" +
                Payer_constructor.PAN_payer(Cards_data1.getData(card_different),"EXTERNAL","test");

        c2a = new C2A(body,true);
    }

    public void testVariables_params_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String body;
        logStartTest("address -> locality + street");
        delete_trans(test_card);
        body = " \"amount\": 2500000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"transaction - пумб мастер\",\n" +
                ThreeDS.threeDS_2_1_0+ ","+
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\"\n" +
                "              }\n" +
                "        },"+
                "       \"address\": {" +
                "               \"locality\": \"11111\",\n" +
                "               \"street\": \"22222\"\n" +
                "               }"+
                "    },"+
                Payer_constructor.PAN_payer(Cards_data1.getData(test_card),"EXTERNAL","test");
        c2a = new C2A(body, true);

        logStartTest("identification -> series + number");
        delete_trans(test_card);
        body = " \"amount\": 2500000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"transaction - пумб мастер\",\n" +
                ThreeDS.threeDS_2_1_0+ ","+
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\"\n" +
                "              }\n" +
                "        },"+
                "       \"identification\": {" +
                "               \"series\": \"11111\",\n" +
                "               \"number\": \"22222\"\n" +
                "               }"+
                "    },"+
                Payer_constructor.PAN_payer(Cards_data1.getData(test_card),"EXTERNAL","test");
        c2a = new C2A(body, true);

        logStartTest("general -> tax_id");
        delete_trans(test_card);
        body = " \"amount\": 2500000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"transaction - пумб мастер\",\n" +
                ThreeDS.threeDS_2_1_0+ ","+
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\"\n" +
                "              }\n" +
                "        },"+
                "       \"general\": {" +
                "               \"tax_id\": \"1234567890\"\n" +
                "               }"+
                "    },"+
                Payer_constructor.PAN_payer(Cards_data1.getData(test_card),"EXTERNAL","test");
        c2a = new C2A(body, true);

        logStartTest("general -> birthday + birth_country + locality_type + birth_locality");
        delete_trans(test_card);
        body = " \"amount\": 2500000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"transaction - пумб мастер\",\n" +
                ThreeDS.threeDS_2_1_0+ ","+
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\"\n" +
                "              }\n" +
                "        },"+
                "       \"general\": {" +
                "               \"birthday\": \"2011-11-11\",\n" +
                "               \"birth_country\": \"UKR\",\n" +
                "               \"locality_type\": 3,\n" +
                "               \"birth_locality\": \"4444\"\n" +
                "               }"+
                "    },"+
                Payer_constructor.PAN_payer(Cards_data1.getData(test_card),"EXTERNAL","test");
        c2a = new C2A(body, true);
    }

    @BeforeTest
    void start() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //BDpostgre.BDpostgre("limiter", "dev","password");
        //createLimits("PROFIT","EXTERNAL_BANK", 3000000,5000000,5,10000000,10);
        //createLimits("EXPENSE","EXTERNAL_BANK", 3000000,5000000,5,10000000,10);
    }

    @AfterTest
    void end() throws SQLException {
        BDpostgre.closeConn();
    }

    void createLimits(String direction, String kind, int transaction_amount, int daily_amount, int daily_quantity, int monthly_amount, int monthly_quantity){
        String body = "{\n" +
                "  \"direction\": \""+direction+"\",\n" +
                "  \"kind\": \""+kind+"\",\n" +
                "  \"transaction_amount\": "+transaction_amount+",\n" +
                "  \"daily_amount\": "+daily_amount+",\n" +
                "  \"daily_quantity\": "+daily_quantity+",\n" +
                "  \"monthly_amount\": "+monthly_amount+",\n" +
                "  \"monthly_quantity\": "+monthly_quantity+"\n" +
                "}";
        limits.createLimits(body);
    }

    void success_c2a_short(int summ){
        String body = " \"amount\": "+summ+",\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"transaction - пумб мастер\",\n" +
                ThreeDS.threeDS_2_1_0+ ","+
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              }}},\n" +
                Payer_constructor.PAN_payer(Cards_data1.getData(test_card),"EXTERNAL","test");

        c2a = new C2A(body,true);
    }

    void success_c2a_full_without_sender(int summ){
        String body = " \"amount\": "+summ+",\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"transaction - пумб мастер\",\n" +
                ThreeDS.threeDS_2_1_0+ ","+
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              }\n" +
                "        }"+
                "    },"+
                Payer_constructor.PAN_payer(Cards_data1.getData(test_card),"EXTERNAL","test");

        c2a = new C2A(body,true);
    }

    void success_c2a_full(int summ){
        String body = " \"amount\": "+summ+",\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"transaction - пумб мастер\",\n" +
                ThreeDS.threeDS_2_1_0+ ","+
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111221222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"07\",\n" +
                "         \"submerchant_url\":\"https://jira.fuib.com/projects/test\",\n" +
                "         \"independent_sales_organization_id\":\"3016715233\"\n" +
                "      },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\"\n" +
                "              }\n" +
                "        },"+
                "       \"address\": {" +
                "               \"locality\": \"11111\",\n" +
                "               \"street\": \"22222\"\n" +
                "               }"+
                "    },"+
                Payer_constructor.PAN_payer(Cards_data1.getData(test_card),"EXTERNAL","test");

        c2a = new C2A(body,0);
    }

    void fail_c2a_short(int summ){
        String body = " \"amount\": "+summ+",\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"transaction - пумб мастер\",\n" +
                ThreeDS.threeDS_2_1_0+ ","+
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA213223130000026007233566001\"\n" +
                "    }," +
                Payer_constructor.PAN_payer(Cards_data1.getData(test_card),"EXTERNAL","test");
        c2a = new C2A();
        c2a.setExpectedStatus(400);
        c2a.setBodyRequest(body);
        c2a.setThreeDS(0);
        c2a.makeTrans();
    }

    String change_days(int day){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
        Date date = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        java.util.Date currentDatePlusOne = c.getTime();
        return formater.format(currentDatePlusOne);
    }

    void set_status(String status) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //Установке статуса успех всем транкам по нашей карте
        BDpostgre.updateSQL("update limits.entries x set status='"+status+"' where x.card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data1.getData(test_card, Card_param.token)+"')");
    }

    void delete_trans(Card card) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDpostgre.updateSQL("delete FROM limits.entries x where x.card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data1.getData(card, Card_param.token)+"')");
    }
}
