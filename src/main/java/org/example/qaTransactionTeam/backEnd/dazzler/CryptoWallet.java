package org.example.qaTransactionTeam.backEnd.dazzler;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class CryptoWallet {

    protected static final Logger logger = Logger.getLogger(CryptoWallet.class);
    private String response;
    private String host = "https://innsmouth.test-fuib.com";

    public void createAcc(String body){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .body(body)
                .when()
                .post(host + "/dazzler/crypto-wallet/accounts")
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("Создание Crypto Wallet - " + response);
    }

    public void balance(String body){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .body(body)
                .when()
                .post(host + "/dazzler/crypto-wallet/accounts/balances")
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("Баланс Crypto Wallet - " + response);
    }

    public void balance_by_coin(String body, String coin){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .body(body)
                .when()
                .post(host + "/dazzler/crypto-wallet/accounts/balances/"+coin)
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("Баланс Crypto Wallet в валюте "+coin+" - " + response);
    }

    public void history(String body){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .body(body)
                .when()
                .post(host + "/dazzler/crypto-wallet/accounts/history")
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("History Crypto Wallet - " + response);
    }

    public void history_by_transactions(String transactions){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .when()
                .get(host + "/dazzler/crypto-wallet/accounts/history/"+transactions)
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("History Crypto Wallet по транзакции "+transactions+" - " + response);
    }

    public void cryptos_list(String body){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .body(body)
                .when()
                .post(host + "/dazzler/crypto-wallet/cryptos")
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("Список доступных крипто валют - " + response);
    }

    public void freeze(String body){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .body(body)
                .when()
                .post(host + "/dazzler/crypto-wallet/freeze")
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("Freeze - " + response);
    }

    public void deposit(String body){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .body(body)
                .when()
                .post(host + "/dazzler/crypto-wallet/deposit")
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("Deposit - " + response);
    }

    public void withdraw(String body){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .contentType(ContentType.JSON)
                .header("x-flow-id", "test")
                .header("Authorization", "Bearer test")
                .body(body)
                .when()
                .post(host + "/dazzler/crypto-wallet/withdraw")
                .then()
                //.statusCode(200)
                .extract()
                .response().asString();

        logger.info("Withdraw - " + response);
    }

}
