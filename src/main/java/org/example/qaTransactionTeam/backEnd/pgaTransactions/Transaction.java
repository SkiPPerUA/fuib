package org.example.qaTransactionTeam.backEnd.pgaTransactions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Transaction {

    private static final Logger logger = Logger.getLogger(Transaction.class);
    protected TokenPGA tokenPGA;
    private String response;
    private String methodData3ds;
    private String methodData3dsForCreq;
    private String urlForCreq;
    private String creq;

    protected void createTrans(Map body){
        tokenPGA = new TokenPGA();
        response = given()
                .contentType(ContentType.URLENC)
                .params(body)
                .when()
                .post("https://pps03.fuib.com:9443/api/v4/"+tokenPGA.getPortalId()+"/payment/"+tokenPGA.getToken()+"/start")
                .then().extract().response().asString();

        logger.info("Регистрация транзакции = "+response);

    }

    protected void prepare_3ds(){
        response = given()
                .contentType(ContentType.URLENC)
                .params("version","2.1.0")
                .params("device.channel","BRW")
                .params("device.browserAcceptHeader","text/html")
                .params("device.browserIP","127.0.0.1")
                .params("device.browserJavaEnabled","false")
                .params("device.browserLanguage","RU")
                .params("device.browserColorDepth","32")
                .params("device.browserScreenHeight","800")
                .params("device.browserScreenWidth","480")
                .params("device.browserTZ","180")
                .params("device.browserUserAgent","Gecko")
                .params("challengeWindowSize","01")
                .params("iframeReturnUrl","https://innsmouth.payhub.com.ua/frames/links/pga/cc6effdb-578f-4f4b-96c1-d87149be9c43/3ds")
                .when()
                .post("https://pps03.fuib.com:9443/api/v4/"+tokenPGA.getPortalId()+"/payment/"+tokenPGA.getToken()+"/3ds2-prepare/accept")
                .then().extract().response().asString();

        JSONObject jsonPrepare = new JSONObject(response);
        methodData3ds = jsonPrepare.getJSONObject("post").getString("threeDSMethodData");
        logger.info("3DSmethodData = "+methodData3ds);

    }

    protected void createIFrame(){
        RestAssured.useRelaxedHTTPSValidation();

        response = given()
                .contentType(ContentType.URLENC)
                .params("threeDSMethodData",methodData3ds)
                .when()
                .post("https://pps03.fuib.com:20443/method")
                .then().extract().response().asString();

        //logger.info("Frame = "+response);
        methodData3dsForCreq = cutMethodDataForCreq(response);
        logger.info("Value for Creq = "+methodData3dsForCreq);
    }

    private String cutMethodDataForCreq(String iframe){
        StringBuffer stringBuffer = new StringBuffer(iframe);
        stringBuffer.delete(0,426);
        stringBuffer.delete(84,stringBuffer.length());

        return stringBuffer.toString();
    }

    protected void creqReturn(){
        response = given()
                .contentType(ContentType.URLENC)
                .accept("application/json")
                .params("threeDSMethodData",methodData3dsForCreq)
                .when()
                .post("https://pps03.fuib.com:9443/api/v4/"+tokenPGA.getPortalId()+"/payment/"+tokenPGA.getToken()+"/merchant-return")
                .then().extract().response().asString();

        logger.info("Return Creq = "+response);
        JSONObject json = new JSONObject(response);
        urlForCreq = json.getString("url");
        creq = json.getJSONObject("post").getString("creq");
    }

    protected void createIFrameWithCreq() throws IOException {
        String htmlCreq = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "  <body onload=\"document.getElementById('acs').submit()\">\n" +
                "    <form id=\"acs\" method=\"post\" action=\""+urlForCreq+"\">\n" +
                "      <input style=\"display:none\" type=\"submit\">\n" +
                "      <input type=\"hidden\" id=\"creq\" value=\""+creq+"\" name=\"creq\">\n" +
                "      </form>\n" +
                "  </body>\n" +
                "</html>";

        Files.write(Paths.get("/Users/user/Documents/Тесты/creq.html"),htmlCreq.getBytes(StandardCharsets.UTF_8));
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File("/Users/user/Documents/Тесты/creq.html"));
    }

    public void status(){

        response = given()
                .contentType(ContentType.URLENC)
                .accept("application/json")
                .when()
                .post("https://pps03.fuib.com:9443/api/v4/"+tokenPGA.getPortalId()+"/payment/"+tokenPGA.getToken()+"")
                .then().extract().response().asString();

        logger.info("Запрос статуса платежа = "+response);

    }
}
