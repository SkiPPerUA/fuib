package org.example.qaTransactionTeam.backEnd.transferGO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class TransferGO {

    private static final Logger logger = Logger.getLogger(TransferGO.class);
    private Response response;
    private int statusCode = 200;
    private final Auth_token token = new Trans_token_payhub();
    private String id = "";

    public void create(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Trace-ID", Uuid_helper.generate_uuid())
                .header("Authorization", "Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/tg-transfers");
        logger.info("Create TransferGO = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
        if (response.getStatusCode() == 200) {
            id = response.then().extract().response().jsonPath().getString("transfer_id");
        }
    }

    public void confirm(String body, String id){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Trace-ID", Uuid_helper.generate_uuid())
                .header("Authorization", "Bearer "+token.getToken())
                .body(body)
                .when()
                .put(token.getHost()+"/tg-transfers/"+id);
        logger.info("Confirm TransferGO = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void confirm(String body){
        if (id.isEmpty()){
            Assert.fail("Параметр ID - пустое");
        }else {
            confirm(body, id);
        }
    }

    public void transStatus(String id){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Trace-ID", Uuid_helper.generate_uuid())
                .header("Authorization", "Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/tg-transfers/"+id);
        logger.info("Status TransferGO = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void getBalance(int currency){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Trace-ID", Uuid_helper.generate_uuid())
                .header("Authorization", "Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/tg-transfers/balances?currency="+currency);
        logger.info("Balance for currency ("+currency+") = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void transStatus(){
        if (id.isEmpty()){
            Assert.fail("Параметр ID - пустое");
        }else {
            transStatus(id);
        }
    }

    public String getResponse() {
        return response.then().extract().response().asString();
    }

    public String getId() {
        return id;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
