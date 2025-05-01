package test.backTests.hce.inbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import test.backTests.hce.HCEConfigs;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.DecoderConfig.decoderConfig;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class ApproveProvisioning {

    @Test
    public void positiveTestApproveProvisioning() throws JSONException {

        String body = "{\n" +
                "“tokenInfo”: { “tokenType”: “”, “tokenAssuranceLevel”: “”, “numberOfActiveTokensForPAN”: “”, “numberOfInactiveTokensForPAN”: “”, “numberOfSuspendedTokensForPAN”: “” }, “panReferenceID”: “”, “walletAccountEmailAddressHash”: “”, “clientWalletAccountID”: “”, “panSource”: “”, “addressVerificationResultCode”: “”, “cvv2ResultsCode”: “”, “consumerEntryMode”: “”, “locale”: “”, " +
                "“encryptedData”: “"+ Configs1.HCE_JWS +"”, " +
                "“deviceInfo”: { “deviceID”: “”, “deviceLanguageCode”: “”, “osType”: “”, “osVersion”: “”, “osBuildID”: “”, “deviceType”: “”, “deviceIDType” : “”, “deviceManufacturer”: “”, “deviceBrand”: “”, “deviceModel”: “”, “deviceName”: “”, “deviceNumber”: “”, “deviceLocation”: “”, “deviceIPAddressV4”: “”, “locationSource”: “”, “tokenProtectionMethod”: “” }\n" +
                "}";

        RestAssuredConfig config = RestAssuredConfig.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8").defaultCharsetForContentType("UTF-8", "application/json")).decoderConfig(decoderConfig().defaultContentCharset("UTF-8").defaultCharsetForContentType("UTF-8", "application/json"));
        String request = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(HCEConfigs.ENDPOINT +"/vtis/v2/tokenRequestors/"+ Configs1.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs1.HCE_TOKEN_REFERENCE_ID +"/approveProvisioning")
                .then()
                .extract().response().prettyPrint();

        /*JSONObject obj = new JSONObject(request);
        String check = obj.getString("actionCode");
        Assert.assertEquals(check,"00");*/
    }

    @Test
    public void negativeTestApproveProvisioning() throws JSONException {

        String body = "{\n" +
                "“tokenInfo”: { “tokenType”: “”, “tokenAssuranceLevel”: “”, “numberOfActiveTokensForPAN”: “”, “numberOfInactiveTokensForPAN”: “”, “numberOfSuspendedTokensForPAN”: “” }, “panReferenceID”: “”, “walletAccountEmailAddressHash”: “”, “clientWalletAccountID”: “”, “panSource”: “”, “addressVerificationResultCode”: “”, “cvv2ResultsCode”: “”, “consumerEntryMode”: “”, “locale”: “”, " +
                "“encryptedData”: “"+ Configs1.HCE_JWS +"”, " +
                "“deviceInfo”: { “deviceID”: “”, “deviceLanguageCode”: “”, “osType”: “”, “osVersion”: “”, “osBuildID”: “”, “deviceType”: “”, “deviceIDType” : “”, “deviceManufacturer”: “”, “deviceBrand”: “”, “deviceModel”: “”, “deviceName”: “”, “deviceNumber”: “”, “deviceLocation”: “”, “deviceIPAddressV4”: “”, “locationSource”: “”, “tokenProtectionMethod”: “” }\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v2/tokenRequestors/"+ Configs1.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs1.HCE_TOKEN_REFERENCE_ID +"/approveProvisioning")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String check = obj.getString("errorCode");
        Assert.assertNotNull(check);
    }
}
