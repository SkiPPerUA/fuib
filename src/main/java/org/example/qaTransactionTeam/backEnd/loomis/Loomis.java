package org.example.qaTransactionTeam.backEnd.loomis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Loomis extends Restful {

    private final Logger logger = Logger.getLogger(Loomis.class);
    Apiman token = new Apiman("ODB","acc_r");

    public Loomis(){
        RestAssured.useRelaxedHTTPSValidation();
    }

    public void getDocument(String body){
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs.PAYHUB_HOST +"/loomis/v1/a2c-direct-transfers/recepient-details"));
    }

    public void getRecipients(String body){
        request(given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(Configs.PAYHUB_HOST +"/loomis/get-recipients"));
    }
}
