package org.example.qaTransactionTeam.backEnd.payHub.frames;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.json.JSONException;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class AcquiringFrameRecurrent {

    private String type = "pga";
    private Response response;
    private static Logger logger = Logger.getLogger(AcquiringFrameRecurrent.class);
    private Auth_token token;

    public AcquiringFrameRecurrent(String id, String body) {
        token = new Trans_token_payhub();
        makeRecurrent(id,body);
    }

    private void makeRecurrent(String id, String body) throws JSONException {
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/frames/links/pga/"+id+"/recurrents");
        logger.info("Recurrent - "+getResponse());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    public String getResponse(){
        return response.then().extract().response().asString();
    }
}
