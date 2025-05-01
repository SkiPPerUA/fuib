package org.example.qaTransactionTeam.backEnd.transaction;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.utils.BDMongo;
import org.example.qaTransactionTeam.backEnd.wonderWoman.WonderWoman;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AppleGoogleReversalRefund {

    private static final Logger logger = Logger.getLogger(AppleGoogleReversalRefund.class);
    private Trans_token_itm createToken;
    private String reversalInfo;
    private String refundInfo;
    private int statusCode = 200;

    {
        try {
            createToken = new Trans_token_itm();
        } catch (JSONException e) {
            logger.error(e);
        }
    }

    public void reversal(String sessionId){
        List<String> amounts = new ArrayList<>();
        WonderWoman wonderWoman = new WonderWoman();
        wonderWoman.getStatus("2101", sessionId);
        JSONArray array = new JSONObject(wonderWoman.getResponse()).getJSONObject("data").getJSONArray("transfers_table");
        for (int i = 0; i < array.length(); i++){
            String s = array.getJSONObject(i).getString("sum");
            amounts.add(s);
        }
        String st = amounts.stream().max(Comparator.naturalOrder()).get();
        logger.info("Sum = "+st+" для "+sessionId);
        reversal(sessionId,st);
    }

    public void reversal(String sessionId, String amount){
        String url = "https://tsystestapi.pumb.ua/vmt/api/reversal/"+createToken.getAcqID()+"/preauth";

        String body = "{\n" +
                "\t\"amount\":"+amount+",\n" +
                "\t\"session_id\":\""+sessionId+"\"\n" +
                "}";

        reversalInfo = given()
                .header("Token", createToken.getToken())
                .header("x-forwarded-for","127.0.0.1")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
//                .statusCode(statusCode)
                .extract()
                .response().asString();

        logger.info("Reversal AppleGoogle - " + reversalInfo);
    }

    public void refund(String sessionId, String amount){
        String url = "https://tsystestapi.pumb.ua/vmt/api/refund/"+createToken.getAcqID()+"/finalized";

        String body = "{\n" +
                "\t\"purchase_stan\":\""+sessionId+"\",\n" +
                "\t\"refund_amount\":\""+amount+"\",\n" +
                "\t\"aprove_number\":  \""+findUppNum(sessionId)+"\"\n" +
                "}";

        refundInfo = given()
                .header("Token", createToken.getToken())
                .header("x-forwarded-for","127.0.0.1")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
//                .statusCode(statusCode)
                .extract()
                .response().asString();

        logger.info("Refund AppleGoogle - " + refundInfo);
    }

    public void refund(String sessionId, String amount, String card, String expDate){
        String url = "https://tsystestapi.pumb.ua/vmt/api/refund/"+createToken.getAcqID()+"/finalized";

        String body = "{\n" +
                "\t\"purchase_stan\":\""+sessionId+"\",\n" +
                "\t\"refund_amount\":\""+amount+"\",\n" +
                "\t\"aprove_number\":  \""+findUppNum(sessionId)+"\",\n" +
                "\t\"card_number\":\""+card+"\",\n" +
                "  \"experation_date\":\""+expDate+"\"\n" +
                "}";

        refundInfo = given()
                .header("Token", createToken.getToken())
                .header("x-forwarded-for","127.0.0.1")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();

        logger.info("Refund на другую карту AppleGoogle - " + refundInfo);
    }

    private String findUppNum(String session){
        WonderWoman wonderWoman = new WonderWoman();
        wonderWoman.getStatus("2101", session);
        JSONObject jsonObject = new JSONObject(wonderWoman.getResponse());
        String st = jsonObject.getJSONObject("data").getJSONArray("transfers_table").getJSONObject(0).getString("aprove_number");
        //logger.info("UppNum = "+st+" для "+session);
        return st;

    }

    public String getReversalInfo() {
        return reversalInfo;
    }

    public String getRefundInfo() {
        return refundInfo;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
