package test.backTests.hce.inbound;

import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetCardholderVerificationMethods {

    @Test
    public void testGetCardholderVerificationMethods(){

        String body = "";

        String request = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/retrieveStepUpMethods")
                .then()
                .extract().response().prettyPrint();
    }
}
