package org.example.qaTransactionTeam.backEnd.admin;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Scoring {

    private static final Logger logger = Logger.getLogger(Scoring.class);
    private final Auth_token token = new Trans_token_payhub("https://innsmouth.test-fuib.com");
    private Response response;
    private int statusCode = 200;
    private final String url = token.getHost();

    public void partnerScoring(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID", Uuid_helper.generate_uuid())
                .header("Authorization", "Bearer "+token.getToken())
                .body(body)
                .when()
                .post(url+"/black-noir/partners/calculates");
        logger.info("Partner scoring = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void internalScoring(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID", Uuid_helper.generate_uuid())
                .header("Authorization", "Bearer "+token.getToken())
                .body(body)
                .when()
                .post(url+"/black-noir/internals/calculates");
        logger.info("Internal scoring = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public String getResponse(){
        return response.then().extract().response().asString();
    }

    public Scoring setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }
}
