package org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction_itm;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P4P extends Transaction_itm implements Transaction {

    private static final Logger logger = Logger.getLogger(P4P.class);
    private String p4pFundId;
    private String typeTrans = "P4P";
    private Map body;
    private String cvv = "";

    public P4P(Map body, String cvv) throws JSONException {
        this.body = body;
        this.cvv = cvv;
        makeTrans();
    }

    public P4P(Map body) throws JSONException {
        this.body = body;
        makeTrans();
    }

    public P4P(Map body, int threeDS) throws JSONException, IOException, InterruptedException {
        this.body = body;
        this.threeDS = threeDS;
        makeTrans();
    }

    public P4P(Map body, String cvv, int threeDS) throws JSONException, IOException, InterruptedException {
        this.body = body;
        this.threeDS = threeDS;
        this.cvv = cvv;
        makeTrans();
    }

    private void P4Pfinish(Map body) throws JSONException {
        response = given()
                .contentType(ContentType.URLENC)
                .header("token", createToken.getToken())
                .params(body)
                .when()
                .post(host+"/api/payment/"+createToken.getAcqID()+"/"+ sessionId +"/");

        logger.info("Зачисление средств P4P - " + this.getResponse());
        if (response.then().extract().response().jsonPath().getString("errorCode").equals("00")) {
            p4pFinalisationId = response.then().extract().response().jsonPath().getString("p4pFinalisationId");
        }
        errorCode = response.then().extract().response().jsonPath().getString("errorCode");
    }

    public void P4Ppayment(String summ,boolean fundId) throws JSONException {
        Map<String, String> body = new HashMap<>();
        body.put("paymentIdP4P",p4pFinalisationId);
        body.put("customAmount",summ);
        if(fundId) {
            p4pFundId = Uuid_helper.generate_uuid();
            body.put("p4pFundId", p4pFundId);
        }
        P4Pfinish(body);
    }

    public void P4Ppayment(String summ) throws JSONException {
        Map<String, String> body = new HashMap<>();
        body.put("paymentIdP4P",p4pFinalisationId);
        body.put("customAmount",summ);
        P4Pfinish(body);
    }

    public void P4Ppayment(String summ, Card card) throws JSONException {
        Map<String, String> body = new HashMap<>();
        body.put("paymentIdP4P",p4pFinalisationId);
        body.put("customAmount",summ);
        body.put("customReceiverCard",Cards_data.getData(card,Card_param.pan));
        P4Pfinish(body);
    }

    public String getP4pFundId() {
        return p4pFundId;
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
