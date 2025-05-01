package test.backTests.hce.outbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateCardMetadata {

    @Test
    public void positiveTestUpdateCardMetadata() throws JSONException {

        String body = "{\n" +
                "“encryptedData”: “"+ Configs.HCE_JWS +"”, “operatorID”: “Operator1”, “operationReason”: “OperationReason”, “cardMetadataInfo”: { “foregroundColor”: “rgb(255,255,255)”, “backgroundColor”: “rgb(255,255,255)”, “labelColor”: “rgb(255,255,255)”, “shortDescription”: “Fifth Group”, “longDescription”: “Fifth Group”, “cardIssuer”: “ieadmin”, “cardArtData”: { “cardArtRefID”: [“ak0 jk”, “bk0 jk”, “ck0 jk”, “k04 jk”] }, “privacyPolicyURL”: “https://www.issuer.com/data/dataprivacy”, “termsAndConditionsURL”: “https://issuer.com/data/tnc”, “contactInfo”: { “contactWebsite”: “https://issuer.com”, “contactNumber”: “800-000-0000”, “contactName”: “issuer” }, “applicationInfo”: { “supportsTokenNotifications”: true, “supportsFPANNotifications”: false, " +
                "“transactionServiceURL”:“https://vntsnotificationservice.visa.com/TxnHist/1”, “messageServiceURL”: null }\n" +
                "} } ";

        String request = given()
                .contentType(ContentType.JSON)
                .params("apiKey", Configs.HCE_APIKEY)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/updateCardMetadata")
                .then()
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String check = obj.getString("responseDetails");
        Assert.assertNotNull(check);


    }

    @Test
    public void negativeTestUpdateCardMetadata() throws JSONException {

        String body = "{\n" +
                "“encryptedData”: “32432432”, “operatorID”: “Operator1”, “operationReason”: “OperationReason”, “cardMetadataInfo”: { “foregroundColor”: “rgb(255,255,255)”, “backgroundColor”: “rgb(255,255,255)”, “labelColor”: “rgb(255,255,255)”, “shortDescription”: “Fifth Group”, “longDescription”: “Fifth Group”, “cardIssuer”: “ieadmin”, “cardArtData”: { “cardArtRefID”: [“ak0 jk”, “bk0 jk”, “ck0 jk”, “k04 jk”] }, “privacyPolicyURL”: “https://www.issuer.com/data/dataprivacy”, “termsAndConditionsURL”: “https://issuer.com/data/tnc”, “contactInfo”: { “contactWebsite”: “https://issuer.com”, “contactNumber”: “800-000-0000”, “contactName”: “issuer” }, “applicationInfo”: { “supportsTokenNotifications”: true, “supportsFPANNotifications”: false, " +
                "“transactionServiceURL”:“https://vntsnotificationservice.visa.com/TxnHist/1”, “messageServiceURL”: null }\n" +
                "} } ";

        String request = given()
                .contentType(ContentType.JSON)
                .params("apiKey", Configs.HCE_APIKEY)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/updateCardMetadata")
                .then()
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String check = obj.getString("errorCode");
        Assert.assertNotNull(check);


    }
}
