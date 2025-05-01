package org.example.qaTransactionTeam.backEnd.payHub.frames;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class Frames {
    private static final Logger logger = Logger.getLogger(Frames.class);
    private String response;
    protected String type;
    protected String linkId;
    protected String transactionId;
    private JSONObject json;
    private Trans_token_payhub token;

    {
        try {
            token = new Trans_token_payhub("svc_ph_trn2_t","HNviY3YJnEBTf&tSBnC3gRn4v%y&fn&B","transacter");
        } catch (JSONException e) {
            logger.error(e);
        }
    }

    public void makeFrame(String body) throws JSONException {
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "    \"external_id\": \""+UUID.randomUUID()+"\",\n" +
                        body +
                        "}")
                .when()
                .post(token.getHost()+"/frames/links/"+type)
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Выполнение "+type+" frame - "+response);
        json = new JSONObject(response);
        linkId = json.getString("id");
        payment(json.getString("url"));

    }

    private void payment(String url){
        System.out.println("Оплата - "+url);
        //Время ожидание оплаты
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            logger.error(e);
//        }
    }

    protected void completeHold(String body, String linkId){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .put(token.getHost()+"/frames/links/"+type+"/"+linkId+"/complete")
                .then().extract().response().prettyPrint();
    }

    public void status(){
        status(linkId);
    }

    public void status(String linkId){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/frames/links/"+type+"/"+ linkId +"")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Status "+type+" frame - "+response);
        json = new JSONObject(response);
        transactionId = json.getJSONObject("transaction").getString("transaction_id");
    }

    public String getResponse() {
        return response;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Trans_token_payhub getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getLinkId() {
        return linkId;
    }
}
