package test.backTests.hce.outbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TokenLifecycle {

    @Test
    public void testTokenLifecycle(){

        String body = "{\n" +
                " \"operatorID\": \"...\",\n" +
                " \"operationReason\": \"a reason\",\n" +
                " \"operationType\": \"TOKEN_DEVICE_BINDING_APPROVE\",\n" +
                " \"deviceInfo\": {\n" +
                "      \"deviceID\": \"XXXX\",\n" +
                "      \"deviceIndex\": 1\n" +
                "   }\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .params("apikey", Configs.HCE_APIKEY)
                .body(body)
                .when()
                .post("https://"+ HCEConfigs.ENDPOINT +"/vtis/v1/tokenRequestors/"+ Configs.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs.HCE_TOKEN_REFERENCE_ID +"/lifecycle")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }
}
