package test.backTests.hce.outbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RetrieveNHPPProfiles {

    @Test
    public void positiveTestRetrieveNHPPProfiles() throws JSONException {

        String body = "{ “encryptedData”: “"+ Configs1.HCE_JWS +"” } ";

        String request = given()
                .contentType(ContentType.JSON)
                .params("apiKey", Configs1.HCE_APIKEY)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/retrieveNHPPProfiles")
                .then()
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String check = obj.getString("nhppProfiles");
        Assert.assertNotNull(check);
    }
}
