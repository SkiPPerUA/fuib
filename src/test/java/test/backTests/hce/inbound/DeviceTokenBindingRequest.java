package test.backTests.hce.inbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeviceTokenBindingRequest {

    @Test
    public void positiveTestDeviceTokenBindingRequest() throws JSONException {

        String body = "{\n" +
                "  \"panReferenceID\": \"V-4...48\",\n" +
                "  \"walletAccountEmailAddressHash\": \"_PI...fY\",\n" +
                "  \"clientWalletAccountID\": \"AWM...Bd\",\n" +
                "  \"deviceInfo\": {\n" +
                "    \"deviceID\": \"zmi...zA\",\n" +
                "    \"deviceLanguageCode\": null,\n" +
                "    \"osType\": null,\n" +
                "    \"osVersion\": null,\n" +
                "    \"osBuildID\": null,\n" +
                "    \"deviceType\": null,\n" +
                "    \"deviceIDType\": null,\n" +
                "    \"deviceManufacturer\": null,\n" +
                "    \"deviceBrand\": null,\n" +
                "    \"deviceModel\": null,\n" +
                "    \"deviceName\": null,\n" +
                "    \"deviceNumber\": null,\n" +
                "    \"deviceLocation\": null,\n" +
                "    \"deviceIPAddressV4\": null,\n" +
                "    \"locationSource\": null,\n" +
                "    \"deviceIndex\": 1\n" +
                "  },\n" +
                "  \"locale\": \"en_US\",\n" +
                "  \"tokenUserInfo\": null\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/tokenRequestors/"+ Configs.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs.HCE_TOKEN_REFERENCE_ID +"/deviceBinding")
                .then()
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String check = obj.getString("errorCode");
        Assert.assertNull(check);
    }

    @Test
    public void negativeTestDeviceTokenBindingRequest() throws JSONException {

        String body = "{\n" +
                "  \"panReferenceID\": \"V-4...48\",\n" +
                "  \"walletAccountEmailAddressHash\": \"_PI...fY\",\n" +
                "  \"clientWalletAccountID\": \"AWM...Bd\",\n" +
                "  \"deviceInfo\": {\n" +
                "    \"deviceID\": \"zmi...zA\",\n" +
                "    \"deviceLanguageCode\": null,\n" +
                "    \"osType\": null,\n" +
                "    \"osVersion\": null,\n" +
                "    \"osBuildID\": null,\n" +
                "    \"deviceType\": null,\n" +
                "    \"deviceIDType\": null,\n" +
                "    \"deviceManufacturer\": null,\n" +
                "    \"deviceBrand\": null,\n" +
                "    \"deviceModel\": null,\n" +
                "    \"deviceName\": null,\n" +
                "    \"deviceNumber\": null,\n" +
                "    \"deviceLocation\": null,\n" +
                "    \"deviceIPAddressV4\": null,\n" +
                "    \"locationSource\": null,\n" +
                "    \"deviceIndex\": 1\n" +
                "  },\n" +
                "  \"locale\": \"en_US\",\n" +
                "  \"tokenUserInfo\": null\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/tokenRequestors/"+ Configs.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs.HCE_TOKEN_REFERENCE_ID +"/deviceBinding")
                .then()
                .extract().response().prettyPrint();

        JSONObject obj = new JSONObject(request);
        String check = obj.getString("errorCode");
        Assert.assertNotNull(check);
    }
}
