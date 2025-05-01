package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_payhub;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class C4C_new extends Transaction_payhub implements Transaction {

    String type = "p4p_new";
    String enrollId;
    String bodyRequest;

    public C4C_new(){
        super.type = type;
        super.token = new Trans_token_payhub();
    }

    public C4C_new(String body){
        super.type = type;
        bodyRequest = body;
        makeTrans();
    }

    public void enroll(String debitId, String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .body(body)
                .when()
                .put(token.getHost()+"/p4p-transfers/"+debitId+"/enroll");
        logger.info("Enroll "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
        enrollId = response.then().extract().response().jsonPath().getString("id");
    }

    public void statusEnroll(String debitId, String enrollId){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .when()
                .get(token.getHost()+"/p4p-transfers/"+debitId+"/enrollments?id="+enrollId);
        logger.info("Статус зачисление "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void statusRefund(String debitId, String refundId){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .when()
                .get(token.getHost()+"/p4p-transfers/"+debitId+"/refunds?id="+refundId);
        logger.info("Refunds status "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void statusRefund(){
       statusRefund(debitId, refundId);
    }

    public void refund(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .body(body)
                .when()
                .post(token.getHost()+"/p4p-transfers/"+debitId+"/refunds");
        logger.info("Refunds "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
        refundId = response.then().extract().response().jsonPath().getString("id");
    }

    public void refund(int amount){
        refund("{\n" +
                "    \"external_id\": \""+Uuid_helper.generate_uuid()+"\",\n" +
                "    \"operation_id\": \""+debitId+"\",\n" +
                "    \"amount\": "+amount+"\n" +
                "}");
    }

    @Override
    public void makeTrans() {
        super.token = new Trans_token_payhub();
        initTransfers(bodyRequest);
    }

    @Override
    public String status() {
        return status(debitId);
    }

    public String status(String debitId) {
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .when()
                .get(token.getHost()+"/p4p-transfers?id="+debitId);
        logger.info("Status debit "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
        if (response.then().extract().response().jsonPath().get("validation_type").equals("3DS")){
            creq = response.then().extract().response().jsonPath().getString("threed_info.c_req");
            url = response.then().extract().response().jsonPath().getString("threed_info.acs_url");
        }
        return response.then().extract().response().asString();
    }

    @Override
    public String getResponse() {
        return response.then().extract().response().asString();
    }

    @Override
    public void setExpectedStatus(int status) {
        super.statusCode = status;
    }

    @Override
    public void setBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
    }

    @Override
    public void setThreeDS(int threeDS) {
        this.threeDS = threeDS;
    }

    public String getEnrollId() {
        return enrollId;
    }

    public String getRefundId(){
        return refundId;
    }

    public void makeThreeDS(){
        ThreeDS.createIFrame(url, creq);
        agree3DS();
    }
}
