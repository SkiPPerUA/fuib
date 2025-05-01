package org.example.qaTransactionTeam.backEnd.admin;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Reports extends Restful {

    private Auth_token token = new Trans_token_payhub();
    private Logger logger = Logger.getLogger(Reports.class);

    public void getMonoRegister(int year, int month, int day){
        logger.info("Get MonoBank register");
        super.expectedCode = 204;
        request(given()
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(String.format(token.getHost()+"/admin/reports/monobank/to-email?year=%s&month=%s&day=%s&email=vladyslav.savchuk@valuetek.com.ua",year,month,day)));
    }
}
