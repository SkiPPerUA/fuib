package org.example.qaTransactionTeam.backEnd.admin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;
import static io.restassured.RestAssured.given;

public class MerchantsConfigs extends Restful {

    private static final Logger logger = Logger.getLogger(MerchantsConfigs.class);
    private final Trans_token_payhub token = new Trans_token_payhub();


    public void getConfigs(String id){
        logger.info(String.format("Get configs {id = %s}",id));
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .when().get(token.getHost()+"/admin/merchants/"+id+"/configs"));
    }

    public void getConfigsTransactions(String id, String transaction_type){
        logger.info(String.format("Get configs {id = %s} transaction_type %s",id,transaction_type));
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .when().get(token.getHost()+"/admin/merchants/"+id+"/configs/transactions?transaction_type="+transaction_type));
    }

    public void updateConfigsTransactions(String id, String body){
        logger.info("Update configs transactions");
        request(given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .body(body)
                .when().put(token.getHost()+"/admin/merchants/"+id+"/configs/transactions"));
    }

    public void addConfigs(String id, String body){
        logger.info("Add configs");
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .body(body).when().post(token.getHost()+"/admin/merchants/"+id+"/configs"));
    }

    public void addConfigsPga(String id, String body){
        RestAssured.useRelaxedHTTPSValidation();
        logger.info("Add configs pga");
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .body(body).when().post(token.getHost()+"/admin/merchants/"+id+"/configs/pga"));
    }

    public void updateConfigsPga(String id, String config, String body){
        RestAssured.useRelaxedHTTPSValidation();
        logger.info("Update configs pga");
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .body(body).when().put(token.getHost()+"/admin/merchants/"+id+"/configs/pga/"+config));
    }

    public void addConfigsInstallments(String id, String body){
        RestAssured.useRelaxedHTTPSValidation();
        logger.info("Add configs installments");
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .body(body).when().post(token.getHost()+"/admin/merchants/"+id+"/installments/points"));
    }

    public void updateConfigsInstallments(String id, String point_id, String body){
        RestAssured.useRelaxedHTTPSValidation();
        expectedCode = 204;
        logger.info("Update configs installments");
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .body(body).when().put(token.getHost()+"/admin/merchants/"+id+"/installments/points/"+point_id));
        expectedCode = 200;
    }

    public void updateConfigs(String id,String body){
        logger.info("Update configs");
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .body(body).when().put(token.getHost()+"/admin/merchants/"+id+"/configs"));
    }

    public void createAdmins(String body){
        logger.info("Create admins");
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .body(body).when().post(token.getHost()+"/admin/merchants/admins"));
    }

    public void updateAdmins(String admins_id, String body){
        logger.info("Update admins");
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .body(body).when().put(token.getHost()+"/admin/merchants/admins/"+admins_id));
    }

    public void getAdmins(String admins_id){
        logger.info("Get admins");
        request(given().contentType(ContentType.JSON).header("Authorization", "Bearer "+token.getToken())
                .when().get(token.getHost()+"/admin/merchants/admins/"+admins_id));
    }
}
