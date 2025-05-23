package test.backTests.nbu.nbu_14;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.Limits;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Test
public class NBU_14_a2c extends BaseTest {

    //Тест связаности переводов по сумме

    Limits limits = new Limits();
    Card card = Card.FUIB_MC;
    A2C a2c;

    public void testNotFullData_one_trans_negative() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        fail_a2c_short(2500000);
    }

    public void test_one_trans_positive_to_fuib() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full_without_recipient(2500000);
    }

    public void test_two_trans_positive_to_fuib() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full_without_recipient(2000000);
        set_status("PROCESSED");
        success_a2c_full_without_recipient(2500000);
    }

    public void testNotFullData_one_trans_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_short(2000000);
    }

    public void testNotFullData_two_trans_nagative() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_short(2000000);
        set_status("PROCESSED");
        fail_a2c_short(100);
    }

    public void testNotFullData_status_fail_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_short(2000000);
        set_status("FAILED");
        success_a2c_short(2000000);
    }

    public void testFullData_status_fail_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full(2500000);
        set_status("FAILED");
        success_a2c_full(2500000);
    }

    public void testNotFullData_status_PROCESSING_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_short(2000000);
        set_status("PROCESSING");
        fail_a2c_short(100);
    }

    public void testFullData_status_PROCESSING_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full(2500000);
        set_status("PROCESSING");
        success_a2c_full(2500000);
    }

    public void testFullData_one_trans_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full(2500000);
    }

    public void testFullData_one_trans_positive_lessSumm() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full(200000);
    }

    public void testFullData_two_trans_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full(2500000);
        set_status("PROCESSED");
        success_a2c_full(2500000);
    }

    public void testOneNotFullData_ThenFullDate_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_short(2000000);
        set_status("PROCESSED");
        success_a2c_full(100);
        set_status("PROCESSED");
        success_a2c_full(2500000);
    }

    public void testOneFullData_SecondNotFullDate_negative() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full(2000000);
        set_status("PROCESSED");
        fail_a2c_short(100);
    }

    public void testDate_short_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_short(2000000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where yyyyMMddHHmmssSSS = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data.getData(card, Card_param.token)+"') order by created_at asc limit 1)");
        success_a2c_short(2000000);
    }

    public void testDate_short_full_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_short(2000000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data.getData(card, Card_param.token)+"') order by created_at asc limit 1)");
        success_a2c_full(2500000);
    }

    public void testDate_full_short_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full(2500000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data.getData(card, Card_param.token)+"') order by created_at asc limit 1)");
        success_a2c_short(2000000);
    }

    public void testDate_full_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full(2500000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data.getData(card, Card_param.token)+"') order by created_at asc limit 1)");
        success_a2c_full(2500000);
    }

    public void testVariables_params_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String body;
        A2C a2c;
        logStartTest("address -> locality + street");
        delete_trans();
        body = "\"amount\": 2500000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              }\n" +
                "        },"+
                "       \"address\": {" +
                "               \"locality\": \"11111\",\n" +
                "               \"street\": \"22222\"\n" +
                "               }"+
                "    },"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t}";
        a2c = new A2C(body);

        logStartTest("identification -> series + number");
        delete_trans();
        body = "\"amount\": 2500000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              }\n" +
                "        },"+
                "       \"identification\": {" +
                "               \"series\": \"11111\",\n" +
                "               \"number\": \"22222\"\n" +
                "               }"+
                "    },"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t}";
        a2c = new A2C(body);

        logStartTest("general -> tax_id");
        delete_trans();
        body = "\"amount\": 2500000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              }\n" +
                "        },"+
                "       \"general\": {" +
                "               \"tax_id\": \"1234567890\"\n" +
                "               }"+
                "    },"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t}";
        a2c = new A2C(body);

        logStartTest("general -> birthday + birth_country + locality_type + birth_locality");
        delete_trans();
        body = "\"amount\": 2500000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              }\n" +
                "        },"+
                "       \"general\": {" +
                "               \"birthday\": \"2011-11-11\",\n" +
                "               \"birth_country\": \"UKR\",\n" +
                "               \"locality_type\": 3,\n" +
                "               \"birth_locality\": \"4444\"\n" +
                "               }"+
                "    },"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t}";
        a2c = new A2C(body);
    }

    public void testCheck_different_reciever() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDpostgre.updateSQL("delete FROM limits.entries x where x.card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data.getData(Card.MONO_MC, Card_param.token)+"')");
        testNotFullData_one_trans_positive();
        set_status("PROCESSED");
        String body = "\"amount\": 2000000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.MONO_MC, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "    }," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"sender\":{\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      }\n" +
                "      }\n" +
                "\t}";
        A2C a2c = new A2C(body);
    }

    @BeforeTest
    void start() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDpostgre.BDpostgre("limiter", "dev","password");
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

    void success_a2c_short(int summ){
        String body = "\"amount\": "+summ+",\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "    }," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"sender\":{\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      }\n" +
                "      }\n" +
                "\t}";
        a2c = new A2C(body);
    }

    void success_a2c_full(int summ){
        String body = "\"amount\": "+summ+",\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              }\n" +
                "        },"+
                "       \"address\": {" +
                "               \"locality\": \"11111\",\n" +
                "               \"street\": \"22222\"\n" +
                "               }"+
                "    },"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t}";
        a2c = new A2C(body);
    }

    void success_a2c_full_without_recipient(int summ){
        String body = "\"amount\": "+summ+",\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              }\n" +
                "        },"+
                "       \"address\": {" +
                "               \"locality\": \"11111\",\n" +
                "               \"street\": \"22222\"\n" +
                "               }"+
                "    },"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t}";
        a2c = new A2C(body);
    }

    void fail_a2c_short(int summ){
        String bodyForFail = "\"amount\": "+summ+",\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "    }," +
                "    \"identification\": {\n" +
                "   \"requirements\":{\n" +
                "      \"sender\":{\n" +
                "         \"account_number\":\"UA213223130000026007233566001\"\n" +
                "      }\n" +
                "      }\n" +
                "\t}";
        a2c = new A2C();
        a2c.setExpectedStatus(400);
        a2c.setBodyRequest(bodyForFail);
        a2c.setThreeDS(0);
        a2c.makeTrans();
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
        BDpostgre.updateSQL("update limits.entries x set status='"+status+"' where x.card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data.getData(card, Card_param.token)+"')");
    }

    void delete_trans() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDpostgre.updateSQL("delete FROM limits.entries x where x.card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data.getData(card, Card_param.token)+"')");
    }
}
