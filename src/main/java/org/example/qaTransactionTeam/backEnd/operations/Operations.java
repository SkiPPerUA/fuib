package org.example.qaTransactionTeam.backEnd.operations;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Operations extends Restful {

    private final Logger logger = Logger.getLogger(Operations.class);
    private final Auth_token token = new Trans_token_payhub(2189387);
    private String operation_id;

    public void create(String body){
        logger.info("Create operations");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/operations"));
        operation_id = response.then().extract().response().jsonPath().getString("data.operation_id");
    }

    public void confirm(String body){
        logger.info("Confirm operations");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body(body)
                .when()
                .put(token.getHost()+"/operations/confirm"));
    }

    public void pay(String body){
        logger.info("Pay operations");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body(body)
                .when()
                .put(token.getHost()+"/operations/pay"));
    }

    public String getOperation_id() {
        return operation_id;
    }
}
