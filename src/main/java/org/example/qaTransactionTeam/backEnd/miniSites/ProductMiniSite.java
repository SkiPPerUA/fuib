package org.example.qaTransactionTeam.backEnd.miniSites;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Configs;

import static io.restassured.RestAssured.given;

public class ProductMiniSite extends MiniSite{

    private static final Logger logger = Logger.getLogger(ProductMiniSite.class);
    private String productId;
    private String response;

    public void addProductForMiniSite(String body, String siteId) {
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body(body)
                .when()
                .post(Configs.PAYHUB_HOST + "/cabina/marketplaces/"+siteId+"/products")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Add Product For Mini Site = " + response);
        productId = response;
    }

    public void getProductForMiniSite(String siteId) {
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .when()
                .get(Configs.PAYHUB_HOST + "/cabina/marketplaces/" + siteId + "/products")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Get Product For Mini Site = " + response);
    }

    public void changePriceProduct(int sum) {
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body("{\n" +
                        "  \"data\": [\n" +
                        "    {\n" +
                        "      \"price\": "+sum+",\n" +
                        "      \"product_id\": \""+productId+"\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when()
                .post(Configs.PAYHUB_HOST + "/cabina/marketplaces/" + siteId + "/products/update")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Change Price Product = " + response);
    }

    public void changeStatusProduct(String status, String siteId, String productId) {
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body("{\n" +
                        "  \"status\": \""+status+"\"\n" +
                        "}")
                .when()
                .put(Configs.PAYHUB_HOST + "/cabina/marketplaces/" + siteId + "/products/"+productId+"/status")
                .then()
//                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Change Status Product on "+status);
    }

    public void editProduct(String body) {
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body(body)
                .when()
                .put(Configs.PAYHUB_HOST + "/cabina/marketplaces/" + siteId + "/products/"+productId+"")
                .then()
                .statusCode(statusCode)
                .extract().response().asString();

        logger.info("Edit Product = " + response);
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public String getResponse() {
        return response;
    }
}
