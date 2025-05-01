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
public class NBU_14_a2c_direct extends BaseTest {

    Limits limits = new Limits();
    Card card = Card.MONO_MC;
    A2C a2c;

    public void test_one_trans_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full_without_identification(2500000);
    }

    public void test_two_trans_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        success_a2c_full_without_identification(2000000);
        set_status("PROCESSED");
        success_a2c_full_without_identification(2500000);
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

    void success_a2c_full_without_identification(int summ){
        String body = "\"amount\": "+summ+",\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 0,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(card, Card_param.pan) +"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "\t},"+
                "\"payer\":{\n" +
                "\t\t\"source\":\"IBAN\",\n" +
                "\t\t\"value\":\"UA213223130000026007233566001\"\n" +
                "\t}";
        a2c = new A2C(body);
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
        BDpostgre.updateSQL("update limits.entries x set status='"+status+"' where x.card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+Cards_data.getData(card, Card_param.token)+"')");
    }

    void delete_trans() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDpostgre.updateSQL("delete FROM limits.entries x where x.card_id = (SELECT x.id FROM cards.cards x WHERE x.itm_token = '"+Cards_data.getData(card, Card_param.token)+"')");
    }
}
