package org.example.qaTransactionTeam.backEnd.cxaxukluth;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Cxaxukluth extends Restful {

    private static final Logger logger = Logger.getLogger(Cxaxukluth.class);
    private Auth_token token = new Trans_token_payhub();

    public void checkBadCard(String pan){
        logger.info("checkBadCard");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body("{\n" +
                        "\"pan\":\""+pan+"\",\n" +
                        "\"action\":\"checkBadCard\"\n" +
                        "}")
                .when()
                .post(token.getHost() + "/transactions/cards/check"));
    }
}
