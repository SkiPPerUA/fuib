package org.example.qaTransactionTeam.backEnd.token;

import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import org.json.JSONException;
import io.restassured.http.ContentType;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Trans_token_itm implements Auth_token{

    private String token;
    private String acqID = "2101";
    private String login = Configs1.LOGIN_MERCHANT_2101;
    private String password = Configs1.PASSWORD_MERCHANT_2101;
    private String host = "https://tsystestapi.pumb.ua";
    //private String host = "https://vmt.dts.fuib.com";
    private static final Logger logger = Logger.getLogger(Trans_token_itm.class);

    public Trans_token_itm(String login, String password, String acqID, String host) throws JSONException {
        this.acqID = acqID;
        this.host = host;
        this.login = login;
        this.password = password;
        create_token();
    }

    public Trans_token_itm(String login, String password, String acqID) throws JSONException {
        this.acqID = acqID;
        this.login = login;
        this.password = password;
        create_token();
    }

    public Trans_token_itm() throws JSONException {
        create_token();
    }

    public Trans_token_itm(String acqID) throws JSONException {
        this.acqID = acqID;
        create_token();
    }

    @Override
    public void create_token() {
        Map<String,String> body = new HashMap<>();
        body.put("login",login);
        body.put("password",password);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(host+"/vmt/token/"+acqID);
        Assert.assertEquals(response.getStatusCode(), 200, "Токен НЕ получен");
        String errorCode = response.then().extract().response().jsonPath().getString("errorCode");
        if(errorCode.equals("00")) {
            token = response.then().extract().response().jsonPath().getString("token");
        }else {
            logger.error(response.then().extract().response().toString());
            Assert.fail("Токен НЕ получен для "+acqID);
        }
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getToken(){
        return token;
    }

    @Override
    public String getAcqID() {
        return acqID;
    }
}
