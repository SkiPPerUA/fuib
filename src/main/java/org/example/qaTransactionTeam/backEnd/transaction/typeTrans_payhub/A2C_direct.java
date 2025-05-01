package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub;

import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_payhub;

import java.io.IOException;

public class A2C_direct extends Transaction_payhub implements Transaction {

    String type = "a2c";
    String bodyRequest;

    public A2C_direct(String body){
        super.type = type;
        this.bodyRequest = body;
        makeTrans();
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
