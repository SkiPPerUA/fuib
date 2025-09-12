package org.example.qaTransactionTeam.backEnd.payHub;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Donation extends Restful {

    protected static final Logger logger = Logger.getLogger(Donation.class);
    private String response;
    private String host = "https://innsmouth.stage-fuib.com";

    public Donation(){
        RestAssured.useRelaxedHTTPSValidation();
    }

    public void createDonat(String body) {
        logger.info("Создание Donate");
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
    }

    public void getReceiptData(String donation_id){
        request(given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .when()
                .get(host + "/frames/donations/"+donation_id+"/receipts"));
    }

}
