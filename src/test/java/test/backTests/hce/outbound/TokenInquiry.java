package test.backTests.hce.outbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TokenInquiry {


    @Test
    public void positiveTestTokenInquiry() throws JSONException {

        String request = given()
                .contentType(ContentType.JSON)
                .params("apiKey", Configs1.HCE_APIKEY)
                .params("deviceBindingInfo",true)
                .when()
                .get("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/tokenRequestors/"+ Configs1.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs1.HCE_TOKEN_REFERENCE_ID +"/details")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String check = obj.getString("encryptedData");
        Assert.assertNotNull(check);

    }
}
