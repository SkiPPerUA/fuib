package org.example.qaTransactionTeam.backEnd.transaction;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class Transaction_itm {

    protected static final Logger logger = Logger.getLogger(Transaction_itm.class);
    protected int statusCode = 200;
    protected Auth_token createToken;
    protected String sessionId;
    protected Response response;
    protected String p4pFinalisationId;
    protected String errorCode;
    protected String id;
    protected String step;
    protected String typeTrans;
    protected String host = "https://tsystestapi.pumb.ua/vmt";
    protected int threeDS;
    Map<String,String> body;
    protected String cres = "eyJ0cmFuc1N0YXR1cyI6IlkiLCJtZXNzYWdlVHlwZSI6IkNSZXMiLCJtZXNzYWdlVmVyc2lvbiI6IjIuMS4wIiwiYWNzVHJhbnNJRCI6ImY1NTQ3NDQwLTYxNzYtNDFlYi1iNjU2LWFkNGYwZDg3ZTM0ZiIsImRzVHJhbnNJRCI6ImY1NTQ0ZDMwLTYxNzYtNDFlYi1iOWZiLTYwMTliNGMyMWEyNyIsInRocmVlRFNTZXJ2ZXJUcmFuc0lEIjoiZWQxYWU0ZDAtNjE3Ni00MWViLThhMTgtODkxODE4OTlmMmYzIn0";

    private void acs(){
        body.put("ip","10.10.10.10");
        if (threeDS == 2){
            body.put("version","2.2.0");
            body.put("fingerprint","1q2w3e4r5t6y7u");
            body.put("channel","BRW");
            body.put("browserAcceptHeader","text/html");
            body.put("browserJavaEnabled","true");
            body.put("browserLanguage","RU");
            body.put("browserJavaScriptEnabled","true");
            body.put("browserColorDepth","32");
            body.put("browserScreenHeight","800");
            body.put("browserScreenWidth","480");
            body.put("browserTZ","180");
            body.put("browserUserAgent","Gecko");
            body.put("challengeWindowSize","02");
            body.put("iframeReturnUrl","https://service.fuib.com");
            body.put("p2pAddParam","true");
//            body.put("phone","38098765433466");
//            body.put("phoneType","01");
//            body.put("email","johndoe2@gmail.com");
//            body.put("cardholderName","John Doe");
        }else if(threeDS == 0){
            body.put("withoutd3ds","true");
            body.put("p2pAddParam","true");
        }
        makeAcs(body);
    }

    protected void acs(String cvv){
        body = new HashMap<>();
        if (!cvv.isEmpty()){
            body.put("cvv", cvv);
        }
        acs();
    }

    private void makeAcs(Map body){
        response = given()
                .contentType(ContentType.URLENC)
                .params(body)
                .when()
                .post(host+"/api/acs/"+createToken.getAcqID()+"/"+ sessionId+"/");
        logger.info("Прохождение ACS для "+typeTrans+" - " + getResponse());
        Assert.assertEquals(response.getStatusCode(), statusCode);

        if (threeDS != 0) {
            JSONObject jsonObject = new JSONObject(getResponse());
            String cReq = jsonObject.getJSONObject("acsModel").getString("cReq");
            String urlForPareq = jsonObject.getJSONObject("acsModel").getString("url");
            id = jsonObject.getJSONObject("acsModel").getString("id");
            step = jsonObject.getJSONObject("acsModel").getString("step");
            try {
                ThreeDS.createIFrame(urlForPareq, cReq);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }

    protected void register(Map body) throws JSONException {
         response = given()
                .header("Token", createToken.getToken())
                .header("extTransId",Uuid_helper.generate_uuid())
                .contentType(ContentType.URLENC)
                .params(body)
                .when()
                .post(host+"/api/register/"+createToken.getAcqID());
        logger.info("Регистрация "+typeTrans+" - " + getResponse());
        Assert.assertEquals(response.getStatusCode(), statusCode);

//         if(!typeTrans.equals("A2C")) {
//             card = body.get("senderCardNumber").toString();
//         }
         sessionId = response.then().extract().response().jsonPath().getString("sessionId");

    }

    protected void finish() throws JSONException {
        response = given()
                .contentType(ContentType.URLENC)
                .when()
                .post(host+"/api/payment/"+createToken.getAcqID()+"/"+ sessionId +"/");
        logger.info("Списание по "+typeTrans+" - " + getResponse());
        Assert.assertEquals(response.getStatusCode(), statusCode);
        if (typeTrans.equals("P4P")) {
            p4pFinalisationId = response.then().extract().response().jsonPath().getString("p4pFinalisationId");
        }
        errorCode = response.then().extract().response().jsonPath().getString("errorCode");
    }

    protected void finishWith3ds() throws JSONException {
        body = new HashMap<>();
        body.put("id", id);
        body.put("step", step);
        body.put("cRes", cres);
        body.put("ip","10.10.10.10");
        response = given()
                .contentType(ContentType.URLENC)
                .params(body)
                .when()
                .post( host+"/api/payment/"+createToken.getAcqID()+"/"+ sessionId);
        logger.info("Списание по "+typeTrans+" - " + getResponse());
        Assert.assertEquals(response.getStatusCode(), statusCode);
        errorCode = response.then().extract().response().jsonPath().getString("errorCode");
        if(typeTrans.equals("P4P")) {
            p4pFinalisationId = response.then().extract().response().jsonPath().getString("p4pFinalisationId");
        }
    }

    public String getResponse() {
        return response.then().extract().response().asString();
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getTransactionId() {
        return sessionId;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
