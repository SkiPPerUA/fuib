package org.example.qaTransactionTeam.backEnd.payHub;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;
import org.json.JSONException;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class CardHolder extends Restful {

    private static final Logger logger = Logger.getLogger(CardHolder.class);
    private final Trans_token_payhub token = new Trans_token_payhub();

    public void nameShort(String pan){
        logger.info("Short Name");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "   \"pan\": \""+pan+"\"\n" +
                        "}")
                .when()
                .post(token.getHost()+"/transactions/cardholders/short-name"));
    }

    public void nameFull(String pan){
        logger.info("Full Name");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "   \"pan\": \""+pan+"\"\n" +
                        "}")
                .when()
                .post(token.getHost()+"/transactions/cardholders/full-name"));
    }

    public void matchTax(String pan, String tax){
        logger.info("Match-tax");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "   \"pan\": \""+pan+"\",\n" +
                        "   \"tax_id\": \""+tax+"\""+
                        "}")
                .when()
                .post(token.getHost()+"/transactions/cardholders/match-tax"));
    }

    public void full_details(String body){
        logger.info("Full-details");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/transactions/cardholders/full-details"));
    }

    public void details(String pan){
        logger.info("Details");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "   \"pan\": \""+pan+"\"\n" +
                        "}")
                .when()
                .post(token.getHost()+"/transactions/cardholders/details"));
    }
}
