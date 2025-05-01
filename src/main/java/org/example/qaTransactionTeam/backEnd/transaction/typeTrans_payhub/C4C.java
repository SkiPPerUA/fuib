package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_payhub;
import org.testng.Assert;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C4C extends Transaction_payhub implements Transaction {

    String type = "c4c";
    String enrollId;
    String bodyRequest;

    public C4C(){
        super.type = type;
        super.token = new Trans_token_payhub();
    }

    public C4C(String body){
        super.type = type;
        bodyRequest = body;
        makeTrans();
    }

    public C4C(String body, boolean withOutConfirmation){
        super.type = type;
        super.withOutConfirmation = withOutConfirmation;
        bodyRequest = body;
        makeTrans();
    }

    public C4C(String body, int threeDS){
        super.type = type;
        bodyRequest = body;
        super.threeDS = threeDS;
        makeTrans();
        get_theeDS_data();
        if (valid_type.equals("3DS")){
            try {
                ThreeDS.createIFrame(url, creq);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            agree3DS();
        }
    }

    public void enroll(int summ){
        externalId = Uuid_helper.generate_uuid();
        String body;
        if (summ < 0){
            body = "{\n" +
                    "    \"external_id\": \""+externalId+"\"\n" +
                    "}";
        }else {
            body = "{\n" +
                    "    \"external_id\": \""+externalId+"\",\n" +
                    "    \"amount\": "+summ+"\n" +
                    "}";
        }
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body(body)
                .when()
                .put(token.getHost() + "/operations/deferred/c2c/"+transactionId+"/enroll");
        String responseBody = response.then().extract().response().asString();
        logger.info("Зачисление "+type+" - "+ responseBody);
        Assert.assertEquals(response.getStatusCode(),statusCode);
        enrollId = response.then().extract().response().jsonPath().getString("id");
    }

    public void enroll(){
        enroll(-1);
    }

    public void statusEnroll(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .when()
                .get(token.getHost() + "/operations/deferred/c2c/"+transactionId+"/enrollments?id="+enrollId+"&details=true");
        logger.info("Статус зачисление "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void refund(int summRefund){
        externalId = Uuid_helper.generate_uuid();
        String body;
        if (summRefund < 0){
            body = "{\n" +
                    "    \"external_id\": \""+externalId+"\",\n" +
                    "    \"operation_id\": \""+transactionId+"\"\n" +
                    "}";
        }else {
            body = "{\n" +
                    "    \"external_id\": \""+externalId+"\",\n" +
                    "    \"operation_id\": \""+transactionId+"\",\n" +
                    "    \"amount\": "+summRefund+"\n" +
                    "}";
        }
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body(body)
                .when()
                .post(token.getHost() + "/operations/deferred/c2c/refund");
        logger.info("Refund "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void refund(){
        refund(-1);
    }

    public void statusRefund(String transactionId, String externalId){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .when()
                .get(token.getHost() + "/operations/deferred/c2c/refund?operation_id="+transactionId+"&external_id="+externalId+"");
        logger.info("Статус refund "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void statusRefund(){
        statusRefund(transactionId,externalId);
    }

    @Override
    public void makeTrans() {
        super.token = new Trans_token_payhub(true);
        try {
            createTrans(bodyRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String status() {
        return super.getStatus(transactionId);
    }

    public String status(String transactionId) {
        return super.getStatus(transactionId);
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
}
