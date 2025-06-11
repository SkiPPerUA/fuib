package test.backTests.admin;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.Limits;
import org.example.qaTransactionTeam.backEnd.helper.Time_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.Payer_constructor;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C2A;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C2C;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;

@Test
public class LimitsTest extends BaseTest {
    //svc_tpuo_ph

    Limits limits = new Limits();
    //PROFIT - зарахування, EXPENSE - cписання
    //FUIB_TO_FUIB - DIRECT_MONEY_TRANS - FUIB_TO_FUIB - EXTERNAL_BANK - FULL_IDENT_DIRECT - FULL_IDENT_FUIB_TO_FUIB
    String kind = "EXTERNAL_BANK";
    Card sender_card = Card.FUIB_MC;
    Card reciever_card = Card.FUIB_VISA;
    int statusCode = 200;
    String response;
    JSONObject json;

    public void trans(){
        success_a2c(100);
    }

    public void testSumm_PROFIT() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        createLimits("PROFIT",kind, 1100,2200,5,3400,10);
        fail_a2c(1101);
        success_a2c(1100);
        success_a2c(1000);
        set_status("PROCESSED");
        fail_a2c(101);
        success_a2c(100);
        set_status("PROCESSED");
        bd_update("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at asc limit 1)");
        createLimits("PROFIT",kind,1100,3000,5,3400,10);
        fail_a2c(1101);
        success_a2c(1100);
        set_status("PROCESSED");
        fail_a2c(101);
        success_a2c(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-1)+"' where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"')");
        bd_update("update limits.entries x set created_at = '"+change_days(-30)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at asc limit 1)");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        success_a2c(1000);
        set_status("PROCESSED");
        fail_a2c(101);
    }

    public void testCount_PROFIT() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        createLimits("PROFIT",kind,1100,2200,5,3400,10);
        success_a2c(100);
        success_a2c(100);
        success_a2c(100);
        success_a2c(100);
        set_status("PROCESSED");
        success_a2c(100);
        set_status("PROCESSED");
        fail_a2c(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at asc limit 1)");
        success_a2c(100);
        set_status("PROCESSED");
        fail_a2c(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-2)+"' where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"')");
        success_a2c(100);
        success_a2c(100);
        success_a2c(100);
        set_status("PROCESSED");
        success_a2c(100);
        set_status("PROCESSED");
        fail_a2c(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-30)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_a2c(100);
        set_status("PROCESSED");
        fail_a2c(100);
    }

    public void testStatus_PROFIT() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        createLimits("PROFIT",kind,1100,1500,4,1700,5);
        success_a2c(100);
        bd_update("update limits.entries x set status='PROCESSED' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_a2c(100);
        bd_update("update limits.entries x set status='FAILED' where correlation_id = (select correlation_id from limits.entries where yyyyMMddHHmmssSSS = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_a2c(100);
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bd_update("update limits.entries x set status='PROCESSING' where correlation_id = (select correlation_id from limits.entries where yyyyMMddHHmmssSSS = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        fail_a2c(1101);
        success_a2c(1100);
        bd_update("update limits.entries x set status='PROCESSED' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        fail_a2c(201);
        success_a2c(100);
        bd_update("update limits.entries x set status='PROCESSED' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        fail_a2c(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-1)+"' where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"')");
        fail_a2c(301);
        success_a2c(100);
        bd_update("update limits.entries x set status='PROCESSED' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        fail_a2c(100);
    }

    public void testTypeLimits_PROFIT() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        createLimits("PROFIT",kind,1100,1500,10,1700,15);
        success_a2c(100);
        bd_update("update limits.entries x set kind='DIRECT_MONEY_TRANS' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_a2c(100);
        bd_update("update limits.entries x set kind='FUIB_TO_FUIB' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_a2c(100);
        bd_update("update limits.entries x set kind='EXTERNAL_BANK' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_a2c(100);
        bd_update("update limits.entries x set kind='FULL_IDENT_DIRECT' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_a2c(100);
        bd_update("update limits.entries x set kind='FULL_IDENT_FUIB_TO_FUIB' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"') order by created_at desc limit 1)");
        set_status("PROCESSED");
        fail_a2c(1001);
        success_a2c(100);
        success_a2c(100);
        success_a2c(100);
        success_a2c(100);
        set_status("PROCESSED");
        success_a2c(100);
        set_status("PROCESSED");
        fail_a2c(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-1)+"' where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"')");
        success_a2c(100);
        success_a2c(100);
        success_a2c(100);
        set_status("PROCESSED");
        success_a2c(100);
        set_status("PROCESSED");
        fail_a2c(301);
        success_a2c(100);
        set_status("PROCESSED");
        fail_a2c(100);
    }

    public void testSumm_EXPENSE() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        createLimits("EXPENSE",kind, 1100,2200,5,3400,10);
        fail_c2a(1101);
        success_c2a(1100);
        success_c2a(1000);
        set_status("PROCESSED");
        fail_c2a(101);
        success_c2a(100);
        set_status("PROCESSED");
        bd_update("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at asc limit 1)");
        createLimits("EXPENSE",kind,1100,3000,5,3400,10);
        fail_c2a(1101);
        success_c2a(1100);
        set_status("PROCESSED");
        fail_c2a(101);
        success_c2a(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-1)+"' where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"')");
        bd_update("update limits.entries x set created_at = '"+change_days(-30)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at asc limit 1)");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        success_c2a(1000);
        set_status("PROCESSED");
        fail_c2a(101);
    }

    public void testCount_EXPENSE() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        createLimits("EXPENSE",kind,1100,2200,5,3400,10);
        success_c2a(100);
        success_c2a(100);
        success_c2a(100);
        success_c2a(100);
        set_status("PROCESSED");
        success_c2a(100);
        set_status("PROCESSED");
        fail_c2a(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-1)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at asc limit 1)");
        success_c2a(100);
        set_status("PROCESSED");
        fail_c2a(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-2)+"' where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"')");
        success_c2a(100);
        success_c2a(100);
        success_c2a(100);
        set_status("PROCESSED");
        success_c2a(100);
        set_status("PROCESSED");
        fail_c2a(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-30)+"'\n" +
                "where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_c2a(100);
        set_status("PROCESSED");
        fail_c2a(100);
    }

    public void testStatus_EXPENSE() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        createLimits("EXPENSE",kind,1100,1500,4,1700,5);
        success_c2a(100);
        bd_update("update limits.entries x set status='PROCESSED' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_c2a(100);
        bd_update("update limits.entries x set status='FAILED' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_c2a(100);
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bd_update("update limits.entries x set status='PROCESSING' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        fail_c2a(1101);
        success_c2a(1100);
        bd_update("update limits.entries x set status='PROCESSED' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        fail_c2a(201);
        success_c2a(100);
        bd_update("update limits.entries x set status='PROCESSED' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        fail_c2a(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-1)+"' where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"')");
        fail_c2a(301);
        success_c2a(100);
        bd_update("update limits.entries x set status='PROCESSED' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        fail_c2a(100);
    }

    public void testTypeLimits_EXPENSE() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        createLimits("EXPENSE",kind,1100,1500,10,1700,15);
        success_c2a(100);
        bd_update("update limits.entries x set kind='DIRECT_MONEY_TRANS' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_c2a(100);
        bd_update("update limits.entries x set kind='FUIB_TO_FUIB' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_c2a(100);
        bd_update("update limits.entries x set kind='EXTERNAL_BANK' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_c2a(100);
        bd_update("update limits.entries x set kind='FULL_IDENT_DIRECT' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        success_c2a(100);
        bd_update("update limits.entries x set kind='FULL_IDENT_FUIB_TO_FUIB' where correlation_id = (select correlation_id from limits.entries where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"') order by created_at desc limit 1)");
        set_status("PROCESSED");
        fail_c2a(1001);
        success_c2a(100);
        success_c2a(100);
        success_c2a(100);
        success_c2a(100);
        set_status("PROCESSED");
        success_c2a(100);
        set_status("PROCESSED");
        fail_c2a(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-1)+"' where card_id = (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"')");
        success_c2a(100);
        success_c2a(100);
        success_c2a(100);
        set_status("PROCESSED");
        success_c2a(100);
        set_status("PROCESSED");
        fail_c2a(301);
        success_c2a(100);
        set_status("PROCESSED");
        fail_c2a(100);
    }

    public void testDirection_same_card_reciever_and_sender() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        createLimits("PROFIT",kind,1100,1500,2,1700,3);
        createLimits("EXPENSE",kind,1100,1500,2,1700,3);
        success_a2c(500);
        success_c2a(500);
        set_status("PROCESSED");
        fail_a2c(1001);
        fail_c2a(1001);
        success_a2c(500);
        set_status("PROCESSED");
        success_c2a(500);
        set_status("PROCESSED");
        fail_a2c(100);
        fail_c2a(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-2)+"' where card_id= (SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"')");
        fail_a2c(701);
        fail_c2a(701);
        success_a2c(500);
        set_status("PROCESSED");
        success_c2a(500);
        set_status("PROCESSED");
        fail_a2c(100);
        fail_c2a(100);
    }

    public void testTypeTrans() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        delete_trans();
        createLimits("PROFIT",kind,1100,1500,2,1700,3);
        createLimits("EXPENSE",kind,1100,1500,2,1700,3);
        success_c2c(500);
        set_status("PROCESSED");
        fail_a2c(1001);
        fail_c2a(1001);
        fail_c2c(1001);
        success_a2c(500);
        set_status("PROCESSED");
        success_c2a(500);
        set_status("PROCESSED");
        fail_a2c(100);
        fail_c2a(100);
        fail_c2c(100);
        bd_update("update limits.entries x set created_at = '"+change_days(-2)+"' where card_id in ((SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"'),(SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"'))");
        fail_a2c(701);
        fail_c2a(701);
        fail_c2c(701);
        success_c2c(600);
        set_status("PROCESSED");
        fail_a2c(100);
        fail_c2a(100);
        fail_c2c(100);
    }

    public void testLimitsForMerchant(){
        String merch = "10546197-0d2f-4059-b9a2-d01cb97eba61"; //
        limits.setMerchantsLimits(false, merch);
        statusCode = 400;
        registrA2Ctrans();
        json = new JSONObject(response);
        Assert.assertEquals(json.getJSONObject("error").getString("message"), "спробуйте меншу суму");
        limits.setMerchantsLimits(true, merch);
        statusCode = 200;
        registrA2Ctrans();
        json = new JSONObject(response);
        Assert.assertEquals(json.getJSONObject("data").getString("status"), "CREATED");
        limits.setMerchantsLimits(false, merch);
    }

    public void clientInfo(){
//        String body = "{\n" +
//                "  \"customer_id\": \"10303233\",\n" +
//                "  \"amount\": 2000,\n" +
//                "  \"pan\": \""+Cards_data.getData(sender_card,Card_param.pan)+"\",\n" +
//                "  \"iban\": \"UA953348510000026201112609803\",\n" +
//                "  \"kind\": \"EXTERNAL_BANK\",\n" +
//                "  \"direction\": \"EXPENSE\"\n" +
//                "}";
        String body = "{\n" +
                "\"customer_id\": \"10303233\",\n" +
                "\"amount\": 1,\n" +
                //"\"ekb_id\": 1535617," +
                "\"kind\": \"EXTERNAL_BANK\",\n" +
                "\"direction\": \"EXPENSE\"\n" +
                "}";
        limits.clientsInfoLimits(body);
    }

    public void relatives(){
        String body = "{\n" +
                "  \"payer\": {\n" +
                "    \"account\": \"UA903348510000026204404198194\",\n" +
                "    \"client_id\": \"1\"\n" +
                "  },\n" +
//                "  \"receiver\": {\n" +
//                "    \"account\": \"string3\",\n" +
//                "    \"client_id\": \"string4\"\n" +
//                "  },\n" +
                "  \"amount\": 11,\n" +
                "  \"correlation_id\": \"3fa85f64-5717-4562-b3fc-2c963f66af11\",\n" +
                "  \"status\": \"FAILED\"\n" +
                "}";
        limits.relatives(body);
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

    void success_a2c(int summ){
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
                "        \"value\": \""+ Cards_data.getData(reciever_card,Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "    }," +
                "\"payer\":{\n" +
                "\t\t\"source\":\"IBAN\",\n" +
                "\t\t\"value\":\"UA613348510000026203405297856\"\n" +
                "\t}";
        A2C a2c = new A2C(body);
    }

    void fail_a2c(int summ){
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
                "        \"value\": \""+ Cards_data.getData(reciever_card,Card_param.pan)+"\",\n" +
                "        \"recipientFirstName\": \"FirstName\",\n" +
                "        \"recipientLastName\": \"LastName\"\n" +
                "    }," +
                "\"payer\":{\n" +
                "\t\t\"source\":\"IBAN\",\n" +
                "\t\t\"value\":\"UA613348510000026203405297856\"\n" +
                "\t}";
            A2C a2c = new A2C();
            a2c.setBodyRequest(body);
            a2c.setExpectedStatus(400);
            a2c.makeTrans();
    }

    void success_c2c(int summ){
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": 0,\n" +
                "    \"currency\": 980,\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(sender_card),"EXTERNAL","test")+"," +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(reciever_card,Card_param.pan)+"\"\n" +
                "    },\n" +
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
                ThreeDS.threeDS_2_1_0;

        C2C c2c = new C2C(body, true);
    }

    void fail_c2c(int summ){
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": 0,\n" +
                "    \"currency\": 980,\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(sender_card),"EXTERNAL","test")+"," +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(reciever_card,Card_param.pan)+"\"\n" +
                "    },\n" +
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
                ThreeDS.threeDS_2_1_0;
        C2C c2c = new C2C();
        c2c.setBodyRequest(body);
        c2c.setExpectedStatus(400);
        c2c.setThreeDS(0);
        c2c.makeTrans();
    }

    void success_c2a(int summ){
        String body = "\"amount\": "+summ+",\n" +
                "    \"commission\": 0,\n" +
                "    \"currency\": 980,\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(sender_card),"EXTERNAL","test")+"," +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA213223130000026007233566001\"\n" +
                "    }," +
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
                ThreeDS.threeDS_2_1_0;
        C2A c2a = new C2A(body,true);
    }

    void fail_c2a(int summ){
        String body = " \"amount\": "+summ+",\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"c2a - пумб мастер\",\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(sender_card),"EXTERNAL","test")+"," +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA213223130000026007233566001\"\n" +
                "    }," +
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
                ThreeDS.threeDS_2_1_0;

        C2A c2a = new C2A();
        c2a.setBodyRequest(body);
        c2a.setExpectedStatus(400);
        c2a.setThreeDS(0);
        c2a.makeTrans();
    }

    void bd_update(String sql) throws SQLException {
        BDpostgre.updateSQL(sql);
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
        bd_update("update limits.entries x set status='"+status+"' where card_id in ((SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"'),(SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"'))");
    }

    void delete_trans() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        bd_update("delete FROM limits.entries x where card_id in ((SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(sender_card,Card_param.token)+"'),(SELECT x.id FROM cards.cards x WHERE itm_token  = '"+ Cards_data.getData(reciever_card,Card_param.token)+"'))");
    }

    void registrA2Ctrans(){
        String body = "\"amount\": 3000100,\n" +
                "    \"currency\": 980,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.MONO_MC, Card_param.pan)+"\"\n" +
                "    }," +
                "\"payer\":{\n" +
                "\t\t\"source\":\"IBAN\",\n" +
                "\t\t\"value\":\"UA093348510000018328000001006\"\n" +
                "\t}";
        Trans_token_payhub token = new Trans_token_payhub(true);
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body("{\n" +
                        "    \"operation_id\": \"" + UUID.randomUUID() + "\",\n" +
                        "    \"external_id\": \"" + UUID.randomUUID() + "\",\n" +
                        body +
                        "}")
                .when()
                .post(token.getHost()+"/transactions/a2c")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Регистрация A2C транзакции - " + response);
    }

    public void IB_entries_positive() throws SQLException {
        String body = "";
        ResultSet DBres;
        String condition;
        String month = Time_helper.getTime("yyyy-MM-");
        String day = Time_helper.getTime("yyyy-MM-dd");

        //Mandatory fields MONTH
        body = "{\n" +
                "  \"sirius_id\": \"477702\",\n" +
                "  \"period\": \"MONTH\"\n" +
                "}";
        limits.getIb_entries(body);
        condition = "where client_id = 477702 and created_at > '"+month+"01'";
        DBres = getDBrecords(condition, false);
        while (DBres.next()){
            Assert.assertTrue(limits.getResponse().contains(DBres.getString("correlation_id")));
        }
        DBres = getDBrecords(condition, true);
        DBres.next();
        Assert.assertTrue(limits.getResponse().contains("total_amount\":"+DBres.getInt(1)+","));

        //Mandatory fields DAY
        body = "{\n" +
                "  \"sirius_id\": \"477702\",\n" +
                "  \"period\": \"DAY\"\n" +
                "}";
        limits.getIb_entries(body);
        condition = "where client_id = 477702 and created_at > '"+day+"'";
        DBres = getDBrecords(condition, false);
        while (DBres.next()){
            Assert.assertTrue(limits.getResponse().contains(DBres.getString("correlation_id")));
        }
        DBres = getDBrecords(condition, true);
        DBres.next();
        Assert.assertTrue(limits.getResponse().contains("total_amount\":"+DBres.getInt(1)+","));

        //Field date
        body = "{\n" +
                "  \"sirius_id\": \"477702\",\n" +
                "  \"period\": \"DAY\",\n" +
                "  \"date\": \"2024-07-10\""+
                "}";
        limits.getIb_entries(body);
        condition = "where client_id = 477702 and created_at between '2024-07-10' and '2024-07-11'";
        DBres = getDBrecords(condition, false);
        while (DBres.next()){
            Assert.assertTrue(limits.getResponse().contains(DBres.getString("correlation_id")));
        }
        DBres = getDBrecords(condition, true);
        DBres.next();
        Assert.assertTrue(limits.getResponse().contains("total_amount\":"+DBres.getInt(1)+","));

        body = "{\n" +
                "  \"sirius_id\": \"477702\",\n" +
                "  \"period\": \"MONTH\",\n" +
                "  \"date\": \"2024-07-10\""+
                "}";
        limits.getIb_entries(body);
        condition = "where client_id = 477702 and created_at between '"+month+"01' and '2024-07-11'";
        DBres = getDBrecords(condition, false);
        while (DBres.next()){
            Assert.assertTrue(limits.getResponse().contains(DBres.getString("correlation_id")));
        }
        DBres = getDBrecords(condition, true);
        DBres.next();
        Assert.assertTrue(limits.getResponse().contains("total_amount\":"+DBres.getInt(1)+","));

        //Field channel
        List.of("A2A", "A2C", "C2A", "C2C", "P4P_DEBIT", "P4P_CREDIT", "PGA", "MOBILE_PAY")
                .forEach( x-> {
                    String bodyReq = "{\n" +
                            "  \"sirius_id\": \"477702\",\n" +
                            "  \"period\": \"MONTH\",\n" +
                            "  \"channel\": [\n" +
                            "    \""+x+"\"\n" +
                            "  ]"+
                        "}";
                    limits.getIb_entries(bodyReq);
                    ResultSet res = getDBrecords("where client_id = 477702 and created_at > '"+month+"01' and channel = '"+x+"'", false);
                    try {
                        while (res.next()){
                            Assert.assertTrue(limits.getResponse().contains(res.getString("correlation_id")));
                        }
                        res = getDBrecords("where client_id = 477702 and created_at > '"+month+"01' and channel = '"+x+"'", true);
                        res.next();
                        Assert.assertTrue(limits.getResponse().contains("total_amount\":"+res.getInt(1)+","));
                    }catch (Throwable e){
                        throw new RuntimeException(e);
                    }
                });

        //Field status
        List.of("PROCESSING", "PROCESSED", "FAILED")
                .forEach( x-> {
                    String bodyReq = "{\n" +
                        "  \"sirius_id\": \"477702\",\n" +
                        "  \"period\": \"MONTH\",\n" +
                        "  \"status\": [\n" +
                        "    \""+x+"\"\n" +
                        "  ]"+
                        "}";
                    limits.getIb_entries(bodyReq);
                    ResultSet res = getDBrecords("where client_id = 477702 and created_at > '"+month+"01' and status = '"+x+"'", false);
                    try {
                        while (res.next()){
                            Assert.assertTrue(limits.getResponse().contains(res.getString("correlation_id")));
                        }
                        res = getDBrecords("where client_id = 477702 and created_at > '"+month+"01' and status = '"+x+"'", true);
                        res.next();
                        Assert.assertTrue(limits.getResponse().contains("total_amount\":"+res.getInt(1)+","));
                    }catch (Throwable e){
                        throw new RuntimeException(e);
                    }
                });

        //Field direction
        List.of("PROFIT", "EXPENSE")
                .forEach( x-> {
                    String bodyReq = "{\n" +
                            "  \"sirius_id\": \"477702\",\n" +
                            "  \"period\": \"MONTH\",\n" +
                            "  \"direction\": \""+x+"\"\n" +
                            "}";
                    limits.getIb_entries(bodyReq);
                    ResultSet res = getDBrecords("where client_id = 477702 and created_at > '"+month+"01' and direction = '"+x+"'", false);
                    try {
                        while (res.next()){
                            Assert.assertTrue(limits.getResponse().contains(res.getString("correlation_id")));
                        }
                        res = getDBrecords("where client_id = 477702 and created_at > '"+month+"01' and direction = '"+x+"'", true);
                        res.next();
                        Assert.assertTrue(limits.getResponse().contains("total_amount\":"+res.getInt(1)+","));
                    }catch (Throwable e){
                        throw new RuntimeException(e);
                    }
                });

        //Field kind
        List.of("DIRECT_MONEY_TRANS", "FUIB_TO_FUIB", "EXTERNAL_BANK", "FULL_IDENT_DIRECT", "FULL_IDENT_FUIB_TO_FUIB", "FUIB_RELATIVES_CUR", "FUIB_RELATIVES_UAH")
                .forEach( x-> {
                    String bodyReq = "{\n" +
                            "  \"sirius_id\": \"477702\",\n" +
                            "  \"period\": \"MONTH\",\n" +
                            "  \"kind\": [\n" +
                            "    \""+x+"\"\n" +
                            "  ]"+
                            "}";
                    limits.getIb_entries(bodyReq);
                    ResultSet res = getDBrecords("where client_id = 477702 and created_at > '"+month+"01' and kind = '"+x+"'", false);
                    try {
                        while (res.next()){
                            Assert.assertTrue(limits.getResponse().contains(res.getString("correlation_id")));
                        }
                        res = getDBrecords("where client_id = 477702 and created_at > '"+month+"01' and kind = '"+x+"'", true);
                        res.next();
                        Assert.assertTrue(limits.getResponse().contains("total_amount\":"+res.getInt(1)+","));
                    }catch (Throwable e){
                        throw new RuntimeException(e);
                    }
                });

        //Field account
        body = "{\n" +
                "  \"sirius_id\": \"477702\",\n" +
                "  \"period\": \"MONTH\",\n" +
                "  \"account\": \"UA043348510000026201404192374\""+
                "}";
        limits.getIb_entries(body);
        condition = "where client_id = 477702 and created_at > '"+month+"01' and account = 'UA043348510000026201404192374'";
        DBres = getDBrecords(condition, false);
        while (DBres.next()){
            Assert.assertTrue(limits.getResponse().contains(DBres.getString("correlation_id")));
        }
        DBres = getDBrecords(condition+" and account = 'UA043348510000026201404192374'", true);
        DBres.next();
        Assert.assertTrue(limits.getResponse().contains("total_amount\":"+DBres.getInt(1)+","));
    }

    public void mirinda_limits(){
        limits.getMirindaLimits("477702");
    }

    private ResultSet getDBrecords(String condition, boolean sumAmount){
        ResultSet res;
        String sql = "";
        if (sumAmount){
            sql = "SELECT sum(amount) FROM limits.ib_entries ";
        }else {
            sql = "SELECT * FROM limits.ib_entries ";
        }
        try {
            res = BDpostgre.selectSQL(sql+condition);
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        return res;
    }

//    @BeforeTest
//    void open() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//        BDpostgre.BDpostgre("limiter", "dev","password");
//    }

    @AfterTest
    void close() throws SQLException {
        BDpostgre.closeConn();
    }
}
