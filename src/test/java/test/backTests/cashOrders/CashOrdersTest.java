package test.backTests.cashOrders;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.cashOrders.CashOrders;
import org.example.qaTransactionTeam.backEnd.payHub.AcquiringTrans;
import org.example.qaTransactionTeam.backEnd.transaction.Payer_constructor;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Test
public class CashOrdersTest extends BaseTest {

    String x_frame_id = "0de939bd-77d7-49b9-a88c-f2473c43c98e";
    String phone = "380999999993";

    @DataProvider
    public Object[][] x_frame_id_nagetive() {
        return new Object[][] {{"1"}, {""},{"default"}, {"null"}};
    }
    @DataProvider
    public Object[][] commission_positive() {return new Object[][] {{"50000"}, {"900000"}};}
    @DataProvider
    public Object[][] commission_nagetive() {
        return new Object[][] {{"49900"}, {"900100"},{"49999"}, {"900001"}};
    }
    @DataProvider
    public Object[][] currency_positive() {
        return new Object[][] {{"USD"}, {"EUR"},{"PLN"}, {"UAH"}};
    }
    @DataProvider
    public Object[][] name_nagetive() {return new Object[][] {{"test"}, {"trser sdf"}, {"Укр da"}, {""},{"12312"}, {"вава!"}, {"!,.:.;(№:"},{"Тест 1324"},};}
    @DataProvider
    public Object[][] phone_nagetive() {return new Object[][] {{"3442342"}, {""}, {"fdsfad"}, {"380998883344 "}, {"380998883344 fds"},};}
    @DataProvider
    public Object[][] email_nagetive() {return new Object[][] {{"dfsadf"}, {"авываыв@dfdas.adf"}, {"fdsfad@dasd"}, {"asd231@.asd"},{"asd231.asd"},{"edfd fds@das.da"},{""},{"fdsfad@mail.ru"}};}

    @Test(dataProvider = "commission_positive")
    public void positive_get_commissions(String amount){
        logStartTest(amount);
        query = new HashMap();
        query.put("amount",amount);
        cashOrders.setStatusCode(200);
        cashOrders.getCommission(query);
        json = new JSONObject(cashOrders.getResponse());
        commiosion = json.getJSONObject("commission").getInt("amount");
        Assert.assertNotEquals(commiosion,0);
    }

