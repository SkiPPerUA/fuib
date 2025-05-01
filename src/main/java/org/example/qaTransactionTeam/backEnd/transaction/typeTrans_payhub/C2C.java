package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub;

import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_payhub;
import java.io.IOException;

public class C2C extends Transaction_payhub implements Transaction {

    String type = "c2c";
    String bodyRequest;

    public C2C(String body){
        super.type = type;
        bodyRequest = body;
        makeTrans();
    }

    public C2C(){
        super.type = type;
        super.token = new Trans_token_payhub();
    }

    public C2C(String body, boolean withOutConfirmation){
        super.type = type;
        super.withOutConfirmation = withOutConfirmation;
        bodyRequest = body;
        makeTrans();
    }

    public C2C(String body, int threeDS){
        if (threeDS == 0){
            super.type = type;
            super.withOutConfirmation = true;
            bodyRequest = body;
            makeTrans();
        }else {
            super.type = type;
            bodyRequest = body;
            super.threeDS = threeDS;
            makeTrans();
            get_theeDS_data();
            try {
                ThreeDS.createIFrame(url, creq);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            agree3DS();
        }
    }

    @Override
    public void makeTrans() {
        super.token = new Trans_token_payhub();
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
        this.threeDS = threeDS;
    }
}
