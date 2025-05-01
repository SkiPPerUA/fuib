package org.example.qaTransactionTeam.backEnd.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;

public class Apiman {

    private String client_id;
    private String password;
    private String environment = "test";
    private String token;

    public Apiman (String client_id, String password) throws JSONException {
        this.client_id = client_id;
        this.password = password;
        CreateToken();
    }

    public Apiman (String client_id, String username, String password, String environment) throws JSONException {
        this.client_id = client_id;
        this.password = password;
        this.environment = environment;
        RestAssured.useRelaxedHTTPSValidation();
        String url = "https://auth."+environment+"-fuib.com/auth/realms/pumb/protocol/openid-connect/token";  //PROD https://authsrv.fuib.com/auth/realms/pumb/protocol/openid-connect/token

        String re = given()
                .contentType(ContentType.URLENC)
                .params("client_id",client_id)
                .params("username",username)
                .params("password",password)
                .params("grant_type","password")
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();

        JSONObject ob = new JSONObject(re);
        token = ob.getString("access_token");
    }

    public Apiman (String client_id, String password, String environment) throws JSONException {
        this.client_id = client_id;
        this.password = password;
        this.environment = environment;
        CreateToken();
    }

    public Apiman (boolean bool) throws JSONException {
        RestAssured.useRelaxedHTTPSValidation();
        String url = "https://auth.test-fuib.com/auth/realms/pumb/protocol/openid-connect/token";
        String re = given()
                .contentType(ContentType.URLENC)
                .params("client_id","ODB")
                .params("username","svc_ph_test_doc")
                .params("password","s53cC8eBY%b#Jmg!RhM948J!F3nbucrr")
                .params("grant_type","password")
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();

        JSONObject ob = new JSONObject(re);
        token = ob.getString("access_token");

    }

    public String getEnvironment() {
        return environment;
    }

    private void CreateToken() throws JSONException {
        RestAssured.useRelaxedHTTPSValidation();
        String url = "https://auth."+environment+"-fuib.com/auth/realms/pumb/protocol/openid-connect/token";  //PROD https://authsrv.fuib.com/auth/realms/pumb/protocol/openid-connect/token

        String re = given()
                .contentType(ContentType.URLENC)
                .params("client_id",client_id)
                .params("username",password)
                .params("password",password)
                .params("grant_type","password")
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();

        JSONObject ob = new JSONObject(re);
        token = ob.getString("access_token");

    }

    public String getToken() {
        return token;
    }
}
