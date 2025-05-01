package org.example.qaTransactionTeam.backEnd.itm;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class GetTransDetByExternal {

    private static final Logger logger = Logger.getLogger(GetTransDetByExternal.class);
    final String token;
    private String response;
    final String acqId;

    public String getResponse() {
        return response;
    }

    public GetTransDetByExternal() throws JSONException {
        Trans_token_itm tok = new Trans_token_itm();
        token = tok.getToken();
        acqId = tok.getAcqID();
    }

    public void GetDetailsByExternal(String external){
        String url = "https://tsystestapi.pumb.ua/vmt/api/v2/transfers/get-transfers/"+acqId+"/external/"+external+"";

        response = given()
                .contentType(ContentType.JSON)
                .header("token", token)
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();

        logger.info("Get по external - "+response);
    }
}
