package org.example.qaTransactionTeam.backEnd.kofola;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Kofola extends Restful {

    private String env = "test";
    private Apiman token = new Apiman("EKB","svc_ph_test_doc","s53cC8eBY%b#Jmg!RhM948J!F3nbucrr", env);

    public void getAccountByIban(String iban){
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .header("X-Flow-ID","test")
                .header("X-Systemcode","test")
                .body("{\n" +
                        "\"iban\": \""+iban+"\"\n" +
                        "}")
                .post("https://core-dbgate."+env+"-fuib.com/kofola/v1/accounts/by-filters?limit=100&offset=0"));
    }
}
