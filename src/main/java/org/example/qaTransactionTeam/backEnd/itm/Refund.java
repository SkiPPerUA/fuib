package org.example.qaTransactionTeam.backEnd.itm;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Refund {

    private static final Logger logger = Logger.getLogger(Refund.class);
    private String url = "https://tsystestapi.pumb.ua/vmt/api/refund/";
    private String refundInfo;
    private String aprove_number;
    private GetTransDetails getTransDetails;
    private Map<String,String> body;
    private String stan;
    private int statusCode = 200;
    private Trans_token_itm createToken;
    {
        try {
            createToken = new Trans_token_itm();
        } catch (JSONException e) {
            logger.error(e);
        }
    }

    public Refund(String session) throws JSONException {

        getTransDetails = new GetTransDetails(session);

        JSONObject ob = new JSONObject(getTransDetails.getResponse());
        JSONArray arr = ob.getJSONObject("data").getJSONArray("transfers_table");

        for (int i = 0; i<arr.length();i++){
            JSONObject object = arr.getJSONObject(i);
            String tran_type = object.getString("transaction_type");
            if(tran_type.equals("OC")) {
                aprove_number = object.getString("aprove_number");
                stan = object.getString("stan");
                logger.info("Aprove number = "+aprove_number);
            }
        }
    }

    public void makeRefund(String sum, String expDate, String card){

        body = new HashMap<>();
        body.put("purchase_stan",stan);
        body.put("refund_amount",sum);
        body.put("aprove_number",aprove_number);
        body.put("experation_date",expDate);
        body.put("card_number", card);
        refundSend();
    }

    public void makeRefund(String sum){

        body = new HashMap<>();
        body.put("purchase_stan",stan);
        body.put("refund_amount",sum);
        body.put("aprove_number",aprove_number);
        refundSend();
    }

    private void refundSend() {

        refundInfo = given()
                .header("Token", createToken.getToken())
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(url+createToken.getAcqID())
                .then()
                .statusCode(statusCode)
                .extract()
                .response().asString();

        logger.info("Инфо по рефанду - "+refundInfo);
    }

    public String getRefundInfo() {
        return refundInfo;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        logger.info("Ожидаемый статус Рефанда = "+statusCode);
    }
}
