package org.example.qaTransactionTeam.backEnd.payHub;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import static io.restassured.RestAssured.given;

public class Donation {

    protected static final Logger logger = Logger.getLogger(Donation.class);
    private String response;
    //private String host = "https://innsmouth.test-fuib.com";
    private String host = "https://innsmouth.payhub.com.ua";

    public void createDonat(String body) {

        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .body(body)
                .when()
                .post(host + "/donations/pay")
                .then()
                .statusCode(200)
                .extract()
                .response().asString();

        logger.info("Создание Donate - " + response);
    }

}
