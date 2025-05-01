package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm;

import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_itm;
import org.json.JSONException;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C2A extends Transaction_itm implements Transaction {

    private String typeTrans = "C2A";
    private Map body;
    private String cvv = "";

    public C2A(){};

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public C2A(Map body, String cvv){
        this.body = body;
        this.cvv = cvv;
        makeTrans();
    }

    public C2A(Map body){
        this.body = body;
        this.threeDS = 0;
        makeTrans();
    }

    public C2A(Map body, int threeDS){
        this.body = body;
        this.threeDS = threeDS;
        makeTrans();
    }

    public C2A(Map body, String cvv, int threeDS){
        this.body = body;
        this.threeDS = threeDS;
        this.cvv = cvv;
        makeTrans();
    }

    @Override
    public void makeTrans() {
        super.typeTrans = typeTrans;
        if (createToken == null) {
            createToken = new Trans_token_itm();
        }
        register(body);
        acs(cvv);
        if(threeDS == 2) {
            finishWith3ds();
        }else {
            finish();
        }
    }

    @Override
    public String status() {
        return null;
    }

    @Override
    public void setExpectedStatus(int status) {
        this.statusCode = status;
    }

    @Override
    public void setBodyRequest(String bodyRequest) {

    }

    public void setBodyRequest(Map bodyRequest) {
        this.body = bodyRequest;
    }

    @Override
    public void setThreeDS(int threeDS) {
        this.threeDS = threeDS;
    }

    public void setToken(String acq){
        createToken = new Trans_token_itm(acq);
    }
}
