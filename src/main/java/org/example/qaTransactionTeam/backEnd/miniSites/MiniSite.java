package org.example.qaTransactionTeam.backEnd.miniSites;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.payByLink.TokenForPayByLink;
import org.example.qaTransactionTeam.backEnd.utils.Configs;

import static io.restassured.RestAssured.given;

public class MiniSite {

    static final Logger logger = Logger.getLogger(MiniSite.class);
    protected TokenForPayByLink token = new TokenForPayByLink("pbl_test","password");
    private String response;
    protected int statusCode = 200;
    protected String siteId;

    public void createMiniSite(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs.PAYHUB_HOST +"/cabina/marketplaces")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Create Mini Site = "+response);
        siteId = response;
    }

    public void createContactsForMiniSite(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "  \"marketplace_id\": \""+siteId+"\",\n" +
                        body +
                        "}")
                .when()
                .post(Configs.PAYHUB_HOST +"/cabina/marketplaces/"+siteId+"/contacts/bulk")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Create Contacts For Mini Site = "+response);
    }

    public void changeStatusForMiniSite(String body, String siteId){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .put(Configs.PAYHUB_HOST +"/cabina/marketplaces/"+siteId+"/status")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Change Status For Mini Site = "+response);
    }

    public void editMiniSite(String body){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .put(Configs.PAYHUB_HOST +"/cabina/marketplaces/"+siteId+"")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Edit Mini Site = "+response);
    }

    public void deleteMiniSite(String siteId){
        response = given()
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .delete(Configs.PAYHUB_HOST +"/cabina/marketplaces/"+siteId+"")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Delete Mini Site");
    }

    public void getMiniSite(String site_Id){
        response = given()
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(Configs.PAYHUB_HOST +"/cabina/marketplaces"+site_Id)
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Get Mini Site = "+response);
    }

    public void getMiniSite(){
        getMiniSite("");
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getResponse() {
        return response;
    }
}
