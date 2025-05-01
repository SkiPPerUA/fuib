package org.example.qaTransactionTeam.backEnd.shCompliance;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class SHCompliance extends Restful {

    private final Logger logger = Logger.getLogger(SHCompliance.class);
    private final Apiman token = new Apiman("ODB","svc_ph_sh2_t","HdSv7zuBYsHc%MNYwTek4zizjBTk42XN","test");

    public void setDatasetValues(String body){
        logger.info("setDatasetValues");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post("https://api.test-fuib.com/gbs/sh-compliance/setDatasetValues"));
    }

    public void getDatasetValues(String body){
        logger.info("getDatasetValues");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post("https://api.test-fuib.com/gbs/sh-compliance/getDatasetValue"));
    }
}
