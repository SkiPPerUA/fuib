package org.example.qaTransactionTeam.backEnd.payByLink;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;

import static io.restassured.RestAssured.given;

public class InvoiceP4P extends Invoice{

    private String type = "p4p";

    public InvoiceP4P(String body){
        createInvoice(type,body);
    }

    public void enrollInvoice(String transId){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "  \"transaction_id\": \""+transId+"\"\n" +
                        "}")
                .when()
                .post(Configs1.PAYHUB_HOST +"/cabina/pay-by-link/"+type+"/invoices/"+invoiceId+"/enroll")
                .then()
                .statusCode(200)
                .extract().response().asString();
    }

}
