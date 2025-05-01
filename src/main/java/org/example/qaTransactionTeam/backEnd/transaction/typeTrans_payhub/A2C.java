package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_payhub;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class A2C extends Transaction_payhub implements Transaction {

    String type = "a2c";
    String bodyRequest;

    public A2C(){
        super.type = type;
    }

    public A2C(String body){
        super.type = type;
        this.bodyRequest = body;
        makeTrans();
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
        return getStatus(transactionId);
    }

    public String status(String transactionId) {
        return getStatus(transactionId);
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
        //На а2с - нет 3дс
    }

}
