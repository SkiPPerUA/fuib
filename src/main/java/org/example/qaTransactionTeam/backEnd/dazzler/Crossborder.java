package org.example.qaTransactionTeam.backEnd.dazzler;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.Configs;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class Crossborder {

    private static Logger logger = Logger.getLogger(Crossborder.class);
    private Response response;
    private String transId;
    //private String token = new Trans_token_payhub(544881).getToken();
    private String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJMU1dxSEYzWUF0UDkzMmNXdm5ESXBzOHhqaHRQeWxiN0JOV2prVWhqcVhrIn0.eyJleHAiOjE3MjA3OTIxMzksImlhdCI6MTcyMDc5MTIzOSwianRpIjoiZjI4ZTc0YjktMDg4Ny00N2YyLWE3YjUtYmE1ZjEzYTg2MGQzIiwiaXNzIjoiaHR0cHM6Ly9hdXRoLnRlc3QtZnVpYi5jb20vYXV0aC9yZWFsbXMvcHVtYiIsImF1ZCI6WyJ0cmFuc2FjdGVyIiwiUEhUUk4iLCJPREIiLCJhY2NvdW50Il0sInN1YiI6ImZjZDViODMzLTUzMmMtNDg5Ny1hOGVjLWMzNTdlZGE4YzdlMiIsInR5cCI6IkJlYXJlciIsImF6cCI6InRyYW5zYWN0ZXIiLCJzZXNzaW9uX3N0YXRlIjoiNzg2ZWM4NjEtM2Y5My00ZmMxLTkzMzYtMWU1ZDg4ZTI4MzViIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJQSFRSTiI6eyJyb2xlcyI6WyJwaHVudHIiLCJwaGNvbSIsInBoX3NlIiwicGhfY2Jfb3AiLCJwaF9jdV9leCIsInBoX3BuX3RrbiIsInBoX3N3X3JlcSJdfSwidHJhbnNhY3RlciI6eyJyb2xlcyI6WyJwaF9wcF9hZG0iLCJwaF9kaW5mIiwicGhfcHBfb3AiXX0sIk9EQiI6eyJyb2xlcyI6WyJhY2NfbG10X3IiLCJwYXlfZG9jX2NydCIsInBheV9kb2NfZGVsIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6IiIsInNpZCI6Ijc4NmVjODYxLTNmOTMtNGZjMS05MzM2LTFlNWQ4OGUyODM1YiIsIm1lcmNoYW50X2lkIjoiMTA1NDYxOTctMGQyZi00MDU5LWI5YTItZDAxY2I5N2ViYTYxIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic3ZjX3RwdW9fcGgifQ.W5PNYosl9l0FVrwS-jglfCYKQmHYLWKR-kV46Taeojf1wURekLGkbXlyQA0NYNLy76mWi9bzjonSTMY5qOfiWnS6U7v23YadH_s9A0tQ2axN927Q-_I1ldY7K6MdKWwFkHTOvco6qlXvC2IwQp9otZNgRI_TDNDXwF47QpE2jqBEF3hZR2fRB9HsvD_qxHZ9MiwhA0acq63pwjPApGsnVzDtCRexs3q3_JnHo_TQOxT1TLUl9Zz1II1bFuyt9aqy0gR2rl9DxSkGk7l6ldByUexEElY3JyIQ-epFW8RlkcxPnrcZZU6IqUdS9GJc4HvwXYMf_brPeclLRDGyRjTZNw";
    private String host = Configs.PAYHUB_HOST;

    public void checkCardCountries(String pan){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode", "2")
                .header("Authorization","Bearer "+token)
                .body("{\n" +
                        "  \"receiver_card_number\": \""+pan+"\"\n" +
                        "}")
                .when()
                .post(host+"/dazzler/crossborder-payments/transfer/receiver-countries");
        logger.info("Card countries - "+getResponce());
    }

    public void getLimits(){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID","VladTest")
                .header("X-Systemcode", "2")
                .header("Authorization","Bearer "+token)
                .when()
                .get(host+"/dazzler/crossborder-payments/limits");
        logger.info("Limits - "+getResponce());
    }

    public void makeTrans(String body) {
        RestAssured.useRelaxedHTTPSValidation();
        initCrossborder(body);
        add_reciever_info();
        send_otp();
        try {
            makeScriptToBD();
        } catch (Throwable e) {
            logger.error(e);
        }
        finishTrans();
    }

    private void initCrossborder(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token)
                .header("X-Flow-Id",1)
                .header("X-Systemcode","test")
                .body(body)
                .when()
                .post(host+"/dazzler/crossborder-payments/transfer/init");
        logger.info("init - "+getResponce());
        transId = response.then().extract().response().jsonPath().get("inner_transfer_id");;
    }

    private void add_reciever_info(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token)
                .header("X-Flow-Id",1)
                .header("X-Systemcode","test")
                .body("{\n" +
                        "  \"inner_transfer_id\": \""+transId+"\",\n" +
                        "  \"receiver_name\" : \"Данило\",\n" +
                        "  \"receiver_middle_name\" : \"Олексійович\",\n" +
                        "  \"receiver_last_name\" : \"Тріфонов\"\n" +
                        "}")
                .when()
                .post(host+"/dazzler/crossborder-payments/transfer/add-receiver-info");
        logger.info("add-receiver-info - "+getResponce());
    }

    private void send_otp(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token)
                .header("X-Flow-Id",1)
                .header("X-Systemcode","test")
                .body("{\n" +
                        "  \"inner_transfer_id\": \""+transId+"\"\n" +
                        "}")
                .when()
                .post(host+"/dazzler/crossborder-payments/transfer/send-otp");
        logger.info("send_otp - "+getResponce());
    }

    private void makeScriptToBD() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            BDpostgre.BDpostgre("dc3-bgpg-001-vs.test-fuib.com:5432/","kreedb", Configs.POSTGRE_SQL_KREEDB_NAME, Configs.POSTGRE_SQL_KREEDB_PASSWORD);
            BDpostgre.updateSQL("UPDATE tb_sir_partner_rests SET rests_confirmed = 99999999999 WHERE sir_acc_id = '114622276';");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            BDpostgre.closeConn();
        }
    }

    private void finishTrans(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token)
                .header("X-Flow-Id",1)
                .header("X-Systemcode","test")
                .body("{\n" +
                        "    \"inner_transfer_id\": \""+transId+"\",\n" +
                        "    \"otp\": \"1111\"\n" +
                        "}")
                .when()
                .post(host+"/dazzler/crossborder-payments/transfer/make");
        logger.info("Finish - "+getResponce());
    }

    public String getResponce(){
        return response.then().extract().response().asString();
    }

    public String getTransId() {
        return transId;
    }
}
