package org.example.qaTransactionTeam.backEnd.payHub;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.json.JSONException;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class AcquiringTrans implements Transaction {

    private static final Logger logger = Logger.getLogger(AcquiringTrans.class);
    private Response response;
    private String transactionId;
    private String body;
    public static int statusCode = 200;
    protected String status;
    private final Auth_token token = new Trans_token_payhub("svc_ph_trn2_t", "HNviY3YJnEBTf&tSBnC3gRn4v%y&fn&B", "transacter"); //pan payer
    //private final Auth_token token = new Trans_token_payhub("svc_ph_test_ptrn", "quxS2&56xvatPZz66LKG8sJQHn3ZYVSA", "transacter"); //itm payer
    //private final Auth_token token = new Trans_token_payhub("svc_ph_Ccuxalsxt", "Lamx!yAgp9sA7VRiReHwQd2jiXFEpFL3", "transacter", "https://rlyeh.payhub.com.ua"); //PROD

    public AcquiringTrans(){}

    public AcquiringTrans(String body) throws JSONException {
        this.body = body;
        makeTrans();
    }

    private void registerTrans(){
        String body1 = "{\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                body +
                "}";

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body1)
                .when()
                .post(token.getHost()+"/pga/transactions");
        logger.info("Выполнение AcquiringTrans - "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
        transactionId = response.then().extract().response().jsonPath().getString("id");
        status = response.then().extract().response().jsonPath().getString("status");
    }

    public void complete_hold(String transactionId, int amount){
        String body;
        if (amount > 0){
            body = "{\"amount\": "+amount+"}";
        }else {
            body = "{}";
        }
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/pga/transactions/"+transactionId+"/complete_hold");
        logger.info("Выполнение AcquiringTrans - "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void complete_hold(int amount){
        complete_hold(transactionId, amount);
    }

    public String getResponse() {
        return response.then().extract().response().asString();
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public void setExpectedStatus(int status) {
        this.statusCode = status;
    }

    @Override
    public void setBodyRequest(String bodyRequest) {
        this.body = bodyRequest;
    }

    @Override
    public void setThreeDS(int threeDS) {

    }

    public void accept3ds2(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(ThreeDS.threeDS_2_2_0_acquiring)
                .when()
                .post(token.getHost()+"/pga/transactions/"+transactionId+"/3ds2_accept");
        logger.info("Accept3ds2 = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
        if (response.then().extract().response().jsonPath().getString("status").equals("3DS_VERIFICATION_NEEDED")){
            String url = response.then().extract().response().jsonPath().getString("3ds2_iframe.url");
            String params3ds = response.then().extract().response().jsonPath().getString("3ds2_iframe.post_params");
            ThreeDS.createIFrame(url,cutCreq(params3ds).toString());
        }
    }

    private StringBuffer cutCreq(String params3ds){
        StringBuffer methodData = new StringBuffer(params3ds);
        methodData.delete(0,9);
        methodData.delete(251,methodData.length());
        return methodData;
    }

    public void status(String transactionId){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{}")
                .when()
                .get(token.getHost()+"/pga/transactions/"+transactionId);
        logger.info("Status = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void get_3ds_type(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/pga/get_3ds_type");
        logger.info("Get_3ds_type = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    private void finishTrans(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "\t\"md\": \"md\",\n" +
                        "  \"c_res\": \"VladTest\"\n" +
                        "}")
                .when()
                .post(token.getHost()+"/pga/transactions/"+transactionId+"/3ds");
        logger.info("Finish Trans = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void confirmTrans(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{}")
                .when()
                .post(token.getHost()+"/pga/transactions/"+transactionId+"/3ds");
        logger.info("Confirm trans = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void declineTrans(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{}")
                .when()
                .post(token.getHost()+"/pga/transactions/"+transactionId+"/3ds2_reject");
        logger.info("Decline trans = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void refund(String transactionId, int amount){
        String body = "";
        if (amount == 0){
            body = "{}";
        }else {
            body = "{\"amount\": "+amount+"}";
        }
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/pga/transactions/"+transactionId+"/refund");
        logger.info("Refund trans = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void refund(int amount){
        refund(transactionId,amount);
    }

    public void refund(){
        refund(transactionId,0);
    }

    public void refund(String transactionId){
        refund(transactionId,0);
    }

    @Override
    public void makeTrans() {
        registerTrans();
        if (status.equals("3DS2_PREPARE")) {
            accept3ds2();
            finishTrans();
        }
    }

    public String status(){
        status(transactionId);
        return getResponse();
    }
}
