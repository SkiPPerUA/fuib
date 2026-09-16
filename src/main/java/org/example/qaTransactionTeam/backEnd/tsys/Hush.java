package org.example.qaTransactionTeam.backEnd.tsys;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Hush extends Restful {

    private static final Logger logger = Logger.getLogger(Hush.class);
    private Apiman token = new Apiman("GBS","svc_ph_hush_t","qYaG%hc#WBMcBZH3H9f55jNtTLfz6nuE","stage");

    public void getVisaTransactions(String params){
        logger.info("getVisaTransactions");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .header("x-flow-id", Uuid_helper.generate_uuid())
                .header("x-systemcode", "59")
                .get("https://api."+token.getEnvironment()+"-fuib.com/tsys/hush/v1/visa-transactions"+params));
    }

    public void visaValidation(String body){
        logger.info("visaValidation");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .header("x-flow-id", Uuid_helper.generate_uuid())
                .header("x-systemcode", "59")
                .body(body)
                .post("https://api."+token.getEnvironment()+"-fuib.com/tsys/hush/v1/visa-card-validations"));
    }
}
