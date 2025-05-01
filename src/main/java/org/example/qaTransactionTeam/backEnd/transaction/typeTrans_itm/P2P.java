package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm;

import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_itm;
import org.json.JSONException;

import java.io.IOException;
import java.util.Map;

public class P2P extends Transaction_itm implements Transaction {

     private String typeTrans = "P2P";
    private Map body;
    private String cvv = "";

    public P2P(Map body, String cvv){
        this.body = body;
        this.cvv = cvv;
        makeTrans();
    }

    public P2P(Map body){
        this.body = body;
        makeTrans();
    }

    public P2P(Map body, int threeDS){
        this.body = body;
        this.threeDS = threeDS;
        makeTrans();
    }

    public P2P(Map body, String cvv, int threeDS){
        this.body = body;
        this.threeDS = threeDS;
        this.cvv = cvv;
        makeTrans();
    }

    @Override
    public void makeTrans() {
        super.typeTrans = typeTrans;
        createToken = new Trans_token_itm();
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

    @Override
    public void setThreeDS(int threeDS) {
        this.threeDS = threeDS;
    }
}
