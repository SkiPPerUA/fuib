package org.example.qaTransactionTeam.backEnd.token;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Shazam_token implements Auth_token{

    private String token;
    private Response response;
    private String host = Configs1.PAYHUB_HOST;
    private Map<String, String> data;

    public Shazam_token(String login, String pass){
        data = new HashMap<>();
        data.put("grant_type","password");
        data.put("username", "svc_ph_test_doc");
        data.put("password","s53cC8eBY%b#Jmg!RhM948J!F3nbucrr");
        data.put("client_id", "EKB");
        create_token();
    }

    @Override
    public void create_token() {
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.URLENC)
                .params(data)
                .when()
                .post("https://auth.test-fuib.com/auth/realms/pumb/protocol/openid-connect/token");
        token = response.then().extract().response().jsonPath().getString("access_token");
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getAcqID() {
        return null;
    }
}
