package org.example.qaTransactionTeam.backEnd.visa.requestToPay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Tusk extends Restful {

    private final Logger logger = Logger.getLogger(Tusk.class);
    private Auth_token token = new Trans_token_payhub("svc_tsys_rtpo_t", "jjw#&HCu42%SkCf79pg5Xf4nyxeS3v3&", "transacter");
    private RequestSpecification requestSpecification;

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
                .header("key_id", "test")
                .header("code", "test")
                .body(body).when().post(token.getHost()+"/outbounds/visa-req2pay-outbounds/initiates"));
//        if (response.then().extract().statusCode() == 201){
//            payment_request_id = response.then().extract().jsonPath().getString("payment_requests.payment_request_id").replace("[","").replace("]","");
//            end_to_end_id = response.then().extract().jsonPath().getString("payment_requests.end_to_end_id").replace("[","").replace("]","");
//        }
    }
}
