package test.backTests.nbu.nbu_14;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.Limits;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Test
public class NBU_14_a2c_cash extends BaseTest {

    Limits limits = new Limits();
    Card card = Card.FUIB_MC;

    public void testNotFullData_one_trans_negative() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        fail_a2c_short(2500000);
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

    public void testOneNotFullData_SecondFullDate_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_short(2000000);
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
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+ Cards_data.getData(card, Card_param.token)+"') order by created_at asc limit 1)");
        success_a2c_short(2000000);
    }

    public void testDate_short_full_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_short(2000000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+Cards_data.getData(card, Card_param.token)+"') order by created_at asc limit 1)");
        success_a2c_full(2500000);
    }

    public void testDate_full_short_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full(2500000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+Cards_data.getData(card, Card_param.token)+"') order by created_at asc limit 1)");
        success_a2c_short(2000000);
    }

    public void testDate_full_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full(2500000);
        set_status("PROCESSED");
        BDpostgre.updateSQL("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+Cards_data.getData(card, Card_param.token)+"') order by created_at asc limit 1)");
        success_a2c_full(2500000);
    }

    public void testCheck_reciever() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        testNotFullData_one_trans_positive();
        String body = "\"amount\": 2000000,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\"\n" +
                "              },\n" +
                "             \"details\":{\n" +
                "                 \"source\":\"02\"" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"reference_number\":\"reference_number\",\n" +
                "                 \"tax_id\":\"5252676776\""+
                "              }\n" +
                "        }},"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(Card.MONO_MC, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t}";
        A2C a2c = new A2C(body);
    }

    public void testParams_positive(){
        Assert.fail("Написать тест");
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
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\"\n" +
                "              },\n" +
                "             \"details\":{\n" +
                "                 \"source\":\"02\"" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"reference_number\":\"reference_number\",\n" +
                "                 \"tax_id\":\"5252676776\""+
                "              }\n" +
                "        }},"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t}";
        A2C a2c = new A2C(body);
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
                "             \"details\":{\n" +
                "                 \"source\":\"02\"" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"reference_number\":\"reference_number\",\n" +
                "                 \"tax_id\":\"5252676776\""+
                "              }\n" +
                "        }},"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t}";
        A2C a2c = new A2C(body);
    }

    void fail_a2c_short(int summ){
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
                "                 \"last_name\":\"IvanovSender\"\n" +
                "              }\n" +
                "        }"+
                "    },"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t}";
        try {
            A2C a2c = new A2C(body);
            Assert.fail("Проверка НЕ сработала");
        }catch (JSONException e){
            //ignore
        }
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

    void set_status(String status) throws SQLException {
        //Установке статуса успех всем транкам по нашей карте
        BDpostgre.updateSQL("update limits.entries x set status='"+status+"' where x.card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+Cards_data.getData(card, Card_param.token)+"')");
    }

    void delete_trans() throws SQLException {
        BDpostgre.updateSQL("delete FROM limits.entries x where x.card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+Cards_data.getData(card, Card_param.token)+"')");
    }
}
