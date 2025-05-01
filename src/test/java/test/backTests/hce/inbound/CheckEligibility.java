package test.backTests.hce.inbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CheckEligibility {

    String errorCode = "";

    @Test
    public void positiveTestCheckEligibility() throws JSONException {

        String body = "{\n" +
                "“tokenRequestorID” : "+ Configs1.HCE_TOKEN_REQUESTOR_ID +",\n" +
                " “tokenReferenceID” : “"+ Configs1.HCE_TOKEN_REFERENCE_ID +"”,\n" +
                " “panReferenceID”: “V-420...90”,\n" +
                " “panSource”: “KEY_ENTERED”,\n" +
                " “deviceInfo”: {\n" +
                "    “deviceID”: “043...7C”,\n" +
                "    “deviceLanguageCode”: “eng”\n" +
                "   },\n" +
                " “encryptedData”: “"+ Configs1.HCE_JWS +"”\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .header("RequestId", HCEConfigs.REQUEST_ID)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/checkEligibility")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String check = obj.getString("cardIssuer");
        Assert.assertNotNull(check);
    }

    @Test
    public void negativeTestCheckEligibility() throws JSONException {

        String body = "{\n" +
                "“tokenRequestorID” : "+ Configs1.HCE_TOKEN_REQUESTOR_ID +",\n" +
                " “tokenReferenceID” : “2”,\n" +
                " “panReferenceID”: “V-420...90”,\n" +
                " “panSource”: “KEY_ENTERED”,\n" +
                " “deviceInfo”: {\n" +
                "    “deviceID”: “043...7C”,\n" +
                "    “deviceLanguageCode”: “eng”\n" +
                "   },\n" +
                " “encryptedData”: “"+ Configs1.HCE_JWS +"”\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .header("RequestId", HCEConfigs.REQUEST_ID)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/checkEligibility")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String actualError = obj.getString("errorCode");
        Assert.assertEquals(actualError,errorCode);
    }
}
