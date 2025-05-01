package test.backTests.itm;



import java.sql.SQLException;
import java.util.UUID;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.itm.*;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.A2C;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.C2A;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.utils.BDMongo;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MT2561 extends BaseTest {
    //Расширить апи получения статуса платежа (Для повторения операции)
    //добавление полей extTransId та expDate

    @BeforeTest
    public void connToDB(){
       BDMongo.BDMongo("vmtdb");

    }

    @Test
    public void createTransWithExtTransIdA2C() throws JSONException {

        logStartTest("createTransWithExtTransIdA2C");

        Map<String,String> body = new HashMap<>();
        body.put("senderAccount","26206111172132");
        body.put("amount","100");
        body.put("operationId","1");
        body.put("customFee","0");
        body.put("receiverCardNumber","?C7JN8U9M0PTWCM4");

        A2C a2c = new A2C(body);
        String A2Csession = a2c.getSessionId();

        Object dataFromMongo = checkMongoTransfers(A2Csession).get("extTransId");
        Assert.assertEquals(dataFromMongo.toString(),"TestVlad");

        logFinishTest("createTransWithExtTransIdA2C");

    }

    @Test
    public void createTransWithExtTransIdC2A() throws JSONException {

        logStartTest("createTransWithExtTransIdC2A");

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber","5355280001301447");
        body.put("amount","100");
        body.put("operationId","2");
        body.put("customFee","0");
        body.put("receiverAccount","26206111172132");
        body.put("expDate","2212");
        body.put("ip","127.0.0.1");
        body.put("fingerprint","tests25");

        C2A c2a = new C2A(body, Cards_data.getData(Card.FUIB_MC, Card_param.token));
        String C2Asession = c2a.getSessionId();

        Object dataFromMongo = checkMongoTransfers(C2Asession).get("extTransId");
        Assert.assertEquals(dataFromMongo.toString(),"TestVlad");

        logFinishTest("createTransWithExtTransIdC2A");
    }

    @Test
    public void createTransWithExtDateC2A() throws JSONException {

        logStartTest("createTransWithExtDateC2A");

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber","5355280001301447");
        body.put("amount","100");
        body.put("operationId","2");
        body.put("customFee","0");
        body.put("receiverAccount","26206111172132");
        body.put("expDate","2212");
        body.put("ip","127.0.0.1");
        body.put("fingerprint","tests25");

        C2A c2a = new C2A(body,Cards_data.getData(Card.FUIB_MC, Card_param.token));
        String C2Asession = c2a.getSessionId();

        Object dataFromMongo = checkMongoTransfers(C2Asession).get("expDate");
        Assert.assertEquals(dataFromMongo.toString(),"2212");

        logFinishTest("createTransWithExtDateC2A");
    }

    @Test
    public void findDatailsByStan() throws JSONException {

        logStartTest("findDatailsByStan");

        GetTransDetByStan detailByStan = new GetTransDetByStan();
        detailByStan.GetDetailsByStan(findStan());

        logFinishTest("findDatailsByStan");
    }

    @Test
    public void findDatailsByExternal() throws JSONException {

        logStartTest("findDatailsByExternal");

        String testExternal = UUID.randomUUID().toString();

        Map<String,String> body = new HashMap<>();
        body.put("senderAccount","26201112609803");
        body.put("amount","100");
        body.put("operationId","1");
        body.put("customFee","0");
        body.put("receiverCardNumber","?C7JN8U9M0PTWCM4");

        makeA2CwithExternal(testExternal,body);

        GetTransDetByExternal details = new GetTransDetByExternal();
        details.GetDetailsByExternal(testExternal);
        System.out.println(details.getResponse());

        logFinishTest("findDatailsByExternal");
    }

    @AfterTest
    public void closeDBConn() throws SQLException {
        BDMongo.closeConn();
    }

    private DBObject checkMongoTransfers(String session){
        BDMongo.openCollection("transfers");
        BDMongo.searchParam().put("reference",session);

        return BDMongo.getCollection().find(BDMongo.searchParam()).next();

    }

    private void makeA2CwithExternal(String testExternal, Map body) throws JSONException {
        Trans_token_itm token = new Trans_token_itm();
        String urlRegistr = "https://tsystestapi.pumb.ua/vmt/api/register/"+token.getAcqID()+"";

        String req = given()
                .contentType(ContentType.URLENC)
                .header("Token", token.getToken())
                .header("extTransId",testExternal)
                .params(body)
                .when()
                .post(urlRegistr)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();

        JSONObject ob = new JSONObject(req);
        String session = ob.getString("sessionId");
        logger.info("Транзакция A2C - "+session);
        logger.info("External - "+testExternal);

        String urlFinish = "https://tsystestapi.pumb.ua/vmt/api/payment/"+token.getAcqID()+"/"+ session +"/";

        String req1 = given()
                .contentType(ContentType.URLENC)
                .when()
                .post(urlFinish)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();
    }

    private String findStan(){
        BDMongo.openCollection("vmtdetails");
        DBObject ob = BDMongo.getCollection().find()
                .sort(new BasicDBObject("_id",-1)).limit(1).next();
        StringBuffer stan = new StringBuffer(ob.get("stan").toString());
        stan.delete(1,2);
        stan.delete(11,stan.length());

        logger.info("Stan из vmtdetails - "+stan.toString());
        return stan.toString();
    }

}

