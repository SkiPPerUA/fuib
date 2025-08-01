package org.example.qaTransactionTeam.backEnd.visa.visaAllias;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;

import static io.restassured.RestAssured.given;

public class CardsOnFiles {
    private static final Logger logger = Logger.getLogger(CardsOnFiles.class);
    private String response;
    private int statusCode = 200;
    private String host = "https://api.test-fuib.com/tsys/zsasz/v1/cards-on-files";
    private Apiman token = new Apiman("GBS","vsp_opr");

    public void cardsOnFiles(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Systemcode","1")
                .header("X-Flow-ID","2")
                .header("X-Username","Username")
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(host)
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Cards on files : "+response);
    }
}
