package org.example.qaTransactionTeam.backEnd.wonderWoman;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.ronan.Ronan;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static io.restassured.RestAssured.given;

public class WonderWoman {

    private static final Logger logger = Logger.getLogger(WonderWoman.class);
    private Response response;
    private String host = "http://wonderw.test-fuib.com"; //"http://wonderw.stage2-fuib.com" "http://wonderw.dev-fuib.com" "http://wonderw.test-fuib.com"

    public void getStatus(String acq, String sessionId){
        response = given()
                .when()
                .get(host+"/api/v2/external/get-transfers/"+acq+"/"+sessionId+"");
        logger.info("Status transaction ("+sessionId+") = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    public void getStatusForSupport(String sessionId){
        response = given()
                .when()
                .get(host+"/api/v2/transfers/admin/get-transfers?rrfs="+sessionId+"");
        logger.info("Status transaction ("+sessionId+") = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    public void getStatus(String acq, String from_date, String to_date) throws URISyntaxException, NoSuchAlgorithmException, IOException, InterruptedException, KeyManagementException, TimeoutException {
        Ronan ronan = new Ronan();
        ronan.token();
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("Token",ronan.getToken())
                .header("X-FORWARDED-FOR","10.4.88.107,10.4.88.107")
                .header("X-Fuib-Api-Version","v1")
                .queryParam("from_date",from_date)
                .queryParam("to_date",to_date)
                .when()
                .get(host+"/api/v1/transfers/transfersHistory/"+acq+"");
        logger.info("Status transaction = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),200);
        ronan.rabbit.closeConn();

    }

    public String getResponse() {
        return response.then().extract().response().asString();
    }
}
