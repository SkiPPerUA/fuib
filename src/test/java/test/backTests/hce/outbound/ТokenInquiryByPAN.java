package test.backTests.hce.outbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ТokenInquiryByPAN {

    @Test
    public void testТokenInquiryByPAN() throws JSONException {

        String body = "{\n" +
                "“encryptedData”: “"+ Configs1.HCE_JWS +"”\n" +
                "} ";

        String request = given()
                .contentType(ContentType.JSON)
                .params("apiKey", Configs1.HCE_APIKEY)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/pan/retrieveTokenInfo")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String check = obj.getString("tokenDetails");
        Assert.assertNotNull(check);
    }
}
