package org.example.qaTransactionTeam.backEnd.transfers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class ExternalCardholder extends Restful {

    private final Logger logger = Logger.getLogger(ExternalCardholder.class);
    private Auth_token token = new Trans_token_payhub();

    public void getExternalCardholder(String body){
        RestAssured.useRelaxedHTTPSValidation();
        logger.info("receivers");
        request(given().
                contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .header("X-Flow-ID", Uuid_helper.generate_uuid())
                .body(body)
                .post(token.getHost()+"/transfers/external-cardholders"));
    }
}
