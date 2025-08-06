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
    private String payment_request_id;
    private String end_to_end_id;

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
        if (response.then().extract().statusCode() == 201){
            payment_request_id = response.then().extract().jsonPath().getString("payment_requests.payment_request_id").replace("[","").replace("]","");
            end_to_end_id = response.then().extract().jsonPath().getString("payment_requests.end_to_end_id").replace("[","").replace("]","");
        }
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

    public void confirm(String body){
        logger.info("confirm -> "+payment_request_id);
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("X-Flow-ID", Uuid_helper.generate_uuid())
                .header("X-Systemcode",Uuid_helper.generate_uuid()).header("x-request-affinity",payment_request_id).body(body).log().all()
                .when().patch(env+"visa-req2pay-inbounds/"+payment_request_id+"/confirms"));

        System.out.println(response.then().extract().headers().asList());
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

    public String getPayment_request_id() {
        return payment_request_id;
    }

    public String getEnd_to_end_id() {
        return end_to_end_id;
    }
}
