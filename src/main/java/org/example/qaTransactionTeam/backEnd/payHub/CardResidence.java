package org.example.qaTransactionTeam.backEnd.payHub;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class CardResidence {

    protected static final Logger logger = Logger.getLogger(CardResidence.class);
    public String response;
    protected Trans_token_payhub token;

    {
        try {
            token = new Trans_token_payhub("transacter_test", Configs1.PAYHUB_PASSWORD, Configs1.PAYHUB_CLIENT,"https://innsmouth.payhub.com.ua");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void checkResidenceCard(String pan){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body("{\"pan\": \""+pan+"\"}")
                .when()
                .post(token.getHost()+"/transactions/cards/is-domestic")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Карта "+pan+" выпушена на Украние - "+response);
    }
}
