package org.example.qaTransactionTeam.backEnd.notificationBP;


import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class ApiNotification {

    private static final Logger logger = Logger.getLogger(ApiNotification.class);
    private String response;
    private Apiman token;
    private Apiman tokenForBatch;

    {
        try {
            tokenForBatch = new Apiman("ITM","pc_trn_mng");
        } catch (JSONException e) {
            logger.error("Ошибка Apiman - "+e);
        }
        try {
            token = new Apiman("ITM","pc_trn_r");
        } catch (JSONException e) {
            logger.error("Ошибка Apiman - "+e);
        }
    }

    public void statistics(String startdate, String finishdate){

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("Start-Date",startdate)
                .header("Finish-Date",finishdate)
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode","7777777")
                .when()
                .get("https://api."+token.getEnvironment()+"-fuib.com/tsys/beyonder/v1/notifications/card-transactions/statistics")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Ответ = "+response);

    }

    public void statistics(String startdate, String finishdate, String codeBP){

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("Start-Date",startdate)
                .header("Finish-Date",finishdate)
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode","7777777")
                .when()
                .get("https://api."+token.getEnvironment()+"-fuib.com/tsys/beyonder/v1/notifications/card-transactions/statistics?bank_id="+codeBP+"")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Ответ для "+codeBP+" = "+response);

    }

    public void cardTransactions(String limit, String offset,String status){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("Limit",limit)
                .header("Offset",offset)
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode","7777777")
                .when()
                .get("https://api."+token.getEnvironment()+"-fuib.com/tsys/beyonder/v1/notifications/card-transactions?status="+status+"")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Ответ для статуса "+status+" = "+response);
    }

    public void cardTransactions(String limit, String offset,String status,String startdate, String finishdate){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("Start-Date",startdate)
                .header("Finish-Date",finishdate)
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode","7777777")
                .when()
                .get("https://api."+token.getEnvironment()+"-fuib.com/tsys/beyonder/v1/notifications/card-transactions?status="+status+"")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Ответ для статуса "+status+" = "+response);
    }

    public void findByStan(String stan){

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode","7777777")
                .when()
                .get("https://api."+token.getEnvironment()+"-fuib.com/tsys/beyonder/v1/notifications/card-transactions/"+stan+"")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Ответ для "+stan+" = "+response);
    }

    public void findByRequestId(String requestId){

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode","7777777")
                .when()
                .get("https://api."+token.getEnvironment()+"-fuib.com/tsys/beyonder/v1/notifications/card-transactions/requests/"+ requestId +"")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Ответ для "+ requestId +" = "+response);
    }

    public void findActiveBatch(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ tokenForBatch.getToken())
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode","7777777")
                .when()
                .get("https://api."+tokenForBatch.getEnvironment()+"-fuib.com/tsys/beyonder/v1/notifications/card-transactions/batch-processes?status=ACTIVE")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Найден пакет = "+response);
    }

    public void resendNegativeNotification(String startdate, String finishdate){

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+tokenForBatch.getToken())
                .header("Start-Date",startdate)
                .header("Finish-Date",finishdate)
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode","7777777")
                .when()
                .post("https://api."+tokenForBatch.getEnvironment()+"-fuib.com/tsys/beyonder/v1/notifications/card-transactions/batch-processes")
                .then()
                .statusCode(200)
                .extract().response().asString();

    }


    public String getResponse() {
        return response;
    }
}
