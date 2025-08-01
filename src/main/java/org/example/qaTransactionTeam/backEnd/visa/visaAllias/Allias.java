package org.example.qaTransactionTeam.backEnd.visa.visaAllias;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;

import static io.restassured.RestAssured.given;


public class Allias {

    private static final Logger logger = Logger.getLogger(Allias.class);
    private String response;
    private int statusCode = 200;
    private String guid;
    private String host = "https://api.test-fuib.com/tsys/zsasz/v1/";
    //private String host = "https://api.fuib.com/tsys/hush-rpc-client/v1/";//PROD
    //private String host = "http://localhost:8081/";
    private Apiman apiman = new Apiman("RPO","va_opr");

    public void create(String body){

        response = given()
                .contentType(ContentType.JSON)
                .header("full-return","false")
                .header("Authorization","Bearer "+apiman.getToken())
                .body(body)
                .when()
                .post(host+"aliases")
                .then()
//                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Create allias: "+response);
    }

    public void resolve(String body){

        response = given()
                .contentType(ContentType.JSON)
                .header("full-return","false")
                .header("Authorization","Bearer "+apiman.getToken())
                .body(body)
                .when()
                .post(host+"resolve")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Resolve allias: "+response);
    }

    public void available(String body){

        response = given()
                .contentType(ContentType.JSON)
                .header("full-return","false")
                .header("X-Systemcode","1")
                .header("X-Flow-ID","2")
                .header("X-Username","3")
                .header("Authorization","Bearer "+apiman.getToken())
                .body(body)
                .when()
                .post(host+"available-aliases")
                .then()
//                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Available allias: "+response);
    }

    public void update(String body){

        response = given()
                .contentType(ContentType.JSON)
                .header("full-return","false")
                .header("Authorization","Bearer "+apiman.getToken())
                .body(body)
                .when()
                .put(host+"aliases")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Update allias: "+response);
    }

    public void delete(String body){

        response = given()
                .contentType(ContentType.JSON)
                .header("full-return","false")
                .header("Authorization","Bearer "+apiman.getToken())
                .body(body)
                .when()
                .delete(host+"aliases")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Delete allias: "+response);
    }

    public void makeReport(String body){

        response = given()
                .contentType(ContentType.JSON)
                .header("full-return","false")
                .header("Authorization","Bearer "+apiman.getToken())
                .body(body)
                .when()
                .post(host+"reports")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Make Report: "+response);
    }

    public void getReport(String curlReport){

        response = given()
                .contentType(ContentType.JSON)
                .header("full-return","false")
                .header("Authorization","Bearer "+apiman.getToken())
                .when()
                .get(host+"reports/"+curlReport)
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Get Report: "+response);
    }

    public void status(String guid){

        response = given()
                .contentType(ContentType.JSON)
                .header("full-return","false")
                .header("Authorization","Bearer "+apiman.getToken())
                .when()
                .get(host+"aliases/"+guid)
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Get status for allias: "+response);
    }

    public String getResponse() {
        return response;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
