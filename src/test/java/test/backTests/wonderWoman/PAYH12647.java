package test.backTests.wonderWoman;

import com.mongodb.BasicDBObject;
import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.ronan.A2CRonan;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.example.qaTransactionTeam.backEnd.wonderWoman.WonderWoman;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeoutException;

public class PAYH12647 extends BaseTest {
    //НЕ корректный статус транзакции (wonder-woman)

    @Test
    void statusSuccess() throws NoSuchAlgorithmException, IOException, URISyntaxException, InterruptedException, KeyManagementException, TimeoutException {
        logStartTest("statusSuccess");
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
        StringBuffer dateNow = new StringBuffer(simpleDateFormat);
        dateNow.replace(10,11,"T");
        dateNow.insert(dateNow.length(),"Z");
        String hours = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        StringBuffer dateTo = new StringBuffer(dateNow);
        dateTo.replace(11,13, String.valueOf(Integer.parseInt(hours)+1));

        A2CRonan a2c = new A2CRonan();
        a2c.A2C(Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        changeBDdata(a2c.reference,"A","1");
        WonderWoman wonderWoman = new WonderWoman();
        wonderWoman.getStatus(a2c.acqId,dateNow.toString(),dateTo.toString());
        JSONArray jsonArr = new JSONObject(wonderWoman.getResponse()).getJSONArray("data");
        for (int i = 0; i<jsonArr.length(); i++){
            JSONObject jsonObject = jsonArr.getJSONObject(i);
            String rrn = jsonObject.getString("reference");
            if(rrn.equals(a2c.reference)){
                Assert.assertEquals(jsonObject.getString("session_state"),"SUCCESS");
            }
        }
        logFinishTest("statusSuccess");
    }

    @Test
    void statusFail() throws NoSuchAlgorithmException, IOException, URISyntaxException, InterruptedException, KeyManagementException, TimeoutException {
        logStartTest("statusFail");
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
        StringBuffer dateNow = new StringBuffer(simpleDateFormat);
        dateNow.replace(10,11,"T");
        dateNow.insert(dateNow.length(),"Z");
        String hours = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        StringBuffer dateTo = new StringBuffer(dateNow);
        dateTo.replace(11,13, String.valueOf(Integer.parseInt(hours)+1));

        A2CRonan a2c = new A2CRonan();
        a2c.A2C(Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        changeBDdata(a2c.reference,"B","2");
        WonderWoman wonderWoman = new WonderWoman();
        wonderWoman.getStatus(a2c.acqId,dateNow.toString(),dateTo.toString());
        JSONArray jsonArr = new JSONObject(wonderWoman.getResponse()).getJSONArray("data");
        for (int i = 0; i<jsonArr.length(); i++){
            JSONObject jsonObject = jsonArr.getJSONObject(i);
            String rrn = jsonObject.getString("reference");
            if(rrn.equals(a2c.reference)){
                Assert.assertEquals(jsonObject.getString("session_state"),"FAIL");
            }
        }
        logFinishTest("statusFail");
    }

    @Test
    void statusPending() throws NoSuchAlgorithmException, IOException, URISyntaxException, InterruptedException, KeyManagementException, TimeoutException {
        logStartTest("statusPending");
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
        StringBuffer dateNow = new StringBuffer(simpleDateFormat);
        dateNow.replace(10,11,"T");
        dateNow.insert(dateNow.length(),"Z");
        String hours = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        StringBuffer dateTo = new StringBuffer(dateNow);
        dateTo.replace(11,13, String.valueOf(Integer.parseInt(hours)+1));

        A2CRonan a2c = new A2CRonan();
        a2c.A2C(Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        changeBDdata(a2c.reference,"B","1");
        WonderWoman wonderWoman = new WonderWoman();
        wonderWoman.getStatus(a2c.acqId,dateNow.toString(),dateTo.toString());
        JSONArray jsonArr = new JSONObject(wonderWoman.getResponse()).getJSONArray("data");
        for (int i = 0; i<jsonArr.length(); i++){
            JSONObject jsonObject = jsonArr.getJSONObject(i);
            String rrn = jsonObject.getString("reference");
            if(rrn.equals(a2c.reference)){
                Assert.assertEquals(jsonObject.getString("session_state"),"PENDING");
            }
        }
        logFinishTest("statusPending");
    }

    private void changeBDdata(String reference, String actn, String msgf){
        BasicDBObject query = new BasicDBObject();
        query.put("rrf", reference);
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("actn", actn);
        newDocument.put("msgf", msgf);
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);
        BDMongo.getCollection().update(query,updateObject);
    }

    @BeforeTest
    void openConn(){
        BDMongo.BDMongo("vmtdb");
        BDMongo.openCollection("vmtdetails");
    }

    @AfterTest
    void closeConn() throws SQLException {
        BDMongo.closeConn();
    }
}
