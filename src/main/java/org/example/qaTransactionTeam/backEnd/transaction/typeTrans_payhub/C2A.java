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
import java.util.UUID;
import static io.restassured.RestAssured.given;

public class C2A extends Transaction_payhub implements Transaction {
    String type = "c2a";
    public String bodyRequest;

    public C2A(String body){
        super.type = type;
        bodyRequest = body;
        makeTrans();
    }

    public C2A(){
        super.type = type;
    }

    public C2A(String body, int threeDS){
        super.type = type;
        bodyRequest = body;
        if (threeDS > 0) {
            this.threeDS = threeDS;
            makeTrans();
            get_theeDS_data();
            try {
                ThreeDS.createIFrame(url, creq);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            agree3DS();
        }else {
            super.withOutConfirmation = true;
            makeTrans();
        }
    }

    public C2A(String body, boolean withOutConfirmation){
        super.type = type;
        bodyRequest = body;
        super.withOutConfirmation = withOutConfirmation;
        makeTrans();
    }

    public void refund(String transactionId, int amount){
        String with_amount = "";
        if (amount != 0) {
             with_amount = "  \"amount\": \"" + amount + "\",\n";
        }
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body("{\n" +
                        "  \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                        with_amount +
                        "  \"transaction_id\": \""+transactionId+"\"\n" +
                        "}")
                .when()
                .put(token.getHost() + "/transactions/c2a/refund");
        logger.info("Refund "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void refund(int amount){
        refund(transactionId, amount);
    }

    public void statusRefund(String transactionId){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .when()
                .get(token.getHost() + "/transactions/c2a/refund?transaction_id="+transactionId+"&external_id="+externalId+"");
        logger.info("Статус refund "+type+" - "+response.then().extract().response().asString());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void statusRefund(){
        statusRefund(transactionId);
    }

    @Override
    public void makeTrans() {
        super.token = new Trans_token_payhub();
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
        return resp;
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
