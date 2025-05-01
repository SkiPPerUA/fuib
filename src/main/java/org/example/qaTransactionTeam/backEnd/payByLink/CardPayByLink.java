package org.example.qaTransactionTeam.backEnd.payByLink;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CardPayByLink {

    private static final Logger logger = Logger.getLogger(CardPayByLink.class);
    private String response;
    private String tokenCard;
    private TokenForPayByLink token = new TokenForPayByLink("pbl_test","password");

    public void addCard(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs1.PAYHUB_HOST +"/wallet/cards")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JSONObject jsonObject = new JSONObject(response);
        tokenCard = jsonObject.getString("token");
        logger.info("Add Card = "+response);
    }

    public void confirmCard(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs1.PAYHUB_HOST +"/wallet/cards/confirm")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Confirm Card = "+response);
    }

    public void renameCard(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .put(Configs1.PAYHUB_HOST +"/wallet/cards")
                .then()
                .statusCode(204)
                .extract().response().asString();
    }

    public void deleteCard(String tokenCard){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .delete(Configs1.PAYHUB_HOST +"/wallet/cards?token="+tokenCard+"")
                .then()
                .statusCode(204)
                .extract().response().asString();

        logger.info("Delete Card = "+response);
    }

    public void statusCard(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(Configs1.PAYHUB_HOST +"/wallet/cards")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("status Card = "+response);
    }

    public void makeDefaultCard(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs1.PAYHUB_HOST +"/wallet/cards/default")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Default Card = "+response);
    }

    public String getResponse() {
        return response;
    }

    public String getTokenCard() {
        return tokenCard;
    }
}

