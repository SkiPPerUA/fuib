package org.example.qaTransactionTeam.backEnd.bus;

import io.restassured.http.ContentType;
import org.checkerframework.checker.units.qual.A;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Domino extends Restful {

    private Apiman token = new Apiman("EKB","corp_cust_r", "test");

    //https://confluence.fuib.com/pages/viewpage.action?pageId=314509085

    public void find_customersBYattributes(String body){
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .body(body)
                .when()
                .post("https://api."+token.getEnvironment()+"-fuib.com:443/bus/wolverine/v2/customers/by-attributes"));
    }

    public void get_customers(String client_id){
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .header("X-Flow-ID", "11")
                .header("X-Systemcode","12312")
                .when()
                .get("https://api."+token.getEnvironment()+"-fuib.com:443/bus/wolverine/v2/customers/"+client_id));
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
