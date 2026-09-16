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
    protected boolean require_3ds_data = false;

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
            debitId = response.then().extract().response().jsonPath().getString("id");
        }
        try {
            require_3ds_data = response.jsonPath().getBoolean("require_3ds_data");
        }catch (Throwable e){
            //logger.info("require_3ds_data не найден");
        }
    }

    public void confirmTransfers(){
        String body = "";
        if (!require_3ds_data){
            body = "{\"transfer_id\": \""+transactionId+"\"}";
        }else {
            body = "{"+ThreeDS.threeDS_2_2_0+"}";
        }
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
        }else if (type.equals("a2c legion")) {
            url = token.getHost()+"/transactions/c2c/3ds";
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

    public void agreeHidden(String transactionId, String threed_data){
        String url;
        String body = "{\n" +
                "    \"transaction_id\": \""+transactionId+"\",\n" +
                "    \"threed_data\": \""+threed_data+"\"\n" +
                "}";
        if(type.equals("c4c")) {
            url = token.getHost()+"/operations/deferred/c2c/"+transactionId+"/3ds";
        }else if (type.equals("p4p_new")) {
            transactionId = debitId;
            body = "{\n" +
                    "    \"c_res\": \"vladTest\"\n" +
                    "}";
            url = token.getHost()+"/p4p-transfers/"+transactionId+"/3ds";
        }else if (type.equals("c2a legion")){
            url = token.getHost()+"/transactions/c2a/3ds";
        }else {
            url = token.getHost()+"/transactions/"+type+"/3ds";
        }
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .put(url);
        logger.info("Подтверждение agreeHidden для "+type+" транзакции - "+response.then().extract().response().asString());
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
        }else if(type.contains("a2a") || type.contains("legion")){
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
        logger.info("Статус "+type+" транзакции {"+transactionId+"} - "+resp);
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
        }
            String status = "";
            if (type.contains("legion") || type.equals("c4c")) {
                status = json.getString("status");
            } else {
                status = json.getJSONObject("data").getString("status");
            }
            if (status.equals("PENDING") && !type.equals("c4c")) {
                try {
                    url = json.getJSONObject("data").getJSONObject("3ds_info").getString("acs_url");
                    if (json.getJSONObject("data").getJSONObject("3ds_info").getString("threed_mode").equals("THREED_TWO_FRAME_HIDDEN")) {
                        creq = json.getJSONObject("data").getJSONObject("3ds_info").getString("hidden_frame_data");
                        Map<String, String> body = new HashMap<>();
                        body.put("threeDSMethodData", creq);
                        String res = given()
                                .contentType(ContentType.URLENC)
                                .params(body)
                                .when()
                                .post(url)
                                .then().extract().response().asString();

                        String threed_data = res.substring(res.indexOf("value=\"")+7,res.indexOf("value=\"")+91);
                        agreeHidden(transactionId,threed_data);
                        Map data = wait_hidden_frame(transactionId);
                        setTransactionId(transactionId);
                        url = (String) data.get("acs_url");
                        creq = (String) data.get("c_req");
                    }else {
                        url = json.getJSONObject("data").getJSONObject("3ds_info").getString("acs_url");
                        creq = json.getJSONObject("data").getJSONObject("3ds_info").getString("c_req");
                    }
                } catch (JSONException | InterruptedException e) {
                    try {
                        Thread.sleep(wait);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    get_theeDS_data();
                }
            }else if (status.equals("PENDING") && type.equals("c4c")) {
                try {
                    url = json.getJSONObject("threed_info").getString("acs_url");
                    if (json.getJSONObject("threed_info").getString("threed_mode").equals("THREED_TWO_FRAME_HIDDEN")) {
                        creq = json.getJSONObject("threed_info").getString("hidden_frame_data");
                        Map<String, String> body = new HashMap<>();
                        body.put("threeDSMethodData", creq);
                        String res = given()
                                .contentType(ContentType.URLENC)
                                .params(body)
                                .when()
                                .post(url)
                                .then().extract().response().asString();

                        String threed_data = res.substring(res.indexOf("value=\"")+7,res.indexOf("value=\"")+91);
                        agreeHidden(transactionId,threed_data);
                        Map data = wait_hidden_frame(transactionId);
                        setTransactionId(transactionId);
                        url = (String) data.get("acs_url");
                        creq = (String) data.get("c_req");
                    }else {
                        url = json.getJSONObject("threed_info").getString("acs_url");
                        creq = json.getJSONObject("threed_info").getString("c_req");
                    }
                } catch (JSONException | InterruptedException e) {
                    try {
                        Thread.sleep(wait);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    get_theeDS_data();
                }
            }else if (status.equals("PROCESSED") || status.equals("PROCESSING") || status.equals("ACTIVE")){
                //Транка успешна - поиск 3дс - прекращать
            }else if (status.equals("3DS_REQUIRED")){
                if (json.getJSONObject("threed_options").getString("threed_mode").equals("THREED_TWO_HIDDEN")){
                    try {
                        url = json.getJSONObject("threed_options").getString("acs_url");
                        creq = json.getJSONObject("threed_options").getString("hidden_frame_data");
                        Map<String, String> body = new HashMap<>();
                        body.put("threeDSMethodData", creq);
                        String res = given()
                                .contentType(ContentType.URLENC)
                                .params(body)
                                .when()
                                .post(url)
                                .then().extract().response().asString();

                        String threed_data = res.substring(res.indexOf("value=\"")+7,res.indexOf("value=\"")+91);
                        agreeHidden(transactionId,threed_data);
                        Map data = wait_hidden_frame(transactionId);
                        setTransactionId(transactionId);
                        url = (String) data.get("acs_url");
                        creq = (String) data.get("c_req");
                    } catch (JSONException e) {
                        try {
                            Thread.sleep(wait);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        get_theeDS_data();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    try {
                        url = json.getJSONObject("threed_options").getString("acs_url");
                        creq = json.getJSONObject("threed_options").getString("c_req");
                    } catch (JSONException e) {
                        try {
                            Thread.sleep(wait);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        get_theeDS_data();
                    }
                }
            }else {
                Assert.fail("Статус транзакции "+type+" ("+transactionId+") = "+status);
            }
        //}
    }

    private Map<String,String> wait_hidden_frame(String trans) throws InterruptedException {
        Thread.sleep(5000);
        String creq = "";
        String url = "";
        Map<String,String> data = new HashMap<>();
        JSONObject json = new JSONObject(getStatus(trans));
        creq = json.getJSONObject("data").getJSONObject("3ds_info").getString("c_req");
        url = json.getJSONObject("data").getJSONObject("3ds_info").getString("acs_url");
        data.put("acs_url", url);
        data.put("c_req", creq);
        return data;
    }

    public String getDebitId() {
        return debitId;
    }

    public Transaction_payhub setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public void getDetails(String transferId, String sender_ekb_id){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .header("X-Flow-ID", "11")
                .when()
                .get(token.getHost()+"/transfers/"+transferId+"/details?sender_id="+sender_ekb_id);
        resp = response.then().extract().response().asString();
        logger.info("getDetails [" + transferId + "] -> " + resp);
    }
}
