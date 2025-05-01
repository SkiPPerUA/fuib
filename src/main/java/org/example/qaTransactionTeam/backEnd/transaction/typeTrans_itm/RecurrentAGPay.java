package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm;

import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.transaction.AppleGooglePay;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.example.qaTransactionTeam.backEnd.wonderWoman.WonderWoman;
import org.json.JSONObject;

public class RecurrentAGPay extends AppleGooglePay implements Transaction {

    private String typeTrans = "MobilePayRecurrent";
    private String body;
    private String cvv = "";
    private String summ;

    @Override
    public void makeTrans() {
        super.typeTrans = typeTrans;
        createToken = new Trans_token_itm("APGPC_APGP",Configs.PASSWORD_MERCHANT_2101,"2196");
        //createToken = new Trans_token_itm("SBPRC_APGP",Configs.PASSWORD_MERCHANT_2101,"2202");
        register(body);
        if (need_threeDS) {
            cres();
            //finalization_three_DS();
        }
        finish(summ);
    }

    public void find_summ(){
        summ = new JSONObject(body).getString("amount");
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

    public RecurrentAGPay setSumm(String summ) {
        this.summ = summ;
        return this;
    }

    public String getRecurrent_id(){
        return recurrent_payment_id;
    }
}
