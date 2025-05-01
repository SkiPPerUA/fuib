package org.example.qaTransactionTeam.backEnd.payByLink;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class TokenForPayByLink {

    private static final Logger logger = Logger.getLogger(TokenForPayByLink.class);
    private String login;
    private String password;
    private String response;
    private String access_token;

    public TokenForPayByLink(String login, String password){
        this.login = login;
        this.password = password;
        createToken();
    }

    private void createToken(){
        RestAssured.useRelaxedHTTPSValidation();
        String body = "{\n" +
                "\t\"login\": \""+login+"\",\n" +
                "\t\"password\": \""+password+"\"\n" +
                "}";
        response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(Configs1.PAYHUB_HOST +"/auth/merchants/admin/otp")
                .then()
                .statusCode(200).extract().response().asString();

        JSONObject json = new JSONObject(response);
        String tokenOtp = json.getString("access_token");

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+tokenOtp)
                .body("{\n" +
                        "\t\"code\": \"1111\"\n" +
                        "}")
                .when()
                .post(Configs1.PAYHUB_HOST +"/auth/merchants/admin/otp/confirm")
                .then()
                .statusCode(200).extract().response().asString();

        json = new JSONObject(response);
        access_token = json.getString("access_token");

    }

    public String getToken() {
        return access_token;
    }
}
