package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub;

import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_payhub;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;

import java.sql.SQLException;

public class A2C_legion extends Transaction_payhub implements Transaction {

    String type = "a2c legion";
    String bodyRequest;

    public A2C_legion(){
        super.type = type;
    }

    public A2C_legion(Auth_token token){
        super.type = type;
        super.token = token;
    }

    public A2C_legion(String body){
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
        //На а2c - нет 3дс
    }

}
