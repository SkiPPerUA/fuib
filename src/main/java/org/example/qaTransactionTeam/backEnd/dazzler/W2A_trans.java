package org.example.qaTransactionTeam.backEnd.dazzler;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class W2A_trans extends Restful {

    private final Logger logger = Logger.getLogger(W2A_trans.class);
    private Auth_token token = new Trans_token_payhub(6241781);

    public void initTrans(String body){
        logger.info("initTrans");
        request(given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer "+token.getToken())
                .body(body)
                .when()
                .get(token.getHost() + "/dazzler/w2a/transactions"));
    }

    public void dPans(String body){
        logger.info("initTrans");
        request(given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("x-real-ip", "0.0.0.0")
                .header("Authorization", "Bearer "+token.getToken())
                .body(body).log().all()
                .when()
                .post(token.getHost() + "/dazzler/w2a/transactions/dpans"));
    }

}
