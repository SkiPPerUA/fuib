package org.example.qaTransactionTeam.backEnd.bus;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Shazam_token;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Shazam {

    private static final Logger logger = Logger.getLogger(Shazam.class);
    private Auth_token token = new Shazam_token("svc_ph_test_doc", "s53cC8eBY%b#Jmg!RhM948J!F3nbucrr");
    private Response response;

    public void deleteTrans(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .body(body)
                .when()
                .delete("https://api.test-fuib.com/bus/shazam/common/api/v1/scrooge/TransferControl");
        logger.info("deleteTrans - "+getResponce());
    }

    public String getResponce(){
        return response.then().extract().response().asString();
    }
}
