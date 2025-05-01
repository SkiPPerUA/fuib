package test.backTests.hce.inbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CardMetadataUpdateNotification {

    @Test
    public void testCardMetadataUpdateNotification(){

        String body = "{\n" +
                " “taskID”: “ed3 91”,\n" +
                " “statusCode”: “SUCCESS”\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .params("eventType", true)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/tokenRequestors/"+ Configs1.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs1.HCE_TOKEN_REFERENCE_ID +"/notification")
                .then()
                .extract().response().prettyPrint();
    }

    @Test
    public void testCardMetadataUpdateNotification1(){

        String body = "{\n" +
                "“taskID”: “ed3...91”, “statusCode”: “FAILURE”, “errorCode”: “VSE40008”\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .params("eventType", true)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/tokenRequestors/"+ Configs1.HCE_TOKEN_REQUESTOR_ID +"/tokens/"+ Configs1.HCE_TOKEN_REFERENCE_ID +"/notification")
                .then()
                .extract().response().prettyPrint();
    }
}
