package org.example.qaTransactionTeam.backEnd.pgaTransactions.typeTrans;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.pgaTransactions.Transaction;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C2A extends Transaction {

    private static final Logger logger = Logger.getLogger(C2A.class);
    private String response;

    public C2A(Map body) throws IOException, InterruptedException {
        createTrans(body);
        prepare_3ds();
        createIFrame();
        creqReturn();
        createIFrameWithCreq();
        //Пауза для прохождение 3дс на фрейме
        Thread.sleep(5000);
        System.out.println("Проверка платежа = https://dc1-tpgab-02-vs.fuib.com:8090/#payment-trx-list:startedAt=-30%D0%B4&token="+tokenPGA.getToken()+"&page=1&pageSize=20");
    }

    public void authorizationConfirm(){
        response = given()
                .contentType(ContentType.URLENC)
                .when()
                .post("https://pps03.fuib.com:9443/api/v4/"+tokenPGA.getPortalId()+"/payment/"+tokenPGA.getToken()+"/authorization-confirm")
                .then().extract().response().asString();

        logger.info("Confirm authorization = "+response);
    }

    public void authorizationDecline(){
        response = given()
                .contentType(ContentType.URLENC)
                .when()
                .post("https://pps03.fuib.com:9443/api/v4/"+tokenPGA.getPortalId()+"/payment/"+tokenPGA.getToken()+"/authorization-decline")
                .then().extract().response().asString();

        logger.info("Decline authorization = "+response);
    }

}
