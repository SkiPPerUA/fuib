package test.backTests.hce.inbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TokenNotification {

    @Test
    public void testTokenNotification(){

        String body = "";

        String request = given()
                .contentType(ContentType.JSON)
                .params("eventType","eventType")
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v2/tokenRequestors/"+ Configs.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs.HCE_TOKEN_REFERENCE_ID +"/tokenChanged")
                .then()
                .extract().response().prettyPrint();
    }
}
