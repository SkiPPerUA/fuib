package org.example.qaTransactionTeam.backEnd.visa.visaAllias;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;

import static io.restassured.RestAssured.given;

public class StopInstructions {

    private static final Logger logger = Logger.getLogger(StopInstructions.class);
    private String response;
    private int statusCode = 200;
    private String host = "https://api.test-fuib.com/tsys/zsasz/v1/stop-instructions";
    private Apiman token = new Apiman("GBS","vsp_opr");

    public void addMerchants(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Systemcode","X-Systemcode")
                .header("X-Flow-ID","X-Flow-ID")
                .header("X-Username","X-Username")
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(host+"/merchants")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Add merchants: "+response);
    }

    public void deleteInstructions(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Systemcode","X-Systemcode")
                .header("X-Flow-ID","X-Flow-ID")
                .header("X-Username","X-Username")
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .delete(host)
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Delete Instructions: "+response);
    }

    public void findInstructionsByPan(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Systemcode","X-Systemcode")
                .header("X-Flow-ID","X-Flow-ID")
                .header("X-Username","X-Username")
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(host)
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Find Instructions: "+response);
    }

    public void continueInstructions(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Systemcode","X-Systemcode")
                .header("X-Flow-ID","X-Flow-ID")
                .header("X-Username","X-Username")
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(host+"/extentions")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Сontinue Instructions: "+response);
    }

    public void getInstructions(String instruction_id){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Systemcode","X-Systemcode")
                .header("X-Flow-ID","X-Flow-ID")
                .header("X-Username","X-Username")
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(host+"/"+instruction_id+"/details")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Get Instructions: "+response);
    }

    public void getAllTransactions(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Systemcode","X-Systemcode")
                .header("X-Flow-ID","X-Flow-ID")
                .header("X-Username","X-Username")
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(host+"/transactions")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Get all transactions: "+response);
    }

    public String getResponse() {
        return response;
    }
}
