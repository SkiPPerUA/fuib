package org.example.qaTransactionTeam.backEnd.dazzler;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Limiter extends Restful {

    private final Logger logger = Logger.getLogger(Limiter.class);
    private Auth_token token = new Trans_token_payhub();

    public void getClientLimits(String body){
        logger.info("getClientLimits");
        request(given()
                .contentType(ContentType.JSON)
                .header("x-flow-id","test")
                .header("x-systemcode","test")
                .header("authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/dazzler/limiter/client-limits"));
    }
}
