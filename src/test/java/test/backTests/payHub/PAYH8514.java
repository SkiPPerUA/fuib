package test.backTests.payHub;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class PAYH8514 extends BaseTest {
    //Провести тестирование кеша транзакций (transaction_id & external_id)

    int statusCode = 200;
    String trans_id;
    String ext_id;
    Trans_token_payhub token;
    JSONObject json;
    String response;

    @BeforeTest
    public void createToken() throws JSONException {
        token = new Trans_token_payhub();
    }

    @Test
    public void testA2C() throws JSONException, InterruptedException {

        logStartTest("testA2CPan");

        registerA2C();
        for(int i = 0; i < 2; i++) {
            statusByTransId("a2c");
            statusByExternalId("a2c");
            statusByAllParam("a2c");
            statusCode = 400;
            logger.info("Ожидаемый статус 400");
            String oldTrans = trans_id;
            trans_id = "3fc880d9-3395-4555-a21b-0de990399999";
            statusByTransId("a2c");
            checkCode();
            statusByAllParam("a2c");
            checkCode();
            trans_id = oldTrans;
            String oldExternal = ext_id;
            ext_id = "test1110011";
            statusByExternalId("a2c");
            checkCode();
            statusByAllParam("a2c");
            checkCode();
            trans_id = "3fc880d9-3395-4555-a21b-0de990399999";
            statusByAllParam("a2c");
            checkCode();
            trans_id = oldTrans;
            ext_id = oldExternal;
            statusCode = 200;
            logger.info("Ожидаемый статус 200");
            statusByTransId("a2c");
            statusByExternalId("a2c");
            statusByAllParam("a2c");

            if (i == 0) {
                Thread.sleep(305000);
            }
        }

        logFinishTest("testA2CPan");
    }

    @Test
    public void testC2A() throws JSONException, InterruptedException {

        logStartTest("testC2A");

        registerC2A();
        for(int i = 0; i < 2; i++) {
        statusByTransId("c2a");
        statusByExternalId("c2a");
        statusByAllParam("c2a");
        statusCode = 400;
        logger.info("Ожидаемый статус 400");
        String oldTrans = trans_id;
        trans_id = "3fc880d9-3395-4555-a21b-0de990399999";
        statusByTransId("c2a");
            checkCode();
        statusByAllParam("c2a");
            checkCode();
        trans_id = oldTrans;
        String oldExternal = ext_id;
        ext_id = "test1110011";
        statusByExternalId("c2a");
            checkCode();
        statusByAllParam("c2a");
            checkCode();
        trans_id = "3fc880d9-3395-4555-a21b-0de990399999";
        statusByAllParam("c2a");
            checkCode();
        trans_id = oldTrans;
        ext_id = oldExternal;
        statusCode = 200;
        logger.info("Ожидаемый статус 200");
        statusByTransId("c2a");
        statusByExternalId("c2a");
        statusByAllParam("c2a");

            if (i == 0) {
                Thread.sleep(305000);
            }
        }

        logFinishTest("testC2A");
    }

    @Test
    public void regressC2C() throws JSONException, InterruptedException {

        logStartTest("regressC2C");

            registerC2С();
            for(int i = 0; i < 2; i++) {
            statusByTransId("c2c");
            statusCode = 400;
            logger.info("Ожидаемый статус 400");
            String oldTrans = trans_id;
            trans_id = "3fc880d9-3395-4555-a21b-0de990399999";
            statusByTransId("c2c");
                checkCode();
            trans_id = oldTrans;
            statusCode = 200;
            logger.info("Ожидаемый статус 200");
            statusByTransId("c2c");
                if (i == 0) {
                    Thread.sleep(305000);
                }
            }

        logFinishTest("regressC2C");
    }

    private void registerA2C() throws JSONException {
        ext_id = UUID.randomUUID().toString();
        String body = "{\n" +
                "    \"operation_id\": \"vlad\",\n" +
                "    \"external_id\": \""+ ext_id+"\",\n" +
                "    \"amount\": 100,\n" +
                "    \"commission\": 20,\n" +
                "    \"currency\": 980,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\"\n" +
                "    }\n" +
                "}";

        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs1.PAYHUB_HOST +"/transactions/a2c")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JSONObject json = new JSONObject(response).getJSONObject("data");
        trans_id = json.getString("transaction_id");
        logger.info("External = "+ext_id);
        logger.info("Регитсрация А2С - "+response);

    }

    private void registerC2A() throws JSONException {
        ext_id = UUID.randomUUID().toString();
        String body = "{\n" +
                "    \"operation_id\": \"vlad1\",\n" +
                "    \"external_id\": \""+ext_id+"\",\n" +
                "    \"amount\": 100,\n" +
                "    \"commission\": 20,\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "        \"expire\": \"2212\",\n" +
                "        \"cvv\": \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"test\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs1.PAYHUB_HOST +"/transactions/c2a")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JSONObject json = new JSONObject(response).getJSONObject("data");
        trans_id = json.getString("transaction_id");
        logger.info("External = "+ext_id);
        logger.info("Регистрация С2A - "+response);

    }

    private void registerC2С() throws JSONException {

        ext_id = UUID.randomUUID().toString();
        String body = "{\n" +
                "    \"operation_id\": \""+UUID.randomUUID().toString()+"\",\n" +
                "    \"external_id\": \""+ext_id+"\",\n" +
                "    \"amount\": 100,\n" +
                "    \"commission\": 10,\n" +
                "    \"currency\": 980,\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"WALLET\",\n" +
                "        \"value\": \"7c86feb6-1650-43d3-83cb-0e432356391a\",\n" +
                "        \"client\": {\n" +
                "            \"source\": \"EXTERNAL\",\n" +
                "            \"id\": \"0\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data1.getData(Card.FUIB_VISA, Card_param.pan)+"\"\n" +
                "    }\n" +
                "}";

        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs1.PAYHUB_HOST +"/transactions/c2c")
                .then()
                .extract().response().asString();

        JSONObject json = new JSONObject(response).getJSONObject("data");
        trans_id = json.getString("transaction_id");
        logger.info("External = "+ext_id);
        logger.info("Регистрация С2C - "+response);

    }

    private void statusByTransId(String typeTrans){
         response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(Configs1.PAYHUB_HOST +"/transactions/"+typeTrans+"?transaction_id="+trans_id)
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Статус "+typeTrans+" по ТрансИд - "+response);
    }

    private void statusByExternalId(String typeTrans){
         response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(Configs1.PAYHUB_HOST +"/transactions/"+typeTrans+"?external_id="+ext_id)
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Статус "+typeTrans+" по ЕкстерналИд - "+response);
    }

    private void statusByAllParam(String typeTrans){
         response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(Configs1.PAYHUB_HOST +"/transactions/"+typeTrans+"?transaction_id="+trans_id+"&external_id="+ext_id)
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Статус "+typeTrans+" по всем параметрам - "+response);
    }

    private void checkCode() throws JSONException {
        json = new JSONObject(response).getJSONObject("error");
        Assert.assertEquals(json.getString("code"),"NOT_FOUND");
    }
}
