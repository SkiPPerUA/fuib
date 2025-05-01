package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub;

import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_payhub;

import java.io.IOException;

public class A2C_tax extends Transaction_payhub implements Transaction {

    String type = "a2c_tax";
    String bodyRequest;
    Auth_token token = new Trans_token_payhub();

    public A2C_tax(){
        super.token = token;
        super.type = type;
    }

    public A2C_tax(String body){
        super.token = token;
        super.type = type;
        this.bodyRequest = body;
        makeTrans();
    }

    @Override
    public void makeTrans() {
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
