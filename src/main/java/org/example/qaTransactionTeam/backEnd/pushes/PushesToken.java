package org.example.qaTransactionTeam.backEnd.pushes;

import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PushesToken {

    private String grant_type;
    private String client_id;
    private String client_secret;
    private String password;
    private String username;
    private String access_token;
    private String refresh_token;
    private String environment;

    public PushesToken(String grant_type, String client_id, String client_secret, String password, String username){
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.password = password;
        this.username = username;
    }

    public void requestToken_authentication_results() throws JSONException {
        String request = given()
                .contentType(ContentType.URLENC)
                .formParam("grant_type",grant_type)
                .formParam("client_id", client_id)
                .formParam("client_secret", client_secret)
                .formParam("password", password)
                .formParam("username", username)
                .when()
                .post("https://auth.dts.fuib.com/auth/realms/pumb_ext/protocol/openid-connect/token")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JSONObject js = new JSONObject(request);
        this.access_token = js.getString("access_token");
        this.refresh_token = js.getString("refresh_token");


    }

    public void requestToken_change_push() throws JSONException {
        String request = given()
                .contentType(ContentType.URLENC)
                .formParam("grant_type",grant_type)
                .formParam("client_id", client_id)
                .formParam("client_secret", client_secret)
                .formParam("password", password)
                .formParam("username", username)
                .when()
                .post("https://auth."+environment+"-fuib.com/auth/realms/pumb/protocol/openid-connect/token")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JSONObject js = new JSONObject(request);
        this.access_token = js.getString("access_token");
        this.refresh_token = js.getString("refresh_token");
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}

