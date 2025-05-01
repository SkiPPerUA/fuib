package test.backTests.pushes;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.backEnd.pushes.PushConfigs;
import org.example.qaTransactionTeam.backEnd.pushes.PushesToken;
import org.json.JSONException;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class PushesAuthenticationResults {

    String body = "{\"ref_id\":\"32423141213122\",\"auth_status\":0,\"auth_sub_status\":0}";
    String accessToken;

    @Test (priority = 0)
    public void getToken() throws JSONException {
        PushesToken token = new PushesToken(PushConfigs.CONFIG_043_PUSH[0], PushConfigs.CONFIG_043_PUSH[1], PushConfigs.CONFIG_043_PUSH[2], PushConfigs.CONFIG_043_PUSH[3], PushConfigs.CONFIG_043_PUSH[4]);

        token.requestToken_authentication_results();
        accessToken = token.getAccess_token();
    }

    @Test (priority = 1)
    public void getResult(){
        String request = given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","77777")
                .header("X-SYSTEMCODE","ext_043")
                .header("Authorization","Bearer "+accessToken)
                .body(body)
                .when()
                .post("https://api.dts.fuib.com/ext-oic/in-betweenerer/v1/043/authentication_results")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

    }
}
