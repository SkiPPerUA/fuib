package org.example.qaTransactionTeam.backEnd.itm;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class GetTransDetails {

    private static final Logger logger = Logger.getLogger(GetTransDetails.class);
    private String sessionId;
    private String token;
    private String response;
    private String acqId;

    public String getResponse() {
        return response;
    }

    public  GetTransDetails(String sessionId) throws JSONException {
        this.sessionId = sessionId;
        Trans_token_itm tok = new Trans_token_itm();
        token = tok.getToken();
        acqId = tok.getAcqID();
        getDetails();
    }

    public  GetTransDetails(String sessionId, String p4pFundId) throws JSONException {
        this.sessionId = sessionId;
        Trans_token_itm tok = new Trans_token_itm();
        token = tok.getToken();
        acqId = tok.getAcqID();
        getDetailsWithp4pFundId(p4pFundId);
    }

    public  GetTransDetails(String sessionId, Trans_token_itm token){
        this.sessionId = sessionId;
        this.token = token.getToken();
        acqId = token.getAcqID();
        getDetails();
    }

    private void getDetails(){
        String url = "https://tsystestapi.pumb.ua/vmt/api/v2/transfers/get-transfers/"+acqId+"/"+ sessionId;

        response = given()
                .contentType(ContentType.JSON)
                .header("token", token)
                .when()
                .get(url)
                .then()
                .extract()
                .response().asString();

        logger.info("Инфо по транзакции - "+response);
    }

    private void getDetailsWithp4pFundId(String p4pFundId){
        String url = "https://tsystestapi.pumb.ua/vmt/api/v2/transfers/get-transfers/"+acqId+"/"+ sessionId;

        response = given()
                .contentType(ContentType.JSON)
                .header("token", token)
                .header("p4pFundId",p4pFundId)
                .when()
                .get(url)
                .then()
                .extract()
                .response().asString();

        logger.info("Инфо по транзакции - "+response);
    }

}
