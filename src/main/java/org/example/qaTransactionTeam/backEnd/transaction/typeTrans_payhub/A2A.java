package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub;

import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_payhub;

import java.io.IOException;

public class A2A extends Transaction_payhub implements Transaction {

    String type = "a2a legion";
    String bodyRequest;

    public A2A(){
        super.type = type;
    }

    public A2A(String body){
        super.type = type;
        this.bodyRequest = body;
        super.token = new Trans_token_payhub(true);
        makeTrans();
    }

    @Override
    public void makeTrans() {
        try {
            initTransfers(bodyRequest);
            confirmTransfers();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public String status() {
        return getStatus(transactionId);
    }

    public void setToken(Auth_token token) {
        super.token = token;
    }

    public String getToken() {
        return token.getToken();
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
        //На а2a - нет 3дс
    }

}
