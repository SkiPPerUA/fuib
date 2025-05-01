package org.example.qaTransactionTeam.backEnd.bus;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Domino extends Restful {

    private Apiman token = new Apiman("EKB","vip_manager", "test");

    public void find_customers(String client_id){
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .when()
                .get("https://api."+token.getEnvironment()+"-fuib.com:443/bus/domino/v2/customers/by-attributes?sirius_id="+client_id));
    }

    public void get_customers(String client_id){
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .when()
                .get("https://api."+token.getEnvironment()+"-fuib.com:443/bus/domino/v2/customers/"+client_id));
    }

    public void update_customers(String client_id, String body){
        setExpectedResponseCode(204);
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .body(body)
                .when()
                .put("https://api."+token.getEnvironment()+"-fuib.com:443/bus/domino/v2/customers/"+client_id));
        setExpectedResponseCode(200);
    }
}
