package org.example.qaTransactionTeam.backEnd.admin;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class DirectBanks extends Restful {

    private static final Logger logger = Logger.getLogger(DirectBanks.class);
    private final Trans_token_payhub token = new Trans_token_payhub();

    public void checkCards(String bank, String pan){
        logger.info("checkCards");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .body("{\n" +
                        "\"pan\": \""+pan+"\"\n" +
                        "}")
                .when()
                .post(token.getHost()+"/admin/direct-banks/"+bank+"/check-cards"));
    }

}
