package org.example.qaTransactionTeam.backEnd.payHub;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class ChangeTransStatus {

    private static final Logger logger = Logger.getLogger(ChangeTransStatus.class);
    private Response response;
    private Trans_token_payhub token = new Trans_token_payhub();

    public void check_status(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .put(token.getHost()+"/admin/transactions/check-status");
        logger.info("Put configs = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),204);
    }

    public void update_status(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .put(token.getHost()+"/admin/transactions/update-status");
        logger.info("Put configs = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),204);
    }

    public String getResponse(){
        return response.then().extract().response().asString();
    }
}
