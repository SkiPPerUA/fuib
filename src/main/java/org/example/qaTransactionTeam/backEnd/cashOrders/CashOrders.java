package org.example.qaTransactionTeam.backEnd.cashOrders;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Admin_token;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.testng.Assert;

import java.util.Map;
import static io.restassured.RestAssured.given;

public class CashOrders {
    protected static final Logger logger = Logger.getLogger(CashOrders.class);
    private String url = "https://innsmouth.test-fuib.com/starlight/";
    private Response response;
    private int statusCode = 200;
    private String transaction_id;
    private Auth_token token = new Admin_token();

    public CashOrders(){
        RestAssured.useRelaxedHTTPSValidation();
    }

    public void getCommission(Map query){
        response = given()
                .header("X-Flow-ID","2871d87283")
                .header("Authorization", "Bearer 1")
                .header("x-frame-id", "0de939bd-77d7-49b9-a88c-f2473c43c98e")
                .queryParams(query)
                .when()
                .get(url+"commissions");
        logger.info("Get commission = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void getFrameConfigs(String x_frame_id){
        response = given()
                .header("X-Flow-ID","2871d87284")
                .header("Authorization", "Bearer 1")
                .header("X-Frame-Id",x_frame_id)
                .when()
                .get(url+"configurations");
        logger.info("Get frame configs = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void getConfigs(String merchant_id){
        response = given()
                .header("X-Flow-ID","2871d87284")
                .header("Authorization", "Bearer "+token.getToken())
                .when()
                .get(url+"configurations/"+merchant_id);
        logger.info("Get configs = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void setConfigs(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","2871d87284")
                .header("Authorization", "Bearer "+token.getToken())
                .body(body)
                .when()
                .post(url+"configurations");
        logger.info("Set configs = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void putConfigs(String body, String frame_id){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","2871d87284")
                .header("Authorization", "Bearer "+token.getToken())
                .queryParam("frame_id", frame_id)
                .body(body)
                .when()
                .put(url+"configurations");
        logger.info("Put configs = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void getTransaction(String x_frame_id, String x_transaction_id){
        response = given()
                .header("X-Flow-ID","2871d87283")
                .header("X-Frame-Id",x_frame_id)
                .header("X-Transaction-Id", x_transaction_id)
                .when()
                .get(url+"transactions");
        logger.info("Get transaction = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void createTransaction(String x_frame_id, String body, String lang){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","2871d87283")
                .header("X-Frame-Id",x_frame_id)
                .queryParam("lang",lang)
                .body(body).log().all()
                .when()
                .post(url+"transactions");
        logger.info("Create transaction = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
        if(statusCode == 200) {
            transaction_id = response.then().extract().response().jsonPath().getString("transaction_id");
        }
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void makeTransaction(String x_frame_id, String body, String lang){
        createTransaction(x_frame_id,body,lang);
        String url = response.then().extract().response().jsonPath().getString("3ds2_iframe.url");
        String params3ds = response.then().extract().response().jsonPath().getString("3ds2_iframe.post_params");
        ThreeDS.createIFrame(url,cutCreq(params3ds).toString());
    }

    public void getBranches(){
        response = given()
                .header("X-Flow-ID","2871d87283")
                .queryParam("lang", "uk")
                .when()
                .get(url+"branches");
        logger.info("Get branches = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void getRates(String currency){
        response = given()
                .header("X-Flow-ID","2871d87283")
                .header("Authorization", "Bearer 1")
                .queryParam("lang", "uk")
                .queryParam("currency_list", currency)
                .when()
                .get(url+"rates");
        logger.info("Get rates = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void timeslots(String lang, String body, String x_frame_id){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","2871d87283")
                .header("X-Frame-Id",x_frame_id)
                .queryParam("lang", lang)
                .body(body)
                .when()
                .post(url+"timeslots");
        logger.info("Timeslots = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    private StringBuffer cutCreq(String params3ds){
        StringBuffer methodData = new StringBuffer(params3ds);
        methodData.delete(0,9);
        methodData.delete(251,methodData.length());
        return methodData;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response.then().extract().response().asString();
    }
}
