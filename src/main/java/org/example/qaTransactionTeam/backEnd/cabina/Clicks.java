package org.example.qaTransactionTeam.backEnd.cabina;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Clicks extends Restful {

    private final Logger logger = Logger.getLogger(Clicks.class);
    private final Trans_token_payhub token = new Trans_token_payhub();

    public void sendMerchantInfo(){
        logger.info("sendMerchantInfo");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .body("{}")
                .post(token.getHost()+"/cabina/clicks/installment-partners"));
    }

}
