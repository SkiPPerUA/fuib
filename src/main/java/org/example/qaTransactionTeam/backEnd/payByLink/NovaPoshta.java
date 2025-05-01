package org.example.qaTransactionTeam.backEnd.payByLink;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.json.JSONArray;

import static io.restassured.RestAssured.given;

public class NovaPoshta {

    private static final Logger logger = Logger.getLogger(NovaPoshta.class);
    private String response;
    private String idSenders;
    private int statusCode = 200;
    private TokenForPayByLink token = new TokenForPayByLink("pbl_test","password");

    public void addKeyNP(String key){
        given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "  \"api_key\": \""+key+"\"\n" +
                        "}")
                .when()
                .post(Configs.PAYHUB_HOST +"/cabina/pechkin/np/keys")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Add key NovaPoshta = "+key);
    }

    public void senders(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(Configs.PAYHUB_HOST +"/cabina/pechkin/np/senders")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Senders NovaPoshta = "+response);
        JSONArray jsonObject = new JSONArray(response);
        idSenders = jsonObject.getJSONObject(0).getString("id");
    }

    public void createNPDoc(String body, String invoiceID){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs.PAYHUB_HOST +"/cabina/pay-by-link/acquiring/invoices/"+invoiceID+"/delivery/np/documents")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Create document NovaPoshta = "+response);
    }

    public void contracts(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(Configs.PAYHUB_HOST +"/cabina/pechkin/np/senders/contacts?counterparty_id="+idSenders+"")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Contacts NovaPoshta = "+response);
    }

    public String getIdSenders() {
        return idSenders;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }
}
