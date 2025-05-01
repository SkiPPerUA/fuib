package org.example.qaTransactionTeam.backEnd.token;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Admin_token implements Auth_token{

    private String token;
    private Response response;
    private String host = Configs.PAYHUB_HOST;
    private String data;

    public Admin_token(){
        data = "{\n" +
                "\t\"login\": \""+ Configs.ADMIN_LOGIN+"\",\n" +
                "\t\"password\": \""+ Configs.ADMIN_PASSWORD+"\"\n" +
                "}";
        create_token();
    }

    @Override
    public void create_token() {
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post(host+"/admin/login");
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
