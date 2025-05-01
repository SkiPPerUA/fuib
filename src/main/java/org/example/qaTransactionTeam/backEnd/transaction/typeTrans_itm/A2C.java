package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm;

import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_itm;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class A2C extends Transaction_itm implements Transaction {

    private String typeTrans = "A2C";
    private final Map requestBody;

    public A2C(Map body) throws JSONException {
        this.requestBody = body;
        makeTrans();
    }

    @Override
    public void makeTrans() {
        super.typeTrans = typeTrans;
        createToken = new Trans_token_itm();
        register(requestBody);
        finish();
    }

    @Override
    public String status() {
        return null;
    }


    @Override
    public void setExpectedStatus(int status) {

    }

    @Override
    public void setBodyRequest(String bodyRequest) {

    }

    @Override
    public void setThreeDS(int threeDS) {

    }
}
