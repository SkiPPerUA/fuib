package org.example.qaTransactionTeam.backEnd.payByLink;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

import static io.restassured.RestAssured.given;

class Invoice {

    private static final Logger logger = Logger.getLogger(Invoice.class);
    protected String response;
    private String typeInvoice;
    protected String invoiceId;
    private String externalId;
    public static int statusCode = 200;
    protected TokenForPayByLink token;
    private String payment_url;
    protected boolean installment = false;
    private JSONArray transactions;

    protected void createInvoice(String type,String body){

        token = new TokenForPayByLink("pbl_test","password");
        typeInvoice = type;
        externalId = UUID.randomUUID().toString();
        logger.info("External Id = "+externalId);
        String body1 = "{\n" +
                "  \"external_id\": \"VladAuto:"+externalId+"\",\n" +
               body +
                "}";

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body1)
                .when()
                .post(Configs1.PAYHUB_HOST +"/cabina/pay-by-link/"+type+"/invoices")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Create invoice = "+response);
        JSONObject jsonObject = new JSONObject(response);
        invoiceId = jsonObject.getString("id");
        if(statusCode == 200) {
            payment_url = jsonObject.getString("payment_url");
        }

    }

    public void statusInvoice(String params){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(Configs1.PAYHUB_HOST +"/cabina/pay-by-link/"+typeInvoice+"/invoices"+params+"")
                .then()
                .statusCode(statusCode).extract().response().asString();

        logger.info("status ("+params+") = "+response);
    }

    public void changeTTL(String TTL){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "\t\"valid_till\": \""+TTL+"\"\n" +
                        "}")
                .when()
                .put(Configs1.PAYHUB_HOST +"/cabina/pay-by-link/"+typeInvoice+"/invoices/"+invoiceId+"/valid_till")
                .then()
                .statusCode(204)
                .extract().response().asString();

        logger.info("Change TTL изменен на = "+TTL);
    }

    public void blockInvoice(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{}")
                .when()
                .put(Configs1.PAYHUB_HOST +"/cabina/pay-by-link/"+typeInvoice+"/invoices/"+invoiceId+"/block")
                .then()
                .statusCode(statusCode).extract().response().asString();
    }

    public void unblockInvoice(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{}")
                .when()
                .put(Configs1.PAYHUB_HOST +"/cabina/pay-by-link/"+typeInvoice+"/invoices/"+invoiceId+"/unblock")
                .then()
                .statusCode(statusCode).extract().response().asString();
    }

    public void cloneInvoice(){
        externalId = UUID.randomUUID().toString();
        logger.info("External Id (clone) = "+externalId);
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "  \"external_id\": \"Clone:"+externalId+"\"\n" +
                        "}")
                .when()
                .post(Configs1.PAYHUB_HOST +"/cabina/pay-by-link/"+typeInvoice+"/invoices/"+invoiceId+"/clone")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Clone invoice = "+response);
    }

    public void payInvoice(){
        System.out.println("Оплата - "+payment_url);
        //Ожидание ввода данных
        try {
                if(typeInvoice.equals("acquiring")){
                     Thread.sleep(30000);
                }else {
                    Thread.sleep(50000);
                }
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    public void refundInvoice(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs1.PAYHUB_HOST +"/cabina/pay-by-link/"+typeInvoice+"/invoices/"+invoiceId+"/refund")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Refund invoice = "+response);
    }

    public void statusTransactionByInvoice(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(Configs1.PAYHUB_HOST +"/cabina/pay-by-link/"+typeInvoice+"/invoices/"+invoiceId+"/transactions")
                .then()
                .statusCode(statusCode).extract().response().asString();

        transactions = new JSONArray(response);
        logger.info("Transactions = "+response);
    }

    public void changeInvoice(String descr, String sum){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "  \"description\": \""+descr+"\",\n" +
                        "  \"amount\": "+sum+"\n" +
                        "}")
                .when()
                .put(Configs1.PAYHUB_HOST +"/cabina/pay-by-link/"+typeInvoice+"/invoices/"+invoiceId+"")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Change invoice = "+response);

    }

    public String getResponse() {
        return response;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getPayment_url() {
        return payment_url;
    }

    public JSONArray getTransactions() {
        return transactions;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
