package org.example.qaTransactionTeam.backEnd.dazzler;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class DeepLink {

    protected static final Logger logger = Logger.getLogger(DeepLink.class);
    private String response;
    private String host = "https://innsmouth.test-fuib.com";
    private String deeplink_value;

    public void createDeppLink(String body) {

        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .body(body)
                .when()
                .post(host + "/dazzler/repcard/deeplinks")
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("Создание DeepLink - " + response);
        JSONObject json = new JSONObject(response);
        deeplink_value = json.getString("deeplink_value");
    }

    public void getDeppLink(String id) {

        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .when()
                .get(host + "/dazzler/repcard/deeplinks/"+id)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();

        logger.info("Get DeepLink - " + response);
    }

    public void getReceiverInfoDeppLink(String id) {

        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .when()
                .get(host + "/frames/repcard/"+id)
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("Get receiver info DeepLink - " + response);
    }
}
