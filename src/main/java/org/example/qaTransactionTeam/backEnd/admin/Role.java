package org.example.qaTransactionTeam.backEnd.admin;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Role {

    private static final Logger logger = Logger.getLogger(Role.class);
    private Response response;
    private String id;
    private int statusCode = 200;
    private int statusCode_put = 204;
    private final Trans_token_payhub token = new Trans_token_payhub();

    public void getGroups(){
        response = given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .when().get(token.getHost()+"/admin/merchants/admins/groups");
        logger.info("Get groups = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void getRoles(){
        response = given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .when().get(token.getHost()+"/admin/merchants/admins/roles");
        logger.info("Get roles = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void getAdmins(){
        response = given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .when().get(token.getHost()+"/admin/merchants/admins?limit=99999999");
        logger.info("Get admins = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void getAdmins(Map query){
        response = given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .queryParams(query).when().get(token.getHost()+"/admin/merchants/admins");
        logger.info(String.format("Get admins with query %s = %s",query,getResponse()));
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void getAdmins(String id){
        response = given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .when().get(token.getHost()+"/admin/merchants/admins/"+id);
        logger.info(String.format("Get admins {id = %s} = %s",id ,getResponse()));
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    public void addAdmins(String body){
        response = given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .body(body).when().post(token.getHost()+"/admin/merchants/admins");
        logger.info("Add admins = "+getResponse());
        Assert.assertEquals(response.getStatusCode(),statusCode);
        id = response.then().extract().response().jsonPath().getString("id");
    }

    public void updateAdmins(String id,String body){
        response = given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .body(body).when().put(token.getHost()+"/admin/merchants/admins/"+id);
        try {
            Assert.assertEquals(response.getStatusCode(),statusCode_put);
        }catch (AssertionError e){
            logger.error("Update admins = "+getResponse());
            Assert.fail(e.toString());
        }
    }

    public String getResponse() {
        return response.then().extract().response().asString();
    }

    public Role setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Role setStatusCode_put(int statusCode_put) {
        this.statusCode_put = statusCode_put;
        return this;
    }

    public String getId() {
        return id;
    }
}
