package test.backTests.pushes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SendPush extends BaseTest {

   private String env = "stage";
   private String refID = "32423241213153";

   private String externalId = "015010203707";
   private String pan = "545219xxxxxx7221";

    @Test
   public void sendPush(){

       String body = "{\n" +
               "\"refId\":\""+refID+"\",\n" +
               "\"acquirerBIN\":\"400551\",\n" +
               "\"merchantID\":\"V3DSTestSuite-12345678\",\n" +
               "\"merchantName\":\"Merchant xyz\",\n" +
               "\"merchantCountry\":\"840\",\n" +
               "\"merchantURL\":\"test\",\n" +
               "\"purchaseDate\":\"2020-11-11T12:19:53.000Z\",\n" +
               "\"amount\":\"9999\",\n" +
               "\"currency\":\"840\",\n" +
               "\"whenExpire\":600,\n" +
               "\"clientInfo\":\"UNKNOWN\",\n" +
               "\"3DSprotocolVersion\":\"2.1.0\",\n" +
               "\"purchaseExponent\":\"2\",\n" +
               "\"externalId\":\""+externalId+"\",\n" +
               "\"maskedPan\":\""+pan+"\"\n" +
               "}";

        String body1 = "{\n" +
                "\"refId\":\"816479080669150676\",\n" +
                "\"acquirerBIN\":\"541733\",\n" +
                "\"merchantID\":\"0208100FMT00001\",\n" +
                "\"merchantName\":\"C2A_VGOT_081*submerc\",\n" +
                "\"merchantCountry\":\"804\",\n" +
                "\"merchantURL\":\"https://pumb.ua\",\n" +
                "\"purchaseDate\":\"\",\n" +
                "\"amount\":\"\",\n" +
                "\"currency\":\"\",\n" +
                "\"whenExpire\":120,\n" +
                "\"clientInfo\":\"VEReq-Remote-Address\\u003d216.119.209.240\\u0026User-Agent\\u003dGeckoPaReqc-cReq-Remote-Address\\u003d176.38.16.105\",\n" +
                "\"3DSprotocolVersion\":\"2.1.0\",\n" +
                "\"purchaseExponent\":\"\",\n" +
                "\"externalId\":\"015010203707\",\n" +
                "\"maskedPan\":\"545219xxxxxx7221\"\n" +
                "}";

        RestAssured.useRelaxedHTTPSValidation();

       String request = given()
               .contentType(ContentType.JSON)
               .header("X-Flow-ID","FUIBtest")
               .header("X-SYSTEMCODE", "777")
               .body(body)
               .when()
               .post("https://api."+env+"-fuib.com/tsys/in-betweenerer/v1/authenticationRequests")
               .then()
              // .statusCode(200)
               .extract().response().prettyPrint();

        System.out.println("Log = https://kibana."+env+"-fuib.com/app/kibana#/discover?_g=h@44136fa&_a=h@c95a4f3");
   }
}
