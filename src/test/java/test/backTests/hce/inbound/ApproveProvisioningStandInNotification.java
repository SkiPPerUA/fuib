package test.backTests.hce.inbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApproveProvisioningStandInNotification {

    @Test
    public void positiveTestApproveProvisioningStandInNotification(){

        String body = "{ “panReferenceID”: “”, “walletAccountEmailAddressHash”: “”, “clientWalletAccountID”: “”, “panSource”: “”, “addressVerificationResultCode”: “”, “cvv2ResultsCode”: “”, “consumerEntryMode”: “”, “locale”: “”," +
                " “encryptedData”: “"+ Configs1.HCE_JWS +"”," +
                " “deviceInfo”: { “deviceID”: “”, “deviceLanguageCode”: “”, “osType”: “”, “osVersion”: “”, “osBuildID”: “”, “deviceType”: “”, “deviceIDType”: “”, “deviceManufacturer”: “”, “deviceBrand”: “”, “deviceModel”: “”, “deviceName”: “”, “deviceNumber”: “”, “deviceLocation”: “”, “deviceIPAddressV4”: “”, “locationSource”: “”, “tokenProtectionMethod”: “” } }";

        String request = given()
                .contentType(ContentType.JSON)
                .params("eventType",true)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v2/tokenRequestors/"+ Configs1.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs1.HCE_TOKEN_REFERENCE_ID +"/tokenChanged")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }

    @Test
    public void positiveTestApproveProvisioningStandInNotification1(){

        String body = "{\n" +
                "“messageReasonCode”: “”, “dateTimeOfEvent”: “”, “panReferenceID”: “”, “walletAccountEmailAddressHash”: “”, “clientWalletAccountId”: “”, “panSource”: “”, “addressVerificationResultCode”: “”, “cvv2ResultsCode”: “”, “consumerEntryMode”: “”, “locale”: “”, “deviceInfo”: { “deviceID”: “”, “deviceLanguageCode”: “”, “osType”: “”, “osVersion”: “”, “osBuildID”: “”, “deviceType”: “”, “deviceIDType”: “”, “deviceManufacturer”: “”, “deviceBrand”: “”, “deviceModel”: “”, “deviceName”: “”, “deviceNumber”: “”, “deviceLocation”: “”, “deviceIpAddressV4”: “”, “locationSource”: “”, “tokenProtectionMethod”: “” },\n" +
                "} “encryptedData”: “"+ Configs1.HCE_JWS +"” } ";

        String request = given()
                .contentType(ContentType.JSON)
                .params("eventType",false)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v2/tokenRequestors/"+ Configs1.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs1.HCE_TOKEN_REFERENCE_ID +"/tokenChanged")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }

    @Test
    public void positiveTestApproveProvisioningStandInNotification2(){

        String body = "{\n" +
                "“messageReason”: “”, “messageReasonCode”: “”, “dateTimeOfEvent”: “”, “panReferenceID”: “”, “deviceID”: “”, " +
                "“encryptedData”: “"+ Configs1.HCE_JWS +"”\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .params("eventType",false)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v2/tokenRequestors/"+ Configs1.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs1.HCE_TOKEN_REFERENCE_ID +"/tokenChanged")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }
}
