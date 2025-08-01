package org.example.qaTransactionTeam.backEnd.visa.requestToPay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class AndrewGarner extends Restful {

    private final Logger logger = Logger.getLogger(AndrewGarner.class);
    private Apiman token = new Apiman("ITM","svc_ph_tusk_t","J!h6xXW6&LwfJnQ&kLaQBNezdBrzijwd", "stage");
    private String env;
    private RequestSpecification requestSpecification;

    public AndrewGarner(){
        RestAssured.useRelaxedHTTPSValidation();
        env = "https://api."+token.getEnvironment()+"-fuib.com/tsys/andrew-garner/v1/";
        requestSpecification = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("X-Flow-ID", Uuid_helper.generate_uuid())
                .header("X-Systemcode",Uuid_helper.generate_uuid());
    }

    public void init(String body){
        logger.info("init");
        request(requestSpecification.body(body).when().post(env+"visa-req2pay-inbounds"));
    }

    public void referenceData(String body){
        logger.info("referenceData");
        request(requestSpecification.body(body).when().post(env+"visa-req2pay-inbounds/reference-data"));
    }

    public void refund(String payment_request_id, String body){
        logger.info("refund -> "+payment_request_id);
        request(requestSpecification.body(body).when().post(env+"visa-req2pay-inbounds/"+payment_request_id+"/refund"));
    }

    public void retrieve(String payment_request_id){
        logger.info("retrieve -> "+payment_request_id);
        request(requestSpecification.when().get(env+"visa-req2pay-inbounds/"+payment_request_id));
    }

    public void amend(String payment_request_id, String body){
        logger.info("amend -> "+payment_request_id);
        request(requestSpecification.body(body).when().patch(env+"visa-req2pay-inbounds/"+payment_request_id+"/amends"));
    }

    public void tag(String body){
        logger.info("tag");
        request(requestSpecification.body(body).when().post(env+"visa-req2pay-inbounds/transactions/tags"));
    }

    public void confirm(String payment_request_id, String body){
        logger.info("confirm -> "+payment_request_id);
        request(requestSpecification.body(body).when().patch(env+"visa-req2pay-inbounds/"+payment_request_id+"/confirms"));
    }

    public void cancel(String payment_request_id, String body){
        logger.info("confirm -> "+payment_request_id);
        request(requestSpecification.body(body).when().patch(env+"visa-req2pay-inbounds/"+payment_request_id+"/cancels"));
    }

    public void retrieves(String body){
        logger.info("retrieves");
        request(requestSpecification.body(body).when().post(env+"visa-req2pay-inbounds/retrieves"));
    }

    public void viewBlock(String body){
        logger.info("viewBlock");
        request(requestSpecification.body(body).when().post(env+"visa-req2pay-controls/views"));
    }

    public void removeBlock(String reference_id, String body){
        logger.info("removeBlock");
        request(requestSpecification.body(body).when().patch(env+"visa-req2pay-controls/"+reference_id+"/removes"));
    }

}
