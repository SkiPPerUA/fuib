package org.example.qaTransactionTeam.backEnd.yupi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.utils.Restful;
import static io.restassured.RestAssured.given;

public class Cards extends Restful {

    private final Logger logger = Logger.getLogger(Cards.class);

    public Cards(){
        RestAssured.useRelaxedHTTPSValidation();
    }

    public void getTWOconverting(String card_id, String part_id){
        request(given().
                contentType(ContentType.JSON)
                .header("Authorization", "Bearer")
                .header("X-Flow-ID", Uuid_helper.generate_uuid())
                .header("X-Systemcode", Uuid_helper.generate_uuid())
                .header("X-Username","Vlad")
                .queryParam("card_id", card_id)
                .queryParam("part_id", part_id)
                .get("https://api.test-fuib.com/gbs/yupi/v1/kissel/v1/cards/dynamic-currency-conversions"));
    }

    public void patchTWOconverting(String card_id, String part_id, String body){
        request(given().
                contentType(ContentType.JSON)
                .header("Authorization", "Bearer")
                .header("X-Flow-ID", Uuid_helper.generate_uuid())
                .header("X-Systemcode", Uuid_helper.generate_uuid())
                .header("X-Username","Vlad")
                .queryParam("card_id", card_id)
                .queryParam("part_id", part_id)
                .body(body)
                .patch("https://api.test-fuib.com/gbs/yupi/v1/kissel/v1/cards/dynamic-currency-conversions"));
    }

    public void getTokens(String card_id){
        logger.info("getTokens");
        request(given().
                contentType(ContentType.JSON)
                .header("Authorization", "Bearer")
                .header("X-Flow-ID", Uuid_helper.generate_uuid())
                .header("X-Systemcode", Uuid_helper.generate_uuid())
                .header("X-Username","Vlad")
                .queryParam("card_id", card_id)
                .get("https://api.test-fuib.com/gbs/yupi/v1/kissel/v1/cards/tokens"));
    }
}
