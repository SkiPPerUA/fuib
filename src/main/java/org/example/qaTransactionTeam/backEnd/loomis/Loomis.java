package org.example.qaTransactionTeam.backEnd.loomis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Loomis extends Restful {

    private final Logger logger = Logger.getLogger(Loomis.class);

    public Loomis(){
        RestAssured.useRelaxedHTTPSValidation();
    }

    public void getDocument(String body){
        request(given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(Configs.PAYHUB_HOST +"/loomis/v1/a2c-direct-transfers/recepient-details"));
    }
}
