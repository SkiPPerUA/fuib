package test.backTests.beyonder;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.notificationBP.ApiNotification;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApiNotificationTest extends BaseTest {

    private String startdate = "2021-05-01 00:00:00";
    private String finishdate = "2021-05-29 00:00:00";
    private ApiNotification api = new ApiNotification();

    @BeforeTest
    public void connBD() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        BDpostgre.BDpostgre("beyonderdb", Configs.POSTGRE_SQL_BEYONDER_NAME,Configs.POSTGRE_SQL_BEYONDER_PASSWORD);
    }

    @Test
    public void testAllStatistic() throws JSONException, SQLException {
        logStartTest("testAllStatistic");


        api.statistics(startdate,finishdate);

        JSONObject bp005 = new JSONObject(api.getResponse()).getJSONObject("043");
        Assert.assertEquals(bp005.getString("BANK_SUCCEEDED"), countBDstatistic("BANK_SUCCEEDED","043"));
        Assert.assertEquals(bp005.getString("BANK_FAILED"), countBDstatistic("BANK_FAILED","043"));

        JSONObject bp034 = new JSONObject(api.getResponse()).getJSONObject("005");
        Assert.assertEquals(bp034.getString("BANK_SUCCEEDED"), countBDstatistic("BANK_SUCCEEDED","005"));

        logFinishTest("testAllStatistic");
    }

    @Test
    public void testStatisticOnePartner() throws JSONException, SQLException {
        logStartTest("testStatisticOnePartner");

        String partner = "043";
        api.statistics(startdate,finishdate,partner);

        JSONObject bp005 = new JSONObject(api.getResponse()).getJSONObject(partner);
        Assert.assertEquals(bp005.getString("BANK_SUCCEEDED"), countBDstatistic("BANK_SUCCEEDED",partner));
        Assert.assertEquals(bp005.getString("BANK_FAILED"), countBDstatistic("BANK_FAILED",partner));

        logFinishTest("testStatisticOnePartner");
    }

    @Test
    public void testCheckOneNotificationLimitOffset() throws JSONException, SQLException {
        logStartTest("testCheckOneNotificationLimitOffset");

        api.cardTransactions("1","0","BANK_SUCCEEDED");
        JSONArray ob2 = new JSONArray(api.getResponse());
        JSONObject ob = ob2.getJSONObject(0);
        Assert.assertEquals(ob.getString("request_id"), cardtransactions("BANK_SUCCEEDED","1","0")[0]);

        api.cardTransactions("1","0","BANK_FAILED");
        JSONArray ob3 = new JSONArray(api.getResponse());
        JSONObject ob1 = ob3.getJSONObject(0);
        Assert.assertEquals(ob1.getString("request_id"), cardtransactions("BANK_FAILED","1","0")[0]);

        logFinishTest("testCheckOneNotificationLimitOffset");
    }

    @Test
    public void testCheckSomeNotificationsLimitOffset() throws JSONException, SQLException {
        logStartTest("testCheckSomeNotificationsLimitOffset");

        api.cardTransactions("3","2","BANK_SUCCEEDED");
        JSONArray ob = new JSONArray(api.getResponse());

        for (int i = 0; i < ob.length();i++){
            JSONObject ob1 = ob.getJSONObject(i);
            Assert.assertEquals(ob1.getString("request_id"), cardtransactions("BANK_SUCCEEDED","3","2")[i]);
        }

        logFinishTest("testCheckSomeNotificationsLimitOffset");
    }

    @Test
    public void testCheckOneNotificationDate() throws JSONException, SQLException {
        logStartTest("testCheckOneNotificationDate");

        api.cardTransactions("1","0","BANK_SUCCEEDED",startdate,finishdate);
        JSONArray ob2 = new JSONArray(api.getResponse());
        JSONObject ob = ob2.getJSONObject(0);
        Assert.assertEquals(ob.getString("request_id"), cardtransactions("BANK_SUCCEEDED")[0]);

        api.cardTransactions("1","0","BANK_FAILED",startdate,finishdate);
        JSONArray ob3 = new JSONArray(api.getResponse());
        JSONObject ob1 = ob3.getJSONObject(0);
        Assert.assertEquals(ob1.getString("request_id"), cardtransactions("BANK_FAILED")[0]);

        logFinishTest("testCheckOneNotificationDate");
    }

    @Test
    public void testCheckSomeNotificationDate() throws JSONException, SQLException {
       logger.info("Старт теста testCheckSomeNotificationDate");

        api.cardTransactions("1","0","BANK_SUCCEEDED",startdate,finishdate);
        JSONArray ob = new JSONArray(api.getResponse());

        for (int i = 0; i < ob.length();i++){
            JSONObject ob1 = ob.getJSONObject(i);
            Assert.assertEquals(ob1.getString("request_id"), cardtransactions("BANK_SUCCEEDED")[i]);
        }

        logger.info("Конец теста testCheckSomeNotificationDate");
        logger.info("------------------------------------------");
    }

    @Test
    public void testFindByStan() throws SQLException, JSONException {

        logStartTest("testFindByStan");

        String stan = findStan();
        api.findByStan(stan);
        JSONArray arr = new JSONArray(api.getResponse());
        JSONObject ob = arr.getJSONObject(0);

        ResultSet res = BDpostgre.selectSQL("select * from notification_processed where ntstan = '"+stan+"'");
        res.next();

        Assert.assertEquals(ob.getString("request_id"),res.getString("request_id"));

        ResultSet res1 = BDpostgre.selectSQL("select count(*) from notification_processed where ntstan = '"+stan+"'");
        res1.next();
        Assert.assertEquals(arr.length(),Integer.parseInt(res1.getString(1)));

        logFinishTest("testFindByStan");
    }

    @Test
    public void testFindByRequestId() throws SQLException, JSONException {

        logStartTest("testFindByRequestId");

        String request_id = findRequestId();
        api.findByRequestId(request_id);
        JSONArray arr = new JSONArray(api.getResponse());
        JSONObject ob = arr.getJSONObject(0);

        ResultSet res = BDpostgre.selectSQL("select * from notification_processed where request_id = '"+request_id+"'");
        res.next();

        Assert.assertEquals(ob.getString("ntstan"),res.getString("ntstan"));
        Assert.assertEquals(arr.length(),1);

        logFinishTest("testFindByRequestId");
    }

    @Test
    public void checkBatch() throws JSONException, SQLException, InterruptedException {

        logStartTest("checkBatch");

        //Подготовка данных для теста
        makeActiveBatch();

        api.findActiveBatch();

        JSONArray arr = new JSONArray(api.getResponse());
        JSONObject ob = arr.getJSONObject(0);
        Assert.assertEquals(ob.getString("xbatch_id"),findBatch());

        //Возврат тестовых данных
        BDpostgre.updateSQL("update batch_record set status = 'FINISHED' where id = 1");

        logFinishTest("checkBatch");
    }

    @Test
    public void resendNotification() throws SQLException {

        logStartTest("resendNotification");
        ResultSet res = BDpostgre.selectSQL("select count(*) from batch_record");
        res.next();
        int countBefore = Integer.parseInt(res.getString(1));
        BDpostgre.updateSQL("update batch_record set status = 'FINISHED'");

        api.resendNegativeNotification(startdate,finishdate);

        ResultSet res1 = BDpostgre.selectSQL("select count(*) from batch_record");
        res1.next();
        int countAfter = Integer.parseInt(res1.getString(1));

        Assert.assertEquals(countAfter,countBefore+1);

        logFinishTest("resendNotification");

    }

    @AfterTest
    public void closeConn() throws SQLException {
        BDpostgre.closeConn();
    }

    private String countBDstatistic(String status, String codeBP) throws SQLException {
        ResultSet res = BDpostgre.selectSQL("select count(*) from notification_processed where status = '"+status+"' and ntispt = '"+codeBP+"' and processed_date between '"+startdate+"' and '"+finishdate+"'");
        res.next();
        return res.getString(1);
    }

    private String[] cardtransactions(String status, String limit, String offset) throws SQLException {
        String [] stan = new String[Integer.parseInt(limit)];
        int i = 0;
        ResultSet res = BDpostgre.selectSQL("select * from notification_processed where status = '"+status+"' order by id desc limit "+limit+" offset "+offset+"");
        while (res.next()){
            stan[i] = res.getString("request_id");
            i++;
        }

        return stan;
    }

    private String[] cardtransactions(String status) throws SQLException {
        ResultSet count = BDpostgre.selectSQL("select count(*) from notification_processed where processed_date between '"+startdate+"' and '"+finishdate+"'");
        count.next();
        String [] stan = new String[Integer.parseInt(count.getString(1))];
        int i = 0;
        ResultSet res = BDpostgre.selectSQL("select * from notification_processed where status = '"+status+"' and processed_date between '"+startdate+"' and '"+finishdate+"'");
        while (res.next()){
            stan[i] = res.getString("request_id");
            i++;
        }

        return stan;
    }

    private String findStan() throws SQLException {
        ResultSet res = BDpostgre.selectSQL("select * from notification_processed np order by processed_date desc limit 1");
        res.next();
        logger.info("Найденный стан - "+res.getString("ntstan"));
        return res.getString("ntstan");
    }

    private String findRequestId() throws SQLException {
        ResultSet res = BDpostgre.selectSQL("select * from notification_processed np order by processed_date desc limit 1");
        res.next();
        logger.info("Найденный request_id - "+res.getString("request_id"));
        return res.getString("request_id");
    }

    private String findBatch() throws SQLException {
        ResultSet res = BDpostgre.selectSQL("select * from batch_record br where status = 'ACTIVE'");
        res.next();
        return res.getString("x_batch_id");
    }

    private void makeActiveBatch() throws SQLException {
        BDpostgre.updateSQL("update batch_record set status = 'ACTIVE' where id = 1");
    }
}
