package test.backTests.hce.inbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RetrieveStepUpMethods {

    @Test
    public void positiveTestRetrieveStepUpMethods() throws JSONException {

        String body = "{\n" +
                "\"tokenRequestorID\": \""+ Configs.HCE_TOKEN_REQUESTOR_ID +"\"," +
                " \"tokenReferenceID\": \""+ Configs.HCE_TOKEN_REFERENCE_ID +"\", " +
                "\"panReferenceID\": \"V-3...55\", \"clientWalletAccountID\": \"897...31\", \"panSource\": \"KEY_ENTERED\", \"otpReason\": \"TOKEN_DEVICE_BINDING\", \"otpMaxReached\": true, \"encryptedData\": \"eyJ...qQ\", \"deviceInfo\": { \"deviceID\": \"413...12\", \"deviceType\": \"MOBILE_PHONE\", \"deviceName\": \"Apple Ipod\", \"deviceNumber\": \"1\", \"deviceIndex\": 9 }\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/retrieveStepUpMethods")
                .then()
                .extract().response().prettyPrint();

        JSONObject ojb = new JSONObject(request);
        Assert.assertNotNull(ojb.getString("responseCode"));
    }

    @Test
    public void negativeTestRetrieveStepUpMethods() throws JSONException {

        String body = "{\n" +
                "\"tokenRequestorID\": \""+ Configs.HCE_TOKEN_REQUESTOR_ID +"\"," +
                " \"tokenReferenceID\": \"33\", " +
                "\"panReferenceID\": \"V-3...55\", \"clientWalletAccountID\": \"897...31\", \"panSource\": \"KEY_ENTERED\", \"otpReason\": \"TOKEN_DEVICE_BINDING\", \"otpMaxReached\": true, \"encryptedData\": \"eyJ...qQ\", \"deviceInfo\": { \"deviceID\": \"413...12\", \"deviceType\": \"MOBILE_PHONE\", \"deviceName\": \"Apple Ipod\", \"deviceNumber\": \"1\", \"deviceIndex\": 9 }\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/retrieveStepUpMethods")
                .then()
                .extract().response().prettyPrint();

        JSONObject ojb = new JSONObject(request);
        Assert.assertNotNull(ojb.getString("errorCode"));
    }
}
