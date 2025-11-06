package org.example.qaTransactionTeam.backEnd.transaction;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.json.JSONException;
import org.testng.Assert;
import static io.restassured.RestAssured.given;


public class AppleGooglePay {

    private static final Logger logger = Logger.getLogger(AppleGooglePay.class);
    protected Trans_token_itm createToken = new Trans_token_itm();
    private Response response;
    protected int statusCode = 200;
    protected String sessionId;
    protected boolean need_threeDS = false;
    protected String typeTrans;
    protected int version3ds;
    protected String recurrent_payment_id;
    private final String host = "https://tsystestapi.pumb.ua";

    public void register(String body) throws JSONException {
        response = given()
                .header("Token", createToken.getToken())
                .header("x-forwarded-for","127.0.0.1")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(host+"/vmt/api/purchase/"+createToken.getAcqID()+"/preauth");
        logger.info("Регистрация - " + getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
        sessionId = response.then().extract().response().jsonPath().getString("session_id");
        recurrent_payment_id = response.then().extract().response().jsonPath().getString("recurrent.recurrent_payment_id");

        if (version3ds == 2) {
            String creq = response.then().extract().response().jsonPath().getString("3ds.c_req");
            String url_three_DS = response.then().extract().response().jsonPath().getString("3ds.url");
            if(!StringUtils.isEmpty(url_three_DS)) {
                ThreeDS.createIFrame(url_three_DS, creq);
                need_threeDS = true;
            }
        }else {
            //logger.info("Транзакция " + typeTrans + " ("+sessionId+") - зарегистрирована без 3дс");
        }
    }

    public void cres(){
        String bodyCrec = "{\n" +
                "\t\"session_id\": \""+sessionId+"\",\n" +
                "\t\"info_3ds\":{\n" +
                "\t\t\"ip\":\"0.0.0.127\",\n" +
                "\"c_res\":\"eyJhY3NUcmFuc0lEIjoiMDA2MWJlNDAtZDAzMi00MWViLThhYTUtMDY1N2JhMzNhN2Q2IiwidHJhbnNTdGF0dXMiOiJZIiwidGhyZWVEU1NlcnZlclRyYW5zSUQiOiJmZjY0MjQ2MC1kMDMxLTQxZWItOGFhNS04ZDU1NDU5NzQ5NDgiLCJtZXNzYWdlVHlwZSI6IkNSZXMiLCJtZXNzYWdlVmVyc2lvbiI6IjIuMS4wIn0\"\n" +
                "\t}\n" +
                "}";

        response = given()
                .header("Token", createToken.getToken())
                .header("x-forwarded-for","127.0.0.1")
                .contentType(ContentType.JSON)
                .body(bodyCrec)
                .when()
                .post( host+"/vmt/api/purchase/"+createToken.getAcqID()+"/preauth");
        recurrent_payment_id = response.then().extract().response().jsonPath().getString("recurrent.recurrent_payment_id");
        logger.info("3ds - " + getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void otp(String otp){
        String bodyOtp = "{\n" +
                "\t\"session_id\": \""+sessionId+"\",\n" +
                "\t\"info_3ds\":{\n" +
                "\t\t\"ip\":\"0.0.0.127\",\n" +
                "\t\t\"otp\":\""+otp+"\"\n" +
                "\t}\n" +
                "}";

        response = given()
                .header("Token", createToken.getToken())
                .header("x-forwarded-for","127.0.0.1")
                .contentType(ContentType.JSON)
                .body(bodyOtp)
                .when()
                .post(host+"/vmt/api/purchase/"+createToken.getAcqID()+"/preauth");
        logger.info("Otp - " + getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void finalization_three_DS(){
        String bodyOtp = "{\n" +
                "\t\"session_id\": \""+sessionId+"\",\n" +
                "\t\"info_3ds\":{\n" +
                "\t\t\"ip\":\"0.0.0.127\"\n" +
                "\t}\n" +
                "}";

        response = given()
                .header("Token", createToken.getToken())
                .header("x-forwarded-for","127.0.0.1")
                .contentType(ContentType.JSON)
                .body(bodyOtp)
                .when()
                .post(host+"/vmt/api/purchase/"+createToken.getAcqID()+"/preauth");
        logger.info("Finalization 3DS - " + getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void finish(String amount){
        String finish = "{\n" +
                "\t\"amount\":\""+amount+"\",\n" +
                "\"additional_data\":{\n" +
                "  \"recipients\": [{     \n" +
                "    \"first_name\": \"Ivan\",\n" +
                "    \"last_name\": \"Ivanov\",\n" +
                "    \"amount\": 100,\n" +
                "    \"account_number\": \"UA213223130000026007233566001\",\n" +
                "    \"independent_sales_organization_id\": \"3056715233\",\n" +
                "    \"merchant_url\": \"https://merchant111.com?com=12345\",\n" +
                "    \"payment_url\": \"https://payment111.com?com=12345\"\n" +
                "  }, {\n" +
                "    \"first_name\": \"Ivan\",\n" +
                "    \"last_name\": \"Petrov\",\n" +
                "    \"amount\": 200,\n" +
                "    \"account_number\": \"UA213223130000026007233566001\",\n" +
                "    \"independent_sales_organization_id\": \"3056715233\",\n" +
                "    \"merchant_url\": \"https://merchant111.com?com=12345\",\n" +
                "    \"payment_url\": \"https://payment111.com?com=12345\"\n" +
                "  }],\n" +
                "  \"sender\": {\n" +
                "    \"first_name\": \"Ivan\",  \n" +
                "    \"last_name\": \"Ivanov\",\n" +
                "    \"account_number\": \"UA213223130000026007233566001\"  \n" +
                "  },\n" +
                "  \"details\": {\n" +
                "    \"additional_message\": \"Lorem ipsum dolor sit amet\",\n" +
                "    \"source\": \"01\",\n" +
                "    \"submerchant_url\": \"https://some.url.com\"\n" +
                "  }\n" +
                "}"+
                "}\n";

        response = given()
                .header("Token", createToken.getToken())
                .header("x-forwarded-for","127.0.0.1")
                .contentType(ContentType.JSON)
                .body(finish)
                .when()
                .post(host+"/vmt/api/purchase/"+createToken.getAcqID()+"/"+sessionId+"/finalize");
        logger.info("Finalize - " + getResponse());
        //Assert.assertEquals(response.getStatusCode(),statusCode);
    }


    public String getSessionId() {
        return sessionId;
    }

    public String getResponse() {
        return response.then().extract().response().asString();
    }
}
