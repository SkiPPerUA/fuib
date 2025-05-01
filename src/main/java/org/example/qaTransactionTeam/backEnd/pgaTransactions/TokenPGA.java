package org.example.qaTransactionTeam.backEnd.pgaTransactions;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class TokenPGA {

    private static final Logger logger = Logger.getLogger(TokenPGA.class);
    private String portalId = "9564166CB48FC7898F54E5149BEFC940";
    private String response;
    private String token;

    public TokenPGA(){
         response = given()
                .contentType(ContentType.URLENC)
                .when()
                .post("https://pps03.fuib.com:9443/api/v4/"+portalId+"/token")
                .then().extract().response().asString();
        JSONObject jsonToken = new JSONObject(response);
        token = jsonToken.getString("token");
        logger.info("TokenPGA = "+token);
        logger.info("PortalID = "+portalId);

    }

    public TokenPGA(String portalId){
        this.portalId = portalId;
        response = given()
                .contentType(ContentType.URLENC)
                .when()
                .post("https://pps03.fuib.com:9443/api/v4/"+portalId+"/token")
                .then().extract().response().asString();
        JSONObject jsonToken = new JSONObject(response);
        token = jsonToken.getString("token");
        logger.info("token = "+token);
        logger.info("PortalID = "+portalId);

    }

    public String getToken() {
        return token;
    }

    public String getResponse() {
        return response;
    }

    public String getPortalId() {
        return portalId;
    }
}
