package org.example.qaTransactionTeam.backEnd.admin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Limits {

    private static final Logger logger = Logger.getLogger(Limits.class);
    private String response;
    private JSONObject json;
    private String limits_id;
    private int status_code = 200;
    private int status_code_put = 204;
    private Trans_token_payhub token;

    {
        try {
            token = new Trans_token_payhub();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getLimits(String id_config){
        if (id_config.toCharArray().length > 1){
            id_config = "/"+id_config;
        }
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/admin/limits/configs"+id_config)
                .then()
                .statusCode(status_code)
                .extract().response().asString();

        logger.info("Get Limits - "+response);
    }

    public void getLimits(){
        getLimits("");
    }

    public void createLimits(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/admin/limits/configs")
                .then()
                .statusCode(status_code)
                .extract().response().asString();

        logger.info("Create Limits - "+response);
        json = new JSONObject(response);
        limits_id = json.getString("id");

    }

    public void createSelfLimits(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/admin/limits/self-configs")
                .then()
                .statusCode(status_code)
                .extract().response().asString();

        logger.info("Create self-limits - "+response);
    }

    public void updateLimits(String body, String id_config){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .put(token.getHost()+"/admin/limits/configs/"+id_config)
                .then()
                .statusCode(status_code_put)
                .extract().response().asString();

        logger.info("Update Limits - "+response);
    }

    public void clientsInfoLimits(String body){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("x-flow-id", Uuid_helper.generate_uuid())
                .body(body)
                .when()
                .post(token.getHost()+"/limits/fuib/clients")
                .then()
                .statusCode(status_code)
                .extract().response().asString();

        logger.info("Clients info limits - "+response);
    }

    public void relatives(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("x-flow-id", Uuid_helper.generate_uuid())
                .body(body)
                .when()
                .post(token.getHost()+"/limits/fuib/relatives")
                .then()
                .statusCode(204)
                .extract().response().asString();


        logger.info("Relatives - "+response);
    }

    public void setMerchantsLimits(boolean body, String merchant_id){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{ \"full_ident\": "+body+" }")
                .when()
                .put(token.getHost()+"/admin/merchants/"+merchant_id+"/configs/full-ident")
                .then()
                .statusCode(204)
                .extract().response().asString();

        logger.info("Set Merchants Limits for "+merchant_id+" - "+body);
    }

    public void getMerchantsLimits(String merchant_id){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/admin/merchants/"+merchant_id+"/configs/full-ident")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Get Merchants Limits for "+merchant_id+" = "+response);
    }

    public void getIb_entries(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/admin/limits/ib-entries")
                .then()
//                .statusCode(200)
                .extract().response().asString();

        logger.info("Get Ib_entries Limits = "+response);
    }

    public void getMirindaLimits(String sirius_id){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/admin/limits/mirinda-limits?sirius_id="+sirius_id)
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Get Mirinda Limits for sirius_id "+sirius_id+" = "+response);
    }

    public String getResponse() {
        return response;
    }

    public String getLimits_id() {
        return limits_id;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public void setStatus_code_put(int status_code_put) {
        this.status_code_put = status_code_put;
    }
}
