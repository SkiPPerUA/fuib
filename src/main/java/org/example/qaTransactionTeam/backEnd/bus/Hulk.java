package org.example.qaTransactionTeam.backEnd.bus;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Shazam_token;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;

import static io.restassured.RestAssured.given;

public class Hulk {

    private static final Logger logger = Logger.getLogger(Hulk.class);
    private Apiman token = new Apiman(true);
    private String response;

    public void find_accounts(String client_id){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .body("{\n" +
                        "  \"filter\": {\n" +
                        "    \"client_id\": \""+client_id+"\"\n" +
                        "  }\n" +
                        "}")
                .when()
                .post(token.getToken()+"/bus/hulk/v2/accounts/by-filters")
                        .then().extract().response().asString();
        logger.info("Accounts - "+response);
    }
}
