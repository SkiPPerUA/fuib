package org.example.qaTransactionTeam.backEnd.counters;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Counters extends Restful {

    private Auth_token token = new Trans_token_payhub();
    private final Logger logger = Logger.getLogger(Counters.class);

    public void getCounter(String client_id, String kind){
        logger.info(String.format("Get counter %s for %s",kind,client_id));
        request(given()
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/counters?client_id="+client_id+"&kind="+kind));
    }
}
