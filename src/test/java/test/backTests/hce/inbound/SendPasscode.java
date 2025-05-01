package test.backTests.hce.inbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SendPasscode {

    String errorCode = "";

    @Test
    public void testSendPasscode() throws JSONException {

        String body = "{\n" +
                "“tokenRequestorID” : "+ Configs.HCE_TOKEN_REQUESTOR_ID +", " +
                "“tokenReferenceID” : “"+ Configs.HCE_TOKEN_REFERENCE_ID +"”, " +
                "“panReferenceID”: “V-4200...90”, " +
                "“clientWalletAccountID”: “99E...4C”, " +
                "“otpMethodIdentifier”: “1e3...60”, " +
                "“otpValue”: “123456”, " +
                "“otpExpirationDate”: “2015-05-18T14:40:32.000Z”, " +
                "“deviceInfo”: " +
                      "{ “deviceID”: “043...7C”, " +
                      "“deviceType”: “MOBILE_PHONE”, " +
                      "“deviceName”: “My iPhone”, " +
                      "“deviceNumber”: “86...30” }, " +
                "“encryptedData”: “"+ Configs.HCE_JWS +"”\n" +
                "} ";

        String request = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/sendPasscode")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String actualError = obj.getString("errorCode");
        Assert.assertEquals(actualError,errorCode);
    }
}
