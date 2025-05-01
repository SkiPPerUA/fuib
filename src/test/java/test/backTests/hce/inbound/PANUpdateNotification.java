package test.backTests.hce.inbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import test.backTests.hce.HCEConfigs;

import static io.restassured.RestAssured.given;

public class PANUpdateNotification {

    @Test
    public void testPANUpdateNotification(){

        String body = "{\n" +
                "“messageReason”: “”," +
                " “messageReasonCode”: “”, " +
                "“dateTimeOfEvent”: “”, " +
                "“panReferenceID”: “”, " +
                "“encryptedData”: “"+ Configs1.HCE_JWS +"”\n" +
                "} ";

        String request = given()
                .contentType(ContentType.JSON)
                .params("eventType","PAN_UPDATED")
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/pan/notification")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }
}
