package org.example.qaTransactionTeam.backEnd.visa.requestToPay;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Request2Pay extends Restful {

    private final Logger logger = Logger.getLogger(Request2Pay.class);
    String url = "";

    public void confirm(String payment_request_id, String body){
        logger.info("confirm -> "+payment_request_id);
        request(given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .header("x-request-affinity","7272")
                .body(body)
                .when()
                .patch(url+"/outbound/request-to-pays/"+payment_request_id+"/confirm"));
    }

    public void tag(String body){
        logger.info("tag");
        request(given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .body(body)
                .when()
                .post(url+"/outbound/request-to-pays/transactions/tag"));
    }

    public void refund(String payment_request_id, String body){
        logger.info("refund -> "+payment_request_id);
        request(given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .header("x-request-affinity","7272")
                .header("original_payment_request_id",payment_request_id)
                .body(body)
                .when()
                .post(url+"/outbound/request-to-pays/"+payment_request_id+"/refund"));
    }

    public void cancel(String payment_request_id, String body){
        logger.info("cancel -> "+payment_request_id);
        request(given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .header("x-request-affinity","7272")
                .header("original_payment_request_id",payment_request_id)
                .body(body)
                .when()
                .patch(url+"/outbound/request-to-pays/"+payment_request_id+"/cancel"));
    }

    public void init(String body){
        logger.info("init");
        request(given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .header("x-request-affinity","7272")
                .body(body)
                .when()
                .post(url+"/outbound/request-to-pays/initiate"));
    }

    public void amend(String payment_request_id, String body){
        logger.info("amend");
        request(given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .header("x-request-affinity","7272")
                .header("original_payment_request_id",payment_request_id)
                .body(body)
                .when()
                .patch(url+"/outbound/request-to-pays/"+payment_request_id+"/amend"));
    }

    public void notifications(String body){
        logger.info("notifications");
        request(given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .body(body)
                .when()
                .post(url+"/outbound/request-to-pays/notifications"));
    }
}
