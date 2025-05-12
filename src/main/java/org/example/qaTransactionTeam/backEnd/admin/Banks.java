package org.example.qaTransactionTeam.backEnd.admin;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Banks extends Restful {

    private static final Logger logger = Logger.getLogger(Banks.class);
    private final Trans_token_payhub token = new Trans_token_payhub();

    public void getCrossborderBins(){
        logger.info("Get crossborder-bins");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/admin/banks/crossborder-bins"));
    }

    public void addCrossborderBins(String body){
        logger.info("Add crossborder-bins");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/admin/banks/crossborder-bins"));
    }

    public void deleteCrossborderBins(String bin){
        logger.info("Delete crossborder-bins");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .when()
                .delete(token.getHost()+"/admin/banks/crossborder-bins/"+bin));
    }

}
