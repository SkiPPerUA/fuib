package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub;

import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_payhub;
import org.json.JSONObject;

public class C2A_legion extends Transaction_payhub implements Transaction {

    String type = "c2a legion";
    String bodyRequest;

    public C2A_legion(){
        super.type = type;
    }

    public C2A_legion(Auth_token token){
        super.type = type;
        super.token = token;
    }

    public C2A_legion(String body){
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
            if (require_3ds_data){
                waitFinalStatus();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void waitFinalStatus(){
        getStatus(transactionId);
        String status = new JSONObject(getResponse()).getString("status");
        if (status.equals("PROCESSED") || status.equals("FAILED")){

        }else if (status.equals("3DS_REQUIRED")){

            get_theeDS_data();
            try {
                ThreeDS.createIFrame(url, creq);
            } catch (Throwable e) {
                e.printStackTrace();
            }

        }else {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            waitFinalStatus();
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
