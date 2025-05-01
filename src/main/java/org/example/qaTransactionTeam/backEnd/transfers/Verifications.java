package org.example.qaTransactionTeam.backEnd.transfers;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.utils.Restful;
import static io.restassured.RestAssured.given;

public class Verifications extends Restful {

    private final Logger logger = Logger.getLogger(Verifications.class);

    public void our_verif(String body){
        logger.info("our_verif");
        request(given().
                contentType(ContentType.JSON)
                .header("Authorization", "Bearer 12")
                .header("X-Flow-ID", Uuid_helper.generate_uuid())
                .body(body)
                .post("https://innsmouth.payhub.com.ua/transfers/verifications"));
    }
}
