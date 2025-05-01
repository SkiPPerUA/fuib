package test.backTests.hce.outbound;

import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import test.backTests.hce.HCEConfigs;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PANLifecycle {

    @Test
    public void testPANLifecycle(){

        String body = "{\n" +
                " “operatorID”: “Operator1”,\n" +
                " “operationReason”: “Replacement due to lost card”,\n" +
                " “operationReasonCode”: “ACCOUNT_UPDATE”,\n" +
                " “operationType”: “UPDATE”,\n" +
                " “encryptedData”: “<JWS>”\n" +
                "}";

        String request = given()
                .contentType(ContentType.JSON)
                .params("apiKey", Configs1.HCE_APIKEY)
                .body(body)
                .when()
                .post("http://"+ HCEConfigs.ENDPOINT +"/vtis/v1/pan/lifecycle")
                .then()
                .extract().response().prettyPrint();
    }
}
