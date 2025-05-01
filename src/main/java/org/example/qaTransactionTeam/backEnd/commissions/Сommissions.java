package org.example.qaTransactionTeam.backEnd.commissions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Сommissions extends Restful {

    private static final Logger logger = Logger.getLogger(Сommissions.class);
    private final Auth_token token = new Trans_token_payhub(true);

    public void commissions_recipients(String body){
        logger.info("Commissions recipients");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/commissions/recipient-calculates"));
    }
}
