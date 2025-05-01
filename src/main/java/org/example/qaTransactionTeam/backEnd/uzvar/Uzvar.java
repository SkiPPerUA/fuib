package org.example.qaTransactionTeam.backEnd.uzvar;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Uzvar extends Restful {

    private static final Logger logger = Logger.getLogger(Uzvar.class);
    private String host = "https://core-dbgate.stage-fuib.com/secure/uzvar/v1/";
    private final Apiman apiman = new Apiman("ITM","ph_int", "stage");

    public void itm_tokens(String pan){
        logger.info("Получение Итм токена");
        request(given()
                .contentType(ContentType.JSON)
                .header("full-return","false")
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode", "2")
                .header("Authorization","Bearer "+apiman.getToken())
                .body("{\"pan\": \""+pan+"\"}")
                .when()
                .post(host+"cards/itm-tokens"));
    }

    public void pan_by_token(String token){
        logger.info("Получение PAN по токену");
        request(given()
                .contentType(ContentType.JSON)
                .header("full-return","false")
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode", "2")
                .header("Authorization","Bearer "+apiman.getToken())
                .when()
                .get(host+"cards/card-numbers?token="+token));
    }
}
