package org.example.qaTransactionTeam.backEnd.payHub;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class Stanok {

    private static final Logger logger = Logger.getLogger(Stanok.class);
    private String response;
    private Trans_token_payhub token;
    {
        try {
            token = new Trans_token_payhub("transacter_test", Configs.PAYHUB_PASSWORD, Configs.PAYHUB_CLIENT);
        } catch (JSONException e) {
            logger.error(e);
        }
    }

    public void findByStan(String stan){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/stanok/search?stan="+stan+"")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Поиск по стану - "+stan);
        logger.info(response);
    }

    public String getResponse() {
        return response;
    }
}
