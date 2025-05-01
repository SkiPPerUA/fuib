package org.example.qaTransactionTeam.backEnd.payHub;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;

public class VisaAlias {

    private final Logger logger = Logger.getLogger(VisaAlias.class);
    //private TokenPayHub token = new TokenPayHub("svc_ph_Dpgrxujvs","GBNKHDrfdxcjm854267", Configs.MOBYPAY_CLIENT,"https://rlyeh.payhub.com.ua");
    private Trans_token_payhub token = new Trans_token_payhub();
    private String response;
    private String uid;

    public void findAlias(String alias){
        response = given()
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/transactions/visa-alias?alias="+alias+"")
                .then()
                .extract().response().asString();

        logger.info("Find Alias = "+response);
        JSONObject json = new JSONObject(response);
        uid = json.getString("uid");
    }

    public String getUid() {
        return uid;
    }

    public String getResponse() {
        return response;
    }
}
