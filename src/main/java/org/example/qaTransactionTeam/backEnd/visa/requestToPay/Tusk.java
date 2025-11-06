package org.example.qaTransactionTeam.backEnd.visa.requestToPay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class Tusk extends Restful {

    private final Logger logger = Logger.getLogger(Tusk.class);
    private Auth_token token = new Trans_token_payhub("svc_tsys_rtpo_t", "jjw#&HCu42%SkCf79pg5Xf4nyxeS3v3&", "transacter");
    private RequestSpecification requestSpecification;
    private String payment_request_id;
    private String end_to_end_id;

    public Tusk(){
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("X-Flow-ID", Uuid_helper.generate_uuid())
                .header("X-Systemcode",Uuid_helper.generate_uuid());
    }

    public void init(String body){
        logger.info("init");
        request(requestSpecification
                .header("X-Request-Affinity", "YY"+String.valueOf(new Random().nextLong()).substring(1,14)+"YY")
                .body(body).when().post(token.getHost()+"/visa-req2pay-outbounds/initiates"));
        if (response.then().extract().statusCode() == 201){
            payment_request_id = response.then().extract().jsonPath().getString("payment_requests.payment_request_id").replace("[","").replace("]","");
            end_to_end_id = response.then().extract().jsonPath().getString("payment_requests.end_to_end_id").replace("[","").replace("]","");
        }
    }

    public void tags(String body){
        logger.info("tags");
        request(requestSpecification.body(body).when().post(token.getHost()+"/visa-req2pay-outbounds/transactions/tags"));
    }

    public void refunds(String original_payment_request_id, String body){
        logger.info("refunds");
        request(requestSpecification
                .header("X-Request-Affinity", "YY"+String.valueOf(new Random().nextLong()).substring(1,14)+"YY")
                .header("original_payment_request_id", original_payment_request_id)
                .body(body).when().post(token.getHost()+"/visa-req2pay-outbounds/"+original_payment_request_id+"/refunds"));
    }

    public void cancels(String payment_request_id, String body){
        logger.info("cancels -> "+payment_request_id);
        request(requestSpecification
                .header("X-Request-Affinity", "YY"+String.valueOf(new Random().nextLong()).substring(1,14)+"YY")
                .header("payment_request_id", payment_request_id+"01")
                .body(body).when().patch(token.getHost()+"/visa-req2pay-outbounds/"+payment_request_id+"/cancels"));
    }

    public void confirms(String payment_request_id, String body){
        logger.info("confirms");
        request(requestSpecification
                .header("X-Request-Affinity", "YY"+String.valueOf(new Random().nextLong()).substring(1,14)+"YY")
                .header("payment_request_id", payment_request_id)
                .body(body).when().patch(token.getHost()+"/visa-req2pay-outbounds/"+payment_request_id+"/confirms"));
    }

    public void amends(String payment_request_id, String body){
        logger.info("amends");
        request(requestSpecification
                .header("X-Request-Affinity", "YY"+String.valueOf(new Random().nextLong()).substring(1,14)+"YY")
                .header("payment_request_id", payment_request_id)
                .body(body).when().patch(token.getHost()+"/visa-req2pay-outbounds/"+payment_request_id+"/amends"));
    }

    public void notifications(String body){
        logger.info("notifications");
        request(requestSpecification.body(body).when().post(token.getHost()+"/visa-req2pay-outbounds/notifications"));
    }

    public void getCardNumberByCardId(String card_id){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("getCardNumberByCardId","Cards:input");
        rabbitMQHttp.sendHttp("{\"card_id\":\""+card_id+"\"}");
    }

    public String getPayment_request_id() {
        return payment_request_id;
    }
}
