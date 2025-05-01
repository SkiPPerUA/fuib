package org.example.qaTransactionTeam.backEnd.itm;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class GetTransDetByStan {

    private static final Logger logger = Logger.getLogger(GetTransDetByStan.class);
    final String token;
    private String response;
    final String acqId;

    public String getResponse() {
        return response;
    }

    public GetTransDetByStan() throws JSONException {
        Trans_token_itm tok = new Trans_token_itm();
        token = tok.getToken();
        acqId = tok.getAcqID();
    }


    public void GetDetailsByStan(String stan){
        String url = "https://tsystestapi.pumb.ua/vmt/api/v2/transfers/get-transfers/"+acqId+"/stan/"+stan+"";

        response = given()
                .contentType(ContentType.JSON)
                .header("token", token)
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();

        logger.info("Get по stan - "+response);
    }
}
