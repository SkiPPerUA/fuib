package org.example.qaTransactionTeam.backEnd.transaction;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class Transaction_payhub {
    protected static final Logger logger = Logger.getLogger(Transaction_payhub.class);
    protected Response response;
    protected String externalId;
    protected String transactionId;
    protected String debitId;
    protected String refundId;
    protected String type;
    protected int statusCode = 200;
    private int wait = 8000;
    protected String resp;
    protected boolean withOutConfirmation = false;
    protected int threeDS = -1;
    protected String url = "";
    protected String creq = "";
    protected String valid_type = "";
    protected Auth_token token;

    protected void createTrans(String body) throws IOException {
        String operationId = Uuid_helper.generate_uuid();
        externalId = Uuid_helper.generate_uuid();

        String url;
        if(type.equals("c4c")) {
            url = token.getHost() + "/operations/deferred/c2c";
        }else if (type.equals("a2c_tax")){
            url = token.getHost() + "/transactions/ta2c";
        }else {
            url = token.getHost() + "/transactions/" + type;
        }

        String body1 = "";
        if(withOutConfirmation || threeDS == 0){
            body1 = "{\n" +
                    "    \"operation_id\": \"" + operationId + "\",\n" +
                    "    \"external_id\": \"" + externalId + "\",\n" +
                    "    \"without_confirmation\": true,\n" +
                    body +
                    "}";
        }else {
            body1 = "{\n" +
                "    \"operation_id\": \"" + operationId + "\",\n" +
                "    \"external_id\": \"" + externalId + "\",\n" +
                body +
                "}";}

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body(body1)
                .when()
                .post(url);
        resp = response.then().extract().response().asString();
        logger.info("Выполнение " + type + " транзакции - " + resp);
        Assert.assertEquals(response.getStatusCode(),statusCode);

        if(type.equals("c4c")){
            transactionId = response.then().extract().response().jsonPath().getString("id");
        }else {
            transactionId = response.then().extract().response().jsonPath().getString("data.transaction_id");
        }
    }

    public void initTransfers(String body){
        String url;
        if(type.equals("p4p_new")) {
            url = "/p4p-transfers";
        }else {
            url = "/transfers";
        }

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .body(body)
                .when()
                .post(token.getHost()+url);
        logger.info("Init "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
        transactionId = response.then().extract().response().jsonPath().getString("transfer_id");
        if(type.equals("p4p_new")) {
            debitId = response.then().extract().response().jsonPath().getString("id");;
        }
    }

    public void confirmTransfers(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .body("{\n" +
                        "   \"transfer_id\": \""+transactionId+"\"\n" +
                        "}")
                .when()
                .post(token.getHost()+"/transfers/"+transactionId);
        logger.info("Confirm - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void confirmTransfers(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .body(body)
                .when()
                .post(token.getHost()+"/transfers/"+transactionId);
        logger.info("Confirm - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void agree3DS(){
        String url;
        String body = "{\n" +
                "    \"transaction_id\": \""+transactionId+"\",\n" +
                "    \"c_res\": \"vladTest\"\n" +
                "}";
        if(type.equals("c4c")) {
            url = token.getHost()+"/operations/deferred/c2c/"+transactionId+"/3ds";
        }else if (type.equals("p4p_new")) {
            transactionId = debitId;
            body = "{\n" +
                    "    \"c_res\": \"vladTest\"\n" +
                    "}";
            url = token.getHost()+"/p4p-transfers/"+transactionId+"/3ds";
        }else {
            url = token.getHost()+"/transactions/"+type+"/3ds";
        }
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .put(url);
        logger.info("Подтверждение 3DS для "+type+" транзакции - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void agreeHidden(String transactionId, String ss){
        String url;
        String body = "{\n" +
                "    \"transaction_id\": \""+transactionId+"\",\n" +
                "    \"threed_data\": \""+ss+"\"\n" +
                "}";
        if(type.equals("c4c")) {
            url = token.getHost()+"/operations/deferred/c2c/"+transactionId+"/3ds";
        }else if (type.equals("p4p_new")) {
            transactionId = debitId;
            body = "{\n" +
                    "    \"c_res\": \"vladTest\"\n" +
                    "}";
            url = token.getHost()+"/p4p-transfers/"+transactionId+"/3ds";
        }else {
            url = token.getHost()+"/transactions/"+type+"/3ds";
        }
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .put(url);
        logger.info("Подтверждение 3DS для "+type+" транзакции - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void merchantsBalance(String type){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/transactions/"+type+"/balance");
        logger.info("Баланс мерчанта "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public String getStatus(String transactionId) {
        String url = "";
        if (type.equals("c4c")){
            url = "/operations/deferred/c2c?id=" + transactionId + "&details=true";
        }else if(type.equals("a2c_tax")){
            url = "/transactions/a2c?transaction_id=" + transactionId + "&details=true";
        }else if(type.contains("a2a")){
            url = "/transfers/"+transactionId;
        }else {
            url = "/transactions/" + type + "?transaction_id=" + transactionId + "&details=true";
        }
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .when()
                .get(token.getHost() + url);
        resp = response.then().extract().response().asString();
        logger.info("Статус "+type+" транзакции - "+resp);
        Assert.assertEquals(response.getStatusCode(),statusCode);
        return  resp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    protected void get_theeDS_data(){
        JSONObject json = new JSONObject(getStatus(transactionId));
        if (type.equals("c4c")) {
            try {
                valid_type = json.getString("validation_type");
            } catch (Throwable e) {
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                get_theeDS_data();
            }
            if (valid_type.equals("3DS")) {
                url = json.getJSONObject("threed_info").getString("acs_url");
                creq = json.getJSONObject("threed_info").getString("c_req");
            }
        }else {
            String status = json.getJSONObject("data").getString("status");
            if (status.equals("PENDING")) {
                try {
                    url = json.getJSONObject("data").getJSONObject("3ds_info").getString("acs_url");

//                    //hidden_frame
//                    creq = json.getJSONObject("data").getJSONObject("3ds_info").getString("hidden_frame_data");
//                    Map<String,String> body = new HashMap<>();
//                    body.put("threeDSMethodData",creq);
//                    String res = given()
//                            .contentType(ContentType.URLENC)
//                            .params(body)
//                            .when()
//                            .post(url)
//                            .then().extract().response().prettyPrint();
//
//                    json = new JSONObject(res);
//                    url = json.getJSONObject("data").getJSONObject("3ds_info").getString("acs_url");

                    creq = json.getJSONObject("data").getJSONObject("3ds_info").getString("c_req");

                } catch (JSONException e) {
                    try {
                        Thread.sleep(wait);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    get_theeDS_data();
                }
            }else if (status.equals("PROCESSED")){
                //Транка успешна - поиск 3дс - прекращать
            }else {
                Assert.fail("Статус транзакции "+type+" ("+transactionId+") = "+status);
            }
        }
    }

    public String getDebitId() {
        return debitId;
    }

    public Transaction_payhub setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }
}
