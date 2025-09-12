package org.example.qaTransactionTeam.backEnd.visa.requestToPay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class ArthurVector extends Restful {

    private final Logger logger = Logger.getLogger(ArthurVector.class);
    private Apiman token = new Apiman("ITM","svc_ph_tusk_t","J!h6xXW6&LwfJnQ&kLaQBNezdBrzijwd", "stage");
    private String env;
    private RequestSpecification requestSpecification;
    private String payment_request_id;
    private String end_to_end_id;

    public ArthurVector(){
        RestAssured.useRelaxedHTTPSValidation();
        env = "https://api."+token.getEnvironment()+"-fuib.com/rtx/api/outbound/v1/requestToPay";
        requestSpecification = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken());
    }

    public void init(String body){
        logger.info("init");
        request(requestSpecification
                .header("keyId", "test")
                .header("x-request-affinity", "YY"+String.valueOf(new Random().nextLong()).substring(1,14)+"YY")
                .body(body).when().post(env));
//        if (response.then().extract().statusCode() == 201){
//            payment_request_id = response.then().extract().jsonPath().getString("payment_requests.payment_request_id").replace("[","").replace("]","");
//            end_to_end_id = response.then().extract().jsonPath().getString("payment_requests.end_to_end_id").replace("[","").replace("]","");
//        }
    }

    public void notifications(String body){
        logger.info("notifications");
        request(requestSpecification
                .header("keyId", "test")
                .body(body).when().post(env+"/notifications"));
    }

    public void refund(String payment_request_id, String body){
        logger.info("refund -> "+payment_request_id);
        request(requestSpecification
                .header("keyId", "test")
                .header("x-request-affinity", "YY"+String.valueOf(new Random().nextLong()).substring(1,14)+"YY")
                .header("paymentRequestId", payment_request_id)
                .body(body).when().post(env+"/"+payment_request_id+"/refund"));
    }

    public void amend(String payment_request_id, String body){
        logger.info("amend -> "+payment_request_id);
        request(requestSpecification
                .header("keyId", "test")
                .header("x-request-affinity", "YY"+String.valueOf(new Random().nextLong()).substring(1,14)+"YY")
                .header("paymentRequestId", payment_request_id)
                .body(body).when().patch(env+"/"+payment_request_id+"/amend"));
    }

    public void tag(String body){
        logger.info("tag");
        request(requestSpecification
                .header("keyId", "test")
                .body(body)
                .when().post(env+"/transactions/tags"));
    }

    public void confirm(String paymentRequestId, String body){
        logger.info("confirm -> "+payment_request_id);
        request(requestSpecification
                .header("keyId", "test")
                .header("x-request-affinity", paymentRequestId+"YY")
                .header("paymentRequestId", paymentRequestId)
                .body(body)
                .when().patch(env+"/"+paymentRequestId+"/confirm"));
    }

    public void cancel(String payment_request_id, String body){
        logger.info("cancel -> "+payment_request_id);
        request(requestSpecification
                .header("keyId", "test")
                .header("x-request-affinity", "YY"+String.valueOf(new Random().nextLong()).substring(1,14)+"YY")
                .header("paymentRequestId", payment_request_id)
                .body(body)
                .when().patch(env+"/"+payment_request_id+"/cancel"));
    }

    public String getPayment_request_id() {
        return payment_request_id;
    }

    public String getEnd_to_end_id() {
        return end_to_end_id;
    }
}