    @Test(dataProvider = "commission_nagetive")
    public void nagetive_get_commissions(String amount){
        logStartTest(amount);
        query = new HashMap();
        query.put("amount",amount);
        cashOrders.setStatusCode(200);
        cashOrders.getCommission(query);
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getJSONObject("commission").getInt("rate"),0);
    }

    public void positive_get_frame_config() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        cashOrders.setStatusCode(200);
        cashOrders.getFrameConfigs(x_frame_id);
        json = new JSONObject(cashOrders.getResponse());
        ResultSet res = sql("frames_test","SELECT x.* FROM cash_order.cash_order_links x where x.id = '"+x_frame_id+"'");
        Assert.assertEquals(json.getString("status"),res.getString("status"));
        Assert.assertEquals(json.getString("frame_id"),x_frame_id);
        Assert.assertEquals(json.getString("frame_id"),res.getString("id"));
        Assert.assertEquals(json.getString("title"),res.getString("title"));
        Assert.assertEquals(json.getString("destination"),res.getString("destination"));
        Assert.assertEquals(json.getString("lang"),res.getString("lang"));
        Assert.assertEquals(json.getString("shop_url"),res.getString("shop_url"));
        Assert.assertEquals(json.getString("short_url"),res.getString("short_url"));
        Assert.assertEquals(json.getString("cancel_url"),res.getString("cancel_url"));
        Assert.assertEquals(json.getString("description"),res.getString("description"));
    }

    public void negative_get_frame_config_not_found(){
        cashOrders.setStatusCode(404);
        cashOrders.getFrameConfigs("1de939bd-77d7-49b9-a88c-f2473c43c98e");
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("code"),"NOT_FOUND");
    }

    @Test(dataProvider = "x_frame_id_nagetive")
    public void negative_x_frame_get_frame_config(String x_frame_id){
        logStartTest(x_frame_id);
        cashOrders.setStatusCode(400);
        cashOrders.getFrameConfigs(x_frame_id);
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("code"),"BAD_REQUEST");
    }

    public void positive_get_transaction() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        cashOrders.setStatusCode(200);
        positive_create_transaction();
        cashOrders.getTransaction(x_frame_id,trans_id);
        json = new JSONObject(cashOrders.getResponse());
        ResultSet res = sql("frames_test","SELECT x.* FROM cash_order.cash_order_transactions x where x.internal_id = '"+trans_id+"'");
        Assert.assertEquals(json.getString("status"),res.getString("status"));
        Assert.assertEquals(x_frame_id,res.getString("link_id"));
        Assert.assertEquals(json.getString("card_from_hash"),res.getString("card_hash"));
    }

    @Test(dataProvider = "x_frame_id_nagetive")
    public void negative_x_frame_id_get_transaction(String x_frame_id){
        logStartTest(x_frame_id);
        cashOrders.setStatusCode(400);
        cashOrders.getTransaction(x_frame_id,"092da93d-b9c8-4002-9f82-306cdc8c8267");
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("code"),"BAD_REQUEST");
    }

    public void negative_get_transaction_not_found(){
        cashOrders.setStatusCode(404);
        cashOrders.getTransaction(x_frame_id,"092da93d-b9c8-4002-9f82-306cdc8c8267");
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("code"),"NOT_FOUND");
    }

    public void positive_get_branches(){
        cashOrders.getBranches();
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("status"), "ok");
        Assert.assertTrue(json.getJSONArray("branches").length() > 0);
    }

    @Test(dataProvider = "currency_positive")
    public void positive_get_rates(String currency){
        logStartTest(currency);
        cashOrders.setStatusCode(200);
        cashOrders.getRates(currency);
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("status"), "ok");
        rates = json.getJSONArray("rates").getJSONObject(0).getFloat("sale_rate");
    }

    public void negative_get_rates_not_found(){
        cashOrders.setStatusCode(404);
        cashOrders.getRates("use");
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("code"), "NOT_FOUND");
    }

    public void positive_timeslots() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String name = "Автотест Влад";
        String email = "vladyslav.savchuk@valuetek.com.ua";
        String merchant_id = "5d6c1ff9-3286-467f-bfe2-5450fde18217";
        body = "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"phone_number\": \""+phone+"\",\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"point_id\": \"DN1\",\n" +
                "  \"merchant_id\": \""+merchant_id+"\"\n" +
                "}";
        cashOrders.setStatusCode(200);
        cashOrders.timeslots("uk", body,x_frame_id);
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("status"), "ok");
        timeslots_id = json.getString("order_id");
        String start_date = json.getJSONArray("timeslots").getJSONObject(1).getString("start");
        String end_date = json.getJSONArray("timeslots").getJSONObject(1).getString("end");
        timeslots_start_date = start_date.substring(0,start_date.toCharArray().length-1);
        timeslots_end_date = end_date.substring(0,end_date.toCharArray().length-1);
        ResultSet res = sql("longshot","SELECT x.* FROM public.orders x where id = '"+timeslots_id+"'");
        Assert.assertEquals(res.getString("status"),"NEW");
        Assert.assertEquals(res.getString("internal_status"),"CREATED");
        Assert.assertEquals(res.getString("phone"), phone);
        Assert.assertEquals(res.getString("email"), email);
        Assert.assertEquals(res.getString("name"), name);
        Assert.assertEquals(res.getString("source"), "WEBSITE");
        Assert.assertEquals(res.getString("merchant_id"), merchant_id);
        //Assert.assertTrue(res.getInt("client_id") > 0);
    }

    public void negative_timeslots_mandatory_fields() {
        String name = "Автотест Влад";
        String phone = "380933943736";
        String email = "fdsd@das.as";
        String point_id = "ALK";
        String merchant_id = "5d6c1ff9-3286-467f-bfe2-5450fde18217";
        cashOrders.setStatusCode(400);
        body = "{\n" +
                "  \"phone_number\": \""+phone+"\",\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"point_id\": \""+point_id+"\",\n" +
                "  \"merchant_id\": \""+merchant_id+"\"\n" +
                "}";
        cashOrders.timeslots("uk", body,x_frame_id);

        body = "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"point_id\": \""+point_id+"\",\n" +
                "  \"merchant_id\": \""+merchant_id+"\"\n" +
                "}";
        cashOrders.timeslots("uk", body,x_frame_id);

        body = "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"phone_number\": \""+phone+"\",\n" +
                "  \"point_id\": \""+point_id+"\",\n" +
                "  \"merchant_id\": \""+merchant_id+"\"\n" +
                "}";
        cashOrders.timeslots("uk", body,x_frame_id);

        body = "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"phone_number\": \""+phone+"\",\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"merchant_id\": \""+merchant_id+"\"\n" +
                "}";
        cashOrders.timeslots("uk", body,x_frame_id);
    }

    @Test(dataProvider = "name_nagetive")
    public void negative_name_timeslots(String name){
        logStartTest(name);
        String phone = "380933943736";
        String email = "fdsd@das.as";
        String point_id = "ALK";
        String merchant_id = "5d6c1ff9-3286-467f-bfe2-5450fde18217";
        body = "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"phone_number\": \""+phone+"\",\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"point_id\": \""+point_id+"\",\n" +
                "  \"merchant_id\": \""+merchant_id+"\"\n" +
                "}";
        cashOrders.setStatusCode(400);
        cashOrders.timeslots("uk", body,x_frame_id);
    }

    @Test(dataProvider = "phone_nagetive")
    public void negative_phone_timeslots(String phone){
        logStartTest(phone);
        String name = "Авто тест";
        String email = "fdsd@das.as";
        String point_id = "ALK";
        String merchant_id = "5d6c1ff9-3286-467f-bfe2-5450fde18217";
        body = "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"phone_number\": \""+phone+"\",\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"point_id\": \""+point_id+"\",\n" +
                "  \"merchant_id\": \""+merchant_id+"\"\n" +
                "}";
        cashOrders.setStatusCode(400);
        cashOrders.timeslots("uk", body,x_frame_id);
    }

    @Test(dataProvider = "email_nagetive")
    public void negative_email_timeslots(String email){
        logStartTest(email);
        String name = "Авто тест";
        String phone = "380938887766";
        String point_id = "ALK";
        String merchant_id = "5d6c1ff9-3286-467f-bfe2-5450fde18217";
        body = "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"phone_number\": \""+phone+"\",\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"point_id\": \""+point_id+"\",\n" +
                "  \"merchant_id\": \""+merchant_id+"\"\n" +
                "}";
        cashOrders.setStatusCode(400);
        cashOrders.timeslots("uk", body,x_frame_id);
    }

    public void positive_create_transaction() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String amount = "60000";
        positive_timeslots();
        positive_get_commissions(amount);
        body = "{\n" +
                    "    \"order\": {\n" +
                    "        \"id\": \""+timeslots_id+"\",\n" +
                    "        \"receiving_date\": {\n" +
                    "            \"start\": \"2024-09-16T05:30:00.000Z\",\n" +
                    "            \"end\": \"2024-09-16T14:00:00.000Z\"\n" +
                    "        },\n" +
                    "        \"amount\": "+amount+",\n" +
                    "        \"point_id\": \"DN1\",\n" +
                    "        \"currency_id\": \"USD\"\n" +
                    "    },\n" +
                    "    \"client_ip\": \"127.0.0.1\",\n" +
                    "    \"cardholder_name\": \"John Dow\",\n" +
                    "    \"description\": \"test123\",\n" +
                    "    \"payer\" :" +
                    "        {     \"source\": \"PAN\", " +
                    "              \"pan\": \""+ Cards_data.getData(Card.FUIB_MC,Card_param.pan)+"\", " +
                    "              \"expire\": \""+ Cards_data.getData(Card.FUIB_MC,Card_param.expire)+"\", " +
                    "              \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC,Card_param.cvv)+"\"},\n" +
                    "    \"threed\": {\n" +
                    "        \"fingerprint\": \"test\",\n" +
                    "        \"channel\": \"BRW\",\n" +
                    "        \"return_url\": \"http://localhost:7171/ping\",\n" +
                    "        \"challenge_window_size\": \"01\",\n" +
                    "        \"iframe_return_url\": \"test\",\n" +
                    "        \"browser_accept_header\": \"test\",\n" +
                    "        \"browser_ip\": \"127.0.0.1\",\n" +
                    "        \"browser_java_enabled\": false,\n" +
                    "        \"browser_javascript_enabled\": true,\n" +
                    "        \"browser_language\": \"uk\",\n" +
                    "        \"browser_color_depth\": 32,\n" +
                    "        \"browser_screen_height\": 1024,\n" +
                    "        \"browser_screen_width\": 768,\n" +
                    "        \"browser_tz\": 2,\n" +
                    "        \"browser_user_agent\": \"test\"\n" +
                    "    }\n" +
                    "}";
        cashOrders.createTransaction(x_frame_id,body, "uk");
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("status"),"3DS_VERIFICATION_NEEDED");
        Assert.assertEquals(json.getString("description"),"test123");
        Assert.assertEquals(json.getInt("amount"),commiosion);
        Assert.assertEquals(json.getInt("deducted_amount"),0);
        Assert.assertEquals(json.getInt("commission"),0);
        trans_id = json.getString("transaction_id");
    }

    public void positive_create_transaction_whithout_desc() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String amount = "50000";
        positive_timeslots();
        positive_get_commissions(amount);
        body = "{\n" +
                "    \"order\": {\n" +
                "        \"id\": \""+timeslots_id+"\",\n" +
                "        \"receiving_date\": {\n" +
                "            \"start\": \""+timeslots_start_date+"\",\n" +
                "            \"end\": \""+timeslots_end_date+"\"\n" +
                "        },\n" +
                "        \"amount\": "+amount+",\n" +
                "        \"point_id\": \"ALK\",\n" +
                "        \"currency_id\": \"USD\"\n" +
                "    },\n" +
                "    \"client_ip\": \"127.0.0.1\",\n" +
                "    \"cardholder_name\": \"John Dow\",\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(Card.FUIB_MC))+","+
                "    \"threed\": {\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"channel\": \"BRW\",\n" +
                "        \"return_url\": \"http://localhost:7171/ping\",\n" +
                "        \"challenge_window_size\": \"01\",\n" +
                "        \"iframe_return_url\": \"test\",\n" +
                "        \"browser_accept_header\": \"test\",\n" +
                "        \"browser_ip\": \"127.0.0.1\",\n" +
                "        \"browser_java_enabled\": false,\n" +
                "        \"browser_javascript_enabled\": true,\n" +
                "        \"browser_language\": \"uk\",\n" +
                "        \"browser_color_depth\": 32,\n" +
                "        \"browser_screen_height\": 1024,\n" +
                "        \"browser_screen_width\": 768,\n" +
                "        \"browser_tz\": 2,\n" +
                "        \"browser_user_agent\": \"test\"\n" +
                "    }\n" +
                "}";
        cashOrders.createTransaction(x_frame_id,body, "uk");
        ResultSet res = sql("frames_test","SELECT x.* FROM cash_order.cash_order_links x where x.id = '"+x_frame_id+"'");
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("status"),"3DS_VERIFICATION_NEEDED");
        Assert.assertEquals(json.getString("description"), res.getString("description"));
        Assert.assertEquals(json.getInt("amount"),commiosion);
        Assert.assertEquals(json.getInt("deducted_amount"),0);
        Assert.assertEquals(json.getInt("commission"),0);
    }

    @Test(dataProvider = "commission_nagetive")
    public void negative_create_transaction(String amount) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logStartTest(amount);
        positive_timeslots();
        cashOrders.setStatusCode(400);
        body = "{\n" +
                "    \"order\": {\n" +
                "        \"id\": \""+timeslots_id+"\",\n" +
                "        \"receiving_date\": {\n" +
                "            \"start\": \""+timeslots_start_date+"\",\n" +
                "            \"end\": \""+timeslots_end_date+"\"\n" +
                "        },\n" +
                "        \"amount\": "+amount+",\n" +
                "        \"point_id\": \"KHO\",\n" +
                "        \"currency_id\": \"USD\"\n" +
                "    },\n" +
                "    \"client_ip\": \"127.0.0.1\",\n" +
                "    \"cardholder_name\": \"John Dow\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"pan\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    },\n" +
                "    \"threed\": {\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"channel\": \"BRW\",\n" +
                "        \"return_url\": \"http://localhost:7171/ping\",\n" +
                "        \"challenge_window_size\": \"01\",\n" +
                "        \"iframe_return_url\": \"test\",\n" +
                "        \"browser_accept_header\": \"test\",\n" +
                "        \"browser_ip\": \"127.0.0.1\",\n" +
                "        \"browser_java_enabled\": false,\n" +
                "        \"browser_javascript_enabled\": true,\n" +
                "        \"browser_language\": \"uk\",\n" +
                "        \"browser_color_depth\": 32,\n" +
                "        \"browser_screen_height\": 1024,\n" +
                "        \"browser_screen_width\": 768,\n" +
                "        \"browser_tz\": 2,\n" +
                "        \"browser_user_agent\": \"test\"\n" +
                "    }\n" +
                "}";
        cashOrders.createTransaction(x_frame_id,body, "uk");
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("code"),"BAD_REQUEST");
    }

    public void negative_create_transaction_mandatory_fields() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        positive_timeslots();
        cashOrders.setStatusCode(400);
        body = "{\n" +
                "    \"client_ip\": \"127.0.0.1\",\n" +
                "    \"cardholder_name\": \"John Dow\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"pan\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    },\n" +
                "    \"threed\": {\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"channel\": \"BRW\",\n" +
                "        \"return_url\": \"http://localhost:7171/ping\",\n" +
                "        \"challenge_window_size\": \"01\",\n" +
                "        \"iframe_return_url\": \"test\",\n" +
                "        \"browser_accept_header\": \"test\",\n" +
                "        \"browser_ip\": \"127.0.0.1\",\n" +
                "        \"browser_java_enabled\": false,\n" +
                "        \"browser_javascript_enabled\": true,\n" +
                "        \"browser_language\": \"uk\",\n" +
                "        \"browser_color_depth\": 32,\n" +
                "        \"browser_screen_height\": 1024,\n" +
                "        \"browser_screen_width\": 768,\n" +
                "        \"browser_tz\": 2,\n" +
                "        \"browser_user_agent\": \"test\"\n" +
                "    }\n" +
                "}";
        cashOrders.createTransaction(x_frame_id,body, "uk");

        body = "{\n" +
                "    \"order\": {\n" +
                "        \"id\": \""+timeslots_id+"\",\n" +
                "        \"receiving_date\": {\n" +
                "            \"start\": \"2023-09-23\",\n" +
                "            \"end\": \"2023-09-23\"\n" +
                "        },\n" +
                "        \"amount\": 50000,\n" +
                "        \"point_id\": \"3330\",\n" +
                "        \"branch\": \"KHO\",\n" +
                "        \"currency_id\": \"USD\"\n" +
                "    },\n" +
                "    \"cardholder_name\": \"John Dow\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"pan\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    },\n" +
                "    \"threed\": {\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"channel\": \"BRW\",\n" +
                "        \"return_url\": \"http://localhost:7171/ping\",\n" +
                "        \"challenge_window_size\": \"01\",\n" +
                "        \"iframe_return_url\": \"test\",\n" +
                "        \"browser_accept_header\": \"test\",\n" +
                "        \"browser_ip\": \"127.0.0.1\",\n" +
                "        \"browser_java_enabled\": false,\n" +
                "        \"browser_javascript_enabled\": true,\n" +
                "        \"browser_language\": \"uk\",\n" +
                "        \"browser_color_depth\": 32,\n" +
                "        \"browser_screen_height\": 1024,\n" +
                "        \"browser_screen_width\": 768,\n" +
                "        \"browser_tz\": 2,\n" +
                "        \"browser_user_agent\": \"test\"\n" +
                "    }\n" +
                "}";
        cashOrders.createTransaction(x_frame_id,body, "uk");

        body = "{\n" +
                "    \"order\": {\n" +
                "        \"id\": \""+timeslots_id+"\",\n" +
                "        \"receiving_date\": {\n" +
                "            \"start\": \"2023-09-23\",\n" +
                "            \"end\": \"2023-09-23\"\n" +
                "        },\n" +
                "        \"amount\": 50000,\n" +
                "        \"point_id\": \"3330\",\n" +
                "        \"branch\": \"KHO\",\n" +
                "        \"currency_id\": \"USD\"\n" +
                "    },\n" +
                "    \"client_ip\": \"127.0.0.1\",\n" +
                "    \"cardholder_name\": \"John Dow\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"threed\": {\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"channel\": \"BRW\",\n" +
                "        \"return_url\": \"http://localhost:7171/ping\",\n" +
                "        \"challenge_window_size\": \"01\",\n" +
                "        \"iframe_return_url\": \"test\",\n" +
                "        \"browser_accept_header\": \"test\",\n" +
                "        \"browser_ip\": \"127.0.0.1\",\n" +
                "        \"browser_java_enabled\": false,\n" +
                "        \"browser_javascript_enabled\": true,\n" +
                "        \"browser_language\": \"uk\",\n" +
                "        \"browser_color_depth\": 32,\n" +
                "        \"browser_screen_height\": 1024,\n" +
                "        \"browser_screen_width\": 768,\n" +
                "        \"browser_tz\": 2,\n" +
                "        \"browser_user_agent\": \"test\"\n" +
                "    }\n" +
                "}";
        cashOrders.createTransaction(x_frame_id,body, "uk");

        body = "{\n" +
                "    \"order\": {\n" +
                "        \"id\": \""+timeslots_id+"\",\n" +
                "        \"receiving_date\": {\n" +
                "            \"start\": \"2023-09-23\",\n" +
                "            \"end\": \"2023-09-23\"\n" +
                "        },\n" +
                "        \"amount\": 50000,\n" +
                "        \"point_id\": \"3330\",\n" +
                "        \"branch\": \"KHO\",\n" +
                "        \"currency_id\": \"USD\"\n" +
                "    },\n" +
                "    \"client_ip\": \"127.0.0.1\",\n" +
                "    \"cardholder_name\": \"John Dow\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"pan\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "        \"expire\": \"2602\",\n" +
                "        \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }\n" +
                "}";
        cashOrders.createTransaction(x_frame_id,body, "uk");
    }

    @Test(enabled = true)
    public void make_transaction() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        cashOrders.setStatusCode(200);
        Card card = Card.MONO_MC;
        positive_timeslots();
        String amount = "50000";
        String cardholder_name = "John Dow123";
        String currency_id = "USD";
        String point_id = "KHO";
        positive_get_commissions(amount);
        positive_get_rates(currency_id);
        body = "{\n" +
                "    \"order\": {\n" +
                "        \"id\": \""+timeslots_id+"\",\n" +
                "        \"receiving_date\": {\n" +
                "            \"start\": \""+timeslots_start_date+"\",\n" +
                "            \"end\": \""+timeslots_end_date+"\"\n" +
                "        },\n" +
                "        \"amount\": "+amount+",\n" +
                "        \"point_id\": \""+point_id+"\",\n" +
                "        \"currency_id\": \""+currency_id+"\"\n" +
                "    },\n" +
                "    \"client_ip\": \"127.0.0.1\",\n" +
                "    \"cardholder_name\": \""+cardholder_name+"\",\n" +
                "    \"description\": \"test\",\n" +
                "    \"threed\": {\n" +
                "        \"fingerprint\": \"test\",\n" +
                "        \"channel\": \"BRW\",\n" +
                "        \"return_url\": \"http://localhost:7171/ping\",\n" +
                "        \"challenge_window_size\": \"01\",\n" +
                "        \"iframe_return_url\": \"test\",\n" +
                "        \"browser_accept_header\": \"test\",\n" +
                "        \"browser_ip\": \"127.0.0.1\",\n" +
                "        \"browser_java_enabled\": false,\n" +
                "        \"browser_javascript_enabled\": true,\n" +
                "        \"browser_language\": \"uk\",\n" +
                "        \"browser_color_depth\": 32,\n" +
                "        \"browser_screen_height\": 1024,\n" +
                "        \"browser_screen_width\": 768,\n" +
                "        \"browser_tz\": 2,\n" +
                "        \"browser_user_agent\": \"test\"\n" +
                "    },\n" +
                "   \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"pan\": \""+ Cards_data.getData(card,Card_param.pan)+"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(card,Card_param.expire)+"\",\n" +
                "        \"cvv\": \""+ Cards_data.getData(card,Card_param.cvv)+"\"\n" +
                "    }" +
                "}";
        cashOrders.makeTransaction(x_frame_id,body, "uk");
        json = new JSONObject(cashOrders.getResponse());
        Assert.assertEquals(json.getString("cardholder_name"), cardholder_name);
        Assert.assertEquals(json.getInt("amount"), commiosion);
        AcquiringTrans acquiring = new AcquiringTrans();
        ResultSet res = sql("frames_test","SELECT x.* FROM cash_order.cash_order_transactions x where x.internal_id = '"+cashOrders.getTransaction_id()+"'");
        acquiring.status(res.getString("id"));
        json = new JSONObject(acquiring.getResponse());
        String status_acquiring = json.getString("status");
        Assert.assertEquals(res.getString("status"), status_acquiring);
        Assert.assertEquals(res.getString("rrn"), json.getString("rrn"));
        ResultSet res1 = sql("longshot", "SELECT x.* FROM public.orders x where id = '" + timeslots_id + "'");
        if (status_acquiring.equals("PROCESSED")) {
            Assert.assertEquals(res1.getString("amount"), amount);
            Assert.assertEquals(res1.getString("currency"), currency_id);
            Assert.assertEquals(res1.getString("branch_id"), point_id);
            float amount_in_uah = Float.parseFloat(amount) * rates;
            Assert.assertEquals(Integer.parseInt(res1.getString("amount_uah")), (int) amount_in_uah);
        }else {
            Assert.assertNull(res1.getString("amount"));
            Assert.assertNull(res1.getString("currency"));
            Assert.assertNull(res1.getString("branch_id"));
            Assert.assertNull(res1.getString("amount_uah"));
        }
    }

    private ResultSet sql(String base, String query) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ResultSet res;
        try {
            BDpostgre.BDpostgre(base, "dev","password");
            res = BDpostgre.selectSQL(query);
        } finally {
            BDpostgre.closeConn();
        }
        res.next();
        return res;
    }

    CashOrders cashOrders = new CashOrders();
    Map query;
    String body;
    String trans_id = "";
    String timeslots_id = "";
    String timeslots_start_date = "";
    String timeslots_end_date = "";
    int commiosion;
    float rates;
    JSONObject json;
}
