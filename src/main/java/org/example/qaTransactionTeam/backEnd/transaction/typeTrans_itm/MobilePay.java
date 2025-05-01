package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm;

import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.transaction.AppleGooglePay;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.wonderWoman.WonderWoman;
import org.json.JSONObject;

public class MobilePay extends AppleGooglePay implements Transaction {

    private String typeTrans = "MobilePay";
    private String body;
    private String cvv = "";
    private String summ;

    public MobilePay(String body, int threeDS){
        this.body = body;
        find_summ();
        this.version3ds = threeDS;
        makeTrans();
    }

    public MobilePay(){};

    public MobilePay(String body, int threeDS, String summ_for_finalization){
        this.body = body;
        summ = summ_for_finalization;
        this.version3ds = threeDS;
        makeTrans();
    }

    public void find_summ(){
        summ = new JSONObject(body).getString("amount");
    }

    @Override
    public void makeTrans() {
        if (createToken == null) {
            createToken = new Trans_token_itm();
        }
        super.typeTrans = typeTrans;
        register(body);
        if (need_threeDS) {
            cres();
            finalization_three_DS();
        }
        finish(summ);
    }

    @Override
    public String status() {
        WonderWoman wonderWoman = new WonderWoman();
        wonderWoman.getStatus(createToken.getAcqID(), sessionId);
        return wonderWoman.getResponse();
    }

    @Override
    public void setExpectedStatus(int status) {
        statusCode = status;
    }

    @Override
    public void setBodyRequest(String bodyRequest) {
        this.body = bodyRequest;
    }

    @Override
    public void setThreeDS(int threeDS) {
        this.version3ds = threeDS;
    }

    @Override
    public String getTransactionId() {
        return sessionId;
    }

    public void setToken(Trans_token_itm token){
        super.createToken = token;
    }

    public String getRecurrent_id(){
        return recurrent_payment_id;
    }

}
